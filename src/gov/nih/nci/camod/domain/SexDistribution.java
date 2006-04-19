/*
 * $Id: SexDistribution.java,v 1.6 2006-04-19 17:37:37 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;


/**
 * @author rajputs
 */
public class SexDistribution extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 3258635453799404851L;

    private String type;

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
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getType();
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
