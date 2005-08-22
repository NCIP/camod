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
    	
    	System.out.println( "<SubmitAction setModelConstants> modelID" + request.getParameter( "aModelID" ) );
		
		String modelID = request.getParameter( "aModelID" );
		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    	
    	AnimalModel am = animalModelManager.getAnimalModel( modelID );	  
    	
    	request.getSession().setAttribute( Constants.MODELID, am.getId().toString() );
    	request.getSession().setAttribute( Constants.MODELDESCRIPTOR, am.getModelDescriptor() );
    	request.getSession().setAttribute( Constants.MODELSTATUS, am.getState() );
    	
    	//Add a message to be displayed in submitModles saying you've deleted a model  
        //ActionMessages msg = new ActionMessages();
        //msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "delete.successful" ) );
        //saveErrors( request, msg );
        
		return mapping.findForward( "submitOverview" );
    }
    
}
