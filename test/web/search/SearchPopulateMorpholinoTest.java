package web.search;

import gov.nih.nci.camod.webapp.form.MorpholinoForm;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseModelNeededTest;
import web.util.TestUtil;

public class SearchPopulateMorpholinoTest extends BaseModelNeededTest
{
    public SearchPopulateMorpholinoTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {

        ResourceBundle theBundle = ResourceBundle.getBundle("test");

        String theUsername = theBundle.getString("username");
        String thePassword = theBundle.getString("password");

        loginToApplication(theUsername, thePassword);
        createModel();
    }

    protected void tearDown() throws Exception
    {
        deleteModel();
        logoutOfApplication();
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite(SearchPopulateMorpholinoTest.class);
        return suite;
    }

    public void testPopulateMorpholino() throws Exception
    {

        navigateToModelForEditing(myModelName);

        // Adding a Morpholino
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Morpholino");
        assertNotNull("Unable to find link to enter a Morpholino", theLink);
        WebResponse theCurrentPage = theLink.click();
        assertCurrentPageContains("if source is not listed");
        WebForm theWebForm = theCurrentPage.getFormWithName("morpholinoForm");

        MorpholinoForm theForm = new MorpholinoForm();
        theForm.setSource("Gene Tool");
        theForm.setTargetedRegion("TESTINGTARGETEDREGION");

        List<String> theParamsToIgnore = new ArrayList<String>();

        /* Add parameters found on submit screen but not displayed on search screen  */
        List<String> theParamsToSkip = new ArrayList<String>();

        TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();

        assertCurrentPageContains("You have successfully added a Morpholino to this model! ");

        // Verify that populate method returns complete and correct data
        navigateToModelForEditing(myModelName);

        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "TESTINGTARGETEDREGION");
        assertNotNull("Unable to find link to verify Morpholino", theLink);
        theCurrentPage = theLink.click();
        assertCurrentPageContains("if source is not listed");
        theWebForm = theCurrentPage.getFormWithName("morpholinoForm");

        //Add parameters found behind but not populate screen
        theParamsToSkip = new ArrayList<String>();


        verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);

    }
}
