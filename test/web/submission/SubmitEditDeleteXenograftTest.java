/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteXenograftTest.java,v 1.1 2005-12-12 15:47:56 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */
package web.submission;

import java.util.ResourceBundle;
import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseModelNeededTest;
import com.meterware.httpunit.*;

public class SubmitEditDeleteXenograftTest extends BaseModelNeededTest {

	public SubmitEditDeleteXenograftTest(String arg0) {
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
        TestSuite suite = new TestSuite(SubmitEditDeleteXenograftTest.class);
        return suite;
    }
    
    public void testXenograft() throws Exception {
        navigateToModel(myModelName);
        
        /* Find Transplant/Xenograft link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Transplant/Xenograft");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("if graft type is not listed");
        WebForm theForm = theCurrentPage.getFormWithName("xenograftForm");
        theForm.setParameter("name", "ABCDEFG");
        theForm.setParameter("hostScientificName", "Mus musculus");         
        theForm.setParameter("graftType", "Cell Line");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Xenograft");
        
        /* Find Transplant/Xenograft link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");        
        assertNotNull("Unable to find link to edit the Transplant/Xenograft", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if graft type is not listed");
        theForm = theCurrentPage.getFormWithName("xenograftForm");
        theForm.setParameter("name", "ABCDEFG");
        theForm.setParameter("hostScientificName", "Mus musculus");         
        theForm.setParameter("graftType", "Cell Line");        
        theForm.setParameter("parentalCellLineName", "Parent Cell Line");        
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Xenograft.");      
        
        /* Find Transplant/Xenograft link to Delete */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");
        assertNotNull("Unable to find link to delete the Transplant/Xenograft", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if graft type is not listed");
        theForm = theCurrentPage.getFormWithName("xenograftForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Xenograft."); 
    }    

}
