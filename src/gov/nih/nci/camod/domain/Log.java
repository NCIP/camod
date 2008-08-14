/*
 * $Id: Log.java,v 1.10 2008-08-14 17:37:00 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2007/07/31 12:03:28  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.8  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.7  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author pandyas
 */
public class Log extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259215453799404851L;

    private String reviewNote;
    private String state;
    private String timestamp;
    private AbstractCancerModel abstractCancerModel;
    private Comments comments;
    private Person submitter;



    /**
     * @return Returns the timestamp.
     */
    public String getTimestamp()
    {
        return timestamp;
    }

    /**
     * @param timestamp
     *            The timestamp to set.
     */
    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * @return Returns the abstractCancerModel.
     */
    public AbstractCancerModel getAbstractCancerModel()
    {
        return abstractCancerModel;
    }

    /**
     * @param abstractCancerModel
     *            The abstractCancerModel to set.
     */
    public void setAbstractCancerModel(AbstractCancerModel abstractCancerModel)
    {
        this.abstractCancerModel = abstractCancerModel;
    }

    /**
     * @return Returns the submitter.
     */
    public Person getSubmitter()
    {
        return submitter;
    }

    /**
     * @param submitter
     *            The submitter to set.
     */
    public void setSubmitter(Person submitter)
    {
        this.submitter = submitter;
    }


    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getTimestamp() + " - " + this.getReviewNote();
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

    /**
     * @return Returns the comments.
     */
    public Comments getComments()
    {
        return comments;
    }

    /**
     * @param comments The comments to set.
     */
    public void setComments(Comments comments)
    {
        this.comments = comments;
    }

    /**
     * @return Returns the reviewNote.
     */
    public String getReviewNote()
    {
        return reviewNote;
    }

    /**
     * @param reviewNote The reviewNote to set.
     */
    public void setReviewNote(String reviewNote)
    {
        this.reviewNote = reviewNote;
    }

    /**
     * @return Returns the state.
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param state The state to set.
     */
    public void setState(String state)
    {
        this.state = state;
    }


}
