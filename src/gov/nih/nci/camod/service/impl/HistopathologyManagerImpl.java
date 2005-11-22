/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyManagerImpl.java,v 1.7 2005-11-22 16:35:43 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/11/18 22:50:02  georgeda
 * Defect #184.  Cleanup editing of old models
 *
 * Revision 1.5  2005/11/09 00:17:16  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.4  2005/11/07 19:15:17  pandyas
 * modified for clinical marker screen
 *
 * Revision 1.3  2005/11/04 14:44:25  georgeda
 * Cleaned up histopathology/assoc metastasis
 *
 * Revision 1.2  2005/11/03 21:47:48  georgeda
 * Changed EVS api
 *
 * Revision 1.1  2005/11/03 18:54:29  pandyas
 * Modified for histopathology screens
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.HistopathologyManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.webapp.form.*;
import java.util.List;

public class HistopathologyManagerImpl extends BaseManager implements HistopathologyManager {

    public List getAll() throws Exception {
        log.trace("In HistopathologyManagerImpl.getAll");
        return super.getAll(Histopathology.class);
    }

    public Histopathology get(String id) throws Exception {
        log.trace("In HistopathologyManagerImpl.get");
        return (Histopathology) super.get(id, Histopathology.class);
    }

    public void save(Histopathology histopathology) throws Exception {
        log.trace("In HistopathologyManagerImpl.save");
        super.save(histopathology);
    }

    public void remove(String id, AnimalModel inAnimalModel) throws Exception {
        log.trace("In HistopathologyManagerImpl.save");

        Histopathology theHistopathology = get(id);

        inAnimalModel.getHistopathologyCollection().remove(theHistopathology);
        super.save(inAnimalModel);
    }

    public void removeAssociatedMetastasis(String id, Histopathology inHistopathology) throws Exception {

        log.info("Entering HistopathologyManagerImpl.createMetastasis");

        Histopathology theMetastasis = get(id);

        inHistopathology.getMetastatisCollection().remove(theMetastasis);
        save(inHistopathology);

        log.info("Exiting HistopathologyManagerImpl.createMetastasis");
    }

    public Histopathology createHistopathology(HistopathologyData inHistopathologyData) throws Exception {

        log.info("Entering HistopathologyManagerImpl.createHistopathology");

        Histopathology theHistopathology = new Histopathology();
        populateHistopathology(inHistopathologyData, theHistopathology);

        log.info("Exiting HistopathologyManagerImpl.createHistopathology");

        return theHistopathology;
    }

    public void updateHistopathology(HistopathologyData inHistopathologyData, Histopathology inHistopathology)
            throws Exception {

        log.info("Entering HistopathologyManagerImpl.updateHistopathology");
        log.info("Updating HistopathologyData: " + inHistopathology.getId());

        // Populate w/ the new values and save
        populateHistopathology(inHistopathologyData, inHistopathology);
        save(inHistopathology);

        log.info("Exiting HistopathologyManagerImpl.updateHistopathology");
    }

    private void populateHistopathology(HistopathologyData inHistopathologyData, Histopathology inHistopathology) {

        log.info("<HistopathologyManagerImpl> Entering populateHistopathology");

        /*
         * Add a Organ to AnimalModel with correct IDs, conceptCode
         */
        log.info("inHistopathology.getOrgan()" + inHistopathology.getOrgan());

        // new submission - organ will be null
        if (inHistopathology.getOrgan() == null) {

            log.info("Creating new Organ object");
            inHistopathology.setOrgan(new Organ());
        }

        String newConceptCode = inHistopathologyData.getOrganTissueCode();
        log.info("newConceptCode: " + newConceptCode);

        String oldConceptCode = inHistopathology.getOrgan().getConceptCode();
        log.info("oldConceptCode: " + oldConceptCode);

        if (!newConceptCode.equals(oldConceptCode)) {
            log.info("Organ is new or was modified so retrieve attributes");
            /*
             * Always get/store organ name through the concept code - never deal
             * with converting name back and forth
             */
            String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inHistopathologyData.getOrganTissueCode());

            log.info("preferedOrganName: " + preferedOrganName);
            inHistopathology.getOrgan().setName(preferedOrganName);

            log.info("populateMetastasis - getOrgan().setConceptCode - OrganTissueCode: "
                    + inHistopathologyData.getOrganTissueCode());
            inHistopathology.getOrgan().setConceptCode(inHistopathologyData.getOrganTissueCode());
        }

        /* Add histopathology to Organ if it's not already there */
        if (!inHistopathology.getOrgan().getHistopathologyCollection().contains(inHistopathology)) {
            inHistopathology.getOrgan().addHistopathology(inHistopathology);
            log.info("Added histopathology to Organ");
        }

