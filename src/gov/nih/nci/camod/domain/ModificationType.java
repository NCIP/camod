/*
 * Created on August 9, 2005
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
public class ModificationType extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259155453799404851L;

    private String name;
    private List targetedModificationCollection = new ArrayList();

    /**
     * @return Returns the targetedModificationCollection.
     */
    public List getTargetedModificationCollection() {
        return targetedModificationCollection;
    }

    /**
     * @param targetedModificationCollection
     *            The targetedModificationCollection to set.
     */
    public void setTargetedModificationCollection(List targetedModificationCollection) {
        this.targetedModificationCollection = targetedModificationCollection;
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
      return true;
    }
}
