/*
 * $Id: GenotypeSummary.java,v 1.7 2006-04-19 17:37:37 pandyas Exp $
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
public class GenotypeSummary extends BaseObject implements Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259295453799404851L;

    private String summary;
    private String genotype;
    private Nomenclature nomenclature;

    /**
     * @return Returns the genotype.
     */
    public String getGenotype()
    {
        return genotype;
    }

    /**
     * @param genotype
     *            The genotype to set.
     */
    public void setGenotype(String genotype)
    {
        this.genotype = genotype;
    }

    /**
     * @return Returns the nomenclature.
     */
    public Nomenclature getNomenclature()
    {
        return nomenclature;
    }

    /**
     * @param nomenclature
     *            The nomenclature to set.
     */
    public void setNomenclature(Nomenclature nomenclature)
    {
        this.nomenclature = nomenclature;
    }

    /**
     * @return Returns the summary.
     */
    public String getSummary()
    {
        return summary;
    }

    /**
     * @param summary
     *            The summary to set.
     */
    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getSummary() + " - " + this.getGenotype();
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
