/**
 * 
 * $Id: SavedQuery.java,v 1.3 2006-05-04 17:29:22 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/05/04 14:11:58  schroedn
 * Changes from Code review
 *
 * Revision 1.1  2006/04/28 19:12:44  schroedn
 * Defect #238, 261
 * Search Result Columns and Saving Queries domain objects and hibernate mapping files
 *
 *
 */

package gov.nih.nci.camod.domain;

import gov.nih.nci.camod.webapp.form.SearchData;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author schroedn
 */
public class SavedQuery extends BaseObject implements Serializable 
{
    private static final long serialVersionUID = 5804789721118396480L;
    private Person user;
    private String queryName;
    private String newResults;
    private String active;
    private long numberResults;
    private SearchData searchData;
    private Set<SavedQueryAttribute> savedQueryAttributes = new HashSet<SavedQueryAttribute>();
    
    // Timestamp of when the query was run
    private Date executeTime;    
    
    // How Long it took the query to run
    private long elapsedTime;    
    private Long isSaved;
    
    /**
     * @return Returns the isSaved setting.
     */
    public Long getIsSaved()
    {
        return isSaved;
    }

    /**
     * @param isSaved
     *     sets the IsSaved
     */ 
    public void setIsSaved(Long isSaved)
    {
        this.isSaved = isSaved;
    }

    /**
     * @return Returns the set of SavedQueryAttribute.
     */
    public Set<SavedQueryAttribute> getSavedQueryAttributes()
    {
        return savedQueryAttributes;
    }

    /**
     * @param inSavedQueryAttribute
     *     sets savedQueryAttribute
     */ 
    public void setSavedQueryAttributes(Set<SavedQueryAttribute> inSavedQueryAttribute)
    {
        this.savedQueryAttributes = inSavedQueryAttribute;
    }

    /**
     * @param inSavedQueryAttribute
     *     adds a SavedQueryAttribute to the set savedQueryAttributes
     */
    public void addSavedQueryAttribute(SavedQueryAttribute inSavedQueryAttribute)
    {
        savedQueryAttributes.add(inSavedQueryAttribute);
    }
    
    /**
     * @param inSavedQueryAttribute
     *      removes a SavedQueryAttribute from the set savedQueryAttributes
     */
    public void removeSavedQueryAttribute(SavedQueryAttribute inSavedQueryAttribute) {
        savedQueryAttributes.remove(inSavedQueryAttribute);
    }
    
    /**
     * @return Returns the query name.
     */
    public String getQueryName() {
    
        return queryName;
    }
    
    /**
     * @param name
     *     sets the query name.
     */ 
    public void setQueryName(String name) {
    
        this.queryName = name;
    }
             
    /**
     * @return Returns the active.
     */
    public String getActive()
    {
        return active;
    }

    /**
     * @param active
     *     sets the active
     */ 
    public void setActive(String active)
    {
        this.active = active;
    }

    /**
     * @return the new results value.
     */
    public String getNewResults()
    {
        return newResults;
    }

    /**
     * @param newResults
     *     sets the new results value.
     */ 
    public void setNewResults(String newResults)
    {
        this.newResults = newResults;
    }

    /**
     * @return Returns the user.
     */
    public Person getUser() {
    
        return user;
    }
    
    /**
     * @param user
     *     sets the user
     */ 
    public void setUser(Person user) {
    
        this.user = user;
    }

    /**
     * @return Returns the elapsedTime.
     */
    public long getElapsedTime()
    {
        return elapsedTime;
    }

    /**
     * @param elapsedTime
     *     sets the elapsedTime
     */ 
    public void setElapsedTime(long elapsedTime)
    {
        this.elapsedTime = elapsedTime;
    }

    /**
     * @return Returns the executeTime.
     */
    public Date getExecuteTime()
    {
        return executeTime;
    }

    /**
     * @param executeTime
     *     sets the executeTime
     */ 
    public void setExecuteTime(Date executeTime)
    {
        this.executeTime = executeTime;
    }

    /**
     * @return Returns the searchData.
     */
    public SearchData getSearchData()
    {
        return searchData;
    }

    /**
     * @param searchData
     *     sets the searchData
     */ 
    public void setSearchData(SearchData searchData)
    {
        this.searchData = searchData;
    }

    /**
     * @return Returns the numberResults.
     */
    public long getNumberResults()
    {
        return numberResults;
    }

    /**
     * @param numberResults
     *     sets the numberResults
     */ 
    public void setNumberResults(long numberResults)
    {
        this.numberResults = numberResults;
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getQueryName() + " - " + this.numberResults;
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
