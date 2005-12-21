/**
 * @author pandyas
 * 
 * $Id: SearchXenograftTest.java,v 1.2 2005-12-21 18:00:31 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/12/12 19:04:21  pandyas
 * first version of JUnit test case
 *
 * 
 */
package web.search;

import gov.nih.nci.camod.webapp.form.XenograftForm;
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

public class SearchXenograftTest extends BaseModelNeededTest {

	public SearchXenograftTest(String arg0) {
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
        TestSuite suite = new TestSuite(SearchXenograftTest.class);
        return suite;
    }
    
	public void testSearchForXenograft() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding a Transplant/Xenograft
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Transplant/Xenograft");
		assertNotNull("Unable to find link to enter a Xenograft", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("if graft type is not listed");
		WebForm theWebForm = theCurrentPage.getFormWithName("xenograftForm");

		XenograftForm theForm = new XenograftForm();
		theForm.setAdministrativeSite("ear");
        theForm.setName("ABCDEFG");
        theForm.setHostScientificName("Mus musculus");         
        theForm.setGraftType("Cell Line");
        theForm.setParentalCellLineName("Parent Cell Line");
        theForm.setHostScientificName("Mus musculus");
        theForm.setATCCNumber("2");
        theForm.setCellAmount("10");
        theForm.setGeneticManipulation("Test Genetic Manipulation");
        
		List theParamsToIgnore = new ArrayList();
		theParamsToIgnore.add("harvestDate");
		theParamsToIgnore.add("organ");
		theParamsToIgnore.add("organTissueCode");
		theParamsToIgnore.add("organTissueName");
		theParamsToIgnore.add("otherAdministrativeSite");
		theParamsToIgnore.add("otherGraftType");
		theParamsToIgnore.add("hostEthinicityStrain");		
		theParamsToIgnore.add("otherHostEthinicityStrain");
		theParamsToIgnore.add("modificationDescription");		
		
		TestUtil.setRandomValues(theForm, theWebForm, false, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Xenograft to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"TRANSPLANT/XENOGRAFT");
		
		verifyValuesOnPage(theWebForm);
	}  

	public void testSearchForXenograftWithOthers() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding a Transplant/Xenograft
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Transplant/Xenograft");
		assertNotNull("Unable to find link to enter a Xenograft", theLink);
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("if graft type is not listed");
		WebForm theWebForm = theCurrentPage.getFormWithName("xenograftForm");

		XenograftForm theForm = new XenograftForm();
		theForm.setAdministrativeSite("ear");
        theForm.setName("ABCDEFG");
        theForm.setHostScientificName("Mus musculus");         
        theForm.setGraftType("Cell Line");
        theForm.setParentalCellLineName("Parent Cell Line");
        theForm.setHostScientificName("Mus musculus");
        theForm.setATCCNumber("2");
        theForm.setCellAmount("10");
        theForm.setGeneticManipulation("Test Genetic Manipulation");
        
		List theParamsToIgnore = new ArrayList();
		theParamsToIgnore.add("harvestDate");
		theParamsToIgnore.add("organ");
		theParamsToIgnore.add("organTissueCode");
		theParamsToIgnore.add("organTissueName");
		theParamsToIgnore.add("otherAdministrativeSite");
		theParamsToIgnore.add("otherGraftType");
		theParamsToIgnore.add("hostEthinicityStrain");		
		theParamsToIgnore.add("otherHostEthinicityStrain");
		theParamsToIgnore.add("modificationDescription");		
		
		TestUtil.setRandomValues(theForm, theWebForm, true, theParamsToIgnore);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		assertCurrentPageContains("You have successfully added a Xenograft to this model!");

		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"TRANSPLANT/XENOGRAFT");
		
		verifyValuesOnPage(theWebForm);
	} 	
	
}
