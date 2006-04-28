/**
 * 
 * $Id: ResultSettings.java,v 1.1 2006-04-28 19:12:44 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ResultSettings extends BaseObject implements Serializable 
{
    private static final long serialVersionUID = 5804789721118396480L;
    private String columnsToDisplay;
    private int itemsPerPage;
    private Person user;
    private Set<ResultSettingsColumns> resultSettingsColumns = new HashSet<ResultSettingsColumns>();
    

    public Set<ResultSettingsColumns> getResultSettingsColumns()
    {
        return resultSettingsColumns;
    }

    public void setResultSettingsColumns(Set<ResultSettingsColumns> sqa)
    {
        this.resultSettingsColumns = sqa;
    }
    
    public String getColumnsToDisplay()
    {
        return columnsToDisplay;
    }
    public void setColumnsToDisplay(String columnsToDisplay)
    {
        this.columnsToDisplay = columnsToDisplay;
    }
    
    public int getItemsPerPage()
    {
        return itemsPerPage;
    }
    public void setItemsPerPage(int itemsPerPage)
    {
        this.itemsPerPage = itemsPerPage;
    }
    
    public Person getUser()
    {
        return user;
    }
    public void setUser(Person user)
    {
        this.user = user;
    }         
}
