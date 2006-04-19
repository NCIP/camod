/*
 * $Id: MutationIdentifier.java,v 1.7 2006-04-19 17:37:37 pandyas Exp $
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
public class MutationIdentifier extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259135453799404851L;

    private String mgiNumber;

    /**
     * @return Returns the mgiNumber.
     */
    public String getMgiNumber()
    {
        return mgiNumber;
    }

    /**
     * @param mgiNumber
     *            The mgiNumber to set.
     */
    public void setMgiNumber(String mgiNumber)
    {
        this.mgiNumber = mgiNumber;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        if (this.getMgiNumber() != null)
            result += this.getMgiNumber();
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
