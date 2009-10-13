package unit.web.search;

import gov.nih.nci.camod.webapp.form.AvailabilityForm;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.NamingException;

import unit.web.base.BaseModelNeededTest;
import unit.web.util.TestUtil;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SearchPopulateModelCharacteristicsTest extends BaseModelNeededTest
{

    public SearchPopulateModelCharacteristicsTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
    	
		try {
			
			setupJNDIdatasource();
			
		} catch (NamingException ex) {
            System.out.println("NamingException in datasouuce binding: " + SearchPopulateModelCharacteristicsTest.class.getName());
        }    	

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
        TestSuite suite = new TestSuite(SearchPopulateModelCharacteristicsTest.class);
        return suite;
    }

    public void testPopulateJacksonLab() throws Exception
    {

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
        theForm.setName("TESTAVAILABILITY");
        theForm.setSource("Jackson Laboratory");

        /* Add parameters found on submit screen but not displayed on search screen  */
        List<String> theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("source");

        TestUtil.setRandomValues(theForm, theWebForm, false);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added an Availability to this model!");

        // Verify that populate method returns complete and correct data
        navigateToModelForEditing(myModelName);

        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "TESTAVAILABILITY");
        assertNotNull("Unable to find link to populate the Jackson Availability", theLink);
        theCurrentPage = theLink.click();
        assertCurrentPageContains("Stock Number:");
        theWebForm = theCurrentPage.getFormWithName("availabilityForm");

        //Add parameters found behind but not populate screen
        theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("aAvailabilityID");
        theParamsToSkip.add("submitAction");

        verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);

        /* Verify Model Characteristics Data on return page 
         *  TODO:  probably have to do this another way to 
         *  verify the populate method for model char - 
         *  too many variables skipped 
         */
        /*
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
         
         // Add parameters found on submit screen but not displayed on search screen  
         theParamsToSkip = new ArrayList<String>();
         // TODO: Fix ones you can		
         theParamsToSkip.add("otherEthnicityStrain");
         theParamsToSkip.add("principalInvestigator");		
         theParamsToSkip.add("ethinicityStrain");
         theParamsToSkip.add("calendarReleaseDate");		
         theParamsToSkip.add("type");
         theParamsToSkip.add("breedingNotes");
         theParamsToSkip.add("scientificName");
         theParamsToSkip.add("experimentDesign");		
         theParamsToSkip.add("description");
         theParamsToSkip.add("url");
         theParamsToSkip.add("modelDescriptor");		
         
         verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
         */
    }

    /* This includes the test for Model Characteristics and Jackson Lab
     * Animal Availability
     */

    public void testSearchForJacksonLab() throws Exception
    {

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
        List<String> theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("source");

        TestUtil.setRandomValues(theForm, theWebForm, false);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added an Availability to this model!");

        TestUtil.moveModelToEditedApproved(myModelName);

        navigateToSpecificSearchPage(myModelName, "MODEL CHARACTERISTICS");

        verifyValuesOnPage(theWebForm, theParamsToSkip);

        /* Verify Model Characteristics Data on search page */
        navigateToModelForEditing(myModelName);

        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_URL_STRING,
                                                                          "AnimalModelPopulateAction.do?method=populate");
        assertNotNull("Unable to find link to curent Model Characteristics", theLink);
        theCurrentPage = theLink.click();
        assertCurrentPageContains("Is this model a tool strain?");
        theWebForm = theCurrentPage.getFormWithName("modelCharacteristicsForm");

        ModelCharacteristicsForm theMCForm = new ModelCharacteristicsForm();
        //value set when model was created so method not needed
        TestUtil.setValuesOnForm(theMCForm, theWebForm);

        /* Add parameters found on submit screen but not displayed on search screen  */
        theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("principalInvestigator");
        theParamsToSkip.add("calendarReleaseDate");
        theParamsToSkip.add("releaseDate");

        navigateToSpecificSearchPage(myModelName, "MODEL CHARACTERISTICS");

        verifyValuesOnPage(theWebForm, theParamsToSkip);
    }

    public void testPopulateMMHCC() throws Exception
    {

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
        List<String> theParamsToSkip = new ArrayList<String>();
        theForm.setName("TESTAVAILABILITY");
        theParamsToSkip.add("source");

        TestUtil.setRandomValues(theForm, theWebForm, false);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added an Availability to this model!");

        // Verify that populate method returns complete and correct data
        navigateToModelForEditing(myModelName);

        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "TESTAVAILABILITY");
        assertNotNull("Unable to find link to populate the Availability", theLink);
        theCurrentPage = theLink.click();
        assertCurrentPageContains("Strain Number:");
        theWebForm = theCurrentPage.getFormWithName("availabilityForm");

        //Add parameters found behind but not populate screen
        theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("aAvailabilityID");
        theParamsToSkip.add("submitAction");

        verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
    }

    public void testSearchForMMHCC() throws Exception
    {

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
        List<String> theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("source");

        TestUtil.setRandomValues(theForm, theWebForm, false);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added an Availability to this model!");

        TestUtil.moveModelToEditedApproved(myModelName);

        navigateToSpecificSearchPage(myModelName, "MODEL CHARACTERISTICS");

        verifyValuesOnPage(theWebForm, theParamsToSkip);
    }

    public void testPopulateInvestigator() throws Exception
    {
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
        theForm.setName("TESTAVAILABILITY");
        theForm.setSource("Investigator");

        /* Add parameters found on submit screen but not displayed on search screen  */
        List<String> theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("source");
        theParamsToSkip.add("stockNumber");

        TestUtil.setRandomValues(theForm, theWebForm, false);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added an Availability to this model!");

        // Verify that populate method returns complete and correct data
        navigateToModelForEditing(myModelName);

        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "TESTAVAILABILITY");
        assertNotNull("Unable to find link to populate the Availability", theLink);
        theCurrentPage = theLink.click();
        assertCurrentPageContains("Principal Investigator:");
        theWebForm = theCurrentPage.getFormWithName("availabilityForm");

        //Add parameters found behind but not populate screen
        theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("aAvailabilityID");
        theParamsToSkip.add("submitAction");

        verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
    }

    public void testSearchForInvestigator() throws Exception
    {
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
        List<String> theParamsToSkip = new ArrayList<String>();
        theParamsToSkip.add("source");
        theParamsToSkip.add("stockNumber");        
        //TODO: fix, check for 'van Steeg, Harry' instead of 'vansteeh'
        theParamsToSkip.add("principalInvestigator");

        TestUtil.setRandomValues(theForm, theWebForm, false);
        TestUtil.setValuesOnForm(theForm, theWebForm);

        theCurrentPage = theWebForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully added an Availability to this model!");

        System.out.println( "ModelName = " + myModelName);
        TestUtil.moveModelToEditedApproved(myModelName);

        navigateToSpecificSearchPage(myModelName, "MODEL CHARACTERISTICS");

        verifyValuesOnPage(theWebForm, theParamsToSkip);
    }

