/**
 * 
 * $Id: SubmitEditDeleteCITest.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.2  2008/10/01 23:54:11  schroedn
 * Junit test fixes for caMOD 2.5 - revision 1
 *
 * Revision 1.1  2008/05/12 16:29:06  pandyas
 * Modified name for consistency
 *
 * Revision 1.7  2006/04/27 15:08:52  pandyas
 * Modified while testing caMod 2.1
 *
 * Revision 1.6  2006/04/17 19:37:34  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.5  2005/12/14 20:14:35  pandyas
 * Added JavaDocs
 *
 * 
 */
package unit.web.submission;

import java.util.ResourceBundle;
import junit.framework.Test;
import junit.framework.TestSuite;
import unit.web.base.BaseModelNeededTest;
import com.meterware.httpunit.*;

/** This is a simple example of using HttpUnit to read and understand web pages. * */
public class SubmitEditDeleteCITest extends BaseModelNeededTest {

    public SubmitEditDeleteCITest(String testName) {
        super(testName);
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
        TestSuite suite = new TestSuite(SubmitEditDeleteCITest.class);
        return suite;
    }

    public void testAddAntibody() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Antibody");        
        assertNotNull("Unable to find link to add a Antibody", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Antibody is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("antibodyForm");
        theForm.setParameter("name", "anti-CTLA-4 antibody");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added an Antibody to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "anti-CTLA-4 antibody");        
        assertNotNull("Unable to find link to edit a Antibody", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Antibody is not listed, then please");
        theForm = theCurrentPage.getFormWithName("antibodyForm");
        theForm.setParameter("name", "anti-CTLA-4 antibody");
        theForm.setParameter("type", "Male Only");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited an Antibody.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "anti-CTLA-4 antibody");        
        assertNotNull("Unable to find link to delete a Antibody", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Antibody is not listed, then please");
        theForm = theCurrentPage.getFormWithName("antibodyForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted an Antibody.");
    }    
 
    public void testAddBacteria() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Bacteria");        
        assertNotNull("Unable to find link to add a Bacteria", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Bacteria is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("bacteriaForm");
        theForm.setParameter("name", "helicobacter felis");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added a Bacteria to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "helicobacter felis");        
        assertNotNull("Unable to find link to edit a Bacteria", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Antibody is not listed, then please");
        theForm = theCurrentPage.getFormWithName("bacteriaForm");
        theForm.setParameter("name", "helicobacter felis");
        theForm.setParameter("type", "Male Only");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited a Bacteria.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "helicobacter felis");        
        assertNotNull("Unable to find link to delete a Bacteria", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Bacteria is not listed, then please");
        theForm = theCurrentPage.getFormWithName("bacteriaForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted a Bacteria.");
    } 
    
    public void testAddChemicalDrug() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        // We may or may not have to hit the agreement link
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Chemical/Drug");        
        assertNotNull("Unable to find link to enter a Chemical/Drug", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Chemical/Drug is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("chemicalDrugForm");
        theForm.setParameter("name", "1,3-butadiene");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added a Chemical / Drug to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "1,3-butadiene");        
        assertNotNull("Unable to find link to edit a Chemical/Drug", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Chemical/Drug is not listed, then please");
        theForm = theCurrentPage.getFormWithName("chemicalDrugForm");
        theForm.setParameter("name", "1,3-butadiene");
        theForm.setParameter("nscNumber", "123");        
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited a Chemical / Drug.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "1,3-butadiene");
        assertNotNull("Unable to find link to delete a Chemical/Drug", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Chemical/Drug is not listed, then please");
        theForm = theCurrentPage.getFormWithName("chemicalDrugForm");               
        theForm.getSubmitButton( "submitAction", "Delete" ).click();              
        assertCurrentPageContains("You have successfully deleted a Chemical / Drug.");                
    }
    
