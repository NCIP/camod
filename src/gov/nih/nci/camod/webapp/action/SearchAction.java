/**
 * 
 * $Id: SearchAction.java,v 1.4 2006-04-28 19:28:10 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/11/16 19:43:30  georgeda
 * Added clear to search forms
 *
 * Revision 1.2  2005/10/07 21:14:24  georgeda
 * Changed the forward action
 *
 * Revision 1.1  2005/10/03 13:52:04  georgeda
 * Search changes
 *
 * Revision 1.2  2005/09/16 15:52:56  georgeda
 * Changes due to manager re-write
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.SavedQuery;
import gov.nih.nci.camod.domain.SavedQueryAttribute;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.PersonManager;
import gov.nih.nci.camod.service.QueryStorageManager;
import gov.nih.nci.camod.webapp.form.SearchForm;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * Action used to implement the search for animal models
 * 
 */
public final class SearchAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException {
   
        SearchForm theForm = new SearchForm();
        SavedQuery resubmittedSavedQuery = null;
        
        QueryStorageManager queryStorageManager = (QueryStorageManager) getBean("queryStorageManager");
        
        String aQueryId = request.getParameter( "aQueryId" );       
        String aSavedQueryId = (String) request.getSession().getAttribute( Constants.ASAVEDQUERYID );
        
        //Retrieve the query to resubmit
        if ( aSavedQueryId != null || aQueryId != null )
        {            
            try {      
                
                if ( aQueryId != null ) {                    
                    //Used for re-running query
                    resubmittedSavedQuery = queryStorageManager.get( aQueryId );
                    
                    // From the saved criteria populate the fields in the simpleSearchForm
                    Set<SavedQueryAttribute> sqaList = resubmittedSavedQuery.getSavedQueryAttributes();
                    
                    // Retrieve the saved criteria and populate the fields in the SearchForm
                    theForm = queryStorageManager.buildSearchData( sqaList, theForm );       
                    
                    request.getSession().setAttribute( Constants.RERUN_QUERY, "yes" );
                    
                } else {                    
                    //Used for editing query
                    resubmittedSavedQuery = queryStorageManager.get( aSavedQueryId );
                    theForm = (SearchForm) form;
                    
                    request.getSession().setAttribute( Constants.RERUN_QUERY, null );
                }
                
            } catch ( Exception e) {                
                // Set the error message
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
                saveErrors(request, msg);
            }
        } else {
            //Normal Search
            theForm = (SearchForm) form;
            
            request.getSession().setAttribute( Constants.QUERY_NAME, null );
            request.getSession().setAttribute( Constants.ASAVEDQUERYID, null );
            request.getSession().setAttribute( Constants.RERUN_QUERY, null );
        }
        
        if( theForm.getKeyword() != null )
        {
            log.debug("Keyword: " + theForm.getKeyword());       
            request.getSession().setAttribute(Constants.KEYWORD_HIGHLIGHT, theForm.getKeyword() ); 
        }
        
        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);
        String theForward = "next";

        // Clear the form
        if ("Clear".equals(theAction)) {
            theForm.allFieldsReset();
            theForward = "back";
        }

        // Do the search
        else {
            
            try {
                AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
                
                // Calculate the elasped time of search
                long start = System.currentTimeMillis();     
                
                // Perform the search
                List results = animalModelManager.search(theForm);                
                
                long elapsedTimeMillis = System.currentTimeMillis()-start;
                
                // Set search results constant
                request.getSession().setAttribute( Constants.SEARCH_RESULTS, results );
                                
                // Create new QUERY HISTORY item
                SavedQuery savedQuery = new SavedQuery();                
                savedQuery.setSearchData( theForm );
                savedQuery.setElapsedTime( elapsedTimeMillis );
                savedQuery.setExecuteTime( new Date() );
                savedQuery.setIsSaved( new Long(0) );                
                savedQuery.setNumberResults( results.size() );                
                savedQuery.setQueryName( "No Name Provided" );  

                if ( aSavedQueryId != null || aQueryId != null ) {
                    savedQuery.setQueryName( resubmittedSavedQuery.getQueryName() );
                    
                    // Set Global Constants for use later
                    request.getSession().setAttribute( Constants.QUERY_NAME, resubmittedSavedQuery.getQueryName() );
                    request.getSession().setAttribute( Constants.ASAVEDQUERYID, resubmittedSavedQuery.getId().toString() );                    
                }
                
                request.getSession().setAttribute( Constants.ELAPSED_TIME, savedQuery.getElapsedTime() );
                request.getSession().setAttribute( Constants.EXECUTE_TIME, savedQuery.getExecuteTime() );
                
                // If a user is logged in, save the query to the history
                String currentUser = (String) request.getSession().getAttribute(Constants.CURRENTUSER);
                
                if ( currentUser != null )
                {
                    //Set the user for this save
                    PersonManager personManager = (PersonManager) getBean("personManager");                
                    savedQuery.setUser( personManager.getByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) ) ); 
                    
                    // Add criteria to be saved. Create a List of SavedQueryAttributes from inQuery.getSearchData()
                    Set <SavedQueryAttribute>criteriaList = queryStorageManager.buildCriteriaList( theForm );
                    savedQuery.setSavedQueryAttributes( criteriaList );                                    
                    
                    //Save search to query history 
                    queryStorageManager.saveQuery( savedQuery );
                                
                }
                                
                //Save the current form data into constants                
                request.getSession().setAttribute(Constants.SEARCH_FORM, theForm);
                
            } catch (Exception e) {

                // Set the error message
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
                saveErrors(request, msg);
            }
        }

        return mapping.findForward(theForward);
    }
}