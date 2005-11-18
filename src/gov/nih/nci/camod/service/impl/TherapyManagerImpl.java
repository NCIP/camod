/**
 * @author dgeorge
 * 
 * $Id: TherapyManagerImpl.java,v 1.17 2005-11-18 22:50:02 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.15  2005/11/14 14:19:22  georgeda
 * Cleanup
 *
 * Revision 1.14  2005/11/09 00:17:16  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.13  2005/11/02 19:02:55  pandyas
 * Added e-mail functionality
 *
 * Revision 1.12  2005/10/26 14:10:48  georgeda
 * Added other administrative route to therapy
 *
 * Revision 1.11  2005/10/25 19:42:15  georgeda
 * Finished Therapy page
 *
 * Revision 1.10  2005/10/19 19:26:35  pandyas
 * added admin route to growth factor
 *
 * Revision 1.9  2005/10/18 21:59:34  pandyas
 * fixed other field
 *
 * Revision 1.8  2005/10/11 16:45:47  pandyas
 * fixed tumor response dose unit retrieval that was not working
 *
 * Revision 1.7  2005/10/06 19:30:22  pandyas
 * modified for Therapy screen
 *
 * Revision 1.6  2005/09/28 21:20:01  georgeda
 * Finished up converting to new manager
 *
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.*;
import gov.nih.nci.camod.webapp.form.cibase.*;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Implementation of the TherapyManager interface. Creates/saves/updates the
 * different types of therapies based on the specific interface passed in
 */
public class TherapyManagerImpl extends BaseManager implements TherapyManager {

