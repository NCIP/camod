/**
 * 
 * $Id: QueryAttributeWrapper.java,v 1.1 2006-04-28 19:12:44 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.domain;

/**
 * Lightweight class used by Criteria subclasses to set 
 * values for storage in the database
 *
 */
public class QueryAttributeWrapper
{
    // Used to store the order in which attributes are stored
    private String subAttributeName;
    
    // The value of the attribute
    private String attributeValue;
    
    // Fully qualified class name of the criteria subclass to store
    private String criteriaClassName;

    /**
     * 
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
    
    /**
     * 
     * @return
     */
    public String getCriteriaClassName() {
        return criteriaClassName;
    }
    
    /**
     * 
     * @param criteriaClassName
     */
    public void setCriteriaClassName(String criteriaClassName) {
        this.criteriaClassName = criteriaClassName;
    }     
}
