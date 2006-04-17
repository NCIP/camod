/*
 * $Id: RegulatoryElement.java,v 1.7 2006-04-17 19:13:46 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;
import gov.nih.nci.camod.util.HashCodeUtil;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RegulatoryElement extends BaseObject implements Comparable, Serializable, Duplicatable
{
    private static final long serialVersionUID = 3258725453799404851L;

    private String name;
    private RegulatoryElementType regulatoryElementType;
    private Species species;

    /**
     * @return Returns the species.
     */
    public Species getSpecies()
    {
        return species;
    }

    /**
     * @param species
     *            The species to set.
     */
    public void setSpecies(Species species)
    {
        this.species = species;
    }


    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the regulatoryElementType.
     */
    public RegulatoryElementType getRegulatoryElementType()
    {
        return regulatoryElementType;
    }

    /**
     * @param regulatoryElementType
     *            The regulatoryElementType to set.
     */
    public void setRegulatoryElementType(RegulatoryElementType regulatoryElementType)
    {
        this.regulatoryElementType = regulatoryElementType;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getName();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final RegulatoryElement obj = (RegulatoryElement) o;
        if (HashCodeUtil.notEqual(this.getName(), obj.getName()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getName());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof RegulatoryElement) && (this.getName() != null) && (((RegulatoryElement) o).getName() != null))
        {
            int result = this.getName().compareTo(((RegulatoryElement) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }
}
