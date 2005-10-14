/*
 * Created on May 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GeneticAlteration extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259335453799404851L;
    
    private String observation;
    private String methodOfObservation;
 

    /**
     * @return Returns the methodOfObservation.
     */
    public String getMethodOfObservation() {
        return methodOfObservation;
    }

    /**
     * @param methodOfObservation
     *            The methodOfObservation to set.
     */
    public void setMethodOfObservation(String methodOfObservation) {
        this.methodOfObservation = methodOfObservation;
    }

    /**
     * @return Returns the observation.
     */
    public String getObservation() {
        return observation;
    }

    /**
     * @param observation
     *            The observation to set.
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

  
    /**
     * @see java.lang.Object#toString()
     */     
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getObservation() + " - " + this.getMethodOfObservation();
       return result;
     }  
     
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
     
}
