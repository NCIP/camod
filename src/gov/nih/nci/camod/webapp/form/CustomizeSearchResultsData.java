/**
 * 
 * $Id: CustomizeSearchResultsData.java,v 1.2 2006-05-10 14:25:10 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/04/28 19:30:06  schroedn
 * Defect # 251, 238
 * Form Data
 *
 *
 */

package gov.nih.nci.camod.webapp.form;

/**
 * 
 * @author schroedn
 *
 */
public interface CustomizeSearchResultsData
{
    public String[] getColumnsToDisplay();
    
    public void setColumnsToDisplay(String[] inColumnsToDisplay);

    public String[] getSelectedColumnsToDisplay();

    public void setSelectedColumnsToDisplay(String[] inSelectedColumnsToDisplay);
    
    public String getItemsPerPage();
    
    public void setItemsPerPage(String inItemsPerPage);
}
