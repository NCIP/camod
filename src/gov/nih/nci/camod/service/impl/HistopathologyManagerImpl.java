/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyManagerImpl.java,v 1.4 2005-11-07 19:15:17 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
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

    public void remove(String id) throws Exception {
        log.trace("In HistopathologyManagerImpl.save");
        super.remove(id, Histopathology.class);
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
        System.out.println("inHistopathology.getOrgan()" + inHistopathology.getOrgan());

        // new submission - organ will be null
        if (inHistopathology.getOrgan() == null) {

            System.out.println("Creating new Organ object");
            inHistopathology.setOrgan(new Organ());
        }

        String newConceptCode = inHistopathologyData.getOrganTissueCode();
        System.out.println("newConceptCode: " + newConceptCode);

        String oldConceptCode = inHistopathology.getOrgan().getConceptCode();
        System.out.println("oldConceptCode: " + oldConceptCode);

        if (!newConceptCode.equals(oldConceptCode)) {
            System.out.println("Organ is new or was modified so retrieve attributes");
            /*
             * Always get/store organ name through the concept code - never deal
             * with converting name back and forth
             */
            String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inHistopathologyData.getOrganTissueCode());

            System.out.println("preferedOrganName: " + preferedOrganName);
            inHistopathology.getOrgan().setName(preferedOrganName);

            System.out.println("populateMetastasis - getOrgan().setConceptCode - OrganTissueCode: "
                    + inHistopathologyData.getOrganTissueCode());
            inHistopathology.getOrgan().setConceptCode(inHistopathologyData.getOrganTissueCode());
        }

        /* Add histopathology to Organ if it's not already there */
        if (!inHistopathology.getOrgan().getHistopathologyCollection().contains(inHistopathology)) {
            inHistopathology.getOrgan().addHistopathology(inHistopathology);
            System.out.println("Added histopathology to Organ");
        }
        
        /*
         * Add a Disease to AnimalModel with correct IDs, conceptCode
         */
        Disease inDisease = null;
        if (inHistopathology.getDiseaseCollection().size() > 0) {
            System.out.println("Creating new Disease object");
            inDisease = (Disease) inHistopathology.getDiseaseCollection().get(0);
            inDisease.setConceptCode(inHistopathologyData.getDiagnosisCode());
            inDisease.setName(EvsTreeUtil.getEVSPreferedDescription(inHistopathologyData.getDiagnosisCode()));

        } else {
            inDisease = new Disease();
            inDisease.setConceptCode(inHistopathologyData.getDiagnosisCode());
            inDisease.setName(EvsTreeUtil.getEVSPreferedDescription(inHistopathologyData.getDiagnosisCode()));
            inHistopathology.addDisease(inDisease);
        }

        System.out.println("Saving: Histopathology object attributes ");
        inHistopathology.setComments(inHistopathologyData.getComments());
        inHistopathology.setGrossDescription(inHistopathologyData.getGrossDescription());

        if (inHistopathologyData.getTumorIncidenceRate() != null
                && inHistopathologyData.getTumorIncidenceRate().length() > 0) {
            inHistopathology.setTumorIncidenceRate(Float.valueOf(inHistopathologyData.getTumorIncidenceRate()));
        }

        inHistopathology.setSurvivalInfo(inHistopathologyData.getSurvivalInfo());
        inHistopathology.setMicroscopicDescription(inHistopathologyData.getMicroscopicDescription());
        inHistopathology.setComparativeData(inHistopathologyData.getComparativeData());
        System.out.println("inHistopathologyData.getComparativeData():  " + inHistopathologyData.getComparativeData());

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
            System.out.println("Saving: inHistopathology.getGeneticAlteration() attributes ");

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
