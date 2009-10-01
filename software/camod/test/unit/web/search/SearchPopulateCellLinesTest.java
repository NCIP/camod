/**
 * @author pandyas
 * 
 * $Id: SearchPopulateCellLinesTest.java,v 1.1 2009-07-13 17:45:12 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2009/07/07 17:46:54  pandyas
 * modified according to the recommended directory layout for BDA
 *
 * Revision 1.3  2008/10/01 23:54:11  schroedn
 * Junit test fixes for caMOD 2.5 - revision 1
 *
 * Revision 1.2  2006/04/27 15:08:43  pandyas
 * Modified while testing caMod 2.1
 *
 * Revision 1.1  2006/01/06 16:08:22  pandyas
 * Added testing for populate methods
 *
 * Revision 1.4  2005/12/29 18:43:30  pandyas
 * Fixed defect# 286: Link to Publications not showing up for just a cell line pub
 *
 * Revision 1.3  2005/12/22 19:57:13  pandyas
 * Add explicit setParameters for firstTimeReported radioButton
 *
 * Revision 1.2  2005/12/16 17:27:34  pandyas
 * Added a cell line publication to script - there are two issues that need resolved - there is additional source code to upload for this to run
 *
 * Revision 1.1  2005/12/13 19:55:49  pandyas
 * JUnit test case for Search Cell Line
 *
 * 
 */
 
package unit.web.search;

import gov.nih.nci.camod.webapp.form.CellLineForm;
import gov.nih.nci.camod.webapp.form.PublicationForm;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

import unit.web.base.BaseModelNeededTest;
import unit.web.util.TestUtil;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import junit.framework.Test;
import junit.framework.TestSuite;

public class SearchPopulateCellLinesTest extends BaseModelNeededTest {

	public SearchPopulateCellLinesTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		
		try {
			
			setupJNDIdatasource();
			
        } catch (NamingException ex) {
            System.out.println("NamingException in datasouuce binding: " + SearchPopulateCellLinesTest.class.getName());
        }
        
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
        TestSuite suite = new TestSuite(SearchPopulateCellLinesTest.class);
        return suite;
    }
    
	public void testPopulateCellLines() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding a Cell Line
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Cell Lines");
		assertNotNull("Unable to find link to enter a Cell Line", theLink);		
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Experiment:");
		WebForm theWebForm = theCurrentPage.getFormWithName("cellLineForm");

		CellLineForm theForm = new CellLineForm();
		theForm.setCellLineName("ABCDEFGH");		
		theForm.setOrgan("Heart");
		theForm.setOrganTissueName("Heart");		
		theForm.setOrganTissueCode("C22498");

		/* Add parameters found on submit screen but not displayed on search screen  */
		List<String> theParamsToSkip = new ArrayList<String>();		
		theParamsToSkip.add("organTissueCode");
		theParamsToSkip.add("organTissueName");
		//TODO:  Page does not contain Heart error - must fix this
		
		TestUtil.setRandomValues(theForm, theWebForm, false);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		//TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

		assertCurrentPageContains("You have successfully added a Cell Line to this model!");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"ABCDEFGH");
		assertNotNull("Unable to find link to populate the Cell Line", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("Experiment:");
		theWebForm = theCurrentPage.getFormWithName("cellLineForm");
		
		//Add parameters found behind but not populate screen
		theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("aCellID");
		theParamsToSkip.add("submitAction");
		//TODO: skipped until error can be fixed in EVS API
		//theParamsToSkip.add("organ");
		//theParamsToSkip.add("organTissueName");
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
		
		/******* Adding Publication to cell line **********************************/
		navigateToModelForEditing(myModelName);

		// Adding a Publication
		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(
				WebLink.MATCH_TEXT, "Enter Publication for Cell Line");
		assertNotNull("Unable to find link to enter a Publication for Cell line", theLink);
		theCurrentPage = theLink.click();		
		assertCurrentPageContains("For publications with a PubMed record");
		theWebForm = theCurrentPage.getFormWithName("publicationForm");
		// set explicitly so validation works
		theWebForm.setParameter("firstTimeReported", "yes");

		PublicationForm thePubForm = new PublicationForm();
        thePubForm.setVolume("10");
        thePubForm.setTitle("title");  
        thePubForm.setStartPage("1111");        
		thePubForm.setAuthors("AUTHORSABCDEFGH");
		thePubForm.setFirstTimeReported("yes");
		thePubForm.setPmid("16323327");
		thePubForm.setYear("1999");
		thePubForm.setEndPage("9999");
        thePubForm.setJaxJNumber("12345");        

		//TODO: clean up the use of aCellID and ACellID, check ATherapyID and APubID also
		List<String> theParamsToIgnore = new ArrayList<String>();
		theParamsToIgnore.add("volume");
		theParamsToIgnore.add("title");		
		theParamsToIgnore.add("aCellID");
		theParamsToIgnore.add("ACellID");		
		theParamsToIgnore.add("APubID");
		theParamsToIgnore.add("ATherapyID");
        theParamsToIgnore.add("jaxJNumber");

		//Add parameters found on submit screen but not displayed on search
		theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("firstTimeReported");
		theParamsToSkip.add("aCellID");
		theParamsToSkip.add("ACellID");		
		theParamsToSkip.add("APubID");
        theParamsToSkip.add("startPage");
        theParamsToSkip.add("endPage");
        theParamsToSkip.add("jaxJNumber");
		//theParamsToSkip.add("volume");
		//theParamsToSkip.add("title");

		TestUtil.setRandomValues(thePubForm, theWebForm, false,	theParamsToIgnore);
		TestUtil.setValuesOnForm(thePubForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added a Publication to this model! ");

		// Verify that populate method returns complete and correct data
		navigateToModelForEditing(myModelName);

		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"AUTHORSABCDEFGH");
		assertNotNull("Unable to find link to verify cell line publication", theLink);		
		theCurrentPage = theLink.click();
		assertCurrentPageContains("For publications with a PubMed record");
		theWebForm = theCurrentPage.getFormWithName("publicationForm");
		
		//Add parameters found behind but not populate screen
		theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("submitAction");		
		theParamsToSkip.add("APubID");
		theParamsToSkip.add("ATherapyID");		
		theParamsToSkip.add("ACellID");
        theParamsToSkip.add("jaxJNumber");
		
		verifyValuesOnPopulatePage(theWebForm, theParamsToSkip);
		
	}    
    
	public void testSearchForCellLines() throws Exception {

		navigateToModelForEditing(myModelName);

		// Adding a Cell Line
		WebLink theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(WebLink.MATCH_CONTAINED_TEXT,
				"Enter Cell Lines");
		assertNotNull("Unable to find link to enter a Cell Line", theLink);		
		WebResponse theCurrentPage = theLink.click();
		assertCurrentPageContains("Experiment:");
		WebForm theWebForm = theCurrentPage.getFormWithName("cellLineForm");

		CellLineForm theForm = new CellLineForm();
		theForm.setOrgan("Heart");
		theForm.setOrganTissueName("Heart");		
		theForm.setOrganTissueCode("C22498");

		/* Add parameters found on submit screen but not displayed on search screen  */
		List<String> theParamsToSkip = new ArrayList<String>();		
		theParamsToSkip.add("organTissueCode");
		theParamsToSkip.add("organTissueName");
		
		//Page does not contain Heart error - must fix this
		theParamsToSkip.add("organ");
		
		TestUtil.setRandomValues(theForm, theWebForm, false);
		TestUtil.setValuesOnForm(theForm, theWebForm);
		
		theCurrentPage = theWebForm.submit();
		TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");

		assertCurrentPageContains("You have successfully added a Cell Line to this model!");

		// ???
		TestUtil.moveModelToEditedApproved(myModelName);

		navigateToSpecificSearchPage(myModelName,"CELL LINES");
		
		verifyValuesOnPage(theWebForm, theParamsToSkip);
		
		/******* Adding Publication to cell line ***********/
		navigateToModelForEditing(myModelName);

		// Adding a Publication
		theLink = myWebConversation.getCurrentPage().getFirstMatchingLink(
				WebLink.MATCH_TEXT, "Enter Publication for Cell Line");
		assertNotNull("Unable to find link to enter a Publication for Cell line", theLink);
		theCurrentPage = theLink.click();		
		assertCurrentPageContains("For publications with a PubMed record");
		theWebForm = theCurrentPage.getFormWithName("publicationForm");
		// set explicitly so validation works
		theWebForm.setParameter("firstTimeReported", "yes");

		PublicationForm thePubForm = new PublicationForm();
		thePubForm.setFirstTimeReported("yes");
		thePubForm.setPmid("16323327");
		thePubForm.setYear("1999");
		thePubForm.setStartPage("1111");
		thePubForm.setEndPage("9999");		
		thePubForm.setVolume("10");
		thePubForm.setTitle("title");
        thePubForm.setJaxJNumber("12345");

		//TODO: clean up the use of aCellID and ACellID, check ATherapyID and APubID also
		List<String> theParamsToIgnore = new ArrayList<String>();
		theParamsToIgnore.add("volume");
		theParamsToIgnore.add("title");		
		theParamsToIgnore.add("aCellID");
		theParamsToIgnore.add("ACellID");		
		theParamsToIgnore.add("APubID");
		theParamsToIgnore.add("ATherapyID");
        theParamsToIgnore.add("jaxJNumber");

		//Add parameters found on submit screen but not displayed on search
		theParamsToSkip = new ArrayList<String>();
		theParamsToSkip.add("firstTimeReported");
		theParamsToSkip.add("aCellID");
		theParamsToSkip.add("ACellID");		
		theParamsToSkip.add("APubID");
		theParamsToSkip.add("volume");
		theParamsToSkip.add("title");
		theParamsToSkip.add("jaxJNumber");		
		

		TestUtil.setRandomValues(thePubForm, theWebForm, false,	theParamsToIgnore);
		TestUtil.setValuesOnForm(thePubForm, theWebForm);

		theCurrentPage = theWebForm.submit();
		TestUtil.getTextOnPage(theCurrentPage, "Error: Bad or missing data", "* indicates a required field");
		
		assertCurrentPageContains("You have successfully added a Publication to this model! ");

		TestUtil.moveModelToEditedApproved(myModelName);

		// check if publication is on cell line page
		navigateToSpecificSearchPage(myModelName, "CELL LINES");
		verifyValuesOnPage(theWebForm, theParamsToSkip);
		
		/* check if publication is on publication search page */
		navigateToSpecificSearchPage(myModelName, "PUBLICATIONS");
		verifyValuesOnPage(theWebForm, theParamsToSkip);
		
	}  

}
