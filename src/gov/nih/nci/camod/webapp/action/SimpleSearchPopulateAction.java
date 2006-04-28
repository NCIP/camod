/**
 * 
 * $Id: SimpleSearchPopulateAction.java,v 1.7 2006-04-28 19:28:50 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.SavedQuery;
import gov.nih.nci.camod.domain.SavedQueryAttribute;
import gov.nih.nci.camod.service.QueryStorageManager;
import gov.nih.nci.camod.webapp.form.SearchForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class SimpleSearchPopulateAction extends BaseAction {

    /**
     * Pre-populate all field values in the form <FormName> Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.trace("In AdvancedSearchPopulateAction.populate");
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PRINCIPALINVESTIGATORQUERYDROP,
                Constants.Dropdowns.ADD_BLANK);

        // Reset the non-simple-search options
        SearchForm theSearchForm = (SearchForm) form;
        theSearchForm.simpleSearchReset();
        
        String aQueryId = request.getParameter("aQueryId"); 
        
        //Populate form field with savedQuery criteria
        if ( aQueryId != null )
        {
            //Set Constant aQueryId
            //This will be used to determine if we are editing a query 
            request.getSession().setAttribute( Constants.ASAVEDQUERYID, aQueryId );
                        
            QueryStorageManager queryStorageManager = (QueryStorageManager) getBean("queryStorageManager");
            
            try {                
                SavedQuery sq = queryStorageManager.get( aQueryId );
                
                request.getSession().setAttribute( Constants.QUERY_NAME, sq.getQueryName() );
                
                // Retrieve the saved criteria and populate the fields in the SearchForm
                Set<SavedQueryAttribute> sqaList = sq.getSavedQueryAttributes();                                
                theSearchForm = queryStorageManager.buildSearchData( sqaList, theSearchForm );                
                
            } catch ( Exception e) {
                // Set the error message
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
                saveErrors(request, msg);
            }
        } else {
            
            //Clear out Constant aQueryId
            request.getSession().setAttribute( Constants.QUERY_NAME, null );
            request.getSession().setAttribute( Constants.ASAVEDQUERYID, null );
        }  
        
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.NEWSPECIESDROP,
                Constants.Dropdowns.ADD_BLANK_OPTION);

        return mapping.findForward("next");
    }
    
}
