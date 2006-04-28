/**
 * 
 * $Id: CustomizeSearchResultsForm.java,v 1.1 2006-04-28 19:30:06 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class CustomizeSearchResultsForm extends BaseForm implements Serializable, CustomizeSearchResultsData {
    
    private static final long serialVersionUID = 3257045453799404851L;
    
    protected String[] columnsToDisplay;
    protected String[] selectedColumnsToDisplay = { "Model Descriptor" };
    protected String itemsPerPage;
   
    public String[] getColumnsToDisplay() {
        return columnsToDisplay;
    }

    public void setColumnsToDisplay( String[] a ) {
        this.columnsToDisplay = a;
    }    
 
    public String[] getSelectedColumnsToDisplay() {
        return selectedColumnsToDisplay;
    }

    public void setSelectedColumnsToDisplay( String[] a ) {
        this.selectedColumnsToDisplay = a;
    }        
    
    public String getItemsPerPage() {
        return itemsPerPage;
    }
    
    public void setItemsPerPage( String a ) {
        this.itemsPerPage = a;
    }
}
