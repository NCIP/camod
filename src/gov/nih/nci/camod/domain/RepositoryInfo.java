/*
 * $Id: RepositoryInfo.java,v 1.7 2006-04-19 17:37:37 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 */
public class RepositoryInfo extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3258705453799404851L;

    private Long inTheRepository;
    private String sentEmailContent;
    private Long suggestSubmission;

    /**
     * @return Returns the inTheRepository.
     */
    public Long getInTheRepository()
    {
        return inTheRepository;
    }

    /**
     * @param inTheRepository
     *            The inTheRepository to set.
     */
    public void setInTheRepository(Long inTheRepository)
    {
        this.inTheRepository = inTheRepository;
    }

    /**
     * @return Returns the sentEmailContent.
     */
    public String getSentEmailContent()
    {
        return sentEmailContent;
    }

    /**
     * @param sentEmailContent
     *            The sentEmailContent to set.
     */
    public void setSentEmailContent(String sentEmailContent)
    {
        this.sentEmailContent = sentEmailContent;
    }

    /**
     * @return Returns the suggestSubmission.
     */
    public Long getSuggestSubmission()
    {
        return suggestSubmission;
    }

    /**
     * @param suggestSubmission
     *            The suggestSubmission to set.
     */
    public void setSuggestSubmission(Long suggestSubmission)
    {
        this.suggestSubmission = suggestSubmission;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getSentEmailContent();
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
