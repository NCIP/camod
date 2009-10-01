/**
 * 
 * $Id: SearchPopulateCITest.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.4  2008/10/01 23:54:11  schroedn
 * Junit test fixes for caMOD 2.5 - revision 1
 *
 * Revision 1.3  2006/04/27 15:08:43  pandyas
 * Modified while testing caMod 2.1
 *
 * Revision 1.2  2006/04/17 19:37:34  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.1  2006/01/06 16:08:22  pandyas
 * Added testing for populate methods
 *
 * Revision 1.6  2005/12/21 17:42:13  pandyas
 * Added test for "Other" dropdown options - GeneDelivery still can't find otherViralVector and is commented out.  I moved all the jsp's action statement to outside the tables
 *
 * Revision 1.5  2005/12/20 15:53:06  pandyas
 * GeneDelivery bug was fixed so that test works.  The other tests still fail validation.
 *
 * Revision 1.4  2005/12/16 17:28:37  pandyas
 * Added TODO for bug found in application while creating this script
 *
 * Revision 1.3  2005/12/14 20:14:03  pandyas
 * Added JavaDocs
 *
 * 
 */
package unit.web.search;

import gov.nih.nci.camod.webapp.form.ChemicalDrugForm;
import gov.nih.nci.camod.webapp.form.EnvironmentalFactorForm;
import gov.nih.nci.camod.webapp.form.GeneDeliveryForm;
import gov.nih.nci.camod.webapp.form.GrowthFactorForm;
import gov.nih.nci.camod.webapp.form.HormoneForm;
import gov.nih.nci.camod.webapp.form.NutritionalFactorForm;
import gov.nih.nci.camod.webapp.form.RadiationForm;
import gov.nih.nci.camod.webapp.form.SurgeryForm;
import gov.nih.nci.camod.webapp.form.ViralTreatmentForm;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import junit.framework.Test;
import junit.framework.TestSuite;
import unit.web.util.TestUtil;
import unit.web.base.BaseModelNeededTest;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class SearchPopulateCITest extends BaseModelNeededTest {

	public SearchPopulateCITest(String testName) {
		super(testName);
	}

	protected void setUp() throws Exception {
		
		try {
			
			setupJNDIdatasource();
			
        } catch (NamingException ex) {
            System.out.println("NamingException in datasouuce binding: " + SearchPopulateCITest.class.getName());
        }		

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
		TestSuite suite = new TestSuite(SearchPopulateCITest.class);
		return suite;
	}

	public void testPopulateChemicalDrugWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Chemical/Drug");
		assertNotNull("Unable to find link to enter a Chemical/Drug", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Chemical/Drug is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("chemicalDrugForm");

		ChemicalDrugForm theForm = new ChemicalDrugForm();
		theForm.setName("Other");
        theForm.setOtherName("Entered Drug Name");
		theForm.setNscNumber("19191919");

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
        // added for debugging purpose only - comment out when done
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
		assertCurrentPageContains("You have successfully added a Chemical / Drug to this model!");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Other - Entered");
		assertNotNull("Unable to find link to populate Chemical/Drug", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Chemical/Drug is not listed, then please");
		theWebForm = theCurrentPage.getFormWithName("chemicalDrugForm");
		
		//Add parameters found behind but not populate screen
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("submitAction");
		theParamsToSkip.add("aCarcinogenExposureID");
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
	}
	
	public void testSearchForChemicalDrug() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Chemical/Drug");
		assertNotNull("Unable to find link to enter a Chemical/Drug", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Chemical/Drug is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("chemicalDrugForm");

		ChemicalDrugForm theForm = new ChemicalDrugForm();
		theForm.setNscNumber("19191919");

		TestUtil.setRandomValues(theForm, theWebForm, false, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
        // added for debugging purpose only - comment out when done
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
		assertCurrentPageContains("You have successfully added a Chemical / Drug to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}

	public void testSearchForChemicalDrugWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Chemical/Drug");
		assertNotNull("Unable to find link to enter a Chemical/Drug", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Chemical/Drug is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("chemicalDrugForm");

		ChemicalDrugForm theForm = new ChemicalDrugForm();
		theForm.setNscNumber("19191919");

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
        // added for debugging purpose only - comment out when done
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
		assertCurrentPageContains("You have successfully added a Chemical / Drug to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}

	public void testPopulateEnvironmentalFactorWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Environmental Factor");
		assertNotNull("Unable to find link to enter an Environmental Factor",
				theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Enviromental Factor is not listed, then please");
		WebForm theWebForm = theCurrentPage
				.getFormWithName("environmentalFactorForm");

		EnvironmentalFactorForm theForm = new EnvironmentalFactorForm();
		theForm.setName("Other");
        theForm.setOtherName("Entered EF Name");

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		// added for debugging purpose only - remove when done
		TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data",
				"* indicates a required field");

		assertCurrentPageContains("You have successfully added an Environmental Factor to this model!");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Other - Entered");
		assertNotNull("Unable to find link to populate Enviromental Factor", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Enviromental Factor is not listed");
		theWebForm = theCurrentPage.getFormWithName("environmentalFactorForm");
		
		//Add parameters found behind but not populate screen
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("submitAction");
		theParamsToSkip.add("aCarcinogenExposureID");
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
	}
	
	public void testSearchForEnvironmentalFactor() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Environmental Factor");
		assertNotNull("Unable to find link to enter an Environmental Factor",
				theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Enviromental Factor is not listed, then please");
		WebForm theWebForm = theCurrentPage
				.getFormWithName("environmentalFactorForm");

		EnvironmentalFactorForm theForm = new EnvironmentalFactorForm();

		TestUtil.setRandomValues(theForm, theWebForm, false, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added an Environmental Factor to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}

	public void testSearchForEnvironmentalFactorWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Environmental Factor");
		assertNotNull("Unable to find link to enter an Environmental Factor",
				theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Enviromental Factor is not listed, then please");
		WebForm theWebForm = theCurrentPage
				.getFormWithName("environmentalFactorForm");

		EnvironmentalFactorForm theForm = new EnvironmentalFactorForm();

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		// added for debugging purpose only - comment out when done
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

		assertCurrentPageContains("You have successfully added an Environmental Factor to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}

	public void testSearchForGeneDelivery() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Gene Delivery");
		assertNotNull("Unable to find link to enter a Gene Delivery", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Viral Vector is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("geneDeliveryForm");

		GeneDeliveryForm theForm = new GeneDeliveryForm();
		theForm.setViralVector("Lentivirus");
		theForm.setOrgan("Heart");
		theForm.setOrganTissueName("Heart");
		theForm.setOrganTissueCode("C22498");

		// Add parameters found on submit screen but not displayed on search
		// screen
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("organTissueCode");
		theParamsToSkip.add("organTissueName");

		TestUtil.setRandomValues(theForm, theWebForm, false, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Gene Delivery to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}
/*	
	public void testSearchForGeneDeliverywithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Gene Delivery");
		assertNotNull("Unable to find link to enter a Gene Delivery", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Viral Vector is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("geneDeliveryForm");

		GeneDeliveryForm theForm = new GeneDeliveryForm();
		theForm.setViralVector("Lentivirus");
		theForm.setOrgan("Heart");
		theForm.setOrganTissueName("Heart");
		theForm.setOrganTissueCode("C22498");

		// Add parameters found on submit screen but not displayed on search
		// screen
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("organTissueCode");
		theParamsToSkip.add("organTissueName");

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added a Gene Delivery to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}	
*/

	public void testPopulateGrowthFactorWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Growth Factor");
		assertNotNull("Unable to find link to enter a Growth Factor", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Growth Factor is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("growthFactorForm");

		GrowthFactorForm theForm = new GrowthFactorForm();

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		assertCurrentPageContains("You have successfully added a Growth Factor to this model!");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Other");
		assertNotNull("Unable to find link to populate Growth Factor", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Growth Factor is not listed");
		theWebForm = theCurrentPage.getFormWithName("growthFactorForm");
		
		//Add parameters found behind but not populate screen
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("submitAction");
		theParamsToSkip.add("aCarcinogenExposureID");
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
	}
	
	public void testSearchForGrowthFactor() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Growth Factor");
		assertNotNull("Unable to find link to enter a Growth Factor", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Growth Factor is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("growthFactorForm");

		GrowthFactorForm theForm = new GrowthFactorForm();

		TestUtil.setRandomValues(theForm, theWebForm, false, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Growth Factor to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}

	public void testSearchForGrowthFactorWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Growth Factor");
		assertNotNull("Unable to find link to enter a Growth Factor", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Growth Factor is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("growthFactorForm");

		GrowthFactorForm theForm = new GrowthFactorForm();

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Growth Factor to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}	

	public void testPopulateHormoneWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Hormone");
		assertNotNull("Unable to find link to enter a Hormone", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Hormone is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("hormoneForm");

		HormoneForm theForm = new HormoneForm();

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		assertCurrentPageContains("You have successfully added a Hormone to this model!");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Other");
		assertNotNull("Unable to find link to populate a Hormone", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Hormone is not listed");
		theWebForm = theCurrentPage.getFormWithName("hormoneForm");
		
		//Add parameters found behind but not populate screen
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("submitAction");
		theParamsToSkip.add("aCarcinogenExposureID");
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
	}	
	
	public void testSearchForHormone() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Hormone");
		assertNotNull("Unable to find link to enter a Hormone", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Hormone is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("hormoneForm");

		HormoneForm theForm = new HormoneForm();

		TestUtil.setRandomValues(theForm, theWebForm, false, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Hormone to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}

	public void testSearchForHormoneWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Hormone");
		assertNotNull("Unable to find link to enter a Hormone", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Hormone is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("hormoneForm");

		HormoneForm theForm = new HormoneForm();

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Hormone to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}	
	
	public void testPopulateNutritionalFactorWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Nutritional Factor");
		assertNotNull("Unable to find link to enter a Nutritional Factor",
				theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Nutritional Factor is not listed, then please");
		WebForm theWebForm = theCurrentPage
				.getFormWithName("nutritionalFactorForm");

		NutritionalFactorForm theForm = new NutritionalFactorForm();
        theForm.setOtherName("Entered Nutritional Name");

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		assertCurrentPageContains("You have successfully added a Nutritional Factor to this model!");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Other - Entered");
		assertNotNull("Unable to find link to populate a Nutritional Factor", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Nutritional Factor is not listed");
		theWebForm = theCurrentPage.getFormWithName("nutritionalFactorForm");
		
		//Add parameters found behind but not populate screen
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("submitAction");
		theParamsToSkip.add("aCarcinogenExposureID");
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
	}
	
	public void testSearchForNutritionalFactor() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Nutritional Factor");
		assertNotNull("Unable to find link to enter a Nutritional Factor",
				theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Nutritional Factor is not listed, then please");
		WebForm theWebForm = theCurrentPage
				.getFormWithName("nutritionalFactorForm");

		NutritionalFactorForm theForm = new NutritionalFactorForm();

		TestUtil.setRandomValues(theForm, theWebForm, false, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Nutritional Factor to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}

	public void testSearchForNutritionalFactorWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Nutritional Factor");
		assertNotNull("Unable to find link to enter a Nutritional Factor",
				theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Nutritional Factor is not listed, then please");
		WebForm theWebForm = theCurrentPage
				.getFormWithName("nutritionalFactorForm");

		NutritionalFactorForm theForm = new NutritionalFactorForm();
        theForm.setOtherName("Entered Nutritional Name");

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Nutritional Factor to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}	
	
	public void testPopulateRadiationWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Radiation");
		assertNotNull("Unable to find link to enter a Radiation", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Radiation is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("radiationForm");

		RadiationForm theForm = new RadiationForm();

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

		assertCurrentPageContains("You have successfully added a Radiation to this model!");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Other");
		assertNotNull("Unable to find link to populate a Radiation", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Radiation is not listed");
		theWebForm = theCurrentPage.getFormWithName("radiationForm");
		
		//Add parameters found behind but not populate screen
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("submitAction");
		theParamsToSkip.add("aCarcinogenExposureID");
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
	}	
	
	public void testSearchForRadiation() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Radiation");
		assertNotNull("Unable to find link to enter a Radiation", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Radiation is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("radiationForm");

		RadiationForm theForm = new RadiationForm();

		TestUtil.setRandomValues(theForm, theWebForm, false, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Radiation to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}

	public void testSearchForRadiationWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Radiation");
		assertNotNull("Unable to find link to enter a Radiation", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Radiation is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("radiationForm");

		RadiationForm theForm = new RadiationForm();

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Radiation to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}	
	
	public void testPopulateSurgeryOtherWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Surgery/Other");
		assertNotNull("Unable to find link to enter a Surgery/Other", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Surgery is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("surgeryForm");

		SurgeryForm theForm = new SurgeryForm();

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
		assertCurrentPageContains("You have successfully added a Surgery / Other to this model!");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Other - ");
		assertNotNull("Unable to find link to populate a Surgery/Other", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Surgery is not listed");
		theWebForm = theCurrentPage.getFormWithName("surgeryForm");
		
		//Add parameters found behind but not populate screen
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("submitAction");
		theParamsToSkip.add("aCarcinogenExposureID");
		//TODO:  Fix
		theParamsToSkip.add("ageAtTreatment");
		theParamsToSkip.add("regimen");		
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
	}	
	
	public void testSearchForSurgeryOther() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Surgery/Other");
		assertNotNull("Unable to find link to enter a Surgery/Other", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Surgery is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("surgeryForm");

		SurgeryForm theForm = new SurgeryForm();

		TestUtil.setRandomValues(theForm, theWebForm, false, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Surgery / Other to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}
	
	public void testSearchForSurgeryOtherWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Surgery/Other");
		assertNotNull("Unable to find link to enter a Surgery/Other", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Surgery is not listed, then please");
		WebForm theWebForm = theCurrentPage.getFormWithName("surgeryForm");

		SurgeryForm theForm = new SurgeryForm();

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Surgery / Other to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}	

/*	public void testPopulateViralTreatmenWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Viral Treatment");
		assertNotNull("Unable to find link to enter a Viral Treatment", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Virus is not listed, then please");
		WebForm theWebForm = theCurrentPage
				.getFormWithName("viralTreatmentForm");

		ViralTreatmentForm theForm = new ViralTreatmentForm();
		theForm.setName("Other");

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Viral Treatment to this model!");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Other");
		assertNotNull("Unable to find link to populate a Viral Treatmen", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Virus is not listed");
		theWebForm = theCurrentPage.getFormWithName("viralTreatmentForm");
		
		//Add parameters found behind but not populate screen
		List<String> theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("submitAction");
		theParamsToSkip.add("aCarcinogenExposureID");
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
		
	} */	
	
	public void testSearchForViralTreatment() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Viral Treatment");
		assertNotNull("Unable to find link to enter a Viral Treatment", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Virus is not listed, then please");
		WebForm theWebForm = theCurrentPage
				.getFormWithName("viralTreatmentForm");

		ViralTreatmentForm theForm = new ViralTreatmentForm();

		TestUtil.setRandomValues(theForm, theWebForm, false, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Viral Treatment to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}

	public void testSearchForViralTreatmenWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage()
				.getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
						"Enter Viral Treatment");
		assertNotNull("Unable to find link to enter a Viral Treatment", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("(if Virus is not listed, then please");
		WebForm theWebForm = theCurrentPage
				.getFormWithName("viralTreatmentForm");

		ViralTreatmentForm theForm = new ViralTreatmentForm();

		TestUtil.setRandomValues(theForm, theWebForm, true, new ArrayList());
		TestUtil.setValuesOnForm(theForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Viral Treatment to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName, "CARCINOGENIC INTERVENTIONS");

		verifyValuesOnPage(theWebForm);
	}	
}
