/**
 * @author pandyas
 * 
 * $Id: EditModelCharacteristicsTest.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.4  2008/10/01 23:54:12  schroedn
 * Junit test fixes for caMOD 2.5 - revision 1
 *
 * Revision 1.3  2006/10/11 15:47:41  pandyas
 * changes while testing 2.1.1
 *
 * Revision 1.2  2006/09/20 19:10:55  georgeda
 * toolMouse now tool strain
 *
 * Revision 1.1  2005/12/21 20:09:33  pandyas
 * Added test for editing a model characteristic
 *
 * 
 */

package unit.java.web.submission;

import java.util.ResourceBundle;
import unit.java.web.base.BaseModelNeededTest;
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
		assertCurrentPageContains("Is this model a tool strain?");
		WebForm theForm = theCurrentPage.getFormWithName("modelCharacteristicsForm");		
    	
        theForm.setParameter("description", "Test Description");
    
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited the Model Characteristics."); 
    }
}
