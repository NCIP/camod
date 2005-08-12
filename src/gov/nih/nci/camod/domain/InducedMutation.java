/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

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
public class InducedMutation extends EngineeredGene {
	private String geneId;
	private String description;
	private EnvironmentalFactor environmentalFactor;
	private List geneticAlterationCollection = new ArrayList();
	
	/**
	 * @return Returns the geneticAlterationCollection.
	 */
	public List getGeneticAlterationCollection() {
		return geneticAlterationCollection;
	}
	/**
	 * @param geneticAlterationCollection The geneticAlterationCollection to set.
	 */
	public void setGeneticAlterationCollection(List geneticAlterationCollection) {
		this.geneticAlterationCollection = geneticAlterationCollection;
	}
	/**
	 * @param geneticAlteration The geneticAlteration to add.
	 */
	public void addGeneticAlteration(GeneticAlteration geneticAlteration) {
		geneticAlterationCollection.add(geneticAlteration);
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
	 * @return Returns the geneId.
	 */
	public String getGeneId() {
		return geneId;
	}
	/**
	 * @param geneId The geneId to set.
	 */
	public void setGeneId(String geneId) {
		this.geneId = geneId;
	}
	/**
	 * @return Returns the environmentalFactor.
	 */
	public EnvironmentalFactor getEnvironmentalFactor() {
		return environmentalFactor;
	}
	/**
	 * @param environmentalFactor The environmentalFactor to set.
	 */
	public void setEnvironmentalFactor(EnvironmentalFactor environmentalFactor) {
		this.environmentalFactor = environmentalFactor;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof InducedMutation)) {
			return false;
		}
		InducedMutation rhs = (InducedMutation) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.environmentalFactor, rhs.environmentalFactor).append(
				this.description, rhs.description).append(
				this.geneticAlterationCollection,
				rhs.geneticAlterationCollection)
				.append(this.geneId, rhs.geneId).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1806683963, 1119230461).appendSuper(
				super.hashCode()).append(this.environmentalFactor).append(
				this.description).append(this.geneticAlterationCollection)
				.append(this.geneId).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("conditionality",
				this.getConditionality()).append("name", this.getName())
				.append("description", this.description).append("id",
						this.getId()).append("comments", this.getComments())
				.append("image", this.getImage()).append(
						"geneticAlterationCollection",
						this.geneticAlterationCollection).append("cabioId",
						this.getCabioId()).append(
						"expressionLevelDescCollection",
						this.getExpressionLevelDescCollection()).append(
						"environmentalFactor", this.environmentalFactor)
				.append("geneId", this.geneId).append("genotypeSummary",
						this.getGenotypeSummary()).toString();
	}
}
