package web.submission;

import java.util.ResourceBundle;

import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseModelNeededTest;

import com.meterware.httpunit.WebLink;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class SubmitPageTest extends BaseModelNeededTest {

    public SubmitPageTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {

        ResourceBundle theBundle = ResourceBundle.getBundle("test");

        String theUsername = theBundle.getString("username");
        String thePassword = theBundle.getString("password");

        loginToApplication(theUsername, thePassword);
    }

    protected void tearDown() throws Exception {
        logoutOfApplication();
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(SubmitPageTest.class);
        return suite;
    }

    public void testAddModel() throws Exception {

        // Really used all over, but we can test it here
        createModel();
    }

    public void testDuplicateModel() throws Exception {

        String theModelId = findModelIdOnPage("duplicate this record (" + myModelName, "delete this record ("
                + myModelName);

        // We may or may not have to hit the agreement link
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_URL_STRING,
                "method=duplicate&" + theModelId);

        assertNotNull("Couldn't find link to duplicate model", theLink);

        // Duplicate the model
        theLink.click();

        // Make sure it worked
        String theDupModelName = "Copy of " + myModelName;
        assertCurrentPageContains(theDupModelName);

        theModelId = findModelIdOnPage("duplicate this record (" + theDupModelName, "delete this record ("
                + theDupModelName);

        // Get the delete link
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_URL_STRING,
                "method=delete&" + theModelId);

        assertNotNull("Couldn't find link to delete duplicated model", theLink);

        theLink.click();

        assertCurrentPageDoesNotContain(theDupModelName);
    }

    public void testDeleteModel() throws Exception {

        // Really used all over, but we can test it here
        deleteModel();
    }
}
