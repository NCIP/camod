/**
 * @author pandyas
 * 
 * $Id: SearchXenograftTest.java,v 1.4 2005-12-21 22:04:14 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
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

public class SearchXenograftTest extends BaseModelNeededTest {

	public SearchXenograftTest(String arg0) {
		super(arg0);
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
        TestSuite suite = new TestSuite(SearchXenograftTest.class);
        return suite;
    }
    
	public void testSearchForXenograft() throws Exception {

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
        theForm.setATCCNumber("2");
        theForm.setCellAmount("10");
        
		List theParamsToIgnore = new ArrayList();
		//textarea fails - investigate if needed
		theParamsToIgnore.add("modificationDescription");
		
		// Add parameters found on submit screen but not displayed on search screen  */
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("organTissueCode");
		theParamsToSkip.add("organTissueName");		
		
		TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		//for debugging validation failures
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added a Xenograft to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"TRANSPLANT/XENOGRAFT");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}  

	public void testSearchForXenograftWithOthers() throws Exception {

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
        theForm.setATCCNumber("2");
        theForm.setCellAmount("10");
        
		List theParamsToIgnore = new ArrayList();
		//textarea fails - investigate if needed
		theParamsToIgnore.add("modificationDescription");
		
		// Add parameters found on submit screen but not displayed on search screen  */
		List theParamsToSkip = new ArrayList();		
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

		navigateToSpecificSearchPage(myModelName,"TRANSPLANT/XENOGRAFT");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}  	
	
}
