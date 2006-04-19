/*
 * $Id: ModelSection.java,v 1.6 2006-04-19 17:37:37 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;


/**
 * @author pandyas
 */
public class ModelSection extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 3259175453799404851L;

    private String name;

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getName();
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
