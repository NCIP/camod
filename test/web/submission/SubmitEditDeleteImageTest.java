/**
 * 
 * $Id: SubmitEditDeleteImageTest.java,v 1.4 2006-10-23 17:08:38 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/09/20 19:16:24  georgeda
 * added getTextOnPage method
 *
 * Revision 1.2  2005/12/27 19:19:34  georgeda
 * Test cleanup
 *
 * Revision 1.1  2005/12/27 15:04:09  georgeda
 * Test cleanup
 *
 * Revision 1.2  2005/12/14 20:14:55  pandyas
 * Added JavaDocs
 *
 * 
 */
package web.submission;

import java.io.File;
import java.util.ResourceBundle;

import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseModelNeededTest;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class SubmitEditDeleteImageTest extends BaseModelNeededTest
{

    public SubmitEditDeleteImageTest(String testName)
    {
        super(testName);
    }

    protected void setUp() throws Exception
    {

        ResourceBundle theBundle = ResourceBundle.getBundle("test");

        String theUsername = theBundle.getString("username");
        String thePassword = theBundle.getString("password");

        loginToApplication(theUsername, thePassword);
        createModel();
    }

    protected void tearDown() throws Exception
    {
        deleteModel();
        logoutOfApplication();
    }

    public static Test suite()
    {
        TestSuite suite = new TestSuite(SubmitEditDeleteImageTest.class);
        return suite;
    }

    public void testImage() throws Exception
    {
        navigateToModelForEditing(myModelName);
        ResourceBundle theBundle = ResourceBundle.getBundle("test");

        // Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Images");
        assertNotNull("Unable to find link to enter a Image", theLink);
        WebResponse theCurrentPage = theLink.click();
        assertCurrentPageContains("(Image of type .jpg, .jpeg, .gif, .sid or .png)");
        WebForm theForm = theCurrentPage.getFormWithName("imageForm");
        theForm.setParameter("fileLocation", new File(theBundle.getString("deploydir") + "/images/iconHelp.gif"));
        theForm.setParameter("title", "test image");
        theCurrentPage = theForm.submit();
        //TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
        
        assertCurrentPageContains("You have successfully added an Image to this model!");

        // Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "test image");
        assertNotNull("Unable to find link to edit a Image", theLink);
        theCurrentPage = theLink.click();
        assertCurrentPageContains("(Image of type .jpg, .jpeg, .gif, .sid or .png)");
        theForm = theCurrentPage.getFormWithName("imageForm");
        // theForm.setParameter("fileLocation", new
        // File(theBundle.getString("imagedirectory")) );
        theForm.setParameter("title", "test image1");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Image.");

        // Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "test image1");
        assertNotNull("Unable to find link to delete a Image", theLink);
        theCurrentPage = theLink.click();
        assertCurrentPageContains("(Image of type .jpg, .jpeg, .gif, .sid or .png)");
        theForm = theCurrentPage.getFormWithName("imageForm");
        theForm.getSubmitButton("submitAction", "Delete").click();
        // TODO: Add check to see if Image is successfully removed from caIMAGE
        assertCurrentPageContains("You have successfully deleted an Image.");
    }
}