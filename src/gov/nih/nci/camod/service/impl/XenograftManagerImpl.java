/**
 * 
 * $Id: XenograftManagerImpl.java,v 1.22 2005-12-12 17:33:37 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.21  2005/12/01 13:43:36  georgeda
 * Defect #226, reuse Taxon objects and do not delete them from Database
 *
 * Revision 1.20  2005/11/28 22:54:11  pandyas
 * Defect #186: Added organ/tissue to Xenograft page, modified search page to display multiple Xenografts with headers, modified XenograftManagerImpl so it does not create or save an organ object if not organ is selected
 *
 * Revision 1.19  2005/11/28 13:46:53  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.18  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.17  2005/11/14 14:19:37  georgeda
 * Cleanup
 *
 * Revision 1.16  2005/11/11 16:27:44  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.XenograftManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.XenograftData;

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

    public void remove(String id, AnimalModel inAnimalModel) throws Exception {
        log.trace("In XenograftManagerImpl.remove");

        inAnimalModel.getXenograftCollection().remove(get(id));
        super.save(inAnimalModel);
    }

    public Xenograft create(XenograftData inXenograftData, AnimalModel inAnimalModel) throws Exception {

        log.info("<XenograftManagerImpl> Entering XenograftManagerImpl.create");

        Xenograft theXenograft = new Xenograft();
        populateXenograft(inXenograftData, theXenograft, inAnimalModel);

        log.info("<XenograftManagerImpl> Exiting XenograftManagerImpl.create");

        return theXenograft;
    }

    public void update(XenograftData inXenograftData, Xenograft inXenograft, AnimalModel inAnimalModel)
            throws Exception {

        log.info("Entering XenograftManagerImpl.update");
        log.info("Updating XenograftData: " + inXenograft.getId());

        // Populate w/ the new values and save
        populateXenograft(inXenograftData, inXenograft, inAnimalModel);
        save(inXenograft);

        log.info("Exiting XenograftManagerImpl.update");
    }

    private void populateXenograft(XenograftData inXenograftData, Xenograft inXenograft, AnimalModel inAnimalModel)
            throws Exception {

        log.info("Entering XenograftManagerImpl.populateXenograft");

        inXenograft.setName(inXenograftData.getName());

        /* Set other adminstrative site or selected adminstrative site */
        // save directly in administrativeSite column of table
        if (inXenograftData.getAdministrativeSite().equals(Constants.Dropdowns.OTHER_OPTION)) {
            inXenograft.setAdministrativeSite(inXenograftData.getOtherAdministrativeSite());
            // Send e-mail for other administrativeSite

            ResourceBundle theBundle = ResourceBundle.getBundle("camod");

            // Iterate through all the reciepts in the config file
            String recipients = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_NOTIFY_KEY);
            StringTokenizer st = new StringTokenizer(recipients, ",");
            String inRecipients[] = new String[st.countTokens()];
            for (int i = 0; i < inRecipients.length; i++) {
                inRecipients[i] = st.nextToken();
            }

            String inSubject = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_SUBJECT_KEY);
            String inFrom = inAnimalModel.getSubmitter().getEmailAddress();

            // gather message keys and variable values to build the e-mail
            // content with
            String[] messageKeys = { Constants.Admin.NONCONTROLLED_VOCABULARY };
            Map values = new TreeMap();
            values.put("type", "Xenograft AdministrativeSite");
            values.put("value", inXenograftData.getOtherAdministrativeSite());
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

        } else {
            inXenograft.setAdministrativeSite(inXenograftData.getAdministrativeSite());
        }
        inXenograft.setGeneticManipulation(inXenograftData.getGeneticManipulation());
        inXenograft.setModificationDescription(inXenograftData.getModificationDescription());
        inXenograft.setParentalCellLineName(inXenograftData.getParentalCellLineName());
        inXenograft.setAtccNumber(inXenograftData.getATCCNumber());
        inXenograft.setCellAmount(inXenograftData.getCellAmount());

        // Create/reuse the taxon
        Taxon theTaxon = TaxonManagerSingleton.instance().getOrCreate(inXenograftData.getHostScientificName(),
                inXenograftData.getHostEthinicityStrain(), inXenograftData.getOtherHostEthinicityStrain());

        // Doesn't equal the old one
        if (theTaxon.getEthnicityStrainUnctrlVocab() != null) {

            ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);

            // Iterate through all the reciepts in the config file
            String recipients = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_NOTIFY_KEY);
            StringTokenizer st = new StringTokenizer(recipients, ",");
            String inRecipients[] = new String[st.countTokens()];
            for (int i = 0; i < inRecipients.length; i++) {
                inRecipients[i] = st.nextToken();
            }

            String inSubject = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_SUBJECT_KEY);
            String inFrom = inAnimalModel.getSubmitter().getEmailAddress();

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

        /*
         * Add a Organ to AnimalModel with correct IDs, conceptCode, only if
         * organ is selected by user
         */
        if (inXenograftData.getOrganTissueCode() != null && inXenograftData.getOrganTissueCode().length() > 0) {
            log.info("newConceptCode is not null: ");

            String newConceptCode = inXenograftData.getOrganTissueCode();
            log.info("newConceptCode: " + newConceptCode);

            // Organ will be null for new submissions
            if (inXenograft.getOrgan() == null) {
                log.info("Organ is new so create new object and retrieve attributes");

                inXenograft.setOrgan(new Organ());

                /*
                 * Always get/store organ name through the concept code - never
                 * deal with converting name back and forth
                 */
                String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inXenograftData.getOrganTissueCode());

                log.info("preferedOrganName: " + preferedOrganName);
                inXenograft.getOrgan().setName(preferedOrganName);

                log.info("populateXenograft - getOrgan().setConceptCode - OrganTissueCode: "
                        + inXenograftData.getOrganTissueCode());
                inXenograft.getOrgan().setConceptCode(inXenograftData.getOrganTissueCode());

            } else {
                // edited screen - submit organ only if changed
                log.info("Organ is modified so compare concept code in DB");

                String oldConceptCode = inXenograft.getOrgan().getConceptCode();
                log.info("oldConceptCode: " + oldConceptCode);

                if (!newConceptCode.equals(oldConceptCode)) {
                    log.info("oldConceptCode != newConceptCode: ");
                    /*
                     * Always get/store organ name through the concept code -
                     * never deal with converting name back and forth
                     */
                    String preferedOrganName = EvsTreeUtil.getEVSPreferedDescription(inXenograftData
                            .getOrganTissueCode());

                    log.info("preferedOrganName: " + preferedOrganName);
                    inXenograft.getOrgan().setName(preferedOrganName);

                    log.info("populateXenograft - getOrgan().setConceptCode - OrganTissueCode: "
                            + inXenograftData.getOrganTissueCode());
                    inXenograft.getOrgan().setConceptCode(inXenograftData.getOrganTissueCode());
                }
            }
        }

        // Taxon
        inXenograft.setHostSpecies(inAnimalModel.getSpecies());
        inXenograft.setOriginSpecies(theTaxon);
        
        // anytime the graft type is "other"
        if (inXenograftData.getGraftType().equals(Constants.Dropdowns.OTHER_OPTION)) {

            ResourceBundle theBundle = ResourceBundle.getBundle("camod");

            // Iterate through all the reciepts in the config file
            String recipients = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_NOTIFY_KEY);
            StringTokenizer st = new StringTokenizer(recipients, ",");
            String inRecipients[] = new String[st.countTokens()];
            for (int i = 0; i < inRecipients.length; i++) {
                inRecipients[i] = st.nextToken();
            }

            String inSubject = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_SUBJECT_KEY);
            String inFrom = inAnimalModel.getSubmitter().getEmailAddress();

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

            inXenograft.setGraftType(null);
            inXenograft.setGraftTypeUnctrlVocab(inXenograftData.getOtherGraftType());
        }
        // anytime graft type is not other set uncontrolled vocab to null
        // (covers editing)
        else {
            inXenograft.setGraftType(inXenograftData.getGraftType());
            inXenograft.setGraftTypeUnctrlVocab(null);
        }

        log.info("Exiting XenograftManagerImpl.populateXenograft");
    }
}
