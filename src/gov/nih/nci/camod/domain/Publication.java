/*
 * Created on May 4, 2005
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
public class Publication extends BaseObject implements Serializable {
	private Long id;
	private String volume;
	private Long endPage;
	private Long year;
	private String title;
	private Long pmid;
	private Long startPage;
	private String journal;
	private String authors;
	private boolean firstTimeReported;
	private PublicationStatus publicationStatus;
	
	/**
	 * @return Returns the firstTimeReported.
	 */
	public boolean isFirstTimeReported() {
		return firstTimeReported;
	}
	/**
	 * @param firstTimeReported The firstTimeReported to set.
	 */
	public void setFirstTimeReported(boolean firstTimeReported) {
		this.firstTimeReported = firstTimeReported;
	}
	/**
	 * @return Returns the authors.
	 */
	public String getAuthors() {
		return authors;
	}
	/**
	 * @param authors The authors to set.
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	/**
	 * @return Returns the endPage.
	 */
	public Long getEndPage() {
		return endPage;
	}
	/**
	 * @param endPage The endPage to set.
	 */
	public void setEndPage(Long endPage) {
		this.endPage = endPage;
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
	 * @return Returns the journal.
	 */
	public String getJournal() {
		return journal;
	}
	/**
	 * @param journal The journal to set.
	 */
	public void setJournal(String journal) {
		this.journal = journal;
	}
	/**
	 * @return Returns the pmid.
	 */
	public Long getPmid() {
		return pmid;
	}
	/**
	 * @param pmid The pmid to set.
	 */
	public void setPmid(Long pmid) {
		this.pmid = pmid;
	}
	/**
	 * @return Returns the publicationStatus.
	 */
	public PublicationStatus getPublicationStatus() {
		return publicationStatus;
	}
	/**
	 * @param publicationStatus The publicationStatus to set.
	 */
	public void setPublicationStatus(PublicationStatus publicationStatus) {
		this.publicationStatus = publicationStatus;
	}
	/**
	 * @return Returns the startPage.
	 */
	public Long getStartPage() {
		return startPage;
	}
	/**
	 * @param startPage The startPage to set.
	 */
	public void setStartPage(Long startPage) {
		this.startPage = startPage;
	}	
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return Returns the volume.
	 */
	public String getVolume() {
		return volume;
	}
	/**
	 * @param volume The volume to set.
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}
	/**
	 * @return Returns the year.
	 */
	public Long getYear() {
		return year;
	}
	/**
	 * @param year The year to set.
	 */
	public void setYear(Long year) {
		this.year = year;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Publication)) {
			return false;
		}
		Publication rhs = (Publication) object;
		return new EqualsBuilder().append(
				this.publicationStatus, rhs.publicationStatus).append(
				this.volume, rhs.volume).append(this.endPage, rhs.endPage)
				.append(this.year, rhs.year).append(this.pmid, rhs.pmid)
				.append(this.title, rhs.title).append(this.firstTimeReported,
						rhs.firstTimeReported)
				.append(this.journal, rhs.journal).append(this.startPage,
						rhs.startPage).append(this.authors, rhs.authors)
				.append(this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1959885453, 1508278755).append(this.publicationStatus).append(
				this.volume).append(this.endPage).append(this.year).append(
				this.pmid).append(this.title).append(this.firstTimeReported)
				.append(this.journal).append(this.startPage).append(
						this.authors).append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("journal", this.journal)
				.append("authors", this.authors).append("publicationStatus",
						this.publicationStatus).append("endPage", this.endPage)
				.append("startPage", this.startPage).append("pmid", this.pmid)
				.append("id", this.id).append("title", this.title).append(
						"volume", this.volume).append("firstTimeReported",
						this.firstTimeReported).append("year", this.year)
				.toString();
	}
}
