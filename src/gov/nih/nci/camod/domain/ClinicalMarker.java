/*
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2006/04/27 15:01:04  pandyas
 * Added displayName for left menu bar
 *
 * Revision 1.8  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 * Revision 1.7  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.6  2005/11/14 14:16:51  georgeda
 * Cleanup
 *
 * 
 * $Id: ClinicalMarker.java,v 1.10 2007-10-31 15:26:26 pandyas Exp $
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

public class ClinicalMarker extends BaseObject implements Comparable, Serializable, Duplicatable
{

    private static final long serialVersionUID = 3259615453799404851L;

    private String name;
    private String nameAlternEntry;    
    private String value;
    private String comments;    

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
     * @return Returns the display name.
     */
    public String getDisplayName()
    {
        String theDisplayName = name;
        if (theDisplayName == null && nameAlternEntry != null)
        {
            theDisplayName = "Other - " + nameAlternEntry;
        }

        return theDisplayName;
    }    
    /**
     * @return Returns the value.
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param value
     *            The value to set.
     */
    public void setValue(String value)
    {
        this.value = value;
    }
    
    /**
     * @return Returns the comments. Comment is a reserved word so 
     * we must use the plural form comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getName() + " - " + this.getValue();
        return result;
    }


    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final ClinicalMarker obj = (ClinicalMarker) o;
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
        if ((o instanceof ClinicalMarker) && (this.getName() != null) && (((ClinicalMarker) o).getName() != null))
        {
            int result = this.getName().compareTo(((ClinicalMarker) o).getName());
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
