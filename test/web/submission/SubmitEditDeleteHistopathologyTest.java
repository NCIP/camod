/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteHistopathologyTest.java,v 1.1 2005-12-13 20:21:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
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
        
        // Find Histopathology link to Submit
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Histopathology");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("Tumor Incidence");
        WebForm theForm = theCurrentPage.getFormWithName("histopathologyForm");
   
        //theForm.getScriptableObject().setParameterValue( "organ", "Heart_MMHCC" );
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22498" );
        //theForm.getScriptableObject().setParameterValue( "organTissueName", "Heart" );
        //theForm.getScriptableObject().setParameterValue( "tumorClassification", "Myeloid_Dysplasia_of_Mouse" );
        //theForm.getScriptableObject().setParameterValue( "diagnosisName", "Myeloid_Dysplasia" ); 
        theForm.setParameter( "diagnosisCode", "C21898");
        //.getScriptableObject().setParameterValue( "diagnosisCode", "C21898" );        

        //addPresetParameter("diagnosisCode", "C21898");
        //theForm.setParameter("diagnosisCode", "C21898");        
        //theForm.setParameter("diagnosisName", "Myeloid Dysplasia");


        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Histopathology to this model!");
        
        /* Find Histopathology link to Edit 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Heart");        
        assertNotNull("Unable to find link to edit the Histopathology", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Tumor Incidence over Lifetime ");
        theForm = theCurrentPage.getFormWithName("histopathologyForm");
        theForm.getScriptableObject().setParameterValue( "organ", "Heart_MMHCC" );
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22498" );
        theForm.getScriptableObject().setParameterValue( "organTissueName", "Heart" );
        theForm.getScriptableObject().setParameterValue( "tumorClassification", "Cardiovascular System Neoplasms" );
        theForm.getScriptableObject().setParameterValue( "diagnosisCode", "C22918" );
        theForm.getScriptableObject().setParameterValue( "diagnosisName", "Cardiovascular System Neoplasms" ); 
        theForm.setParameter("comments", "Unit Test Case");          
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Histopathology.");      
        */
        /* Find Histopathology link to Delete 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Heart");
        assertNotNull("Unable to find link to delete the Histopathology", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Tumor Incidence over Lifetime ");
        theForm = theCurrentPage.getFormWithName("histopathologyForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Histopathology."); 
        */   
    } 
    
    public void testClinicalMarker() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /* Find Clinical Marker link to Submit 
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Clinical Marker");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("Select Clinical");
        WebForm theForm = theCurrentPage.getFormWithName("clinicalMarkerForm");
        theForm.setParameter("name", "ABCD");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Clinical Marker to this model!");
        */
        /* Find Model Availablity link to Edit 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCD");        
        assertNotNull("Unable to find link to edit the Availability from Jackson Lab", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Select Clinical");
        theForm = theCurrentPage.getFormWithName("clinicalMarkerForm");
        theForm.setParameter("name", "ABCD");
        theForm.setParameter("value", "12345");          
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Clinical Marker.");      
        */
        /* Find Model Availablity link to Delete 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCD");
        assertNotNull("Unable to find link to delete the Availability from Jackson Lab", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Select Clinical");
        theForm = theCurrentPage.getFormWithName("clinicalMarkerForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Clinical Marker."); 
        */
    }    

}
