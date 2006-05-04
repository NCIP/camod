/**
 * 
 * $Id: SavedQueryAttribute.java,v 1.2 2006-05-04 14:12:13 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
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
    
    // Name of the attribute is the fully qualified classname (subclass of criteria)
    private String attributeName;
    
    // Value of the criteria
    private String attributeValue; 
    
    private static final long serialVersionUID = 2581622057143043273L;


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
}
