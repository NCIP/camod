/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteTransIntTest.java,v 1.4 2006-11-09 17:10:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/10/18 18:11:06  pandyas
 * modified form name from morpholino to transientInterference
 *
 * Revision 1.2  2006/10/17 17:23:00  pandyas
 * changed name to match object
 *
 * Revision 1.1  2006/10/16 14:49:56  pandyas
 * modified name of test to add SiRNA
 *
 * Revision 1.1  2006/05/03 20:06:16  pandyas
 * Testing the new Morpholino object data in the application
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

public class SubmitEditDeleteTransIntTest extends BaseModelNeededTest {

	public SubmitEditDeleteTransIntTest(String arg0) {
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
        TestSuite suite = new TestSuite(SubmitEditDeleteTransIntTest.class);
        return suite;
    }
    
    public void testMorpholino() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        // Find Morpholino link to Submit
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Morpholino");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("if source is not listed");
        WebForm theForm = theCurrentPage.getFormWithName("transientInterferenceForm");
        theForm.setParameter("targetedRegion", "ABCDEFG");
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
        assertCurrentPageContains("You have successfully added a Transient Interference to this model!");
        
        /* Find Morpholino link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");        
        assertNotNull("Unable to find link to edit the Morpholino", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if source is not listed");
        theForm = theCurrentPage.getFormWithName("transientInterferenceForm");
        theForm.setParameter("targetedRegion", "ABCDEFGHI"); 
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
        assertCurrentPageContains("You have successfully edited a Transient Interference.");      
        
        /* Find Morpholino link to Delete*/ 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFGHI");
        assertNotNull("Unable to find link to delete the Morpholino", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if source is not listed");
        theForm = theCurrentPage.getFormWithName("transientInterferenceForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();  
      
        assertCurrentPageContains("You have successfully deleted a Transient Interference."); 
    } 
    
    public void testSirna() throws Exception {
        navigateToModelForEditing(myModelName);
        
        // Find Morpholino link to Submit
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter SiRNA");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("if source is not listed");
        WebForm theForm = theCurrentPage.getFormWithName("transientInterferenceForm");
        theForm.setParameter("targetedRegion", "ABCDEFG");
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
        assertCurrentPageContains("You have successfully added a Transient Interference to this model!");
        
        // Find Morpholino link to Edit 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");        
        assertNotNull("Unable to find link to edit the SiRNA", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if source is not listed");
        theForm = theCurrentPage.getFormWithName("transientInterferenceForm");
        theForm.setParameter("targetedRegion", "ABCDEFGHI"); 
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
        assertCurrentPageContains("You have successfully edited a Transient Interference.");      
        
        // Find Morpholino link to Delete 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFGHI");
        assertNotNull("Unable to find link to delete the SiRNA", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if source is not listed");
        theForm = theCurrentPage.getFormWithName("transientInterferenceForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();  
      
        assertCurrentPageContains("You have successfully deleted a Transient Interference."); 
    }     

}
