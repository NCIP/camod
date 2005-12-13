/**
 * @author pandyas
 * 
 * $Id: SubmitEditDeleteCellLinesTest.java,v 1.4 2005-12-13 20:24:15 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/12/12 16:28:32  pandyas
 * added testPublication to Cell Lines test class
 *
 * Revision 1.2  2005/12/12 15:52:47  pandyas
 * modified: navigateToModelForEditing(myModelName);
 *
 * Revision 1.1  2005/12/12 15:49:22  pandyas
 * JUnit test case for Cell Lines - initial version
 *
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
    	navigateToModelForEditing(myModelName);
        
        // Find Cell Lines link to Submit
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Cell Lines");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("Experiment:");
        WebForm theForm = theCurrentPage.getFormWithName("cellLineForm");
        theForm.setParameter("cellLineName", "ABCDEFG");
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22498" );
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Cell Line to this model!");
        
        /* Find Cell Lines link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "ABCDEFG");        
        assertNotNull("Unable to find link to edit the Cell Line", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("Experiment:");
        theForm = theCurrentPage.getFormWithName("cellLineForm");
        theForm.setParameter("cellLineName", "ABCDEFG");        
        theForm.getScriptableObject().setParameterValue( "organTissueCode", "C22498" );
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

    public void testPublication() throws Exception {
    	navigateToModelForEditing(myModelName);
        
        /* Find Publication link to Submit */
        WebLink theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Publication");
        WebResponse theCurrentPage = theLink.click(); 
        assertCurrentPageContains("For publications with a PubMed record");
        WebForm theForm = theCurrentPage.getFormWithName("publicationForm");
        theForm.setParameter("authors", "Pandya S");
        theForm.setParameter("name", "Published");
        theForm.setParameter("firstTimeReported", "yes");
        theForm.setParameter("pmid", "16323327");
        theForm.setParameter("title", "Dying with dignity: a round-table.");        
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Publication to this model!");
        
        /* Find Publication link to Edit */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Pandya");        
        assertNotNull("Unable to find link to edit the Publication", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("For publications with a PubMed record");
        theForm = theCurrentPage.getFormWithName("publicationForm");
        theForm.setParameter("authors", "Pandya S");
        theForm.setParameter("name", "Published");
        theForm.setParameter("firstTimeReported", "yes");
        theForm.setParameter("pmid", "16323327");
        theForm.setParameter("title", "Dying with dignity: a round-table.");  
        theForm.setParameter("year", "1999");         
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Publication.");      
        
        /* Find Publication link to Delete */
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Pandya");
        assertNotNull("Unable to find link to delete the Publication", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("For publications with a PubMed record");
        theForm = theCurrentPage.getFormWithName("publicationForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Publication.");         
    }  

}
