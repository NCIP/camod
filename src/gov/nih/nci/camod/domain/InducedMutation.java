/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.*;

public class InducedMutation extends EngineeredGene {

    private static final long serialVersionUID = 3259235453799404851L;

    private String geneId;
    private String description;
    //private EnvironmentalFactor environmentalFactor;
    private List geneticAlterationCollection = new ArrayList();
    private List environmentalFactorCollection = new ArrayList();

    /**
     * @return Returns the geneticAlterationCollection.
     */
    public List getGeneticAlterationCollection() {
        return geneticAlterationCollection;
    }

    /**
     * @param geneticAlterationCollection
     *            The geneticAlterationCollection to set.
     */
    public void setGeneticAlterationCollection(List geneticAlterationCollection) {
        this.geneticAlterationCollection = geneticAlterationCollection;
    }

    /**
     * @param geneticAlteration
     *            The geneticAlteration to add.
     */
    public void addGeneticAlteration(GeneticAlteration geneticAlteration) {
        geneticAlterationCollection.add(geneticAlteration);
    }

    /**
     * @return Returns the geneticAlterationCollection.
     */
    public List getEnvironmentalFactorCollection() {
        return environmentalFactorCollection;
    }

    /**
     * @param geneticAlterationCollection
     *            The geneticAlterationCollection to set.
     */
    public void setEnvironmentalFactorCollection(List environmentalFactorCollection) {
        this.environmentalFactorCollection = environmentalFactorCollection;
    }
    
    //TODO: this is not how we should do this and we should clean this up later
    public EnvironmentalFactor getEnvironmentalFactor () {
    	
		//EnvironmentalFactor nick = (EnvironmentalFactor) environmentalFactorCollection.get(0);
		//System.out.println( "******************* Nick=" + nick.getName() );
    	if ( environmentalFactorCollection.size() > 0 )
    		return (EnvironmentalFactor) environmentalFactorCollection.get(0);
    	else
    		return null;
    }
    
    /**
     * @param geneticAlteration
     *            The geneticAlteration to add.
     */
    public void addEnvironmentalFactor(EnvironmentalFactor environmentalFactor) {
        environmentalFactorCollection.add(environmentalFactor);
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
     * @return Returns the geneId.
     */
    public String getGeneId() {
        return geneId;
    }

    /**
     * @param geneId
     *            The geneId to set.
     */
    public void setGeneId(String geneId) {
        this.geneId = geneId;
    }

    /**
     * @return Returns the environmentalFactor.
     *
    public EnvironmentalFactor getEnvironmentalFactor() {
        return environmentalFactor;
    }

    **
     * @param environmentalFactor
     *            The environmentalFactor to set.
     *
    public void setEnvironmentalFactor(EnvironmentalFactor environmentalFactor) {
        this.environmentalFactor = environmentalFactor;
    }
    */

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof InducedMutation)) {
            return false;
        }
        InducedMutation rhs = (InducedMutation) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.description, rhs.description).append(
                this.geneticAlterationCollection, rhs.geneticAlterationCollection).append(this.geneId, rhs.geneId)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(185645383, 717000701).appendSuper(super.hashCode()).append(
        		this.description).append(this.geneticAlterationCollection).append(this.geneId).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("conditionality", this.getConditionality()).append("name",
                this.getName()).append("expressionFeatureCollection", this.getExpressionFeatureCollection()).append(
                "id", this.getId()).append("comments", this.getComments()).append("description", this.description)
                .append("image", this.getImage()).append("cabioId", this.getCabioId()).append(
                        "geneticAlterationCollection", this.geneticAlterationCollection).append("mutationIdentifier",
                        this.getMutationIdentifier()).append(
                        "genotypeSummary", this.getGenotypeSummary()).append("geneId", this.geneId).toString();
    }
}
