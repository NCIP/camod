package web.search;

import gov.nih.nci.camod.webapp.form.AvailabilityForm;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;

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

public class SearchModelCharacteristicsTest extends BaseModelNeededTest {
	
	public SearchModelCharacteristicsTest(String arg0) {
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
        TestSuite suite = new TestSuite(SearchModelCharacteristicsTest.class);
        return suite;
    }
    
    /* This includes the test for Model Characteristics and Jackson Lab
     * Animal Availability
     */ 
    
	public void testSearchForModelCharacteristics() throws Exception {

		// Adding Jackson Lab Animal Availability
		navigateToModelForEditing(myModelName);
		
		// Adding a Animal Availability
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Available from Jackson Lab.");
		assertNotNull("Unable to find link to enter a Jackson Lab Availability", theLink);		
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Stock Number:");
		WebForm theWebForm = theCurrentPage.getFormWithName("availabilityForm");

		AvailabilityForm theForm = new AvailabilityForm();
		theForm.setSource("Jackson Laboratory");
		
		/* Add parameters found on submit screen but not displayed on search screen  */
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("source");		

		TestUtil.setRandomValues(theForm, theWebForm, false);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

		assertCurrentPageContains("You have successfully added an Availability to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"MODEL CHARACTERISTICS");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
		
		/* Verify Model Characteristics Data on search page */
		navigateToModelForEditing(myModelName);
		
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_URL_STRING,
		"AnimalModelPopulateAction.do?method=populate");		
		assertNotNull("Unable to find link to curent Model Characteristics", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("Is this model a toolmouse?");		
		theWebForm = theCurrentPage.getFormWithName("modelCharacteristicsForm");		
		
		ModelCharacteristicsForm theMCForm = new ModelCharacteristicsForm();
		//value set when model was created so method not needed
		TestUtil.setValuesOnForm(theMCForm, theWebForm);
		
		/* Add parameters found on submit screen but not displayed on search screen  */
		theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("principalInvestigator");
		theParamsToSkip.add("calendarReleaseDate");	
		theParamsToSkip.add("releaseDate");			
		
		navigateToSpecificSearchPage(myModelName,"MODEL CHARACTERISTICS");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);			
	} 
	
	public void testSearchForMMHCC() throws Exception {

		// Adding MMHCC Animal Availability
		navigateToModelForEditing(myModelName);
		
		// Adding a Animal Availability
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Available from MMHCC Repo.");
		assertNotNull("Unable to find link to enter a MMHCC Availability", theLink);		
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Strain Number:");
		WebForm theWebForm = theCurrentPage.getFormWithName("availabilityForm");

		AvailabilityForm theForm = new AvailabilityForm();
		theForm.setSource("MMHCC Repository");
		
		/* Add parameters found on submit screen but not displayed on search screen  */
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("source");		

		TestUtil.setRandomValues(theForm, theWebForm, false);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

		assertCurrentPageContains("You have successfully added an Availability to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"MODEL CHARACTERISTICS");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}
	
	public void testSearchForInvestigator() throws Exception {

		// Adding Investigator Animal Availability
		navigateToModelForEditing(myModelName);
		
		// Adding a Animal Availability
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Available from Investigator");
		assertNotNull("Unable to find link to enter an Investigator Availability", theLink);		
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Principal Investigator:");
		WebForm theWebForm = theCurrentPage.getFormWithName("availabilityForm");

		AvailabilityForm theForm = new AvailabilityForm();
		theForm.setSource("Investigator");
		
		/* Add parameters found on submit screen but not displayed on search screen  */
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("source");
		theParamsToSkip.add("stockNumber");		

		TestUtil.setRandomValues(theForm, theWebForm, false);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

		assertCurrentPageContains("You have successfully added an Availability to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"MODEL CHARACTERISTICS");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}
	
	public void testSearchForIMSR() throws Exception {

		// Adding IMSR Animal Availability
		navigateToModelForEditing(myModelName);
		
		// Adding a Animal Availability
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Available from IMSR");
		assertNotNull("Unable to find link to enter a IMSR Availability", theLink);		
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Stock Number:");
		WebForm theWebForm = theCurrentPage.getFormWithName("availabilityForm");

		AvailabilityForm theForm = new AvailabilityForm();
		theForm.setSource("IMSR");
		
		/* Add parameters found on submit screen but not displayed on search screen  */
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("source");		

		TestUtil.setRandomValues(theForm, theWebForm, false);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

		assertCurrentPageContains("You have successfully added an Availability to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"MODEL CHARACTERISTICS");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}	

}
