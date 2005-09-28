/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.XenograftManager;
import gov.nih.nci.camod.webapp.form.XenograftForm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class XenograftManagerImpl extends BaseManager implements XenograftManager {

	public List getAll() throws Exception {
		log.trace("In XenograftManagerImpl.getAll");
		return super.getAll(Xenograft.class);
	}

	public Xenograft get(String id) throws Exception {
		log.trace("In XenograftManagerImpl.get");
		return (Xenograft) super.get(id, Xenograft.class);
	}

	public void save(Xenograft xenograft) throws Exception {
		log.trace("In XenograftManagerImpl.save");
		super.save(xenograft);    	     
	}

	public void remove(String id) throws Exception {		
		log.trace("In XenograftManagerImpl.remove");		
		super.remove(id, Xenograft.class);
	}

	public Xenograft create(XenograftForm inXenograftForm, String inUsername, AnimalModel inAnimalModel ) 
		throws Exception {

		log.trace( "Entering XenograftManagerImpl.create" );

		Xenograft theXenograft = new Xenograft();
		
		log.trace( "Exiting XenograftManagerImpl.create" );
		return populateXenograft(inXenograftForm, inUsername, theXenograft, inAnimalModel );
	}

	public void update(XenograftForm inXenograftForm, Xenograft theXenograft, AnimalModel inAnimalModel)
		throws Exception {

		log.trace( "Entering XenograftManagerImpl.update" );
		log.debug( "Updating XenograftForm: " + theXenograft.getId() );

		// Populate w/ the new values and save
		theXenograft = populateXenograft(inXenograftForm, null, theXenograft, inAnimalModel);
		
		save(theXenograft);

		log.trace( "Exiting XenograftManagerImpl.update" );
	}

	private Xenograft populateXenograft( XenograftForm inXenograftForm, String inUsername, Xenograft theXenograft, AnimalModel inAnimalModel) 
		throws Exception {

		log.trace( "Entering populateXenograft" );

        theXenograft.setName( inXenograftForm.getName() );
        theXenograft.setAdministrativeSite( inXenograftForm.getAdministrativeSite() );
        theXenograft.setGeneticManipulation( inXenograftForm.getGeneticManipulation() );
        theXenograft.setModificationDescription( inXenograftForm.getModificationDescription() );
        theXenograft.setParentalCellLineName( inXenograftForm.getParentalCellLineName() );
        theXenograft.setAtccNumber( inXenograftForm.getATCCNumber() );
        theXenograft.setCellAmount( inXenograftForm.getCellAmount() );
        
        // Find the matching taxon
        Taxon theTaxon = new Taxon();
        List taxonList = (List) TaxonManagerSingleton.instance().getAll(  );
        
        for( int i=0; i<taxonList.size(); i++ )
        {
        	theTaxon = (Taxon) taxonList.get(0);
        	if ( theTaxon.getEthnicityStrain().equals( inXenograftForm.getHostEthinicityStrain() ))
        		break;        		
        }
        
        //Retrieve the specific Taxon needed for this model based on it's Species and Strain        
        //theTaxon.setScientificName( inXenograftForm.getHostScientificName() );
        //theTaxon.setEthnicityStrain( inXenograftForm.getHostEthinicityStrain() );
        
        if ( inXenograftForm.getOtherHostEthinicityStrain() != null )
        {
        	//TODO: Send an email
        	System.out.println( "SENDING EMAIL STRAIN" );
        	
        	theTaxon.setEthnicityStrainUnctrlVocab( inXenograftForm.getOtherHostEthinicityStrain() );
        }
        
        // Taxon
        theXenograft.setHostSpecies( theTaxon );
        theXenograft.setOriginSpecies( inAnimalModel.getSpecies() );
        
        //Convert String into a Date                     
        if( inXenograftForm.getHarvestDate() != null) {
        	if ( ! inXenograftForm.getHarvestDate().equals("") ) {        	
        		try {

                    String inputFormatString = "dd/MM/yyyy";
                    
                    // parse the input - turn it into a date object
                    DateFormat inputFormat = new SimpleDateFormat(inputFormatString);
                    Date dateTimeValue = inputFormat.parse(inXenograftForm.getHarvestDate());
                    theXenograft.setHarvestDate( dateTimeValue );
                    	
        		} catch ( Exception e ) {
        			// TODO: Possibly setup validator here to catch incorrect formatting of date field
        			System.out.println( "Error: Incorrect format! " + e );
        		}
        	}
        }

        // Graft Type
        theXenograft.setGraftType( inXenograftForm.getGraftType() );
        
        // Send an email when this happens 
        if ( inXenograftForm.getOtherGraftType() != null ) {
        	
        	//TODO: send an email        	
        	System.out.println( "SENDING EMAIL GRAFT" );
        	
        	theXenograft.setGraftTypeUnctrlVocab( inXenograftForm.getOtherGraftType() );        	
        }
        
        // Add xenograft to animalModel
        //inAnimalModel.addXenograft( theXenograft );
     
        // Persist changes to db
        //AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
        //animalModelManager.save( inAnimalModel );     
			
		log.trace( "Exiting populateXenograft" );

		return theXenograft;
	}
}
