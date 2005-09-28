package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.XenograftManager;
import gov.nih.nci.camod.webapp.form.XenograftForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * XenograftAction Class
 */
public final class XenograftAction extends BaseAction {
	
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
		XenograftForm xenograftForm = ( XenograftForm ) form;
        
		// Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute( Constants.MODELID );
        String aXenograftID = request.getParameter( "aXenograftID" );
        
		log.info( "<XenograftAction edit> following Characteristics:" + 
							"\n\t name: "      + xenograftForm.getName() + 
							"\n\t ATTCNumber: " + xenograftForm.getATCCNumber() + 
							"\n\t ParentalCellLineName: "      + xenograftForm.getParentalCellLineName() + 
							"\n\t getAgeAtTreatment: "          + xenograftForm.getAgeAtTreatment() +
							"\n\t getAgeUnit: "      + xenograftForm.getAgeUnit() +
							"\n\t getCellAmount: "  + xenograftForm.getCellAmount() +
							"\n\t getHarvestDate: "  + xenograftForm.getHarvestDate() +
							"\n\t getModificationDescription: "  + xenograftForm.getModificationDescription() +
							"\n\t getGeneticManipulation: "  + xenograftForm.getGeneticManipulation() +
							"\n\t getAdministrativeSite: "  + xenograftForm.getAdministrativeSite() +
							"\n\t getGraftType: "  + xenograftForm.getGraftType() +
							"\n\t getOtherGraftType: "  + xenograftForm.getOtherGraftType() +
							"\n\t getHostScientificName: "  + xenograftForm.getHostScientificName() +
							"\n\t getHostEthinicityStrain: "  + xenograftForm.getHostEthinicityStrain() +
							"\n\t getOtherHostEthinicityStrain: "  + xenograftForm.getOtherHostEthinicityStrain() +
							"\n\t user: "             + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
	    
		String theForward = "AnimalModelTreePopulateAction";
		
	    try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
                        
    		List xenograftList = theAnimalModel.getXenograftCollection();
    		Xenograft theXenograft = new Xenograft();
    		
            for (int i = 0; i < xenograftList.size(); i++) {        	
            	theXenograft  = (Xenograft) xenograftList.get(i);                                              
    			if ( theXenograft.getId().toString().equals( aXenograftID) )
    				break;
            }	
            
            XenograftManager xenograftManager = ( XenograftManager ) getBean( "xenograftManager");
            xenograftManager.update( xenograftForm, theXenograft, theAnimalModel );
            
            theAnimalModelManager.saveXenograft( xenograftForm, theXenograft, theAnimalModel  );           
            
            // Setup global constants to use for submission / editing process
            //request.getSession().setAttribute(Constants.MODELID, theAnimalModel.getId().toString());
            //request.getSession().setAttribute(Constants.MODELDESCRIPTOR, theAnimalModel.getModelDescriptor());
            //request.getSession().setAttribute(Constants.MODELSTATUS, theAnimalModel.getState());

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("xenograft.edit.successful"));
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
		XenograftForm xenograftForm = ( XenograftForm ) form;
        
		// Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
        
		log.info( "<XenograftAction save> following Characteristics:" + 
							"\n\t name: "      + xenograftForm.getName() + 
							"\n\t ATTCNumber: " + xenograftForm.getATCCNumber() + 
							"\n\t ParentalCellLineName: "      + xenograftForm.getParentalCellLineName() + 
							"\n\t getAgeAtTreatment: "          + xenograftForm.getAgeAtTreatment() +
							"\n\t getAgeUnit: "      + xenograftForm.getAgeUnit() +
							"\n\t getCellAmount: "  + xenograftForm.getCellAmount() +
							"\n\t getHarvestDate: "  + xenograftForm.getHarvestDate() +
							"\n\t getModificationDescription: "  + xenograftForm.getModificationDescription() +
							"\n\t getGeneticManipulation: "  + xenograftForm.getGeneticManipulation() +
							"\n\t getAdministrativeSite: "  + xenograftForm.getAdministrativeSite() +
							"\n\t getGraftType: "  + xenograftForm.getGraftType() +
							"\n\t getOtherGraftType: "  + xenograftForm.getOtherGraftType() +
							"\n\t getHostScientificName: "  + xenograftForm.getHostScientificName() +
							"\n\t getHostEthinicityStrain: "  + xenograftForm.getHostEthinicityStrain() +
							"\n\t getOtherHostEthinicityStrain: "  + xenograftForm.getOtherHostEthinicityStrain() +
							"\n\t user: "             + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
	    
		String theForward = "AnimalModelTreePopulateAction";
	    try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            theAnimalModelManager.saveXenograft( xenograftForm, null, theAnimalModel );           

            log.info("New Xenograft created" );

            // Setup global constants to use for submission / editing process
            //request.getSession().setAttribute(Constants.MODELID, theAnimalModel.getId().toString());
            //request.getSession().setAttribute(Constants.MODELDESCRIPTOR, theAnimalModel.getModelDescriptor());
            //request.getSession().setAttribute(Constants.MODELSTATUS, theAnimalModel.getState());

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("xenograft.creation.successful"));
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
    
    public ActionForward SetStrainDropdown(ActionMapping mapping, ActionForm form,
								             HttpServletRequest request,
								             HttpServletResponse response)
	throws Exception {    	 
		 
		String speciesName = request.getParameter( "speciesName" );
		
		System.out.println( "<SetStrainDropdown execute> speciesName: " + speciesName );
		
		NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.STRAINDROP, speciesName );

		XenograftForm xenograftForm = ( XenograftForm ) form;
		request.getSession().setAttribute( Constants.FORMDATA, xenograftForm );
		return mapping.findForward( "submitTransplantXenograft" );	        	     
	}
}