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
public class Transgene extends EngineeredGene {

    private static final long serialVersionUID = 3258505453799404851L;

    private String locationOfIntegration;
    private List regulatoryElementCollection = new ArrayList();
    private List taxonCollection = new ArrayList();

    /**
     * @return Returns the taxonCollection.
     */
    public List getTaxonCollection() {
        return taxonCollection;
    }

    /**
     * @param taxonCollection
     *            The taxonCollection to set.
     */
    public void setTaxonCollection(List taxonCollection) {
        this.taxonCollection = taxonCollection;
    }

    /**
     * @param taxon
     *            The taxon to add.
     */
    public void addTaxon(Taxon taxon) {
        taxonCollection.add(taxon);
    }

    /**
     * @return Returns the regulatoryElementCollection.
     */
    public List getRegulatoryElementCollection() {
        return regulatoryElementCollection;
    }

    /**
     * @param regulatoryElementCollection
     *            The regulatoryElementCollection to set.
     */
    public void setRegulatoryElementCollection(List regulatoryElementCollection) {
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
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Transgene)) {
            return false;
        }
        Transgene rhs = (Transgene) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.taxonCollection, rhs.taxonCollection)
                .append(this.locationOfIntegration, rhs.locationOfIntegration).append(this.regulatoryElementCollection,
                        rhs.regulatoryElementCollection).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1792793975, 1838484929).appendSuper(super.hashCode()).append(this.taxonCollection)
                .append(this.locationOfIntegration).append(this.regulatoryElementCollection).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("conditionality", this.getConditionality()).append(
                "locationOfIntegration", this.locationOfIntegration).append("name", this.getName()).append(
                "expressionFeatureCollection", this.getExpressionFeatureCollection()).append("id", this.getId())
                .append("comments", this.getComments()).append("image", this.getImage()).append("cabioId",
                        this.getCabioId()).append("taxonCollection", this.taxonCollection).append("mutationIdentifier",
                        this.getMutationIdentifier()).append("regulatoryElementCollection",
                        this.regulatoryElementCollection).append("genotypeSummary", this.getGenotypeSummary())
                .toString();
    }
}
