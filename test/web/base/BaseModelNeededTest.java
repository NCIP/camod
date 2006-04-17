/**
 * 
 * $Id: BaseModelNeededTest.java,v 1.3 2006-04-17 19:37:32 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/12/13 19:17:51  pandyas
 * added JavaDocs
 *
 * 
 */

package web.base;

import gov.nih.nci.camod.util.GUIDGenerator;

import com.meterware.httpunit.*;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class BaseModelNeededTest extends BaseHttpTest {

    protected static String myModelName = null;
   

    public BaseModelNeededTest(String testName) {
        super(testName);
    }

    protected void createModel() throws Exception {

        // Assume we are logged in. Click the SUBMIT button
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                "SUBMIT MODELS");

        assertNotNull("Couldn't find link to submission page", theLink);

        theLink.click();

        // We may or may not have to hit the agreement link
        theLink = myWebConversation.getCurrentPage()
                .getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Add New Model");

        assertNotNull("Couldn't find link to add new model", theLink);

        WebResponse theCurrentPage = theLink.click();
        assertCurrentPageContains("Model Characteristics");

        // Fill in the values and hit submit; Should fail the first time and the
        // populate will fill in the ethnicity strain values
        WebForm theForm = theCurrentPage.getFormWithName("modelCharacteristicsForm");

        myModelName = "_" + GUIDGenerator.getInstance().genNewGuid();
        theForm.setParameter("modelDescriptor", myModelName);
        theForm.setParameter("principalInvestigator", "shenc");
        theForm.setParameter("scientificName", "Mus musculus");
        theForm.setParameter("description", "Test Phenotype");
        theCurrentPage = theForm.submit();

        // Set the ethnicity strain and submit again
        theForm = theCurrentPage.getFormWithName("modelCharacteristicsForm");
        theForm.setParameter("ethinicityStrain", "129");
        theCurrentPage = theForm.submit();

        assertCurrentPageContains("You have successfully created a new model!");
    }

    protected void deleteModel() throws Exception {
        
        // Assume we are logged in. Click the SUBMIT button
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
                "SUBMIT MODELS");

        theLink.click();
        
        String theModelId = findModelIdOnPage("duplicate this record (" + myModelName, "delete this record ("
                + myModelName);

        // We may or may not have to hit the agreement link
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_URL_STRING,
                "method=delete&" + theModelId);

        assertNotNull("Couldn't find link to delete model", theLink);

        theLink.click();

        assertCurrentPageDoesNotContain(myModelName);
    }
}
