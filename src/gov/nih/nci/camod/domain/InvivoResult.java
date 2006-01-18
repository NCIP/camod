/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class InvivoResult extends BaseObject implements Serializable, Duplicatable, Comparable
{
    private static final long serialVersionUID = 3259235453799404851L;

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
    public Float getPercentTreatedControl()
    {
        return percentTreatedControl;
    }

    /**
     * @param percentTreatedControl
     *            The percentTreatedControl to set.
     */
    public void setPercentTreatedControl(Float percentTreatedControl)
    {
        this.percentTreatedControl = percentTreatedControl;
    }

    /**
     * @return Returns the agent.
     */
    public Agent getAgent()
    {
        return agent;
    }

    /**
     * @param agent
     *            The agent to set.
     */
    public void setAgent(Agent agent)
    {
        this.agent = agent;
    }

    /**
     * @return Returns the treatment.
     */
    public Treatment getTreatment()
    {
        return treatment;
    }

    /**
     * @param treatment
     *            The treatment to set.
     */
    public void setTreatment(Treatment treatment)
    {
        this.treatment = treatment;
    }

    /**
     * @return Returns the endpointCode.
     */
    public EndpointCode getEndpointCode()
    {
        return endpointCode;
    }

    /**
     * @param endpointCode
     *            The endpointCode to set.
     */
    public void setEndpointCode(EndpointCode endpointCode)
    {
        this.endpointCode = endpointCode;
    }

    /**
     * @return Returns the evaluationDay.
     */
    public String getEvaluationDay()
    {
        return evaluationDay;
    }

    /**
     * @param evaluationDay
     *            The evaluationDay to set.
     */
    public void setEvaluationDay(String evaluationDay)
    {
        this.evaluationDay = evaluationDay;
    }

    /**
     * @return Returns the toxicityEvalDay.
     */
    public String getToxicityEvalDay()
    {
        return toxicityEvalDay;
    }

    /**
     * @param toxicityEvalDay
     *            The toxicityEvalDay to set.
     */
    public void setToxicityEvalDay(String toxicityEvalDay)
    {
        this.toxicityEvalDay = toxicityEvalDay;
    }

    /**
     * @return Returns the toxicitySurvivors.
     */
    public String getToxicitySurvivors()
    {
        return toxicitySurvivors;
    }

    /**
     * @param toxicitySurvivors
     *            The toxicitySurvivors to set.
     */
    public void setToxicitySurvivors(String toxicitySurvivors)
    {
        this.toxicitySurvivors = toxicitySurvivors;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getEvaluationDay() + " - " + this.getToxicitySurvivors();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final InvivoResult obj = (InvivoResult) o;
        if (HashCodeUtil.notEqual(this.getAgent(), obj.getAgent()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getAgent());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof InvivoResult) && (this.getAgent() != null) && (((InvivoResult) o).getAgent() != null))
        {
            int result = this.getAgent().compareTo(((InvivoResult) o).getAgent());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }
}
