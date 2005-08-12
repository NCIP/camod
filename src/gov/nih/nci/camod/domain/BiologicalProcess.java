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
public class BiologicalProcess extends BaseObject implements Serializable {
	private Long id;
	private String processName;	
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
	 * @return Returns the processName.
	 */
	public String getProcessName() {
		return processName;
	}
	/**
	 * @param processName The processName to set.
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
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
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BiologicalProcess)) {
			return false;
		}
		BiologicalProcess rhs = (BiologicalProcess) object;
		return new EqualsBuilder().append(
				this.processName, rhs.processName).append(this.id, rhs.id)
				.isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(257632777, 169965399).append(this.processName).append(this.id)
				.toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append(
				"processName", this.processName).toString();
	}
}
