package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.camod.util.HashCodeUtil;

import java.io.Serializable;

public class StainingMethod extends BaseObject implements Comparable, Serializable
{
    private static final long serialVersionUID = 3258615453799404851L;

    private String name;
    private String nameUnctrlVocab;
    private String conceptCode;

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
     * @return Returns the nameUnctrlVocab.
     */
    public String getNameUnctrlVocab()
    {
        return nameUnctrlVocab;
    }

    /**
     * @param nameUnctrlVocab
     *            The nameUnctrlVocab to set.
     */
    public void setNameUnctrlVocab(String nameUnctrlVocab)
    {
        this.nameUnctrlVocab = nameUnctrlVocab;
    }

    /**
     * @return Returns the conceptCode.
     */
    public String getConceptCode()
    {
        return conceptCode;
    }

    /**
     * @param conceptCode
     *            The conceptCode to set.
     */
    public void setConceptCode(String conceptCode)
    {
        this.conceptCode = conceptCode;
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
        final StainingMethod obj = (StainingMethod) o;
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
        // compare by evs comcept code
        if ((o instanceof StainingMethod) && (this.conceptCode != null) && (((StainingMethod) o).conceptCode != null))
        {
            int result = this.conceptCode.compareTo(((StainingMethod) o).conceptCode);
            if (result != 0)
            {
                return result;
            }
        }
        else if ((o instanceof StainingMethod) && (this.getName() != null) && (((StainingMethod) o).getName() != null))
        {
            int result = this.getName().compareTo(((StainingMethod) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }
}
