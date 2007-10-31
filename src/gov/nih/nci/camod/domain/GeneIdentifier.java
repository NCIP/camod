/*
 * $Id: GeneIdentifier.java,v 1.1 2007-10-31 16:10:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author pandyas
 */
public class GeneIdentifier extends BaseObject implements Serializable, Duplicatable, Comparable
{
    private static final long serialVersionUID = 3259355453799404851L;

    private String entrezGeneID;

	/**
	 * @return the entrezGeneID
	 */
	public String getEntrezGeneID() {
		return entrezGeneID;
	}

	/**
	 * @param entrezGeneID the entrezGeneID to set
	 */
	public void setEntrezGeneID(String entrezGeneID) {
		this.entrezGeneID = entrezGeneID;
	}

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getEntrezGeneID();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final GeneIdentifier obj = (GeneIdentifier) o;
        if (HashCodeUtil.notEqual(this.getEntrezGeneID(), obj.getEntrezGeneID()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getEntrezGeneID());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof GeneIdentifier) && (this.getEntrezGeneID() != null) && (((GeneIdentifier) o).getEntrezGeneID() != null))
        {
            int result = this.getEntrezGeneID().compareTo(((GeneIdentifier) o).getEntrezGeneID());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

}
