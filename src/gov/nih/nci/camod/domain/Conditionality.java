/*
 * Created on May 6, 2005
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
public class Conditionality extends BaseObject implements Serializable {
	private Long id;
	private String conditionedBy;
	private String description;
	
	/**
	 * @return Returns the conditionedBy.
	 */
	public String getConditionedBy() {
		return conditionedBy;
	}
	/**
	 * @param conditionedBy The conditionedBy to set.
	 */
	public void setConditionedBy(String conditionedBy) {
		this.conditionedBy = conditionedBy;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
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
		if (!(object instanceof Conditionality)) {
			return false;
		}
		Conditionality rhs = (Conditionality) object;
		return new EqualsBuilder().append(
				this.conditionedBy, rhs.conditionedBy).append(this.description,
				rhs.description).append(this.id, rhs.id).isEquals();
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-394824585, -1813869519).append(this.conditionedBy).append(
				this.description).append(this.id).toHashCode();
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("conditionedBy",
				this.conditionedBy).append("id", this.id).append("description",
				this.description).toString();
	}
}
