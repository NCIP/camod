/*
 * $Id: Transgene.java,v 1.12 2006-04-17 19:13:46 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.domain;

import java.util.*;

/**
 * @author rajputs
 */
public class Transgene extends EngineeredGene {

	private static final long serialVersionUID = 3258505453799404851L;

	private String locationOfIntegration;

	private Boolean isRandom;

	private String constructSequence;
	
	private Species species;	

	private Set<RegulatoryElement> regulatoryElementCollection = new TreeSet<RegulatoryElement>();



	/**
	 * @return Returns the locationOfIntegration.
	 */
	public String getLocationOfIntegration() {
		return locationOfIntegration;
	}

	/**
	 * @param locationOfIntegration
	 *            The locationOfIntegration to set.
	 */
	public void setLocationOfIntegration(String locationOfIntegration) {
		this.locationOfIntegration = locationOfIntegration;
	}

	/**
	 * @return Returns the isRandom.
	 */
	public Boolean getIsRandom() {
		return isRandom;
	}

	/**
	 * @param isRandom
	 *            The isRandom to set.
	 */
	public void setIsRandom(Boolean isRandom) {
		this.isRandom = isRandom;
	}

	/**
	 * @return Returns the constructSequence.
	 */
	public String getConstructSequence() {
		return constructSequence;
	}

	/**
	 * @param constructSequence
	 *            The constructSequence to set.
	 */
	public void setConstructSequence(String constructSequence) {
		this.constructSequence = constructSequence;
	}

	/**
	 * @return Returns the species.
	 */
	public Species getSpecies() {
		return species;
	}

	/**
	 * @param species
	 *            The species to set.
	 */
	public void setSpecies(Species species) {
		this.species = species;
	}

	/**
	 * @return Returns the regulatoryElementCollection.
	 */
	public Set<RegulatoryElement> getRegulatoryElementCollection() {
		return regulatoryElementCollection;
	}

	/**
	 * @param regulatoryElementCollection
	 *            The regulatoryElementCollection to set.
	 */
	public void setRegulatoryElementCollection(
			Set<RegulatoryElement> regulatoryElementCollection) {
		this.regulatoryElementCollection = regulatoryElementCollection;
	}

	/**
	 * @param regulatoryElement
	 *            The regulatoryElement to add.
	 */
	public void addRegulatoryElement(RegulatoryElement regulatoryElement) {
		regulatoryElementCollection.add(regulatoryElement);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = super.toString() + " - ";
		result += this.getLocationOfIntegration();
		return result;
	}

	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;
		if (!(this.getClass().isInstance(o)))
			return false;
		return true;
	}
}
