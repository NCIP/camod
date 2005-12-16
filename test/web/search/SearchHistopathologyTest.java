/**
 * @author pandyas
 * 
 * $Id: SearchHistopathologyTest.java,v 1.2 2005-12-16 18:13:21 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/12/14 17:17:42  pandyas
 * JUnit test case for Search Histopathology - depends on other changes in main tree that will NOT be uploaded until we go to production
 *
 * 
 */

package web.search;

import gov.nih.nci.camod.webapp.form.AssociatedMetastasisForm;
import gov.nih.nci.camod.webapp.form.ClinicalMarkerForm;
import gov.nih.nci.camod.webapp.form.HistopathologyForm;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import web.base.BaseModelNeededTest;
import web.util.TestUtil;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SearchHistopathologyTest extends BaseModelNeededTest {

	public SearchHistopathologyTest(String arg0) {
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
		TestSuite suite = new TestSuite(SearchHistopathologyTest.class);
		return suite;
	}
	
	public void testSearchForHistopathology() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding a Histopathology
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Histopathology");
		assertNotNull("Unable to find link to enter a Histopathology", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Tumor Incidence");
		WebForm theWebForm = theCurrentPage.getFormWithName("histopathologyForm");

		HistopathologyForm theForm = new HistopathologyForm();
		theForm.setOrgan("Heart");
		theForm.setOrganTissueName("Heart");		
		theForm.setOrganTissueCode("C22498");
		theForm.setTumorClassification("Lymphoid_Hyperplasia_of_the_Mouse_Intestinal_Tract");
		theForm.setDiagnosisName("Lymphoid_Hyperplasia_of_the_Mouse_Intestinal_Tract");
		theForm.setDiagnosisCode("C22100");	
		//theForm.setAgeOfOnset("1");
		theForm.setVolumeOfTumor("2");
		theForm.setWeightOfTumor("30");
		theForm.setComments("Test Comments");
		theForm.setTumorIncidenceRate("20");
		
		/* Add parameters found on submit screen but not displayed on search screen  */
		List theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("organTissueCode");
		theParamsToSkip.add("organTissueName");
		theParamsToSkip.add("tumorClassification");
		theParamsToSkip.add("diagnosisCode");
		theParamsToSkip.add("diagnosisName");
		theParamsToSkip.add("methodOfObservation");  
		
		
		TestUtil.setRandomValues(theForm, theWebForm, false);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Histopathology to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"HISTOPATHOLOGY");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
		
		/******* Adding Assoc Metas to Histopathology ***********/		
		navigateToModelForEditing(myModelName);

		// Adding an Assoc Metastasis
		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Assoc Metastasis");
		assertNotNull("Unable to find link to add an Assoc Metastasis to Histopathology", theLink);
		theCurrentPage = theLink.click();
		assertCurrentPageContains("Age of Metastasis");
		theWebForm = theCurrentPage.getFormWithName("associatedMetastasisForm");

		AssociatedMetastasisForm theAMForm = new AssociatedMetastasisForm();
		theAMForm.setOrgan("Heart");
		theAMForm.setOrganTissueName("Heart");		
		theAMForm.setOrganTissueCode("C22498");
		theAMForm.setTumorClassification("Lymphoid_Hyperplasia_of_the_Mouse_Intestinal_Tract");
		theAMForm.setDiagnosisName("Lymphoid_Hyperplasia_of_the_Mouse_Intestinal_Tract");
		theAMForm.setDiagnosisCode("C22100");	
		theAMForm.setVolumeOfTumor("2");
		theAMForm.setWeightOfTumor("30");
		theAMForm.setComments("Test Comments");
		theAMForm.setTumorIncidenceRate("20");
		
		//TODO: add check for histopathologyID so we can remove from skip list
		/* Add parameters found on submit screen but not displayed on search screen  */
		theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("organTissueCode");
		theParamsToSkip.add("organTissueName");
		theParamsToSkip.add("tumorClassification");
		theParamsToSkip.add("diagnosisCode");
		theParamsToSkip.add("diagnosisName");
		theParamsToSkip.add("methodOfObservation");
		theParamsToSkip.add("aHistopathologyID");	
		
		TestUtil.setRandomValues(theAMForm, theWebForm, false);
		TestUtil.setValuesOnForm(theAMForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added an Associated Metastasis to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"HISTOPATHOLOGY");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
		
		/******* Adding Clinical Marker to Histopathology ***********/		
		navigateToModelForEditing(myModelName);

		// Adding an Clinical Marker		
		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
		"Enter Clinical Marker");
		assertNotNull("Unable to find link to add a Clinical Marker to Histopathology", theLink);
		theCurrentPage = theLink.click();
		assertCurrentPageContains("Select Clinical");
		theWebForm = theCurrentPage.getFormWithName("clinicalMarkerForm");

		ClinicalMarkerForm theCMForm = new ClinicalMarkerForm();

		//TODO: add check for histopathologyID so we can remove from skip list
		/* Add parameters found on submit screen but not displayed on search screen  */
		theParamsToSkip = new ArrayList();		
		theParamsToSkip.add("aHistopathologyID");

		TestUtil.setRandomValues(theCMForm, theWebForm, false);
		TestUtil.setValuesOnForm(theCMForm, theWebForm);	
		
		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Clinical Marker to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"HISTOPATHOLOGY");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);		
		
	}  	

}
