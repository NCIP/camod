/**
 * 
 * $Id: SearchGeneticDescriptionTest.java,v 1.8 2006-01-03 17:36:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2005/12/29 20:13:20  pandyas
 * Removed disabled="true" for methodOfObservation, disable done in javascript
 *
 * Revision 1.6  2005/12/29 19:44:55  pandyas
 * Fixed Defect# 325:  Spontaneous Mutation does not show up in search under Genetic Description unless other GD is entered and modified unit test.
 *
 * Revision 1.4  2005/12/27 19:04:19  pandyas
 * Added code setparameter for string array for modification type.  Added the rest of the testing for other fields.
 *
 * Revision 1.3  2005/12/22 19:16:43  pandyas
 * Modified test for EngineeredTransgene and added one for other fields.  Many of the files were modified to get the disabled='true' out of the jsp
 *
 * Revision 1.2  2005/12/13 22:01:50  pandyas
 * added JavaDocs
 *
 * 
 */

package web.search;

import gov.nih.nci.camod.webapp.form.AssociatedExpressionForm;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneForm;
import gov.nih.nci.camod.webapp.form.GenomicSegmentForm;
import gov.nih.nci.camod.webapp.form.InducedMutationForm;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationForm;
import gov.nih.nci.camod.webapp.form.TargetedModificationForm;

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

		/* Search for Engineered Transgene  */
		navigateToModelForEditing(myModelName);

		// Adding Engineered Transgene
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Engineered Transgene");
		assertNotNull("Unable to find link to enter an Engineered Transgene", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Transgene (coding sequence only):");
		WebForm theWebForm = theCurrentPage.getFormWithName("engineeredTransgeneForm");
		//had to be set explicitly so it didn't fail validation
		theWebForm.setParameter("locationOfIntegration", "Random");

		EngineeredTransgeneForm theForm = new EngineeredTransgeneForm();
		theForm.setNumberMGI("19191919");
		theForm.setLocationOfIntegration("Random");
		//set conditionedBy here and skipped description below
		theForm.setConditionedBy("Not Conditional");		
		
		List theParamsToIgnore = new ArrayList();
		theParamsToIgnore.add("transgeneId");
		theParamsToIgnore.add("fileLocation");
		theParamsToIgnore.add("fileServerLocation");
		theParamsToIgnore.add("title");
		theParamsToIgnore.add("descriptionOfConstruct");
		
		// Add parameters found on submit screen but not displayed on search screen  
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("description");
		
		TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added an Engineered Transgene to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"GENETIC DESCRIPTION");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
		
		
		/* Adding Associated Expression to Engineered Transgene  		*/
		navigateToModelForEditing(myModelName);

		// Adding Assoc Expression
		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Assoc Expression");
		assertNotNull("Unable to find link to enter an Assoc Expression", theLink);
		theCurrentPage = theLink.click();
		assertCurrentPageContains("Expression Level:");
		theWebForm = theCurrentPage.getFormWithName("associatedExpressionForm");

		AssociatedExpressionForm theAEForm = new AssociatedExpressionForm();
		theAEForm.setOrgan("Heart");
		theAEForm.setOrganTissueName("Heart");		
		theAEForm.setOrganTissueCode("C22498");

		/* Add parameters found on submit screen but not displayed on search screen  */
		theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("organTissueCode");
		theParamsToSkip.add("organTissueName");
		theParamsToSkip.add("aEngineeredTransgeneID");
		
		TestUtil.setRandomValues(theAEForm, theWebForm, false);
		TestUtil.setValuesOnForm(theAEForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added an Associated Expression Transgene to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"GENETIC DESCRIPTION");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);		

		
	}
	
	public void testSearchForEngineeredTransgeneWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Engineered Transgene");
		assertNotNull("Unable to find link to enter an Engineered Transgene", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Transgene (coding sequence only):");
		WebForm theWebForm = theCurrentPage.getFormWithName("engineeredTransgeneForm");
		//had to be set explicitly so it didn't fail validation
		theWebForm.setParameter("locationOfIntegration", "Random");

		EngineeredTransgeneForm theForm = new EngineeredTransgeneForm();
		theForm.setNumberMGI("19191919");
		theForm.setLocationOfIntegration("Random");
		//set conditionedBy here and skipped description below
		theForm.setConditionedBy("Not Conditional");		
		
		List theParamsToIgnore = new ArrayList();
		theParamsToIgnore.add("transgeneId");
		theParamsToIgnore.add("fileLocation");
		theParamsToIgnore.add("fileServerLocation");
		theParamsToIgnore.add("title");
		theParamsToIgnore.add("descriptionOfConstruct");
		
		// Add parameters found on submit screen but not displayed on search screen  */
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("description");
		//Parameter not on form
		theParamsToSkip.add("otherLocationOfIntegration");
		
		TestUtil.setRandomValues(theForm, theWebForm, true, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added an Engineered Transgene to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"GENETIC DESCRIPTION");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}	
	
	public void testSearchForGenomicSegment() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Genomic Segment");
		assertNotNull("Unable to find link to enter an Genomic Segment", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("-if category you are looking for is not listed");
		WebForm theWebForm = theCurrentPage.getFormWithName("genomicSegmentForm");
		//had to be set explicitly so it didn't fail validation
		theWebForm.setParameter("locationOfIntegration", "Random");
		
		GenomicSegmentForm theForm = new GenomicSegmentForm();
		theForm.setNumberMGI("19191919");
		theForm.setLocationOfIntegration("Random");
		
		List theParamsToIgnore = new ArrayList();
		theParamsToIgnore.add("transgeneId");
		theParamsToIgnore.add("fileLocation");
		theParamsToIgnore.add("fileServerLocation");
		theParamsToIgnore.add("title");
		theParamsToIgnore.add("descriptionOfConstruct");
		
		// Add parameters found on submit screen but not displayed on search screen  
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("description");
		theParamsToSkip.add("segmentId");
		theParamsToSkip.add("segmentName");		
		
		
		TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added a Genomic Segment to this model! ");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"GENETIC DESCRIPTION");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}

	public void testSearchForGenomicSegmentWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Genomic Segment");
		assertNotNull("Unable to find link to enter an Genomic Segment", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("-if category you are looking for is not listed");
		WebForm theWebForm = theCurrentPage.getFormWithName("genomicSegmentForm");
		//had to be set explicitly so it didn't fail validation
		theWebForm.setParameter("locationOfIntegration", "Random");
		
		GenomicSegmentForm theForm = new GenomicSegmentForm();
		theForm.setNumberMGI("19191919");
		theForm.setLocationOfIntegration("Random");
		
		List theParamsToIgnore = new ArrayList();
		theParamsToIgnore.add("transgeneId");
		theParamsToIgnore.add("fileLocation");
		theParamsToIgnore.add("fileServerLocation");
		theParamsToIgnore.add("title");
		theParamsToIgnore.add("descriptionOfConstruct");
		
		// Add parameters found on submit screen but not displayed on search screen  
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("description");
		theParamsToSkip.add("segmentId");
		theParamsToSkip.add("segmentName");			
		//Parameter not on form
		theParamsToSkip.add("otherLocationOfIntegration");		
		
		TestUtil.setRandomValues(theForm, theWebForm, true, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added a Genomic Segment to this model! ");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"GENETIC DESCRIPTION");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}
	
	public void testSearchForTargetedMutation() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding Engineered Transgene
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Targeted Modification");
		assertNotNull("Unable to find link to enter a Targeted Modification", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Targeted Gene/Locus:");

		WebForm theWebForm = theCurrentPage.getFormWithName("targetedModificationForm");
		
		//create string array for modification type
		String[] testArray = theWebForm.getOptionValues("modificationType");
		//set the second and third option from dropdown (other is first)
		theWebForm.setParameter("modificationType" ,new String[]{testArray[1],testArray[2]});

		TargetedModificationForm theForm = new TargetedModificationForm();
		theForm.setNumberMGI("19191919");
		//set conditionedBy here and skip description below
		theForm.setConditionedBy("Not Conditional");	
		
		List theParamsToIgnore = new ArrayList();
		theParamsToIgnore.add("transgeneId");
		theParamsToIgnore.add("fileLocation");
		theParamsToIgnore.add("fileServerLocation");
		theParamsToIgnore.add("title");
		theParamsToIgnore.add("descriptionOfConstruct");
		
		// Add parameters found on submit screen but not displayed on search screen  
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("description");
		theParamsToSkip.add("modificationId");		
		
		TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added a Targeted Modification to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"GENETIC DESCRIPTION");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}

	public void testSearchForTargetedMutationWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding Engineered Transgene
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Targeted Modification");
		assertNotNull("Unable to find link to enter a Targeted Modification", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Targeted Gene/Locus:");

		WebForm theWebForm = theCurrentPage.getFormWithName("targetedModificationForm");
		
		//create string array for modification type
		String[] testArray = theWebForm.getOptionValues("modificationType");
		//set the other and second option from dropdown 
		theWebForm.setParameter("modificationType" ,new String[]{testArray[0],testArray[1]});
		theWebForm.setParameter("otherModificationType", "Testing otherModificationType");

		TargetedModificationForm theForm = new TargetedModificationForm();
		theForm.setNumberMGI("19191919");
		//set conditionedBy here and skip description below
		theForm.setConditionedBy("Not Conditional");	
		
		List theParamsToIgnore = new ArrayList();
		theParamsToIgnore.add("transgeneId");
		theParamsToIgnore.add("fileLocation");
		theParamsToIgnore.add("fileServerLocation");
		theParamsToIgnore.add("title");
		theParamsToIgnore.add("descriptionOfConstruct");
		
		// Add parameters found on submit screen but not displayed on search screen  
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("description");
		theParamsToSkip.add("modificationId");		
		
		//other value set above so send in false here
		TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added a Targeted Modification to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"GENETIC DESCRIPTION");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}
	
	public void testSearchForInducedMutation() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Induced Mutation");
		assertNotNull("Unable to find link to enter an Induced Mutation", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Inducing Agent Category:");
		WebForm theWebForm = theCurrentPage.getFormWithName("inducedMutationForm");
		
		InducedMutationForm theForm = new InducedMutationForm();
		theForm.setNumberMGI("19191919");
		theForm.setObservation("Test Observation");
		theForm.setMethodOfObservation("Test Method of Observation");
		// unless set Chemical, CAS# is not displayed on search screen
		theForm.setType("Radiation");		
		
		// Add parameters found on submit screen but not displayed on search screen  
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("type");
		theParamsToSkip.add("CASNumber");
		
		TestUtil.setRandomValues(theForm, theWebForm, false);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added an Induced Mutation to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"GENETIC DESCRIPTION");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}
	
	public void testSearchForInducedMutationWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Induced Mutation");
		assertNotNull("Unable to find link to enter an Induced Mutation", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Inducing Agent Category:");
		WebForm theWebForm = theCurrentPage.getFormWithName("inducedMutationForm");
		
		InducedMutationForm theForm = new InducedMutationForm();
		theForm.setNumberMGI("19191919");
		theForm.setObservation("Test Observation");
		theForm.setMethodOfObservation("Test Method of Observation");
		// unless set Chemical, CAS# is not displayed on search screen
		theForm.setType("Radiation");		
		
		// Add parameters found on submit screen but not displayed on search screen  
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("type");
		theParamsToSkip.add("CASNumber");
		
		TestUtil.setRandomValues(theForm, theWebForm, true);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added an Induced Mutation to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"GENETIC DESCRIPTION");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
	}	

	public void testSearchForSpontaneousMutation() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Spontaneous Mutation");
		assertNotNull("Unable to find link to enter a Spontaneous Mutation", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Gene Name:");
		WebForm theWebForm = theCurrentPage.getFormWithName("spontaneousMutationForm");
		
		SpontaneousMutationForm theForm = new SpontaneousMutationForm();
		theForm.setNumberMGI("19191919");
		
		TestUtil.setRandomValues(theForm, theWebForm, false);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added a Spontaneous Mutation to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"GENETIC DESCRIPTION");
		
		verifyValuesOnPage(theWebForm);
	}
	
}
