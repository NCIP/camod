/*
 * $Id: TumorCode.java,v 1.8 2007-07-31 12:03:28 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.6  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;

import java.io.Serializable;

/**
 * @author rajputs
 */
public class TumorCode extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3258465453799404851L;

    private String abbreviation;
    private String description;

    /**
     * @return Returns the abbreviation.
     */
    public String getAbbreviation()
    {
        return abbreviation;
    }

    /**
     * @param abbreviation
     *            The abbreviation to set.
     */
    public void setAbbreviation(String abbreviation)
    {
        this.abbreviation = abbreviation;
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

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getAbbreviation();
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
