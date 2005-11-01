/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ClinicalMarker extends BaseObject implements Comparable, Serializable, Duplicatable {

    private static final long serialVersionUID = 3259615453799404851L;
    
    private String name;
    private String value;

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            The value to set.
     */
    public void setValue(String value) {
        this.value = value;
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
       result += this.getName()+" - "+this.getValue();           
       return result;
     }     
    

        
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false; 
      final ClinicalMarker obj = (ClinicalMarker) o;
      if (HashCodeUtil.notEqual(this.getName(), obj.getName())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getName());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof ClinicalMarker) && (this.getName() != null) && (((ClinicalMarker)o).getName() != null)) {   
        int result = this.getName().compareTo( ((ClinicalMarker)o).getName() );
        if (result != 0) { return result; }               
      }

      return super.compareTo(o);
    }      

}
