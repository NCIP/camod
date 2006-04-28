/**
 * 
 * $Id: SaveQueryAction.java,v 1.1 2006-04-28 19:26:58 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.SavedQuery;
import gov.nih.nci.camod.domain.SavedQueryAttribute;
import gov.nih.nci.camod.service.PersonManager;
import gov.nih.nci.camod.service.QueryStorageManager;
import gov.nih.nci.camod.webapp.form.SaveQueryForm;
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

public class SaveQueryAction extends BaseAction
{

    public ActionForward save(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException {
      
            System.out.println( "SaveQueryAction save entering..." );
            
            SaveQueryForm theForm = (SaveQueryForm) form;                       
            
            QueryStorageManager queryStorageManager = (QueryStorageManager) getBean("queryStorageManager");
            List results = (List) request.getSession().getAttribute( Constants.SEARCH_RESULTS );
                        
            String theForward = "noSaveOption";
            
             try {
                 
                 if( theForm.getSaveAsNew().equals( "no" ) ) {
                     
                     // UPDATE Saved Query
                     System.out.println( "Updating the query!" );
                     request.getSession().setAttribute( Constants.DUP_NAME, "false" );
                     
                     String aSavedQueryId = (String) request.getSession().getAttribute( Constants.ASAVEDQUERYID );                                          
                     SavedQuery theQueryToUpdate = queryStorageManager.get( aSavedQueryId );
                     
                     theQueryToUpdate.setElapsedTime( (Long) request.getSession().getAttribute( Constants.ELAPSED_TIME ) );
                     theQueryToUpdate.setExecuteTime( (Date) request.getSession().getAttribute( Constants.EXECUTE_TIME ) );       
                     theQueryToUpdate.setNumberResults( results.size() );
                     
                     SearchForm aSearchForm = (SearchForm) request.getSession().getAttribute( Constants.SEARCH_FORM );
                     Set <SavedQueryAttribute> criteriaList = theQueryToUpdate.getSavedQueryAttributes();                         
                     criteriaList.clear();                    
                     criteriaList.addAll( queryStorageManager.buildCriteriaList( aSearchForm ) );                 
                     
                     queryStorageManager.update( theQueryToUpdate );
                               
                 }
                 else if ( theForm.getSaveAsNew().equals( "yes" )) {
                     
                     System.out.println( "Saving a new query!" );
                     
                     // Create NEW Saved Query               
                     SavedQuery theQueryToSave = new SavedQuery();
                     
                     boolean duplicateName = false;
                     request.getSession().setAttribute( Constants.DUP_NAME, "false" );
                     
                     List allSaved = queryStorageManager.getSavedQueriesByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) );
                     for( int i=0; i < allSaved.size(); i++)
                     {
                         SavedQuery sq = (SavedQuery) allSaved.get( i );                         
                         if( theForm.getQueryName().equals( sq.getQueryName() )){
                             System.out.println( "Query name is a DUPLICATE");
                             duplicateName = true;
                             request.getSession().setAttribute( Constants.DUP_NAME, "true" );
                             theForward = "next"; 
                         }
                     }
                     
                     if ( duplicateName == false )
                     {
                         theQueryToSave.setQueryName( theForm.getQueryName() );
                         theQueryToSave.setIsSaved( 1l );
                         
                         theQueryToSave.setElapsedTime( (Long) request.getSession().getAttribute( Constants.ELAPSED_TIME ) );                     
                         theQueryToSave.setExecuteTime( (Date) request.getSession().getAttribute( Constants.EXECUTE_TIME ) );
                         theQueryToSave.setNumberResults( results.size() );
                         
                         // Add list of criteria
                         Set <SavedQueryAttribute> criteriaList = queryStorageManager.buildCriteriaList( (SearchForm) request.getSession().getAttribute( Constants.SEARCH_FORM ) );                         
                         theQueryToSave.setSavedQueryAttributes( criteriaList );
                         
                         //Set the user for this save
                         PersonManager personManager = (PersonManager) getBean("personManager");                
                         theQueryToSave.setUser( personManager.getByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) ) ); 
                                          
                         queryStorageManager.saveQuery( theQueryToSave );                     
    
                         // Used for sidebar, update the number of saved queries for current user
                         List savedQueriesList = queryStorageManager.getSavedQueriesByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) );
                         request.getSession().setAttribute( Constants.NUMBEROFSAVEDQUERIES, String.valueOf(savedQueriesList.size() ) );
                     }    
                     
                     request.getSession().setAttribute( Constants.QUERY_NAME, theForm.getQueryName() );                                     
                }
                else { }
                 
             } catch (Exception e) {
        
                 // Set the error message
                 ActionMessages msg = new ActionMessages();
                 msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
                     saveErrors(request, msg);
             }
        
             return mapping.findForward(theForward);
     }    
    
    public ActionForward returnUserQueries(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException {
      
            System.out.println( "SaveQueryAction returnUserQueries entering..." );
             
            String theForward = "searchQueryHistory";
            
             try {
                 QueryStorageManager queryStorageManager = (QueryStorageManager) getBean("queryStorageManager");                 
                 List savedQueriesList = queryStorageManager.getAllByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) );
                 request.getSession().setAttribute(Constants.USERSQUERYLIST, savedQueriesList);
                 
             } catch (Exception e) {
        
                 // Set the error message
                 ActionMessages msg = new ActionMessages();
                 msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
                     saveErrors(request, msg);
             }
        
             return mapping.findForward(theForward);
     }  
    
    public ActionForward returnSavedUserQueries(ActionMapping mapping, ActionForm form, 
                                           HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
    
          System.out.println( "SaveQueryAction returnSavedUserQueries entering..." );
           
          String theForward = "searchSavedQueries";
          
           try {
               QueryStorageManager queryStorageManager = (QueryStorageManager) getBean("queryStorageManager");
               List savedQueriesList = queryStorageManager.getSavedQueriesByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) );
               
               request.getSession().setAttribute(Constants.USERSAVEDQUERYLIST, savedQueriesList);
               
           } catch (Exception e) {
      
               // Set the error message
               ActionMessages msg = new ActionMessages();
               msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
                   saveErrors(request, msg);
           }
      
           return mapping.findForward(theForward);
   }  
    
    public ActionForward delete(ActionMapping mapping, ActionForm form, 
                                                HttpServletRequest request, HttpServletResponse response) 
           throws IOException, ServletException {
         
               System.out.println( "SaveQueryAction delete entering..." );
                
               String theForward = "querydeleted";
               
               QueryStorageManager queryStorageManager = (QueryStorageManager) getBean("queryStorageManager");
               
               String aQueryId = request.getParameter("aQueryId");  
               
                try {                    
                        //Delete from db
                        queryStorageManager.remove( aQueryId );
                    
                        // Used for sizebar, update the number of saved queries for current user
                        List savedQueriesList = queryStorageManager.getSavedQueriesByUsername( (String) request.getSession().getAttribute(Constants.CURRENTUSER) );
                        request.getSession().setAttribute(Constants.NUMBEROFSAVEDQUERIES, String.valueOf(savedQueriesList.size()) );
                        
                       //Add deletion successful message
                        ActionMessages msg = new ActionMessages();
                        msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("delete.successful"));
                            saveErrors(request, msg);
                        
                } catch (Exception e) {
           
                    // Set the error message
                    ActionMessages msg = new ActionMessages();
                    msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
                        saveErrors(request, msg);
                }
           
                return mapping.findForward(theForward);
        }      
}