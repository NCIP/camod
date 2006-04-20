/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyManagerImpl.java,v 1.10 2006-04-20 19:18:53 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2006/04/17 19:11:06  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.8  2006/01/18 14:24:24  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.7  2005/11/22 16:35:43  georgeda
 * Defect #107, cleanup of disease tree
 *
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

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Disease;
import gov.nih.nci.camod.domain.GeneticAlteration;
import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.HistopathologyManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.webapp.form.AssociatedMetastasisData;
import gov.nih.nci.camod.webapp.form.HistopathologyData;

import java.util.List;

public class HistopathologyManagerImpl extends BaseManager implements HistopathologyManager
{
    public List getAll() throws Exception
    {
        log.trace("In HistopathologyManagerImpl.getAll");
        return super.getAll(Histopathology.class);
    }

    public Histopathology get(String id) throws Exception
    {
        log.trace("In HistopathologyManagerImpl.get");
        return (Histopathology) super.get(id, Histopathology.class);
    }

    public void save(Histopathology histopathology) throws Exception
    {
        log.trace("In HistopathologyManagerImpl.save");
        super.save(histopathology);
    }

    public void remove(String id,
                       AnimalModel inAnimalModel) throws Exception
    {
        log.trace("In HistopathologyManagerImpl.save");

        Histopathology theHistopathology = get(id);

        inAnimalModel.getHistopathologyCollection().remove(theHistopathology);
        super.save(inAnimalModel);
    }

    public void removeAssociatedMetastasis(String id,
                                           Histopathology inHistopathology) throws Exception
    {
        log.info("Entering HistopathologyManagerImpl.createMetastasis");

        Histopathology theMetastasis = get(id);

        inHistopathology.getMetastasisCollection().remove(theMetastasis);
        save(inHistopathology);

        log.info("Exiting HistopathologyManagerImpl.createMetastasis");
    }

    public Histopathology createHistopathology(HistopathologyData inHistopathologyData) throws Exception
    {
        log.info("Entering HistopathologyManagerImpl.createHistopathology");

        Histopathology theHistopathology = new Histopathology();
        populateHistopathology(inHistopathologyData, theHistopathology);

        log.info("Exiting HistopathologyManagerImpl.createHistopathology");

        return theHistopathology;
    }

    public void updateHistopathology(HistopathologyData inHistopathologyData,
                                     Histopathology inHistopathology) throws Exception
    {
        log.info("Entering HistopathologyManagerImpl.updateHistopathology");
        log.info("Updating HistopathologyData: " + inHistopathology.getId());

        // Populate w/ the new values and save
        populateHistopathology(inHistopathologyData, inHistopathology);
        
        save(inHistopathology);

        log.info("Exiting HistopathologyManagerImpl.updateHistopathology");
    }

