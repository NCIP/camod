/**
 * 
 * $Id: QueryHistory.java,v 1.1 2006-04-28 19:12:44 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

/**
 * Domain class to represent a query history record 
 *
 */
public class QueryHistory implements Serializable {


    private static final long serialVersionUID = 120909944929218045L;
    
    // primayr key
    private long id;
    
    // The user that ran the query
    private Party user;
    
    // Timestamp of when the query was run
    private Date executeTime;
    
    // The saved query that was run (if any)
    private SavedQuery savedQuery;
    
    // How long it took the query to run
    private Long elapsedTime;
    
    // User ID of the user associated with this query history record
    private long userId;
    
    // The criteria of the query
   // private Set<QueryHistoryAttribute> queryHistoryAttributes;
    private List<QueryHistoryAttribute> queryHistoryAttributes = new ArrayList<QueryHistoryAttribute>();   
    
    public List<QueryHistoryAttribute> getQueryHistoryAttributes()
    {
        return queryHistoryAttributes;
    }

    public List<QueryHistoryAttribute> getQueryHistoryAttributesList()
    {
        if (queryHistoryAttributes != null)
            return new ArrayList<QueryHistoryAttribute>(new TreeSet<QueryHistoryAttribute>(queryHistoryAttributes));
        return null;
    }

    public void setQueryHistoryAttributes(List<QueryHistoryAttribute> qha)
    {
        this.queryHistoryAttributes = qha;
    }

    public void addQueryHistoryAttributes(QueryHistoryAttribute qha)
    {
        queryHistoryAttributes.add(qha);
    }    
    
//    /**
//     * 
//     *
//     */
//    public QueryHistory() {
//        queryHistoryAttributes = new HashSet<QueryHistoryAttribute>();
//    }

    /**
     * 
     * @param attr
     */
    public void removeQueryHistoryAttribute(QueryHistoryAttribute attr) {
        queryHistoryAttributes.remove(attr);
    }
    
    /**
     * 
     * @return
     */
    public Date getExecuteTime() {
    
        return executeTime;
    }
    
    /**
     * 
     * @param executeTime
     */
    public void setExecuteTime(Date executeTime) {
    
        this.executeTime = executeTime;
    }
    
    /**
     * 
     * @return
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
    
//    /**
//     * 
//     * @return
//     */
////    public Set<QueryHistoryAttribute> getQueryHistoryAttributes() {
////        return queryHistoryAttributes;
////    }
//    
//    /**
//     * 
//     * @param queryAttributes
//     */
//    public void setQueryHistoryAttributes(Set<QueryHistoryAttribute> queryAttributes) {
//        this.queryHistoryAttributes = queryAttributes;
//    }
    
    /**
     * 
     * @return
     */
    public Party getUser() {
        return user;
    }
    
    /**
     * 
     * @param usr
     */
    public void setUser(Party usr) {
        this.user = usr;
    }

    /**
     * 
     * @return
     */
    public Long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * 
     * @param elapsedTime
     */
    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * 
     * @return
     */
    public SavedQuery getSavedQuery() {
        return savedQuery;
    }

    /**
     * 
     * @param savedQuery
     */
    public void setSavedQuery(SavedQuery savedQuery) {
        this.savedQuery = savedQuery;
    }
    
    /**
     * 
     * @param attr
     */
    public void addQueryAttribute(QueryAttributeWrapper attr)
    {
        QueryHistoryAttribute qha = new QueryHistoryAttribute(attr, this);
        queryHistoryAttributes.add(qha);
    }

    /**
     * 
     * @return
     */
    public long getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }
     
    
}
