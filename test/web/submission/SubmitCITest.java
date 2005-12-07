package web.submission;

import java.util.ResourceBundle;

import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseModelNeededTest;

import com.meterware.httpunit.*;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class SubmitCITest extends BaseModelNeededTest {

    public SubmitCITest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        
        ResourceBundle theBundle = ResourceBundle.getBundle("test");

        String theUsername = theBundle.getString("username");
        String thePassword = theBundle.getString("password");
        
        loginToApplication(theUsername, thePassword);
        createModel();
    }

    protected void tearDown() throws Exception {
        deleteModel();
        logoutOfApplication();
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(SubmitCITest.class);
        return suite;
    }

    public void testAddChemicalDrug() throws Exception {
        navigateToModel(myModelName);
        
        // We may or may not have to hit the agreement link
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Chemical/Drug");
        
        assertNotNull("Unable to find link to enter a Chemical/Drug", theLink);
        
        WebResponse theCurrentPage = theLink.click();
        
        assertCurrentPageContains("(if Chemical/Drug is not listed, then please");

        // Fill in the values and hit submit; Should fail the first time and the
        // populate will fill in the ethnicity strain values
        WebForm theForm = theCurrentPage.getFormWithName("chemicalDrugForm");

        theForm.setParameter("name", "1,2-dimethylhydrazine (DMH)");
        theCurrentPage = theForm.submit();

        assertCurrentPageContains("You have successfully added a Chemical / Drug to this model!");
        
        
    }
}
