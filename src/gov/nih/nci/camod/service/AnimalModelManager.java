/**
 * 
 * $Id: AnimalModelManager.java,v 1.41 2008-08-14 06:24:37 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.40  2008/01/16 18:30:30  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.39  2007/10/31 18:48:03  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.38  2007/07/31 12:03:05  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.37  2006/10/17 16:14:18  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.36  2006/05/03 20:03:10  pandyas
 * Modified to add Morpholino object data to application
 *
 * Revision 1.35  2006/04/20 19:18:05  pandyas
 * Moved save Assoc Met from AnimalModel to the Histopathology
 *
 * Revision 1.34  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.33  2005/11/14 14:17:17  georgeda
 * Cleanup
 *
 * Revision 1.32  2005/11/07 19:15:33  pandyas
 * modified for clinical marker screen
 *
 * Revision 1.31  2005/11/03 18:54:43  pandyas
 * Modified for histopathology screens
 *
 * Revision 1.30  2005/11/02 19:03:25  pandyas
 * Added e-mail functionality
 *
 * Revision 1.29  2005/10/27 17:16:25  schroedn
 * merged changes, modded addAssociatedExpression
 *
 * Revision 1.28  2005/10/27 15:29:59  georgeda
 * Cleanup
 *
 * Revision 1.27  2005/10/27 12:52:40  georgeda
 * Refactor of publication manager
 *
 * Revision 1.26  2005/10/26 20:42:13  schroedn
 * merged changes, Added AssocExpression to EngineeredTransgene submission page
 *
 * Revision 1.25  2005/10/26 20:16:07  pandyas
 * implemented model availability
 *
 * Revision 1.24  2005/10/24 21:04:03  schroedn
 * Added Image to submission
 *
 * Revision 1.23  2005/10/24 17:10:39  georgeda
 * First pass at duplicate
 *
 * Revision 1.22  2005/10/21 19:38:37  schroedn
 * Added caImage ftp capabilities for EngineeredTransgene, GenomicSegment and TargetedModification
 *
 * Revision 1.21  2005/10/21 16:07:37  pandyas
 * implementation of animal availability
 *
 * Revision 1.20  2005/10/11 20:52:53  schroedn
 * EngineeredTransgene and GenomicSegment edit/save works, not image
 *
 * EngineeredTransgene - 'Other' Species not working
 *
 * Revision 1.19  2005/10/10 14:06:44  georgeda
 * Performance improvement
 *
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
 * Revision 1.40  2008/01/16 18:30:30  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.39  2007/10/31 18:48:03  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.38  2007/07/31 12:03:05  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.37  2006/10/17 16:14:18  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.36  2006/05/03 20:03:10  pandyas
 * Modified to add Morpholino object data to application
 *
 * Revision 1.35  2006/04/20 19:18:05  pandyas
 * Moved save Assoc Met from AnimalModel to the Histopathology
 *
 * Revision 1.34  2006/04/17 19:13:16  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.33  2005/11/14 14:17:17  georgeda
 * Cleanup
 *
 * Revision 1.32  2005/11/07 19:15:33  pandyas
 * modified for clinical marker screen
 *
 * Revision 1.31  2005/11/03 18:54:43  pandyas
 * Modified for histopathology screens
 *
 * Revision 1.30  2005/11/02 19:03:25  pandyas
 * Added e-mail functionality
 *
 * Revision 1.29  2005/10/27 17:16:25  schroedn
 * merged changes, modded addAssociatedExpression
 *
 * Revision 1.28  2005/10/27 15:29:59  georgeda
 * Cleanup
 *
 * Revision 1.27  2005/10/27 12:52:40  georgeda
 * Refactor of publication manager
 *
 * Revision 1.26  2005/10/26 20:42:13  schroedn
 * merged changes, Added AssocExpression to EngineeredTransgene submission page
 *
 * Revision 1.25  2005/10/26 20:16:07  pandyas
 * implemented model availability
 *
 * Revision 1.24  2005/10/24 21:04:03  schroedn
 * Added Image to submission
 *
 * Revision 1.23  2005/10/24 17:10:39  georgeda
 * First pass at duplicate
 *
 * Revision 1.22  2005/10/21 19:38:37  schroedn
 * Added caImage ftp capabilities for EngineeredTransgene, GenomicSegment and TargetedModification
 *
 * Revision 1.21  2005/10/21 16:07:37  pandyas
 * implementation of animal availability
 *
 * Revision 1.20  2005/10/11 20:52:53  schroedn
 * EngineeredTransgene and GenomicSegment edit/save works, not image
 *
 * EngineeredTransgene - 'Other' Species not working
 *
 * Revision 1.19  2005/10/10 14:06:44  georgeda
 * Performance improvement
 *
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

import javax.servlet.http.HttpServletRequest;

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
    
    public AnimalModel duplicate(AnimalModel animalModel) throws Exception;

    public void updateAndAddLog(AnimalModel inAnimalModel, Log inLog) throws Exception;

    public void update(ModelCharacteristicsData inModelCharacteristics, AnimalModel inAnimalModel) throws Exception;

    public AnimalModel create(ModelCharacteristicsData inModelCharacteristics, String inUsername) throws Exception;

    public void remove(String id) throws Exception;

    public List search(SearchData inSearchData) throws Exception;
    
    public List searchAdmin(CurationAssignmentData inCurationAssignment) throws Exception;    

    public void addTransplant(AnimalModel inAnimalModel, TransplantData inTransplantData) throws Exception;

    public void addGeneDelivery(AnimalModel inAnimalModel, GeneDeliveryData inGeneDeliveryData) throws Exception;

    public void addCarcinogenExposure(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrugData) throws Exception;

    public void addCarcinogenExposure(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactorData)
            throws Exception;

    public void addCarcinogenExposure(AnimalModel inAnimalModel, RadiationData inRadiationData) throws Exception;

    public void addCarcinogenExposure(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatmentData) throws Exception;

    public void addCarcinogenExposure(AnimalModel inAnimalModel, GrowthFactorData inGrowthFactorData) throws Exception;

    public void addCarcinogenExposure(AnimalModel inAnimalModel, HormoneData inHormoneData) throws Exception;

    public void addCarcinogenExposure(AnimalModel inAnimalModel, NutritionalFactorData inNutritionalFactorData) throws Exception;

    public void addCarcinogenExposure(AnimalModel inAnimalModel, SurgeryData inSurgeryData) throws Exception;
    
    public void addCellLine(AnimalModel inAnimalModel, CellLineData inCellLineData) throws Exception;    

    public void addMicroArrayData( AnimalModel inAnimalModel, MicroArrayDataData inMicroArrayData ) throws Exception;
    
    public void addGeneticDescription(AnimalModel inAnimalModel, SpontaneousMutationData inSpontaneousMutationData) throws Exception;
    
    public void addGeneticDescription(AnimalModel inAnimalModel, InducedMutationData inInducedMutationData) throws Exception;
    
    public void addGeneticDescription(AnimalModel inAnimalModel, TargetedModificationData inTargetedModificationData, HttpServletRequest request) throws Exception;
    
    public void addGeneticDescription(AnimalModel inAnimalModel, GenomicSegmentData inGenomicSegmentData, HttpServletRequest request) throws Exception;
    
    public void addGeneticDescription(AnimalModel inAnimalModel, EngineeredTransgeneData inEngineeredTransgeneData, HttpServletRequest request) throws Exception;

    public void addImage(AnimalModel inAnimalModel, ImageData inImageData, String inPath) throws Exception;
    
    public void addTherapy(AnimalModel inAnimalModel, TherapyData inTherapyData) throws Exception; 
    
    public void addAvailability(AnimalModel inAnimalModel, AvailabilityData inAvailabilityData) throws Exception;
    
    public void addInvestigatorAvailability(AnimalModel inAnimalModel, AvailabilityData inAvailabilityData) throws Exception;     
    
    public void addAssociatedExpression(AnimalModel inAnimalModel, EngineeredGene inEngineeredGene, AssociatedExpressionData inAssociatedExpressionData ) throws Exception;

    public void addPublication( AnimalModel inAnimalModel, PublicationData inPublicationData) throws Exception;
    
    public void addHistopathology(AnimalModel inAnimalModel, HistopathologyData inHistopathologyData) throws Exception;
    
    public void addClinicalMarker(AnimalModel inAnimalModel, Histopathology inHistopathology, ClinicalMarkerData inClinicalMarkerData) throws Exception;
    
    public void addTransientInterference(AnimalModel inAnimalModel, TransientInterferenceData inTransientInterferenceData) throws Exception;    
    
}
