/*
 * $Id: Party.java,v 1.7 2006-04-17 19:13:46 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.*;

/**
 * 
 * $Id: Party.java,v 1.7 2006-04-17 19:13:46 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */
public class Party extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 3259045453799404851L;

    private Set<ContactInfo> contactInfoCollection = new TreeSet<ContactInfo>();
    private Set<Role> roleCollection = new TreeSet<Role>();

    /**
     * @return Returns the roleCollection.
     */
    public Set<Role> getRoleCollection()
    {
        return roleCollection;
    }

    /**
     * @param roleCollection
     *            The roleCollection to set.
     */
    public void setRoleCollection(Set<Role> roleCollection)
    {
        this.roleCollection = roleCollection;
    }

    /**
     * @param role
     *            The role to add.
     */
    public void addRole(Role role)
    {
        role.getPartyCollection().add(this);
        roleCollection.add(role);
    }

    /**
     * @return Returns the contactInfoCollection.
     */
    public Set getContactInfoCollection()
    {
        return contactInfoCollection;
    }

    /**
     * @param contactInfoCollection
     *            The contactInfoCollection to set.
     */
    public void setContactInfoCollection(Set<ContactInfo> contactInfoCollection)
    {
        this.contactInfoCollection = contactInfoCollection;
    }

    public void addContactInfo(ContactInfo contactInfo)
    {
        contactInfo.getPartyCollection().add(this);
        contactInfoCollection.add(contactInfo);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString();
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
