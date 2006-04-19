/*
 * $Id: GenomicSegment.java,v 1.10 2006-04-19 17:37:37 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

/**
 * @author rajputs
 */
public class GenomicSegment extends EngineeredGene {

	private static final long serialVersionUID = 3259315453799404851L;

	private String locationOfIntegration;

	private String segmentSize;

	private String cloneDesignator;

	private Boolean isRandom;

	private String constructSequence;

	private SegmentType segmentType;

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
	 * @return Returns the segmentSize.
	 */
	public String getSegmentSize() {
		return segmentSize;
	}

	/**
	 * @param segmentSize
	 *            The segmentSize to set.
	 */
	public void setSegmentSize(String segmentSize) {
		this.segmentSize = segmentSize;
	}

	/**
	 * @return Returns the cloneDesignator.
	 */
	public String getCloneDesignator() {
		return cloneDesignator;
	}

	/**
	 * @param cloneDesignator
	 *            The cloneDesignator to set.
	 */
	public void setCloneDesignator(String cloneDesignator) {
		this.cloneDesignator = cloneDesignator;
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
	 * @return Returns the segmentType.
	 */
	public SegmentType getSegmentType() {
		return segmentType;
	}

	/**
	 * @param segmentType
	 *            The segmentType to set.
	 */
	public void setSegmentType(SegmentType segmentType) {
		this.segmentType = segmentType;
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
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = super.toString() + " - ";
		result += this.getLocationOfIntegration() + " - "
				+ this.getCloneDesignator() + " - " + this.getSegmentSize();
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
