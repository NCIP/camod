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
import gov.nih.nci.camod.webapp.form.cibase.*;

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
        populateName(inViralTreatmentData, theTherapy, "Viral");
        populateAgeGender(inViralTreatmentData, theTherapy);
        populateTreatment(inViralTreatmentData, theTherapy);
        populateDose(inViralTreatmentData, theTherapy);
        populateAdministration(inViralTreatmentData, theTherapy);
        
        return theTherapy;
    }

    public void update(ViralTreatmentData inViralTreatmentData, Therapy inTherapy) throws Exception {

        log.trace("In TherapyManagerImpl.update");

        populateName(inViralTreatmentData, inTherapy, "Viral");
        populateAgeGender(inViralTreatmentData, inTherapy);
        populateTreatment(inViralTreatmentData, inTherapy);
        populateDose(inViralTreatmentData, inTherapy);
        populateAdministration(inViralTreatmentData, inTherapy);
        
        save(inTherapy);
    }

    public Therapy create(RadiationData inRadiationData) {

        log.trace("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        
        populateName(inRadiationData, theTherapy, "Radiation");
        populateAgeGender(inRadiationData, theTherapy);
        populateTreatment(inRadiationData, theTherapy);
        populateDose(inRadiationData, theTherapy);
        populateAdministration(inRadiationData, theTherapy);

        return theTherapy;
    }

    public void update(RadiationData inRadiationData, Therapy inTherapy) throws Exception {

        log.trace("In TherapyManagerImpl.update");

        populateName(inRadiationData, inTherapy, "Radiation");
        populateAgeGender(inRadiationData, inTherapy);
        populateTreatment(inRadiationData, inTherapy);
        populateDose(inRadiationData, inTherapy);
        populateAdministration(inRadiationData, inTherapy);
        
        save(inTherapy);
    }

    public Therapy create(EnvironmentalFactorData inEnvironmentalFactorData) {

        log.trace("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        
        populateName(inEnvironmentalFactorData, theTherapy, "Environment");
        populateAgeGender(inEnvironmentalFactorData, theTherapy);
        populateTreatment(inEnvironmentalFactorData, theTherapy);
        populateDose(inEnvironmentalFactorData, theTherapy);
        populateAdministration(inEnvironmentalFactorData, theTherapy);

        return theTherapy;
    }

    public void update(EnvironmentalFactorData inEnvironmentalFactorData, Therapy inTherapy) throws Exception {

        log.trace("In TherapyManagerImpl.update");

        populateName(inEnvironmentalFactorData, inTherapy, "Environment");
        populateAgeGender(inEnvironmentalFactorData, inTherapy);
        populateTreatment(inEnvironmentalFactorData, inTherapy);
        populateDose(inEnvironmentalFactorData, inTherapy);
        populateAdministration(inEnvironmentalFactorData, inTherapy);
        
        save(inTherapy);
    }

    public Therapy create(ChemicalDrugData inChemicalDrugData) {

        log.trace("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        
        populateName(inChemicalDrugData, theTherapy, "Chemical / Drug");
        populateAgeGender(inChemicalDrugData, theTherapy);
        populateTreatment(inChemicalDrugData, theTherapy);
        populateDose(inChemicalDrugData, theTherapy);
        populateAdministration(inChemicalDrugData, theTherapy);
        
        populateChemicalDrug(inChemicalDrugData, theTherapy);

        return theTherapy;
    }

    public void update(ChemicalDrugData inChemicalDrugData, Therapy inTherapy) throws Exception {

        log.trace("In TherapyManagerImpl.update");
        
        populateName(inChemicalDrugData, inTherapy, "Chemical / Drug");
        populateAgeGender(inChemicalDrugData, inTherapy);
        populateTreatment(inChemicalDrugData, inTherapy);
        populateDose(inChemicalDrugData, inTherapy);
        populateAdministration(inChemicalDrugData, inTherapy);
        
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

    private void populateAgeGender(AgeGenderData inAgeGender, Therapy theTherapy) {

        // Set the treatment
        Treatment theTreatment = theTherapy.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            theTherapy.setTreatment(theTreatment);
        }

        // Set the gender
        SexDistribution sexDistribution = SexDistributionManagerSingleton.instance().getByType(inAgeGender.getType());

        // save the treatment
        theTreatment.setSexDistribution(sexDistribution);

        // Append the ageunit onto the age at treatment variable
        theTreatment.setAgeAtTreatment(inAgeGender.getAgeAtTreatment() + " " + inAgeGender.getAgeUnit());
    }

    private void populateTreatment(TreatmentData inTreatment, Therapy theTherapy) {

        // Set the treatment
        Treatment theTreatment = theTherapy.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            theTherapy.setTreatment(theTreatment);
        }

        // save the treatment
        theTreatment.setRegimen(inTreatment.getRegimen());
    }

    private void populateAdministration(AdministrationData inAdministrationData, Therapy theTherapy) {

        // Set the treatment
        Treatment theTreatment = theTherapy.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            theTherapy.setTreatment(theTreatment);
        }

        theTreatment.setAdministrativeRoute(inAdministrationData.getAdministrativeRoute());

        // Agent IS-A an EnvironmentalFactor
        Agent theAgent = theTherapy.getAgent();
        if (theAgent == null) {
            theAgent = new Agent();
            theTherapy.setAgent(theAgent);
        }
    }

    private void populateDose(DoseData inDoseData, Therapy theTherapy) {

        // Set the treatment
        Treatment theTreatment = theTherapy.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            theTherapy.setTreatment(theTreatment);
        }

        theTreatment.setDosage(inDoseData.getDosage() + " " + inDoseData.getDoseUnit());
    }

    private void populateName(NameData inNameData, Therapy theTherapy, String inType) {

        // Set the treatment
        Treatment theTreatment = theTherapy.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            theTherapy.setTreatment(theTreatment);
        }

        // Agent IS-A an EnvironmentalFactor
        Agent theAgent = theTherapy.getAgent();
        if (theAgent == null) {
            theAgent = new Agent();
            theTherapy.setAgent(theAgent);
        }
        theAgent.setType(inType);
        theAgent.setName(inNameData.getName());
        theTherapy.setTherapeuticExperiment(new Boolean(false));
    }
}
