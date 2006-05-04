/*
 * $Id: InducedMutation.java,v 1.15 2006-05-04 19:28:24 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.14  2006/04/19 15:05:46  georgeda
 * remove old code
 *
 * Revision 1.13  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;


public class InducedMutation extends EngineeredGene
{
    private static final long serialVersionUID = 3259235453799404851L;

    private String geneId;
    private String description;
    private GeneticAlteration geneticAlteration;
    private EnvironmentalFactor environmentalFactor;

    /**
     * @return Returns the geneticAlteration.
     */
    public GeneticAlteration getGeneticAlteration() {
        return geneticAlteration;
    }

    /**
     * @param geneticAlteration
     *            The geneticAlteration to set.
     */
    public void setGeneticAlteration(GeneticAlteration geneticAlteration) {
        this.geneticAlteration = geneticAlteration;
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
