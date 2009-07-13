package web.search;

import gov.nih.nci.camod.webapp.form.TransientInterferenceForm;
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

public class SearchPopulateTransIntTest extends BaseModelNeededTest
{
    public SearchPopulateTransIntTest(String arg0)
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
        TestSuite suite = new TestSuite(SearchPopulateTransIntTest.class);
        return suite;
    }

    public void testPopulateMorpholino() throws Exception
    {

        navigateToModelForEditing(myModelName);
        System.out.println("myModelName: " + myModelName);

        // Adding a Morpholino
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Morpholino");
        System.out.println("theLink: " + theLink);
        
        assertNotNull("Unable to find link to enter a Morpholino", theLink);
        WebResponse theCurrentPage = theLink.click();
        assertCurrentPageContains("if source is not listed");
        System.out.println("here: " );
        
        WebForm theWebForm = theCurrentPage.getFormWithName("transientInterferenceForm");

        TransientInterferenceForm theForm = new TransientInterferenceForm();
        theForm.setSource("Gene Tool");
        theForm.setTargetedRegion("TESTINGTARGETEDREGION");

        List<String> theParamsToIgnore = new ArrayList<String>();

        /* Add parameters found on submit screen but not displayed on search screen  */
        List<String> theParamsToSkip = new ArrayList<String>();

        TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();

        assertCurrentPageContains("You have successfully added a Transient Interference to this model!");

        // Verify that populate method returns complete and correct data
        navigateToModelForEditing(myModelName);

        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "TESTINGTARGETEDREGION");
        assertNotNull("Unable to find link to verify Morpholino", theLink);
        theCurrentPage = theLink.click();
        
        assertCurrentPageContains("if source is not listed");
        theWebForm = theCurrentPage.getFormWithName("transientInterferenceForm");

        //Add parameters found behind but not populate screen
        theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("aTransIntID");
        theParamsToSkip.add("aConceptCode");
        theParamsToSkip.add("otherDeliveryMethod");
        // shows up as a match but maybe font is not read
        theParamsToSkip.add("sequenceDirection");
        theParamsToSkip.add("otherSource");
        theParamsToSkip.add("submitAction");
        theParamsToSkip.add("otherVisualLigand");

        verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);

    }


    public void testPopulateSirna() throws Exception
    {

        navigateToModelForEditing(myModelName);
        System.out.println("myModelName: " + myModelName);

        // Adding a Morpholino
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter SiRNA");
        System.out.println("theLink: " + theLink);
        
        assertNotNull("Unable to find link to enter a SiRNA", theLink);
        WebResponse theCurrentPage = theLink.click();
        assertCurrentPageContains("if source is not listed");
        System.out.println("here: " );
        
        WebForm theWebForm = theCurrentPage.getFormWithName("transientInterferenceForm");

        TransientInterferenceForm theForm = new TransientInterferenceForm();
        theForm.setSource("Amaxa");
        theForm.setTargetedRegion("TESTINGTARGETEDREGION");

        List<String> theParamsToIgnore = new ArrayList<String>();

        // Add parameters found on submit screen but not displayed on search screen  
        List<String> theParamsToSkip = new ArrayList<String>();

        TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();

        assertCurrentPageContains("You have successfully added a Transient Interference to this model! ");

        // Verify that populate method returns complete and correct data
        navigateToModelForEditing(myModelName);

        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "TESTINGTARGETEDREGION");
        assertNotNull("Unable to find link to verify SiRNA", theLink);
        theCurrentPage = theLink.click();
        
        assertCurrentPageContains("if source is not listed");
        theWebForm = theCurrentPage.getFormWithName("transientInterferenceForm");

        //Add parameters found behind but not populate screen
        theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("aTransIntID");
        theParamsToSkip.add("aConceptCode");        
        theParamsToSkip.add("otherDeliveryMethod");
        // shows up as a match but maybe font is not read
        theParamsToSkip.add("sequenceDirection");
        theParamsToSkip.add("otherSource");
        theParamsToSkip.add("submitAction");
        theParamsToSkip.add("otherVisualLigand");

        verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
    }
    

}
