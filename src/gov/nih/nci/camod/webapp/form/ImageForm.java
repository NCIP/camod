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
public class ImageForm extends BaseForm implements Serializable, ImageData {
    
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
	protected String DescriptionOfConstruct;
	protected FormFile fileLocation;	
	protected String staining;
	protected String otherStaining;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getOtherStaining() {
		return otherStaining;
	}
	public void setOtherStaining(String otherStaining) {
		this.otherStaining = otherStaining;
	}
	public String getStaining() {
		return staining;
	}
	public void setStaining(String staining) {
		this.staining = staining;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
}
