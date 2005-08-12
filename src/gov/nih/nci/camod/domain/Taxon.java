/*
 * Created on May 5, 2005
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
public class Taxon extends BaseObject implements Serializable {
	private Long id;
	private String scientificName;
	private String ethnicityStrain;
	private String abbreviation;
	private String commonName;
	
	/**
	 * @return Returns the abbreviation.
	 */
	public String getAbbreviation() {
		return abbreviation;
	}
	/**
	 * @param abbreviation The abbreviation to set.
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	/**
	 * @return Returns the commonName.
	 */
	public String getCommonName() {
		return commonName;
	}
	/**
	 * @param commonName The commonName to set.
	 */
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	/**
	 * @return Returns the ethnicityStrain.
	 */
	public String getEthnicityStrain() {
		return ethnicityStrain;
	}
	/**
	 * @param ethnicityStrain The ethnicityStrain to set.
	 */
	public void setEthnicityStrain(String ethnicityStrain) {
		this.ethnicityStrain = ethnicityStrain;
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
	 * @return Returns the scientificName.
	 */
	public String getScientificName() {
		return scientificName;
	}
	/**
	 * @param scientificName The scientificName to set.
	 */
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Taxon)) {
			return false;
		}
		Taxon rhs = (Taxon) object;
		return new EqualsBuilder().append(
				this.scientificName, rhs.scientificName).append(
				this.ethnicityStrain, rhs.ethnicityStrain).append(
				this.abbreviation, rhs.abbreviation).append(this.commonName,
				rhs.commonName).append(this.id, rhs.id).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1425898483, 492718709).append(this.scientificName).append(
				this.ethnicityStrain).append(this.abbreviation).append(
				this.commonName).append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append(
				"commonName", this.commonName).append("ethnicityStrain",
				this.ethnicityStrain).append("abbreviation", this.abbreviation)
				.append("scientificName", this.scientificName).toString();
	}
}
