/*
 * $Id: Publication.java,v 1.13 2008-08-14 06:23:15 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2007/10/31 15:54:46  pandyas
 * Fixed #8355 	Add comments field to every submission page
 *
 * Revision 1.11  2007/05/07 16:51:17  pandyas
 * Added zfinPubId to object and mapping for pulications from zfin.org
 *
 * Revision 1.10  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.9  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 */
public class Publication extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3258755453799404851L;

    private String volume;
    private Long endPage;
    private Long year;
    private String title;
    private Long pmid;
    private Long startPage;
    private String journal;
    private String authors;
    private Boolean firstTimeReported;
    private String jaxJNumber;
    private String zfinPubId; 
    private String rgdPubID;
    private PublicationStatus publicationStatus;
    private String comments;

    
    /**
     * @return Returns the authors.
     */
    public String getAuthors()
    {
        return authors;
    }

    /**
     * @param authors
     *            The authors to set.
     */
    public void setAuthors(String authors)
    {
        this.authors = authors;
    }

    /**
     * @return Returns the endPage.
     */
    public Long getEndPage()
    {
        return endPage;
    }

    /**
     * @param endPage
     *            The endPage to set.
     */
    public void setEndPage(Long endPage)
    {
        this.endPage = endPage;
    }


    /**
     * @return Returns the journal.
     */
    public String getJournal()
    {
        return journal;
    }

    /**
     * @param journal
     *            The journal to set.
     */
    public void setJournal(String journal)
    {
        this.journal = journal;
    }

    /**
     * @return Returns the pmid.
     */
    public Long getPmid()
    {
        return pmid;
    }

    /**
     * @param pmid
     *            The pmid to set.
     */
    public void setPmid(Long pmid)
    {
        this.pmid = pmid;
    }

    /**
     * @return Returns the publicationStatus.
     */
    public PublicationStatus getPublicationStatus()
    {
        return publicationStatus;
    }

    /**
     * @param publicationStatus
     *            The publicationStatus to set.
     */
    public void setPublicationStatus(PublicationStatus publicationStatus)
    {
        this.publicationStatus = publicationStatus;
    }

    /**
     * @return Returns the startPage.
     */
    public Long getStartPage()
    {
        return startPage;
    }

    /**
     * @param startPage
     *            The startPage to set.
     */
    public void setStartPage(Long startPage)
    {
        this.startPage = startPage;
    }

    /**
     * @return Returns the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title
     *            The title to set.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return Returns the volume.
     */
    public String getVolume()
    {
        return volume;
    }

    /**
     * @param volume
     *            The volume to set.
     */
    public void setVolume(String volume)
    {
        this.volume = volume;
    }

    /**
     * @return Returns the year.
     */
    public Long getYear()
    {
        return year;
    }

    /**
     * @param year
     *            The year to set.
     */
    public void setYear(Long year)
    {
        this.year = year;
    }
    
    /**
     * @return Returns the firstTimeReported.
     */
    public Boolean isFirstTimeReported()
    {
        return firstTimeReported;
    }

    /**
     * @param firstTimeReported
     *            The firstTimeReported to set.
     */
    public void setFirstTimeReported(Boolean firstTimeReported)
    {
        this.firstTimeReported = firstTimeReported;
    }
    
    /**
     * @return Returns the jaxJNumber.
     */
    public String getJaxJNumber()
    {
        return jaxJNumber;
    }

    /**
     * @param jaxJNumber
     *            The jaxJNumber to set.
     */
    public void setJaxJNumber(String jaxJNumber)
    {
        this.jaxJNumber = jaxJNumber;
    }
    
    /**
     * @return Returns the comments. Comment is a reserved word so 
     * we must use the plural form comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getTitle();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        return true;
    }

	public String getZfinPubId() {
		return zfinPubId;
	}

	public void setZfinPubId(String zfinPubId) {
		this.zfinPubId = zfinPubId;
	}

	public String getRgdPubID() {
		return rgdPubID;
	}

	public void setRgdPubID(String rgdPubID) {
		this.rgdPubID = rgdPubID;
	}
}
