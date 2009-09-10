/**
 * 
 * $Id: TestUtil.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.8  2008/10/01 23:54:11  schroedn
 * Junit test fixes for caMOD 2.5 - revision 1
 *
 * Revision 1.7  2006/10/23 16:50:25  pandyas
 * modified printout for tests
 *
 * Revision 1.6  2006/05/08 14:23:35  georgeda
 * Reformat and clean up warnings
 *
 * Revision 1.5  2006/01/06 16:11:07  pandyas
 * Modified to include methods to test if the populate method returns complete and correct data - initial modifications
 *
 * Revision 1.4  2005/12/20 15:54:30  pandyas
 * Added method getTextOnPage:  To capture failed validation text during  debugging
 *
 * Revision 1.3  2005/12/13 20:28:16  pandyas
 * Modified inForm.setParameterValue() to use inForm.getScriptableObject().setParameterValue() instead
 *
 * 
 */

package unit.java.web.util;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.impl.AnimalModelManagerSingleton;
import gov.nih.nci.camod.util.GUIDGenerator;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.security.junk.RandomIntGenerator;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebResponse;

public class TestUtil {
	
    static protected WebConversation myWebConversation = new WebConversation();    

    static private RandomIntGenerator ourRandomIntGenerator = new RandomIntGenerator(10, 20);
    
    static protected Map<String, String> ourPairList = new HashMap<String, String>();
    
    public static Map getMap(){
    	return ourPairList;
    }

    public static void moveModelToEditedApproved(String inModelName) throws Exception {
        AnimalModel theQBEAnimalModel = new AnimalModel();

        theQBEAnimalModel.setModelDescriptor(inModelName);

        // HQL problem
        List theModelList = Search.query(theQBEAnimalModel);
        
//        SearchForm inSearchData = new SearchForm();
//        inSearchData.setModelDescriptor(inModelName);
//        List theModelList = QueryManagerSingleton.instance().searchForAnimalModels(inSearchData);
//        
        if (theModelList.size() > 1) {
            throw new IllegalArgumentException("More than one model matched the name: " + inModelName);
        } else if (theModelList.size() == 0) {
            throw new IllegalArgumentException("No models matched the name: " + inModelName);
        } else {

            AnimalModel theAnimalModel = (AnimalModel) theModelList.get(0);
            theAnimalModel.setState("Edited-approved");
            AnimalModelManagerSingleton.instance().save(theAnimalModel);

        }
    }

    public static void setRandomValues(Object inDataObject, WebForm inForm, boolean setOtherValues,
            List inParamsToIgnore) throws Exception {
    	
    	System.out.println("\n<TestUtil> Entered setRandomValues");    	

        Map theBeanProps = PropertyUtils.describe(inDataObject);
        Iterator theProperties = theBeanProps.entrySet().iterator();

        // loop thru bean properties
        while (theProperties.hasNext()) {

            Map.Entry theEntry = (Map.Entry) theProperties.next();

            if (theEntry.getKey() != null) {

                String thePropertyName = theEntry.getKey().toString();

                PropertyDescriptor thePropertyDescriptor = PropertyUtils.getPropertyDescriptor(inDataObject,
                        thePropertyName);

                Object thePropertyValue = theEntry.getValue();

                // Only override non-set values  (theForm.set...)
                if (thePropertyValue == null && !inParamsToIgnore.contains(thePropertyName)) {
                    
                	
                    // check if property is a string
                    if (thePropertyDescriptor.getPropertyType().getName().equals("java.lang.String")) {

                        String[] theOptions = inForm.getOptionValues(thePropertyName);

                        if (theOptions.length > 0) {
                        	System.out.println("This property is a collection: " + thePropertyName);
                            if (Arrays.asList(theOptions).contains(Constants.Dropdowns.OTHER_OPTION)
                                    && setOtherValues == true) {
                                BeanUtils.setProperty(inDataObject, thePropertyName, Constants.Dropdowns.OTHER_OPTION);
                                System.out.println("Collection PropertyName: " + thePropertyName + "PropertyValue:" + Constants.Dropdowns.OTHER_OPTION);
                            } else {
                            	String newOption = theOptions[theOptions.length - 1];
                                BeanUtils.setProperty(inDataObject, thePropertyName, newOption);
                                System.out.println("Collection PropertyName: " + thePropertyName + "PropertyValue:" + newOption);
                            }
                        } else {

                            // If we're not setting the other option, skip these
                            if (thePropertyName.indexOf("other") == -1 || setOtherValues == true) {
                            	String newRandomValue = GUIDGenerator.getInstance().genNewGuid();                            	
                                BeanUtils.setProperty(inDataObject, thePropertyName, newRandomValue);
                                System.out.println("The PropertyName in other loop: " + thePropertyName + "\t The PropertyValue: "
                                		+ newRandomValue);
                            }
                        }
                    } else if (thePropertyDescriptor.getPropertyType().getName().equals("java.lang.Long")) {
                        BeanUtils.setProperty(inDataObject, thePropertyName, new Long(ourRandomIntGenerator.draw()));
                    } else if (thePropertyDescriptor.getPropertyType().getName().equals("java.lang.Double")) {
                        BeanUtils.setProperty(inDataObject, thePropertyName, new Double(ourRandomIntGenerator.draw()));
                    } else {
                        // Ignore for now
                    }
                }
            }
        }
    	System.out.println("<TestUtil> Exiting setRandomValues\n");
    }
    
