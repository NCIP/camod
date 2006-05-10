/**
 * 
 * $Id: ResultSettingsColumns.java,v 1.3 2006-05-10 13:35:21 schroedn Exp $
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

/**
 * @author schroedn
 */
public class ResultSettingsColumns  extends BaseObject implements Serializable
{
    private static final long serialVersionUID = 5804789721118396480L;

    // Pointer to this object's parent  
    private ResultSettings parentResultSettings;
    private String columnName;
    
    /**
     * @return Returns column Name.
     */    
    public String getColumnName()
    {
        return columnName;
    }
    
    /**
     * @param columnName
     *         Sets the column Name.
     */
    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }
    
    /**
     * @return Returns the parent ResultSettings
     */
    public ResultSettings getParentResultSettings()
    {
        return parentResultSettings;
    }
    
    /**
     * @param parentResultSettings
     *      Parent ResultSettings to set
     */
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
