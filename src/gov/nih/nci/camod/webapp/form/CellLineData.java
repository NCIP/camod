/**
 * @author pandyas
 * 
 * $Id: CellLineData.java,v 1.1 2005-09-30 18:50:18 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.form;

/**
 * Cell Line interface for CI screen
 */
public interface CellLineData {

	public String getClid();
	public void setClid(String clid);
	public String getCellLineName();
	public void setCellLineName(String cellLineName);
	public String getOrganName();
	public void setOrganName(String organName);
	public String getExperiment();
	public void setExperiment(String experiment);
	public String getResults();
	public void setResults(String results);
	public String getComments();
	public void setComments(String comments);
	public String getConceptCode();
	public void setConceptCode(String conceptCode);
	public String getOrgan();
	public void setOrgan( String organ );
	public String getOrganTissueName();
	public void setOrganTissueName( String organTissueName );
	public String getOrganTissueCode();
	public void setOrganTissueCode( String organTissueCode );	
	
}
