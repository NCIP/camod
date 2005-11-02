/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

import java.io.Serializable;

/**
 * @author rajputs 
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class EnvironmentalFactor extends BaseObject implements Comparable, Serializable, Duplicatable {

    private static final long serialVersionUID = 3259445453799404851L;
    
    private String type;
    private String typeUnctrlVocab;
    private String name;
    private String nameUnctrlVocab;
    private String casNumber;

    /**
     * @return Returns the casNumber.
     */
    public String getCasNumber() {
        return casNumber;
    }

    /**
     * @param casNumber
     *            The casNumber to set.
     */
    public void setCasNumber(String casNumber) {
        this.casNumber = casNumber;
    }

    /**
     * @return Returns the display name.
     */
    public String getDisplayName() {
        String theDisplayName = name;
        if (name.equals(Constants.Dropdowns.OTHER_OPTION))
        {
            theDisplayName += " - " + nameUnctrlVocab;
        }
        return theDisplayName;
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
     * @return Returns the nameUnctrlVocab.
     */
    public String getNameUnctrlVocab() {
        return nameUnctrlVocab;
    }

    /**
     * @param nameUnctrlVocab
     *            The nameUnctrlVocab to set.
     */
    public void setNameUnctrlVocab(String nameUnctrlVocab) {
        this.nameUnctrlVocab = nameUnctrlVocab;
    }

    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return Returns the typeUnctrlVocab.
     */
    public String getTypeUnctrlVocab() {
        return typeUnctrlVocab;
    }

    /**
     * @param typeUnctrlVocab
     *            The typeUnctrlVocab to set.
     */
    public void setTypeUnctrlVocab(String typeUnctrlVocab) {
        this.typeUnctrlVocab = typeUnctrlVocab;
    }

    public String toString() {
       String result = super.toString() + " - ";      
       result += this.getName();  
       return result;
     }    
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false; 
      final EnvironmentalFactor obj = (EnvironmentalFactor) o;
      if (HashCodeUtil.notEqual(this.getName(), obj.getName())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getName());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof EnvironmentalFactor) && (this.getName() != null) && (((EnvironmentalFactor)o).getName() != null)) {   
        int result = this.getName().compareTo( ((EnvironmentalFactor)o).getName() );
        if (result != 0) { return result; }        
      }

      return super.compareTo(o);
    }      

}