    public void testAddEnvironmentalFactor() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Environmental Factor");        
        assertNotNull("Unable to find link to add a Environmental Factor", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Enviromental Factor is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("environmentalFactorForm");
        theForm.setParameter("name", "mainstream cigarette smoke (MCS)");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully added an Environmental Factor to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "mainstream cig");        
        assertNotNull("Unable to find link to edit a Environmental Factor", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Enviromental Factor is not listed, then please");
        theForm = theCurrentPage.getFormWithName("environmentalFactorForm");
        theForm.setParameter("name", "mainstream cigarette smoke (MCS)");
        theForm.setParameter("administrativeRoute", "intravenous");
        theCurrentPage = theForm.submit();
        assertCurrentPageContains("You have successfully edited an Environmental Factor.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "mainstream cigarette");        
        assertNotNull("Unable to find link to delete a Environmental Factor", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Enviromental Factor is not listed, then please");
        theForm = theCurrentPage.getFormWithName("environmentalFactorForm");
        theForm.getSubmitButton( "submitAction", "Delete" ).click();
        assertCurrentPageContains("You have successfully deleted an Environmental Factor.");
    }
    
    public void testAddGeneDelivery() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
        		"Enter Gene Delivery");        
        assertNotNull("Unable to find link to add a Gene Delivery", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Viral Vector is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("geneDeliveryForm");
        theForm.setParameter("viralVector", "Adenovirus");
        theForm.setParameter("geneInVirus", "123");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added a Gene Delivery to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "123");        
        assertNotNull("Unable to find link to edit a Gene Delivery", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Viral Vector is not listed, then please");
        theForm = theCurrentPage.getFormWithName("geneDeliveryForm");
        theForm.setParameter("viralVector", "Adenovirus");
        theForm.setParameter("geneInVirus", "456");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited a Gene Delivery.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "456");        
        assertNotNull("Unable to find link to delete a Gene Delivery", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Viral Vector is not listed, then please");
        theForm = theCurrentPage.getFormWithName("geneDeliveryForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted a Gene Delivery.");
    }
    
    public void testAddGrowthFactor() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Growth Factor");        
        assertNotNull("Unable to find link to add a Growth Factor", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Growth Factor is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("growthFactorForm");
        theForm.setParameter("name", "insulin-like growth factor 1 (IGF1) (IGF-1) (human recombinant)");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added a Growth Factor to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "insulin-like gro");        
        assertNotNull("Unable to find link to edit a Growth Factor", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Growth Factor is not listed, then please");
        theForm = theCurrentPage.getFormWithName("growthFactorForm");
        theForm.setParameter("name", "insulin-like growth factor 1 (IGF1) (IGF-1) (human recombinant)");
        theForm.setParameter("type", "Male Only");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited a Growth Factor.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "insulin-like gro");        
        assertNotNull("Unable to find link to delete a Growth Factor", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Growth Factor is not listed, then please");
        theForm = theCurrentPage.getFormWithName("growthFactorForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted a Growth Factor.");
    }
    
    public void testAddHormone() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Hormone");        
        assertNotNull("Unable to find link to add a Hormone", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Hormone is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("hormoneForm");
        theForm.setParameter("name", "estrone");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added a Hormone to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "estrone");        
        assertNotNull("Unable to find link to edit a Hormone", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Hormone is not listed, then please");
        theForm = theCurrentPage.getFormWithName("hormoneForm");
        theForm.setParameter("name", "estrone");
        theForm.setParameter("type", "Male Only");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited a Hormone.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "estrone");        
        assertNotNull("Unable to find link to delete a Hormone", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Hormone is not listed, then please");
        theForm = theCurrentPage.getFormWithName("hormoneForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted a Hormone.");
    }
    
    public void testAddNutritionalFactor() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Nutritional Factor");        
        assertNotNull("Unable to find link to add a Nutritional Factor", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Nutritional Factor is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("nutritionalFactorForm");
        theForm.setParameter("name", "black tea");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added a Nutritional Factor to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "black tea");        
        assertNotNull("Unable to find link to edit a Nutritional Factor", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Nutritional Factor is not listed, then please");
        theForm = theCurrentPage.getFormWithName("nutritionalFactorForm");
        theForm.setParameter("name", "black tea");
        theForm.setParameter("type", "Male Only");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited a Nutritional Factor.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "black tea");        
        assertNotNull("Unable to find link to delete a Nutritional Factor", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Nutritional Factor is not listed, then please");
        theForm = theCurrentPage.getFormWithName("nutritionalFactorForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted a Nutritional Factor.");
    }
  
    public void testAddPlasmid() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Plasmid");        
        assertNotNull("Unable to find link to add a Plasmid", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Plasmid is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("plasmidForm");
        theForm.setParameter("name", "control plasmid pPGK");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added a Plasmid to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "estrone");        
        assertNotNull("Unable to find link to edit a Plasmid", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Plasmid is not listed, then please");
        theForm = theCurrentPage.getFormWithName("plasmidForm");
        theForm.setParameter("name", "control plasmid pPGK");
        theForm.setParameter("type", "Male Only");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited a Plasmid.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "estrone");        
        assertNotNull("Unable to find link to delete a Plasmid", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Plasmid is not listed, then please");
        theForm = theCurrentPage.getFormWithName("plasmidForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted a Plasmid.");
    }
    
    public void testAddRadiation() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, 
        		"Enter Radiation");        
        assertNotNull("Unable to find link to add a Radiation", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Radiation is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("radiationForm");
        theForm.setParameter("name", "beta-radiation");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added a Radiation to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "beta-rad");        
        assertNotNull("Unable to find link to edit a Radiation", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Radiation is not listed, then please");
        theForm = theCurrentPage.getFormWithName("radiationForm");
        theForm.setParameter("name", "beta-radiation");
        theForm.setParameter("type", "Male Only");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited a Radiation.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "beta-radi");        
        assertNotNull("Unable to find link to delete a Radiation", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Radiation is not listed, then please");
        theForm = theCurrentPage.getFormWithName("radiationForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted a Radiation.");
    }
 
