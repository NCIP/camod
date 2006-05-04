/**
 * 
 * $Id: ResultSettings.java,v 1.2 2006-05-04 17:29:22 pandyas Exp $
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
    
    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getColumnsToDisplay() + " - " + this.getItemsPerPage();
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
