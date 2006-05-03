/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteMorpholinoTest.java,v 1.1 2006-05-03 20:06:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */
package web.submission;

import java.util.ResourceBundle;
import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseModelNeededTest;
import web.util.TestUtil;

import com.meterware.httpunit.*;

public class SubmitEditDeleteMorpholinoTest extends BaseModelNeededTest {

	public SubmitEditDeleteMorpholinoTest(String arg0) {
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
        TestSuite suite = new TestSuite(SubmitEditDeleteMorpholinoTest.class);
        return suite;
    }
    
    public void testMorpholino() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        // Find Morpholino link to Submit
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Morpholino");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("if source is not listed");
        WebForm theForm = theCurrentPage.getFormWithName("morpholinoForm");
        theForm.setParameter("targetedRegion", "ABCDEFG");
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
        assertCurrentPageContains("You have successfully added a Morpholino to this model!");
        
        /* Find Morpholino link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");        
        assertNotNull("Unable to find link to edit the Morpholino", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if source is not listed");
        theForm = theCurrentPage.getFormWithName("morpholinoForm");
        theForm.setParameter("targetedRegion", "ABCDEFGHI"); 
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
        assertCurrentPageContains("You have successfully edited a Morpholino.");      
        
        /* Find Morpholino link to Delete*/ 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFGHI");
        assertNotNull("Unable to find link to delete the Morpholino", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if source is not listed");
        theForm = theCurrentPage.getFormWithName("morpholinoForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();  
      
        assertCurrentPageContains("You have successfully deleted a Morpholino."); 
    } 

}
