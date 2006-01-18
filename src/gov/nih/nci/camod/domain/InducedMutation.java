/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.util.*;

public class InducedMutation extends EngineeredGene
{
    private static final long serialVersionUID = 3259235453799404851L;

    private String geneId;
    private String description;
    private List<GeneticAlteration> geneticAlterationCollection = new ArrayList<GeneticAlteration>();
    private List<EnvironmentalFactor> environmentalFactorCollection = new ArrayList<EnvironmentalFactor>();

    /**
     * @return Returns the geneticAlterationCollection.
     */
    public List<GeneticAlteration> getGeneticAlterationCollection()
    {
        return geneticAlterationCollection;
    }

    public List<GeneticAlteration> getGeneticAlterationCollectionSorted()
    {
        if (geneticAlterationCollection != null)
            return new ArrayList<GeneticAlteration>(new TreeSet<GeneticAlteration>(geneticAlterationCollection));
        return null;
    }

    /**
     * @param geneticAlterationCollection
     *            The geneticAlterationCollection to set.
     */
    public void setGeneticAlterationCollection(List<GeneticAlteration> geneticAlterationCollection)
    {
        this.geneticAlterationCollection = geneticAlterationCollection;
    }

    /**
     * @param geneticAlteration
     *            The geneticAlteration to add.
     */
    public void addGeneticAlteration(GeneticAlteration geneticAlteration)
    {
        geneticAlterationCollection.add(geneticAlteration);
    }

    /**
     * @return Returns the geneticAlterationCollection.
     */
    public List<EnvironmentalFactor> getEnvironmentalFactorCollection()
    {
        return environmentalFactorCollection;
    }

    public List<EnvironmentalFactor> getEnvironmentalFactorCollectionSorted()
    {
        if (environmentalFactorCollection != null)
            return new ArrayList<EnvironmentalFactor>(new TreeSet<EnvironmentalFactor>(environmentalFactorCollection));
        return null;
    }

    /**
     * @param geneticAlterationCollection
     *            The geneticAlterationCollection to set.
     */
    public void setEnvironmentalFactorCollection(List<EnvironmentalFactor> environmentalFactorCollection)
    {
        this.environmentalFactorCollection = environmentalFactorCollection;
    }

    //TODO: this is not how we should do this and we should clean this up later
    public EnvironmentalFactor getEnvironmentalFactor()
    {
        if (environmentalFactorCollection.size() > 0)
            return (EnvironmentalFactor) environmentalFactorCollection.get(0);
        else
            return null;
    }

    /**
     * @param geneticAlteration
     *            The geneticAlteration to add.
     */
    public void addEnvironmentalFactor(EnvironmentalFactor environmentalFactor)
    {
        environmentalFactorCollection.add(environmentalFactor);
    }

    /**
     * @return Returns the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return Returns the geneId.
     */
    public String getGeneId()
    {
        return geneId;
    }

    /**
     * @param geneId
     *            The geneId to set.
     */
    public void setGeneId(String geneId)
    {
        this.geneId = geneId;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getGeneId() + " - " + this.getDescription();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        return true;
    }

}
