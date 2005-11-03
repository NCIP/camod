/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;



/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Party extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259045453799404851L;
    
    private List contactInfoCollection = new ArrayList();
    private List roleCollection = new ArrayList();

    /**
     * @return Returns the roleCollection.
     */
    public List getRoleCollection() {
        return roleCollection;
    }
    
    public List getRoleCollectionSorted() {      
      if (roleCollection != null) return new ArrayList(new TreeSet(roleCollection));
      return null;
    }       

    /**
     * @param roleCollection
     *            The roleCollection to set.
     */
    public void setRoleCollection(List roleCollection) {
        this.roleCollection = roleCollection;
    }

    /**
     * @param role
     *            The role to add.
     */
    public void addRole(Role role) {
        role.getPartyCollection().add(this);
        roleCollection.add(role);
    }
  

    /**
     * @return Returns the contactInfoCollection.
     */
    public List getContactInfoCollection() {
        return contactInfoCollection;
    }
    
    public List getContactInfoCollectionSorted() {      
      if (contactInfoCollection != null) return new ArrayList(new TreeSet(contactInfoCollection));
      return null;
    }       


    /**
     * @param contactInfoCollection
     *            The contactInfoCollection to set.
     */
    public void setContactInfoCollection(List contactInfoCollection) {
        this.contactInfoCollection = contactInfoCollection;
    }

    public void addContactInfo(ContactInfo contactInfo) {
        contactInfo.getPartyCollection().add(this);
        contactInfoCollection.add(contactInfo);
    }

    /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString();             
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
