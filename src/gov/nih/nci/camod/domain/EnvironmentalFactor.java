/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnvironmentalFactor extends BaseObject implements Serializable {
	private Long id;
	private String type;
	private String name;
	private String casNumber;
	private List inducedMutationCollection = new ArrayList();
	
	/**
	 * @return Returns the casNumber.
	 */
	public String getCasNumber() {
		return casNumber;
	}
	/**
	 * @param casNumber The casNumber to set.
	 */
	public void setCasNumber(String casNumber) {
		this.casNumber = casNumber;
	}
	/**
	 * @return Returns the inducedMutationCollection.
	 */
	public List getInducedMutationCollection() {
		return inducedMutationCollection;
	}
	/**
	 * @param inducedMutationCollection The inducedMutationCollection to set.
	 */
	public void setInducedMutationCollection(List inducedMutationCollection) {
		this.inducedMutationCollection = inducedMutationCollection;
	}
	/**
	 * @param inducedMutation The inducedMutation to add.
	 */
	public void addInducedMutation(InducedMutation inducedMutation) {
		inducedMutation.setEnvironmentalFactor(this);
		inducedMutationCollection.add(inducedMutation);
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
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof EnvironmentalFactor)) {
			return false;
		}
		EnvironmentalFactor rhs = (EnvironmentalFactor) object;
		return new EqualsBuilder().append(
				this.casNumber, rhs.casNumber).append(this.type, rhs.type)
				.append(this.name, rhs.name).append(this.id, rhs.id).append(
						this.inducedMutationCollection,
						rhs.inducedMutationCollection).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1041064431, -1510046889).append(this.casNumber).append(this.type)
				.append(this.name).append(this.id).append(this.inducedMutationCollection).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name).append(
				"inducedMutationCollection", this.inducedMutationCollection)
				.append("id", this.id).append("casNumber", this.casNumber)
				.append("type", this.type).toString();
	}
}
