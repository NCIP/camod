/**
 * 
 * $Id: SaveQueryForm.java,v 1.2 2006-05-10 14:25:10 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/04/28 19:30:06  schroedn
 * Defect # 251, 238
 * Form Data
 *
 *
 */

package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

/**
 * 
 * @author schroedn
 *
 */
public class SaveQueryForm extends BaseForm implements Serializable {
    
    private static final long serialVersionUID = 3257165453799404851L;
    protected String queryName;
    protected String saveAsNew = "no";
    
    public String getQueryName()
    {
        return queryName;
    }

    public void setQueryName(String inQueryName)
    {
        this.queryName = inQueryName;
    }

    public String getSaveAsNew()
    {
        return saveAsNew;
    }

    public void setSaveAsNew(String inSaveAsNew)
    {
        this.saveAsNew = inSaveAsNew;
    }


}
