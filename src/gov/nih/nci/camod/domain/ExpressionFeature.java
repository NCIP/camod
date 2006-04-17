/*
 * $Id: ExpressionFeature.java,v 1.7 2006-04-17 19:13:46 pandyas Exp $
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
public class ExpressionFeature extends BaseObject implements Comparable, Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259435453799404851L;

    private Organ organ;
    private ExpressionLevelDesc expressionLevelDesc;

    /**
     * @return Returns the expressionLevelDesc.
     */
    public ExpressionLevelDesc getExpressionLevelDesc()
    {
        return expressionLevelDesc;
    }

    /**
     * @param expressionLevelDesc
     *            The expressionLevelDesc to set.
     */
    public void setExpressionLevelDesc(ExpressionLevelDesc expressionLevelDesc)
    {
        this.expressionLevelDesc = expressionLevelDesc;
    }

    /**
     * @return Returns the organ.
     */
    public Organ getOrgan()
    {
        return organ;
    }

    /**
     * @param organ
     *            The organ to set.
     */
    public void setOrgan(Organ organ)
    {
        this.organ = organ;
    }
  
    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getOrgan();
        return result;
    }

    public boolean equals(Object o)
    {
        if (!super.equals(o))
            return false;
        if (!(this.getClass().isInstance(o)))
            return false;
        final ExpressionFeature obj = (ExpressionFeature) o;
        if (HashCodeUtil.notEqual(this.getOrgan(), obj.getOrgan()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash(result, this.getOrgan());
        return result + super.hashCode();
    }

    public int compareTo(Object o)
    {
        if ((o instanceof ExpressionFeature) && (this.getOrgan() != null) && (((ExpressionFeature) o).getOrgan() != null))
        {
            int result = this.getOrgan().compareTo(((ExpressionFeature) o).getOrgan());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

}
