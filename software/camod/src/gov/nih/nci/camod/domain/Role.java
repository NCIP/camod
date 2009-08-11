/*
 * $Id: Role.java,v 1.7 2006-04-18 16:27:17 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.*;
import gov.nih.nci.camod.util.HashCodeUtil;


/**
 * 
 * $Id: Role.java,v 1.7 2006-04-18 16:27:17 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 * 
 */
public class Role extends BaseObject implements Serializable, Comparable
{
    private static final long serialVersionUID = 3258695453799404851L;

    private String name;
    private Set<Party> partyCollection = new TreeSet<Party>();

    /**
     * @return Returns the partyCollection.
     */
    public Set<Party> getPartyCollection()
    {
        return partyCollection;
    }

    /**
     * @param partyCollection
     *            The partyCollection to set.
     */
    public void setPartyCollection(Set<Party> partyCollection)
    {
        this.partyCollection = partyCollection;
    }

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
        final Role obj = (Role) o;
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
        if ((o instanceof Role) && (this.getName() != null) && (((Role) o).getName() != null))
        {
            int result = this.getName().compareTo(((Role) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }
}
