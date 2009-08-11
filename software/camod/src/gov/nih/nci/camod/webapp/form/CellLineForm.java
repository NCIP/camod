/**
 * @author pandyas
 * 
 * $Id: CellLineForm.java,v 1.10 2006-04-17 19:09:19 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2005/10/20 20:27:27  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;


public class CellLineForm extends BaseForm implements Serializable, CellLineData {
    
    private static final long serialVersionUID = 3257355453799404851L;
    
	/**
	 * Default empty constructor
	 * @author rajputs
	 */
	public CellLineForm() {}

	protected String cellLineName;
	protected String experiment;
	protected String results;
	protected String comments;

	protected String organ;
	protected String organTissueName;
	protected String organTissueCode;	

	/**
	 * @return Returns the cellLineName.
	 */
	public String getCellLineName() {
		return cellLineName;
	}
	/**
	 * @param cellLineName The cellLineName to set.
	 */
	public void setCellLineName(String cellLineName) {
		this.cellLineName = cellLineName;
	}

	/**
	 * @return Returns the experiment.
	 */
	public String getExperiment() {
		return experiment;
	}
	/**
	 * @param experiment The experiment to set.
	 */
	public void setExperiment(String experiment) {
		this.experiment = experiment;
	}
	/**
	 * @return Returns the results.
	 */
	public String getResults() {
		return results;
	}
	/**
	 * @param results The results to set.
	 */
	public void setResults(String results) {
		this.results = results;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the organ.
	 */	
	public String getOrgan() {
		return organ;
	}
	/**
	 * @param organ The organ to set.
	 */	
	public void setOrgan( String organ ) {
		this.organ = organ;
	}
	/**
	 * @return Returns the organTissueName.
	 */	
	public String getOrganTissueName() {
		return organTissueName;
	}
	/**
	 * @param organTissueName The organTissueName to set.
	 */	
	public void setOrganTissueName( String organTissueName ) {
		this.organTissueName = organTissueName;
	}
	/**
	 * @return Returns the organTissueCode (concept code).
	 */	
	public String getOrganTissueCode() {
		return organTissueCode;
	}
	/**
	 * @param organTissueCode The organTissueCode (concept code) to set .
	 */	
	public void setOrganTissueCode( String organTissueCode ) {
		this.organTissueCode = organTissueCode;
	}	
}
