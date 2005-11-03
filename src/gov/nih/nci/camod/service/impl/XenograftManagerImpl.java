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
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.XenograftData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class XenograftManagerImpl extends BaseManager implements XenograftManager {

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

    public Xenograft create(XenograftData inXenograftData, AnimalModel inAnimalModel) throws Exception {

        log.debug("Entering XenograftManagerImpl.create");

        Xenograft theXenograft = new Xenograft();

        log.trace("Exiting XenograftManagerImpl.create");
        populateXenograft(inXenograftData, theXenograft, inAnimalModel);

        return theXenograft;
    }

    public void update(XenograftData inXenograftData, Xenograft inXenograft, AnimalModel inAnimalModel)
            throws Exception {

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

        Taxon theTaxon = inXenograft.getHostSpecies();
        String theOldVocab = "";
        if (theTaxon == null) {
            theTaxon = TaxonManagerSingleton.instance().create(inXenograftData.getHostScientificName(),
                    inXenograftData.getHostEthinicityStrain(), inXenograftData.getOtherHostEthinicityStrain());
        } else {
            theOldVocab = theTaxon.getEthnicityStrainUnctrlVocab();
            TaxonManagerSingleton.instance()
                    .update(inXenograftData.getHostScientificName(), inXenograftData.getHostEthinicityStrain(),
                            inXenograftData.getOtherHostEthinicityStrain(), theTaxon);
        }

        if (inXenograftData.getOtherHostEthinicityStrain() != null
                && !inXenograftData.getOtherHostEthinicityStrain().equals(theOldVocab)) {

            // TODO refine email content

            ResourceBundle theBundle = ResourceBundle.getBundle("camod");

            // Iterate through all the reciepts in the config file
            String recipients = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_NOTIFY_KEY);
            StringTokenizer st = new StringTokenizer(recipients, ",");
            String inRecipients[] = new String[st.countTokens()];
            for (int i = 0; i < inRecipients.length; i++) {
                inRecipients[i] = st.nextToken();
            }

            String inSubject = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_SUBJECT_KEY);
            String inFrom = inAnimalModel.getSubmitter().emailAddress();

            // gather message keys and variable values to build the e-mail
            // content with
            String[] messageKeys = { Constants.Admin.NONCONTROLLED_VOCABULARY };
            Map values = new TreeMap();
            values.put("type", "HostEthinicityStrain");
            values.put("value", inXenograftData.getOtherHostEthinicityStrain());
            values.put("submitter", inAnimalModel.getSubmitter());
            values.put("model", inAnimalModel.getModelDescriptor());
            values.put("modelstate", inAnimalModel.getState());

            // Send the email
            try {
                MailUtil.sendMail(inRecipients, inSubject, "", inFrom, messageKeys, values);
            } catch (Exception e) {
                log.error("Caught exception sending mail: ", e);
                e.printStackTrace();
            }
        }

        // Taxon
        inXenograft.setHostSpecies(theTaxon);
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

        // anytime the graft type is "other"
        if (inXenograftData.getGraftType().equals(Constants.Dropdowns.OTHER_OPTION)) {
            // TODO: refine email content

            ResourceBundle theBundle = ResourceBundle.getBundle("camod");

            // Iterate through all the reciepts in the config file
            String recipients = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_NOTIFY_KEY);
            StringTokenizer st = new StringTokenizer(recipients, ",");
            String inRecipients[] = new String[st.countTokens()];
            for (int i = 0; i < inRecipients.length; i++) {
                inRecipients[i] = st.nextToken();
            }

            String inSubject = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_SUBJECT_KEY);
            String inFrom = inAnimalModel.getSubmitter().emailAddress();

            // gather message keys and variable values to build the e-mail
            // content with
            String[] messageKeys = { Constants.Admin.NONCONTROLLED_VOCABULARY };
            Map values = new TreeMap();
            values.put("type", "GraftType");
            values.put("value", inXenograftData.getOtherGraftType());
            values.put("submitter", inAnimalModel.getSubmitter());
            values.put("model", inAnimalModel.getModelDescriptor());
            values.put("modelstate", inAnimalModel.getState());

            // Send the email
            try {
                MailUtil.sendMail(inRecipients, inSubject, "", inFrom, messageKeys, values);
            } catch (Exception e) {
                log.error("Caught exception sending mail: ", e);
                e.printStackTrace();
            }

            inXenograft.setGraftType(Constants.Dropdowns.OTHER_OPTION);
            inXenograft.setGraftTypeUnctrlVocab(inXenograftData.getOtherGraftType());
        }
        // anytime graft type is not other set uncontrolled vocab to null
        // (covers editing)
        else {
            System.out.println("graft type not other");
            inXenograft.setGraftType(inXenograftData.getGraftType());
            inXenograft.setGraftTypeUnctrlVocab(null);
        }

        log.debug("Exiting populateXenograft");
    }
}
