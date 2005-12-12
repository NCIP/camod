package web.base;

import gov.nih.nci.camod.Constants;

import java.util.ResourceBundle;

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

        ResourceBundle theBundle = ResourceBundle.getBundle("test");

        String theHost = theBundle.getString("testhost");

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

        navigateToLoginPage();

        WebForm theForm = myWebConversation.getCurrentPage().getFormWithName("loginForm");

        theForm.setParameter("username", inUsername);
        theForm.setParameter("password", inPassword);
        theForm.submit();

        // Make sure we logged in
        assertCurrentPageContains("Currently logged in as");
    }

    protected String findModelIdOnPage(String inStartText, String inEndText) throws Exception {

        String theModelId = "";

        String thePageText = myWebConversation.getCurrentPage().getText();

        int theFirstIndex = thePageText.indexOf(inStartText);
        int theLastIndex = thePageText.indexOf(inEndText);

        if (theFirstIndex < theLastIndex) {
            thePageText = thePageText.substring(theFirstIndex, theLastIndex);

            // Parse out the modelId
            int theModelIdIndex = thePageText.indexOf(Constants.Parameters.MODELID);
            if (theModelIdIndex != -1) {

                thePageText = thePageText.substring(theModelIdIndex);
                int theEndingQuoteIndex = thePageText.indexOf("\"");

                theModelId = thePageText.substring(0, theEndingQuoteIndex);
            } else {
                throw new Exception("Cannot find text " + Constants.Parameters.MODELID
                        + " between starting and ending strings: " + inStartText + " , " + inEndText);
            }
        } else {
            throw new Exception("Unable to locate text with starting and ending strings: " + inStartText + " , "
                    + inEndText);
        }

        return theModelId;
    }

    protected void verifyValuesOnPage(WebForm inForm) throws Exception {

        String[] theParameters = inForm.getParameterNames();

        for (int i = 0; i < theParameters.length; i++) {

            String theParameterName = theParameters[i];

            if (!theParameterName.equals("method") && !theParameterName.equals("unprotected_method")) {
                String theParameterValue = inForm.getParameterValue(theParameterName);

                if (!theParameterValue.equals(Constants.Dropdowns.OTHER_OPTION)) {
                    assertCurrentPageContains(theParameterValue);
                }
            }
        }
    }

}
