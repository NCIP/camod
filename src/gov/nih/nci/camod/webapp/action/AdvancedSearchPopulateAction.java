/**
 * 
 * $Id: AdvancedSearchPopulateAction.java,v 1.10 2006-12-11 19:28:48 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2006/10/17 16:11:00  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.8  2006/05/10 14:15:39  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.7  2006/04/28 19:23:34  schroedn
 * Defect # 261
 * Prepopulates the advanced search page with the saved query data to edit
 *
 * Revision 1.6  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import java.util.Set;

import gov.nih.nci.camod.domain.SavedQuery;
import gov.nih.nci.camod.domain.SavedQueryAttribute;
import gov.nih.nci.camod.service.SavedQueryManager;
import gov.nih.nci.camod.webapp.form.SearchForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import gov.nih.nci.camod.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class AdvancedSearchPopulateAction extends BaseAction {

    /**
     * Pre-populate all field values in the form <FormName> Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.info("In AdvancedSearchPopulateAction.populate");		       

        // Reset the non-simple-search options
        SearchForm theSearchForm = (SearchForm) form;
                
        String aQueryId = request.getParameter( Constants.Parameters.QUERYID ); 
      
        //Populate form field with savedQuery criteria
        if ( aQueryId != null )
        {
            theSearchForm.allFieldsReset();
            
            //Set Constant aQueryId
            //This will be used to determine if we are editing a query 
            request.getSession().setAttribute( Constants.ASAVEDQUERYID, aQueryId );
                        
            SavedQueryManager savedQueryManager = (SavedQueryManager) getBean("savedQueryManager");
            
            try {                
                SavedQuery inSavedQuery = savedQueryManager.get( aQueryId );
                
                log.info("<AdvancedSearchPopulateAction.populate> inSavedQuery.getQueryName(): " +inSavedQuery.getQueryName());
                
                request.getSession().setAttribute( Constants.QUERY_NAME, inSavedQuery.getQueryName() );
                
                // Retrieve the saved criteria and populate the fields in the SearchForm
                Set<SavedQueryAttribute> inSavedQueryAttributeList = inSavedQuery.getSavedQueryAttributes();                                
                savedQueryManager.buildSearchData( inSavedQueryAttributeList, theSearchForm );                
                
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
        
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PRINCIPALINVESTIGATORQUERYDROP,
                Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.NEWSPECIESDROP,
                Constants.Dropdowns.ADD_BLANK_OPTION);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CHEMICALDRUGQUERYDROP,
                Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.GROWTHFACTORQUERYDROP,
                Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.HORMONEQUERYDROP,
                Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.RADIATIONQUERYDROP,
                Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.VIRUSQUERYDROP,
                Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SURGERYQUERYDROP,
                Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.INDUCEDMUTATIONAGENTQUERYDROP,
                Constants.Dropdowns.ADD_BLANK);        
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.EXTERNALSOURCEQUERYDROP,
                Constants.Dropdowns.ADD_BLANK);  
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CARCINOGENICINTERVENTIONDROP,
                                         Constants.Dropdowns.ADD_BLANK);         

        return mapping.findForward("next");
    }
}
