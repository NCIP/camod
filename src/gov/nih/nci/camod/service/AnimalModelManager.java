/**
 * 
 * $Id: AnimalModelManager.java,v 1.19 2005-10-10 14:06:44 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.18  2005/10/06 20:41:38  schroedn
 * InducedMutation, TargetedMutation, GenomicSegment changes
 *
 * Revision 1.17  2005/10/06 19:29:50  pandyas
 * modified for Therapy screen
 *
 * Revision 1.16  2005/10/06 13:36:10  georgeda
 * Changed ModelCharacteristics interface to be consistent w/ the rest of the interfaces
 *
 * Revision 1.15  2005/10/04 20:10:16  schroedn
 * Added Spontaneous Mutation, InducedMutation, Histopathology, TargetedModification and GenomicSegment
 *
 * Revision 1.14  2005/10/03 13:51:14  georgeda
 * Search changes
 *
 * Revision 1.13  2005/09/30 18:57:35  pandyas
 * added for cell line
 *
 * Revision 1.12  2005/09/28 21:19:50  georgeda
 * Finished up converting to new manager
 *
 * Revision 1.11  2005/09/28 15:12:30  schroedn
 * Added GeneDelivery and Xenograft/Transplant, businass logic in Managers
 *
 * Revision 1.10  2005/09/28 14:11:53  schroedn
 * Added saveXenograft and saveGeneDelivery
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.18  2005/10/06 20:41:38  schroedn
 * InducedMutation, TargetedMutation, GenomicSegment changes
 *
 * Revision 1.17  2005/10/06 19:29:50  pandyas
 * modified for Therapy screen
 *
 * Revision 1.16  2005/10/06 13:36:10  georgeda
 * Changed ModelCharacteristics interface to be consistent w/ the rest of the interfaces
 *
 * Revision 1.15  2005/10/04 20:10:16  schroedn
 * Added Spontaneous Mutation, InducedMutation, Histopathology, TargetedModification and GenomicSegment
 *
 * Revision 1.14  2005/10/03 13:51:14  georgeda
 * Search changes
 *
 * Revision 1.13  2005/09/30 18:57:35  pandyas
 * added for cell line
 *
 * Revision 1.12  2005/09/28 21:19:50  georgeda
 * Finished up converting to new manager
 *
 * Revision 1.9  2005/09/28 12:46:12  georgeda
 * Cleanup of animal manager
 *
 * Revision 1.8  2005/09/27 19:17:15  georgeda
 * Refactor of CI managers
 *
 * Revision 1.7  2005/09/27 16:36:43  georgeda
 * Added ChemicalDrug screens
 * Revision 1.6  2005/09/23 14:54:58  georgeda
 * Made SexDistribution a reference table
 *
 * Revision 1.5  2005/09/16 15:52:54  georgeda
 * Changes due to manager re-write
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.webapp.form.*;

import java.util.List;

/**
 * Interface for the AnimalModelManager class. See implementing classes for
 * details.
 */
public interface AnimalModelManager {

    public List getAll() throws Exception;

    public List getAllByUser(String username) throws Exception;
    
    public List getAllByStateForPerson(String inState, Person inPerson) throws Exception;

    public AnimalModel get(String id) throws Exception;

    public void save(AnimalModel animalModel) throws Exception;

    public void updateAndAddLog(AnimalModel inAnimalModel, Log inLog) throws Exception;

    public void update(ModelCharacteristicsData inModelCharacteristics, AnimalModel inAnimalModel) throws Exception;

    public AnimalModel create(ModelCharacteristicsData inModelCharacteristics, String inUsername) throws Exception;

    public void remove(String id) throws Exception;

    public List search(SearchData inSearchData) throws Exception;

    public void addXenograft(AnimalModel inAnimalModel, XenograftData inXenograftData) throws Exception;

    public void addGeneDelivery(AnimalModel inAnimalModel, GeneDeliveryData inGeneDeliveryData) throws Exception;

    public void addTherapy(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrugData) throws Exception;

    public void addTherapy(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactorData)
            throws Exception;

    public void addTherapy(AnimalModel inAnimalModel, RadiationData inRadiationData) throws Exception;

    public void addTherapy(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatmentData) throws Exception;

    public void addTherapy(AnimalModel inAnimalModel, GrowthFactorData inGrowthFactorData) throws Exception;

    public void addTherapy(AnimalModel inAnimalModel, HormoneData inHormoneData) throws Exception;

    public void addTherapy(AnimalModel inAnimalModel, NutritionalFactorData inNutritionalFactorData) throws Exception;

    public void addTherapy(AnimalModel inAnimalModel, SurgeryData inSurgeryData) throws Exception;
    
    public void addCellLine(AnimalModel inAnimalModel, CellLineData inCellLineData) throws Exception;    

    public void addGeneticDescription(AnimalModel inAnimalModel, SpontaneousMutationData inSpontaneousMutationData) throws Exception;
    
    public void addGeneticDescription(AnimalModel inAnimalModel, InducedMutationData inInducedMutationData) throws Exception;
    
    public void addGeneticDescription(AnimalModel inAnimalModel, TargetedModificationData inTargetedModificationData) throws Exception;
    
    public void addGeneticDescription(AnimalModel inAnimalModel, GenomicSegmentData inGenomicSegmentData) throws Exception;
    
    public void addTherapy(AnimalModel inAnimalModel, TherapyData inTherapyData) throws Exception; 
    
}
