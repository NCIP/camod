/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.TargetedModificationManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.ImageForm;
import gov.nih.nci.camod.webapp.form.TargetedModificationData;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;


public class TargetedModificationManagerImpl extends BaseManager implements TargetedModificationManager {

    public List getAll() throws Exception {
        log.trace("In TargetedModificationManagerImpl.getAll");
        return super.getAll(TargetedModification.class);
    }

    public TargetedModification get(String id) throws Exception {
        log.trace("In TargetedModificationManagerImpl.get");
        return (TargetedModification) super.get(id, TargetedModification.class);
    }

    public void save(TargetedModification TargetedModification) throws Exception {
        log.trace("In TargetedModificationManagerImpl.save");
        super.save(TargetedModification);
    }

    public void remove(String id) throws Exception {
        log.trace("In TargetedModificationManagerImpl.remove");
        super.remove(id, TargetedModification.class);
    }

    public TargetedModification create(AnimalModel inAnimalModel, TargetedModificationData inTargetedModificationData, HttpServletRequest request)
            throws Exception {

        log.trace("Entering TargetedModificationManagerImpl.create");

        TargetedModification theTargetedModification = new TargetedModification();

        populateTargetedModification(inAnimalModel, inTargetedModificationData, theTargetedModification, request);

        log.trace("Exiting TargetedModificationManagerImpl.create");

        return theTargetedModification;
    }

    public void update(AnimalModel inAnimalModel, TargetedModificationData inTargetedModificationData,
            TargetedModification theTargetedModification, HttpServletRequest request) throws Exception {

        log.trace("Entering TargetedModificationManagerImpl.update");
        log.debug("Updating TargetedModificationForm: " + theTargetedModification.getId());

        // Populate w/ the new values and save
        populateTargetedModification(inAnimalModel, inTargetedModificationData, theTargetedModification, request);

        save(theTargetedModification);

        log.trace("Exiting TargetedModificationManagerImpl.update");
    }

    private void populateTargetedModification(AnimalModel inAnimalModel, TargetedModificationData inTargetedModificationData,
            TargetedModification theTargetedModification, HttpServletRequest request) throws Exception {

        log.trace("Entering populateTargetedModification");

        // set Targeted Gene/Locus
        theTargetedModification.setName(inTargetedModificationData.getName());

        if (!inTargetedModificationData.getModificationType().equals(Constants.Dropdowns.OTHER_OPTION)) {
            ModificationType theModificationType = ModificationTypeManagerSingleton.instance().getByName(
                    inTargetedModificationData.getModificationType());

            if (!theTargetedModification.getModificationTypeCollection().contains(theModificationType)) {
                theTargetedModification.addModificationType(theModificationType);
            }
        }
        // Other - Type of Modification
        if (inTargetedModificationData.getOtherModificationType() != null) {
            if (!inTargetedModificationData.getOtherModificationType().equals("")) {

                // Remove all the targeted modifications from the list
                theTargetedModification.getModificationTypeCollection().clear();

                log.trace("Sending Notification eMail - new Targeted Modification added");

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
                values.put("type", "TargetedModification");
                values.put("value", inTargetedModificationData.getOtherModificationType());
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
        if (theTargetedModification.getImage() != null) {
            Image image = theTargetedModification.getImage();
            image.setTitle(inTargetedModificationData.getTitle());
            image.setDescription(inTargetedModificationData.getDescriptionOfConstruct());
            theTargetedModification.setImage(image);
        }

        // Upload Construct File location, Title of Construct, Description of
        // Construct
        // Check for exisiting Image for this GenomicSegment
        if (inTargetedModificationData.getFileLocation().getFileName() != null && ! inTargetedModificationData.getFileLocation().getFileName().equals( "" )) {
        	        	        
        	ImageForm inImageData = new ImageForm();
        	
        	String inPath = request.getSession().getServletContext().getRealPath("/config/temp.jpg");
        	
        	inImageData.setDescriptionOfConstruct( inTargetedModificationData.getDescriptionOfConstruct());
        	inImageData.setTitle( inTargetedModificationData.getTitle() );
        	inImageData.setFileServerLocation( inTargetedModificationData.getFileServerLocation() );
        	inImageData.setFileLocation( inTargetedModificationData.getFileLocation() );
        	
        	Image image = ImageManagerSingleton.instance().create( new AnimalModel(), inImageData, inPath, Constants.CaImage.FTPGENCONSTORAGEDIRECTORY );
        	
        	System.out.println( "Image info: \ndescription:" + image.getDescription() + " \ntitle:" + image.getTitle() + " \nname:" + image.getFileServerLocation() + " \nid:" + image.getId() );
        	theTargetedModification.setImage(image);        	
        }

		// MGI Number
		// Check for exisiting MutationIdentifier
		MutationIdentifier inMutationIdentifier = null;
		if (theTargetedModification.getMutationIdentifier() != null)
			inMutationIdentifier = theTargetedModification.getMutationIdentifier();
		else
			inMutationIdentifier = new MutationIdentifier();

		if ( inTargetedModificationData.getNumberMGI() == null || inTargetedModificationData.getNumberMGI().equals( "" ))	{
			theTargetedModification.setMutationIdentifier( null );
		} else {
			String strNumberMGI = inTargetedModificationData.getNumberMGI().trim();
			Pattern p = Pattern.compile("[0-9]{" + strNumberMGI.length() + "}");
			Matcher m = p.matcher(strNumberMGI);
			if (m.matches() && strNumberMGI != null && !strNumberMGI.equals("")) {
				inMutationIdentifier.setNumberMGI(Long.valueOf(strNumberMGI));
				theTargetedModification.setMutationIdentifier(inMutationIdentifier);
			}
		}

        theTargetedModification.setComments(inTargetedModificationData.getComments());

        log.trace("Exiting populateTargetedModification");
    }
}