/*
 * Created on May 5, 2005
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
public class ExpressionFeature extends BaseObject implements Serializable {
	private Long id;
	private Organ organ;
	private ExpressionLevelDesc expressionLevelDesc;
	
	/**
	 * @return Returns the expressionLevelDesc.
	 */
	public ExpressionLevelDesc getExpressionLevelDesc() {
		return expressionLevelDesc;
	}
	/**
	 * @param expressionLevelDesc The expressionLevelDesc to set.
	 */
	public void setExpressionLevelDesc(ExpressionLevelDesc expressionLevelDesc) {
		this.expressionLevelDesc = expressionLevelDesc;
	}
	/**
	 * @return Returns the organ.
	 */
	public Organ getOrgan() {
		return organ;
	}
	/**
	 * @param organ The organ to set.
	 */
	public void setOrgan(Organ organ) {
		this.organ = organ;
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
		if (!(object instanceof ExpressionFeature)) {
			return false;
		}
		ExpressionFeature rhs = (ExpressionFeature) object;
		return new EqualsBuilder().append(
				this.expressionLevelDesc, rhs.expressionLevelDesc).append(
				this.organ, rhs.organ).append(this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-288852383, -1919687697).append(this.expressionLevelDesc).append(
				this.organ).append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("organ",
				this.organ).append("expressionLevelDesc",
				this.expressionLevelDesc).toString();
	}
}
