/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteTransplantTest.java,v 1.3 2008-10-01 23:54:12 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2008/01/16 18:28:43  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.1  2007/11/01 13:53:50  pandyas
 * Fixed #8290     Rename graft object into transplantion object
 *
 * Revision 1.2  2007/08/08 16:04:28  pandyas
 * Removed reference to transplant - as per VCDE changes
 *
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

public class SubmitEditDeleteTransplantTest extends BaseModelNeededTest {

	public SubmitEditDeleteTransplantTest(String arg0) {
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
        TestSuite suite = new TestSuite(SubmitEditDeleteTransplantTest.class);
        return suite;
    }
    
    public void testTransplant() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /* Find Transplant link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Transplant");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("- if transplant type is not listed");
        
        WebForm theForm = theCurrentPage.getFormWithName("TransplantForm");
        theForm.setParameter("name", "ABCDEFG");
        theForm.setParameter("sourceType", "Cell Line");        
        theForm.setParameter("donorScientificName", "Mus musculus");
        theCurrentPage = theForm.submit();
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        assertCurrentPageContains("if strain is not listed");

        // Set the donorEthnicityStrain and submit again
        theForm = theCurrentPage.getFormWithName("TransplantForm");
        theForm.setParameter("donorEthinicityStrain", "129");
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
        assertCurrentPageContains("You have successfully added a Transplant");
        
        
        /* Find Transplant link to Edit 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");        
        assertNotNull("Unable to find link to edit the Transplant", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if Transplant type is not listed");
        theForm = theCurrentPage.getFormWithName("TransplantForm");
        theForm.setParameter("name", "ABCDEFG");
        theForm.setParameter("donorScientificName", "Mus musculus"); 
        theForm.setParameter("sourceType", "Cell Line");
        theCurrentPage = theForm.submit();
        
        // Set the ethnicity strain and submit again
        theForm = theCurrentPage.getFormWithName("TransplantForm");
        theForm.setParameter("donorEthinicityStrain", "129");
        theForm.setParameter("parentalCellLineName", "Parent Cell Line");
        theCurrentPage = theForm.submit();
        TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageContains("You have successfully edited a Transplant.");      
        */
        
        /* Find Transplant link to Delete 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");
        assertNotNull("Unable to find link to delete the Transplant", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("if Transplant type is not listed");
        theForm = theCurrentPage.getFormWithName("TransplantForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Transplant.");
        */ 
    }    

}
