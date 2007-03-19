/*
 * $Id: MutationIdentifier.java,v 1.8 2007-03-19 18:56:11 pandyas Exp $
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

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 */
public class MutationIdentifier extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259135453799404851L;

    private String mgiNumber;
    private String zfinNumber;
    private String rgdNumber;

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
     * @return Returns the zfinNumber.
     */
    public String getZfinNumber()
    {
        return zfinNumber;
    }

    /**
     * @param zfinNumber
     *            The zfinNumber to set.
     */
    public void setZfinNumber(String zfinNumber)
    {
        this.zfinNumber = zfinNumber;
    } 
    
    /**
     * @return Returns the rgdNumber.
     */
    public String getRgdNumber()
    {
        return rgdNumber;
    }

    /**
     * @param rgdNumber
     *            The rgdNumber to set.
     */
    public void setRgdNumber(String rgdNumber)
    {
        this.rgdNumber = rgdNumber;
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
