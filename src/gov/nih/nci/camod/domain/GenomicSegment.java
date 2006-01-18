/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GenomicSegment extends EngineeredGene
{

    private static final long serialVersionUID = 3259315453799404851L;

    private String locationOfIntegration;
    private String segmentSize;
    private String cloneDesignator;
    private List<SegmentType> segmentTypeCollection = new ArrayList<SegmentType>();

    /**
     * @return Returns the segmentTypeCollection.
     */
    public List<SegmentType> getSegmentTypeCollection()
    {
        return segmentTypeCollection;
    }

    public List<SegmentType> getSegmentTypeCollectionSorted()
    {
        if (segmentTypeCollection != null)
            return new ArrayList<SegmentType>(new TreeSet<SegmentType>(segmentTypeCollection));
        return null;
    }

    /**
     * @param segmentTypeCollection
     *            The segmentTypeCollection to set.
     */
    public void setSegmentTypeCollection(List<SegmentType> segmentTypeCollection)
    {
        this.segmentTypeCollection = segmentTypeCollection;
    }

    /**
     * @param segmentType
     *            The segmentType to add.
     */
    public void addSegmentType(SegmentType segmentType)
    {
        segmentTypeCollection.add(segmentType);
    }

    /**
     * @return Returns the cloneDesignator.
     */
    public String getCloneDesignator()
    {
        return cloneDesignator;
    }

    /**
     * @param cloneDesignator
     *            The cloneDesignator to set.
     */
    public void setCloneDesignator(String cloneDesignator)
    {
        this.cloneDesignator = cloneDesignator;
    }

    /**
     * @return Returns the locationOfIntegration.
     */
    public String getLocationOfIntegration()
    {
        return locationOfIntegration;
    }

    /**
     * @param locationOfIntegration
     *            The locationOfIntegration to set.
     */
    public void setLocationOfIntegration(String locationOfIntegration)
    {
        this.locationOfIntegration = locationOfIntegration;
    }

    /**
     * @return Returns the segmentSize.
     */
    public String getSegmentSize()
    {
        return segmentSize;
    }

    /**
     * @param segmentSize
     *            The segmentSize to set.
     */
    public void setSegmentSize(String segmentSize)
    {
        this.segmentSize = segmentSize;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getLocationOfIntegration() + " - " + this.getCloneDesignator() + " - " + this.getSegmentSize();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        return true;
    }

}
