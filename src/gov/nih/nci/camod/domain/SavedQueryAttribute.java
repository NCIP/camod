/**
 * 
 * $Id: SavedQueryAttribute.java,v 1.1 2006-04-28 19:12:44 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.domain;

import java.io.Serializable;

public class SavedQueryAttribute extends BaseObject implements Serializable
{
    // Pointer to this object's parent  
    private SavedQuery parentQuery;
    
    // Name of the attribute is the fully qualified classname (subclass of criteria)
    private String attributeName;
    
    // Used to store the order of the attributes
    private String subAttributeName;
    
    // Value of the criteria
    private String attributeValue; 
    
    /**
     * 
     */
    private static final long serialVersionUID = 2581622057143043273L;
    
    /**
     * 
     *
     */
    public SavedQueryAttribute()
    {
    }    
    
    /**
     * Creates an object based on a QueryAttributeWrapper
     * 
     * @param wrapper
     * @param parent
     */        
    public SavedQueryAttribute(QueryAttributeWrapper wrapper, SavedQuery parent)
    {
        setAttributeName(wrapper.getCriteriaClassName());
        setAttributeValue(wrapper.getAttributeValue());
        setSubAttributeName(wrapper.getSubAttributeName());
        setParentQuery(parent);
    }
    
    public SavedQuery getParentQuery() {
        return parentQuery;
    }

    public void setParentQuery(SavedQuery parentQuery) {
        this.parentQuery = parentQuery;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

    public void setAttributeName(String attributeName)
    {
        this.attributeName = attributeName;
    }

    public String getAttributeValue()
    {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue)
    {
        this.attributeValue = attributeValue;
    }

    public String getSubAttributeName()
    {
        return subAttributeName;
    }

    public void setSubAttributeName(String subAttributeName)
    {
        this.subAttributeName = subAttributeName;
    }       
       
}
