/*
 * $Id: GeneFunction.java,v 1.7 2006-04-17 19:13:46 pandyas Exp $
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
public class GeneFunction extends BaseObject implements Serializable, Duplicatable, Comparable
{
    private static final long serialVersionUID = 3259355453799404851L;

    private String function;
    private EngineeredGene engineeredGene;

    /**
     * @return Returns the engineeredGene.
     */
    public EngineeredGene getEngineeredGene()
    {
        return engineeredGene;
    }

    /**
     * @param engineeredGene
     *            The engineeredGene to set.
     */
    public void setEngineeredGene(EngineeredGene engineeredGene)
    {
        this.engineeredGene = engineeredGene;
    }

    /**
     * @return Returns the function.
     */
    public String getFunction()
    {
        return function;
    }

    /**
     * @param function
     *            The function to set.
     */
    public void setFunction(String function)
    {
        this.function = function;
    }


    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getFunction();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final GeneFunction obj = (GeneFunction) o;
        if (HashCodeUtil.notEqual(this.getFunction(), obj.getFunction()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getFunction());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof GeneFunction) && (this.getFunction() != null) && (((GeneFunction) o).getFunction() != null))
        {
            int result = this.getFunction().compareTo(((GeneFunction) o).getFunction());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

}
