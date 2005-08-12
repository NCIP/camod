/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AgentTarget extends BaseObject implements Serializable {
	private Long id;
	private String targetName;
	private List agentCollection = new ArrayList();
	
	/**
	 * @return Returns the agentCollection.
	 */
	public List getAgentCollection() {
		return agentCollection;
	}
	/**
	 * @param agentCollection The agentCollection to set.
	 */
	public void setAgentCollection(List agentCollection) {
		this.agentCollection = agentCollection;
	}
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
	 * @return Returns the targetName.
	 */
	public String getTargetName() {
		return targetName;
	}
	/**
	 * @param targetName The targetName to set.
	 */
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AgentTarget)) {
			return false;
		}
		AgentTarget rhs = (AgentTarget) object;
		return new EqualsBuilder().append(
				this.agentCollection, rhs.agentCollection).append(
				this.targetName, rhs.targetName).append(this.id, rhs.id)
				.isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(379893387, 1828014099).append(this.agentCollection).append(
				this.targetName).append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("agentCollection",
				this.agentCollection).append("id", this.id).append(
				"targetName", this.targetName).toString();
	}
}
