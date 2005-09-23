/**
 * @author dgeorge
 * 
 * $Id: AnimalModelManagerImpl.java,v 1.12 2005-09-23 14:55:19 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2005/09/22 18:55:53  georgeda
 * Get coordinator from user in properties file
 *
 * Revision 1.10  2005/09/19 18:13:51  georgeda
 * Changed boolean to Boolean
 *
 * Revision 1.9  2005/09/19 12:55:24  georgeda
 * Handle empty sex distribution table
 *
 * Revision 1.8  2005/09/16 15:52:57  georgeda
 * Changes due to manager re-write
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.ModelCharacteristics;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.HibernateUtil;

import java.util.*;

/**
 * Manages fetching/saving/updating of animal models
 */
public class AnimalModelManagerImpl extends BaseManager implements AnimalModelManager {

    /**
     * Get all of the animal models in the DB
     * 
     * @return the list of all animal models
     * 
     * @exception throws
     *                an Exception if an error occurred
     */
    public List getAll() throws Exception {

        log.trace("Entering getAll");

        List theAnimalModels = null;

        try {
            theAnimalModels = Search.query(AnimalModel.class);
        } catch (Exception e) {
            log.error("Exception occurred in getAll", e);
            throw e;
        }

        log.trace("Exiting getAll");

        return theAnimalModels;
    }

    /**
     * Get all of the animal models submitted by a username
     * 
     * @param inUsername
     *            the username the models are submitted by
     * 
     * @return the list of animal models
     * 
     * @exception throws
     *                an Exception if an error occurred
     */
    public List getAllByUser(String inUsername) throws Exception {

        log.trace("Entering getAll");

        // The list of AnimalModels to be returned
        List theAnimalModels = null;

        // The following two objects are needed for eQBE.
        AnimalModel theAnimalModel = new AnimalModel();
        Person theSubmitter = new Person();
        theSubmitter.setUsername(inUsername);
        theAnimalModel.setSubmitter(theSubmitter);

        try {
            theAnimalModels = Search.query(theAnimalModel);
        } catch (Exception e) {
            log.error("Exception occurred in getAll", e);
            throw e;
        }

        log.trace("Exiting getAllByState");

        return theAnimalModels;
    }

    /**
     * Get all of the animal models of a specific state
     * 
     * @param inState
     *            the state to query for
     * 
     * @return the list of animal models
     * 
     * @exception Exception
     *                if an error occurred
     */
    public List getAllByState(String inState) throws Exception {

        log.trace("Entering getAllByState");

        // The list of AnimalModels to be returned
        List theAnimalModels = new ArrayList();

        // The following two objects are needed for eQBE.
        AnimalModel theAnimalModel = new AnimalModel();
        theAnimalModel.setState(inState);

        try {
            theAnimalModels = Search.query(theAnimalModel);
        } catch (Exception e) {
            log.error("Exception occurred in getAllByState", e);
            throw e;
        }

        log.trace("Exiting getAllByState");

        return theAnimalModels;
    }

    /**
     * Get a specific animal model
     * 
     * @param id
     *            The unique id for the model
     * 
     * @return the animal model if found, null otherwise
     * @throws Exception
     * 
     * @exception Exception
     *                if an error occurred
     */
    public AnimalModel get(String id) throws Exception {

        log.trace("Entering get");

        AnimalModel animalModel = null;

        try {
            log.debug("Querying for id: " + id);
            animalModel = (AnimalModel) Search.queryById(AnimalModel.class, new Long(id));
        } catch (PersistenceException pe) {
            log.error("Exception occurred in getAnimalModel", pe);
            throw pe;
        } catch (Exception e) {
            log.error("Exception occurred in getAnimalModel", e);
            throw e;
        }

        log.trace("Exiting get");
        return animalModel;
    }

