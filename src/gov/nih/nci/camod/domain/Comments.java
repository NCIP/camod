/*
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2007/07/31 12:03:28  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.9  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.8  2005/11/14 14:16:51  georgeda
 * Cleanup
 *
 * 
 * $Id: Comments.java,v 1.11 2008-08-14 17:25:00 pandyas Exp $
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import gov.nih.nci.camod.util.Duplicatable;

public class Comments extends BaseObject implements Serializable, Curateable, Duplicatable
{

    private static final long serialVersionUID = 3259595453799404851L;

    private String remark;
    private AbstractCancerModel abstractCancerModel;
    private Availability availability;
    private ModelSection modelSection;
    private Person submitter;
    private String state;

    /**
     * @return Returns the state.
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param state
     *            The state to set.
     */
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * @return Returns the availability.
     */
    public Availability getAvailability()
    {
        return availability;
    }

    /**
     * @param availability
     *            The availability to set.
     */
    public void setAvailability(Availability availability)
    {
        this.availability = availability;
    }

    /**
     * @return Returns the modelSection.
     */
    public ModelSection getModelSection()
    {
        return modelSection;
    }

    /**
     * @param modelSection
     *            The modelSection to set.
     */
    public void setModelSection(ModelSection modelSection)
    {
        this.modelSection = modelSection;
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

    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getRemark();
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
     * @return Returns the abstractCancerModel.
     */
    public AbstractCancerModel getAbstractCancerModel()
    {
        return abstractCancerModel;
    }

    /**
     * @param abstractCancerModel The abstractCancerModel to set.
     */
    public void setAbstractCancerModel(AbstractCancerModel abstractCancerModel)
    {
        this.abstractCancerModel = abstractCancerModel;
    }

    /**
     * @return Returns the remark.
     */
    public String getRemark()
    {
        return remark;
    }

    /**
     * @param remark The remark to set.
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }



}
