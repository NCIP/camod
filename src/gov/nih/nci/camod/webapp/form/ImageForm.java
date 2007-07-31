/*
 * $Id: ImageForm.java,v 1.11 2007-07-31 12:02:02 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2007/04/20 17:51:03  pandyas
 * Modified to add Staining Method tree to Image submission
 *
 * Revision 1.9  2007/04/18 19:20:22  pandyas
 * Modified to add Staining Method tree to Image submission
 *
 * Revision 1.8  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.webapp.form;
/**
 * $Id: ImageForm.java,v 1.11 2007-07-31 12:02:02 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2007/04/20 17:51:03  pandyas
 * Modified to add Staining Method tree to Image submission
 *
 * Revision 1.9  2007/04/18 19:20:22  pandyas
 * Modified to add Staining Method tree to Image submission
 *
 * Revision 1.8  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.7  2005/11/07 21:57:34  georgeda
 * Changes for images
 *
 */

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

public class ImageForm extends BaseForm implements Serializable, ImageData {

    private static final long serialVersionUID = 3257195453799404851L;

    protected String url;
    protected String title;
    protected String description;
    protected String descriptionOfConstruct;
    protected FormFile fileLocation;
    protected String stainingMethod;    
    protected String stainingMethodName;
    protected String stainingMethodCode;    
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

    /**
     * @return Returns the stainingMethodCode.
     */
    public String getStainingMethodCode()
    {
        return stainingMethodCode;
    }

    /**
     * @param stainingMethodCode The stainingMethodCode to set.
     */
    public void setStainingMethodCode(String stainingMethodCode)
    {
        this.stainingMethodCode = stainingMethodCode;
    }

    /**
     * @return Returns the stainingMethodName.
     */
    public String getStainingMethodName()
    {
        return stainingMethodName;
    }

    /**
     * @param stainingMethodName The stainingMethodName to set.
     */
    public void setStainingMethodName(String stainingMethodName)
    {
        this.stainingMethodName = stainingMethodName;
    }

    /**
     * @return Returns the stainingMethod.
     */
    public String getStainingMethod()
    {
        return stainingMethod;
    }

    /**
     * @param stainingMethod The stainingMethod to set.
     */
    public void setStainingMethod(String stainingMethod)
    {
        this.stainingMethod = stainingMethod;
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
