/**
 * 
 * $Id: Query.java,v 1.1 2006-04-28 19:12:44 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.webapp.form.SearchData;

import java.io.Serializable;
import java.util.Date;

public class Query implements Serializable {

    private static final long serialVersionUID = 5804789721118396480L;
    
    private String userID;
    private Date executeTime;    
    private SearchData searchData;
    
    // The number of milliseconds it took to run this query
    private long elapsedTimeInMillis;

    // Primary key of the saved query that was run for this query
    private long savedQueryId = 0;
    
    // Primay key of the query history record that was created as a result
    // of running this query
    private long queryHistoryId = 0;
    
    private String queryName;    
        
    
    public Date getExecuteTime()
    {
        return executeTime;
    }
    public void setExecuteTime(Date executeTime)
    {
        this.executeTime = executeTime;
    }
    public String getUserID()
    {
        if (userID != null) 
            return userID;
        else 
            return null;
    }
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    public long getElapsedTimeInMillis()
    {
        return elapsedTimeInMillis;
    }
    public void setElapsedTimeInMillis(long elapsedTimeInMillis)
    {
        this.elapsedTimeInMillis = elapsedTimeInMillis;
    }
    public long getQueryHistoryId()
    {
        return queryHistoryId;
    }
    public void setQueryHistoryId(long queryHistoryId)
    {
        this.queryHistoryId = queryHistoryId;
    }
    public String getQueryName()
    {
        return queryName;
    }
    public void setQueryName(String queryName)
    {
        this.queryName = queryName;
    }
    public long getSavedQueryId()
    {
        return savedQueryId;
    }
    public void setSavedQueryId(long savedQueryId)
    {
        this.savedQueryId = savedQueryId;
    }
    public SearchData getSearchData()
    {
        return searchData;
    }
    public void setSearchData(SearchData searchData)
    {
        this.searchData = searchData;
    }    
}
