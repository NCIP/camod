/*
 * Created on July 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.*;

/**
 * @author pandyas
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Comments extends BaseObject implements Serializable, Curateable {

    private static final long serialVersionUID = 3259595453799404851L;
    
    private String remark;
    private AbstractCancerModel cancerModel;
    private Availability availability;
    private ModelSection modelSection;
    private Person submitter;
    private String state;
 
    /**
     * @return Returns the name.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return Returns the cancerModel.
     */
    public AbstractCancerModel getCancerModel() {
        return cancerModel;
    }

    /**
     * @param cancerModel
     *            The cancerModel to set.
     */
    public void setCancerModel(AbstractCancerModel cancerModel) {
        this.cancerModel = cancerModel;
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
     * @return Returns the modelSection.
     */
    public ModelSection getModelSection() {
        return modelSection;
    }

    /**
     * @param modelSection
     *            The modelSection to set.
     */
    public void setModelSection(ModelSection modelSection) {
        this.modelSection = modelSection;
    }

    /**
     * @return Returns the submitter.
     */
    public Person getSubmitter() {
        return submitter;
    }

    /**
     * @param submitter
     *            The submitter to set.
     */
    public void setSubmitter(Person submitter) {
        this.submitter = submitter;
    }

     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getRemark(); 
       return result;
     }    
     
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
     
}
