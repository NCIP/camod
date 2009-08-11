/**
 * @author schroedln
 * 
 * $Id: InducedMutationManagerImpl.java,v 1.35 2008-08-15 18:23:01 pandyas Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.34  2008/02/08 16:45:59  pandyas
 * modified log statement for final deployment to QA
 *
 * Revision 1.33  2008/01/27 23:26:33  pandyas
 * Modifed to clear Gene Identifer when removed from GUI
 *
 * Revision 1.32  2008/01/22 15:57:12  pandyas
 * Modified to submit and edit gene identifier object
 *
 * Revision 1.31  2008/01/17 18:08:47  pandyas
 * Modified for # 11722  	The gene identifier does not work correctly for targeted modification submission and edit
 *
 * Revision 1.30  2008/01/16 18:30:21  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.29  2007/11/05 15:51:42  pandyas
 * took out debug statements
 *
 * Revision 1.28  2007/10/31 19:13:27  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.27  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.26  2007/04/04 13:17:49  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.25  2007/03/27 18:37:31  pandyas
 * Modified code to trim identifiers - cleaner for display link
 *
 * Revision 1.24  2007/03/26 12:01:11  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.23  2006/08/17 18:25:42  pandyas
 * Defect# 410: Externalize properties files - Code Changes to send mail method
 *
 * Revision 1.22  2006/05/04 19:27:45  pandyas
 * Changed GeneticAlterationCollection to GeneticAlteration relationship from SpontaneousMutation and InducedMutation objects
 *
 * Revision 1.21  2006/04/19 15:08:17  georgeda
 * Fixed issue w/ display of induced mutation
 *
 * Revision 1.20  2006/04/18 16:20:05  pandyas
 * Updated populate method to save IM correctly
 *
 * Revision 1.19  2006/04/17 19:11:06  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.18  2006/01/18 14:24:24  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.17  2005/11/28 13:46:53  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.16  2005/11/16 15:31:05  georgeda
 * Defect #41. Clean up of email functionality
 *
 * Revision 1.15  2005/11/09 00:17:16  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.14  2005/11/03 19:23:47  schroedn
 * Minor bug fixes
 *
 * Revision 1.13  2005/11/02 19:44:20  schroedn
 * Merged changes, modified Image function, fix MGI num bug
 *
 * Revision 1.12  2005/11/02 19:07:25  pandyas
 * Added e-mail functionality
 *
 * Revision 1.10  2005/10/31 18:55:51  georgeda
 * More validation changes
 *
 * Revision 1.9  2005/10/31 18:00:24  georgeda
 * Validation changes
 *
 * Revision 1.8  2005/10/27 20:54:31  schroedn
 * added buttons and caIMAGE dev server locations
 *
 * Revision 1.7  2005/10/24 21:04:03  schroedn
 * Added Image to submission
 *
 * Revision 1.6  2005/10/20 21:12:53  stewardd
 * modified to use extended MailUtil API
 *
 * Revision 1.5  2005/10/12 20:10:49  schroedn
 * Added Validation
 *
 * Revision 1.4  2005/10/06 20:41:49  schroedn
 * InducedMutation, TargetedMutation, GenomicSegment changes
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.EnvironmentalFactor;
import gov.nih.nci.camod.domain.GeneIdentifier;
import gov.nih.nci.camod.domain.GeneticAlteration;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.service.InducedMutationManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.InducedMutationData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class InducedMutationManagerImpl extends BaseManager implements
		InducedMutationManager {
	public List getAll() throws Exception {
		log.debug("In InducedMutationManagerImpl.getAll");
		return super.getAll(InducedMutation.class);
	}

	public InducedMutation get(String id) throws Exception {
		log.debug("In InducedMutationManagerImpl.get");
		return (InducedMutation) super.get(id, InducedMutation.class);
	}

	public void save(InducedMutation InducedMutation) throws Exception {
		log.debug("In InducedMutationManagerImpl.save");
		super.save(InducedMutation);
	}

	public void remove(String id, AnimalModel inAnimalModel) throws Exception {
		log.debug("In InducedMutationManagerImpl.remove");
		inAnimalModel.getEngineeredGeneCollection().remove(get(id));
		super.save(inAnimalModel);
	}

	public InducedMutation create(AnimalModel inAnimalModel,
			InducedMutationData inInducedMutationData) throws Exception {
		log.debug("Entering InducedMutationManagerImpl.create");

		InducedMutation theInducedMutation = new InducedMutation();

		populateInducedMutation(inAnimalModel, inInducedMutationData,
				theInducedMutation);

		log.debug("Exiting InducedMutationManagerImpl.create");

		return theInducedMutation;
	}

	public void update(AnimalModel inAnimalModel,
			InducedMutationData inInducedMutationData,
			InducedMutation inInducedMutation) throws Exception {
		log.debug("Entering InducedMutationManagerImpl.update");
		log.debug("Updating InducedMutationForm: " + inInducedMutation.getId());

		// Populate w/ the new values and save
		populateInducedMutation(inAnimalModel, inInducedMutationData,
				inInducedMutation);
		save(inInducedMutation);

		log.debug("Exiting InducedMutationManagerImpl.update");
	}

	private void populateInducedMutation(AnimalModel inAnimalModel,
			InducedMutationData inInducedMutationData,
			InducedMutation inInducedMutation) throws Exception {
		log.debug("Entering populateInducedMutation");

		EnvironmentalFactor theEnvironFactor = null;

		// Check to see if a Environmental Factor already exists,
		// if it does edit it else create a new EnvironmentalFactor
		if (inInducedMutation.getEnvironmentalFactor() != null) {
			theEnvironFactor = (EnvironmentalFactor) inInducedMutation
					.getEnvironmentalFactor();
		} else {
			theEnvironFactor = new EnvironmentalFactor();
			inInducedMutation.setEnvironmentalFactor(theEnvironFactor);
		}
		log
				.info("In InducedMutationManagerImpl.populateInducedMutation created EF");

		// Name of Inducing Agent - Saved in uncontrolled vocab field since it
		// is free text
		theEnvironFactor.setNameAlternEntry(inInducedMutationData.getName());
		log.debug("In InducedMutationManagerImpl.save set name : "
				+ inInducedMutationData.getName());

		// Inducing Agent Category type / Other type
		if (inInducedMutationData.getOtherType() != null) {
			log
					.info("Sending Notification eMail - new InducedMutation Agent added");
			// Send the email
			sendEmail(inAnimalModel, inInducedMutationData.getOtherType(),
					"OtherInducedMutation");
			// Do not save 'Other' in the database
			theEnvironFactor.setTypeAlternEntry(inInducedMutationData
					.getOtherType());
			log.debug("inInducedMutationData.getOtherType(): "
					+ inInducedMutationData.getOtherType());
		} else {
			theEnvironFactor.setType(inInducedMutationData.getType());
			log.debug("inInducedMutationData.getType(): "
					+ inInducedMutationData.getType());
		}

		// CAS Number
		theEnvironFactor.setCasNumber(inInducedMutationData.getCasNumber());
		
		// flag for IM in Environmental Factor object
		theEnvironFactor.setIsInducedMutationTrigger(true);
         
         // GeneIdentifier
         GeneIdentifier inGeneIdentifier = null;         
         if (inInducedMutationData.getGeneIdentifier() != null && inInducedMutationData.getGeneIdentifier().length() >0) {        
             log.debug("inTargetedModificationData.getGeneIdentifier(): " + inInducedMutationData.getGeneIdentifier());
             //Check for existing GeneIdentifier
             if (inInducedMutation.getGeneIdentifier() != null) {
                 inGeneIdentifier = inInducedMutation.getGeneIdentifier();
             } else {
                 inGeneIdentifier = new GeneIdentifier();
             }
             
             inGeneIdentifier = GeneIdentifierManagerSingleton.instance().getOrCreate(
            		 inInducedMutationData.getGeneIdentifier().trim());
             
             log.debug("inTargetedModificationData new inGeneIdentifier: " + inGeneIdentifier.toString());
             inInducedMutation.setGeneIdentifier(inGeneIdentifier);                
         } else {
             log.debug("setEntrezGeneID to null");
             inInducedMutation.setGeneIdentifier(inGeneIdentifier);
         }           

		// Description
		inInducedMutation
				.setDescription(inInducedMutationData.getDescription());
		log.debug("inInducedMutationData.getDescription(): "
				+ inInducedMutationData.getDescription());

		// Observation and Method of Observation
		// No genetic alteration in DB - data is entered from the GUI
		if (inInducedMutation.getGeneticAlteration() == null
				&& inInducedMutationData.getObservation() != null
				&& inInducedMutationData.getObservation().length() > 0) {
			inInducedMutation.setGeneticAlteration(new GeneticAlteration());
			log
					.info("Saving: inInducedMutation.getGeneticAlteration() attributes ");

			inInducedMutation.getGeneticAlteration().setObservation(
					inInducedMutationData.getObservation());
			inInducedMutation.getGeneticAlteration().setMethodOfObservation(
					inInducedMutationData.getMethodOfObservation());
		}

		// Already have a genetic alteration in DB. Either blank it out or
		// update it
		else {
			if (inInducedMutationData.getObservation() != null
					&& inInducedMutationData.getObservation().length() > 0) {
				inInducedMutation.getGeneticAlteration().setObservation(
						inInducedMutationData.getObservation());
				inInducedMutation.getGeneticAlteration()
						.setMethodOfObservation(
								inInducedMutationData.getMethodOfObservation());
			} else {
				inInducedMutation.setGeneticAlteration(null);
			}
		}

		// MGI Number
		// Check for exisiting MutationIdentifier
		MutationIdentifier inMutationIdentifier = null;

		if (inInducedMutationData.getMgiId() != null && inInducedMutationData.getMgiId().length() >0) {
			// Only create a MutationIdentifier if the value is filled in on the GUI
			log.debug("inInducedMutationData.getMgiId() != null");
			if (inInducedMutation.getMutationIdentifier() != null) {
				inMutationIdentifier = inInducedMutation.getMutationIdentifier();
			} else {
				inMutationIdentifier = new MutationIdentifier();
			}			
			inMutationIdentifier.setMgiId(inInducedMutationData
					.getMgiId().trim());
			inInducedMutation.setMutationIdentifier(inMutationIdentifier);
		} else {
			// remove MutationIdetifier if deleted later
			inInducedMutation.setMutationIdentifier(inMutationIdentifier);			
		}
		
		if (inInducedMutationData.getZfinId() != null && inInducedMutationData.getZfinId().length() >0) {
			log.debug("inInducedMutationData.getZfinId() != null");			
			if (inInducedMutation.getMutationIdentifier() != null) {
				inMutationIdentifier = inInducedMutation.getMutationIdentifier();
			} else {
				inMutationIdentifier = new MutationIdentifier();
			}
			inMutationIdentifier.setZfinId(inInducedMutationData
					.getZfinId().trim());
			inInducedMutation.setMutationIdentifier(inMutationIdentifier);
		} else {
			// remove MutationIdetifier if deleted later
			inInducedMutation.setMutationIdentifier(inMutationIdentifier);			
		}
		
		if (inInducedMutationData.getRgdId() != null && inInducedMutationData.getRgdId().length() >0) {
			log.debug("inInducedMutationData.getRgdId() != null");			
			if (inInducedMutation.getMutationIdentifier() != null) {
				inMutationIdentifier = inInducedMutation.getMutationIdentifier();
			} else {
				inMutationIdentifier = new MutationIdentifier();
			}
			inMutationIdentifier.setRgdId(inInducedMutationData
					.getRgdId().trim());
			inInducedMutation.setMutationIdentifier(inMutationIdentifier);
		} else {
			// remove MutationIdetifier if deleted later
			inInducedMutation.setMutationIdentifier(inMutationIdentifier);			
		}		

		// Comments
		inInducedMutation.setComments(inInducedMutationData.getComments());

		log.debug("Exiting populateInducedMutation");
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
