/*
 * $Log: not supported by cvs2svn $
 * 
 * $Id: ChemicalClass.java,v 1.6 2005-11-14 14:16:51 georgeda Exp $
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import gov.nih.nci.camod.util.HashCodeUtil;

public class ChemicalClass extends BaseObject implements Serializable, Comparable {

    private static final long serialVersionUID = 3259635453799404851L;

    private String chemicalClassName;
    private List agentCollection = new ArrayList();

    /**
     * @return Returns the agentCollection.
     */
    public List getAgentCollection() {
        return agentCollection;
    }
    
    public List getAgentCollectionSorted() {      
      if (agentCollection != null) return new ArrayList(new TreeSet(agentCollection));
      return null;
    }    


    /**
     * @param agentCollection
     *            The agentCollection to set.
     */
    public void setAgentCollection(List agentCollection) {
        this.agentCollection = agentCollection;
    }

    /**
     * @return Returns the chemicalClassName.
     */
    public String getChemicalClassName() {
        return chemicalClassName;
    }

    /**
     * @param chemicalClassName
     *            The chemicalClassName to set.
     */
    public void setChemicalClassName(String chemicalClassName) {
        this.chemicalClassName = chemicalClassName;
    }
 
  
    /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getChemicalClassName();           
       return result;
     }     
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false; 
      final ChemicalClass obj = (ChemicalClass) o;
      if (HashCodeUtil.notEqual(this.getChemicalClassName(), obj.getChemicalClassName())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getChemicalClassName());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof ChemicalClass) && (this.getChemicalClassName() != null) && (((ChemicalClass)o).getChemicalClassName() != null)) {   
        int result = this.getChemicalClassName().compareTo( ((ChemicalClass)o).getChemicalClassName() );
        if (result != 0) { return result; }               
      }

      return super.compareTo(o);
    }      

}
