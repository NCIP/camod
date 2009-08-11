/**
 * 
 * $Id: QueryAttribute.java,v 1.1 2006-04-28 19:12:44 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.domain;

/**
 * Abstract superclass of QueryHistoryAttribute and SavedQueryAttribute.
 * 
 * This class was created because much of the behavior of those subclasses is
 * the same.
 *
 */
public abstract class QueryAttribute 
{
    // Primary key
    private long id;
    
    // Name of the attribute is the fully qualified classname (subclass of criteria)
    private String attributeName;
    
    // Used to store the order of the attributes
    private String subAttributeName;
    
    // Value of the criteria
    private String attributeValue; 

    /**
     * 
     * @return
     */    
    public String getAttributeName() {
        return attributeName;
    }
    
    /**
     * 
     * @param attributeName
     */
    public void setAttributeName(String attributeName) {
    
        this.attributeName = attributeName;
    }
    
    /**
     * 
     * @return
     */
    public String getAttributeValue() {
    
        return attributeValue;
    }
    
    /**
     * 
     * @param attributeValue
     */
    public void setAttributeValue(String attributeValue) {
    
        this.attributeValue = attributeValue;
    }
    
    /**
     * 
     */
    public long getId() {
        return id;
    }
    
    /**
     * 
     * @param id
     */
    public void setId(long id) {
    
        this.id = id;
    }
    
    /**
     * 
     * @return
     */
    public String getSubAttributeName() {
    
        return subAttributeName;
    }
    
    /**
     * 
     * @param subAttributeName
     */
    public void setSubAttributeName(String subAttributeName) {
    
        this.subAttributeName = subAttributeName;
    }
}
