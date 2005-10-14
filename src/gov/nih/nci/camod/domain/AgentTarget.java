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
public class AgentTarget extends BaseObject implements Serializable {

    private static final long serialVersionUID = 4259725453799404851L;
    
    private String targetName;
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
     * @return Returns the targetName.
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * @param targetName
     *            The targetName to set.
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
  
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
      String result = super.toString() + " - ";      
      result += this.getTargetName();                
      return result;
    }       
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }     
}
