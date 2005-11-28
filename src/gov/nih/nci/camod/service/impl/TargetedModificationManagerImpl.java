/**
 * 
 * $Id: TargetedModificationManagerImpl.java,v 1.20 2005-11-28 13:46:53 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
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
        log.info("In TargetedModificationManagerImpl.save");
        super.save(TargetedModification);
    }

    public void remove(String id, AnimalModel inAnimalModel) throws Exception {
        log.info("In TargetedModificationManagerImpl.remove");
        inAnimalModel.getEngineeredGeneCollection().remove(get(id));
        super.save(inAnimalModel);
    }

    public TargetedModification create(AnimalModel inAnimalModel, TargetedModificationData inTargetedModificationData, HttpServletRequest request)
            throws Exception {

        log.info("Entering TargetedModificationManagerImpl.create");

        TargetedModification theTargetedModification = new TargetedModification();

        populateTargetedModification(inAnimalModel, inTargetedModificationData, theTargetedModification, request);

        log.info("Exiting TargetedModificationManagerImpl.create");

        return theTargetedModification;
    }

    public void update(AnimalModel inAnimalModel, TargetedModificationData inTargetedModificationData,
            TargetedModification theTargetedModification, HttpServletRequest request) throws Exception {

        log.info("Entering TargetedModificationManagerImpl.update");
        log.info("Updating TargetedModificationForm: " + theTargetedModification.getId());

        // Populate w/ the new values and save
        populateTargetedModification(inAnimalModel, inTargetedModificationData, theTargetedModification, request);

        save(theTargetedModification);

        log.info("Exiting TargetedModificationManagerImpl.update");
    }

    private void populateTargetedModification(AnimalModel inAnimalModel, TargetedModificationData inTargetedModificationData,
            TargetedModification theTargetedModification, HttpServletRequest request) throws Exception {

        log.info("Entering populateTargetedModification");

        // set Targeted Gene/Locus
        theTargetedModification.setName(inTargetedModificationData.getName());

        // Get Modification Types
        String[] theModificationTypes = inTargetedModificationData.getModificationType();

        List theCurrentModificationTypeList = theTargetedModification.getModificationTypeCollection();
        theCurrentModificationTypeList.clear();

        //if other is one of the (multiple) selections
        if (Arrays.asList(theModificationTypes).contains(Constants.Dropdowns.OTHER_OPTION)) {
        	log.info("the Modification Type selection(s) includes other ");
            for (int i = 0; i < theModificationTypes.length; i++)   {
            	ModificationType modificationType = ModificationTypeManagerSingleton.instance().getByName(
                		theModificationTypes[i]);
            	log.info("theModificationTypes[i] (containing other): " + theModificationTypes[i]);
                
            	//Add an other selection and send e-mail
                if (theModificationTypes[i].equals(Constants.Dropdowns.OTHER_OPTION)) {
                	
            		theTargetedModification.setModTypeUnctrlVocab(inTargetedModificationData.getOtherModificationType());
            		
                	// Add selections if not already in DB (No duplicates - during editing phase)
                	if (!theTargetedModification.getModificationTypeCollection().contains(modificationType)) {
                    	//add id for other into the bridge table  
                		theCurrentModificationTypeList.add(modificationType);
                		
                		// Send e-mail: 
                        log.info("Sending Notification eMail - new Targeted Modification added");

                        ResourceBundle theBundle = ResourceBundle.getBundle("camod");

                        // Iterate through all the reciepts in the config file
                        String recipients = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_NOTIFY_KEY);
                        StringTokenizer st = new StringTokenizer(recipients, ",");
                        String inRecipients[] = new String[st.countTokens()];
                        for (int j = 0; j < inRecipients.length; j++) {
                            inRecipients[j] = st.nextToken();
                        }

                        String inSubject = theBundle.getString(Constants.BundleKeys.NEW_UNCONTROLLED_VOCAB_SUBJECT_KEY);
                        String inFrom = inAnimalModel.getSubmitter().getEmailAddress();

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
                    }
                } else {
                		// Add the non-other selection(s) 
                		//log.error("modification type name is not other: " + modificationType);
                		// Add selections if not already in DB (No duplicates - during editing phase)
                		if (!theTargetedModification.getModificationTypeCollection().contains(modificationType)) {                	
                			theCurrentModificationTypeList.add(modificationType);
                		}
                }
            }   //end of for loop
        } else {
        		log.error("the Modification Type selection(s) does not include other ");
        		//clear out the uncontrolledVocab if editing model
        		theTargetedModification.setModTypeUnctrlVocab("");
        		//Get the selection
        		for (int i = 0; i < theModificationTypes.length; i++) {
        			ModificationType modificationType = ModificationTypeManagerSingleton.instance().getByName(
                		theModificationTypes[i]);
        			//log.error("theModificationTypes[i] (selection does not include other): " + theModificationTypes[i]);
        			// Add selections if not already in DB (No duplicates - during editing phase)
        			if (!theTargetedModification.getModificationTypeCollection().contains(modificationType)) {        		
                	theCurrentModificationTypeList.add(modificationType);
        			}   		
        		}  //end of for
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

        log.info("Exiting populateTargetedModification");
    }
}