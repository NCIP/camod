/*
 * Created on May 6, 2005
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
public class Phenotype extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3258775453799404851L;
    
    private String breedingNotes;
    private String description;
    private SexDistribution sexDistribution;

    /**
     * @return Returns the sexDistribution.
     */
    public SexDistribution getSexDistribution() {
        return sexDistribution;
    }

    /**
     * @param sexDistribution
     *            The sexDistribution to set.
     */
    public void setSexDistribution(SexDistribution sexDistribution) {
        this.sexDistribution = sexDistribution;
    }

    /**
     * @return Returns the breedingNotes.
     */
    public String getBreedingNotes() {
        return breedingNotes;
    }

    /**
     * @param breedingNotes
     *            The breedingNotes to set.
     */
    public void setBreedingNotes(String breedingNotes) {
        this.breedingNotes = breedingNotes;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

      /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getDescription();
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
