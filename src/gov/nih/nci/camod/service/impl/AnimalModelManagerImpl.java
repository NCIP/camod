/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
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
        List theAnimalModels = new ArrayList();

        // The following two objects are needed for eQBE.
        AnimalModel theAnimalModel = new AnimalModel();
        theAnimalModel.setState(inState);

        // Apply evaluators to object properties
        Evaluation theEvaluation = new Evaluation();
        theEvaluation.addEvaluator("animalModel.state", Evaluator.EQUAL);

        try {
            String theSQL = "select abs_cancer_model_id from abs_cancer_model where state = '" + inState + "'";

            ResultSet theRS = Search.query(theSQL, new Object[0]);

            while (theRS.next()) {
                theAnimalModels
                        .add(Search.queryById(AnimalModel.class, new Long(theRS.getLong("abs_cancer_model_id"))));
            }
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

    public Long save(Person person, ContactInfo contactInfo, AnimalModel animalModel, Taxon taxon, Phenotype phenotype,
            SexDistribution sexDistribution, Availability availability) {

        Long id = null; // Return id to the caller

        try {

            // See if there's already a person that exists
            Person thePerson = PersonManagerSingleton.instance().getByUsername(person.getUsername());

            if (thePerson != null) {
                person = thePerson;
            } else {
                person.addContactInfo(contactInfo);
                PersonManagerSingleton.instance().save(person);
            }

            // Save taxon
            Persist.save(taxon);

            // See if there's already a sex distribution that exists, otherwise
            // save a new one
            SexDistribution theSexDistribution = SexDistributionManagerSingleton.instance().getByType(
                    sexDistribution.getType());

            if (theSexDistribution != null) {
                sexDistribution = theSexDistribution;
            } else {
                SexDistributionManagerSingleton.instance().save(sexDistribution);
            }

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
            id = animalModel.getId(); // Get the id of the just saved
            // AnimalModel
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
     * Search for animal models based on: - modelName - piName - siteOfTumor -
     * speciesName
     * 
     * Note: This method is currently a dummy search method and simply returns
     * all the animal model objects in the database. Searching using eQBE needs
     * to be done.
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
