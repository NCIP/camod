/**
 * @author dgeorge
 * 
 * $Id: TherapyManager.java,v 1.8 2005-11-09 00:17:06 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2005/11/02 19:03:25  pandyas
 * Added e-mail functionality
 *
 * Revision 1.6  2005/10/06 19:28:14  pandyas
 * modified for Therapy screen
 *
 * Revision 1.5  2005/09/28 21:19:49  georgeda
 * Finished up converting to new manager
 *
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.webapp.form.*;

/**
 * Interface for managing the therapy objects
 */
public interface TherapyManager {

    public Therapy get(String id) throws Exception;

    public void save(Therapy therapy) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;

    /////////////////////////////////////////////////
    // The interface specific create/updates
    /////////////////////////////////////////////////
    public Therapy create(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrugData);

    public void update(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrugData, Therapy inTherapy) throws Exception;

    public Therapy create(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactorData);

    public void update(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactorData, Therapy inTherapy) throws Exception;

    public Therapy create(AnimalModel inAnimalModel, RadiationData inRadiationData);

    public void update(AnimalModel inAnimalModel, RadiationData inRadiationData, Therapy inTherapy) throws Exception;

    public Therapy create(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatmentData);

    public void update(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatmentData, Therapy inTherapy) throws Exception;

    public Therapy create(AnimalModel inAnimalModel, GrowthFactorData inGrowthFactorData);

    public void update(AnimalModel inAnimalModel, GrowthFactorData inGrowthFactorData, Therapy inTherapy) throws Exception;

    public Therapy create(AnimalModel inAnimalModel, HormoneData inHormoneData);

    public void update(AnimalModel inAnimalModel, HormoneData inHormoneData, Therapy inTherapy) throws Exception;

    public Therapy create(AnimalModel inAnimalModel, NutritionalFactorData inNutritionalFactorData);

    public void update(AnimalModel inAnimalModel, NutritionalFactorData inNutritionalFactorData, Therapy inTherapy) throws Exception;

    public Therapy create(AnimalModel inAnimalModel, SurgeryData inSurgeryData);

    public void update(AnimalModel inAnimalModel, SurgeryData inSurgeryData, Therapy inTherapy) throws Exception;
    
    public Therapy create(AnimalModel inAnimalModel, TherapyData inTherapyData) throws Exception;
    
    public void update(AnimalModel inAnimalModel, TherapyData inTherapyData, Therapy inTherapy) throws Exception;    
}
