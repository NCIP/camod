/*
 * $Id: Disease.java,v 1.14 2006-04-17 19:13:46 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.util.*;
import java.io.Serializable;

/**
 * @author rajputs TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class Disease extends BaseObject implements Comparable, Serializable, Duplicatable
{
    private static final long serialVersionUID = 3259515453799404851L;

    private String name;
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
     * @return Returns the conceptCode.
     */
    public String getConceptCode()
    {
        return conceptCode;
    }

    /**
     * @return Returns the EVS Preferred displayName
     * 		if the conceptCode = 000000, then return the name
     */
    public String getEVSPreferredDescription()
    {
        String thePreferedDesc = null;
        if ("000000".equals(conceptCode) || "C000000".equals(conceptCode))
        {
            thePreferedDesc = name;
        }
        else
        {
            thePreferedDesc = EvsTreeUtil.getEVSPreferedDescription(conceptCode);
        }
        return thePreferedDesc;
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
        final Disease obj = (Disease) o;
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
        if ((o instanceof Disease) && (this.getName() != null) && (((Disease) o).getName() != null))
        {
            int result = this.getName().compareTo(((Disease) o).getName());
            if (result != 0)
            {
                return result;
            }
        }

        return super.compareTo(o);
    }

}
