/**
 * 
 * $Id: SaveQueryForm.java,v 1.1 2006-04-28 19:30:06 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class SaveQueryForm extends BaseForm implements Serializable {
    
    private static final long serialVersionUID = 3257165453799404851L;

    public SaveQueryForm() {}
    
    protected String queryName;
    protected String saveAsNew = "no";
    
    public String getQueryName()
    {
        return queryName;
    }

    public void setQueryName(String queryName)
    {
        this.queryName = queryName;
    }

    public String getSaveAsNew()
    {
        return saveAsNew;
    }

    public void setSaveAsNew(String saveAsNew)
    {
        this.saveAsNew = saveAsNew;
    }


}
