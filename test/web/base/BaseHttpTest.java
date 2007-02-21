/**
 * 
 * $Id: BaseHttpTest.java,v 1.10 2007-02-21 17:08:09 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2006/10/11 15:17:49  pandyas
 * added debug statements
 *
 * Revision 1.8  2006/04/27 15:08:33  pandyas
 * Modified while testing caMod 2.1
 *
 * Revision 1.7  2006/04/17 19:37:32  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.6  2006/01/06 16:11:48  pandyas
 * Modified to include methods to test if the populate method returns complete and correct data - initial modifications
 *
 * Revision 1.5  2005/12/14 15:23:28  pandyas
 * took out search for pubID - not callled anywhere
 *
 * Revision 1.4  2005/12/13 19:17:38  pandyas
 * Modified to ignore values on submit page but not on display page
 *
 * 
 */
package web.base;

import gov.nih.nci.camod.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import web.util.TestUtil;
import junit.framework.TestCase;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;


/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class BaseHttpTest extends TestCase {

    protected WebConversation myWebConversation = new WebConversation();

    public BaseHttpTest(String testName) {
        super(testName);
    }

    protected void assertCurrentPageContains(String inTextToFind) throws Exception {
        String theCurrentPage = myWebConversation.getCurrentPage().getText();
        assertTrue("Page does not contain text: " + inTextToFind, theCurrentPage.indexOf(inTextToFind) != -1);
    }

    protected void assertCurrentPageDoesNotContain(String inTextToFind) throws Exception {
        String theCurrentPage = myWebConversation.getCurrentPage().getText();
        assertTrue("Page incorrectly contains text: " + inTextToFind, theCurrentPage.indexOf(inTextToFind) == -1);
        
    }

    protected void navigateToLoginPage() throws Exception {
        
        System.out.println("<navigateToLoginPage> Enter" );

        ResourceBundle theBundle = ResourceBundle.getBundle("test");

        String theHost = theBundle.getString("testhost");
        System.out.println("<navigateToLoginPage> theHost: " + theHost);
        
        // Obtain the main page on the meterware web site
        WebRequest theRequest = new GetMethodWebRequest(theHost);

        WebResponse theResponse = myWebConversation.getResponse(theRequest);

        if (theResponse.getText().indexOf("Currently logged in as") != -1) {
            logoutOfApplication();
            navigateToLoginPage();
        }

        // We may or may not have to hit the agreement link
        WebLink theLink = theResponse.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "CLICKING HERE");

        if (theLink != null) {
            theLink.click();
        }
    }

    protected void navigateToModelForEditing(String inModel) throws Exception {

        // Assume we are logged in. Click the SUBMIT button
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                "SUBMIT MODELS");

        assertNotNull("Couldn't find link to submission page", theLink);

        theLink.click();

        // We may or may not have to hit the agreement link
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, inModel);

        assertNotNull("Couldn't find link to edit model", theLink);

        theLink.click();
        assertCurrentPageContains("Editing Model:<b>" + inModel);

    }

    protected void navigateToModelForSearching(String inModelName) throws Exception {

        // Assume we are logged in. Click the SUBMIT button
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                "SEARCH MODELS");

        assertNotNull("Couldn't find link to search page", theLink);

        theLink.click();

        // Fill in the info for the search
        WebForm theForm = myWebConversation.getCurrentPage().getFormWithName("searchForm");

        assertNotNull("Unable to find form for search", theForm);

        theForm.setParameter("modelDescriptor", inModelName);
        theForm.submit();

        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, inModelName);

        assertNotNull("Couldn't find link to search for model", theLink);

        theLink.click();

        assertCurrentPageContains("Viewing Model:");
        assertCurrentPageContains(inModelName);

    }

    protected void navigateToSpecificSearchPage(String inModelName, String inLinkText) throws Exception {

        navigateToModelForSearching(inModelName);

        // Assume we are logged in. Click the SUBMIT button
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                inLinkText);

        assertNotNull("Couldn't find link to specific search page: " + inLinkText, theLink);

        theLink.click();
    }

    protected void logoutOfApplication() throws Exception {

        ResourceBundle theBundle = ResourceBundle.getBundle("test");

        String theHost = theBundle.getString("testhost");

        // Obtain the main page
        WebRequest theRequest = new GetMethodWebRequest(theHost);
        WebResponse theResponse = myWebConversation.getResponse(theRequest);

        WebLink theLink = theResponse.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Log out");

        if (theLink != null) {
            theResponse = theLink.click();
        }
    }

    protected void loginToApplication(String inUsername, String inPassword) throws Exception {

        System.out.println("<loginToApplication> Enter" );
        navigateToLoginPage();

        WebForm theForm = myWebConversation.getCurrentPage().getFormWithName("loginForm");
        System.out.println("<loginToApplication> theForm: " + theForm );

        theForm.setParameter("username", inUsername);
        theForm.setParameter("password", inPassword);
        
        //Handling re-direct in HttpUnit
        WebResponse response = theForm.submit();
        WebRequest refreshReq;
        refreshReq =  response.getRefreshRequest();
        //System.out.println("refresh request: " + refreshReq.getURL());
        
        // get new response using refreshReq URL
        response = myWebConversation.getResponse(refreshReq.getURL().toString());
        assertNotNull("Response from Refresh Request: ", response);

        // Make sure we logged in
        assertCurrentPageContains("Currently logged in as");
    }

    protected String findModelIdOnPage(String inStartText, String inEndText) throws Exception {

        String theModelId = "";
        //System.out.println("<findModelIdOnPage> inStartText: " + inStartText);
        //System.out.println("<findModelIdOnPage> inEndText: " + inEndText);
        

        String thePageText = myWebConversation.getCurrentPage().getText();
        //System.out.println("<findModelIdOnPage> thePageText: " + thePageText);

        int theFirstIndex = thePageText.indexOf(inStartText);
        //System.out.println("<findModelIdOnPage> theFirstIndex: " + theFirstIndex);
        int theLastIndex = thePageText.indexOf(inEndText);
        //System.out.println("<findModelIdOnPage> theLastIndex: " + theLastIndex);

        if (theFirstIndex < theLastIndex) {
            thePageText = thePageText.substring(theFirstIndex, theLastIndex);
            //System.out.println("<findModelIdOnPage> thePageText: " + thePageText);

            // Parse out the modelId
            int theModelIdIndex = thePageText.indexOf(Constants.Parameters.MODELID);
            if (theModelIdIndex != -1) {

                thePageText = thePageText.substring(theModelIdIndex);
                int theEndingQuoteIndex = thePageText.indexOf("\"");

                theModelId = thePageText.substring(0, theEndingQuoteIndex);
            } else {
                throw new Exception("Cannot find ModelID between starting string: " + inStartText + "and endingstring: " + inEndText);
            }
        } else {
            throw new Exception("Unable to locate text with starting string: " + inStartText + " and ending string: "
                    + inEndText);
        }

        return theModelId;
    }
    

    protected void verifyValuesOnPage(WebForm inForm, List inIgnoreList) throws Exception {
    	
    	System.out.println("\nEntered verifyValuesOnPage(): ");

        String[] theParameters = inForm.getParameterNames();
        System.out.println("The number of parameters from the form: " + theParameters.length);
        
        for (int i = 0; i < theParameters.length; i++) {       	

            String theParameterName = theParameters[i];
            
            if(!inIgnoreList.contains(theParameterName)){

            	if (!theParameterName.equals("method") && !theParameterName.equals("unprotected_method")) {
            		String theParameterValue = inForm.getParameterValue(theParameterName);
            		
            		if (!Constants.Dropdowns.OTHER_OPTION.equals(theParameterValue) && theParameterValue !=null) {
                		System.out.println("Set:  " + theParameterName + "  To: " + theParameterValue);            			
            			assertCurrentPageContains(theParameterValue);
            		}
            	}
            }
        }
    	System.out.println("Exited verifyValuesOnPage(): \n");        
    }

    
    protected void verifyValuesOnPage(WebForm inForm) throws Exception {
    	verifyValuesOnPage(inForm, new ArrayList());    	
    }
    
    protected void verifyValuesOnPopulatePage(WebForm inForm) throws Exception {
    	verifyValuesOnPopulatePage(inForm, new ArrayList());    	
    }    
    
    protected void verifyValuesOnPopulatePage(WebForm inForm, List inIgnoreList) throws Exception {
    	
    	System.out.println("\nEntered verifyValuesOnPopulatePage: ");
    	
        String[] theParameters = inForm.getParameterNames();
       
        for (int i = 0; i < theParameters.length; i++) {       	

            String theParameterName = theParameters[i];
            
            System.out.println("ParameterName(Form): " + theParameterName + "\t ParameterValue: " + inForm.getParameterValue(theParameterName));               
            
            if(!inIgnoreList.contains(theParameterName) ){
            	
            	if (!theParameterName.equals("method") && !theParameterName.equals("unprotected_method")) {
            		
            		String theParameterValue = TestUtil.getMap().get(theParameterName).toString();

            		System.out.println("ParameterName(Map): " + theParameterName + "\t ParameterValue: " + theParameterValue);
            		
            		if (!Constants.Dropdowns.OTHER_OPTION.equals(theParameterValue) && theParameterValue !=null) {
                		         			
            			assertCurrentPageContains(theParameterValue);
      		
            		}
            	}
            }
        }
    	System.out.println("Exited verifyValuesOnPopulatePage: \n");        
    }   
    
    protected void navigateToModelForAdmin(String inModel) throws Exception {

        // Assume we are logged in. Click the SUBMIT button
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                "ADMIN");

        assertNotNull("Couldn't find link to admin page", theLink);

        theLink.click();

        // We may or may not have to hit the agreement link
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, inModel);

        assertNotNull("Couldn't find link to admin section", theLink);

        theLink.click();
        assertCurrentPageContains("Administration Roles");

    }    
   
}
