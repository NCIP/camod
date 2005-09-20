/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ScreeningResult extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3258675453799404851L;

    private Long id;
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
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ScreeningResult)) {
            return false;
        }
        ScreeningResult rhs = (ScreeningResult) object;
        return new EqualsBuilder().append(this.agent, rhs.agent).append(this.diffinh, rhs.diffinh).append(this.aveinh,
                rhs.aveinh).append(this.treatment, rhs.treatment).append(this.stage, rhs.stage).append(this.id, rhs.id)
                .append(this.inhibitionRate, rhs.inhibitionRate).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-801918033, -2181113).append(this.agent).append(this.diffinh).append(this.aveinh)
                .append(this.treatment).append(this.stage).append(this.id).append(this.inhibitionRate).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("aveinh", this.aveinh).append("inhibitionRate", this.inhibitionRate)
                .append("id", this.id).append("agent", this.agent).append("diffinh", this.diffinh).append("stage",
                        this.stage).append("treatment", this.treatment).toString();
    }
}
