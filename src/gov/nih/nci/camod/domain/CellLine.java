/*
 * Created on May 5, 2005
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
public class CellLine extends BaseObject implements Serializable {
	private Long id;
	private String comments;
	private String experiment;
	private String name;
	private String nameUnctrlVocab;	
	private String results;
	private List publicationCollection = new ArrayList();
	private Organ organ;	
	
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
	 * @return Returns the experiment.
	 */
	public String getExperiment() {
		return experiment;
	}
	/**
	 * @param experiment The experiment to set.
	 */
	public void setExperiment(String experiment) {
		this.experiment = experiment;
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
	 * @return Returns the nameUnctrlVocab.
	 */
	public String getNameUnctrlVocab() {
		return nameUnctrlVocab;
	}
	/**
	 * @param nameUnctrlVocab The nameUnctrlVocab to set.
	 */
	public void setNameUnctrlVocab(String nameUnctrlVocab) {
		this.nameUnctrlVocab = nameUnctrlVocab;
	}	
	/**
	 * @return Returns the publicationCollection.
	 */
	public List getPublicationCollection() {
		return publicationCollection;
	}
	/**
	 * @param publicationCollection The publicationCollection to set.
	 */
	public void setPublicationCollection(List publicationCollection) {
		this.publicationCollection = publicationCollection;
	}
	/**
	 * @param publication The publication to add.
	 */
	public void addPublication(Publication publication) {
		publicationCollection.add(publication);
	}
	/**
	 * @return Returns the results.
	 */
	public String getResults() {
		return results;
	}
	/**
	 * @param results The results to set.
	 */
	public void setResults(String results) {
		this.results = results;
	}
	
	/**  
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CellLine)) {
			return false;
		}
		CellLine rhs = (CellLine) object;
		return new EqualsBuilder().append(
				this.experiment, rhs.experiment)
				.append(this.comments, rhs.comments).append(this.organ, rhs.organ).append(
				this.publicationCollection, rhs.publicationCollection).append(this.nameUnctrlVocab, rhs.nameUnctrlVocab)
				.append(this.name, rhs.name).append(this.results, rhs.results).append(
				this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1563857285, 1875579981).append(this.experiment).append(this.comments)
				.append(this.organ).append(this.publicationCollection)
				.append(this.name).append(this.nameUnctrlVocab).append(this.results).append(this.id)
				.toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("experiment", this.experiment)
				.append("name", this.name).append("id", this.id).append(
						"comments", this.comments).append("name_unctrl_vocab", this.nameUnctrlVocab)
						.append("publicationCollection", this.publicationCollection)
				.append("organ", this.organ).append("results", this.results)
				.toString();
	}
}
