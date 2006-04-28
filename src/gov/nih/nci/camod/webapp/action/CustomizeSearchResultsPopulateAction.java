/**
 * 
 * $Id: CustomizeSearchResultsPopulateAction.java,v 1.1 2006-04-28 19:25:29 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.ResultSettings;
import gov.nih.nci.camod.domain.ResultSettingsColumns;
import gov.nih.nci.camod.service.ResultSettingsManager;
import gov.nih.nci.camod.webapp.form.CustomizeSearchResultsForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CustomizeSearchResultsPopulateAction extends BaseAction
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, HttpServletResponse response) 
    throws Exception {

        log.trace("Entering CustomizeSearchResultsPopulateAction.execute");
        
        CustomizeSearchResultsForm theForm = (CustomizeSearchResultsForm) form;
                
        // Create a form to edit
        ResultSettingsManager resultSettingsManager = (ResultSettingsManager) getBean("resultSettingsManager");
        
        // Get ResultSettings by username
        ResultSettings rSettings = resultSettingsManager.getByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) );
        
        // Set Defaults
        String[] savedColumns = {"Model Descriptor" , "Tumor Sites", "Species" } ;
        int pageItems = 15;
        
        List selectedList = new ArrayList();
        
        // If no saved prefrences, set to default
        if ( rSettings != null ) {                        
                        
            // Grab saved preferences
            Set<ResultSettingsColumns> sqaList= rSettings.getResultSettingsColumns();
            Iterator<ResultSettingsColumns> sqaIter = sqaList.iterator();                   
            pageItems = rSettings.getItemsPerPage();
            
            while ( sqaIter.hasNext() ){
                ResultSettingsColumns sqa = (ResultSettingsColumns) sqaIter.next();
                System.out.println( "ResultSettingsColumns: " + sqa.getColumnName() );                        
                selectedList.add( sqa.getColumnName() );
            }
                        
        } else {
            
            // Set default preferences
            //pageItems = rSettings.getItemsPerPage();
            //String strColumns = rSettings.getColumnsToDisplay();
            //savedColumns = strColumns.split( "," );
            
            // Convert Array into a List     
            selectedList = Arrays.asList(savedColumns);
        }
        
        // Grab the List of possible columns from a file
        NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.SEARCHRESULTCOLUMNS, null );                 
        NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.ITEMSPERPAGE, null );  
        
        // Convert Array into a List
        //List selectedList = Arrays.asList(savedColumns);        
        
        // Retrieve column list from file
        List columnList = (List) request.getSession().getAttribute(Constants.Dropdowns.SEARCHRESULTCOLUMNS);
              
        // Remove any saved savedColumns that are not in file        
        // Retrieve all options
        //List middleList = (List) request.getSession().getAttribute(Constants.SEARCHRESULTCOLUMNS);
        //middleList.remove( selectedList );
        //selectedList = columnList;
        //selectedList.remove( middleList );
                
        // Remove all from ColumnsToDisplay that are on SelectedColumnsToDisplay
        columnList.remove( selectedList );
        
        // Set the attributes to the Form
        theForm.setItemsPerPage( String.valueOf( pageItems ) );
        
        //Set the selection fields        
        request.getSession().setAttribute( Constants.Dropdowns.SEARCHRESULTCOLUMNS,  columnList );
        request.getSession().setAttribute( Constants.Dropdowns.SELECTEDSEARCHRESULTCOLUMNS, selectedList );
        
        String theForward = "next";

        log.debug("Forwarding to model section: " + theForward);
        log.trace("Exiting CustomizeSearchResultsAction.execute");

        return mapping.findForward(theForward);
        
    }
}
