/*
 * $Id: TargetedModificationForm.java,v 1.12 2007-03-26 12:03:10 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

/**
 * 
 * $Id: TargetedModificationForm.java,v 1.12 2007-03-26 12:03:10 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */
public class TargetedModificationForm extends BaseForm implements Serializable, TargetedModificationData
{

    private static final long serialVersionUID = 3257085453799404851L;

    protected String name;
    protected String[] modificationType;
    protected String otherModificationType;
    protected String geneId;
    protected String esCellLineName;
    protected String blastocystName;
    protected String conditionedBy;
    protected String description;
    protected String comments;
    protected String mgiNumber;
	protected String zfinNumber;
	protected String rgdNumber;     
    protected String fileServerLocation;
    protected FormFile fileLocation;
    protected String title;
    protected String descriptionOfConstruct;
    protected String modificationId;
    protected String imageUrl;
    protected String thumbUrl;
    protected String constructSequence;

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the modificationType.
     */
    public String[] getModificationType()
    {
        return modificationType;
    }

    /**
     * @param modificationType The modificationType to set.
     */
    public void setModificationType(String[] modificationType)
    {
        this.modificationType = modificationType;
    }

    /**
     * @return Returns the otherModificationType.
     */
    public String getOtherModificationType()
    {
        return otherModificationType;
    }

    /**
     * @param otherModificationType The otherModificationType to set.
     */
    public void setOtherModificationType(String otherModificationType)
    {
        this.otherModificationType = otherModificationType;
    }

    /**
     * @return Returns the geneId.
     */
    public String getGeneId()
    {
        return geneId;
    }

    /**
     * @param geneId The geneId to set.
     */
    public void setGeneId(String geneId)
    {
        this.geneId = geneId;
    }

    /**
     * @return Returns the esCellLineName.
     */
    public String getEsCellLineName()
    {
        return esCellLineName;
    }

    /**
     * @param esCellLineName The esCellLineName to set.
     */
    public void setEsCellLineName(String esCellLineName)
    {
        this.esCellLineName = esCellLineName;
    }

    /**
     * @return Returns the blastocystName.
     */
    public String getBlastocystName()
    {
        return blastocystName;
    }

    /**
     * @param blastocystName The blastocystName to set.
     */
    public void setBlastocystName(String blastocystName)
    {
        this.blastocystName = blastocystName;
    }

    /**
     * @return Returns the conditionedBy.
     */
    public String getConditionedBy()
    {
        return conditionedBy;
    }

    /**
     * @param conditionedBy The conditionedBy to set.
     */
    public void setConditionedBy(String conditionedBy)
    {
        this.conditionedBy = conditionedBy;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return Returns the comments.
     */
    public String getComments()
    {
        return comments;
    }

    /**
     * @param comments The comments to set.
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }

    /**
     * @return Returns the mgiNumber.
     */
    public String getMgiNumber()
    {
        return mgiNumber;
    }

    /**
     * @param mgiNumber The mgiNumber to set.
     */
    public void setMgiNumber(String mgiNumber)
    {
        this.mgiNumber = mgiNumber;
    }

    public String getFileServerLocation()
    {
        return fileServerLocation;
    }

    public void setFileServerLocation(String fileServerLocation)
    {
        this.fileServerLocation = fileServerLocation;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescriptionOfConstruct()
    {
        return descriptionOfConstruct;
    }

    public void setDescriptionOfConstruct(String descriptionOfConstruct)
    {
        this.descriptionOfConstruct = descriptionOfConstruct;
    }

    public FormFile getFileLocation()
    {
        return fileLocation;
    }

    public void setFileLocation(FormFile fileLocation)
    {
        this.fileLocation = fileLocation;
    }

    public String getModificationId()
    {
        return modificationId;
    }

    public void setModificationId(String modificationId)
    {
        this.modificationId = modificationId;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getThumbUrl()
    {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl)
    {
        this.thumbUrl = thumbUrl;
    }

    /**
     * @return Returns the constructSequence.
     */
    public String getConstructSequence()
    {
        return constructSequence;
    }

    /**
     * @param constructSequence The constructSequence to set.
     */
    public void setConstructSequence(String constructSequence)
    {
        this.constructSequence = constructSequence;
    }

	public String getRgdNumber() {
		return rgdNumber;
	}

	public void setRgdNumber(String rgdNumber) {
		this.rgdNumber = rgdNumber;
	}

	public String getZfinNumber() {
		return zfinNumber;
	}

	public void setZfinNumber(String zfinNumber) {
		this.zfinNumber = zfinNumber;
	}
}
