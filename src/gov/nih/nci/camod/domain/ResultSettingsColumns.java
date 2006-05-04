/**
 * 
 * $Id: ResultSettingsColumns.java,v 1.2 2006-05-04 17:29:22 pandyas Exp $
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

public class ResultSettingsColumns extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 5804789721118396480L;

    // Pointer to this object's parent  
    private ResultSettings parentResultSettings;
    private String columnName;

    public String getColumnName()
    {
        return columnName;
    }

    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }

    public ResultSettings getParentResultSettings()
    {
        return parentResultSettings;
    }

    public void setParentResultSettings(ResultSettings parentResultSettings)
    {
        this.parentResultSettings = parentResultSettings;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String result = super.toString() + " - ";
        result += this.getColumnName();
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
