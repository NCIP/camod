/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteHistopathologyTest.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.3  2006/04/27 15:08:52  pandyas
 * Modified while testing caMod 2.1
 *
 * Revision 1.2  2005/12/13 21:56:32  pandyas
 * JUnit test case for SubmitDeleteEditHistopathology - depends on other changes in main path that will NOT be uploaded until we go to production
 *
 * Revision 1.1  2005/12/13 20:21:16  pandyas
 * initial version does not work
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

public class SubmitEditDeleteHistopathologyTest extends BaseModelNeededTest {

	public SubmitEditDeleteHistopathologyTest(String arg0) {
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
        TestSuite suite = new TestSuite(SubmitEditDeleteHistopathologyTest.class);
        return suite;
    }
    
    public void testHistopathology() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /*  Find Histopathology link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Histopathology");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("Tumor Incidence");
        WebForm theForm = theCurrentPage.getFormWithName("histopathologyForm");
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22498" );
        theForm.getScriptableObject().setParameterValue( "diagnosisCode", "C21898" );        
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Histopathology to this model!");
        
        /* Find Histopathology link to Edit */ 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Heart");        
        assertNotNull("Unable to find link to edit the Histopathology", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Tumor Incidence over Lifetime ");
        theForm = theCurrentPage.getFormWithName("histopathologyForm");
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22498" );
        theForm.getScriptableObject().setParameterValue( "diagnosisCode", "C22918" );
        theForm.setParameter("comments", "Unit Test Case");          
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Histopathology.");      
        
        /* Find Assoc Metastasis link to Submit */
        theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Assoc Metastasis");
        theCurrentPage = theLink.click(); 
        assertCurrentPageContains("Age of Metastasis");
        theForm = theCurrentPage.getFormWithName("associatedMetastasisForm");  
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22517" );
        theForm.getScriptableObject().setParameterValue( "diagnosisCode", "C21898" );
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added an Associated Metastasis to this model!");
        
        /* Find Assoc Metastasis link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Liver - ");        
        assertNotNull("Unable to find link to edit the Assoc Metastasis", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Age of Metastasis");
        theForm = theCurrentPage.getFormWithName("associatedMetastasisForm");
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22517" );
        theForm.getScriptableObject().setParameterValue( "diagnosisCode", "C21898" );
        theForm.setParameter("comments", "Unit Test Case");          
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Associated Metastasis.");      
        
        /* Find Clinical Marker link to Submit */
        theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Clinical Marker");
        theCurrentPage = theLink.click(); 
        assertCurrentPageContains("Other Clinical Marker");
        theForm = theCurrentPage.getFormWithName("clinicalMarkerForm");
        theForm.setParameter("name", "Estrogen Receptor (ER)");
        theForm.setParameter("value", "12"); 
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Clinical Marker to this model!");
        
        //The clinical marker displayName does not display on the left menu bar
        //This does not happen for manual testing
        /* Find Clinical Marker link to Edit 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Estrogen");        
        assertNotNull("Unable to find link to edit the Clinical Marker", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Clinical Marker");
        theForm = theCurrentPage.getFormWithName("clinicalMarkerForm");
        theForm.setParameter("name", "Progesterone Receptor (PR)");
        theForm.setParameter("value", "12");          
        theCurrentPage = theForm.submit();
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        assertCurrentPageContains("You have successfully edited a Clinical Marker.");      
        */
        /* Find Histopathology link to Delete */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Heart");
        assertNotNull("Unable to find link to delete the Histopathology", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Tumor Incidence over Lifetime ");
        theForm = theCurrentPage.getFormWithName("histopathologyForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Histopathology."); 
        
    }    

}
