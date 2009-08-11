/**
 * 
 * $Id: ResultSettings.java,v 1.3 2006-05-10 14:13:51 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/05/04 17:29:22  pandyas
 * Added toString() and equals() to domain class  - encouraged by Hibernate
 *
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

/**
 * @author schroedn
 */
public class ResultSettings extends BaseObject implements Serializable 
{
    private static final long serialVersionUID = 5804789721118396480L;
    private String columnsToDisplay;
    private int itemsPerPage;
    private Person user;
    private Set<ResultSettingsColumns> resultSettingsColumns = new HashSet<ResultSettingsColumns>();
    
    /**
     * @return Returns the set of resultSettingsColumns.
     */
    public Set<ResultSettingsColumns> getResultSettingsColumns()
    {
        return resultSettingsColumns;
    }
    
    /**
     * @param inResultSettingsColumns
     *      Sets the resultSettingsColumns.
     */
    public void setResultSettingsColumns(Set<ResultSettingsColumns> inResultSettingsColumns)
    {
        this.resultSettingsColumns = inResultSettingsColumns;
    }
    
    /**
     * @return Returns the columns to display.
     */
    public String getColumnsToDisplay()
    {
        return columnsToDisplay;
    }
    
    /**
     * @param inColumnsToDisplay
     *      Sets the columns to display.
     */
    public void setColumnsToDisplay(String inColumnsToDisplay)
    {
        this.columnsToDisplay = inColumnsToDisplay;
    }
    
    /**
     * @return Returns the items per page to display.
     */
    public int getItemsPerPage()
    {
        return itemsPerPage;
    }
    
    /**
     * @param inItemsPerPage
     *      sets the itmes per page to display.
     */
    public void setItemsPerPage(int inItemsPerPage)
    {
        this.itemsPerPage = inItemsPerPage;
    }
    
    /**
     * @return Returns the user.
     */
    public Person getUser()
    {
        return user;
    }
    
    /**
     * @param user
     *      Sets the user.
     */
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
