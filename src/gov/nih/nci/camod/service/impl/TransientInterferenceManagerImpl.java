/**
 * @pandyas
 * 
 * $Id: TransientInterferenceManagerImpl.java,v 1.1 2006-10-17 16:14:05 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/05/03 20:04:04  pandyas
 * Modified to add Morpholino object data to application
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.TransientInterferenceManager;
import gov.nih.nci.camod.webapp.form.*;
import gov.nih.nci.camod.util.MailUtil;

/**
 * Manager provides get method
 */
public class TransientInterferenceManagerImpl extends BaseManager implements
		TransientInterferenceManager {

	public List getAll() throws Exception {
		log.info("In TransientInterferenceManagerImpl.getAll");
		return super.getAll(TransientInterference.class);
	}

	/**
	 * Get a specific TransientInterference by id
	 * 
	 * @param id
	 *            the unique id for a TransientInterference
	 * 
	 * @return the matching TransientInterference object, or null if not found.
	 * 
	 * @exception Exception
	 *                when anything goes wrong.
	 */
	public TransientInterference get(String id) throws Exception {
		log.info("In TransientInterferenceManagerImpl.get");
		return (TransientInterference) super.get(id,
				TransientInterference.class);
	}

	/**
	 * Save TransientInterference
	 * 
	 * @param TransientInterference
	 *            the TransientInterference to save
	 * 
	 * @exception Exception
	 *                when anything goes wrong.
	 */
	public void save(TransientInterference transientInterference)
			throws Exception {
		log.info("In TransientInterferenceManagerImpl.save");
		super.save(transientInterference);
	}

	/**
	 * Remove a specific TransientInterference by id
	 * 
	 * @param id
	 *            the unique id for a TransientInterference
	 * 
	 * @exception Exception
	 *                when anything goes wrong.
	 */
	public void remove(String id, AnimalModel inAnimalModel) throws Exception {
		log.info("In TransientInterferenceManagerImpl.remove");

		TransientInterference theTransientInterference = get(id);

		inAnimalModel.getTransientInterferenceCollection().remove(
				theTransientInterference);
		super.save(inAnimalModel);
	}

	/**
	 * Create a TransientInterference object with the correct data filled in.
	 * 
	 * @param inTransientInterferenceData
	 *            the interface to create the TransientInterference object from
	 * 
	 * @returns a TransientInterference
	 */
	public TransientInterference create(AnimalModel inAnimaModel,
			TransientInterferenceData inTransientInterferenceData)
			throws Exception {

		log.info("In TransientInterferenceManagerImpl.create Entering");

		TransientInterference theTransientInterference = new TransientInterference();

		// Set TransientInterferenceMethod only once during create - skip during
		// update
		populateTransIntMethod(inTransientInterferenceData,
				theTransientInterference, inAnimaModel);
		populate(inTransientInterferenceData, theTransientInterference,
				inAnimaModel);

		log.info("In TransientInterferenceManagerImpl.create Exiting");
		return theTransientInterference;
	}

	public void update(AnimalModel inAnimalModel,
			TransientInterferenceData inTransientInterferenceData,
			TransientInterference inTransientInterference) throws Exception {

		log.debug("In TransientInterferenceManagerImpl.update");
		populate(inTransientInterferenceData, inTransientInterference,
				inAnimalModel);

		save(inTransientInterference);

	}

	private void populateTransIntMethod(
			TransientInterferenceData inTransientInterferenceData,
			TransientInterference inTransientInterference,
			AnimalModel inAnimalModel) throws Exception {
		log
				.info("<TransientInterferenceManagerImpl> Entering populateTransMethod");

		log.info("inTransientInterferenceData.getAConceptCode(): "
				+ inTransientInterferenceData.getAConceptCode());

		/* get TransientInterferenceMethod object */
		TransientInterferenceMethod theMethod = TransIntMethodManagerSingleton
				.instance().getByConceptCode(
						inTransientInterferenceData.getAConceptCode());
		log.info("theTransientInterferenceMethod: " + theMethod);

		// set TransientInterferenceMethod
		inTransientInterference.setTransientInterferenceMethod(theMethod);
	}

	private void populate(
			TransientInterferenceData inTransientInterferenceData,
			TransientInterference inTransientInterference,
			AnimalModel inAnimalModel) throws Exception {
		log.info("<TransientInterferenceManagerImpl> Entering populate");

		// Save Source
		if (inTransientInterferenceData.getSource().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			log.info("source equals other");
			inTransientInterference.setSource(null);
			inTransientInterference
					.setSourceUnctrVocab(inTransientInterferenceData
							.getOtherSource());

			log.info("Sending Notification eMail - new Source added");
			sendEmail(inAnimalModel, inTransientInterferenceData
					.getOtherSource(), "otherSource");
		} else if (inTransientInterferenceData.getSource() != null) {
			log.info("source not other or null");
			inTransientInterference.setSource(inTransientInterferenceData
					.getSource());
			inTransientInterference.setSourceUnctrVocab(null);
		}

		// Save Type
		inTransientInterference.setType(inTransientInterferenceData.getType());

		// Save SequenceDirection
		inTransientInterference
				.setSequenceDirection(inTransientInterferenceData
						.getSequenceDirection());

		// Save Targeted Region
		inTransientInterference.setTargetedRegion(inTransientInterferenceData
				.getTargetedRegion());

		// Save Concentration
		inTransientInterference.setConcentration(inTransientInterferenceData
				.getConcentration());
		inTransientInterference
				.setConcentrationUnit(inTransientInterferenceData
						.getConcentrationUnit());

		// Save Delivery Method
		if (inTransientInterferenceData.getDeliveryMethod().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			log.info("DeliveryMethod equals other");
			inTransientInterference.setDeliveryMethod(null);
			inTransientInterference
					.setDeliveryMethodUnctrlVocab(inTransientInterferenceData
							.getOtherDeliveryMethod());

			log.info("Sending Notification eMail - new DeliveryMethod added");
			sendEmail(inAnimalModel, inTransientInterferenceData
					.getOtherDeliveryMethod(), "otherDeliveryMethod");
		} else if (inTransientInterferenceData.getDeliveryMethod() != null) {
			log.info("DeliveryMethod not other or null");
			inTransientInterference
					.setDeliveryMethod(inTransientInterferenceData
							.getDeliveryMethod());
			inTransientInterference.setDeliveryMethodUnctrlVocab(null);
		}

		// Save visualizationLigands
		if (inTransientInterferenceData.getVisualLigand().equals(
				Constants.Dropdowns.OTHER_OPTION)) {
			log.info("visualizationLigands equals other");
			inTransientInterference.setVisualLigand(null);
			inTransientInterference
					.setVisualLigandUnctrlVocab(inTransientInterferenceData
							.getOtherVisualLigand());

			log.info("Sending Notification eMail - new VisualLigands added");
			sendEmail(inAnimalModel, inTransientInterferenceData
					.getOtherVisualLigand(), "otherVisualLigands");
		} else if (inTransientInterferenceData.getVisualLigand() != null) {
			log.info("visualLigands not other or null");
			inTransientInterference.setVisualLigand(inTransientInterferenceData
					.getVisualLigand());
			inTransientInterference.setVisualLigandUnctrlVocab(null);
		}

		// Save Comment
		inTransientInterference.setComments(inTransientInterferenceData
				.getComments());

		log.info("<TransientInterfaceManagerImpl> Exiting populate");
	}

	private void sendEmail(AnimalModel inAnimalModel,
			String theUncontrolledVocab, String inType) {
		// Get the e-mail resource
		Properties camodProperties = new Properties();
		String camodPropertiesFileName = null;

		camodPropertiesFileName = System
				.getProperty("gov.nih.nci.camod.camodProperties");

		try {

			FileInputStream in = new FileInputStream(camodPropertiesFileName);
			camodProperties.load(in);

		} catch (FileNotFoundException e) {
			log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();
		}

		// Iterate through all the reciepts in the config file
		String recipients = UserManagerSingleton.instance()
				.getEmailForCoordinator();
		StringTokenizer st = new StringTokenizer(recipients, ",");
		String inRecipients[] = new String[st.countTokens()];
		for (int i = 0; i < inRecipients.length; i++) {
			inRecipients[i] = st.nextToken();
		}

		String inSubject = camodProperties
				.getProperty("model.new_unctrl_vocab_subject");
		String inFrom = inAnimalModel.getSubmitter().getEmailAddress();

		// gather message keys and variable values to build the e-mail
		String[] messageKeys = { Constants.Admin.NONCONTROLLED_VOCABULARY };
		Map<String, Object> values = new TreeMap<String, Object>();
		values.put("type", inType);
		values.put("value", theUncontrolledVocab);
		values.put("submitter", inAnimalModel.getSubmitter());
		values.put("model", inAnimalModel.getModelDescriptor());
		values.put("modelstate", inAnimalModel.getState());

		// Send the email
		try {
			MailUtil.sendMail(inRecipients, inSubject, "", inFrom, messageKeys,
					values);
		} catch (Exception e) {
			log.error("Caught exception sending mail: ", e);
			e.printStackTrace();
		}
	}

}
