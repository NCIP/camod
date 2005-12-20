/**
 * 
 * $Id: TestUtil.java,v 1.4 2005-12-20 15:54:30 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/12/13 20:28:16  pandyas
 * Modified inForm.setParameterValue() to use inForm.getScriptableObject().setParameterValue() instead
 *
 * 
 */

package web.util;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.impl.AnimalModelManagerSingleton;
import gov.nih.nci.camod.util.GUIDGenerator;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.security.junk.RandomIntGenerator;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static void moveModelToEditedApproved(String inModelName) throws Exception {
        AnimalModel theQBEAnimalModel = new AnimalModel();

        theQBEAnimalModel.setModelDescriptor(inModelName);

        List theModelList = Search.query(theQBEAnimalModel);

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
    	
    	System.out.println("<TestUtil> Entered setRandomValues");    	

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
                                System.out.println("This property is assigned the value other: " + thePropertyName);
                            } else {
                                BeanUtils.setProperty(inDataObject, thePropertyName, theOptions[theOptions.length - 1]);
                                System.out.println("setting random value to " + thePropertyName);
                            }
                        } else {

                            // If we're not setting the other option, skip these
                            if (thePropertyName.indexOf("other") == -1 || setOtherValues == true) {
                            	System.out.println("This property is not a collection and randomly set: " + thePropertyName);
                                BeanUtils.setProperty(inDataObject, thePropertyName, GUIDGenerator.getInstance()
                                        .genNewGuid());
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
    	System.out.println("<TestUtil> Exiting setRandomValues");
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

                    System.out.println("Setting value: " + thePropertyName + " to " + thePropertyValue.toString());
                    //inForm.setParameter(thePropertyName, thePropertyValue.toString());
                    inForm.getScriptableObject().setParameterValue(thePropertyName, thePropertyValue.toString());
                }
            }
        }
        
    	System.out.println("<TestUtil> Exited setValuesOnForm");        
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