    public static void setRandomValues(Object inDataObject, WebForm inForm, boolean setOtherValues) 
    	throws Exception  {
    	
        setRandomValues(inDataObject, inForm, setOtherValues, new ArrayList());
    	
    }
    

    public static void setValuesOnForm(Object inDataObject, WebForm inForm) throws Exception {
    	
    	System.out.println("<TestUtil> Entered setValuesOnForm");

        Map theBeanProps = PropertyUtils.describe(inDataObject);
        Iterator theProperties = theBeanProps.entrySet().iterator();

        // loop thru bean properties
        while (theProperties.hasNext()) {

            Map.Entry theEntry = (Map.Entry) theProperties.next();

            if (theEntry.getKey() != null) {
                String thePropertyName = theEntry.getKey().toString();
                Object thePropertyValue = theEntry.getValue();

                if (thePropertyValue != null && inForm.hasParameterNamed(thePropertyName)) {
                    System.out.println("Setting value: " + thePropertyName + " to: " + thePropertyValue.toString());
                    inForm.getScriptableObject().setParameterValue(thePropertyName, thePropertyValue.toString());
                    //WebForm savedForm = new WebForm;
                    savePropertyNameValue(thePropertyName, thePropertyValue.toString());
                }
            }
        }
        //Save ParameterName and ParameterValue to copare during populate testing

    	System.out.println("<TestUtil> Exited setValuesOnForm");        
    }
    
    public static void savePropertyNameValue(String inPropertyName, String inPropertyValue){
    	ourPairList.put(inPropertyName, inPropertyValue);
    	System.out.println("Added Property and Value to map: " + "\t" + inPropertyName + "\t" +  inPropertyValue);

    }
    /*
     * This simple method is used to capture text on a page.
     * Validation text is captured for debugging purposes.
     */
    public static void getTextOnPage(WebResponse theCurrentPage, String inStartText, String inEndText) throws Exception {
    	
    	System.out.println("<TestUtil> entered getTextOnPage method");

            String thePageText = theCurrentPage.getText();

            int theFirstIndex = thePageText.indexOf(inStartText);
            int theLastIndex = thePageText.indexOf(inEndText);

            if (theFirstIndex < theLastIndex && thePageText !=null) {
                thePageText = thePageText.substring(theFirstIndex, theLastIndex);
                System.out.println("\nThe Text: " +thePageText);                
    		} else {
    			System.out.println("\nThere is no Text on the current page: ");
    		}
    	
    	System.out.println("<TestUtil> exited getTextOnPage method");    	
		
	}
}
