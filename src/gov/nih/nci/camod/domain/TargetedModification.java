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
public class TargetedModification extends EngineeredGene {
	private String esCellLineName;
	private String blastocystName;
	private String geneId;
	private List modificationTypeCollection = new ArrayList();
	
	/**
	 * @return Returns the modificationTypeCollection.
	 */
	public List getModificationTypeCollection() {
		return modificationTypeCollection;
	}
	/**
	 * @param modificationTypeCollection The modificationTypeCollection to set.
	 */
	public void setModificationTypeCollection(List modificationTypeCollection) {
		this.modificationTypeCollection = modificationTypeCollection;
	}
	/**
	 * @param modificationType The modificationType to add.
	 */
	public void addModificationType(ModificationType modificationType) {
		modificationType.getTargetedModificationCollection().add(this);
		modificationTypeCollection.add(modificationType);
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
	 * @return Returns the blastocystName.
	 */
	public String getBlastocystName() {
		return blastocystName;
	}
	/**
	 * @param blastocystName The blastocystName to set.
	 */
	public void setBlastocystName(String blastocystName) {
		this.blastocystName = blastocystName;
	}
	/**
	 * @return Returns the esCellLineName.
	 */
	public String getEsCellLineName() {
		return esCellLineName;
	}
	/**
	 * @param esCellLineName The esCellLineName to set.
	 */
	public void setEsCellLineName(String esCellLineName) {
		this.esCellLineName = esCellLineName;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TargetedModification)) {
			return false;
		}
		TargetedModification rhs = (TargetedModification) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.modificationTypeCollection,
						rhs.modificationTypeCollection).append(
						this.esCellLineName, rhs.esCellLineName).append(
						this.geneId, rhs.geneId).append(this.blastocystName,
						rhs.blastocystName).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1377742101, -963966355).appendSuper(
				super.hashCode()).append(this.modificationTypeCollection)
				.append(this.esCellLineName).append(this.geneId).append(
						this.blastocystName).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("conditionality",
				this.getConditionality()).append("name", this.getName())
				.append("expressionFeatureCollection",
						this.getExpressionFeatureCollection()).append(
						"esCellLineName", this.esCellLineName).append("id",
						this.getId()).append("comments", this.getComments())
				.append("image", this.getImage()).append("cabioId",
						this.getCabioId()).append("mutationIdentifier",
						this.getMutationIdentifier()).append("blastocystName",
						this.blastocystName).append(
						"modificationTypeCollection",
						this.modificationTypeCollection).append("geneId",
						this.geneId).append("genotypeSummary",
						this.getGenotypeSummary()).toString();
	}
}
