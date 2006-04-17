/*
 * $Id: Log.java,v 1.7 2006-04-17 19:13:46 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author pandyas
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Log extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259215453799404851L;

    private String notes;
    private String type;
    private String subsystem;
    private String timestamp;
    private AbstractCancerModel cancerModel;
    private Comments comment;
    private Person submitter;

    /**
     * @return Returns the notes.
     */
    public String getNotes()
    {
        return notes;
    }

    /**
     * @param notes
     *            The notes to set.
     */
    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    /**
     * @return Returns the type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * @return Returns the subsystem.
     */
    public String getSubsystem()
    {
        return subsystem;
    }

    /**
     * @param subsystem
     *            The subsystem to set.
     */
    public void setSubsystem(String subsystem)
    {
        this.subsystem = subsystem;
    }

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
     * @return Returns the cancerModel.
     */
    public AbstractCancerModel getCancerModel()
    {
        return cancerModel;
    }

    /**
     * @param cancerModel
     *            The cancerModel to set.
     */
    public void setCancerModel(AbstractCancerModel cancerModel)
    {
        this.cancerModel = cancerModel;
    }

    /**
     * @return Returns the comment.
     */
    public Comments getComment()
    {
        return comment;
    }

    /**
     * @param comment
     *            The comment to set.
     */
    public void setComment(Comments comment)
    {
        this.comment = comment;
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
        result += this.getTimestamp() + " - " + this.getNotes();
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