        Disease theDisease = null;
        if (inHistopathology.getDiseaseCollection().size() > 0) {

            theDisease = (Disease) inHistopathology.getDiseaseCollection().get(0);

            /*
             * Add a Disease to AnimalModel with correct IDs, conceptCode
             */
            String oldDiseaseConceptCode = theDisease.getConceptCode();
            String newDiseaseConceptCode = inHistopathologyData.getDiagnosisCode();

            // It's a new concept code, or the concept code is a "catch-all" for any user entered data
            if (!newDiseaseConceptCode.equals(oldDiseaseConceptCode) || inHistopathologyData.getDiagnosisCode().indexOf("000000") != -1) {

                log.info("Creating new Disease object");
                theDisease = (Disease) inHistopathology.getDiseaseCollection().get(0);
                theDisease.setConceptCode(inHistopathologyData.getDiagnosisCode());

                // Hack to handle user entered tumor classifications
                if (inHistopathologyData.getDiagnosisCode().indexOf("000000") != -1) {
                    theDisease.setName(inHistopathologyData.getDiagnosisName());
                } else {
                    theDisease.setName(EvsTreeUtil.getEVSPreferedDescription(inHistopathologyData.getDiagnosisCode()));
                }
               
            }

        } else {
            theDisease = new Disease();
            theDisease.setConceptCode(inHistopathologyData.getDiagnosisCode());
            
            // Hack to handle user entered tumor classifications
            if (inHistopathologyData.getDiagnosisCode().indexOf("000000") != -1) {
                theDisease.setName(inHistopathologyData.getDiagnosisName());
            } else {
                theDisease.setName(EvsTreeUtil.getEVSPreferedDescription(inHistopathologyData.getDiagnosisCode()));
            }
            inHistopathology.addDisease(theDisease);
        }

        log.info("Saving: Histopathology object attributes ");
        inHistopathology.setComments(inHistopathologyData.getComments());
        inHistopathology.setGrossDescription(inHistopathologyData.getGrossDescription());

        if (inHistopathologyData.getTumorIncidenceRate() != null
                && inHistopathologyData.getTumorIncidenceRate().length() > 0) {
            inHistopathology.setTumorIncidenceRate(Float.valueOf(inHistopathologyData.getTumorIncidenceRate()));
        }

        inHistopathology.setSurvivalInfo(inHistopathologyData.getSurvivalInfo());
        inHistopathology.setMicroscopicDescription(inHistopathologyData.getMicroscopicDescription());
        inHistopathology.setComparativeData(inHistopathologyData.getComparativeData());
        log.info("inHistopathologyData.getComparativeData():  " + inHistopathologyData.getComparativeData());

        if (inHistopathologyData.getWeightOfTumor() != null && inHistopathologyData.getWeightOfTumor().length() > 0) {
            inHistopathology.setWeightOfTumor(Float.valueOf(inHistopathologyData.getWeightOfTumor()));
        }
        if (inHistopathologyData.getVolumeOfTumor() != null && inHistopathologyData.getVolumeOfTumor().length() > 0) {
            inHistopathology.setVolumeOfTumor(Float.valueOf(inHistopathologyData.getVolumeOfTumor()));
        }

        // Histopathology attributes that need units appended
        inHistopathology.setAgeOfOnset(inHistopathologyData.getAgeOfOnset() + " " + inHistopathologyData.getAgeUnit());

        // No genetic alteration and we have data for it
        if (inHistopathology.getGeneticAlteration() == null && inHistopathologyData.getObservation() != null
                && inHistopathologyData.getObservation().length() > 0) {
            inHistopathology.setGeneticAlteration(new GeneticAlteration());
            log.info("Saving: inHistopathology.getGeneticAlteration() attributes ");

            inHistopathology.getGeneticAlteration().setObservation(inHistopathologyData.getObservation());
            inHistopathology.getGeneticAlteration().setMethodOfObservation(
                    inHistopathologyData.getMethodOfObservation());
        }

        // Already have a genetic alteration. Either blank it out or update it
        else {
            if (inHistopathologyData.getObservation() != null && inHistopathologyData.getObservation().length() > 0) {
                inHistopathology.getGeneticAlteration().setObservation(inHistopathologyData.getObservation());
                inHistopathology.getGeneticAlteration().setMethodOfObservation(
                        inHistopathologyData.getMethodOfObservation());
            } else {
                inHistopathology.setGeneticAlteration(null);
            }
        }

        log.info("<HistopathologyManagerImpl> Exiting populateHistopathology");
    }

    public void createAssociatedMetastasis(AssociatedMetastasisData inAssociatedMetastasisData,
            Histopathology inHistopathology) throws Exception {

        log.info("Entering HistopathologyManagerImpl.createMetastasis");

        Histopathology theAssociatedMetastasis = new Histopathology();
        populateHistopathology(inAssociatedMetastasisData, theAssociatedMetastasis);

        inHistopathology.addHistopathology(theAssociatedMetastasis);

        log.info("Exiting HistopathologyManagerImpl.createMetastasis");
    }

    public void updateAssociatedMetastasis(AssociatedMetastasisData inAssociatedMetastasisData,
            Histopathology inAssociatedMetastasis) throws Exception {

        log.info("Entering HistopathologyManagerImpl.updateAssociatedMetastasis");
        log.info("Updating HistopathologyData: " + inAssociatedMetastasis.getId());

        // Populate w/ the new values and save
        populateHistopathology(inAssociatedMetastasisData, inAssociatedMetastasis);
        save(inAssociatedMetastasis);

        log.info("Exiting HistopathologyManagerImpl.updateAssociatedMetastasis");
    }

}
