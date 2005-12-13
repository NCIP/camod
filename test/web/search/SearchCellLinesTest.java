/**
 * @author pandyas
 * 
 * $Id: SearchCellLinesTest.java,v 1.1 2005-12-13 19:55:49 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */
package web.search;

import gov.nih.nci.camod.webapp.form.CellLineForm;

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

public class SearchCellLinesTest extends BaseModelNeededTest {

	public SearchCellLinesTest(String arg0) {
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
        TestSuite suite = new TestSuite(SearchCellLinesTest.class);
        return suite;
    }
    
	public void testSearchForCellLines() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding a Cell Line
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Cell Lines");
		assertNotNull("Unable to find link to enter a Cell Line", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Experiment:");
		WebForm theWebForm = theCurrentPage.getFormWithName("cellLineForm");

		CellLineForm theForm = new CellLineForm();
		theForm.setOrgan("Heart");
		theForm.setOrganTissueName("Heart");		
		theForm.setOrganTissueCode("C22498");

		/* Add parameters found on submit screen but not displayed on search screen  */
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("organTissueCode");
		theParamsToSkip.add("organTissueName");
		
		TestUtil.setRandomValues(theForm, theWebForm, false);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Cell Line to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"CELL LINES");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}  

}
