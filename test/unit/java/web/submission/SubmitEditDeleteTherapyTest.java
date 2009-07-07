/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteTherapyTest.java,v 1.1 2009-07-07 17:46:54 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/12/12 16:26:16  pandyas
 * added testPublication to Therapy test class
 *
 * 
 */
package web.submission;

import java.util.ResourceBundle;
import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseModelNeededTest;
import com.meterware.httpunit.*;

public class SubmitEditDeleteTherapyTest extends BaseModelNeededTest {

	public SubmitEditDeleteTherapyTest(String arg0) {
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
        TestSuite suite = new TestSuite(SubmitEditDeleteTherapyTest.class);
        return suite;
    }
    
    public void testTherapy() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /* Find Therapy link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Therapy");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("- if Administration Route is not listed");
        WebForm theForm = theCurrentPage.getFormWithName("therapyForm");
        theForm.setParameter("name", "ABCDEFG");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Therapy to this model!");
        
        /* Find Therapy link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");        
        assertNotNull("Unable to find link to edit the Therapy", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("- if Administration Route is not listed");
        theForm = theCurrentPage.getFormWithName("therapyForm");
        theForm.setParameter("name", "ABCDEFG");
        theForm.setParameter("results", "results");         
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Therapy.");      
        
        /* Find Therapy link to Delete */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");
        assertNotNull("Unable to find link to delete the Therapy", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("- if Administration Route is not listed");
        theForm = theCurrentPage.getFormWithName("therapyForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Therapy."); 
    }
    
    public void testPublication() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /* Find Publication link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Publication");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("For publications with a PubMed record");
        WebForm theForm = theCurrentPage.getFormWithName("publicationForm");
        theForm.setParameter("authors", "Pandya S");
        theForm.setParameter("name", "Published");
        theForm.setParameter("firstTimeReported", "yes");
        theForm.setParameter("pmid", "16323327");
        theForm.setParameter("title", "Dying with dignity: a round-table.");        
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Publication to this model!");
        
        /* Find Publication link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Pandya");        
        assertNotNull("Unable to find link to edit the Publication", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("For publications with a PubMed record");
        theForm = theCurrentPage.getFormWithName("publicationForm");
        theForm.setParameter("authors", "Pandya S");
        theForm.setParameter("name", "Published");
        theForm.setParameter("firstTimeReported", "yes");
        theForm.setParameter("pmid", "16323327");
        theForm.setParameter("title", "Dying with dignity: a round-table.");  
        theForm.setParameter("year", "1999");         
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Publication.");      
        
        /* Find Publication link to Delete */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Pandya");
        assertNotNull("Unable to find link to delete the Publication", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("For publications with a PubMed record");
        theForm = theCurrentPage.getFormWithName("publicationForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Publication.");         
    }  

}
