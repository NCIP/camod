/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RegulatoryElement extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3258725453799404851L;

    private Long id;
    private String name;
    private RegulatoryElementType regulatoryElementType;
    private Taxon taxon;

    /**
     * @return Returns the taxon.
     */
    public Taxon getTaxon() {
        return taxon;
    }

    /**
     * @param taxon
     *            The taxon to set.
     */
    public void setTaxon(Taxon taxon) {
        this.taxon = taxon;
    }

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the regulatoryElementType.
     */
    public RegulatoryElementType getRegulatoryElementType() {
        return regulatoryElementType;
    }

    /**
     * @param regulatoryElementType
     *            The regulatoryElementType to set.
     */
    public void setRegulatoryElementType(RegulatoryElementType regulatoryElementType) {
        this.regulatoryElementType = regulatoryElementType;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof RegulatoryElement)) {
            return false;
        }
        RegulatoryElement rhs = (RegulatoryElement) object;
        return new EqualsBuilder().append(this.regulatoryElementType, rhs.regulatoryElementType).append(this.taxon,
                rhs.taxon).append(this.name, rhs.name).append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1988715503, 91372047).append(this.regulatoryElementType).append(this.taxon).append(
                this.name).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("name", this.name).append("id", this.id).append(
                "regulatoryElementType", this.regulatoryElementType).append("taxon", this.taxon).toString();
    }
}
