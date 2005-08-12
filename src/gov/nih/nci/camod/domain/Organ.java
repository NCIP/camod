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
public class Organ extends BaseObject implements Serializable {
	private Long id;
	private String name;
	private String conceptCode;
	private List histopathologyCollection = new ArrayList();
	private List expressionLevelDescCollection = new ArrayList();	
	
	/**
	 * @return Returns the expressionLevelDescCollection.
	 */
	public List getExpressionLevelDescCollection() {
		return expressionLevelDescCollection;
	}
	/**
	 * @param expressionLevelDescCollection The expressionLevelDescCollection to set.
	 */
	public void setExpressionLevelDescCollection(List expressionLevelDescCollection) {
		this.expressionLevelDescCollection = expressionLevelDescCollection;
	}
	/**
	 * @param expressionLevelDesc The expressionLevelDesc to add.
	 */
	public void addExpressionLevelDesc(ExpressionLevelDesc expressionLevelDesc) {
		expressionLevelDesc.getOrganCollection().add(this);
		expressionLevelDescCollection.add(expressionLevelDesc);
	}
	/**
	 * @return Returns the histopathologyCollection.
	 */
	public List getHistopathologyCollection() {
		return histopathologyCollection;
	}
	/**
	 * @param histopathologyCollection The histopathologyCollection to set.
	 */
	public void setHistopathologyCollection(List histopathologyCollection) {
		this.histopathologyCollection = histopathologyCollection;
	}
	/**
	 * @param histopatholgy The histopathology to add.
	 */
	public void addHistopathology(Histopathology histopathology) {
		histopathology.setOrgan(this);
		histopathologyCollection.add(histopathology);
	}	
	/**
	 * @return Returns the conceptCode.
	 */
	public String getConceptCode() {
		return conceptCode;
	}
	/**
	 * @param conceptCode The conceptCode to set.
	 */
	public void setConceptCode(String conceptCode) {
		this.conceptCode = conceptCode;
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
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Organ)) {
			return false;
		}
		Organ rhs = (Organ) object;
		return new EqualsBuilder().append(
				this.histopathologyCollection, rhs.histopathologyCollection)
				.append(this.expressionLevelDescCollection,
						rhs.expressionLevelDescCollection).append(this.name,
						rhs.name).append(this.conceptCode, rhs.conceptCode)
				.append(this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-2122195223, -1703430887).append(this.histopathologyCollection).append(
				this.expressionLevelDescCollection).append(this.name).append(
				this.conceptCode).append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name).append(
				"conceptCode", this.conceptCode).append("id", this.id).append(
				"expressionLevelDescCollection",
				this.expressionLevelDescCollection).append(
				"histopathologyCollection", this.histopathologyCollection)
				.toString();
	}
}
