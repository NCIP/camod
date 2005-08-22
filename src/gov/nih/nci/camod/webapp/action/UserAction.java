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
    		System.out.println( "<UserAction ReturnUserModels> Entering... " );
	    	
    		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    	
	    	List amList = animalModelManager.getAll( (String) request.getSession().getAttribute("camod.loggedon.username") );	 
	    	
	    	//sort list by modelDescriptor, ignoring case
	    	Collections.sort( amList, new _sortAnimalModels() );
	    	
	    	request.getSession().setAttribute( Constants.USERMODELLIST, amList );
	    	
    		return mapping.findForward( "submitModels" );
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
 