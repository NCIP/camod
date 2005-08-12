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
 * @author pandyas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Log extends BaseObject implements Serializable {
	private Long id;
	private String notes;
	private String type;
	private String subsystem;
	private String timestamp;
	private AbstractCancerModel cancerModel;
	private Comments comment;
	private Party submitter;	
	
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
	 * @return Returns the notes.
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}		
	/**
	 * @return Returns the subsystem.
	 */
	public String getSubsystem() {
		return subsystem;
	}
	/**
	 * @param subsystem The subsystem to set.
	 */
	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}	
	/**
	 * @return Returns the timestamp.
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp The timestamp to set.
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return Returns the cancerModel.
	 */
	public AbstractCancerModel getCancerModel() {
		return cancerModel;
	}
	/**
	 * @param cancerModel The cancerModel to set.
	 */
	public void setCancerModel(AbstractCancerModel cancerModel) {			
		this.cancerModel = cancerModel;
	}
	/**
	 * @return Returns the comment.
	 */
	public Comments getComment() {
		return comment;
	}
	/**
	 * @param comment The comment to set.
	 */
	public void setComment(Comments comment) {			
		this.comment = comment;
	}	
	/**
	 * @return Returns the submitter.
	 */
	public Party getSubmitter() {
		return submitter;
	}
	/**
	 * @param submitter The submitter to set.
	 */
	public void setSubmitter(Party submitter) {
		this.submitter = submitter;
	}	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1727118201, -1843324729).append(this.notes).append(
				this.type).append(this.subsystem).append(
				this.timestamp).append(this.cancerModel).append(
				this.comment).append(this.submitter).append(
				this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append(
				"notes", this.notes).append("type", this.type).append(
				"subsystem", this.subsystem).append(
				"timestamp", this.timestamp).append(
				"cancerModel", this.cancerModel).append(
				"comment", this.comment).append(
				"submitter", this.submitter).toString();
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Log)) {
			return false;
		}
		Log rhs = (Log) object;
		return new EqualsBuilder().append(
				this.notes, rhs.notes).append(
				this.type, rhs.type).append(
				this.subsystem, rhs.subsystem).append(
				this.timestamp, rhs.timestamp).append(
				this.cancerModel, rhs.cancerModel).append(
				this.comment, rhs.comment).append(	
				this.submitter, rhs.submitter).append(						
				this.id, rhs.id).isEquals();
	}
}