    private void populateHistopathology(HistopathologyData inHistopathologyData,
                                        Histopathology inHistopathology) throws Exception
    {
        log.info("<HistopathologyManagerImpl> Entering populateHistopathology");

        log.info("inHistopathology.getOrgan()" + inHistopathology.getOrgan());

        // every submission - lookup organ or create one new
        Organ theNewOrgan = OrganManagerSingleton.instance().getOrCreate(inHistopathologyData.getOrganTissueCode());

        log.info("inHistopathologyData.getOrganTissueCode():" + inHistopathologyData.getOrganTissueCode());

        //theOrgan.setName(EvsTreeUtil.getEVSPreferedDescription(inHistopathologyData.getOrganTissueCode()));
        theNewOrgan.setName(inHistopathologyData.getOrganTissueName());
        inHistopathology.setOrgan(theNewOrgan);


        // every submission - lookup disease or create one new
        Disease theNewDisease = DiseaseManagerSingleton.instance().getOrCreate(inHistopathologyData.getDiagnosisCode(),
                                                                               inHistopathologyData.getDiagnosisName());

        // Hack to handle user entered tumor classifications
        if (inHistopathologyData.getDiagnosisCode().indexOf("000000") != -1)
        {
            theNewDisease.setName(inHistopathologyData.getDiagnosisName());
            inHistopathology.setDisease(theNewDisease);
        }
        else
        {
            theNewDisease.setName(EvsTreeUtil.getEVSPreferedDescription(inHistopathologyData.getDiagnosisCode()));
            inHistopathology.setDisease(theNewDisease);
        }

        log.info("Saving: Histopathology object attributes ");
        inHistopathology.setComments(inHistopathologyData.getComments());
        inHistopathology.setGrossDescription(inHistopathologyData.getGrossDescription());

        if (inHistopathologyData.getTumorIncidenceRate() != null && inHistopathologyData.getTumorIncidenceRate().length() > 0)
        {
            inHistopathology.setTumorIncidenceRate(Float.valueOf(inHistopathologyData.getTumorIncidenceRate()));
        }

        inHistopathology.setSurvivalInfo(inHistopathologyData.getSurvivalInfo());
        inHistopathology.setMicroscopicDescription(inHistopathologyData.getMicroscopicDescription());
        inHistopathology.setComparativeData(inHistopathologyData.getComparativeData());
        log.info("inHistopathologyData.getComparativeData():  " + inHistopathologyData.getComparativeData());

        if (inHistopathologyData.getWeightOfTumor() != null && inHistopathologyData.getWeightOfTumor().length() > 0)
        {
            inHistopathology.setWeightOfTumor(inHistopathologyData.getWeightOfTumor());
        }
        if (inHistopathologyData.getVolumeOfTumor() != null && inHistopathologyData.getVolumeOfTumor().length() > 0)
        {
            inHistopathology.setVolumeOfTumor(inHistopathologyData.getVolumeOfTumor());
        }

        // Histopathology attributes that need units 
        inHistopathology.setAgeOfOnset(inHistopathologyData.getAgeOfOnset());
        inHistopathology.setAgeOfOnsetUnit(inHistopathologyData.getAgeOfOnsetUnit());

        // No genetic alteration and we have data for it
        if (inHistopathology.getGeneticAlteration() == null && inHistopathologyData.getObservation() != null && inHistopathologyData.getObservation().length() > 0)
        {
            inHistopathology.setGeneticAlteration(new GeneticAlteration());
            log.info("Saving: inHistopathology.getGeneticAlteration() attributes ");

            inHistopathology.getGeneticAlteration().setObservation(inHistopathologyData.getObservation());
            inHistopathology.getGeneticAlteration().setMethodOfObservation(inHistopathologyData.getMethodOfObservation());
        }

        // Already have a genetic alteration. Either blank it out or update it
        else
        {
            if (inHistopathologyData.getObservation() != null && inHistopathologyData.getObservation().length() > 0)
            {
                inHistopathology.getGeneticAlteration().setObservation(inHistopathologyData.getObservation());
                inHistopathology.getGeneticAlteration().setMethodOfObservation(inHistopathologyData.getMethodOfObservation());
            }
            else
            {
                inHistopathology.setGeneticAlteration(null);
            }
        }

        log.info("<HistopathologyManagerImpl> Exiting populateHistopathology");
    }

    public void createAssociatedMetastasis(AssociatedMetastasisData inAssociatedMetastasisData,
                                           Histopathology inHistopathology) throws Exception
    {
        log.info("Entering HistopathologyManagerImpl.createAssociatedMetastasis");

        Histopathology theAssociatedMetastasis = new Histopathology();
        populateHistopathology(inAssociatedMetastasisData, theAssociatedMetastasis);

        inHistopathology.addMetastasis(theAssociatedMetastasis);
        save(inHistopathology);
        log.info("Exiting HistopathologyManagerImpl.createAssociatedMetastasis");
    }
    
    public void addAssociatedMetastasis(AnimalModel inAnimalModel,
                                        Histopathology inHistopathology,
                                        AssociatedMetastasisData inAssociatedMetastasisData) throws Exception
    {

        log.info("Entering HistopathologyManagerImpl.addAssociatedMetastasis");
        HistopathologyManagerSingleton.instance().createAssociatedMetastasis(inAssociatedMetastasisData, inHistopathology);

        log.info("Exiting HistopathologyManagerImpl.addHistopathology");
    }    

    public void updateAssociatedMetastasis(AssociatedMetastasisData inAssociatedMetastasisData,
                                           Histopathology inAssociatedMetastasis) throws Exception
    {
        log.info("Entering HistopathologyManagerImpl.updateAssociatedMetastasis");
        log.info("Updating HistopathologyData: " + inAssociatedMetastasis.getId());

        // Populate w/ the new values and save
        populateHistopathology(inAssociatedMetastasisData, inAssociatedMetastasis);
        //populateAssociatedMetastasis(inAssociatedMetastasisData, theAssociatedMetastasis);         
        save(inAssociatedMetastasis);

        log.info("Exiting HistopathologyManagerImpl.updateAssociatedMetastasis");
    }
/*   
    private void populateAssociatedMetastasis(inAssociatedMetastasisData, 
                                              theAssociatedMetastasis) throws Exception
    {
        
    }
*/
}
