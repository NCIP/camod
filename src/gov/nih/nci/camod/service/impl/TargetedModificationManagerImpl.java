/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Conditionality;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.ModificationType;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.service.TargetedModificationManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.TargetedModificationData;

import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TargetedModificationManagerImpl extends BaseManager implements
		TargetedModificationManager {

	public List getAll() throws Exception {
		log.trace("In TargetedModificationManagerImpl.getAll");
		return super.getAll(TargetedModification.class);
	}

	public TargetedModification get(String id) throws Exception {
		log.trace("In TargetedModificationManagerImpl.get");
		return (TargetedModification) super.get(id, TargetedModification.class);
	}

	public void save(TargetedModification TargetedModification)
			throws Exception {
		log.trace("In TargetedModificationManagerImpl.save");
		super.save(TargetedModification);
	}

	public void remove(String id) throws Exception {
		log.trace("In TargetedModificationManagerImpl.remove");
		super.remove(id, TargetedModification.class);
	}

	public TargetedModification create(	TargetedModificationData inTargetedModificationData)
			throws Exception {

		log.trace("Entering TargetedModificationManagerImpl.create");

		TargetedModification theTargetedModification = new TargetedModification();

		populateTargetedModification(inTargetedModificationData, theTargetedModification);
		
		log.trace("Exiting TargetedModificationManagerImpl.create");

		return theTargetedModification;
	}

	public void update(TargetedModificationData inTargetedModificationData,	TargetedModification theTargetedModification) 
		throws Exception {

		log.trace("Entering TargetedModificationManagerImpl.update");
		log.debug("Updating TargetedModificationForm: "
				+ theTargetedModification.getId());

		// Populate w/ the new values and save
		populateTargetedModification(inTargetedModificationData, theTargetedModification);
		

		save(theTargetedModification);

		log.trace("Exiting TargetedModificationManagerImpl.update");
	}

	private void populateTargetedModification( TargetedModificationData inTargetedModificationData,	TargetedModification theTargetedModification) 
		throws Exception {

		log.trace("Entering populateTargetedModification");

		// set Targeted Gene/Locus
		theTargetedModification.setName( inTargetedModificationData.getName() );

		// Type of Modification
		ModificationType inModificationType = null;
		if ( theTargetedModification.getModificationTypeCollection().size() > 0 )
			inModificationType = (ModificationType) theTargetedModification.getModificationTypeCollection().get(0);
		else
			inModificationType = new ModificationType();
		
		inModificationType.setName(inTargetedModificationData.getModificationType());
		
		if ( theTargetedModification.getModificationTypeCollection().size() < 1 )			
			theTargetedModification.addModificationType(inModificationType);

		// Other - Type of Modification
		if (inTargetedModificationData.getOtherModificationType() != null) {
			if (!inTargetedModificationData.getOtherModificationType().equals("")) {

				log.trace("Sending Notification eMail - new Targeted Modification added");
				ResourceBundle theBundle = ResourceBundle.getBundle("camod");

				// Iterate through all the reciepts in the config file
				String recipients = theBundle.getString(Constants.EmailMessage.RECIPIENTS);
				StringTokenizer st = new StringTokenizer(recipients, ",");
				String inRecipients[] = new String[st.countTokens()];

				for (int i = 0; i < inRecipients.length; i++)
					inRecipients[i] = st.nextToken();

				String inSubject = theBundle
						.getString(Constants.EmailMessage.SUBJECT);
				String inMessage = theBundle
						.getString(Constants.EmailMessage.MESSAGE)
						+ " Targeted Modification added ( "
						+ inTargetedModificationData.getOtherModificationType()
						+ " ) and is awaiting your approval.";
				String inFrom = theBundle.getString(Constants.EmailMessage.FROM);
				// theBundle.getString(Constants.EmailMessage.SENDER);

				// Send the email
				try {
					log	.trace("Sending Notification eMail - new Targeted Modification added");
					MailUtil.sendMail(inRecipients, inSubject, inMessage,inFrom);
					log.trace("Notification eMail sent");
				} catch (Exception e) {
					log.trace("Caught exception " + e);
					// System.out.println("Caught exception" + e);
					e.printStackTrace();
				}

				// 2. Set flag, this Strain will need to be approved before
				// being added the list
				theTargetedModification.setModTypeUnctrlVocab(inTargetedModificationData.getOtherModificationType());
			}
		}

		// Gene Id
		theTargetedModification.setGeneId(inTargetedModificationData.getGeneId());

		// Genetic Background - Donor
		theTargetedModification.setEsCellLineName(inTargetedModificationData.getEsCellLineName());

		// Genetic Background - Recipient
		theTargetedModification.setBlastocystName(inTargetedModificationData.getBlastocystName());

		// Conditional?
		// Check for exisiting Conditionality for this TargetedModification
		Conditionality inConditionality = null;
		if (theTargetedModification.getConditionality() != null)
			inConditionality = theTargetedModification.getConditionality();
		else
			inConditionality = new Conditionality();

		inConditionality.setConditionedBy(inTargetedModificationData.getConditionedBy());
		inConditionality.setDescription(inTargetedModificationData.getDescription());
		theTargetedModification.setConditionality(inConditionality);

		// Upload Construct Map
		// Check for exisiting Image for this TargetedModification
		Image image = null;
		if (theTargetedModification.getImage() != null)
			image = theTargetedModification.getImage();
		else
			image = new Image();

		image.setFileServerLocation(inTargetedModificationData.getFileServerLocation());
		image.setTitle(inTargetedModificationData.getTitle());
		image.setDescription(inTargetedModificationData.getDescriptionOfConstruct());
		theTargetedModification.setImage(image);

		// MGI Number
		// Check for exisiting MutationIdentifier
		MutationIdentifier inMutationIdentifier = null;
		if (theTargetedModification.getMutationIdentifier() != null)
			inMutationIdentifier = theTargetedModification.getMutationIdentifier();
		else
			inMutationIdentifier = new MutationIdentifier();

		String strNumberMGI = inTargetedModificationData.getNumberMGI().trim();
		Pattern p = Pattern.compile("[0-9]{" + strNumberMGI.length() + "}");
		Matcher m = p.matcher(strNumberMGI);
		if (m.matches() && strNumberMGI != null && !strNumberMGI.equals("")) {
			inMutationIdentifier.setNumberMGI(Long.valueOf(strNumberMGI));
			theTargetedModification.setMutationIdentifier(inMutationIdentifier);
		}

		theTargetedModification.setComments(inTargetedModificationData.getComments());

		log.trace("Exiting populateTargetedModification");
	}
}