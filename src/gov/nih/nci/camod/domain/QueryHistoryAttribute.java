/**
 * 
 * $Id: QueryHistoryAttribute.java,v 1.1 2006-04-28 19:12:44 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.domain;

import java.io.Serializable;

/**
 * Domain class to represent the criteria stored for a query history record
 * 
 * @author dietrichj
 *
 */
public class QueryHistoryAttribute extends BaseObject implements Serializable {

    // Pointer to this object's parent
    private QueryHistory parentQuery;
    
    /**
     * 
     */
    private static final long serialVersionUID = 2581622057143043273L;
    

    /**
     * 
     *
     */
    public QueryHistoryAttribute()
     {
        
     }
    
    /**
     * Creates an object based on a QueryAttributeWrapper
     * 
     * @param wrapper
     * @param parent
     */    
    public QueryHistoryAttribute(QueryAttributeWrapper wrapper, QueryHistory parent)
    {
        // Transfer the data from the wrapper to this object.
//        setAttributeName(wrapper.getCriteriaClassName());
//        setAttributeValue(wrapper.getAttributeValue());
//        setSubAttributeName(wrapper.getSubAttributeName());
//        setParentQuery(parent);
    }
    
    /**
     * 
     * @return
     */
    public QueryHistory getParentQuery() {
        return parentQuery;
    }

    /**
     * 
     * @param parentQuery
     */
    public void setParentQuery(QueryHistory parentQuery) {    
        this.parentQuery = parentQuery;
    }       
    
}
