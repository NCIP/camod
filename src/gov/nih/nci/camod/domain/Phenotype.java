/*
 * Created on May 6, 2005
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
public class Phenotype extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3258775453799404851L;

    private Long id;
    private String breedingNotes;
    private String description;
    private SexDistribution sexDistribution;

    /**
     * @return Returns the sexDistribution.
     */
    public SexDistribution getSexDistribution() {
        return sexDistribution;
    }

    /**
     * @param sexDistribution
     *            The sexDistribution to set.
     */
    public void setSexDistribution(SexDistribution sexDistribution) {
        this.sexDistribution = sexDistribution;
    }

    /**
     * @return Returns the breedingNotes.
     */
    public String getBreedingNotes() {
        return breedingNotes;
    }

    /**
     * @param breedingNotes
     *            The breedingNotes to set.
     */
    public void setBreedingNotes(String breedingNotes) {
        this.breedingNotes = breedingNotes;
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Phenotype)) {
            return false;
        }
        Phenotype rhs = (Phenotype) object;
        return new EqualsBuilder().append(this.description, rhs.description).append(this.sexDistribution,
                rhs.sexDistribution).append(this.breedingNotes, rhs.breedingNotes).append(this.id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(640880873, -980896221).append(this.description).append(this.sexDistribution).append(
                this.breedingNotes).append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("sexDistribution", this.sexDistribution).append("id", this.id).append(
                "description", this.description).append("breedingNotes", this.breedingNotes).toString();
    }
}
