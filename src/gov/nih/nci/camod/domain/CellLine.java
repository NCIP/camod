/**
 * 
 * $Id: CellLine.java,v 1.8 2005-10-21 19:40:21 piparom Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2005/10/20 20:25:00  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.camod.util.Duplicatable;

public class CellLine extends BaseObject implements Serializable, Duplicatable {

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
