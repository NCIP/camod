/**
 * @author dgeorge
 * 
 * $Id: TherapyManager.java,v 1.6 2005-10-06 19:28:14 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/09/28 21:19:49  georgeda
 * Finished up converting to new manager
 *
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.webapp.form.*;

/**
 * Interface for managing the therapy objects
 */
public interface TherapyManager {

    public Therapy get(String id) throws Exception;

    public void save(Therapy therapy) throws Exception;

    public void remove(String id) throws Exception;

    /////////////////////////////////////////////////
    // The interface specific create/updates
    /////////////////////////////////////////////////
    public Therapy create(ChemicalDrugData inChemicalDrugData);

    public void update(ChemicalDrugData inChemicalDrugData, Therapy inTherapy) throws Exception;

    public Therapy create(EnvironmentalFactorData inEnvironmentalFactorData);

    public void update(EnvironmentalFactorData inEnvironmentalFactorData, Therapy inTherapy) throws Exception;

    public Therapy create(RadiationData inRadiationData);

    public void update(RadiationData inRadiationData, Therapy inTherapy) throws Exception;

    public Therapy create(ViralTreatmentData inViralTreatmentData);

    public void update(ViralTreatmentData inViralTreatmentData, Therapy inTherapy) throws Exception;

    public Therapy create(GrowthFactorData inGrowthFactorData);

    public void update(GrowthFactorData inGrowthFactorData, Therapy inTherapy) throws Exception;

    public Therapy create(HormoneData inHormoneData);

    public void update(HormoneData inHormoneData, Therapy inTherapy) throws Exception;

    public Therapy create(NutritionalFactorData inNutritionalFactorData);

    public void update(NutritionalFactorData inNutritionalFactorData, Therapy inTherapy) throws Exception;

    public Therapy create(SurgeryData inSurgeryData);

    public void update(SurgeryData inSurgeryData, Therapy inTherapy) throws Exception;
    
    public Therapy create(TherapyData inTherapyData) throws Exception;
    
    public void update(TherapyData inTherapyData, Therapy inTherapy) throws Exception;    
}
