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

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Therapy extends BaseObject implements Serializable, Duplicatable {

    private static final long serialVersionUID = 3258525453799404851L;
    
    private String experiment;
    private String comments;
    private String results;
    private String toxicityGrade;
    private String biomarker;
    private String tumorResponse;
    private Treatment treatment;
    private List publicationCollection = new ArrayList();
    private Agent agent;
    private Boolean therapeuticExperiment;

    /**
     * @return Returns the therapeuticExperiment.
     */
    public Boolean getTherapeuticExperiment() {
        return therapeuticExperiment;
    }

    /**
     * @param therapeuticExperiment
     *            The therapeuticExperiment to set.
     */
    public void setTherapeuticExperiment(Boolean therapeuticExperiment) {
        this.therapeuticExperiment = therapeuticExperiment;
    }

    /**
     * @return Returns the agent.
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * @param agent
     *            The agent to set.
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * @return Returns the biomarker.
     */
    public String getBiomarker() {
        return biomarker;
    }

    /**
     * @param biomarker
     *            The biomarker to set.
     */
    public void setBiomarker(String biomarker) {
        this.biomarker = biomarker;
    }

    /**
     * @return Returns the toxicityGrade.
     */
    public String getToxicityGrade() {
        return toxicityGrade;
    }

    /**
     * @param toxicityGrade
     *            The toxicityGrade to set.
     */
    public void setToxicityGrade(String toxicityGrade) {
        this.toxicityGrade = toxicityGrade;
    }

    /**
     * @return Returns the tumorResponse.
     */
    public String getTumorResponse() {
        return tumorResponse;
    }

    /**
     * @param tumorResponse
     *            The tumorResponse to set.
     */
    public void setTumorResponse(String tumorResponse) {
        this.tumorResponse = tumorResponse;
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
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return Returns the experiment.
     */
    public String getExperiment() {
        return experiment;
    }

    /**
     * @param experiment
     *            The experiment to set.
     */
    public void setExperiment(String experiment) {
        this.experiment = experiment;
    }
 

    /**
     * @return Returns the results.
     */
    public String getResults() {
        return results;
    }

    /**
     * @param results
     *            The results to set.
     */
    public void setResults(String results) {
        this.results = results;
    }

    /**
     * @return Returns the treatment.
     */
    public Treatment getTreatment() {
        return treatment;
    }

    /**
     * @param treatment
     *            The treatment to set.
     */
    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

     /**
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getExperiment();
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
