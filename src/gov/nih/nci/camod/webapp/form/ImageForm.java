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
public class ImageForm extends BaseForm implements Serializable {
    
    private static final long serialVersionUID = 3257195453799404851L;
    
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public ImageForm() {}
	
	protected String fileServerLocation;
	protected String title;
	protected String description;
	protected String staining;
	protected String otherStaining;

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

	/**
	 * @return Returns the staining.
	 */
	public String getStaining() {
		return staining;
	}
	/**
	 * @param staining The staining to set.
	 */
	public void setStaining(String staining) {
		this.staining = staining;
	}
	/**
	 * @return Returns the otherStaining.
	 */
	public String getOtherStaining() {
		return otherStaining;
	}
	/**
	 * @param otherStaining The otherStaining to set.
	 */
	public void setOtherStaining(String otherStaining) {
		this.otherStaining = otherStaining;
	}	
}
