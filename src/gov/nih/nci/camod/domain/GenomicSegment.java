/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GenomicSegment extends EngineeredGene {

    private static final long serialVersionUID = 3259315453799404851L;

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
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.locationOfIntegration,
                rhs.locationOfIntegration).append(this.segmentTypeCollection, rhs.segmentTypeCollection).append(
                this.segmentSize, rhs.segmentSize).append(this.cloneDesignator, rhs.cloneDesignator).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1013910251, -2066007607).appendSuper(super.hashCode()).append(
                this.locationOfIntegration).append(this.segmentTypeCollection).append(this.segmentSize).append(
                this.cloneDesignator).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("conditionality", this.getConditionality()).append(
                "locationOfIntegration", this.locationOfIntegration).append("name", this.getName()).append(
                "expressionFeatureCollection", this.getExpressionFeatureCollection()).append("id", this.getId())
                .append("comments", this.getComments()).append("image", this.getImage()).append("segmentSize",
                        this.segmentSize).append("cabioId", this.getCabioId()).append("cloneDesignator",
                        this.cloneDesignator).append("mutationIdentifier", this.getMutationIdentifier()).append(
                        "segmentTypeCollection", this.segmentTypeCollection).append("genotypeSummary",
                        this.getGenotypeSummary()).toString();
    }
}
