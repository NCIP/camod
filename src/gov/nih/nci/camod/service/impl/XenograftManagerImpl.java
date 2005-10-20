/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.XenograftManager;
import gov.nih.nci.camod.webapp.form.XenograftData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class XenograftManagerImpl extends BaseManager implements
        XenograftManager {

    public List getAll() throws Exception {
        log.trace("In XenograftManagerImpl.getAll");
        return super.getAll(Xenograft.class);
    }

    public Xenograft get(String id) throws Exception {
        log.trace("In XenograftManagerImpl.get");
        return (Xenograft) super.get(id, Xenograft.class);
    }

    public void save(Xenograft xenograft) throws Exception {
        log.trace("In XenograftManagerImpl.save");
        super.save(xenograft);
    }

    public void remove(String id) throws Exception {
        log.trace("In XenograftManagerImpl.remove");
        super.remove(id, Xenograft.class);
    }

    public Xenograft create(XenograftData inXenograftData,
                            AnimalModel inAnimalModel) throws Exception {

        log.debug("Entering XenograftManagerImpl.create");

        Xenograft theXenograft = new Xenograft();

        log.trace("Exiting XenograftManagerImpl.create");
        populateXenograft(inXenograftData, theXenograft, inAnimalModel);

        return theXenograft;
    }

    public void update(XenograftData inXenograftData, Xenograft inXenograft,
                       AnimalModel inAnimalModel) throws Exception {

        log.debug("Entering XenograftManagerImpl.update");
        log.debug("Updating XenograftData: " + inXenograft.getId());

        // Populate w/ the new values and save
        populateXenograft(inXenograftData, inXenograft, inAnimalModel);
        save(inXenograft);

        log.debug("Exiting XenograftManagerImpl.update");
    }

private void populateXenograft(XenograftData inXenograftData, Xenograft inXenograft, AnimalModel inAnimalModel)
            throws Exception {

        log.debug("Entering populateXenograft");

        inXenograft.setName(inXenograftData.getName());
        inXenograft.setAdministrativeSite(inXenograftData.getAdministrativeSite());
        inXenograft.setGeneticManipulation(inXenograftData.getGeneticManipulation());
        inXenograft.setModificationDescription(inXenograftData.getModificationDescription());
        inXenograft.setParentalCellLineName(inXenograftData.getParentalCellLineName());
        inXenograft.setAtccNumber(inXenograftData.getATCCNumber());
        inXenograft.setCellAmount(inXenograftData.getCellAmount());

        // Taxon theTaxon = new Taxon();
        // theTaxon.setScientificName(inXenograftData.getHostScientificName());
        // theTaxon.setEthnicityStrain(inXenograftData.getHostEthinicityStrain());

        // Find the matching taxon in the db and reuse it
        Taxon theTaxon = new Taxon();
        List taxonList = (List) TaxonManagerSingleton.instance().getAll(  );

        for( int i=0; i < taxonList.size(); i++ ) {
            theTaxon = (Taxon) taxonList.get(i);
            if ( theTaxon.getEthnicityStrain() != null ) {
                if ( theTaxon.getEthnicityStrain().equals( inXenograftData.getHostEthinicityStrain() ))
                    break;
            }
        }

        if (inXenograftData.getOtherHostEthinicityStrain() != null) {


            // TODO refine email content
            
            // gather message keys and variable values to build the e-mail content with
            String[] messageKeys = {"uncontrolledvocab"};
            TreeMap values = new TreeMap();
            values.put("submitter",inAnimalModel.getSubmitter());
            values.put("model",inAnimalModel.getModelDescriptor());
            values.put("modelstate",inAnimalModel.getState());


            theTaxon.setEthnicityStrain(null);
            theTaxon.setEthnicityStrainUnctrlVocab(inXenograftData.getOtherHostEthinicityStrain());
        }

        // Taxon
        inXenograft.setHostSpecies( theTaxon );
        inXenograft.setOriginSpecies(inAnimalModel.getSpecies());

        // Convert String into a Date
        if (inXenograftData.getHarvestDate() != null) {
            if (!inXenograftData.getHarvestDate().equals("")) {
                try {

                    String inputFormatString = "dd/MM/yyyy";

                    // parse the input - turn it into a date object
                    DateFormat inputFormat = new SimpleDateFormat(inputFormatString);
                    Date dateTimeValue = inputFormat.parse(inXenograftData.getHarvestDate());
                    inXenograft.setHarvestDate(dateTimeValue);

                } catch (Exception e) {
                    // TODO: Possibly setup validator here to catch incorrect
                    // formatting of date field
                    System.out.println("Error: Incorrect format! " + e);
                }
            }
        }

        //anytime the graft type is "other"
        if (inXenograftData.getGraftType().equals(Constants.Dropdowns.OTHER_OPTION) )   {
            // TODO: refine email content

            // gather message keys and variable values to build the e-mail content with
            String[] messageKeys = {"uncontrolledvocab"};
            TreeMap values = new TreeMap();
            values.put("submitter",inAnimalModel.getSubmitter());
            values.put("model",inAnimalModel.getModelDescriptor());
            values.put("modelstate",inAnimalModel.getState());


            inXenograft.setGraftType(Constants.Dropdowns.OTHER_OPTION);
            inXenograft.setGraftTypeUnctrlVocab(inXenograftData.getOtherGraftType());
        }
        // anytime graft type is not other set uncontrolled vocab to null (covers editing)
        else   {
            System.out.println("graft type not other");
            inXenograft.setGraftType(inXenograftData.getGraftType());
            inXenograft.setGraftTypeUnctrlVocab(null);
        }

        log.debug("Exiting populateXenograft");
    }
}
