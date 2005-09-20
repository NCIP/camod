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
public class Therapy extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3258525453799404851L;

    private Long id;
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
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Therapy)) {
            return false;
        }
        Therapy rhs = (Therapy) object;
        return new EqualsBuilder().append(this.tumorResponse, rhs.tumorResponse).append(this.comments, rhs.comments)
                .append(this.experiment, rhs.experiment).append(this.therapeuticExperiment, rhs.therapeuticExperiment)
                .append(this.agent, rhs.agent).append(this.toxicityGrade, rhs.toxicityGrade).append(this.treatment,
                        rhs.treatment).append(this.publicationCollection, rhs.publicationCollection).append(
                        this.biomarker, rhs.biomarker).append(this.results, rhs.results).append(this.id, rhs.id)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(288096921, 542841797).append(this.tumorResponse).append(this.comments).append(
                this.experiment).append(this.therapeuticExperiment).append(this.agent).append(this.toxicityGrade)
                .append(this.treatment).append(this.publicationCollection).append(this.biomarker).append(this.results)
                .append(this.id).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("experiment", this.experiment).append("tumorResponse",
                this.tumorResponse).append("id", this.id).append("comments", this.comments).append("agent", this.agent)
                .append("publicationCollection", this.publicationCollection).append("biomarker", this.biomarker)
                .append("therapeuticExperiment", this.therapeuticExperiment).append("treatment", this.treatment)
                .append("results", this.results).append("toxicityGrade", this.toxicityGrade).toString();
    }
}
