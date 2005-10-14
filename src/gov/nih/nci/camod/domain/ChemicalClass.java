/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ChemicalClass extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259635453799404851L;

    private String chemicalClassName;
    private List agentCollection = new ArrayList();

    /**
     * @return Returns the agentCollection.
     */
    public List getAgentCollection() {
        return agentCollection;
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
      return true;
    }
     
}
