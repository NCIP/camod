/*
 * Created on May 3, 2005
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
public class TumorCode extends BaseObject implements Serializable, Duplicatable {

    private static final long serialVersionUID = 3258465453799404851L;
    
    private String code;
    private String description;

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            The code to set.
     */
    public void setCode(String code) {
        this.code = code;
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
       result += this.getCode();
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
