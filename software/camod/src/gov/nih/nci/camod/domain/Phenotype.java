/*
 * $Id: Phenotype.java,v 1.7 2006-04-19 17:37:37 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;

/**
 * @author rajputs
 */
public class Phenotype extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3258775453799404851L;

    private String breedingNotes;
    private String description;
    private SexDistribution sexDistribution;

    /**
     * @return Returns the sexDistribution.
     */
    public SexDistribution getSexDistribution()
    {
        return sexDistribution;
    }

    /**
     * @param sexDistribution
     *            The sexDistribution to set.
     */
    public void setSexDistribution(SexDistribution sexDistribution)
    {
        this.sexDistribution = sexDistribution;
    }

    /**
     * @return Returns the breedingNotes.
     */
    public String getBreedingNotes()
    {
        return breedingNotes;
    }

    /**
     * @param breedingNotes
     *            The breedingNotes to set.
     */
    public void setBreedingNotes(String breedingNotes)
    {
        this.breedingNotes = breedingNotes;
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
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getDescription();
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
