/**
 * 
 * $Id: SavedQueryAttribute.java,v 1.3 2006-05-04 17:29:22 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/05/04 14:12:13  schroedn
 * Changes from code review
 *
 * Revision 1.1  2006/04/28 19:12:44  schroedn
 * Defect #238, 261
 * Search Result Columns and Saving Queries domain objects and hibernate mapping files
 *
 *
 */

package gov.nih.nci.camod.domain;

import java.io.Serializable;

/**
 * @author schroedn
 */
public class SavedQueryAttribute extends BaseObject implements Serializable
{

    private static final long serialVersionUID = 2581622057143043273L;

    // Name of the attribute is the fully qualified classname (subclass of criteria)
    private String attributeName;

    // Value of the criteria
    private String attributeValue;


    /**
     * @return Returns the attributeName setting.
     */
    public String getAttributeName()
    {
        return attributeName;
    }

    /**
     * @param attributeName
     *     sets the attributeName
     */
    public void setAttributeName(String attributeName)
    {
        this.attributeName = attributeName;
    }

    /**
     * @return Returns the attributeValue setting.
     */
    public String getAttributeValue()
    {
        return attributeValue;
    }

    /**
     * @param attributeValue
     *     sets the attributeValue
     */
    public void setAttributeValue(String attributeValue)
    {
        this.attributeValue = attributeValue;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getAttributeName() + " - " + this.getAttributeValue();
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
