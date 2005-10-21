/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MutationIdentifier extends BaseObject implements Serializable, Duplicatable {

    private static final long serialVersionUID = 3259135453799404851L;
    
    private Long numberMGI;

  
    /**
     * @return Returns the numberMGI.
     */
    public Long getNumberMGI() {
        return numberMGI;
    }

    /**
     * @param numberMGI
     *            The numberMGI to set.
     */
    public void setNumberMGI(Long numberMGI) {
        this.numberMGI = numberMGI;
    }

     /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       if (this.getNumberMGI() != null) result += this.getNumberMGI();
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