    /**
     * Save an animal model
     * 
     * @param id
     *            The unique id for the model
     * 
     * @exception Exception
     *                if an error occurred
     */
    public void save(AnimalModel inAnimalModel) throws Exception {

        log.trace("Entering save");

        try {
            // Begin an transaction
            HibernateUtil.beginTransaction();

            // Save the related objects
            // NOTE: Will be fixed when cascading updates are correctly
            // implemented
            log.debug("Saving availability");
            Persist.save(inAnimalModel.getAvailability());

            log.debug("Saving sexDistribution");
            Persist.save(inAnimalModel.getPhenotype().getSexDistribution());

            log.debug("Saving phenotype");
            Persist.save(inAnimalModel.getPhenotype());

            log.debug("Saving species");
            Persist.save(inAnimalModel.getSpecies());

            log.debug("Saving PI");
            PersonManagerSingleton.instance().save(inAnimalModel.getPrincipalInvestigator());

            log.debug("Saving submitter");
            PersonManagerSingleton.instance().save(inAnimalModel.getSubmitter());

            // Save the base animal model
            log.debug("Saving animal model");
            Persist.save(inAnimalModel);

            // Commit all changes or none
            HibernateUtil.commitTransaction();

        } catch (PersistenceException pe) {
            log.error("PersistenceException in save", pe);
            HibernateUtil.rollbackTransaction();
            throw pe;
        } catch (Exception e) {
            log.error("Exception in save", e);
            HibernateUtil.rollbackTransaction();
            throw e;
        }
    }

    /**
     * Update an animal model and create an associated log entry
     * 
     * @param id
     *            The unique id for the model
     * @throws Exception
     * 
     * @exception Exception
     *                if an error occurred
     */
    public void updateAndAddLog(AnimalModel inAnimalModel, Log inLog) throws Exception {

        log.trace("Entering updateAndAddLog");

        try {

            // Make sure they get saved together
            HibernateUtil.beginTransaction();
            Persist.save(inAnimalModel);
            Persist.save(inLog);
            HibernateUtil.commitTransaction();

        } catch (PersistenceException pe) {
            HibernateUtil.rollbackTransaction();
            log.error("PersistenceException in save", pe);
            throw pe;
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            log.error("Exception in save", e);
            throw e;
        }

        log.trace("Exiting updateAndAddLog");
    }

    /**
     * Create a new/unsaved animal model
     * 
     * @param inModelCharacteristics
     *            The values for the model and associated objects
     * 
     * @param inUsername
     *            The submitter
     * 
     * @return the created and unsaved AnimalModel
     * @throws Exception 
     */
    public AnimalModel create(ModelCharacteristics inModelCharacteristics, String inUsername) throws Exception {

        log.trace("Entering create");

        AnimalModel theAnimalModel = new AnimalModel();

        log.trace("Exiting create");
        return populateAnimalModel(inModelCharacteristics, inUsername, theAnimalModel);
    }

    /**
     * Update the animal model w/ the new characteristics and save
     * 
     * @param inModelCharacteristics
     *            The new values for the model and associated objects
     * 
     * @param inAnimalModel
     *            The animal model to update
     */
    public void update(ModelCharacteristics inModelCharacteristics, AnimalModel inAnimalModel) throws Exception {

        log.trace("Entering update");

        log.debug("Updating animal model: " + inAnimalModel.getId());

        // Populate w/ the new values and save
        inAnimalModel = populateAnimalModel(inModelCharacteristics, null, inAnimalModel);
        save(inAnimalModel);

        log.trace("Exiting update");
    }

    /**
     * Remove the animal model from the system. Should remove all associated
     * data as well
     * 
     * @param id
     *            The unique id of the animal model to delete
     * 
     * @throws Exception
     *             An error occurred when attempting to delete the model
     */
    public void remove(String id) throws Exception {

        log.trace("Entering remove");

        try {
            log.debug("Removing animal model: " + id);
            Persist.deleteById(AnimalModel.class, new Long(id));
        } catch (PersistenceException pe) {
            log.error("Unable to delete model: ", pe);
            throw pe;
        } catch (Exception e) {
            log.error("Unable to delete model: ", e);
            throw e;
        }

        log.trace("Exiting remove");
    }

