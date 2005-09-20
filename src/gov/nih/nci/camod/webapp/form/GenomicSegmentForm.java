/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;


/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GenomicSegmentForm extends BaseForm implements Serializable {
    
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
	protected String title;
	protected String description;
	
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
	public String getFileServerLocation() {
		return fileServerLocation;
	}
	/**
	 * @param fileServerLocation The fileServerLocation to set.
	 */
	public void setFileServerLocation(String fileServerLocation) {
		this.fileServerLocation = fileServerLocation;
	}
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
	

}
