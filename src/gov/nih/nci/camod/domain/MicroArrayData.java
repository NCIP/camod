/*
 * Created on May 4, 2005
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
public class MicroArrayData extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259195453799404851L;
    
    private String experimentName;
    private Long experimentId;
    private String otherLocationURL;
    private Availability availability;

    /**
     * @return Returns the otherLocationURL.
     */
    public String getOtherLocationURL() {
        return otherLocationURL;
    }

    /**
     * @param otherLocationURL
     *            The otherLocationURL to set.
     */
    public void setOtherLocationURL(String otherLocationURL) {
        this.otherLocationURL = otherLocationURL;
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
     * @return Returns the experimentId.
     */
    public Long getExperimentId() {
        return experimentId;
    }

    /**
     * @param experimentId
     *            The experimentId to set.
     */
    public void setExperimentId(Long experimentId) {
        this.experimentId = experimentId;
    }

    /**
     * @return Returns the experimentName.
     */
    public String getExperimentName() {
        return experimentName;
    }

    /**
     * @param experimentName
     *            The experimentName to set.
     */
    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }
   
    /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getExperimentName();
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }

}
