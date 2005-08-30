/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Availability;
import gov.nih.nci.camod.domain.ContactInfo;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.domain.Phenotype;
import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import org.hibernate.eqbe.*;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AnimalModelManagerImpl extends BaseManager implements AnimalModelManager {
	
	public List getAll() {		
		List animalModels = null;
		
		try {
			animalModels = Search.query(AnimalModel.class);
		} catch (Exception e) {
			System.out.println("Exception in AnimalModelManagerImpl.getAnimalModels");
			e.printStackTrace();
		}
		
		return animalModels;
	}
	
	public List getAll(String username) {		
		// The list of AnimalModels to be returned
		List animalModels = null;
		
		// The following two objects are needed for eQBE.
		AnimalModel animalModel = new AnimalModel();
		Person principalInvestigator = new Person();
		principalInvestigator.setUsername(username);
		animalModel.setPrincipalInvestigator(principalInvestigator);
		
		// Apply evaluators to object properties
	    Evaluation evaluation = new Evaluation();
	    evaluation.addEvaluator("principalInvestigator.username", Evaluator.ILIKE_ANYWHERE); 
		
		try {
			animalModels = Search.query(animalModel, evaluation);
		} catch (Exception e) {
			System.out.println("Exception in AnimalModelManagerImpl.getAnimalModels(String username)");
			e.printStackTrace();
		}
		
		return animalModels;
	}
    
    public List getAllByState(String inState) {
        
        // The list of AnimalModels to be returned
        List theAnimalModels = null;
        
        // The following two objects are needed for eQBE.
        AnimalModel theAnimalModel = new AnimalModel();
        theAnimalModel.setState(inState);
        
        // Apply evaluators to object properties
        Evaluation theEvaluation = new Evaluation();
        theEvaluation.addEvaluator("animalModel.state", Evaluator.EQUAL); 
        
        try {
            theAnimalModels = Search.query(theAnimalModel, theEvaluation);
        } catch (Exception e) {
            System.out.println("Exception in AnimalModelManagerImpl.getAnimalModels(String username)");
            e.printStackTrace();
        }
        
        return theAnimalModels;
    }
    
	public AnimalModel get(String id) {
		AnimalModel animalModel = null;
		
		try {
			animalModel = (AnimalModel) Search.queryById(AnimalModel.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AnimalModelManagerImpl.getAnimalModel(String id)");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AnimalModelManagerImpl.getAnimalModel(String id)");
			e.printStackTrace();
		}
		
		return animalModel;
    }

    public void save(AnimalModel animalModel) {
    	try {
			Persist.save(animalModel);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AnimalModelManagerImpl.saveAnimalModel");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AnimalModelManagerImpl.saveAnimalModel");
			e.printStackTrace();
		}
    }
    
    public Long save(
        Person person,
    	ContactInfo contactInfo,
    	AnimalModel animalModel,
    	Taxon taxon,
    	Phenotype phenotype,
    	SexDistribution sexDistribution,
    	Availability availability) {
    	
    	Long id = null; // Return id to the caller
    	
    	try {			
    		// Save contact info and person
    		person.addContactInfo(contactInfo);
    		Persist.save(person);
    		
    		// Save taxon
    		Persist.save(taxon);    		
    		
    		// Save sexDistribution and phenotype
    		Persist.save(sexDistribution);
    		phenotype.setSexDistribution(sexDistribution);
    		Persist.save(phenotype);   		
    		
			// Save availability
    		Persist.save(availability);
    		
    		// Save animalModel
			animalModel.setPrincipalInvestigator(person);
    		animalModel.setSpecies(taxon);
    		animalModel.setPhenotype(phenotype);
    		animalModel.setAvailability(availability);    		
    		Persist.save(animalModel);  
    		id = animalModel.getId(); // Get the id of the just saved AnimalModel
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AnimalModelManagerImpl.saveAnimalModel");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AnimalModelManagerImpl.saveAnimalModel");
			e.printStackTrace();
		}
		
		return id;
    }

    public void remove(String id) {
    	try {
			Persist.deleteById(AnimalModel.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AnimalModelManagerImpl.removeAnimalModel");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AnimalModelManagerImpl.removeAnimalModel");
			e.printStackTrace();
		}
    }
    
    /**
     * Search for animal models based on:
     * 
     *     - modelName
     *     - piName
     *     - siteOfTumor
     *     - speciesName
     * 
     * Note: This method is currently a dummy search method and simply returns all the animal model
     * objects in the database. Searching using eQBE needs to be done.
     */    
    public List search() {
    	List animalModels = null;
		
		try {
			animalModels = Search.query(AnimalModel.class);
		} catch (Exception e) {
			System.out.println("Exception in AnimalModelManagerImpl.search");
			e.printStackTrace();
		}
		
		return animalModels;
    }
}
