/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GenomicSegmentForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public GenomicSegmentForm() {}
	
	// id is of type String since it comes from the presentation layer
	protected String id;
	protected String locationOfIntegration;
	protected String segmentSize;
	protected String cloneDesignator;
	
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
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
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
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
    }
}
