/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.SegmentType;
import gov.nih.nci.camod.service.GenomicSegmentManager;
import gov.nih.nci.camod.webapp.form.GenomicSegmentData;
import gov.nih.nci.camod.webapp.form.ImageForm;

import java.util.List;
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

    public void remove(String id) throws Exception {
        log.trace("In GenomicSegmentManagerImpl.remove");
        super.remove(id, GenomicSegment.class);
    }

    public GenomicSegment create(GenomicSegmentData inGenomicSegmentData, HttpServletRequest request) throws Exception {

        log.trace("Entering GenomicSegmentManagerImpl.create");

        GenomicSegment inGenomicSegment = new GenomicSegment();
        populateGenomicSegment(inGenomicSegmentData, inGenomicSegment, request);

        log.trace("Exiting GenomicSegmentManagerImpl.create");

        return inGenomicSegment;
    }

    public void update(GenomicSegmentData inGenomicSegmentData, GenomicSegment inGenomicSegment,
            HttpServletRequest request) throws Exception {

        log.trace("Entering GenomicSegmentManagerImpl.update");
        log.debug("Updating GenomicSegmentForm: " + inGenomicSegment.getId());

        // Populate w/ the new values and save
        populateGenomicSegment(inGenomicSegmentData, inGenomicSegment, request);
        save(inGenomicSegment);

        log.trace("Exiting GenomicSegmentManagerImpl.update");
    }

    private void populateGenomicSegment(GenomicSegmentData inGenomicSegmentData, GenomicSegment inGenomicSegment,
            HttpServletRequest request) throws Exception {

        log.trace("Entering populateGenomicSegment");

        if (inGenomicSegmentData.getLocationOfIntegration().equals("Targeted"))
            inGenomicSegment.setLocationOfIntegration(inGenomicSegmentData.getOtherLocationOfIntegration());
        else
            inGenomicSegment.setLocationOfIntegration(inGenomicSegmentData.getLocationOfIntegration());

        inGenomicSegment.setComments(inGenomicSegmentData.getComments());
        inGenomicSegment.setSegmentSize(inGenomicSegmentData.getSegmentSize());
        inGenomicSegment.setCloneDesignator(inGenomicSegmentData.getCloneDesignator());

        // SegmentType
        // TODO: Find matching existing segment type
        SegmentType inSegmentType = null;
        if (inGenomicSegment.getSegmentTypeCollection().size() > 0)
            inSegmentType = (SegmentType) inGenomicSegment.getSegmentTypeCollection().get(0);
        else
            inSegmentType = new SegmentType();

        inSegmentType.setName(inGenomicSegmentData.getSegmentName());

        // TODO: Send Email
        if (inGenomicSegmentData.getOtherSegmentName() != null) {
            inSegmentType.setNameUnctrlVocab(inGenomicSegmentData.getOtherSegmentName());
        }

        inGenomicSegment.addSegmentType(inSegmentType);

        if (inGenomicSegment.getImage() != null) {
            Image image = inGenomicSegment.getImage();
            image.setTitle(inGenomicSegmentData.getTitle());
            image.setDescription(inGenomicSegmentData.getDescriptionOfConstruct());
            inGenomicSegment.setImage(image);
        }

        // Upload Construct File location, Title of Construct, Description of
        // Construct
        // Check for exisiting Image for this GenomicSegment
        if (inGenomicSegmentData.getFileLocation() != null) {
        	        	        
        	ImageForm inImageData = new ImageForm();
        	
        	String inPath = request.getSession().getServletContext().getRealPath("/config/temp.jpg");
        	
        	inImageData.setDescriptionOfConstruct( inGenomicSegmentData.getDescriptionOfConstruct());
        	inImageData.setTitle( inGenomicSegmentData.getTitle() );
        	inImageData.setFileServerLocation( inGenomicSegmentData.getFileServerLocation() );
        	inImageData.setFileLocation( inGenomicSegmentData.getFileLocation() );
        	
        	Image image = ImageManagerSingleton.instance().create( inImageData, inPath );
        	
        	inGenomicSegment.setImage(image);        	
        }
        
		// MGI Number
		// Check for exisiting MutationIdentifier
		MutationIdentifier inMutationIdentifier = null;
		if (inGenomicSegment.getMutationIdentifier() != null)
			inMutationIdentifier = inGenomicSegment.getMutationIdentifier();
		else
			inMutationIdentifier = new MutationIdentifier();

		String strNumberMGI = inGenomicSegmentData.getNumberMGI().trim();
		Pattern p = Pattern.compile("[0-9]{" + strNumberMGI.length() + "}");
		Matcher m = p.matcher(strNumberMGI);
		if (m.matches() && strNumberMGI != null && !strNumberMGI.equals("")) {
			inMutationIdentifier.setNumberMGI(Long.valueOf(strNumberMGI));
			inGenomicSegment.setMutationIdentifier(inMutationIdentifier);
		}
        
        log.trace("Exiting populateGenomicSegment");
    }
}