//    public void testPopulateIMSR() throws Exception
//    {
//        // Adding IMSR Animal Availability
//        navigateToModelForEditing(myModelName);
//
//        // Adding a Animal Availability
//        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Available from IMSR");
//        assertNotNull("Unable to find link to enter a IMSR Availability", theLink);
//        WebResponse theCurrentPage = theLink.click();
//        assertCurrentPageContains("Stock Number:");
//        WebForm theWebForm = theCurrentPage.getFormWithName("availabilityForm");
//
//        AvailabilityForm theForm = new AvailabilityForm();
//        theForm.setName("TESTAVAILABILITY");
//        theForm.setSource("IMSR");
//
//        /* Add parameters found on submit screen but not displayed on search screen  */
//        List<String> theParamsToSkip = new ArrayList<String>();
//        theParamsToSkip.add("source");
//
//        TestUtil.setRandomValues(theForm, theWebForm, false);
//        TestUtil.setValuesOnForm(theForm, theWebForm);
//
//        theCurrentPage = theWebForm.submit();
//        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
//
//        assertCurrentPageContains("You have successfully added an Availability to this model!");
//
//        // Verify that populate method returns complete and correct data
//        System.out.println("ModelName" + myModelName);
//       
//        navigateToModelForEditing(myModelName);
//
//        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "TESTAVAILABILITY");
//        assertNotNull("Unable to find link to populate the Availability", theLink);
//        theCurrentPage = theLink.click();
//        assertCurrentPageContains("Stock Number:");
//        theWebForm = theCurrentPage.getFormWithName("availabilityForm");
//
//        //Add parameters found behind but not populate screen
//        theParamsToSkip = new ArrayList<String>();
//        theParamsToSkip.add("aAvailabilityID");
//        theParamsToSkip.add("submitAction");
//
//        verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
//    }
//
//    public void testSearchForIMSR() throws Exception
//    {
//        // Adding IMSR Animal Availability
//        navigateToModelForEditing(myModelName);
//
//        // Adding a Animal Availability
//        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Available from IMSR");
//        assertNotNull("Unable to find link to enter a IMSR Availability", theLink);
//        WebResponse theCurrentPage = theLink.click();
//        assertCurrentPageContains("Stock Number:");
//        WebForm theWebForm = theCurrentPage.getFormWithName("availabilityForm");
//
//        AvailabilityForm theForm = new AvailabilityForm();
//        theForm.setSource("IMSR");
//
//        /* Add parameters found on submit screen but not displayed on search screen  */
//        List<String> theParamsToSkip = new ArrayList<String>();
//        theParamsToSkip.add("source");
//
//        TestUtil.setRandomValues(theForm, theWebForm, false);
//        TestUtil.setValuesOnForm(theForm, theWebForm);
//
//        theCurrentPage = theWebForm.submit();
//        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
//
//        assertCurrentPageContains("You have successfully added an Availability to this model!");
//
//        TestUtil.moveModelToEditedApproved(myModelName);
//
//        navigateToSpecificSearchPage(myModelName, "MODEL CHARACTERISTICS");
//
//        verifyValuesOnPage(theWebForm, theParamsToSkip);
//    }

}
