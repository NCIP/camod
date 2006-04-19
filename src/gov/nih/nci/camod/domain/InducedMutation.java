/*
 * $Id: InducedMutation.java,v 1.14 2006-04-19 15:05:46 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.13  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.util.*;

public class InducedMutation extends EngineeredGene
{
    private static final long serialVersionUID = 3259235453799404851L;

    private String geneId;
    private String description;
    private Set<GeneticAlteration> geneticAlterationCollection = new TreeSet<GeneticAlteration>();
    private EnvironmentalFactor environmentalFactor;

    /**
     * @return Returns the geneticAlterationCollection.
     */
    public Set<GeneticAlteration> getGeneticAlterationCollection()
    {
        return geneticAlterationCollection;
    }

    /**
     * @param geneticAlterationCollection
     *            The geneticAlterationCollection to set.
     */
    public void setGeneticAlterationCollection(Set<GeneticAlteration> geneticAlterationCollection)
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
     * @return Returns the environmentalFactor.
     */
    public EnvironmentalFactor getEnvironmentalFactor() {
        return environmentalFactor;
    }

    /**
     * @param environmentalFactor
     *            The environmentalFactor to set.
     */
    public void setEnvironmentalFactor(EnvironmentalFactor environmentalFactor) {
        this.environmentalFactor = environmentalFactor;
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
