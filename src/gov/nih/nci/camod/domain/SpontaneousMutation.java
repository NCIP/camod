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
public class SpontaneousMutation extends BaseObject implements Serializable {
	private Long id;
	private String name;
	private String comments;
	private List geneticAlterationCollection = new ArrayList();
	private MutationIdentifier mutationIdentifier;
	
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
		if (!(object instanceof SpontaneousMutation)) {
			return false;
		}
		SpontaneousMutation rhs = (SpontaneousMutation) object;
		return new EqualsBuilder().append(
				this.comments, rhs.comments).append(this.mutationIdentifier,
				rhs.mutationIdentifier).append(
				this.geneticAlterationCollection,
				rhs.geneticAlterationCollection).append(this.name, rhs.name)
				.append(this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-172502213, -804621663).append(this.comments).append(
				this.mutationIdentifier).append(
				this.geneticAlterationCollection).append(this.name).append(
				this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("name", this.name).append("id",
				this.id).append("comments", this.comments)
				.append("geneticAlterationCollection",
						this.geneticAlterationCollection).append(
						"mutationIdentifier", this.mutationIdentifier)
				.toString();
	}
}
