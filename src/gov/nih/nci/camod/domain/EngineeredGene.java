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
public class EngineeredGene extends BaseObject implements Serializable {
	private Long id;
	private Long cabioId;
	private String name;
	private String comments;
	private GenotypeSummary genotypeSummary;
	private Image image;
	private Conditionality conditionality;
	private MutationIdentifier mutationIdentifier;
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
	 * @param organ The organ to add.
	 */
	public void addOrgan(Organ organ) {
		organCollection.add(organ);
	}	
	/**
	 * @return Returns the mutationIdentifier.
	 */
	public MutationIdentifier getMutationIdentifier() {
		return mutationIdentifier;
	}
	/**
	 * @param mutationIdentifier The mutationIdentifier to set.
	 */
	public void setMutationIdentifier(MutationIdentifier mutationIdentifier) {
		this.mutationIdentifier = mutationIdentifier;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the conditionality.
	 */
	public Conditionality getConditionality() {
		return conditionality;
	}
	/**
	 * @param conditionality The conditionality to set.
	 */
	public void setConditionality(Conditionality conditionality) {
		this.conditionality = conditionality;
	}	
	/**
	 * @return Returns the image.
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * @param image The image to set.
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	/**
	 * @return Returns the genotypeSummary.
	 */
	public GenotypeSummary getGenotypeSummary() {
		return genotypeSummary;
	}
	/**
	 * @param genotypeSummary The genotypeSummary to set.
	 */
	public void setGenotypeSummary(GenotypeSummary genotypeSummary) {
		this.genotypeSummary = genotypeSummary;
	}
	/**
	 * @return Returns the cabioId.
	 */
	public Long getCabioId() {
		return cabioId;
	}
	/**
	 * @param cabioId The cabioId to set.
	 */
	public void setCabioId(Long cabioId) {
		this.cabioId = cabioId;
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
		if (!(object instanceof EngineeredGene)) {
			return false;
		}
		EngineeredGene rhs = (EngineeredGene) object;
		return new EqualsBuilder().append(
				this.comments, rhs.comments).append(this.mutationIdentifier,
				rhs.mutationIdentifier).append(this.genotypeSummary,
				rhs.genotypeSummary).append(this.organCollection,
				rhs.organCollection).append(this.conditionality,
				rhs.conditionality).append(this.image, rhs.image).append(
				this.cabioId, rhs.cabioId).append(this.name, rhs.name).append(
				this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1420752269, 1266067367).append(this.comments).append(
				this.mutationIdentifier).append(this.genotypeSummary).append(
				this.organCollection).append(this.conditionality).append(
				this.image).append(this.cabioId).append(this.name).append(
				this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("conditionality",
				this.conditionality).append("name", this.name).append("id",
				this.id).append("comments", this.comments).append("image",
				this.image).append("organCollection", this.organCollection)
				.append("cabioId", this.cabioId).append("mutationIdentifier",
						this.mutationIdentifier).append("genotypeSummary",
						this.genotypeSummary).toString();
	}
}
