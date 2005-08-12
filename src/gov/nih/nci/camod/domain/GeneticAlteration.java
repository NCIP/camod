/*
 * Created on May 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GeneticAlteration extends BaseObject implements Serializable {
	private Long id;
	private String observation;
	private String methodOfObservation;
	
	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return Returns the methodOfObservation.
	 */
	public String getMethodOfObservation() {
		return methodOfObservation;
	}
	/**
	 * @param methodOfObservation The methodOfObservation to set.
	 */
	public void setMethodOfObservation(String methodOfObservation) {
		this.methodOfObservation = methodOfObservation;
	}
	/**
	 * @return Returns the observation.
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * @param observation The observation to set.
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof GeneticAlteration)) {
			return false;
		}
		GeneticAlteration rhs = (GeneticAlteration) object;
		return new EqualsBuilder().append(
				this.observation, rhs.observation).append(
				this.methodOfObservation, rhs.methodOfObservation).append(
				this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1279374983, 846457973).append(this.observation).append(
				this.methodOfObservation).append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append(
				"methodOfObservation", this.methodOfObservation).append(
				"observation", this.observation).toString();
	}
}
