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
        if (log.isDebugEnabled()) {
            log.debug("Entering 'edit' method");
        }
		
		// Create a form to edit
		GeneDeliveryForm geneDeliveryForm = ( GeneDeliveryForm ) form;

		System.out.println( "<EnvironmentalFactorAction save> following Characteristics:" + 
				"\n\t ViralVector: "      + geneDeliveryForm.getViralVector() + 
				"\n\t OtherViralVector: " + geneDeliveryForm.getOtherViralVector() + 
				"\n\t GeneInVirus: "      + geneDeliveryForm.getGeneInVirus() + 
				"\n\t Regimen: "          + geneDeliveryForm.getRegimen() +
				"\n\t ConceptCode: "      + geneDeliveryForm.getOrgan() +
				"\n\t organTissueCode: "  + geneDeliveryForm.getOrganTissueCode() +
				"\n\t organTissueName: "  + geneDeliveryForm.getOrganTissueName() +
				"\n\t user: "             + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );

    	// Grab the current Therapy we are working with related to this animalModel
    	String aTherapyID = request.getParameter( "aTherapyID" );		
		
		// Grab the current modelID from the session 
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );		
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );	    		    			
		AnimalModel am = animalModelManager.get( modelID );

		/* Create all the manager objects needed for Screen */
		TreatmentManager treatmentManager = ( TreatmentManager ) getBean( "treatmentManager" );
		GeneDeliveryManager geneDeliveryManager = (GeneDeliveryManager) getBean( "geneDeliveryManager" );        
		OrganManager organManager = (OrganManager) getBean( "organManager" );
		
		List geneDeliveryList = am.getGeneDeliveryCollection();
		GeneDelivery gene = new GeneDelivery();
		
        for (int i = 0; i < geneDeliveryList.size(); i++) {        	
        	gene = (GeneDelivery) geneDeliveryList.get(i);                                              
			if ( gene.getId().toString().equals( aTherapyID) )
				break;
        }       

 		/*1. Create Treatment object, set its regimen, and save Treatment object.	*/	
		Treatment treatment = gene.getTreatment();
		treatment.setRegimen( geneDeliveryForm.getRegimen() );
		
		//save treatment object		
		treatmentManager.save( treatment );
		
		/* 2. Create a GeneDelivery Object and set attributes from GUI, save GeneDelivery object */
		GeneDelivery geneDelivery = gene;
		geneDelivery.setViralVector( geneDeliveryForm.getViralVector() );
		geneDelivery.setGeneInVirus( geneDeliveryForm.getGeneInVirus() );
		geneDelivery.setTreatment( treatment );
		
        /* Add a Organ to AnimalModel with correct IDs, conceptCode (i.e. Location of Delivery) */
        System.out.println( "Saving: getOrgan=" + geneDeliveryForm.getOrgan() + " Not Saving organTissueName=" + geneDeliveryForm.getOrganTissueName() );
        
        Organ organ = gene.getOrgan();
        organ.setName( geneDeliveryForm.getOrganTissueName() );                      
        organ.setConceptCode( geneDeliveryForm.getOrganTissueCode() );      
        organManager.save( organ );
		
        geneDelivery.setOrgan( organ );
		geneDeliveryManager.save( geneDelivery );                
              
    	//Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "genedelivery.edit.successful" ) );
        saveErrors( request, msg );
     
        return mapping.findForward("AnimalModelTreePopulateAction");
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
    	
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }
        
		// Create a form to edit
		GeneDeliveryForm geneDeliveryForm = ( GeneDeliveryForm ) form;
    	
       // String organTissueCode = request.getParameter( "organTissueCode" );        
       // String organTissueName = request.getParameter( "organTissueName" );  
		
		System.out.println( "<EnvironmentalFactorAction save> following Characteristics:" + 
							"\n\t ViralVector: "      + geneDeliveryForm.getViralVector() + 
							"\n\t OtherViralVector: " + geneDeliveryForm.getOtherViralVector() + 
							"\n\t GeneInVirus: "      + geneDeliveryForm.getGeneInVirus() + 
							"\n\t Regimen: "          + geneDeliveryForm.getRegimen() +
							"\n\t ConceptCode: "      + geneDeliveryForm.getOrgan() +
							"\n\t organTissueCode: "  + geneDeliveryForm.getOrganTissueCode() +
							"\n\t organTissueName: "  + geneDeliveryForm.getOrganTissueName() +
							"\n\t user: "             + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
		
		/* Grab the current modelID from the session */
        String modelID = (String) request.getSession().getAttribute( Constants.MODELID );
        
        /* Create all the manager objects needed for Screen */
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
        TreatmentManager treatmentManager = ( TreatmentManager ) getBean( "treatmentManager" );
        GeneDeliveryManager geneDeliveryManager = (GeneDeliveryManager) getBean( "geneDeliveryManager" );        
        OrganManager organManager = (OrganManager) getBean( "organManager" );
        TherapyManager therapyManager = ( TherapyManager ) getBean( "therapyManager" );
        
        /* Set modelID in AnimalModel object */
        AnimalModel animalModel = animalModelManager.get( modelID );        
 
 		/*1. Create Treatment object, set its regimen, and save Treatment object.	*/	
		Treatment treatment = new Treatment();
		treatment.setRegimen( geneDeliveryForm.getRegimen() );
		
		//save treatment object		
		treatmentManager.save( treatment );
		
		/* 2. Create a GeneDelivery Object and set attributes from GUI, save GeneDelivery object */
		GeneDelivery geneDelivery = new GeneDelivery();
		geneDelivery.setViralVector( geneDeliveryForm.getViralVector() );
		geneDelivery.setGeneInVirus( geneDeliveryForm.getGeneInVirus() );
		geneDelivery.setTreatment( treatment );
		
        /* Add a Organ to AnimalModel with correct IDs, conceptCode (i.e. Location of Delivery) */
        System.out.println( "Saving: getOrgan=" + geneDeliveryForm.getOrgan() + " Not Saving organTissueName=" + geneDeliveryForm.getOrganTissueName() );
        
        Organ organ = new Organ();
        organ.setName( geneDeliveryForm.getOrganTissueName() );                      
        organ.setConceptCode( geneDeliveryForm.getOrganTissueCode() );      
        organManager.save( organ );
		
        geneDelivery.setOrgan( organ );
		geneDeliveryManager.save( geneDelivery );
                          
        /*  4. Create Therapy object, set its therapeuticExperiment property to false.
    	 *	4.1 set its treatment property (saved in #2).
    	 *	4.3 Add Therapy to animalModel 
    	 *	4.4 No need to explicity save Therapy object b/c 1...1 relationship with AnimalModel   	    		
    	 *	When TherapeuticExperiment property is false, tells us that this is an environmentalFactor
    	 */
        Therapy therapy = new Therapy();
        therapy.setTherapeuticExperiment( new Boolean( false ) ); 
        therapyManager.save( therapy );
        
        /* 5. Add Therapy to AnimalModel */
        animalModel.addTherapy( therapy );
        animalModel.addGeneDelivery( geneDelivery );
                
		/* 6. save the animalModel = saves Therapy (Hibernate saves child in 1...1 relationships)  */  
        animalModelManager.save( animalModel );
              
    	//Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "genedelivery.creation.successful" ) );
        saveErrors( request, msg );
     
        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}