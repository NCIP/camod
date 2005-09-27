/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.webapp.form.*;

import java.util.List;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TherapyManagerImpl extends BaseManager implements TherapyManager {

    public Therapy create(ViralTreatmentData inViralTreatmentData) {

        log.trace("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        populateCarcinogenicIntervention(inViralTreatmentData, theTherapy, "Viral");

        return theTherapy;
    }

    public void update(ViralTreatmentData inViralTreatmentData, Therapy inTherapy) throws Exception {

        log.trace("In TherapyManagerImpl.update");

        populateCarcinogenicIntervention(inViralTreatmentData, inTherapy, "Viral");
        save(inTherapy);
    }
    
    public Therapy create(RadiationData inRadiationData) {

        log.trace("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        populateCarcinogenicIntervention(inRadiationData, theTherapy, "Radiation");

        return theTherapy;
    }

    public void update(RadiationData inRadiationData, Therapy inTherapy) throws Exception {

        log.trace("In TherapyManagerImpl.update");

        populateCarcinogenicIntervention(inRadiationData, inTherapy, "Radiation");
        save(inTherapy);
    }
    
    public Therapy create(EnvironmentalFactorData inEnvironmentalFactorData) {

        log.trace("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        populateCarcinogenicIntervention(inEnvironmentalFactorData, theTherapy, "Environment");

        return theTherapy;
    }

    public void update(EnvironmentalFactorData inEnvironmentalFactorData, Therapy inTherapy) throws Exception {

        log.trace("In TherapyManagerImpl.update");

        populateCarcinogenicIntervention(inEnvironmentalFactorData, inTherapy, "Environment");
        save(inTherapy);
    }

    public Therapy create(ChemicalDrugData inChemicalDrugData) {

        log.trace("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        populateCarcinogenicIntervention(inChemicalDrugData, theTherapy, "Chemical / Drug");
        populateChemicalDrug(inChemicalDrugData, theTherapy);

        return theTherapy;
    }

    public void update(ChemicalDrugData inChemicalDrugData, Therapy inTherapy) throws Exception {

        log.trace("In TherapyManagerImpl.update");
        populateCarcinogenicIntervention(inChemicalDrugData, inTherapy, "Chemical / Drug");
        populateChemicalDrug(inChemicalDrugData, inTherapy);
        save(inTherapy);
    }

    public List getAll() throws Exception {
        log.trace("In TherapyManagerImpl.getAll");
        return super.getAll(Therapy.class);
    }

    public Therapy get(String id) throws Exception {
        log.trace("In TherapyManagerImpl.get");
        return (Therapy) super.get(id, Therapy.class);
    }

    public void save(Therapy therapy) throws Exception {
        log.trace("In TherapyManagerImpl.save");
        super.save(therapy);
    }

    public void remove(String id) throws Exception {
        log.trace("In TherapyManagerImpl.save");
        super.remove(id, Therapy.class);
    }

    private void populateChemicalDrug(ChemicalDrugData inChemicalDrug, Therapy theTherapy) {

        // Agent IS-A an EnvironmentalFactor
        Agent theAgent = theTherapy.getAgent();

        String theNSCNumber = inChemicalDrug.getNSCNumber().trim();
        if (theNSCNumber != null && theNSCNumber.length() > 0) {
            try {
                theAgent.setNscNumber(Long.valueOf(theNSCNumber));
            } catch (NumberFormatException e) {
                log.error("Bad NSC number: " + theNSCNumber);
            }
        }
        String theCasNumber = inChemicalDrug.getCASNumber().trim();
        if (theCasNumber != null && theCasNumber.length() > 0) {
            theAgent.setCasNumber(theCasNumber);
        }
    }

    private void populateCarcinogenicIntervention(CarcinogenicInterventionData inCarcinogenicIntervention,
            Therapy theTherapy, String inType) {

        // Set the treatment
        Treatment theTreatment = theTherapy.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            theTherapy.setTreatment(theTreatment);
        }

        // Set the gender
        SexDistribution sexDistribution = SexDistributionManagerSingleton.instance().getByType(
                inCarcinogenicIntervention.getType());

        // save the treatment
        theTreatment.setRegimen(inCarcinogenicIntervention.getRegimen());
        theTreatment.setSexDistribution(sexDistribution);

        // Append the ageunit onto the age at treatment variable
        theTreatment.setAgeAtTreatment(inCarcinogenicIntervention.getAgeAtTreatment() + " "
                + inCarcinogenicIntervention.getAgeUnit());
        theTreatment.setDosage(inCarcinogenicIntervention.getDosage() + " " + inCarcinogenicIntervention.getDoseUnit());
        theTreatment.setAdministrativeRoute(inCarcinogenicIntervention.getAdministrativeRoute());

        // Agent IS-A an EnvironmentalFactor
        Agent theAgent = theTherapy.getAgent();
        if (theAgent == null) {
            theAgent = new Agent();
            theTherapy.setAgent(theAgent);
        }
        theAgent.setType(inType);
        theAgent.setName(inCarcinogenicIntervention.getName());

        // TherapeuticExperiment property is false, tells us that this is an
        // environmentalFactor
        theTherapy.setTherapeuticExperiment(new Boolean(false));
        theTherapy.setAgent(theAgent);
        theTherapy.setTreatment(theTreatment);
    }
}
