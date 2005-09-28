package gov.nih.nci.camod.webapp.action;

import java.util.List;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.*;
import gov.nih.nci.camod.webapp.form.GeneDeliveryForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * GeneDeliveryAction Class
 */
public final class GeneDeliveryAction extends BaseAction {
	
    /**
     * Delete
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        return mapping.findForward("");
    }

	/**
	 * Cancel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward duplicate(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
    	
    	 return mapping.findForward("");
    }    
    
    /**
     * Edit
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        
    	log.trace( "Entering edit" );

    	// Create a form to edit
		GeneDeliveryForm geneDeliveryForm = ( GeneDeliveryForm ) form;
    
		// Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute( Constants.MODELID );
        String aTherapyID = request.getParameter( "aTherapyID" );
        
		log.info( "<EnvironmentalFactorAction save> following Characteristics:" + 
				"\n\t ViralVector: "      + geneDeliveryForm.getViralVector() + 
				"\n\t OtherViralVector: " + geneDeliveryForm.getOtherViralVector() + 
				"\n\t GeneInVirus: "      + geneDeliveryForm.getGeneInVirus() + 
				"\n\t Regimen: "          + geneDeliveryForm.getRegimen() +
				"\n\t ConceptCode: "      + geneDeliveryForm.getOrgan() +
				"\n\t organTissueCode: "  + geneDeliveryForm.getOrganTissueCode() +
				"\n\t organTissueName: "  + geneDeliveryForm.getOrganTissueName() +
				"\n\t user: "             + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );

		String theForward = "AnimalModelTreePopulateAction";
		
	    try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
                        
    		//List xenograftList = theAnimalModel.getXenograftCollection();
    		//GeneDeliveryManager geneDeliveryManager = (GeneDeliveryManager) getBean( "geneDeliveryManager" );        
    		
    		List geneDeliveryList = theAnimalModel.getGeneDeliveryCollection();
    		
    		GeneDelivery gene = new GeneDelivery();
    		
            for (int i = 0; i < geneDeliveryList.size(); i++) {        	
            	gene = (GeneDelivery) geneDeliveryList.get(i);                                              
    			if ( gene.getId().toString().equals( aTherapyID) )
    				break;
            }   
            
            //geneDeliveryManager.update( geneDeliveryForm , gene, theAnimalModel );
            theAnimalModelManager.saveGeneDelivery( geneDeliveryForm, gene, theAnimalModel ); 
            
            // Setup global constants to use for submission / editing process
            //request.getSession().setAttribute(Constants.MODELID, theAnimalModel.getId().toString());
            //request.getSession().setAttribute(Constants.MODELDESCRIPTOR, theAnimalModel.getModelDescriptor());
            //request.getSession().setAttribute(Constants.MODELSTATUS, theAnimalModel.getState());

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genedelivery.edit.successful"));
            saveErrors(request, msg);
            
	    } catch( Exception e ) {
            log.error("Exception ocurred creating Xenograft", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);

            theForward = "failure";
	    }
	    
        log.trace("Exiting edit");
        return mapping.findForward(theForward);    	
    }

    /**
     * Save
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save( ActionMapping mapping, 
    						   ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response )
    throws Exception {
    	
    	log.trace( "Entering save" );        
        
		// Create a form to edit
		GeneDeliveryForm geneDeliveryForm = ( GeneDeliveryForm ) form;
    	
		// Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
        
        log.info( "<EnvironmentalFactorAction save> following Characteristics:" + 
						"\n\t ViralVector: "      + geneDeliveryForm.getViralVector() + 
						"\n\t OtherViralVector: " + geneDeliveryForm.getOtherViralVector() + 
						"\n\t GeneInVirus: "      + geneDeliveryForm.getGeneInVirus() + 
						"\n\t Regimen: "          + geneDeliveryForm.getRegimen() +
						"\n\t ConceptCode: "      + geneDeliveryForm.getOrgan() +
						"\n\t organTissueCode: "  + geneDeliveryForm.getOrganTissueCode() +
						"\n\t organTissueName: "  + geneDeliveryForm.getOrganTissueName() +
						"\n\t user: "             + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
		
		String theForward = "AnimalModelTreePopulateAction";
		
	    try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            theAnimalModelManager.saveGeneDelivery( geneDeliveryForm, null, theAnimalModel );           

            log.info("New GeneDelivery created" );

            // Setup global constants to use for submission / editing process
            //request.getSession().setAttribute(Constants.MODELID, theAnimalModel.getId().toString());
            //request.getSession().setAttribute(Constants.MODELDESCRIPTOR, theAnimalModel.getModelDescriptor());
            //request.getSession().setAttribute(Constants.MODELSTATUS, theAnimalModel.getState());

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("genedelivery.creation.successful"));
            saveErrors(request, msg);
            
	    } catch( Exception e ) {
            log.error("Exception ocurred creating Xenograft", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);

            theForward = "failure";
	    }
	    
        log.trace("Exiting save");
        return mapping.findForward(theForward);    
    }
}