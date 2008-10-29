/**
 * 
 * $Id: SimpleSearchPopulateAction.java,v 1.16 2008-10-29 07:06:25 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.15  2008/07/02 17:43:51  pandyas
 * Commented out debug statements
 *
 * Revision 1.14  2008/05/27 14:37:02  pandyas
 * Modified to prevent SQL injection
 * Cleaned method name before proceeding
 * Re: Apps Scan run 05/23/2008
 *
 * Revision 1.13  2007/09/12 19:36:40  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.12  2007/05/21 17:33:55  pandyas
 * Modified simple and adv search species drop down to pull from DB (approved model species only)
 *
 * Revision 1.11  2007/05/18 14:40:49  pandyas
 * Modified species and organ tree to default to no organ button and to work when user goes from species to empty selection
 *
 * Revision 1.10  2007/05/16 12:29:35  pandyas
 * Modified adv and simple search vocab tree section to populate depending on species selected
 *
 * Revision 1.9  2006/10/17 16:11:00  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.8  2006/05/10 14:15:39  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.7  2006/04/28 19:28:50  schroedn
 * Defect # 261
 * Prepopulates form with SaveQuery the user is editing
 *
 * Revision 1.6  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.SavedQuery;
import gov.nih.nci.camod.domain.SavedQueryAttribute;
import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.service.SavedQueryManager;
import gov.nih.nci.camod.service.impl.SpeciesManagerSingleton;
import gov.nih.nci.camod.util.SafeHTMLUtil;
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

        log.info("In SimpleSearchPopulateAction.populate");
        
        // Get and clean method to prevent SQL injection
        String methodName = request.getParameter("unprotected_method");
        log.debug("methodName: " + methodName);
        methodName = SafeHTMLUtil.clean(methodName);
        log.debug("methodName: " + methodName);
        
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
                        
            SavedQueryManager savedQueryManager = (SavedQueryManager) getBean("savedQueryManager");
            
            try {                
                SavedQuery sq = savedQueryManager.get( aQueryId );
                
                log.debug("<SimpleSearchPopulateAction.populate> sq.getQueryName(): " + sq.getQueryName());

                
                request.getSession().setAttribute( Constants.QUERY_NAME, sq.getQueryName() );
                
                // Retrieve the saved criteria and populate the fields in the SearchForm
                Set<SavedQueryAttribute> sqaList = sq.getSavedQueryAttributes();                                
                savedQueryManager.buildSearchData( sqaList, theSearchForm );                
                
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
        
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.APPROVEDSPECIESDROP,
                Constants.Dropdowns.ADD_BLANK_OPTION);
    

        return mapping.findForward("next");
    }
    public ActionForward setSpeciesForOrganTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
    	String theSearchSpecies = null;    	
        SearchForm theSearchForm = (SearchForm) form;
        
        // Check if null - if user goes from species to empty this correctly redirects to screen        
        if (theSearchForm.getSpecies() !=null && theSearchForm.getSpecies().length() > 0){
            log.debug("theSearchForm.getSpecies(): "+ theSearchForm.getSpecies());
            
            // Set selected species to a constant to determine which organ tree displays 
            // using common name because Rat has two species
            Species species = SpeciesManagerSingleton.instance().getByName(theSearchForm.getSpecies());
            theSearchSpecies = species.getCommonName();
            log.debug("<setSpeciesForOrganTree> theSearchSpecies: "+ theSearchSpecies);        	
        }

        request.getSession().setAttribute(Constants.SEARCHSPECIESCOMMONNAME, theSearchSpecies);

        return mapping.findForward("next");    	
    }
    
    public ActionForward clearSpeciesForOrganTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
    	log.info( "Clearing Species for Organ Tree" );
    	
    	String theSearchSpecies = null;
    	SearchForm theSearchForm = (SearchForm) form;
    	theSearchForm.simpleSearchReset();
    	
        request.getSession().setAttribute(Constants.SEARCHSPECIESCOMMONNAME, theSearchSpecies);

        return mapping.findForward("next");    	
    }    
}