    /**
     * Search for animal models based on: - modelName - piName - siteOfTumor -
     * speciesName
     * 
     * Note: This method is currently a dummy search method and simply returns
     * all the animal model objects in the database. Searching using eQBE needs
     * to be done.
     * 
     * @throws Exception
     */
    public List search() throws Exception {

        log.trace("Entering search");

        List animalModels = null;

        try {
            animalModels = Search.query(AnimalModel.class);
        } catch (Exception e) {
            log.error("Exception occurred searching for models", e);
            throw e;
        }

        log.trace("Exiting search");
        return animalModels;
    }

    // Populate the model based on the model characteristics form passed in. It
    // will update associated
    // object if they exist, create them if they don't.
    private AnimalModel populateAnimalModel(ModelCharacteristics inModelCharacteristics, String inUsername,
            AnimalModel inAnimalModel) throws Exception {

        log.trace("Entering populateAnimalModel");

        // Handle the person information
        if (inUsername != null) {
            Person thePerson = PersonManagerSingleton.instance().getByUsername(inUsername);
            if (thePerson == null) {

                // Create a new person
                thePerson = new Person();
                thePerson.setUsername(inUsername);
                thePerson.setIsPrincipalInvestigator(new Boolean(true));

                // Add the contact information
                ContactInfo theContactInfo = new ContactInfo();
                theContactInfo.setEmail(inModelCharacteristics.getEmail());
                thePerson.addContactInfo(theContactInfo);
            }

            System.out.println("The person: " + thePerson.getUsername());
            inAnimalModel.setSubmitter(thePerson);

            // TODO: Change this to match the real PI
            inAnimalModel.setPrincipalInvestigator(thePerson);
        }

        // Set the animal model information
        boolean isToolMouse = inModelCharacteristics.getIsToolMouse().equals("yes") ? true : false;
        inAnimalModel.setIsToolMouse(new Boolean(isToolMouse));
        inAnimalModel.setUrl(inModelCharacteristics.getUrl());
        inAnimalModel.setModelDescriptor(inModelCharacteristics.getModelDescriptor());
        inAnimalModel.setExperimentDesign(inModelCharacteristics.getExperimentDesign());

        // Create/reuse the taxon
        Taxon theTaxon = inAnimalModel.getSpecies();
        if (theTaxon == null) {
            theTaxon = new Taxon();
        }
        theTaxon.setScientificName(inModelCharacteristics.getScientificName());
        theTaxon.setEthnicityStrain(inModelCharacteristics.getEthinicityStrain());

        // TODO: Handle other ethnicity strain. Where?

        Phenotype thePhenotype = inAnimalModel.getPhenotype();
        if (thePhenotype == null) {
            thePhenotype = new Phenotype();
        }

        // Get/create the sex distribution
        SexDistribution theSexDistribution = SexDistributionManagerSingleton.instance().getByType(
                inModelCharacteristics.getType());
        if (theSexDistribution == null) {
            theSexDistribution = new SexDistribution();
            theSexDistribution.setType(inModelCharacteristics.getType());
        }

        // Create the phenotype
        thePhenotype.setDescription(inModelCharacteristics.getDescription());
        thePhenotype.setBreedingNotes(inModelCharacteristics.getBreedingNotes());
        thePhenotype.setSexDistribution(theSexDistribution);

        // Get the availability
        Availability theAvailability = inAnimalModel.getAvailability();

        // When the model was created
        if (theAvailability == null) {
            theAvailability = new Availability();
        }
        theAvailability.setEnteredDate(new Date());

        if (inModelCharacteristics.getReleaseDate().equals("immediately")) {
            theAvailability.setReleaseDate(new Date());
        } else {
            // TODO: add popup calender to submitNewModel.jsp and convert the
            // string to Date object here
            // modelChar.getCalendarReleaseDate();
            theAvailability.setReleaseDate(new Date());
        }

        // By default a new model's state is set to incomplete if it's not set
        if (inAnimalModel.getState() == null) {
            inAnimalModel.setState("Incomplete");
        }

        // Associated the created objects
        inAnimalModel.setAvailability(theAvailability);
        inAnimalModel.setPhenotype(thePhenotype);
        inAnimalModel.setSpecies(theTaxon);

        log.trace("Exiting populateAnimalModel");

        return inAnimalModel;
    }
}
