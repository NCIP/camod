/**
 * 
 * $Id: ResultSettingsColumns.java,v 1.1 2006-04-28 19:12:44 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.domain;

import java.io.Serializable;

public class ResultSettingsColumns  extends BaseObject implements Serializable
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
}
