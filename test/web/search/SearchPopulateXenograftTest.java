/**
 * @author pandyas
 * 
 * $Id: SearchPopulateXenograftTest.java,v 1.4 2006-04-27 15:33:43 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/04/27 15:08:43  pandyas
 * Modified while testing caMod 2.1
 *
 * Revision 1.2  2006/04/17 19:37:34  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.1  2006/01/06 16:08:22  pandyas
 * Added testing for populate methods
 *
 * Revision 1.5  2006/01/03 20:56:58  pandyas
 * added TODO note
 *
 * Revision 1.4  2005/12/21 22:04:14  pandyas
 * commented out debug line
 *
 * Revision 1.3  2005/12/21 21:35:23  pandyas
 * Added test for "Other" dropdown options
 *
 * Revision 1.2  2005/12/21 18:00:31  pandyas
 * Added test for "Other" dropdown options
 *
 * Revision 1.1  2005/12/12 19:04:21  pandyas
 * first version of JUnit test case
 *
 * 
 */
package web.search;

import gov.nih.nci.camod.webapp.form.XenograftForm;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import web.base.BaseModelNeededTest;
import web.util.TestUtil;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SearchPopulateXenograftTest extends BaseModelNeededTest
{

    public SearchPopulateXenograftTest(String arg0)
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
        TestSuite suite = new TestSuite(SearchPopulateXenograftTest.class);
        return suite;
    }

    public void testPopulateXenograftWithOthers() throws Exception
    {

        navigateToModelForEditing(myModelName);

        // Adding a Transplant/Xenograft
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                                                                                  "Enter Transplant/Xenograft");

        assertNotNull("Unable to find link to enter a Xenograft", theLink);
        WebResponse theCurrentPage = theLink.click();
        assertCurrentPageContains("if graft type is not listed");
        
        // Fill in the values and hit submit; Should fail the first time and the
        // populate will fill in the ethnicity strain values        
        WebForm theWebForm = theCurrentPage.getFormWithName("xenograftForm");
        
        theWebForm.setParameter("donorScientificName", "Mus musculus");
        theCurrentPage = theWebForm.submit();
        assertCurrentPageContains("if graft type is not listed");
        // Set the ethnicity strain and submit again
        theWebForm = theCurrentPage.getFormWithName("xenograftForm");
        //theWebForm.setParameter("donorEthinicityStrain", "129");
        //theCurrentPage = theWebForm.submit();
        
        XenograftForm theForm = new XenograftForm();
        theForm.setXenograftName("TESTXENOGRAFT");
        theForm.setOrgan("Heart");
        theForm.setOrganTissueName("Heart");
        theForm.setOrganTissueCode("C22498");
        theForm.setAtccNumber("2");
        theForm.setCellAmount("10");
        theForm.setGrowthPeriod("20");
        //theForm.setDonorEthinicityStrain("129");


        List<String> theParamsToIgnore = new ArrayList<String>();
        //TODO - remove disabled=true but keep disabled until geneticManipulation is entered
        theParamsToIgnore.add("modificationDescription");
        theParamsToIgnore.add("donorScientificName");
        theParamsToIgnore.add("donorEthinicityStrain");

        // Add parameters found on submit screen but not displayed on search screen  */
        List<String> theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("organTissueCode");
        theParamsToSkip.add("organTissueName");
        //Find a way to test these fields
        theParamsToSkip.add("donorScientificName");
        theParamsToSkip.add("donorEthinicityStrain");        

        TestUtil.setRandomValues(theForm, theWebForm, true, theParamsToIgnore);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();
        //for debugging validation failures
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added a Xenograft to this model!");

        // Verify that populate method returns complete and correct data
        navigateToModelForEditing(myModelName);

        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "TESTXENOGRAFT");
        assertNotNull("Unable to find link to verify xenograft", theLink);
        theCurrentPage = theLink.click();
        assertCurrentPageContains("if graft type is not listed");
        theWebForm = theCurrentPage.getFormWithName("xenograftForm");

        //Add parameters found behind but not populate screen
        theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("aXenograftID");
        theParamsToSkip.add("submitAction");
        theParamsToSkip.add("otherHostEthinicityStrain");
        theParamsToSkip.add("modificationDescription");

        verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
    }

    public void testSearchForXenograft() throws Exception
    {

        navigateToModelForEditing(myModelName);

        // Adding a Transplant/Xenograft
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                                                                                  "Enter Transplant/Xenograft");
        assertNotNull("Unable to find link to enter a Xenograft", theLink);
        WebResponse theCurrentPage = theLink.click();
        assertCurrentPageContains("if graft type is not listed");
        WebForm theWebForm = theCurrentPage.getFormWithName("xenograftForm");

        XenograftForm theForm = new XenograftForm();
        theForm.setOrgan("Heart");
        theForm.setOrganTissueName("Heart");
        theForm.setOrganTissueCode("C22498");
        theForm.setAtccNumber("2");
        theForm.setCellAmount("10");
        theForm.setDonorScientificName("Mus musculus");
        theForm.setDonorEthinicityStrain("129");

        List<String> theParamsToIgnore = new ArrayList<String>();
        //TODO - remove disabled=true but keep disabled until geneticManipulation is entered
        theParamsToIgnore.add("modificationDescription");

        // Add parameters found on submit screen but not displayed on search screen  */
        List<String> theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("organTissueCode");
        theParamsToSkip.add("organTissueName");

        TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();
        //for debugging validation failures
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added a Xenograft to this model!");

        TestUtil.moveModelToEditedApproved(myModelName);

        navigateToSpecificSearchPage(myModelName, "TRANSPLANT/XENOGRAFT");

        verifyValuesOnPage(theWebForm, theParamsToSkip);
    }

    public void testSearchForXenograftWithOthers() throws Exception
    {

        navigateToModelForEditing(myModelName);

        // Adding a Transplant/Xenograft
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                                                                                  "Enter Transplant/Xenograft");
        assertNotNull("Unable to find link to enter a Xenograft", theLink);
        WebResponse theCurrentPage = theLink.click();
        assertCurrentPageContains("if graft type is not listed");
        WebForm theWebForm = theCurrentPage.getFormWithName("xenograftForm");

        XenograftForm theForm = new XenograftForm();
        theForm.setOrgan("Heart");
        theForm.setOrganTissueName("Heart");
        theForm.setOrganTissueCode("C22498");
        theForm.setAtccNumber("2");
        theForm.setCellAmount("10");
        theForm.setDonorScientificName("Mus musculus");
        theForm.setDonorEthinicityStrain("129");

        List<String> theParamsToIgnore = new ArrayList<String>();
        //textarea fails - investigate if needed
        theParamsToIgnore.add("modificationDescription");

        // Add parameters found on submit screen but not displayed on search screen  */
        List<String> theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("organTissueCode");
        theParamsToSkip.add("organTissueName");
        //not set in original model so must be skipped
        theParamsToSkip.add("otherHostEthinicityStrain");

        TestUtil.setRandomValues(theForm, theWebForm, true, theParamsToIgnore);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added a Xenograft to this model!");

        TestUtil.moveModelToEditedApproved(myModelName);

        navigateToSpecificSearchPage(myModelName, "TRANSPLANT/XENOGRAFT");

        verifyValuesOnPage(theWebForm, theParamsToSkip);
    }

}
