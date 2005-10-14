/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.ArrayList;
import java.util.List;

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
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getGeneId() + " - " + this.getDescription();
       return result;
     }  
     
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
     
}
