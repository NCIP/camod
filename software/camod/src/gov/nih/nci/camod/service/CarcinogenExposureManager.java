/**
 * 
 * $Id: CarcinogenExposureManager.java,v 1.1 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.webapp.form.ChemicalDrugData;
import gov.nih.nci.camod.webapp.form.EnvironmentalFactorData;
import gov.nih.nci.camod.webapp.form.GrowthFactorData;
import gov.nih.nci.camod.webapp.form.HormoneData;
import gov.nih.nci.camod.webapp.form.NutritionalFactorData;
import gov.nih.nci.camod.webapp.form.RadiationData;
import gov.nih.nci.camod.webapp.form.SurgeryData;
import gov.nih.nci.camod.webapp.form.ViralTreatmentData;
import gov.nih.nci.camod.webapp.form.AntibodyData;
import gov.nih.nci.camod.webapp.form.BacteriaData;
import gov.nih.nci.camod.webapp.form.PlasmidData;
import gov.nih.nci.camod.webapp.form.SignalingMoleculeData;
import gov.nih.nci.camod.webapp.form.TransposonData;


/**
 * Interface for managing the Carcinogen Exposure objects
 */
public interface CarcinogenExposureManager
{
    public CarcinogenExposure get(String id) throws Exception;

    public void save(CarcinogenExposure carcinogenExposure) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;
    
    public CarcinogenExposure create(AnimalModel inAnimalModel, HormoneData inHormoneData);

    public void update(AnimalModel inAnimalModel, HormoneData inHormoneData, CarcinogenExposure inCarcinogenExposure) throws Exception;
    
    public CarcinogenExposure create(AnimalModel inAnimalModel, NutritionalFactorData inNutritionalFactorData);

    public void update(AnimalModel inAnimalModel, NutritionalFactorData inNutritionalFactorData, CarcinogenExposure inCarcinogenExposure) throws Exception;
    
    public CarcinogenExposure create(AnimalModel inAnimalModel, RadiationData inRadiationData);

    public void update(AnimalModel inAnimalModel, RadiationData inRadiationData, CarcinogenExposure inCarcinogenExposure) throws Exception;

    public CarcinogenExposure create(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatmentData);

    public void update(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatmentData, CarcinogenExposure inCarcinogenExposure) throws Exception;

    public CarcinogenExposure create(AnimalModel inAnimalModel, SurgeryData inSurgeryData);

    public void update(AnimalModel inAnimalModel, SurgeryData inSurgeryData, CarcinogenExposure inCarcinogenExposure) throws Exception;
    
    
    /////////////////////////////////////////////////
    // The interface specific create/updates
    /////////////////////////////////////////////////
    public CarcinogenExposure create(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrugData);
    
    public void update(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrugData, CarcinogenExposure inCarcinogenExposure) throws Exception;    

    public CarcinogenExposure create(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactorData);

    public void update(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactorData, CarcinogenExposure inCarcinogenExposure) throws Exception;

    public CarcinogenExposure create(AnimalModel inAnimalModel, GrowthFactorData inGrowthFactorData);

    public void update(AnimalModel inAnimalModel, GrowthFactorData inGrowthFactorData, CarcinogenExposure inCarcinogenExposure) throws Exception;
    
    
    public CarcinogenExposure create(AnimalModel inAnimalModel, AntibodyData inAntibodyData);

    public void update(AnimalModel inAnimalModel, AntibodyData inAntibodyData, CarcinogenExposure inCarcinogenExposure) throws Exception;
    
    
    public CarcinogenExposure create(AnimalModel inAnimalModel, BacteriaData inBacteriaData);

    public void update(AnimalModel inAnimalModel, BacteriaData inBacteriaData, CarcinogenExposure inCarcinogenExposure) throws Exception;
    
    
    public CarcinogenExposure create(AnimalModel inAnimalModel, PlasmidData inPlasmidData);

    public void update(AnimalModel inAnimalModel, PlasmidData inPlasmidData, CarcinogenExposure inCarcinogenExposure) throws Exception;

    
    public CarcinogenExposure create(AnimalModel inAnimalModel, SignalingMoleculeData inSignalingMoleculeData);

    public void update(AnimalModel inAnimalModel, SignalingMoleculeData inSignalingMoleculeData, CarcinogenExposure inCarcinogenExposure) throws Exception;

    
    public CarcinogenExposure create(AnimalModel inAnimalModel, TransposonData inTransposonData);

    public void update(AnimalModel inAnimalModel, TransposonData inTransposonData, CarcinogenExposure inCarcinogenExposure) throws Exception;

    


}
