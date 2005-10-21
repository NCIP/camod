/*
 * Created on May 6, 2005
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
public class SegmentType extends BaseObject implements Serializable, Duplicatable {

    private static final long serialVersionUID = 3258655453799404851L;
    
    private String name;
    private String nameUnctrlVocab;
  

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
     * @return Returns the nameUnctrlVocab.
     */
    public String getNameUnctrlVocab() {
        return nameUnctrlVocab;
    }

    /**
     * @param nameUnctrlVocab
     *            The nameUnctrlVocab to set.
     */
    public void setNameUnctrlVocab(String nameUnctrlVocab) {
        this.nameUnctrlVocab = nameUnctrlVocab;
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
