/**
 * 
 * $Id: CustomizeSearchResultsData.java,v 1.1 2006-04-28 19:30:06 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.webapp.form;

public interface CustomizeSearchResultsData
{
    public String[] getColumnsToDisplay();
    
    public void setColumnsToDisplay(String[] a);

    public String[] getSelectedColumnsToDisplay();

    public void setSelectedColumnsToDisplay(String[] a);
    
    public String getItemsPerPage();
    
    public void setItemsPerPage(String a);
}
