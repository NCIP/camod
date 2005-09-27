/**
 * @author dgeorge
 * 
 * $Id: AnimalModelManagerImpl.java,v 1.15 2005-09-27 19:17:16 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.14  2005/09/27 16:44:49  georgeda
 * Added ChemicalDrug handling
 *
 * Revision 1.13  2005/09/26 14:04:36  georgeda
 * Cleanup for cascade fix and common manager code
 *
 * Revision 1.12  2005/09/23 14:55:19  georgeda
 * Made SexDistribution a reference table
 *
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
import gov.nih.nci.camod.webapp.form.*;
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
        log.trace("In AnimalModelManagerImpl.getAll");
        return super.getAll(AnimalModel.class);
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

        log.trace("Entering AnimalModelManagerImpl.getAllByUser");

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

        log.trace("Exiting AnimalModelManagerImpl.getAllByUser");

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

        log.trace("Entering AnimalModelManagerImpl.getAllByState");

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

        log.trace("Exiting AnimalModelManagerImpl.getAllByState");

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
        log.trace("In AnimalModelManagerImpl.get");
        return (AnimalModel) super.get(id, AnimalModel.class);
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
        log.trace("In AnimalModelManagerImpl.save");
        super.save(inAnimalModel);
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

        log.trace("Entering AnimalModelManagerImpl.create");

        AnimalModel theAnimalModel = new AnimalModel();

        log.trace("Exiting AnimalModelManagerImpl.create");
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

        log.trace("Entering AnimalModelManagerImpl.update");

        log.debug("Updating animal model: " + inAnimalModel.getId());

        // Populate w/ the new values and save
        inAnimalModel = populateAnimalModel(inModelCharacteristics, null, inAnimalModel);
        save(inAnimalModel);

        log.trace("Exiting AnimalModelManagerImpl.update");
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
        log.trace("In AnimalModelManagerImpl.remove");
        super.remove(id, AnimalModel.class);
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

    /**
     * Update a chemical/drug therapy
     * 
     * @param inAnimalModel
     *            the animal model that has the therapy
     * @param inChemicalDrug
     *            the new chemical drug data
     * @param inTherapyId
     *            the terapy id we're updating
     * 
     * @throws Exception
     */
    public void updateTherapy(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrug, String inTherapyId)
            throws Exception {

        log.trace("Entering AnimalModelManagerImpl.updateTherapy");

        Therapy theTherapy = findTherapy(inAnimalModel, inTherapyId);

        if (theTherapy != null) {
            TherapyManagerSingleton.instance().update(inChemicalDrug, theTherapy);
        } else {
            throw new IllegalArgumentException("Unknown therapy: " + inTherapyId);
        }
        log.trace("Exiting AnimalModelManagerImpl.updateTherapy");
    }

    /**
     * Add a chemical/drug therapy
     * 
     * @param inAnimalModel
     *            the animal model that has the therapy
     * @param inChemicalDrug
     *            the new chemical drug data
     * @throws Exception
     */
    public void addTherapy(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrug) throws Exception {

        log.trace("Entering AnimalModelManagerImpl.addTherapy");
        Therapy theTherapy = TherapyManagerSingleton.instance().create(inChemicalDrug);
        inAnimalModel.addTherapy(theTherapy);
        save(inAnimalModel);
        log.trace("Exiting AnimalModelManagerImpl.addTherapy");
    }

    /**
     * Update an environmental factor therapy
     * 
     * @param inAnimalModel
     *            the animal model that has the therapy
     * @param inEnvironmentalFactor
     *            the new chemical drug data
     * @param inTherapyId
     *            the terapy id we're updating
     * 
     * @throws Exception
     */
    public void updateTherapy(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactor, String inTherapyId)
            throws Exception {

        log.trace("Entering AnimalModelManagerImpl.updateTherapy");

        Therapy theTherapy = findTherapy(inAnimalModel, inTherapyId);

        if (theTherapy != null) {
            TherapyManagerSingleton.instance().update(inEnvironmentalFactor, theTherapy);
        } else {
            throw new IllegalArgumentException("Unknown therapy: " + inTherapyId);
        }
        log.trace("Exiting AnimalModelManagerImpl.updateTherapy");
    }

    /**
     * Add an environmental factor therapy
     * 
     * @param inAnimalModel
     *            the animal model that has the therapy
     * @param inEnvironmentalFactor
     *            the ef data
     * @throws Exception
     */
    public void addTherapy(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactor) throws Exception {

        log.trace("Entering AnimalModelManagerImpl.addTherapy");
        Therapy theTherapy = TherapyManagerSingleton.instance().create(inEnvironmentalFactor);
        inAnimalModel.addTherapy(theTherapy);
        save(inAnimalModel);
        log.trace("Exiting AnimalModelManagerImpl.addTherapy");
    }
    
    /**
     * Update an environmental factor therapy
     * 
     * @param inAnimalModel
     *            the animal model that has the therapy
     * @param inRadiationData
     *            the new radiation data
     * @param inTherapyId
     *            the therapy id we're updating
     * 
     * @throws Exception
     */
    public void updateTherapy(AnimalModel inAnimalModel, RadiationData inRadiationData, String inTherapyId)
            throws Exception {

        log.trace("Entering AnimalModelManagerImpl.updateTherapy");

        Therapy theTherapy = findTherapy(inAnimalModel, inTherapyId);

        if (theTherapy != null) {
            TherapyManagerSingleton.instance().update(inRadiationData, theTherapy);
        } else {
            throw new IllegalArgumentException("Unknown therapy: " + inTherapyId);
        }
        log.trace("Exiting AnimalModelManagerImpl.updateTherapy");
    }

    /**
     * Add an environmental factor therapy
     * 
     * @param inAnimalModel
     *            the animal model that has the therapy
     * @param inRadiationData
     *            the new radiation data
     * @throws Exception
     */
    public void addTherapy(AnimalModel inAnimalModel, RadiationData inRadiationData) throws Exception {

        log.trace("Entering AnimalModelManagerImpl.addTherapy");
        Therapy theTherapy = TherapyManagerSingleton.instance().create(inRadiationData);
        inAnimalModel.addTherapy(theTherapy);
        save(inAnimalModel);
        log.trace("Exiting AnimalModelManagerImpl.addTherapy");
    }
    
    /**
     * Update an environmental factor therapy
     * 
     * @param inAnimalModel
     *            the animal model that has the therapy
     * @param inViralTreatmentData
     *            the new viral treatment data
     * @param inTherapyId
     *            the therapy id we're updating
     * 
     * @throws Exception
     */
    public void updateTherapy(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatmentData, String inTherapyId)
            throws Exception {

        log.trace("Entering AnimalModelManagerImpl.updateTherapy");

        Therapy theTherapy = findTherapy(inAnimalModel, inTherapyId);

        if (theTherapy != null) {
            TherapyManagerSingleton.instance().update(inViralTreatmentData, theTherapy);
        } else {
            throw new IllegalArgumentException("Unknown therapy: " + inTherapyId);
        }
        log.trace("Exiting AnimalModelManagerImpl.updateTherapy");
    }

    /**
     * Add an environmental factor therapy
     * 
     * @param inAnimalModel
     *            the animal model that has the therapy
     * @param inViralTreatmentData
     *            the new viral treatment data
     * @throws Exception
     */
    public void addTherapy(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatmentData) throws Exception {

        log.trace("Entering AnimalModelManagerImpl.addTherapy");
        Therapy theTherapy = TherapyManagerSingleton.instance().create(inViralTreatmentData);
        inAnimalModel.addTherapy(theTherapy);
        save(inAnimalModel);
        log.trace("Exiting AnimalModelManagerImpl.addTherapy");
    }
    
    // Find a therapy based on the id
    private Therapy findTherapy(AnimalModel inAnimalModel, String inTherapyId) {
        Therapy theTherapy = null;

        // retrieve the list of all therapies from the current animalModel
        List theTherapyList = inAnimalModel.getTherapyCollection();

        // find the specific one we need
        for (int i = 0; i < theTherapyList.size(); i++) {
            theTherapy = (Therapy) theTherapyList.get(i);
            if (theTherapy.getId().toString().equals(inTherapyId)) {
                log.debug("found a match for id: " + inTherapyId);
                break;
            }
        }

        return theTherapy;
    }
}
