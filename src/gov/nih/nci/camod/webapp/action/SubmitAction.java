package gov.nih.nci.camod.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import gov.nih.nci.camod.Constants;

import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.domain.AnimalModel;

public class SubmitAction extends BaseAction{

	/** called from SubmitModels.jsp from list of models links 
	 * 
	 */
    public ActionForward setModelConstants( ActionMapping mapping, 
		    							    ActionForm form,
									        HttpServletRequest request,
									        HttpServletResponse response)
    throws Exception {
    	
    	System.out.println( "<SubmitAction setModelConstants> modelID" + request.getParameter( "modelID" ) );
		
    	if( checkIfLoggedIn( request ) )
		{
    		String modelID = request.getParameter( "modelID" );
    		
    		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    	
	    	AnimalModel am = animalModelManager.getAnimalModel( modelID );	  
	    	
	    	request.getSession().setAttribute( Constants.MODELID, am.getId() );
	    	request.getSession().setAttribute( Constants.MODELDESCRIPTOR, am.getModelDescriptor() );
	    	request.getSession().setAttribute( Constants.MODELSTATUS, am.getState() );
	    	
	    	//Add a message to be displayed in submitModles saying you've deleted a model  
	        //ActionMessages msg = new ActionMessages();
	        //msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "delete.successful" ) );
	        //saveErrors( request, msg );
	        
    		return mapping.findForward( "success" );
		} else {
			return mapping.findForward( "loginrequired" );
		}
    }
    
    
    
	/* Delete a AnimalModel based on it's id 
	 * 
	 
    public ActionForward deleteModel( ActionMapping mapping, 
									  ActionForm form,
							          HttpServletRequest request,
							          HttpServletResponse response)
	throws Exception {
		
		System.out.println( "<SubmitAction DeleteModel> modelID" + request.getParameter( "modelID" ) );
		
		if( checkIfLoggedIn( request ) )
		{
			String modelID = request.getParameter( "modelID" );
			
			AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
			animalModelManager.removeAnimalModel( modelID );
			
	    	//Add a message to be displayed in submitModles saying you've deleted a model  
	        ActionMessages msg = new ActionMessages();
	        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "delete.successful" ) );
	        saveErrors( request, msg );
			
			return mapping.findForward( "modeldeleted" );
		} else {
			return mapping.findForward( "loginrequired" );
		}	
    }
    */
    
	// Retrieve a AnimalModel based on it's id
    // This function is used to pre-populate forms
	// Save this model in the constants as ANIMALMODEL
    /*
    public ActionForward RetrieveAnimalModel( ActionMapping mapping, 
									  		  ActionForm form,
							                  HttpServletRequest request,
							                  HttpServletResponse response)
	throws Exception {
		
		System.out.println( "<SubmitAction RetrieveAnimalModel> modelID=" + request.getSession().getAttribute( Constants.MODELID ) );
		String formPage = request.getParameter( "formPage" );
		System.out.println( "<SubmitAction RetrieveAnimalModel> formPage=" + formPage );
		
		if( checkIfLoggedIn( request ) )
		{								
			String modelID = "" + request.getSession().getAttribute( Constants.MODELID );
			
			AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
			AnimalModel am = animalModelManager.getAnimalModel( modelID );	
			request.getSession().setAttribute( Constants.ANIMALMODEL, am );
			
			//for now, just goto submitModelCharacteristics
			//TODO: figure out howto pass parameter formPage=submitModelCharacteristiccs and forward the current page for redirection
			
			return mapping.findForward( formPage );
		} else {
			return mapping.findForward( "loginrequired" );
		}	
    }
    */
    
    /** Used to check camod.loggedon.username attribute to see if any user is logged in
     * 
     */
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
