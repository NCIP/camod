/**
 * @author dgeorge
 * 
 * $Id: CurationAssignmentForm.java,v 1.6 2008-08-12 19:40:33 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2007/08/07 15:39:35  pandyas
 * Fixed clear button on adminEditModels.jsp
 *
 * Revision 1.4  2007/08/06 17:27:03  pandyas
 * working on reest button on adminEdit screen
 *
 * Revision 1.3  2007/07/31 12:02:06  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.2  2005/10/24 13:28:30  georgeda
 * Cleanup changes
 *
 * Revision 1.1  2005/10/17 13:23:22  georgeda
 * Initial revision
 *
 * Revision 1.1  2005/09/19 19:54:06  georgeda
 * New model assignment functionality
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Form for querying for model assignment
 *
 */
public class CurationAssignmentForm extends BaseForm implements Serializable, CurationAssignmentData {

    private static final long serialVersionUID = 6227851969634170134L;
    
    /**
     * Default empty constructor
     * 
     */
    public CurationAssignmentForm() {
    }
    // current state for admin edit models screen and model assignment screens
    protected String currentState;
    protected String externalSource;
    protected String modelDescriptor;
    protected String modelId; 
    protected String screener; 
    protected String editor;   
    protected String species;
    protected String principalInvestigator;


    
	/**
	 * @return the currentState
	 */
	public String getCurrentState() {
		return currentState;
	}

	/**
	 * @param currentState the currentState to set
	 */
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	/**
	 * @return the editor
	 */
	public String getEditor() {
		return editor;
	}

	/**
	 * @param editor the editor to set
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}

	/**
	 * @return the externalSource
	 */
	public String getExternalSource() {
		return externalSource;
	}

	/**
	 * @param externalSource the externalSource to set
	 */
	public void setExternalSource(String externalSource) {
		this.externalSource = externalSource;
	}

	/**
	 * @return the modelDescriptor
	 */
	public String getModelDescriptor() {
		return modelDescriptor;
	}

	/**
	 * @param modelDescriptor the modelDescriptor to set
	 */
	public void setModelDescriptor(String modelDescriptor) {
		this.modelDescriptor = modelDescriptor;
	}

	/**
	 * @return the modelId
	 */
	public String getModelId() {
		return modelId;
	}

	/**
	 * @param modelId the modelId to set
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	/**
	 * @return the principalInvestigator
	 */
	public String getPrincipalInvestigator() {
		return principalInvestigator;
	}

	/**
	 * @param principalInvestigator the principalInvestigator to set
	 */
	public void setPrincipalInvestigator(String principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}

	/**
	 * @return the screener
	 */
	public String getScreener() {
		return screener;
	}

	/**
	 * @param screener the screener to set
	 */
	public void setScreener(String screener) {
		this.screener = screener;
	}

	/**
	 * @return the species
	 */
	public String getSpecies() {
		return species;
	}

	/**
	 * @param species the species to set
	 */
	public void setSpecies(String species) {
		this.species = species;
	}

	// Resets all fields on edit model and model assignment screens
    public void allFieldsReset() {
        currentState = null;
        externalSource = null;
        modelDescriptor = null; 
        modelId = null;
        screener = null;        
        editor = null;
        species = null;
        principalInvestigator = null;       
    }
    
}
