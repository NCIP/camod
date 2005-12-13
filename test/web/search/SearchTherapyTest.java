/**
 * @author pandyas
 * 
 * $Id: SearchTherapyTest.java,v 1.1 2005-12-13 20:39:14 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package web.search;

import gov.nih.nci.camod.webapp.form.TherapyForm;
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

public class SearchTherapyTest extends BaseModelNeededTest {

	public SearchTherapyTest(String arg0) {
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
        TestSuite suite = new TestSuite(SearchTherapyTest.class);
        return suite;
    }
    
	public void testSearchForTherapy() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding a Therapy
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Therapy");
		assertNotNull("Unable to find link to enter a Therapy", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Chemical Class:");
		WebForm theWebForm = theCurrentPage.getFormWithName("therapyForm");

		TherapyForm theForm = new TherapyForm();
        theForm.setNSCNumber("33832"); 
        theForm.setCASNumber("50-81-7");
        
		List theParamsToIgnore = new ArrayList();
		theParamsToIgnore.add("chemicalClasses");
		theParamsToIgnore.add("selectedChemicalClasses");
		theParamsToIgnore.add("processes");
		theParamsToIgnore.add("selectedProcesses");
		theParamsToIgnore.add("targets");
		theParamsToIgnore.add("selectedTargets");
		theParamsToIgnore.add("administrativeRoute");		
	
		/* Add parameters found on submit screen but not displayed on search screen  */
		List theParamsToSkip = new ArrayList();
		theParamsToSkip.add("selectedChemicalClasses");
		theParamsToSkip.add("selectedProcesses");
		theParamsToSkip.add("selectedTargets");
		
		
		TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		
		assertCurrentPageContains("You have successfully added a Therapy to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"THERAPEUTIC APPROACHES");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}  

}
