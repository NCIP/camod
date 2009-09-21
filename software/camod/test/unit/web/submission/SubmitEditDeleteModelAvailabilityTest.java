/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteModelAvailabilityTest.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.3  2008/10/02 00:04:29  schroedn
 * Removed IMSR
 *
 * Revision 1.2  2008/10/01 23:54:11  schroedn
 * Junit test fixes for caMOD 2.5 - revision 1
 *
 * Revision 1.1  2005/12/27 15:04:09  georgeda
 * Test cleanup
 *
 * Revision 1.3  2005/12/12 15:53:48  pandyas
 * modified: navigateToModelForEditing(myModelName);
 *
 * Revision 1.2  2005/12/12 15:53:12  pandyas
 * modified: navigateToModelForEditing(myModelName);
 *
 * Revision 1.1  2005/12/12 15:46:37  pandyas
 * JUnit test case for Model Availability
 *
 * 
 */
package unit.web.submission;

import java.util.ResourceBundle;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import unit.web.base.BaseModelNeededTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SubmitEditDeleteModelAvailabilityTest extends BaseModelNeededTest {

	public SubmitEditDeleteModelAvailabilityTest(String arg0) {
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
        TestSuite suite = new TestSuite(SubmitEditDeleteModelAvailabilityTest.class);
        return suite;
    }
    
    public void testJacksonLab() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /* Find Model Availablity link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Available from Jackson Lab.");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("Strain Name:");
        WebForm theForm = theCurrentPage.getFormWithName("availabilityForm");
        theForm.setParameter("name", "JJJJJ");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added an Availability to this model!");
        
        /* Find Model Availablity link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "JJJJJ");        
        assertNotNull("Unable to find link to edit the Availability from Jackson Lab", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Strain Name:");
        theForm = theCurrentPage.getFormWithName("availabilityForm");
        theForm.setParameter("name", "ABCDEFG");
        theForm.setParameter("stockNumber", "2222");          
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Availability.");      
        
        /* Find Model Availablity link to Delete */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");
        assertNotNull("Unable to find link to delete the Availability from Jackson Lab", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Strain Name:");
        theForm = theCurrentPage.getFormWithName("availabilityForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted an Availability."); 
    } 
    
    public void testMMHCCRepo() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /* Find Model Availablity link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Available from MMHCC Repo.");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("Strain Name:");
        WebForm theForm = theCurrentPage.getFormWithName("availabilityForm");
        theForm.setParameter("name", "MMMMM");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added an Availability to this model!");
        
        /* Find Model Availablity link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "MMMMM");        
        assertNotNull("Unable to find link to edit the Availability from MMHCC Repo.", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Strain Name:");
        theForm = theCurrentPage.getFormWithName("availabilityForm");
        theForm.setParameter("name", "MMMMMM");
        theForm.setParameter("stockNumber", "1111");         
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Availability.");      
        
        /* Find Model Availablity link to Delete */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "MMMMMM");
        assertNotNull("Unable to find link to delete the Availability from MMHCC Repo.", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Strain Name:");
        theForm = theCurrentPage.getFormWithName("availabilityForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted an Availability."); 
    } 
    
    public void testInvestigator() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /* Find Model Availablity link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Available from Investigator");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("Strain Name:");
        WebForm theForm = theCurrentPage.getFormWithName("availabilityForm");
        
        theForm.setParameter("name", "IIIII");
        theForm.setParameter("principalInvestigator", "wagnerk");
        theCurrentPage = theForm.submit();
        
        assertCurrentPageContains("You have successfully added an Availability to this model!");

        /* Find Model Availablity link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "IIIII");        
        assertNotNull("Unable to find link to edit the Availability from the Investigator", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Strain Name:");
        theForm = theCurrentPage.getFormWithName("availabilityForm");
        theForm.setParameter("name", "IIIIIE");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Availability.");      
        
        /* Find Model Availablity link to Delete */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "IIIIIE");
        assertNotNull("Unable to find link to delete the Availability from the Investigator", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Strain Name:");
        theForm = theCurrentPage.getFormWithName("availabilityForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted an Availability."); 
    }
    

}
