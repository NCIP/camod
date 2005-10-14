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
public class BiologicalProcess extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259655453799404851L;
    
    private String processName;
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
     * @return Returns the processName.
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * @param processName
     *            The processName to set.
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    } 
 
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
      String result = super.toString() + " - ";      
      result += this.getProcessName();           
      return result;
    }     
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
    
}
