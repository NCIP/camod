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
public class InvivoResult extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259235453799404851L;

    private Long id;
    private String evaluationDay;
    private String toxicitySurvivors;
    private String toxicityEvalDay;
    private EndpointCode endpointCode;
    private Treatment treatment;
    private Agent agent;
    private Float percentTreatedControl;

    /**
     * @return Returns the percentTreatedControl.
     */
    public Float getPercentTreatedControl() {
        return percentTreatedControl;
    }

    /**
     * @param percentTreatedControl
     *            The percentTreatedControl to set.
     */
    public void setPercentTreatedControl(Float percentTreatedControl) {
        this.percentTreatedControl = percentTreatedControl;
    }

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
     * @return Returns the treatment.
     */
    public Treatment getTreatment() {
        return treatment;
    }

    /**
     * @param treatment
     *            The treatment to set.
     */
    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    /**
     * @return Returns the endpointCode.
     */
    public EndpointCode getEndpointCode() {
        return endpointCode;
    }

    /**
     * @param endpointCode
     *            The endpointCode to set.
     */
    public void setEndpointCode(EndpointCode endpointCode) {
        this.endpointCode = endpointCode;
    }

    /**
     * @return Returns the evaluationDay.
     */
    public String getEvaluationDay() {
        return evaluationDay;
    }

    /**
     * @param evaluationDay
     *            The evaluationDay to set.
     */
    public void setEvaluationDay(String evaluationDay) {
        this.evaluationDay = evaluationDay;
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
     * @return Returns the toxicityEvalDay.
     */
    public String getToxicityEvalDay() {
        return toxicityEvalDay;
    }

    /**
     * @param toxicityEvalDay
     *            The toxicityEvalDay to set.
     */
    public void setToxicityEvalDay(String toxicityEvalDay) {
        this.toxicityEvalDay = toxicityEvalDay;
    }

    /**
     * @return Returns the toxicitySurvivors.
     */
    public String getToxicitySurvivors() {
        return toxicitySurvivors;
    }

    /**
     * @param toxicitySurvivors
     *            The toxicitySurvivors to set.
     */
    public void setToxicitySurvivors(String toxicitySurvivors) {
        this.toxicitySurvivors = toxicitySurvivors;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof InvivoResult)) {
            return false;
        }
        InvivoResult rhs = (InvivoResult) object;
        return new EqualsBuilder().append(this.agent, rhs.agent).append(this.toxicityEvalDay, rhs.toxicityEvalDay)
                .append(this.endpointCode, rhs.endpointCode).append(this.percentTreatedControl,
                        rhs.percentTreatedControl).append(this.treatment, rhs.treatment).append(this.evaluationDay,
                        rhs.evaluationDay).append(this.toxicitySurvivors, rhs.toxicitySurvivors)
                .append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(421896029, 274365963).append(this.agent).append(this.toxicityEvalDay).append(
                this.endpointCode).append(this.percentTreatedControl).append(this.treatment).append(this.evaluationDay)
                .append(this.toxicitySurvivors).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("toxicitySurvivors", this.toxicitySurvivors)
                .append("endpointCode", this.endpointCode).append("agent", this.agent).append("percentTreatedControl",
                        this.percentTreatedControl).append("toxicityEvalDay", this.toxicityEvalDay).append("treatment",
                        this.treatment).append("evaluationDay", this.evaluationDay).toString();
    }
}
