/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
public class Conditionality extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259575453799404851L;

    private String conditionedBy;
    private String description;

    /**
     * @return Returns the conditionedBy.
     */
    public String getConditionedBy()
    {
        return conditionedBy;
    }

    /**
     * @param conditionedBy
     *            The conditionedBy to set.
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
     * @param description
     *            The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getDescription();
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
