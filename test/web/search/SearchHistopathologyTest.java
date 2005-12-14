/**
 * @author pandyas
 * 
 * $Id: SearchHistopathologyTest.java,v 1.1 2005-12-14 17:17:42 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package web.search;

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
	}  	

}
