/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;
/**
 * $Id: ImageForm.java,v 1.7 2005-11-07 21:57:34 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 */

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

public class ImageForm extends BaseForm implements Serializable, ImageData {

    private static final long serialVersionUID = 3257195453799404851L;

    protected String fileServerLocation;
    protected String title;
    protected String description;
    protected String descriptionOfConstruct;
    protected FormFile fileLocation;
    protected String staining;
    protected String otherStaining;
    protected String imageId;
    protected String imageUrl;
    protected String thumbUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionOfConstruct() {
        return descriptionOfConstruct;
    }

    public void setDescriptionOfConstruct(String descriptionOfConstruct) {
        this.descriptionOfConstruct = descriptionOfConstruct;
    }

    public FormFile getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(FormFile fileLocation) {
        if (fileLocation.getFileName() == null || fileLocation.getFileName().length() == 0) {
            this.fileLocation = null;
        } else {
            this.fileLocation = fileLocation;
        }
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

    
    public String getImageId() {
        return imageId;
    }

    
    public void setImageId(String imageId) {
        this.imageId = imageId;
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
