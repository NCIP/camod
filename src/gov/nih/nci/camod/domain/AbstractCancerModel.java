/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AbstractCancerModel extends BaseObject implements Serializable, CancerModel {

    private static final long serialVersionUID = 4259765453799404851L;

    private Long id;
    private String experimentDesign;
    private String modelDescriptor;
    private String state;
    private List publicationCollection = new ArrayList();
    private Availability availability;
    private Taxon species;
    private Person submitter;
    private Person principalInvestigator;

    /**
     * @return Returns the principalInvestigator.
     */
    public Party getPrincipalInvestigator() {
        return principalInvestigator;
    }

    /**
     * @param principalInvestigator
     *            The principalInvestigator to set.
     */
    public void setPrincipalInvestigator(Person principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }

    /**
     * @return Returns the submitter.
     */
    public Person getSubmitter() {
        return submitter;
    }

    /**
     * @param submitter
     *            The submitter to set.
     */
    public void setSubmitter(Person submitter) {
        this.submitter = submitter;
    }

    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return Returns the species.
     */
    public Taxon getSpecies() {
        return species;
    }

    /**
     * @param species
     *            The species to set.
     */
    public void setSpecies(Taxon species) {
        this.species = species;
    }

    /**
     * @return Returns the availability.
     */
    public Availability getAvailability() {
        return availability;
    }

    /**
     * @param availability
     *            The availability to set.
     */
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    /**
     * @return Returns the publicationCollection.
     */
    public List getPublicationCollection() {
        return publicationCollection;
    }

    /**
     * @param publicationCollection
     *            The publicationCollection to set.
     */
    public void setPublicationCollection(List publicationCollection) {
        this.publicationCollection = publicationCollection;
    }

    /**
     * @param publication
     *            The publication to add.
     */
    public void addPublication(Publication publication) {
        publicationCollection.add(publication);
    }

    /**
     * @return Returns the experimentDesign.
     */
    public String getExperimentDesign() {
        return experimentDesign;
    }

    /**
     * @param experimentDesign
     *            The experimentDesign to set.
     */
    public void setExperimentDesign(String experimentDesign) {
        this.experimentDesign = experimentDesign;
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
     * @return Returns the modelDescriptor.
     */
    public String getModelDescriptor() {
        return modelDescriptor;
    }

    /**
     * @param modelDescriptor
     *            The modelDescriptor to set.
     */
    public void setModelDescriptor(String modelDescriptor) {
        this.modelDescriptor = modelDescriptor;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof AbstractCancerModel)) {
            return false;
        }
        AbstractCancerModel rhs = (AbstractCancerModel) object;
        return new EqualsBuilder().append(this.principalInvestigator, rhs.principalInvestigator).append(
                this.experimentDesign, rhs.experimentDesign).append(this.availability, rhs.availability).append(
                this.submitter, rhs.submitter).append(this.state, rhs.state).append(this.publicationCollection,
                rhs.publicationCollection).append(this.modelDescriptor, rhs.modelDescriptor).append(this.id, rhs.id)
                .append(this.species, rhs.species).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-294059533, -659032637).append(this.principalInvestigator).append(
                this.experimentDesign).append(this.availability).append(this.submitter).append(this.state).append(
                this.publicationCollection).append(this.modelDescriptor).append(this.id).append(this.species)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("species", this.species).append("state", this.state).append("id",
                this.id).append("availability", this.availability).append("experimentDesign", this.experimentDesign)
                .append("submitter", this.submitter).append("principalInvestigator", this.principalInvestigator)
                .append("publicationCollection", this.publicationCollection).append("modelDescriptor",
                        this.modelDescriptor).toString();
    }
}
