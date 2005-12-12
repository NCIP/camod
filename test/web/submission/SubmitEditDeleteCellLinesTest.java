/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteCellLinesTest.java,v 1.1 2005-12-12 15:49:22 pandyas Exp $
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

public class SubmitEditDeleteCellLinesTest extends BaseModelNeededTest {

	public SubmitEditDeleteCellLinesTest(String arg0) {
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
        TestSuite suite = new TestSuite(SubmitEditDeleteCellLinesTest.class);
        return suite;
    }
    
    public void testCellLines() throws Exception {
        navigateToModel(myModelName);
        
        // Find Cell Lines link to Submit
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Cell Lines");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("Experiment:");
        WebForm theForm = theCurrentPage.getFormWithName("cellLineForm");
        theForm.setParameter("cellLineName", "ABCDEFG");
        theForm.getScriptableObject().setParameterValue( "organ", "Heart_MMHCC" );
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22498" );
        theForm.getScriptableObject().setParameterValue( "organTissueName", "Heart" );        
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Cell Line to this model!");
        
        /* Find Cell Lines link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");        
        assertNotNull("Unable to find link to edit the Cell Line", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Experiment:");
        theForm = theCurrentPage.getFormWithName("cellLineForm");
        theForm.setParameter("cellLineName", "ABCDEFG");        
        theForm.getScriptableObject().setParameterValue( "organ", "Heart_MMHCC" );
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22498" );
        theForm.getScriptableObject().setParameterValue( "organTissueName", "Heart" );
        theForm.setParameter("experiment", "Experiment Test");          
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Cell Line.");      
        
        /* Find Cell Lines link to Delete*/ 
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");
        assertNotNull("Unable to find link to delete the Cell Line", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Experiment:");
        theForm = theCurrentPage.getFormWithName("cellLineForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Cell Line."); 
           
    } 
    //TODO:  add publication test when completed
    //public void testPublication() throws Exception {}    	
    

}
