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
public class ScreeningResult extends BaseObject implements Serializable, Duplicatable {

    private static final long serialVersionUID = 3258675453799404851L;
    
    private String stage;
    private Float diffinh;
    private Float aveinh;
    private Float inhibitionRate;
    private Treatment treatment;
    private Agent agent;

    /**
     * @return Returns the agent.
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * @param agent
     *            The agent to set.
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * @return Returns the treatmentSchedule.
     */
    public Treatment getTreatment() {
        return treatment;
    }

    /**
     * @param treatmentSchedule
     *            The treatmentSchedule to set.
     */
    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    /**
     * @return Returns the aveinh.
     */
    public Float getAveinh() {
        return aveinh;
    }

    /**
     * @param aveinh
     *            The aveinh to set.
     */
    public void setAveinh(Float aveinh) {
        this.aveinh = aveinh;
    }

    /**
     * @return Returns the diffinh.
     */
    public Float getDiffinh() {
        return diffinh;
    }

    /**
     * @param diffinh
     *            The diffinh to set.
     */
    public void setDiffinh(Float diffinh) {
        this.diffinh = diffinh;
    }
  

    /**
     * @return Returns the inhibitionRate.
     */
    public Float getInhibitionRate() {
        return inhibitionRate;
    }

    /**
     * @param inhibitionRate
     *            The inhibitionRate to set.
     */
    public void setInhibitionRate(Float inhibitionRate) {
        this.inhibitionRate = inhibitionRate;
    }

    /**
     * @return Returns the stage.
     */
    public String getStage() {
        return stage;
    }

    /**
     * @param stage
     *            The stage to set.
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

     /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getStage();
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
