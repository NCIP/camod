/**
 * 
 * $Id: TargetedModificationManagerImpl.java,v 1.40 2008-08-18 13:55:26 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.39  2008/08/14 16:38:15  pandyas
 * modified debug line to use log
 *
 * Revision 1.38  2008/02/08 16:46:42  pandyas
 * modified log statement for final deployment to QA
 *
 * Revision 1.37  2008/01/27 23:27:11  pandyas
 * Modifed to clear Gene Identifer when removed from GUI
 *
 * Revision 1.36  2008/01/22 15:57:12  pandyas
 * Modified to submit and edit gene identifier object
 *
 * Revision 1.35  2008/01/17 18:08:47  pandyas
 * Modified for # 11722  	The gene identifier does not work correctly for targeted modification submission and edit
 *
 * Revision 1.34  2008/01/16 18:30:22  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.33  2007/10/31 19:13:27  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.32  2007/09/12 19:36:04  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.31  2007/08/15 16:00:55  pandyas
 * Bug #8351:  Construct Sequence info not shown in edit mode and on search page
 *
 * Search issue due to not saving construct sequence on GS and TM screens
 *
 * Revision 1.30  2007/07/31 12:02:22  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.29  2007/04/04 13:17:49  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.28  2007/03/27 18:38:26  pandyas
 * Modified code to trim identifiers - cleaner for display link
 *
 * Revision 1.27  2007/03/26 12:01:11  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.26  2006/08/17 18:20:33  pandyas
 * Defect# 410: Externalize properties files - Code changes to get properties
 *
 * Revision 1.25  2006/05/08 13:33:40  georgeda
 * Clean up warnings
 *
 * Revision 1.24  2006/04/20 14:06:40  pandyas
 * changed Modification Type to getOrCreate
 *
 * Revision 1.23  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.22  2005/12/13 16:27:27  schroedn
 * Added Check for Image upload, only used for Unit testing
 *
 * Revision 1.21  2005/11/28 18:31:57  georgeda
 * Defect #64, fix for newly submitted models
 *
 * Revision 1.20  2005/11/28 13:46:53  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.19  2005/11/16 19:47:13  pandyas
 * Modified Targeted Modification Types dropdown to allow multiple selections, allow the user to select "other" by itself, and allow users to select "other" along with one or more selection
 *
 * Revision 1.18  2005/11/16 19:32:59  pandyas
 * Modified Targeted Modification Types dropdown to allow multiple selections, allow the user to select "other" by itself, and allow users to select "other" along with one or more selection
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.TargetedModificationManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.ImageForm;
import gov.nih.nci.camod.webapp.form.TargetedModificationData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

public class TargetedModificationManagerImpl extends BaseManager implements
		TargetedModificationManager {

    public List getAll() throws Exception
    {
        log.trace("In TargetedModificationManagerImpl.getAll");
        return super.getAll(TargetedModification.class);
    }

    public TargetedModification get(String id) throws Exception
    {
        log.trace("In TargetedModificationManagerImpl.get");
        return (TargetedModification) super.get(id, TargetedModification.class);
    }

    public void save(TargetedModification TargetedModification) throws Exception
    {
        log.debug("In TargetedModificationManagerImpl.save");
        super.save(TargetedModification);
    }

    public void remove(String id,
                       AnimalModel inAnimalModel) throws Exception
    {
        log.debug("In TargetedModificationManagerImpl.remove");
        inAnimalModel.getEngineeredGeneCollection().remove(get(id));
        super.save(inAnimalModel);
    }

    public TargetedModification create(AnimalModel inAnimalModel,
                                       TargetedModificationData inTargetedModificationData,
                                       HttpServletRequest request) throws Exception
    {

        log.debug("Entering TargetedModificationManagerImpl.create");

        TargetedModification theTargetedModification = new TargetedModification();

		populateTargetedModification(inAnimalModel, inTargetedModificationData,
				theTargetedModification, request);

		log.debug("Exiting TargetedModificationManagerImpl.create");

		return theTargetedModification;
	}

    public void update(AnimalModel inAnimalModel,
                       TargetedModificationData inTargetedModificationData,
                       TargetedModification theTargetedModification,
                       HttpServletRequest request) throws Exception
    {

        log.debug("Entering TargetedModificationManagerImpl.update");
        log.debug("Updating TargetedModificationForm: " + theTargetedModification.getId());

        // Populate w/ the new values and save
        populateTargetedModification(inAnimalModel, inTargetedModificationData, theTargetedModification, request);

        save(theTargetedModification);

        log.debug("Exiting TargetedModificationManagerImpl.update");
    }

    private void populateTargetedModification(AnimalModel inAnimalModel,
                                              TargetedModificationData inTargetedModificationData,
                                              TargetedModification inTargetedModification,
                                              HttpServletRequest request) throws Exception
    {

        log.debug("Entering populateTargetedModification");

        // set Targeted Gene/Locus
        inTargetedModification.setName(inTargetedModificationData.getName());

		// Get Modification Types selected from the GUI
		String[] theModificationTypes = inTargetedModificationData
				.getModificationType();

		// associates the current list with the Object, so it is reused to save
		// them back into the database
		Set<ModificationType> theCurrentModificationTypeSet = inTargetedModification
				.getModificationTypeCollection();

		// Clears list, but list remains associated with TM object (no explicit
		// save needed for hibernate)
		theCurrentModificationTypeSet.clear();

		for (int i = 0; i < theModificationTypes.length; i++) {
			log.debug(" other theModificationType from GUI: "
					+ inTargetedModificationData.getOtherModificationType());

			// Create/reuse the ModificationType object - Reuses other if
			// uncontrolled value matches
			ModificationType theModificationType = ModificationTypeManagerSingleton
					.instance().getOrCreate(
							theModificationTypes[i],
							inTargetedModificationData
									.getOtherModificationType());

			if (theModificationTypes.equals(Constants.Dropdowns.OTHER_OPTION)) {
				log.debug(" other theModificationTyp: "
						+ theModificationType.toString());
				// Send e-mail and add modification type (already reused in
				// getOrCreate)
				log
						.info("Sending Notification eMail - new Targeted Modification added");
				sendEmail(inAnimalModel, inTargetedModificationData
						.getOtherModificationType(), "OtherModificationType");
				// theCurrentModificationTypeSet.add(theModificationType);
			}
			// Add selection if not already in DB (No duplicates during editing
			// phase)
			if (!inTargetedModification.getModificationTypeCollection()
					.contains(theModificationType)) {
				log.debug("\n new ModificationType: "
						+ theModificationType.toString());
				theCurrentModificationTypeSet.add(theModificationType);
			}

		}
		
        // GeneIdentifier
        GeneIdentifier inGeneIdentifier = null;
                
        if (inTargetedModificationData.getGeneIdentifier() != null && inTargetedModificationData.getGeneIdentifier().length() >0) {        
        log.debug("inTargetedModificationData.getGeneIdentifier(): " + inTargetedModificationData.getGeneIdentifier());
            //Check for existing GeneIdentifier
	        if (inTargetedModification.getGeneIdentifier() != null) {
	            inGeneIdentifier = inTargetedModification.getGeneIdentifier();
	        } else {
	            inGeneIdentifier = new GeneIdentifier();
	        }
            inGeneIdentifier = GeneIdentifierManagerSingleton.instance().getOrCreate(
            		inTargetedModificationData.getGeneIdentifier().trim());        
            inTargetedModification.setGeneIdentifier(inGeneIdentifier);   
        } else {
            log.debug("setEntrezGeneID to null");
            inTargetedModification.setGeneIdentifier(inGeneIdentifier);
        }
            

        

            // MGI Number
            // Check for exisiting MutationIdentifier
            MutationIdentifier inMutationIdentifier = null;

            if (inTargetedModificationData.getMgiId() != null && inTargetedModificationData.getMgiId().length() >0) {
                //Check for existing MutationIdentifier            	
                if (inTargetedModification.getMutationIdentifier() != null) {
                    inMutationIdentifier = inTargetedModification.getMutationIdentifier();
                } else {
                    inMutationIdentifier = new MutationIdentifier();
                }
                inMutationIdentifier.setMgiId(inTargetedModificationData
                        .getMgiId().trim());
                inTargetedModification.setMutationIdentifier(inMutationIdentifier);
            } else {
    			// remove MutationIdetifier if deleted later            	
            	inTargetedModification.setMutationIdentifier(inMutationIdentifier);
            }
            
            if (inTargetedModificationData.getZfinId() != null && inTargetedModificationData.getZfinId().length() >0) {
                //Check for existing MutationIdentifier            	
                if (inTargetedModification.getMutationIdentifier() != null) {
                    inMutationIdentifier = inTargetedModification.getMutationIdentifier();
                } else {
                    inMutationIdentifier = new MutationIdentifier();
                }            	
                inMutationIdentifier.setZfinId(inTargetedModificationData
                        .getZfinId().trim());
                inTargetedModification.setMutationIdentifier(inMutationIdentifier);
            }else {
    			// remove MutationIdetifier if deleted later            	
            	inTargetedModification.setMutationIdentifier(inMutationIdentifier);
            }
            
            if (inTargetedModificationData.getRgdId() != null && inTargetedModificationData.getRgdId().length() >0) {
                //Check for existing MutationIdentifier            	
                if (inTargetedModification.getMutationIdentifier() != null) {
                    inMutationIdentifier = inTargetedModification.getMutationIdentifier();
                } else {
                    inMutationIdentifier = new MutationIdentifier();
                }
                inMutationIdentifier.setRgdId(inTargetedModificationData
                        .getRgdId().trim());
                inTargetedModification.setMutationIdentifier(inMutationIdentifier);
            } else {
    			// remove MutationIdetifier if deleted later            	
            	inTargetedModification.setMutationIdentifier(inMutationIdentifier);
            }

		// Genetic Background - Donor
		inTargetedModification.setEsCellLineName(inTargetedModificationData
				.getEsCellLineName());

		// Genetic Background - Recipient
		inTargetedModification.setBlastocystName(inTargetedModificationData
				.getBlastocystName());

		// Is it conditional?
		if (inTargetedModificationData.getConditionedBy() != null) {

			// Conditional, Conditional Description
			Conditionality theConditionality = null;
			if (inTargetedModification.getConditionality() != null) {
				theConditionality = inTargetedModification.getConditionality();
			} else {
				theConditionality = new Conditionality();
			}

			if (inTargetedModificationData.getConditionedBy().equals(
					Constants.CONDITIONAL)) {
				theConditionality.setConditionedBy("1");
			} else {
				theConditionality.setConditionedBy("0");
			}

			theConditionality.setDescription(inTargetedModificationData
					.getDescription());
			inTargetedModification.setConditionality(theConditionality);
		} else {
			inTargetedModification.setConditionality(null);
		}

		// Upload Construct Map
		// Check for exisiting Image for this TargetedModification
		if (inTargetedModification.getImage() != null) {
			Image image = inTargetedModification.getImage();
			image.setTitle(inTargetedModificationData.getTitle());
			image.setDescription(inTargetedModificationData
					.getDescriptionOfConstruct());
			inTargetedModification.setImage(image);
		}

		// Upload Construct File location, Title of Construct, Description of
		// Construct
		// Check for exisiting Image for this GenomicSegment
		if (inTargetedModificationData.getFileLocation() != null)
			if (inTargetedModificationData.getFileLocation().getFileName() != null
					&& !inTargetedModificationData.getFileLocation()
							.getFileName().equals("")) {

				ImageForm inImageData = new ImageForm();

				String inPath = request.getSession().getServletContext()
						.getRealPath("/config/temp.jpg");

				inImageData.setDescriptionOfConstruct(inTargetedModificationData.getDescriptionOfConstruct());
				inImageData.setTitle(inTargetedModificationData.getTitle());
				inImageData.setUrl(inTargetedModificationData.getUrl());
				inImageData.setFileLocation(inTargetedModificationData.getFileLocation());

				Image image = ImageManagerSingleton.instance().create(
						new AnimalModel(), inImageData, inPath,
						Constants.CaImage.FTPGENCONSTORAGEDIRECTORY);

				log.debug("Image info: \ndescription:"
						+ image.getDescription() + " \ntitle:"
						+ image.getTitle() + " \nname:"
						+ image.getUrl() + " \nid:"
						+ image.getId());
				inTargetedModification.setImage(image);
			}



		inTargetedModification.setComments(inTargetedModificationData
				.getComments());
        
        inTargetedModification.setConstructSequence(inTargetedModificationData.getConstructSequence());

		log.debug("Exiting populateTargetedModification");
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