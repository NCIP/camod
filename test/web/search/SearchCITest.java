package web.search;

import gov.nih.nci.camod.webapp.form.ChemicalDrugForm;
import gov.nih.nci.camod.webapp.form.EnvironmentalFactorForm;

import java.util.ArrayList;
import java.util.ResourceBundle;

import junit.framework.Test;
import junit.framework.TestSuite;
import web.util.TestUtil;
import web.base.BaseModelNeededTest;

import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class SearchCITest extends BaseModelNeededTest {

	public SearchCITest(String testName) {
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
		TestSuite suite = new TestSuite(SearchCITest.class);
		return suite;
	}

	public void testSearchForChemicalDrug() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Chemical/Drug");
		assertNotNull("Unable to find link to enter a Chemical/Drug", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Chemical/Drug is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("chemicalDrugForm");

		ChemicalDrugForm theForm = new ChemicalDrugForm();
		theForm.setNSCNumber("19191919");
		
		TestUtil.setRandomValues(theForm, theWebForm, false, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Chemical / Drug to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"CARCINOGENIC INTERVENTIONS");
		
		verifyValuesOnPage(theWebForm);
	}
	
	public void testSearchForChemicalDrugWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Chemical/Drug");
		assertNotNull("Unable to find link to enter a Chemical/Drug", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Chemical/Drug is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("chemicalDrugForm");

		ChemicalDrugForm theForm = new ChemicalDrugForm();
		theForm.setNSCNumber("19191919");
		
		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Chemical / Drug to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"CARCINOGENIC INTERVENTIONS");
		
		verifyValuesOnPage(theWebForm);
	}
	
	public void testSearchForEnvironmentalFactor() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Environmental Factor");
		assertNotNull("Unable to find link to enter an Environmental Factor", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Enviromental Factor is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("environmentalFactorForm");

		EnvironmentalFactorForm theForm = new EnvironmentalFactorForm();
		
		TestUtil.setRandomValues(theForm, theWebForm, false, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added an Environmental Factor to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"CARCINOGENIC INTERVENTIONS");
		
		verifyValuesOnPage(theWebForm);
	}
	
	public void testSearchForEnvironmentalFactorWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Environmental Factor");
		assertNotNull("Unable to find link to enter an Environmental Factor", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Enviromental Factor is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("environmentalFactorForm");

		EnvironmentalFactorForm theForm = new EnvironmentalFactorForm();
		
		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added an Environmental Factor to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"CARCINOGENIC INTERVENTIONS");
		
		verifyValuesOnPage(theWebForm);
	}
	
}