    public void testAddSignalingMolecule() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Signaling Molecule");        
        assertNotNull("Unable to find link to add a Signaling Molecule", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Signaling Molecule is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("signalingMoleculeForm");
        theForm.setParameter("name", "uroguanylin");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added a Signaling Molecule to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "uroguanylin");        
        assertNotNull("Unable to find link to edit a Signaling Molecule", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Signaling Molecule is not listed, then please");
        theForm = theCurrentPage.getFormWithName("signalingMoleculeForm");
        theForm.setParameter("name", "uroguanylin");
        theForm.setParameter("type", "Male Only");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited a Signaling Molecule.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "uroguanylin");        
        assertNotNull("Unable to find link to delete a Signaling Molecule", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Signaling Molecule is not listed, then please");
        theForm = theCurrentPage.getFormWithName("signalingMoleculeForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted a Signaling Molecule.");
    }    
    
    public void testAddSurgeryOther() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
        		"Enter Surgery/Other");        
        assertNotNull("Unable to find link to add a Surgery/Other", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Surgery is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("surgeryForm");
        theForm.setParameter("name", "splenectomy");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added a Surgery / Other to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "splenectomy");        
        assertNotNull("Unable to find link to edit a Surgery/Other", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Surgery is not listed, then please");
        theForm = theCurrentPage.getFormWithName("surgeryForm");
        theForm.setParameter("name", "splenectomy");
        theForm.setParameter("type", "Male Only");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited a Surgery / Other.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "splenectomy");        
        assertNotNull("Unable to find link to delete a Surgery/Other", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Surgery is not listed, then please");
        theForm = theCurrentPage.getFormWithName("surgeryForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted a Surgery / Other.");
    }
 
    public void testAddTransposon() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "Enter Transposon");        
        assertNotNull("Unable to find link to add a Transposon", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Transposon is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("hormoneForm");
        theForm.setParameter("name", "firefly luciferase");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added a Transposon to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "firefly luciferase");        
        assertNotNull("Unable to find link to edit a Transposon", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Transposon is not listed, then please");
        theForm = theCurrentPage.getFormWithName("hormoneForm");
        theForm.setParameter("name", "firefly luciferase");
        theForm.setParameter("type", "Male Only");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited a Transposon.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "firefly luciferase");        
        assertNotNull("Unable to find link to delete a Transposon", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Transposon is not listed, then please");
        theForm = theCurrentPage.getFormWithName("hormoneForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted a Transposon.");
    }
    
    public void testAddViralTreatment() throws Exception {
        navigateToModelForEditing(myModelName);
        
        //Adding
        WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
        		"Enter Viral Treatment");        
        assertNotNull("Unable to find link to add a Viral Treatment", theLink);        
        WebResponse theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Virus is not listed, then please");
        WebForm theForm = theCurrentPage.getFormWithName("viralTreatmentForm");
        theForm.setParameter("name", "vaccinia virus");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully added a Viral Treatment to this model!");
        
        //Editing
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "vaccinia virus");        
        assertNotNull("Unable to find link to edit a Viral Treatment", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Virus is not listed, then please");
        theForm = theCurrentPage.getFormWithName("viralTreatmentForm");
        theForm.setParameter("name", "vaccinia virus");
        theForm.setParameter("type", "Male Only");        
        theCurrentPage = theForm.submit();        
        assertCurrentPageContains("You have successfully edited a Viral Treatment.");
        
        //Deleting
        theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT, "vaccinia virus");        
        assertNotNull("Unable to find link to delete a Viral Treatment", theLink);        
        theCurrentPage = theLink.click();        
        assertCurrentPageContains("(if Virus is not listed, then please");
        theForm = theCurrentPage.getFormWithName("viralTreatmentForm");     
        theForm.getSubmitButton( "submitAction", "Delete" ).click();        
        assertCurrentPageContains("You have successfully deleted a Viral Treatment.");
    }
}
