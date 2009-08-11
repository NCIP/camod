/**
 * 
 * $Id: SubmitPageTest.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
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
 * Revision 1.2  2005/12/14 20:15:26  pandyas
 * Added JavaDocs
 *
 * 
 */
package web.submission;

import java.util.ResourceBundle;
import junit.framework.Test;
import junit.framework.TestSuite;
import web.base.BaseModelNeededTest;
import com.meterware.httpunit.WebLink;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class SubmitPageTest extends BaseModelNeededTest {

    public SubmitPageTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {

        ResourceBundle theBundle = ResourceBundle.getBundle("test");

        String theUsername = theBundle.getString("username");
        String thePassword = theBundle.getString("password");

        loginToApplication(theUsername, thePassword);
    }

    protected void tearDown() throws Exception {
        logoutOfApplication();
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(SubmitPageTest.class);
        return suite;
    }

    public void testAddModel() throws Exception {

        // Really used all over, but we can test it here
        createModel();
    }

    public void testDuplicateModel() throws Exception {

        //String theModelId = findModelIdOnPage("aModelID=", "\">" + myModelName);
        String theModelId = findModelIdOnPage("duplicate this record (" + myModelName, "delete this record (" + myModelName);
       
        theModelId = theModelId.replaceFirst("aModelID=", "");
        System.out.println( "theModelID = " + theModelId + "<");
        
        /*
        String theString = findModelIdOnPage("aModelID=", "' onclick='return");
        String theModelId = theString.substring(0, theString.length()-1);
        */
        //System.out.println("<testDuplicateModel> theModelId: " + theModelId);

        /*String theModelId = findModelIdOnPage("duplicate this record (" + myModelName, "delete this record ("  + myModelName);
         *          * 
         * <a href='/camod/DuplicateAnimalModelAction.do?method=duplicate&amp;aModelID=10009841' onclick='return confirm
         * (&#039;Are you sure you want to duplicate this record (_11750e1b55956fa9-b5b64ff11ab0a5b7)?&#039;);' >
         * <IMG src="images/dupRecord.gif" border=0></a>  
         * 
         * <a href='/camod/DeleteAnimalModelAction.do?method=delete&amp;aModelID=10009841' onclick='return confirm(&#039;Are you sure you want to delete this record (_11750e1b55956fa9-b5b64ff11ab0a5b7)?&#039;);' >
         * <IMG src="images/remove.gif" border=0/></a>    
         *
         *
         * <a href="/camod/SubmitAction.do?method=setModelConstants&amp;aModelID=10009841">_11750e1b55956fa9-b5b64ff11ab0a5b7 (Incomplete)</a>
         *
         *
         */

        // We may or may not have to hit the agreement link
        System.out.println( " test= " + "method=duplicate&amp;aModelID=" + theModelId );
        
        WebLink[] tst = myWebConversation.getCurrentPage().getLinks();
        
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_URL_STRING, "aModelID=" + theModelId);
        //System.out.println("<testDuplicateModel> theLink: " + theLink);
        
        assertNotNull("Couldn't find link to duplicate model", theLink);
        //System.out.println("<testDuplicateModel> assertNotNull");
        
        // Duplicate the model
        theLink.click();

        // Make sure it worked
        String theDupModelName = myModelName +" (Copy)";
        //System.out.println("<testDuplicateModel> theDupModelName: " + theDupModelName);
        
        assertCurrentPageContains(theDupModelName);
        //System.out.println("<testDuplicateModel> Here1: " + theDupModelName);

        theModelId = findModelIdOnPage("duplicate this record (" + theDupModelName, "delete this record ("
                + theDupModelName);
        //System.out.println("<testDuplicateModel> Here2: " + theDupModelName);
        
        // Get the delete link
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_URL_STRING,
                "method=delete&" + theModelId);

        //System.out.println("<testDuplicateModel> Here3: " + theDupModelName);
        assertNotNull("Couldn't find link to delete duplicated model", theLink);

        theLink.click();
        //TestUtil.getTextOnPage("Error: Bad or missing data", "* indicates a required field");

        assertCurrentPageDoesNotContain(theDupModelName);
    }

    public void testDeleteModel() throws Exception {

        // Really used all over, but we can test it here
        deleteModel();
    }
}
