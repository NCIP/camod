/**
 * 
 * $Id: SearchGeneticDescriptionTest.java,v 1.2 2005-12-13 22:01:50 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package web.search;

import gov.nih.nci.camod.webapp.form.EngineeredTransgeneForm;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import junit.framework.Test;
import junit.framework.TestSuite;
import web.util.TestUtil;
import web.base.BaseModelNeededTest;

import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class SearchGeneticDescriptionTest extends BaseModelNeededTest {

	public SearchGeneticDescriptionTest(String testName) {
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
		TestSuite suite = new TestSuite(SearchGeneticDescriptionTest.class);
		return suite;
	}

	public void testSearchForEngineeredTransgene() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Engineered Transgene");
		assertNotNull("Unable to find link to enter an Engineered Transgene", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Transgene (coding sequence only):");
		WebForm theWebForm = theCurrentPage.getFormWithName("engineeredTransgeneForm");

		EngineeredTransgeneForm theForm = new EngineeredTransgeneForm();
		theForm.setNumberMGI("19191919");
		theForm.setLocationOfIntegration("Random");
		
		List theParamsToIgnore = new ArrayList();
		theParamsToIgnore.add("transgeneId");
		theParamsToIgnore.add("fileLocation");
		theParamsToIgnore.add("fileServerLocation");
		theParamsToIgnore.add("title");
		theParamsToIgnore.add("descriptionOfConstruct");
		
		TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added an Engineered Transgene to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"GENETIC DESCRIPTION");
		
		verifyValuesOnPage(theWebForm);
	}
}
