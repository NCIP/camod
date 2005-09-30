/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

/**
 * @author pandyas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CellLineForm extends BaseForm implements Serializable, CellLineData {
    
    private static final long serialVersionUID = 3257355453799404851L;
    
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public CellLineForm() {}

	protected String clid;
	protected String cellLineName;
	protected String organName;
	protected String experiment;
	protected String results;
	protected String comments;
	
	protected String conceptCode;
	protected String organ;
	protected String organTissueName;
	protected String organTissueCode;	

	/**
	 * @return Returns the clid.
	 */
	public String getClid() {
		return clid;
	}
	/**
	 * @param clid The clid to set.
	 */
	public void setClid(String clid) {
		this.clid = clid;
	}
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
	 * @return Returns the organName.
	 */
	public String getOrganName() {
		return organName;
	}
	/**
	 * @param organName The organName to set.
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
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
	 * @return Returns the conceptCode.
	 */
	public String getConceptCode() {
		return conceptCode;
	}
	/**
	 * @param conceptCode The conceptCode to set.
	 */
	public void setConceptCode(String conceptCode) {
		this.conceptCode = conceptCode;
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
	 * @return Returns the organTissueCode.
	 */	
	public String getOrganTissueCode() {
		return organTissueCode;
	}
	/**
	 * @param organTissueCode The organTissueCode to set.
	 */	
	public void setOrganTissueCode( String organTissueCode ) {
		this.organTissueCode = organTissueCode;
	}	
}
