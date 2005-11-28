/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.SegmentType;
import gov.nih.nci.camod.service.GenomicSegmentManager;
import gov.nih.nci.camod.util.MailUtil;
import gov.nih.nci.camod.webapp.form.GenomicSegmentData;
import gov.nih.nci.camod.webapp.form.ImageForm;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class GenomicSegmentManagerImpl extends BaseManager implements GenomicSegmentManager {

    public List getAll() throws Exception {
        log.trace("In GenomicSegmentManagerImpl.getAll");
        return super.getAll(GenomicSegment.class);
    }

    public GenomicSegment get(String id) throws Exception {
        log.trace("In GenomicSegmentManagerImpl.get");
        return (GenomicSegment) super.get(id, GenomicSegment.class);
    }

    public void save(GenomicSegment GenomicSegment) throws Exception {
        log.trace("In GenomicSegmentManagerImpl.save");
        super.save(GenomicSegment);
    }

    public void remove(String id, AnimalModel inAnimalModel) throws Exception {
        log.trace("In GenomicSegmentManagerImpl.remove");
        inAnimalModel.getEngineeredGeneCollection().remove(get(id));
        super.save(inAnimalModel);
    }

    public GenomicSegment create(AnimalModel inAnimalModel, GenomicSegmentData inGenomicSegmentData,
            HttpServletRequest request) throws Exception {

        log.trace("Entering GenomicSegmentManagerImpl.create");

        GenomicSegment inGenomicSegment = new GenomicSegment();
        populateGenomicSegment(inAnimalModel, inGenomicSegmentData, inGenomicSegment, request);

        log.trace("Exiting GenomicSegmentManagerImpl.create");

        return inGenomicSegment;
    }

    public void update(AnimalModel inAnimalModel, GenomicSegmentData inGenomicSegmentData,
            GenomicSegment inGenomicSegment, HttpServletRequest request) throws Exception {

        log.trace("Entering GenomicSegmentManagerImpl.update");
        log.debug("Updating GenomicSegmentForm: " + inGenomicSegment.getId());

        // Populate w/ the new values and save
        populateGenomicSegment(inAnimalModel, inGenomicSegmentData, inGenomicSegment, request);
        save(inGenomicSegment);

        log.trace("Exiting GenomicSegmentManagerImpl.update");
    }

    private void populateGenomicSegment(AnimalModel inAnimalModel, GenomicSegmentData inGenomicSegmentData,
            GenomicSegment inGenomicSegment, HttpServletRequest request) throws Exception {

        log.trace("Entering populateGenomicSegment");

        if (inGenomicSegmentData.getLocationOfIntegration().equals("Targeted"))
            inGenomicSegment.setLocationOfIntegration(inGenomicSegmentData.getOtherLocationOfIntegration());
        else
            inGenomicSegment.setLocationOfIntegration(inGenomicSegmentData.getLocationOfIntegration());

        inGenomicSegment.setComments(inGenomicSegmentData.getComments());
        inGenomicSegment.setSegmentSize(inGenomicSegmentData.getSegmentSize());
        inGenomicSegment.setCloneDesignator(inGenomicSegmentData.getCloneDesignator());

        // SegmentType.  Reuse if it's aready there, update otherwise.
        SegmentType theSegmentType = null;
        if (inGenomicSegment.getSegmentTypeCollection().size() > 0) {
            theSegmentType = (SegmentType) inGenomicSegment.getSegmentTypeCollection().get(0);
        }
        else {
            theSegmentType = new SegmentType();
            inGenomicSegment.addSegmentType(theSegmentType);
        }

        theSegmentType.setName(inGenomicSegmentData.getSegmentName());

        if (inGenomicSegmentData.getOtherSegmentName() != null) {
        	theSegmentType.setName(null);
            theSegmentType.setNameUnctrlVocab(inGenomicSegmentData.getOtherSegmentName());

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
            values.put("type", "SegmentName");
            values.put("value", inGenomicSegmentData.getOtherSegmentName());
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

        if (inGenomicSegment.getImage() != null) {
            Image image = inGenomicSegment.getImage();
            image.setTitle(inGenomicSegmentData.getTitle());
            // image.setFileServerLocation( );
            image.setDescription(inGenomicSegmentData.getDescriptionOfConstruct());
            inGenomicSegment.setImage(image);
        }

        // Upload Construct File location, Title of Construct, Description of
        // Construct
        // Check for exisiting Image for this GenomicSegment
        if (inGenomicSegmentData.getFileLocation().getFileName() != null
                && !inGenomicSegmentData.getFileLocation().getFileName().equals("")) {

            ImageForm inImageData = new ImageForm();

            String inPath = request.getSession().getServletContext().getRealPath("/config/temp.jpg");

            inImageData.setDescriptionOfConstruct(inGenomicSegmentData.getDescriptionOfConstruct());
            inImageData.setTitle(inGenomicSegmentData.getTitle());
            inImageData.setFileServerLocation(inGenomicSegmentData.getFileServerLocation());
            inImageData.setFileLocation(inGenomicSegmentData.getFileLocation());

            Image image = ImageManagerSingleton.instance().create(new AnimalModel(), inImageData, inPath,
                    Constants.CaImage.FTPGENCONSTORAGEDIRECTORY);

            System.out.println("Image info: \ndescription:" + image.getDescription() + " \ntitle:" + image.getTitle()
                    + " \nname:" + image.getFileServerLocation() + " \nid:" + image.getId());
            inGenomicSegment.setImage(image);
        }

        // MGI Number
        // Check for exisiting MutationIdentifier
        MutationIdentifier inMutationIdentifier = null;
        if (inGenomicSegment.getMutationIdentifier() != null)
            inMutationIdentifier = inGenomicSegment.getMutationIdentifier();
        else
            inMutationIdentifier = new MutationIdentifier();

        if (inGenomicSegmentData.getNumberMGI() == null || inGenomicSegmentData.getNumberMGI().equals("")) {
            inGenomicSegment.setMutationIdentifier(null);
        } else {
            String strNumberMGI = inGenomicSegmentData.getNumberMGI().trim();
            Pattern p = Pattern.compile("[0-9]{" + strNumberMGI.length() + "}");
            Matcher m = p.matcher(strNumberMGI);
            if (m.matches() && strNumberMGI != null && !strNumberMGI.equals("")) {
                inMutationIdentifier.setNumberMGI(Long.valueOf(strNumberMGI));
                inGenomicSegment.setMutationIdentifier(inMutationIdentifier);
            }
        }

        log.trace("Exiting populateGenomicSegment");
    }
}
