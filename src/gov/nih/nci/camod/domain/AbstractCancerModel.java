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
import java.util.Collections;

import gov.nih.nci.camod.util.Duplicatable;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AbstractCancerModel extends BaseObject implements Serializable, CancerModel, Duplicatable {

    private static final long serialVersionUID = 4259765453799404851L;
    
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
    public Person getPrincipalInvestigator() {
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
      if (publicationCollection != null) Collections.sort(publicationCollection);
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
      String result = super.toString() + " - ";      
      result += this.getModelDescriptor();                
      return result;
    }       
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
    
}
