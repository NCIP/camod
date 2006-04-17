/*
 * $Id: Publication.java,v 1.9 2006-04-17 19:13:46 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
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
    private PublicationStatus publicationStatus;

    
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
}