    /**
     * Create a therapy object with the correct data filled in.
     * 
     * @param inSurgeryData
     *            the interface to create the therapy object from
     * 
     * @returns a therapy
     */
    public Therapy create(AnimalModel inAnimalModel, SurgeryData inSurgeryData) {

        log.debug("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        populateName(inAnimalModel, inSurgeryData, theTherapy, "Other");
        populateTreatment(inSurgeryData, theTherapy);
        populateAgeGender(inSurgeryData, theTherapy);

        return theTherapy;
    }

    /**
     * Update a therapy object with the correct data filled in.
     * 
     * @param inSurgeryData
     *            the interface to update the therapy object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel, SurgeryData inSurgeryData, Therapy inTherapy) throws Exception {

        log.debug("In TherapyManagerImpl.update");

        populateName(inAnimalModel, inSurgeryData, inTherapy, "Other");
        populateTreatment(inSurgeryData, inTherapy);
        populateAgeGender(inSurgeryData, inTherapy);

        save(inTherapy);
    }

    /**
     * Create a therapy object with the correct data filled in.
     * 
     * @param inNutritionalFactorData
     *            the interface to create the therapy object from
     * 
     * @returns a therapy
     */
    public Therapy create(AnimalModel inAnimalModel, NutritionalFactorData inNutritionalFactorData) {

        log.debug("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        populateName(inAnimalModel, inNutritionalFactorData, theTherapy, "Nutrition");
        populateTreatment(inNutritionalFactorData, theTherapy);
        populateAgeGender(inNutritionalFactorData, theTherapy);
        populateDose(inNutritionalFactorData, theTherapy);

        return theTherapy;
    }

    /**
     * Update a therapy object with the correct data filled in.
     * 
     * @param inNutritionalFactorData
     *            the interface to update the therapy object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel, NutritionalFactorData inNutritionalFactorData, Therapy inTherapy)
            throws Exception {

        log.debug("In TherapyManagerImpl.update");

        populateName(inAnimalModel, inNutritionalFactorData, inTherapy, "Nutrition");
        populateTreatment(inNutritionalFactorData, inTherapy);
        populateAgeGender(inNutritionalFactorData, inTherapy);
        populateDose(inNutritionalFactorData, inTherapy);

        save(inTherapy);
    }

    /**
     * Create a therapy object with the correct data filled in.
     * 
     * @param inHormoneData
     *            the interface to create the therapy object from
     * 
     * @returns a therapy
     */
    public Therapy create(AnimalModel inAnimalModel, HormoneData inHormoneData) {

        log.debug("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        populateName(inAnimalModel, inHormoneData, theTherapy, "Hormone");
        populateAgeGender(inHormoneData, theTherapy);
        populateTreatment(inHormoneData, theTherapy);
        populateDose(inHormoneData, theTherapy);
        populateAdministration(inAnimalModel, inHormoneData, theTherapy);

        return theTherapy;
    }

    /**
     * Update a therapy object with the correct data filled in.
     * 
     * @param inHormoneData
     *            the interface to update the therapy object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel, HormoneData inHormoneData, Therapy inTherapy) throws Exception {

        log.debug("In TherapyManagerImpl.update");

        populateName(inAnimalModel, inHormoneData, inTherapy, "Hormone");
        populateAgeGender(inHormoneData, inTherapy);
        populateTreatment(inHormoneData, inTherapy);
        populateDose(inHormoneData, inTherapy);
        populateAdministration(inAnimalModel, inHormoneData, inTherapy);

        save(inTherapy);
    }

    /**
     * Create a therapy object with the correct data filled in.
     * 
     * @param inGrowthFactorData
     *            the interface to create the therapy object from
     * 
     * @returns a therapy
     */
    public Therapy create(AnimalModel inAnimalModel, GrowthFactorData inGrowthFactorData) {

        log.debug("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        populateName(inAnimalModel, inGrowthFactorData, theTherapy, "Growth Factor");
        populateAgeGender(inGrowthFactorData, theTherapy);
        populateTreatment(inGrowthFactorData, theTherapy);
        populateDose(inGrowthFactorData, theTherapy);
        populateAdministration(inAnimalModel, inGrowthFactorData, theTherapy);

        return theTherapy;
    }

    /**
     * Update a therapy object with the correct data filled in.
     * 
     * @param inGrowthFactorData
     *            the interface to update the therapy object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel, GrowthFactorData inGrowthFactorData, Therapy inTherapy)
            throws Exception {

        log.debug("In TherapyManagerImpl.update");

        populateName(inAnimalModel, inGrowthFactorData, inTherapy, "Growth Factor");
        populateAgeGender(inGrowthFactorData, inTherapy);
        populateTreatment(inGrowthFactorData, inTherapy);
        populateDose(inGrowthFactorData, inTherapy);
        populateAdministration(inAnimalModel, inGrowthFactorData, inTherapy);

        save(inTherapy);
    }

    /**
     * Create a therapy object with the correct data filled in.
     * 
     * @param inViralTreatmentData
     *            the interface to create the therapy object from
     * 
     * @returns a therapy
     */
    public Therapy create(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatmentData) {

        log.debug("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();
        populateName(inAnimalModel, inViralTreatmentData, theTherapy, "Viral");
        populateAgeGender(inViralTreatmentData, theTherapy);
        populateTreatment(inViralTreatmentData, theTherapy);
        populateDose(inViralTreatmentData, theTherapy);
        populateAdministration(inAnimalModel, inViralTreatmentData, theTherapy);

        return theTherapy;
    }

    /**
     * Update a therapy object with the correct data filled in.
     * 
     * @param inViralTreatmentData
     *            the interface to update the therapy object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel, ViralTreatmentData inViralTreatmentData, Therapy inTherapy)
            throws Exception {

        log.debug("In TherapyManagerImpl.update");

        populateName(inAnimalModel, inViralTreatmentData, inTherapy, "Viral");
        populateAgeGender(inViralTreatmentData, inTherapy);
        populateTreatment(inViralTreatmentData, inTherapy);
        populateDose(inViralTreatmentData, inTherapy);
        populateAdministration(inAnimalModel, inViralTreatmentData, inTherapy);

        save(inTherapy);
    }

    /**
     * Create a therapy object with the correct data filled in.
     * 
     * @param inRadiationData
     *            the interface to create the therapy object from
     * 
     * @returns a therapy
     */
    public Therapy create(AnimalModel inAnimalModel, RadiationData inRadiationData) {

        log.debug("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();

        populateName(inAnimalModel, inRadiationData, theTherapy, "Radiation");
        populateAgeGender(inRadiationData, theTherapy);
        populateTreatment(inRadiationData, theTherapy);
        populateDose(inRadiationData, theTherapy);
        populateAdministration(inAnimalModel, inRadiationData, theTherapy);

        return theTherapy;
    }

    /**
     * Update a therapy object with the correct data filled in.
     * 
     * @param inRadiationData
     *            the interface to update the therapy object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel, RadiationData inRadiationData, Therapy inTherapy) throws Exception {

        log.debug("In TherapyManagerImpl.update");

        populateName(inAnimalModel, inRadiationData, inTherapy, "Radiation");
        populateAgeGender(inRadiationData, inTherapy);
        populateTreatment(inRadiationData, inTherapy);
        populateDose(inRadiationData, inTherapy);
        populateAdministration(inAnimalModel, inRadiationData, inTherapy);

        save(inTherapy);
    }

    /**
     * Create a therapy object with the correct data filled in.
     * 
     * @param inEnvironmentalFactorData
     *            the interface to create the therapy object from
     * 
     * @returns a therapy
     */
    public Therapy create(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactorData) {

        log.debug("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();

        populateName(inAnimalModel, inEnvironmentalFactorData, theTherapy, "Environment");
        populateAgeGender(inEnvironmentalFactorData, theTherapy);
        populateTreatment(inEnvironmentalFactorData, theTherapy);
        populateDose(inEnvironmentalFactorData, theTherapy);
        populateAdministration(inAnimalModel, inEnvironmentalFactorData, theTherapy);

        return theTherapy;
    }

    /**
     * Update a therapy object with the correct data filled in.
     * 
     * @param inEnvironmentalFactorData
     *            the interface to update the therapy object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel, EnvironmentalFactorData inEnvironmentalFactorData, Therapy inTherapy)
            throws Exception {

        log.trace("In TherapyManagerImpl.update");

        populateName(inAnimalModel, inEnvironmentalFactorData, inTherapy, "Environment");
        populateAgeGender(inEnvironmentalFactorData, inTherapy);
        populateTreatment(inEnvironmentalFactorData, inTherapy);
        populateDose(inEnvironmentalFactorData, inTherapy);
        populateAdministration(inAnimalModel, inEnvironmentalFactorData, inTherapy);

        save(inTherapy);
    }

    /**
     * Create a therapy object with the correct data filled in.
     * 
     * @param inChemicalDrugData
     *            the interface to create the therapy object from
     * 
     * @returns a therapy
     */
    public Therapy create(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrugData) {

        log.debug("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();

        populateName(inAnimalModel, inChemicalDrugData, theTherapy, "Chemical / Drug");
        populateAgeGender(inChemicalDrugData, theTherapy);
        populateTreatment(inChemicalDrugData, theTherapy);
        populateDose(inChemicalDrugData, theTherapy);
        populateAdministration(inAnimalModel, inChemicalDrugData, theTherapy);

        populateChemicalDrug(inChemicalDrugData, theTherapy);

        return theTherapy;
    }

    /**
     * Update a therapy object with the correct data filled in.
     * 
     * @param inChemicalDrugData
     *            the interface to update the therapy object from
     * 
     * @param inTherapy
     *            the therapy object to update
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void update(AnimalModel inAnimalModel, ChemicalDrugData inChemicalDrugData, Therapy inTherapy)
            throws Exception {

        log.debug("In TherapyManagerImpl.update");

        populateName(inAnimalModel, inChemicalDrugData, inTherapy, "Chemical / Drug");
        populateAgeGender(inChemicalDrugData, inTherapy);
        populateTreatment(inChemicalDrugData, inTherapy);
        populateDose(inChemicalDrugData, inTherapy);
        populateAdministration(inAnimalModel, inChemicalDrugData, inTherapy);

        populateChemicalDrug(inChemicalDrugData, inTherapy);
        save(inTherapy);
    }

    /**
     * Get a specific Therapy by id
     * 
     * @param id
     *            the unique id for a therapy
     * 
     * @return the matching Therapy object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Therapy get(String id) throws Exception {
        log.trace("In TherapyManagerImpl.get");
        return (Therapy) super.get(id, Therapy.class);
    }

    /**
     * Save Therapy
     * 
     * @param therapy
     *            the therapy to save
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void save(Therapy therapy) throws Exception {
        log.debug("In TherapyManagerImpl.save");
        super.save(therapy);
    }

    /**
     * Remove a specific Therapy by id
     * 
     * @param id
     *            the unique id for a therapy
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void remove(String id, AnimalModel inAnimalModel) throws Exception {
        log.debug("In TherapyManagerImpl.remove");

        Therapy theTherapy = get(id);

        inAnimalModel.getTherapyCollection().remove(theTherapy);
        super.save(inAnimalModel);
    }

    // ///////////////////////////////////////////////////////
    // Populate methods for the specific interfaces that
    // each interface implements
    // ///////////////////////////////////////////////////////
    private void populateChemicalDrug(ChemicalDrugData inChemicalDrug, Therapy theTherapy) {

        log.debug("In TherapyManagerImpl.populateChemicalDrug");

        // Agent IS-A an EnvironmentalFactor
        Agent theAgent = theTherapy.getAgent();

        String theNSCNumber = inChemicalDrug.getNSCNumber().trim();
        if (theNSCNumber != null && theNSCNumber.length() > 0) {
            try {
                theAgent.setNscNumber(Long.valueOf(theNSCNumber));
            } catch (NumberFormatException e) {
                log.error("Bad NSC number: " + theNSCNumber);
            }
        }
        String theCasNumber = inChemicalDrug.getCASNumber().trim();
        if (theCasNumber != null && theCasNumber.length() > 0) {
            theAgent.setCasNumber(theCasNumber);
        }
    }

    private void populateAgeGender(AgeGenderData inAgeGender, Therapy theTherapy) {

        log.debug("In TherapyManagerImpl.populateAgeGender");

        // Set the treatment
        Treatment theTreatment = theTherapy.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            theTherapy.setTreatment(theTreatment);
        }

        // Set the gender
        SexDistribution sexDistribution = SexDistributionManagerSingleton.instance().getByType(inAgeGender.getType());

        // save the treatment
        theTreatment.setSexDistribution(sexDistribution);

        // Append the ageunit onto the age at treatment variable
        theTreatment.setAgeAtTreatment(inAgeGender.getAgeAtTreatment() + " " + inAgeGender.getAgeUnit());
    }

    private void populateTreatment(TreatmentData inTreatment, Therapy theTherapy) {

        log.debug("In TherapyManagerImpl.populateTreatment");

        // Set the treatment
        Treatment theTreatment = theTherapy.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            theTherapy.setTreatment(theTreatment);
        }

        // save the treatment
        theTreatment.setRegimen(inTreatment.getRegimen());
    }

    private void populateAdministration(AnimalModel inAnimalModel, AdministrationData inAdministrationData,
            Therapy theTherapy) {

        log.debug("In TherapyManagerImpl.populateAdministration");

        if (inAdministrationData.getAdministrativeRoute() != null
                && inAdministrationData.getAdministrativeRoute().length() > 0) {
            // Set the treatment
            Treatment theTreatment = theTherapy.getTreatment();
            if (theTreatment == null) {
                theTreatment = new Treatment();
                theTherapy.setTreatment(theTreatment);
            }
        }

        /* Set other adminstrative route or selected adminstrative route */
        // anytime admin route is other
        if (inAdministrationData.getAdministrativeRoute().equals(Constants.Dropdowns.OTHER_OPTION)) {
            log.info("admin route equals other");

            theTherapy.getTreatment().setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
            theTherapy.getTreatment().setAdminRouteUnctrlVocab(inAdministrationData.getOtherAdministrativeRoute());

            log.trace("Sending Notification eMail - new Administrative Route added");

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
            values.put("type", "AdministrativeRoute");
            values.put("value", inAdministrationData.getOtherAdministrativeRoute());
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

            // anytime admin route is not other, set uncontrolled vocab to null
            // (covers editing)
        } else if (inAdministrationData.getAdministrativeRoute() != null) {
            log.info("admin route not other");

            theTherapy.getTreatment().setAdministrativeRoute(inAdministrationData.getAdministrativeRoute());
            theTherapy.getTreatment().setAdminRouteUnctrlVocab(null);
        }

        // Agent IS-A an EnvironmentalFactor
        Agent theAgent = theTherapy.getAgent();
        if (theAgent == null) {
            theAgent = new Agent();
            theTherapy.setAgent(theAgent);
        }
    }

    private void populateDose(DoseData inDoseData, Therapy theTherapy) {

        // Set the treatment
        Treatment theTreatment = theTherapy.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            theTherapy.setTreatment(theTreatment);
        }

        theTreatment.setDosage(inDoseData.getDosage() + " " + inDoseData.getDoseUnit());
    }

    private void populateName(AnimalModel inAnimalModel, NameData inNameData, Therapy theTherapy, String inType) {

        log.debug("In TherapyManagerImpl.populateName");

        // Set the treatment
        Treatment theTreatment = theTherapy.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            theTherapy.setTreatment(theTreatment);
        }

        // Agent IS-A an EnvironmentalFactor
        Agent theAgent = theTherapy.getAgent();
        if (theAgent == null) {
            theAgent = new Agent();
            theTherapy.setAgent(theAgent);
        }
        theAgent.setType(inType);

        /* Set other name or selected chemical name */
        // anytime the name is "other"
        if (inNameData.getName().equals(Constants.Dropdowns.OTHER_OPTION)) {

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
            values.put("type", "TherapyName");
            values.put("value", inNameData.getOtherName());
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

            theAgent.setName(Constants.Dropdowns.OTHER_OPTION);
            theAgent.setNameUnctrlVocab(inNameData.getOtherName());
        }
        // anytime name is not other, set uncontrolled vocab to null (covers
        // editing)
        else {
            log.info("Name is not other");
            theAgent.setName(inNameData.getName());
            theAgent.setNameUnctrlVocab(null);
        }

        theTherapy.setTherapeuticExperiment(new Boolean(false));
    }

    /**
     * Create a therapy object with the correct data filled in.
     * 
     * @param inTherapyData
     *            the interface to create the therapy object from
     * 
     * @returns a therapy
     */
    public Therapy create(AnimalModel inAnimaModel, TherapyData inTherapyData) throws Exception {

        log.debug("In TherapyManagerImpl.create");

        Therapy theTherapy = new Therapy();

        populateAgeGender(inTherapyData, theTherapy);
        populateDose(inTherapyData, theTherapy);
        populateTherapy(inTherapyData, theTherapy);
        populateAdministration(inAnimaModel, inTherapyData, theTherapy);

        return theTherapy;
    }

    public void update(AnimalModel inAnimalModel, TherapyData inTherapyData, Therapy inTherapy) throws Exception {

        log.debug("In TherapyManagerImpl.update");

        populateAgeGender(inTherapyData, inTherapy);
        populateDose(inTherapyData, inTherapy);
        populateTherapy(inTherapyData, inTherapy);
        populateAdministration(inAnimalModel, inTherapyData, inTherapy);
        save(inTherapy);

    }

    private void populateTherapy(TherapyData inTherapyData, Therapy theTherapy) throws Exception {

        log.trace("Entering populateTherapy");

        /* populateName method without otherName */

        // Set the treatment
        Treatment theTreatment = theTherapy.getTreatment();
        if (theTreatment == null) {
            theTreatment = new Treatment();
            theTherapy.setTreatment(theTreatment);
        }

        // Agent IS-A an EnvironmentalFactor
        Agent theAgent = theTherapy.getAgent();
        if (theAgent == null) {
            theAgent = new Agent();
            theTherapy.setAgent(theAgent);
        }
        // theAgent.setType(inType);
        theAgent.setName(inTherapyData.getName());
        theTherapy.setTherapeuticExperiment(new Boolean(true));

        // Set the administrative route
        theTreatment.setAdministrativeRoute(inTherapyData.getAdministrativeRoute());

        // Set NSC and CAS
        String theNSCNumber = inTherapyData.getNSCNumber().trim();
        if (theNSCNumber != null && theNSCNumber.length() > 0) {
            try {
                theAgent.setNscNumber(Long.valueOf(theNSCNumber));
            } catch (NumberFormatException e) {
                log.error("Bad NSC number: " + theNSCNumber);
            }
        }
        String theCasNumber = inTherapyData.getCASNumber().trim();
        if (theCasNumber != null && theCasNumber.length() > 0) {
            theAgent.setCasNumber(theCasNumber);
        }

        // Therapy object attributes
        theTherapy.setToxicityGrade(inTherapyData.getToxicityGrade());
        theTherapy.setBiomarker(inTherapyData.getBiomarker());
        theTherapy.setTumorResponse(inTherapyData.getTumorResponse() + " " + inTherapyData.getTumorAgeUnit());
        theTherapy.setExperiment(inTherapyData.getExperiment());
        theTherapy.setResults(inTherapyData.getResults());
        theTherapy.setComments(inTherapyData.getComments());

        // Get the ChemicalClass
        String[] theChemicalClasses = inTherapyData.getSelectedChemicalClasses();
        List theCurrentChemicalClassList = theTherapy.getAgent().getChemicalClassCollection();
        theCurrentChemicalClassList.clear();
        if (theChemicalClasses != null) {
            for (int i = 0; i < theChemicalClasses.length; i++) {
                ChemicalClass theChemicalClass = ChemicalClassManagerSingleton.instance().getByName(
                        theChemicalClasses[i]);
                if (theChemicalClass == null) {
                    log.error("Unknown chemical class name: " + theChemicalClasses[i]);
                } else {
                    theCurrentChemicalClassList.add(theChemicalClass);
                }
            }
        }

        // Get the biological process
        String[] theProcesses = inTherapyData.getSelectedProcesses();
        List theCurrentProcessList = theTherapy.getAgent().getBiologicalProcessCollection();
        theCurrentProcessList.clear();
        if (theProcesses != null) {
            for (int i = 0; i < theProcesses.length; i++) {
                BiologicalProcess theBiologicalProcess = BiologicalProcessManagerSingleton.instance().getByName(
                        theProcesses[i]);
                if (theBiologicalProcess == null) {
                    log.error("Unknown biological process name: " + theProcesses[i]);
                } else {
                    theCurrentProcessList.add(theBiologicalProcess);
                }
            }
        }

        // Get the agent target
        String[] theTargets = inTherapyData.getSelectedTargets();
        List theCurrentAgentList = theTherapy.getAgent().getAgentTargetCollection();
        theCurrentAgentList.clear();
        if (theTargets != null) {
            for (int i = 0; i < theTargets.length; i++) {
                AgentTarget theAgentTarget = AgentTargetManagerSingleton.instance().getByName(theTargets[i]);
                if (theAgentTarget == null) {
                    log.error("Unknown agent target name: " + theTargets[i]);
                } else {
                    theCurrentAgentList.add(theAgentTarget);
                }
            }
        }
        log.trace("Exiting populateTherapy");
    }
}
