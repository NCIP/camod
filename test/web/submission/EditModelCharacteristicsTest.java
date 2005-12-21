/**
 * @author pandyas
 * 
 * $Id: EditModelCharacteristicsTest.java,v 1.1 2005-12-21 20:09:33 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package web.submission;

import java.util.ResourceBundle;
import web.base.BaseModelNeededTest;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import junit.framework.Test;
import junit.framework.TestSuite;

public class EditModelCharacteristicsTest extends BaseModelNeededTest {

	public EditModelCharacteristicsTest(String arg0) {
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
        TestSuite suite = new TestSuite(EditModelCharacteristicsTest.class);
        return suite;
    }
    
    public void testModelCharacteristics() throws Exception {
    	navigateToModelForEditing(myModelName);
		
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_URL_STRING,
		"AnimalModelPopulateAction.do?method=populate");
        assertNotNull("Couldn't find link to model characteristics data", theLink);
        
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Is this model a toolmouse?");
		WebForm theForm = theCurrentPage.getFormWithName("modelCharacteristicsForm");		
    	
        theForm.setParameter("description", "Test Description");
    
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited the model characteristics!"); 
    }
}
