/**
 * 
 * $Id: CustomizeSearchResultsPopulateAction.java,v 1.3 2006-05-17 14:16:26 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/05/10 14:15:39  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.1  2006/04/28 19:25:29  schroedn
 * Defect # 238
 * Saves / Updates user settings for search result columns
 *
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

/**
 * Populates the CustomizeSearchResultsForm with either default settings or the currently logged
 * on user's settings.
 * 
 * @author Administrator
 *
 */
public class CustomizeSearchResultsPopulateAction extends BaseAction
{
    /**
     * Pre-populate all fields for CustomizeSearchResultsForm for CustomizeSearchResults.jsp
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception
    {

        log.debug("Entering CustomizeSearchResultsPopulateAction.execute");

        CustomizeSearchResultsForm theForm = (CustomizeSearchResultsForm) form;

        // Create a form to edit
        ResultSettingsManager resultSettingsManager = (ResultSettingsManager) getBean("resultSettingsManager");

        // Get ResultSettings by username
        ResultSettings rSettings = resultSettingsManager.getByUsername((String) request.getSession().getAttribute(Constants.CURRENTUSER));

        // Set Defaults
        String[] savedColumns = Constants.SEARCHRESULTCOLUMNSDEFAULT;
        int pageItems = Constants.ITEMSPERPAGEDEFAULT;

        List selectedList = new ArrayList();

        // Load user's settings
        if (rSettings != null)
        {
            pageItems = rSettings.getItemsPerPage();

            // Grab saved preferences
            Set<ResultSettingsColumns> theResultSettingsColumnsList = rSettings.getResultSettingsColumns();          
            Iterator<ResultSettingsColumns> rscIter = theResultSettingsColumnsList.iterator();
            String[] theColumns = new String[theResultSettingsColumnsList.size()];

            while (rscIter.hasNext())
            {
                ResultSettingsColumns theResultSettingsColumns = (ResultSettingsColumns) rscIter.next();
                theColumns[theResultSettingsColumns.getColumnOrder()] = theResultSettingsColumns.getColumnName();
            }
            theForm.setSelectedColumnsToDisplay(theColumns);

        }
        else
        {
            // If no saved prefrences, set to default
            // Convert Array into a List     
            selectedList = Arrays.asList(savedColumns);
            theForm.setSelectedColumnsToDisplay( savedColumns );
        }

        // Grab the List of possible columns from a file
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEARCHRESULTCOLUMNSDROP, null);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ITEMSPERPAGEDROP, null);

        // Retrieve column list from file
        List columnList = (List) request.getSession().getAttribute(Constants.Dropdowns.SEARCHRESULTCOLUMNSDROP);

        // Remove any saved savedColumns that are not in file        
        // Remove all from ColumnsToDisplay that are on SelectedColumnsToDisplay
        columnList.remove(selectedList);

        //Set the selection fields        
        request.getSession().setAttribute(Constants.Dropdowns.SEARCHRESULTCOLUMNSDROP, columnList);

        // Set the attributes to the Form
        theForm.setItemsPerPage(String.valueOf(pageItems));

        String theForward = "next";

        log.debug("Forwarding to model section: " + theForward);
        log.debug("Exiting CustomizeSearchResultsAction.execute");

        return mapping.findForward(theForward);
    }
}
