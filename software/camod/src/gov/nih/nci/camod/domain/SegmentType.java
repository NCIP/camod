/*
 * $Id: SegmentType.java,v 1.10 2007-10-31 15:35:21 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2006/04/19 17:37:37  pandyas
 * Removed TODO text
 *
 * Revision 1.8  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 */
public class SegmentType extends BaseObject implements Serializable, Duplicatable, Comparable
{
    private static final long serialVersionUID = 3258655453799404851L;

    private String name;
    private String nameAlternEntry;

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
        final SegmentType obj = (SegmentType) o;
        if (HashCodeUtil.notEqual(this.getName(), obj.getName()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getName());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof SegmentType) && (this.getName() != null) && (((SegmentType) o).getName() != null))
        {
            int result = this.getName().compareTo(((SegmentType) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

	/**
	 * @return the nameAlternEntry
	 */
	public String getNameAlternEntry() {
		return nameAlternEntry;
	}

	/**
	 * @param nameAlternEntry the nameAlternEntry to set
	 */
	public void setNameAlternEntry(String nameAlternEntry) {
		this.nameAlternEntry = nameAlternEntry;
	}
}
