/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.Date;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Availability extends BaseObject implements Serializable, Duplicatable {

    private static final long serialVersionUID = 3259655453799404851L;
    
    private Date enteredDate;
    private String visibleTo;
    private Date modifiedDate;
    private Date releaseDate;

    /**
     * @return Returns the enteredDate.
     */
    public Date getEnteredDate() {
        return enteredDate;
    }

    /**
     * @param enteredDate
     *            The enteredDate to set.
     */
    public void setEnteredDate(Date enteredDate) {
        this.enteredDate = enteredDate;
    }
   
    /**
     * @return Returns the modifiedDate.
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate
     *            The modifiedDate to set.
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return Returns the releaseDate.
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate
     *            The releaseDate to set.
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return Returns the visibleTo.
     */
    public String getVisibleTo() {
        return visibleTo;
    }

    /**
     * @param visibleTo
     *            The visibleTo to set.
     */
    public void setVisibleTo(String visibleTo) {
        this.visibleTo = visibleTo;
    }  

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
      String result = super.toString() + " - ";      
      result += this.getEnteredDate()+" - "+this.getModifiedDate()+" - "+this.getReleaseDate();                
      return result;
    }     
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }

}
