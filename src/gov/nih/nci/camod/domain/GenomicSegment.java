/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GenomicSegment extends EngineeredGene {
	private String locationOfIntegration;

	private String segmentSize;

	private String cloneDesignator;

	private List segmentTypeCollection = new ArrayList();

	/**
	 * @return Returns the segmentTypeCollection.
	 */
	public List getSegmentTypeCollection() {
		return segmentTypeCollection;
	}

	/**
	 * @param segmentTypeCollection
	 *            The segmentTypeCollection to set.
	 */
	public void setSegmentTypeCollection(List segmentTypeCollection) {
		this.segmentTypeCollection = segmentTypeCollection;
	}

	/**
	 * @param segmentType
	 *            The segmentType to add.
	 */
	public void addSegmentType(SegmentType segmentType) {
		segmentTypeCollection.add(segmentType);
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
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof GenomicSegment)) {
			return false;
		}
		GenomicSegment rhs = (GenomicSegment) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.locationOfIntegration, rhs.locationOfIntegration).append(
				this.segmentSize, rhs.segmentSize).append(this.cloneDesignator,
				rhs.cloneDesignator).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1738802159, 1516001829).appendSuper(
				super.hashCode()).append(this.locationOfIntegration).append(
				this.segmentSize).append(this.cloneDesignator).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("conditionality",
				this.getConditionality()).append("locationOfIntegration",
				this.locationOfIntegration).append("name", this.getName())
				.append("id", this.getId()).append("comments",
						this.getComments()).append("image", this.getImage())
				.append("organCollection", this.getOrganCollection()).append(
						"segmentSize", this.segmentSize).append("cabioId",
						this.getCabioId()).append("cloneDesignator",
						this.cloneDesignator).append("mutationIdentifier",
						this.getMutationIdentifier()).append("genotypeSummary",
						this.getGenotypeSummary()).toString();
	}
}
