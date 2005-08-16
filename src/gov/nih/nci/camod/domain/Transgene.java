/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

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
public class Transgene extends EngineeredGene {
	private String locationOfIntegration;
	private List regulatoryElementCollection = new ArrayList();
	private Taxon species;
	
	/**
	 * @return Returns the species.
	 */
	public Taxon getSpecies() {
		return species;
	}
	/**
	 * @param species The species to set.
	 */
	public void setSpecies(Taxon species) {
		this.species = species;
	}
	/**
	 * @return Returns the regulatoryElementCollection.
	 */
	public List getRegulatoryElementCollection() {
		return regulatoryElementCollection;
	}
	/**
	 * @param regulatoryElementCollection The regulatoryElementCollection to set.
	 */
	public void setRegulatoryElementCollection(List regulatoryElementCollection) {
		this.regulatoryElementCollection = regulatoryElementCollection;
	}
	/**
	 * @param regulatoryElement The regulatoryElement to add.
	 */
	public void addRegulatoryElement(RegulatoryElement regulatoryElement) {
		regulatoryElementCollection.add(regulatoryElement);
	}	
	/**
	 * @return Returns the locationOfIntegration.
	 */
	public String getLocationOfIntegration() {
		return locationOfIntegration;
	}
	/**
	 * @param locationOfIntegration The locationOfIntegration to set.
	 */
	public void setLocationOfIntegration(String locationOfIntegration) {
		this.locationOfIntegration = locationOfIntegration;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Transgene)) {
			return false;
		}
		Transgene rhs = (Transgene) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.locationOfIntegration, rhs.locationOfIntegration).append(
				this.regulatoryElementCollection,
				rhs.regulatoryElementCollection).append(this.species,
				rhs.species).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-570776691, 2101951297).appendSuper(
				super.hashCode()).append(this.locationOfIntegration).append(
				this.regulatoryElementCollection).append(this.species)
				.toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("conditionality",
				this.getConditionality()).append("species", this.species)
				.append("name", this.getName()).append("locationOfIntegration",
						this.locationOfIntegration).append("id", this.getId())
				.append("comments", this.getComments()).append("image",
						this.getImage()).append("organCollection",
						this.getOrganCollection()).append("cabioId",
						this.getCabioId()).append("mutationIdentifier",
						this.getMutationIdentifier()).append("genotypeSummary",
						this.getGenotypeSummary()).append(
						"regulatoryElementCollection",
						this.regulatoryElementCollection).toString();
	}
}
