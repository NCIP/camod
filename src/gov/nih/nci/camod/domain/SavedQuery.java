/**
 * 
 * $Id: SavedQuery.java,v 1.1 2006-04-28 19:12:44 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.webapp.form.SearchData;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SavedQuery extends BaseObject implements Serializable 
{
    private static final long serialVersionUID = 5804789721118396480L;
    private Person user;
    private String queryName;
    private String newResults;
    private String active;
    private long numberResults;
    
    //private Long id;    
    private Long userId;
    //private SavedQueryLastExec lastExecuteDate;  
    
    private SearchData searchData;
    private Set<SavedQueryAttribute> savedQueryAttributes = new HashSet<SavedQueryAttribute>();

    // Timestamp of when the query was run
    private Date executeTime;
    
    // How Long it took the query to run
    private long elapsedTime;    
    private Long isSaved;
    
    public Long getIsSaved()
    {
        return isSaved;
    }

    public void setIsSaved(Long isSaved)
    {
        this.isSaved = isSaved;
    }

    public Set<SavedQueryAttribute> getSavedQueryAttributes()
    {
        return savedQueryAttributes;
    }

    public Set<SavedQueryAttribute> getSavedQueryAttributesSorted()
    {
        if (savedQueryAttributes != null)
            return new HashSet<SavedQueryAttribute>(new HashSet<SavedQueryAttribute>(savedQueryAttributes));
        return null;
    }

    public void setSavedQueryAttributes(Set<SavedQueryAttribute> sqa)
    {
        this.savedQueryAttributes = sqa;
    }

    public void addSavedQueryAttribute(SavedQueryAttribute sqa)
    {
        savedQueryAttributes.add(sqa);
    }
    
    public void addQueryAttribute(QueryAttributeWrapper attr)
    {
        SavedQueryAttribute qha = new SavedQueryAttribute(attr, this);
        savedQueryAttributes.add(qha);
    }

    public void removeSavedQueryAttribute(SavedQueryAttribute attr) {
        savedQueryAttributes.remove(attr);
    }
    
//    public Long getId() {
//    
//        return id;
//    }
//    
//    public void setId(Long id) {
//    
//        this.id = id;
//    }
    
    public String getQueryName() {
    
        return queryName;
    }
    
    public void setQueryName(String name) {
    
        this.queryName = name;
    }
             
    
    public String getActive()
    {
        return active;
    }

    public void setActive(String active)
    {
        this.active = active;
    }

    public String getNewResults()
    {
        return newResults;
    }

    public void setNewResults(String newResults)
    {
        this.newResults = newResults;
    }

    public Person getUser() {
    
        return user;
    }
    
    public void setUser(Person user) {
    
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getElapsedTime()
    {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime)
    {
        this.elapsedTime = elapsedTime;
    }

    public Date getExecuteTime()
    {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime)
    {
        this.executeTime = executeTime;
    }

    public SearchData getSearchData()
    {
        return searchData;
    }

    public void setSearchData(SearchData searchData)
    {
        this.searchData = searchData;
    }

    public long getNumberResults()
    {
        return numberResults;
    }

    public void setNumberResults(long numberResults)
    {
        this.numberResults = numberResults;
    }    
}
