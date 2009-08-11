/**
 * $Id: AnimalDistributor.java,v 1.9 2006-10-17 16:14:36 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2006/04/19 17:37:37  pandyas
 * Removed text
 *
 * Revision 1.7  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 * Revision 1.6  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.5  2005/11/07 17:46:07  pandyas
 * cleaned up item from problem tab, added javadocs
 *
 *
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;
import gov.nih.nci.camod.util.HashCodeUtil;


/**
 * @author rajputs
 */
public class AnimalDistributor extends BaseObject implements Serializable, Comparable
{

    private static final long serialVersionUID = 4259685453799404851L;

    private String name;
    

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
        final AnimalDistributor obj = (AnimalDistributor) o;
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
        if ((o instanceof AnimalDistributor) && (this.getName() != null) && (((AnimalDistributor) o).getName() != null))
        {
            int result = this.getName().compareTo(((AnimalDistributor) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }


}
