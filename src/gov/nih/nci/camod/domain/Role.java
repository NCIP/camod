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

import gov.nih.nci.camod.util.HashCodeUtil;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Role extends BaseObject implements Serializable, Comparable {

    private static final long serialVersionUID = 3258695453799404851L;
    
    private String name;
    private List partyCollection = new ArrayList();

    /**
     * @return Returns the partyCollection.
     */
    public List getPartyCollection() {
        return partyCollection;
    }

    public List getPartyCollectionSorted() {      
      if (partyCollection != null) return new ArrayList(new TreeSet(partyCollection));
      return null;
    }    
    
    /**
     * @param partyCollection
     *            The partyCollection to set.
     */
    public void setPartyCollection(List partyCollection) {
        this.partyCollection = partyCollection;
    }

   
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

      /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getName();
       return result;
     }  
    
     public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false; 
      final Role obj = (Role) o;
      if (HashCodeUtil.notEqual(this.getName(), obj.getName())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getName());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof Role) && (this.getName() != null) && (((Role)o).getName() != null)) {   
        int result = this.getName().compareTo( ((Role)o).getName() );
        if (result != 0) { return result; }               
      }

      return super.compareTo(o);
    }      

}
