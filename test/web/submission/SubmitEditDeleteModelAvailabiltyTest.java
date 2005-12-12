/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteModelAvailabiltyTest.java,v 1.3 2005-12-12 15:53:48 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/12/12 15:53:12  pandyas
 * modified: navigateToModelForEditing(myModelName);
 *
 * Revision 1.1  2005/12/12 15:46:37  pandyas
 * JUnit test case for Model Availability
 *
 * 
 */
package web.submission;

import java.util.ResourceBundle;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import web.base.BaseModelNeededTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SubmitEditDeleteModelAvailabiltyTest extends BaseModelNeededTest {

	public SubmitEditDeleteModelAvailabiltyTest(String arg0) {
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
        TestSuite suite = new TestSuite(SubmitEditDeleteModelAvailabiltyTest.class);
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
    
    public void testIMSR() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /* Find Model Availablity link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Available from IMSR");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("Strain Name:");
        WebForm theForm = theCurrentPage.getFormWithName("availabilityForm");
        theForm.setParameter("name", "IMMMM");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added an Availability to this model!");
        
        /* Find the Model Availability link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "IMMMM");        
        assertNotNull("Unable to find link to edit the Availability from IMSR", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Strain Name:");
        theForm = theCurrentPage.getFormWithName("availabilityForm");
        theForm.setParameter("name", "IMMMM");
        theForm.setParameter("stockNumber", "111");         
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Availability.");      
        
        /* Find the Model Availability link to Delete */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "IMMMM");
        assertNotNull("Unable to find link to delete the Availability from the Investigator", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Strain Name:");
        theForm = theCurrentPage.getFormWithName("availabilityForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted an Availability."); 
    }    

}
