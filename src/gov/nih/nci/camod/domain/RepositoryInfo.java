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
public class RepositoryInfo extends BaseObject implements Serializable {
	private Long id;
	private Long inTheRepository;
	private String sentEmailContent;
	private Long suggestSubmission;
	
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
	 * @return Returns the inTheRepository.
	 */
	public Long getInTheRepository() {
		return inTheRepository;
	}
	/**
	 * @param inTheRepository The inTheRepository to set.
	 */
	public void setInTheRepository(Long inTheRepository) {
		this.inTheRepository = inTheRepository;
	}
	/**
	 * @return Returns the sentEmailContent.
	 */
	public String getSentEmailContent() {
		return sentEmailContent;
	}
	/**
	 * @param sentEmailContent The sentEmailContent to set.
	 */
	public void setSentEmailContent(String sentEmailContent) {
		this.sentEmailContent = sentEmailContent;
	}
	/**
	 * @return Returns the suggestSubmission.
	 */
	public Long getSuggestSubmission() {
		return suggestSubmission;
	}
	/**
	 * @param suggestSubmission The suggestSubmission to set.
	 */
	public void setSuggestSubmission(Long suggestSubmission) {
		this.suggestSubmission = suggestSubmission;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof RepositoryInfo)) {
			return false;
		}
		RepositoryInfo rhs = (RepositoryInfo) object;
		return new EqualsBuilder().append(
				this.inTheRepository, rhs.inTheRepository).append(
				this.sentEmailContent, rhs.sentEmailContent).append(
				this.suggestSubmission, rhs.suggestSubmission).append(this.id,
				rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(2091782299, 1949449931).append(this.inTheRepository).append(
				this.sentEmailContent).append(this.suggestSubmission).append(
				this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("suggestSubmission",
				this.suggestSubmission).append("id", this.id).append(
				"sentEmailContent", this.sentEmailContent).append(
				"inTheRepository", this.inTheRepository).toString();
	}
}
