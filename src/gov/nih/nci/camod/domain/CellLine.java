/*
 * Created on May 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CellLine extends BaseObject implements Serializable {

    private static final long serialVersionUID = 3259655453799404851L;
    
    private String comments;
    private String experiment;
    private String name;
    private String results;
    private List publicationCollection = new ArrayList();
    private Organ organ;

    /**
     * @return Returns the organ.
     */
    public Organ getOrgan() {
        return organ;
    }

    /**
     * @param organ
     *            The organ to set.
     */
    public void setOrgan(Organ organ) {
        this.organ = organ;
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
      String result = super.toString() + " - ";      
      result += this.getName();          
      return result;
    }     
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
    
}
