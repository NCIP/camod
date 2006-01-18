/*
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2005/11/14 14:16:51  georgeda
 * Cleanup
 *
 * 
 * $Id: AbstractCancerModel.java,v 1.12 2006-01-18 14:23:31 georgeda Exp $
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

import java.io.Serializable;
import java.util.*;

public class AbstractCancerModel extends BaseObject implements Serializable, CancerModel, Duplicatable, Comparable {

    private static final long serialVersionUID = 4259765453799404851L;
    
    private String experimentDesign;
    private String modelDescriptor;
    private String state;
    private List<Publication> publicationCollection = new ArrayList<Publication>();
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
    public List<Publication> getPublicationCollection() {            
      return publicationCollection;          
    }
      
    public List<Publication> getPublicationCollectionSorted() {      
      if (publicationCollection != null) return new ArrayList<Publication>(new TreeSet<Publication>(publicationCollection));
      return null;
    }    

    /**
     * @param publicationCollection
     *            The publicationCollection to set.
     */
    public void setPublicationCollection(List<Publication> publicationCollection) {
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
      final AbstractCancerModel obj = (AbstractCancerModel) o;
      if (HashCodeUtil.notEqual(this.getModelDescriptor(), obj.getModelDescriptor())) return false;
      return true;
    }
     
    public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash(result, this.getModelDescriptor());    
      return result + super.hashCode();    
    }  
    
    public int compareTo(Object o) {
      if ((o instanceof AbstractCancerModel) && (this.getModelDescriptor() != null) && (((AbstractCancerModel)o).getModelDescriptor() != null)) {   
        int result = this.getModelDescriptor().compareTo( ((AbstractCancerModel)o).getModelDescriptor() );
        if (result != 0) { return result; }               
      }

      return super.compareTo(o);
    }  
    
}
