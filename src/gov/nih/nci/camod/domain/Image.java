/*
 * Created on May 4, 2005
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
public class Image extends BaseObject implements Serializable, Duplicatable {

    private static final long serialVersionUID = 3259255453799404851L;
        
    private String title;
    private String description;
    private String staining;
    private String stainingUnctrlVocab;
    private String fileServerLocation;
    private Availability availability;

    /**
     * @return Returns the fileServerLocation.
     */
    public String getFileServerLocation() {
        return fileServerLocation;
    }

    /**
     * @param fileServerLocation
     *            The fileServerLocation to set.
     */
    public void setFileServerLocation(String fileServerLocation) {
        this.fileServerLocation = fileServerLocation;
    }

    /**
     * @return Returns the availability.
     */
    public Availability getAvailability() {
        return availability;
    }

    /**
     * @param availability
     *            The availability to set.
     */
    public void setAvailability(Availability availability) {
        this.availability = availability;
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
     * @return Returns the staining.
     */
    public String getStaining() {
        return staining;
    }

    /**
     * @param staining
     *            The staining to set.
     */
    public void setStaining(String staining) {
        this.staining = staining;
    }

    /**
     * @return Returns the stainingUnctrlVocab.
     */
    public String getStainingUnctrlVocab() {
        return stainingUnctrlVocab;
    }

    /**
     * @param stainingUnctrlVocab
     *            The stainingUnctrlVocab to set.
     */
    public void setStainingUnctrlVocab(String stainingUnctrlVocab) {
        this.stainingUnctrlVocab = stainingUnctrlVocab;
    }

    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }  

    /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getTitle() + " - " + this.getDescription();
       return result;
     }  
     
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
     
}
