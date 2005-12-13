/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteHistopathologyTest.java,v 1.2 2005-12-13 21:56:32 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/12/13 20:21:16  pandyas
 * initial version does not work
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
        assertCurrentPageContains("Select Clinical");
        theForm = theCurrentPage.getFormWithName("clinicalMarkerForm");
        theForm.setParameter("name", "Progesterone Receptor (PR)");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Clinical Marker to this model!");
        
        /* Find Clinical Marker link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Progesterone Receptor");        
        assertNotNull("Unable to find link to edit the Clinical Marker", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Select Clinical");
        theForm = theCurrentPage.getFormWithName("clinicalMarkerForm");
        theForm.setParameter("name", "Progesterone Receptor (PR)");
        theForm.setParameter("value", "1234567890");          
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Clinical Marker.");      
        
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
