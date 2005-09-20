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
public class Comments extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259595453799404851L;

    private Long id;
    private String remark;
    private AbstractCancerModel cancerModel;
    private Availability availability;
    private ModelSection modelSection;
    private Person submitter;
    private String state;

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

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

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1727118201, -1843324729).append(this.remark).append(this.cancerModel).append(
                this.state).append(this.availability).append(this.modelSection).append(this.submitter).append(this.id)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("remark", this.remark).append("state", this.state).append(
                "cancerModel", this.cancerModel).append("availability", this.availability).append("modelSection",
                this.modelSection).append("submitter", this.submitter).append("id", this.id).toString();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments rhs = (Comments) object;
        return new EqualsBuilder().append(this.remark, rhs.remark).append(this.state, rhs.state).append(
                this.cancerModel, rhs.cancerModel).append(this.availability, rhs.availability).append(
                this.modelSection, rhs.modelSection).append(this.submitter, rhs.submitter).append(this.id, rhs.id)
                .isEquals();
    }
}
