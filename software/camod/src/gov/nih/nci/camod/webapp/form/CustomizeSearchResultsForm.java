/**
 * 
 * $Id: CustomizeSearchResultsForm.java,v 1.2 2006-05-10 14:25:10 schroedn Exp $
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
public class CustomizeSearchResultsForm extends BaseForm implements Serializable, CustomizeSearchResultsData {
    
    private static final long serialVersionUID = 3257045453799404851L;    
    protected String[] columnsToDisplay;
    protected String[] selectedColumnsToDisplay = { "Model Descriptor" };
    protected String itemsPerPage;
   
    public String[] getColumnsToDisplay() {
        return columnsToDisplay;
    }

    public void setColumnsToDisplay( String[] inColumnsToDisplay ) {
        this.columnsToDisplay = inColumnsToDisplay;
    }    
 
    public String[] getSelectedColumnsToDisplay() {
        return selectedColumnsToDisplay;
    }

    public void setSelectedColumnsToDisplay( String[] inSelectedColumnsToDisplay ) {
        this.selectedColumnsToDisplay = inSelectedColumnsToDisplay;
    }        
    
    public String getItemsPerPage() {
        return itemsPerPage;
    }
    
    public void setItemsPerPage( String inItemsPerPage ) {
        this.itemsPerPage = inItemsPerPage;
    }
}
