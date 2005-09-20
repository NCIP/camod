/*
 * Created on May 5, 2005
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
public class GenotypeSummary extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259295453799404851L;

    private Long id;
    private String summary;
    private String genotype;
    private Nomenclature nomenclature;

    /**
     * @return Returns the genotype.
     */
    public String getGenotype() {
        return genotype;
    }

    /**
     * @param genotype
     *            The genotype to set.
     */
    public void setGenotype(String genotype) {
        this.genotype = genotype;
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
     * @return Returns the nomenclature.
     */
    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    /**
     * @param nomenclature
     *            The nomenclature to set.
     */
    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    /**
     * @return Returns the summary.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     *            The summary to set.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof GenotypeSummary)) {
            return false;
        }
        GenotypeSummary rhs = (GenotypeSummary) object;
        return new EqualsBuilder().append(this.summary, rhs.summary).append(this.nomenclature, rhs.nomenclature)
                .append(this.genotype, rhs.genotype).append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-101066549, 1339728487).append(this.summary).append(this.nomenclature).append(
                this.genotype).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("id", this.id).append("genotype", this.genotype).append("summary",
                this.summary).append("nomenclature", this.nomenclature).toString();
    }
}
