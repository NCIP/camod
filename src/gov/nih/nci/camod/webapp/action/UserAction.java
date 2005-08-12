package gov.nih.nci.camod.webapp.action;

import java.util.List;
import java.util.Collections;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.domain.AnimalModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class UserAction extends BaseAction {
	
    /** ReturnUserModels
     *  This action returns all models related to the currently logged on user
     */ 
    public ActionForward ReturnUserModels(  ActionMapping mapping, 
    										ActionForm form,
									        HttpServletRequest request,
									        HttpServletResponse response ) 
    	{
    		if( checkIfLoggedIn( request ) )
    		{
	    		System.out.println( "<UserAction ReturnUserModels> Entering... " );
		    	
	    		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    	
		    	List amList = animalModelManager.getAnimalModels( (String) request.getSession().getAttribute("camod.loggedon.username") );	 
		    	
		    	//sort list by modelDescriptor, ignoring case
		    	Collections.sort( amList, new _sortAnimalModels() );
		    	
		    	request.getSession().setAttribute( Constants.USERMODELLIST, amList );
		    	
	    		return mapping.findForward( "usermodellist" );
    		} else {
    			return mapping.findForward( "loginrequired" );
    		}
    }
     
    //Used to check camod.loggedon.username attribute to see if a user is logged in
    //
	public boolean checkIfLoggedIn( HttpServletRequest request )
	{
		if ( request.getSession().getAttribute("camod.loggedon.username") == null ) {				
            
            ActionErrors errors = new ActionErrors();
            errors.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "error.login.required" ) );
            saveErrors( request, errors );
                         
			return false;
			
		} else 
			return true;
	} 
}

class _sortAnimalModels implements java.util.Comparator {
    
    public void SortAnimalModels() {}
       
    public int compare( Object oo1, Object oo2 ) 
    {
      AnimalModel o1 = (AnimalModel) oo1;    	
      AnimalModel o2 = (AnimalModel) oo2;
      
      if ( o1.getModelDescriptor().compareToIgnoreCase( o2.getModelDescriptor() ) > 0 )
        return 1;
      else
        if ( o1.getModelDescriptor().compareToIgnoreCase( o2.getModelDescriptor() ) < 0 )
          return -1;
        else
          return 0;
    }   
}
 