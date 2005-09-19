package gov.nih.nci.camod.webapp.action;

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
        if (log.isDebugEnabled()) {
            log.debug("Entering 'edit' method");
        }

        return mapping.findForward("");
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
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
		// Create a form to edit
		GeneDeliveryForm geneDelivForm = ( GeneDeliveryForm ) form;
    	
		System.out.println( "<EnvironmentalFactorAction save> following Characteristics:" + 
								"\n\t ViralVector: " + geneDelivForm.getViralVector() + 
								"\n\t OtherViralVector: " + geneDelivForm.getOtherViralVector() + 
								"\n\t GeneInVirus: " + geneDelivForm.getGeneInVirus() + 
								"\n\t Regimen: " + geneDelivForm.getRegimen() +
								"\n\t ConceptCode: " + geneDelivForm.getConceptCode() +																
								"\n\t user: " + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
		
		/* Grab the current modelID from the session */
        String modelID = (String) request.getSession().getAttribute( Constants.MODELID );
        
        /* Create all the manager objects needed for Screen */
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
        TreatmentManager treatmentManager = ( TreatmentManager ) getBean( "treatmentManager" );
        GeneDeliveryManager geneDeliveryManager = (GeneDeliveryManager) getBean("geneDeliveryManager");        
        
        /* Set modelID in AnimalModel object */
        AnimalModel animalModel = animalModelManager.get( modelID );        
 
 		/*1. Create Treatment object, set its regime, and save Treatment object.	*/	
		Treatment treatment = new Treatment();
		treatment.setRegimen(geneDelivForm.getRegimen());
		
		//save treatment object		
		treatmentManager.save(treatment);
		
		/* 2. Create a GeneDelivery Object and set attributes from GUI, save GeneDelivery object */
		GeneDelivery geneDelivery = new GeneDelivery();
		geneDelivery.setViralVector(geneDelivForm.getViralVector());
		geneDelivery.setGeneInVirus(geneDelivForm.getGeneInVirus());
		geneDeliveryManager.save(geneDelivery);
        
        /*4. Create Therapy object, set its therapeuticExperiment property to false.
    		4.1 set its treatment property (saved in #2).
    		4.3 Add Therapy to animalModel 
    		4.4 No need to explicity save Therapy object b/c 1...1 relationship with AnimalModel   	
    		When TherapeuticExperiment property is false, tells us that this is an environmentalFactor
    	*/
        Therapy therapy = new Therapy();
        therapy.setTherapeuticExperiment( new Boolean(false) );    
        therapy.setTreatment( treatment ); 
        
        /* 5. Add Therapy to AnimalModel */
        animalModel.addTherapy( therapy );

		/* 6. save the animalModel = saves Therapy (Hibernate saves child in 1...1 relationships)  */  
        animalModelManager.save( animalModel );
        
        /* TODO:  Implement conceptCode with Tree 
		TO DO: convert this to a button to retrieve the evs# from the tree  
		The evs# is inserted into Organ.conceptCode, then the OrganID is inserted   
		into as the GeneDelivery.geneDeliveryID value   */
	
    	//Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "genedelivery.creation.successful" ) );
        saveErrors( request, msg );
     
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}