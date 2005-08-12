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
public class MicroArrayData extends BaseObject implements Serializable {
	private Long id;
	private String experimentName;
	private Long experimentId;
	private String otherLocationURL;
	private Availability availability;
	
	/**
	 * @return Returns the otherLocationURL.
	 */
	public String getOtherLocationURL() {
		return otherLocationURL;
	}
	/**
	 * @param otherLocationURL The otherLocationURL to set.
	 */
	public void setOtherLocationURL(String otherLocationURL) {
		this.otherLocationURL = otherLocationURL;
	}
	/**
	 * @return Returns the availability.
	 */
	public Availability getAvailability() {
		return availability;
	}
	/**
	 * @param availability The availability to set.
	 */
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}
	/**
	 * @return Returns the experimentId.
	 */
	public Long getExperimentId() {
		return experimentId;
	}
	/**
	 * @param experimentId The experimentId to set.
	 */
	public void setExperimentId(Long experimentId) {
		this.experimentId = experimentId;
	}
	/**
	 * @return Returns the experimentName.
	 */
	public String getExperimentName() {
		return experimentName;
	}
	/**
	 * @param experimentName The experimentName to set.
	 */
	public void setExperimentName(String experimentName) {
		this.experimentName = experimentName;
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
		if (!(object instanceof MicroArrayData)) {
			return false;
		}
		MicroArrayData rhs = (MicroArrayData) object;
		return new EqualsBuilder().append(
				this.availability, rhs.availability).append(
				this.otherLocationURL, rhs.otherLocationURL).append(
				this.experimentName, rhs.experimentName).append(
				this.experimentId, rhs.experimentId).append(this.id, rhs.id)
				.isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1366507925, 1797915275).append(this.availability).append(
				this.otherLocationURL).append(this.experimentName).append(
				this.experimentId).append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append(
				"availability", this.availability).append("experimentName",
				this.experimentName).append("otherLocationURL",
				this.otherLocationURL)
				.append("experimentId", this.experimentId).toString();
	}
}
