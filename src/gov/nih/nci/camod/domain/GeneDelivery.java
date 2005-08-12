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
public class GeneDelivery extends BaseObject implements Serializable {
	private Long id;
	private String viralVector;
	private String geneInVirus;
	private Organ organ;
	private Treatment treatment;
	
	/**
	 * @return Returns the treatment.
	 */
	public Treatment getTreatment() {
		return treatment;
	}
	/**
	 * @param treatment The treatment to set.
	 */
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
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
	 * @return Returns the geneInVirus.
	 */
	public String getGeneInVirus() {
		return geneInVirus;
	}
	/**
	 * @param geneInVirus The geneInVirus to set.
	 */
	public void setGeneInVirus(String geneInVirus) {
		this.geneInVirus = geneInVirus;
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
	 * @return Returns the viralVector.
	 */
	public String getViralVector() {
		return viralVector;
	}
	/**
	 * @param viralVector The viralVector to set.
	 */
	public void setViralVector(String viralVector) {
		this.viralVector = viralVector;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof GeneDelivery)) {
			return false;
		}
		GeneDelivery rhs = (GeneDelivery) object;
		return new EqualsBuilder().append(
				this.geneInVirus, rhs.geneInVirus).append(this.treatment,
				rhs.treatment).append(this.organ, rhs.organ).append(
				this.viralVector, rhs.viralVector).append(this.id, rhs.id)
				.isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(651543349, -512038163).append(this.geneInVirus).append(
				this.treatment).append(this.organ).append(this.viralVector)
				.append(this.id).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append(
				"geneInVirus", this.geneInVirus).append("organ", this.organ)
				.append("treatment", this.treatment).append("viralVector",
						this.viralVector).toString();
	}
}
