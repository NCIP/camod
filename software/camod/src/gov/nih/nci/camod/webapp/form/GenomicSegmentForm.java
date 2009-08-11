/*
 * $Id: GenomicSegmentForm.java,v 1.14 2007-07-31 12:02:04 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.13  2007/04/04 13:23:37  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.12  2007/03/26 12:03:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.11  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;


/**
 * 
 * $Id: GenomicSegmentForm.java,v 1.14 2007-07-31 12:02:04 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.13  2007/04/04 13:23:37  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.12  2007/03/26 12:03:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.11  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */
public class GenomicSegmentForm extends BaseForm implements Serializable, GenomicSegmentData
{

    private static final long serialVersionUID = 3257255453799404851L;

    /**
     * Default empty constructor
     * @author rajputs
     */
    public GenomicSegmentForm()
    {}

    protected String isRandom;    
    protected String locationOfIntegration;
    protected String segmentName;
    protected String otherSegmentName;
    protected String segmentSize;
    protected String cloneDesignator;
    protected String comments;
    protected String mgiId;
	protected String zfinId;
	protected String rgdId;    
    protected String url;
    protected FormFile fileLocation;
    protected String title;
    protected String description;
    protected String DescriptionOfConstruct;
    protected String genes;
    protected String markers;
    protected String segmentId;
    protected String imageUrl;
    protected String thumbUrl;
    protected String constructSequence;

    
    /**
     * @return Returns the isRandom.
     */
    public String getIsRandom()
    {
        return isRandom;
    }

    /**
     * @param isRandom The isRandom to set.
     */
    public void setIsRandom(String isRandom)
    {
        this.isRandom = isRandom;
    }    
    /**
     * @return Returns the locationOfIntegration.
     */
    public String getLocationOfIntegration()
    {
        return locationOfIntegration;
    }

    /**
     * @param locationOfIntegration The locationOfIntegration to set.
     */
    public void setLocationOfIntegration(String locationOfIntegration)
    {
        this.locationOfIntegration = locationOfIntegration;
    }



    /**
     * @return Returns the segmentName.
     */
    public String getSegmentName()
    {
        return segmentName;
    }

    /**
     * @param segmentName The segmentName to set.
     */
    public void setSegmentName(String segmentName)
    {
        this.segmentName = segmentName;
    }

    /**
     * @return Returns the otherSegmentName.
     */
    public String getOtherSegmentName()
    {
        return otherSegmentName;
    }

    /**
     * @param otherSegmentName The otherSegmentName to set.
     */
    public void setOtherSegmentName(String otherSegmentName)
    {
        this.otherSegmentName = otherSegmentName;
    }

    /**
     * @return Returns the segmentSize.
     */
    public String getSegmentSize()
    {
        return segmentSize;
    }

    /**
     * @param segmentSize The segmentSize to set.
     */
    public void setSegmentSize(String segmentSize)
    {
        this.segmentSize = segmentSize;
    }

    /**
     * @return Returns the cloneDesignator.
     */
    public String getCloneDesignator()
    {
        return cloneDesignator;
    }

    /**
     * @param cloneDesignator The cloneDesignator to set.
     */
    public void setCloneDesignator(String cloneDesignator)
    {
        this.cloneDesignator = cloneDesignator;
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
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title The title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
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

    public String getGenes()
    {
        return genes;
    }

    public void setGenes(String genes)
    {
        this.genes = genes;
    }

    public String getMarkers()
    {
        return markers;
    }

    public void setMarkers(String markers)
    {
        this.markers = markers;
    }

    public String getDescriptionOfConstruct()
    {
        return DescriptionOfConstruct;
    }

    public void setDescriptionOfConstruct(String descriptionOfConstruct)
    {
        DescriptionOfConstruct = descriptionOfConstruct;
    }

    public FormFile getFileLocation()
    {
        return fileLocation;
    }

    public void setFileLocation(FormFile fileLocation)
    {
        this.fileLocation = fileLocation;
    }

    public String getSegmentId()
    {
        return segmentId;
    }

    public void setSegmentId(String segmentId)
    {
        this.segmentId = segmentId;
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

	public String getMgiId() {
		return mgiId;
	}

	public void setMgiId(String mgiId) {
		this.mgiId = mgiId;
	}

	public String getRgdId() {
		return rgdId;
	}

	public void setRgdId(String rgdId) {
		this.rgdId = rgdId;
	}

	public String getZfinId() {
		return zfinId;
	}

	public void setZfinId(String zfinId) {
		this.zfinId = zfinId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
