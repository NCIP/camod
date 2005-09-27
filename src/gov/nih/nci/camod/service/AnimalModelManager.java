/**
 * 
 * $Id: AnimalModelManager.java,v 1.8 2005-09-27 19:17:15 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2005/09/27 16:36:43  georgeda
 * Added ChemicalDrug screens
 *
 * Revision 1.6  2005/09/23 14:54:58  georgeda
 * Made SexDistribution a reference table
 *
 * Revision 1.5  2005/09/16 15:52:54  georgeda
 * Changes due to manager re-write
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Log;
import gov.nih.nci.camod.webapp.form.*;

import java.util.List;

/**
 * Interface for the AnimalModelManager class. See implementing classes for
 * details.
 */
public interface AnimalModelManager {

    public List getAll() throws Exception;

    public List getAllByUser(String username) throws Exception;

    public List getAllByState(String inState) throws Exception;

    public AnimalModel get(String id) throws Exception;

    public void save(AnimalModel animalModel) throws Exception;

    public void updateAndAddLog(AnimalModel inAnimalModel, Log inLog) throws Exception;

    public void update(ModelCharacteristics inModelCharacteristics, AnimalModel inAnimalModel) throws Exception;

    public AnimalModel create(ModelCharacteristics inModelCharacteristics, String inUsername) throws Exception;

    public void remove(String id) throws Exception;

    public List search() throws Exception;
    
    public void updateTherapy(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrug, String inTherapyId) throws Exception;
    
    public void addTherapy(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrug) throws Exception;
    
    public void updateTherapy(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactor, String inTherapyId) throws Exception;
    
    public void addTherapy(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactor) throws Exception;
    
    public void updateTherapy(AnimalModel inAnimalModel, RadiationData inRadiation, String inTherapyId) throws Exception;
    
    public void addTherapy(AnimalModel inAnimalModel, RadiationData inRadiation) throws Exception;
    
    public void updateTherapy(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatment, String inTherapyId) throws Exception;
    
    public void addTherapy(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatment) throws Exception;
    
    
}
