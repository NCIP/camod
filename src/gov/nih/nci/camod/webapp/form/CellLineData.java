/**
 * @author pandyas
 * 
 * $Id: CellLineData.java,v 1.2 2005-10-10 19:56:28 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/09/30 18:50:18  pandyas
 * added for cell line
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

/**
 * Cell Line interface for CI screen
 */
public interface CellLineData {

	public String getCellLineName();
	public void setCellLineName(String cellLineName);
	public String getExperiment();
	public void setExperiment(String experiment);
	public String getResults();
	public void setResults(String results);
	public String getComments();
	public void setComments(String comments);
	public String getOrgan();
	public void setOrgan( String organ );
	public String getOrganTissueName();
	public void setOrganTissueName( String organTissueName );
	public String getOrganTissueCode();
	public void setOrganTissueCode( String organTissueCode );	
	
}
