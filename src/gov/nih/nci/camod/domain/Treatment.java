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
public class Treatment extends BaseObject implements Serializable {
	private Long id;
	private String regimen;
	private String dosage;
	private String administrativeRoute;
	private String ageAtTreatment;
	private SexDistribution sexDistribution;
	
	/**
	 * @return Returns the sexDistribution.
	 */
	public SexDistribution getSexDistribution() {
		return sexDistribution;
	}
	/**
	 * @param sexDistribution The sexDistribution to set.
	 */
	public void setSexDistribution(SexDistribution sexDistribution) {
		this.sexDistribution = sexDistribution;
	}
	/**
	 * @return Returns the administrativeRoute.
	 */
	public String getAdministrativeRoute() {
		return administrativeRoute;
	}
	/**
	 * @param administrativeRoute The administrativeRoute to set.
	 */
	public void setAdministrativeRoute(String administrativeRoute) {
		this.administrativeRoute = administrativeRoute;
	}
	/**
	 * @return Returns the ageAtTreatment.
	 */
	public String getAgeAtTreatment() {
		return ageAtTreatment;
	}
	/**
	 * @param ageAtTreatment The ageAtTreatment to set.
	 */
	public void setAgeAtTreatment(String ageAtTreatment) {
		this.ageAtTreatment = ageAtTreatment;
	}
	/**
	 * @return Returns the dosage.
	 */
	public String getDosage() {
		return dosage;
	}
	/**
	 * @param dosage The dosage to set.
	 */
	public void setDosage(String dosage) {
		this.dosage = dosage;
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
	 * @return Returns the regimen.
	 */
	public String getRegimen() {
		return regimen;
	}
	/**
	 * @param regimen The regimen to set.
	 */
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Treatment)) {
			return false;
		}
		Treatment rhs = (Treatment) object;
		return new EqualsBuilder().append(
				this.ageAtTreatment, rhs.ageAtTreatment).append(this.regimen,
				rhs.regimen).append(this.dosage, rhs.dosage).append(
				this.sexDistribution, rhs.sexDistribution).append(this.id,
				rhs.id).append(this.administrativeRoute,
				rhs.administrativeRoute).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1737623555, 1630365737).append(this.ageAtTreatment).append(
				this.regimen).append(this.dosage).append(this.sexDistribution)
				.append(this.id).append(this.administrativeRoute).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("sexDistribution",
				this.sexDistribution).append("id", this.id).append("regimen",
				this.regimen).append("ageAtTreatment", this.ageAtTreatment)
				.append("dosage", this.dosage).append("administrativeRoute",
						this.administrativeRoute).toString();
	}
}
