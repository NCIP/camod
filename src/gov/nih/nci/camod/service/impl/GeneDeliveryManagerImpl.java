/**
 * @author schroedln
 * 
 * $Id: GeneDeliveryManagerImpl.java,v 1.3 2005-09-28 15:12:27 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.domain.Treatment;

import gov.nih.nci.camod.service.GeneDeliveryManager;
import gov.nih.nci.camod.webapp.form.GeneDeliveryForm;

import java.util.List;

public class GeneDeliveryManagerImpl extends BaseManager implements GeneDeliveryManager {
	
	public List getAll() throws Exception {
		log.trace("In GeneDeliveryManagerImpl.getAll");
		return super.getAll(GeneDelivery.class);
	}

	public GeneDelivery get(String id) throws Exception {
		log.trace("In GeneDeliveryManagerImpl.get");
		return (GeneDelivery) super.get(id, GeneDelivery.class);
	}

	public void save(GeneDelivery geneDelivery) throws Exception {
		log.trace("In GeneDeliveryManagerImpl.save");
		super.save(geneDelivery);    	     
	}

	public void remove(String id) throws Exception {		
		log.trace("In GeneDeliveryManagerImpl.remove");		
		super.remove(id, GeneDelivery.class);
	}

	public GeneDelivery create(GeneDeliveryForm inGeneDeliveryForm, String inUsername, AnimalModel inAnimalModel ) 
		throws Exception {
	
		log.trace( "Entering GeneDeliveryManagerImpl.create" );
	
		GeneDelivery theGeneDelivery = new GeneDelivery();
		
		log.trace( "Exiting GeneDeliveryManagerImpl.create" );
		return populateGeneDelivery(inGeneDeliveryForm, inUsername, theGeneDelivery, inAnimalModel );
	}

	public void update(GeneDeliveryForm inGeneDeliveryForm, GeneDelivery theGeneDelivery, AnimalModel inAnimalModel)
		throws Exception {

		log.trace( "Entering GeneDeliveryManagerImpl.update" );
		log.debug( "Updating GeneDeliveryForm: " + theGeneDelivery.getId() );
	
		// Populate w/ the new values and save
		theGeneDelivery = populateGeneDelivery(inGeneDeliveryForm, null, theGeneDelivery, inAnimalModel);
		
		save(theGeneDelivery);
	
		log.trace( "Exiting GeneDeliveryManagerImpl.update" );
	}
	
	private GeneDelivery populateGeneDelivery( GeneDeliveryForm inGeneDeliveryForm, String inUsername, GeneDelivery theGeneDelivery, AnimalModel inAnimalModel) 
		throws Exception {
	
		log.trace( "Entering populateGeneDelivery" );
		 
 		/*1. Create Treatment object, set its regimen, and save Treatment object.	*/	
		Treatment treatment = new Treatment();
		treatment.setRegimen( inGeneDeliveryForm.getRegimen() );
		
		//save treatment object		
		//treatmentManager.save( treatment );
		
		/* 2. Create a GeneDelivery Object and set attributes from GUI, save GeneDelivery object */
		GeneDelivery geneDelivery = new GeneDelivery();
		geneDelivery.setViralVector( inGeneDeliveryForm.getViralVector() );
		geneDelivery.setGeneInVirus( inGeneDeliveryForm.getGeneInVirus() );
		geneDelivery.setTreatment( treatment );
		
        /* Add a Organ to AnimalModel with correct IDs, conceptCode (i.e. Location of Delivery) */
        System.out.println( "Saving: getOrgan=" + inGeneDeliveryForm.getOrgan() + " Not Saving organTissueName=" + inGeneDeliveryForm.getOrganTissueName() );
        
        Organ organ = new Organ();
        organ.setName( inGeneDeliveryForm.getOrganTissueName() );                      
        organ.setConceptCode( inGeneDeliveryForm.getOrganTissueCode() );      
        //organManager.save( organ );
		
        geneDelivery.setOrgan( organ );
		//geneDeliveryManager.save( geneDelivery );
                          
        /*  4. Create Therapy object, set its therapeuticExperiment property to false.
    	 *	4.1 set its treatment property (saved in #2).
    	 *	4.3 Add Therapy to animalModel 
    	 *	4.4 No need to explicity save Therapy object b/c 1...1 relationship with AnimalModel   	    		
    	 *	When TherapeuticExperiment property is false, tells us that this is an environmentalFactor
    	 */
	    // TODO: SAVE THIS SOMEHOW?
	    //Therapy therapy = new Therapy();
	    //therapy.setTherapeuticExperiment( new Boolean( false ) ); 
        //therapyManager.save( therapy );
        
        /* 5. Add Therapy to AnimalModel */
        //animalModel.addTherapy( therapy );
        //animalModel.addGeneDelivery( geneDelivery );
                
		/* 6. save the animalModel = saves Therapy (Hibernate saves child in 1...1 relationships)  */  
        //animalModelManager.save( animalModel );
              
    	//Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        //ActionMessages msg = new ActionMessages();
        //msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "genedelivery.creation.successful" ) );
        //saveErrors( request, msg );

		log.trace( "Exiting populateGeneDelivery" );
	
		return theGeneDelivery;
	}
	
}
