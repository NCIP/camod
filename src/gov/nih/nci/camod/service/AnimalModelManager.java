/**
 * 
 * $Id: AnimalModelManager.java,v 1.10 2005-09-28 14:11:53 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
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
import gov.nih.nci.camod.domain.GeneDelivery;
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
    
    public void saveXenograft( XenograftForm inXenograftForm, Xenograft inXenograft, AnimalModel inAnimalModel ) throws Exception;
    
    public void saveGeneDelivery( GeneDeliveryForm inGeneDeliveryForm, GeneDelivery inGeneDelivery, AnimalModel inAnimalModel ) throws Exception;
    
    public void addTherapy(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrug) throws Exception;
    
    public void addTherapy(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactor) throws Exception;
    
    public void addTherapy(AnimalModel inAnimalModel, RadiationData inRadiation) throws Exception;
       
    public void addTherapy(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatment) throws Exception;
    
    
}
