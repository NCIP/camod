/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;


/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GenomicSegmentForm extends BaseForm implements Serializable, GenomicSegmentData {
    
    private static final long serialVersionUID = 3257255453799404851L;
    
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public GenomicSegmentForm() {}
	
	protected String locationOfIntegration;
	protected String segmentName;
	protected String otherSegmentName;	
	protected String segmentSize;
	protected String cloneDesignator;
	protected String comments;
	protected String numberMGI;
	protected String fileServerLocation;	
	protected FormFile fileLocation;	
	protected String title;
	protected String description;
	protected String DescriptionOfConstruct;
	protected String genes;
	protected String markers;
	protected String otherLocationOfIntegration;
    protected String segmentId;
    protected String imageUrl;
    protected String thumbUrl;
    
	/**
	 * @return Returns the locationOfIntegration.
	 */
	public String getLocationOfIntegration() {
		return locationOfIntegration;
	}
	/**
	 * @param locationOfIntegration The locationOfIntegration to set.
	 */
	public void setLocationOfIntegration(String locationOfIntegration) {
		this.locationOfIntegration = locationOfIntegration;
	}
	/**
	 * @return Returns the locationOfIntegration.
	 */
	public String getOtherLocationOfIntegration() {
		return otherLocationOfIntegration;
	}
	/**
	 * @param locationOfIntegration The locationOfIntegration to set.
	 */
	public void setOtherLocationOfIntegration(String otherLocationOfIntegration) {
		this.otherLocationOfIntegration = otherLocationOfIntegration;
	}
	/**
	 * @return Returns the segmentName.
	 */
	public String getSegmentName() {
		return segmentName;
	}
	/**
	 * @param segmentName The segmentName to set.
	 */
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	/**
	 * @return Returns the otherSegmentName.
	 */
	public String getOtherSegmentName() {
		return otherSegmentName;
	}
	/**
	 * @param otherSegmentName The otherSegmentName to set.
	 */
	public void setOtherSegmentName(String otherSegmentName) {
		this.otherSegmentName = otherSegmentName;
	}	
	/**
	 * @return Returns the segmentSize.
	 */
	public String getSegmentSize() {
		return segmentSize;
	}
	/**
	 * @param segmentSize The segmentSize to set.
	 */
	public void setSegmentSize(String segmentSize) {
		this.segmentSize = segmentSize;
	}	
	/**
	 * @return Returns the cloneDesignator.
	 */
	public String getCloneDesignator() {
		return cloneDesignator;
	}
	/**
	 * @param cloneDesignator The cloneDesignator to set.
	 */
	public void setCloneDesignator(String cloneDesignator) {
		this.cloneDesignator = cloneDesignator;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the numberMGI.
	 */
	public String getNumberMGI() {
		return numberMGI;
	}
	/**
	 * @param numberMGI The numberMGI to set.
	 */
	public void setNumberMGI(String numberMGI) {
		this.numberMGI = numberMGI;
	}
	/**
	 * @return Returns the fileServerLocation.
	 */
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenes() {
		return genes;
	}
	public void setGenes(String genes) {
		this.genes = genes;
	}
	public String getMarkers() {
		return markers;
	}
	public void setMarkers(String markers) {
		this.markers = markers;
	}
	public String getDescriptionOfConstruct() {
		return DescriptionOfConstruct;
	}
	public void setDescriptionOfConstruct(String descriptionOfConstruct) {
		DescriptionOfConstruct = descriptionOfConstruct;
	}
	public FormFile getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(FormFile fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getFileServerLocation() {
		return fileServerLocation;
	}
	public void setFileServerLocation(String fileServerLocation) {
		this.fileServerLocation = fileServerLocation;
	}
    
    public String getSegmentId() {
        return segmentId;
    }
    
    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }	
	
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

}
