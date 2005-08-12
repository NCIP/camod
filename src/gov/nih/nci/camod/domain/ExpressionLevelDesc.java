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
public class ExpressionLevelDesc extends BaseObject implements Serializable {
	private Long id;
	private String expressionLevel;
	private List organCollection = new ArrayList();
	
	/**
	 * @return Returns the organCollection.
	 */
	public List getOrganCollection() {
		return organCollection;
	}
	/**
	 * @param organCollection The organCollection to set.
	 */
	public void setOrganCollection(List organCollection) {
		this.organCollection = organCollection;
	}
	/**
	 * @return Returns the expressionLevel.
	 */
	public String getExpressionLevel() {
		return expressionLevel;
	}
	/**
	 * @param expressionLevel The expressionLevel to set.
	 */
	public void setExpressionLevel(String expressionLevel) {
		this.expressionLevel = expressionLevel;
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
		if (!(object instanceof ExpressionLevelDesc)) {
			return false;
		}
		ExpressionLevelDesc rhs = (ExpressionLevelDesc) object;
		return new EqualsBuilder().append(
				this.organCollection, rhs.organCollection).append(
				this.expressionLevel, rhs.expressionLevel).append(this.id,
				rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-511900051, -355258071).append(this.organCollection).append(
				this.expressionLevel).append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append(
				"organCollection", this.organCollection).append(
				"expressionLevel", this.expressionLevel).toString();
	}
}
