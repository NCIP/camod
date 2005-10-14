/*
 * Created on May 5, 2005
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
public class GeneFunction extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259355453799404851L;
    
    private String function;
    private EngineeredGene engineeredGene;

    /**
     * @return Returns the engineeredGene.
     */
    public EngineeredGene getEngineeredGene() {
        return engineeredGene;
    }

    /**
     * @param engineeredGene
     *            The engineeredGene to set.
     */
    public void setEngineeredGene(EngineeredGene engineeredGene) {
        this.engineeredGene = engineeredGene;
    }

    /**
     * @return Returns the function.
     */
    public String getFunction() {
        return function;
    }

    /**
     * @param function
     *            The function to set.
     */
    public void setFunction(String function) {
        this.function = function;
    }

  

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
       String result = super.toString() + " - ";      
       result += this.getFunction();
       return result;
    }    
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
    
}
