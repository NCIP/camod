/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteGraftTest.java,v 1.2 2007-08-08 16:04:28 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2007/07/31 12:00:41  pandyas
 * VCDE silver level  changes
 * Modified all names used for a new attribute
 *
 * Revision 1.3  2006/04/27 15:08:52  pandyas
 * Modified while testing caMod 2.1
 *
 * Revision 1.2  2005/12/12 16:01:07  pandyas
 * modified: navigateToModelForEditing(myModelName);
 *
 * Revision 1.1  2005/12/12 15:47:56  pandyas
 * JUnit test case for Xenograft/Transplant
 *
 * 
 */
package web.submission;

import java.util.ResourceBundle;
import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseModelNeededTest;
import web.util.TestUtil;

import com.meterware.httpunit.*;

public class SubmitEditDeleteGraftTest extends BaseModelNeededTest {

	public SubmitEditDeleteGraftTest(String arg0) {
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
        TestSuite suite = new TestSuite(SubmitEditDeleteGraftTest.class);
        return suite;
    }
    
    public void testGraft() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /* Find Graft link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Graft");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("if graft type is not listed");
        WebForm theForm = theCurrentPage.getFormWithName("raftForm");
        theForm.setParameter("name", "ABCDEFG");
        theForm.setParameter("sourceType", "Cell Line");        
        theForm.setParameter("donorScientificName", "Mus musculus");
        theCurrentPage = theForm.submit();
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        assertCurrentPageContains("if strain is not listed");

        // Set the donorEthnicityStrain and submit again
        theForm = theCurrentPage.getFormWithName("graftForm");
        theForm.setParameter("donorEthinicityStrain", "129");
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
        assertCurrentPageContains("You have successfully added a Graft");
        
        
        /* Find Graft link to Edit 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");        
        assertNotNull("Unable to find link to edit the Graft", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if graft type is not listed");
        theForm = theCurrentPage.getFormWithName("graftForm");
        theForm.setParameter("name", "ABCDEFG");
        theForm.setParameter("donorScientificName", "Mus musculus"); 
        theForm.setParameter("sourceType", "Cell Line");
        theCurrentPage = theForm.submit();
        
        // Set the ethnicity strain and submit again
        theForm = theCurrentPage.getFormWithName("graftForm");
        theForm.setParameter("donorEthinicityStrain", "129");
        theForm.setParameter("parentalCellLineName", "Parent Cell Line");
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully edited a Graft.");      
        */
        
        /* Find Graft link to Delete 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");
        assertNotNull("Unable to find link to delete the Graft", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if graft type is not listed");
        theForm = theCurrentPage.getFormWithName("graftForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Graft.");
        */ 
    }    

}
