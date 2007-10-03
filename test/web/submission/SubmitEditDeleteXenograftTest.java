/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteXenograftTest.java,v 1.3 2006-04-27 15:08:52 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
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
    	navigateToModelForEditing(myModelName);
        
        /* Find Transplant/Xenograft link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Transplant/Xenograft");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("if graft type is not listed");
        WebForm theForm = theCurrentPage.getFormWithName("xenograftForm");
        theForm.setParameter("xenograftName", "ABCDEFG");
        theForm.setParameter("graftType", "Cell Line");        
        theForm.setParameter("donorScientificName", "Mus musculus");
        theCurrentPage = theForm.submit();
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        assertCurrentPageContains("if strain is not listed");

        // Set the donorEthnicityStrain and submit again
        theForm = theCurrentPage.getFormWithName("xenograftForm");
        theForm.setParameter("donorEthinicityStrain", "129");
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
        assertCurrentPageContains("You have successfully added a Xenograft");
        
        
        /* Find Transplant/Xenograft link to Edit 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");        
        assertNotNull("Unable to find link to edit the Transplant/Xenograft", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if graft type is not listed");
        theForm = theCurrentPage.getFormWithName("xenograftForm");
        theForm.setParameter("xenograftName", "ABCDEFG");
        theForm.setParameter("donorScientificName", "Mus musculus"); 
        theForm.setParameter("graftType", "Cell Line");
        theCurrentPage = theForm.submit();
        
        // Set the ethnicity strain and submit again
        theForm = theCurrentPage.getFormWithName("xenograftForm");
        theForm.setParameter("donorEthinicityStrain", "129");
        theForm.setParameter("parentalCellLineName", "Parent Cell Line");
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully edited a Xenograft.");      
        */
        
        /* Find Transplant/Xenograft link to Delete 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");
        assertNotNull("Unable to find link to delete the Transplant/Xenograft", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if graft type is not listed");
        theForm = theCurrentPage.getFormWithName("xenograftForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Xenograft.");
        */ 
    }    

}
