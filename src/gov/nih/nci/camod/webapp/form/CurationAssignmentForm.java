/**
 * @author dgeorge
 * 
 * $Id: CurationAssignmentForm.java,v 1.4 2007-08-06 17:27:03 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
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

    // Previous developer preferred the use of "my"
    protected String myCurrentState;
    // Added later in my preferred format
    protected String externalSource;
    protected String modelDescriptor;
    protected String modelId; 
    protected String screener; 
    protected String editor;   
    protected String species;
    protected String principalInvestigator;

    public String getCurrentState() {
        return myCurrentState;
    }

    public void setCurrentState(String inCurrentState) {
        myCurrentState = inCurrentState;
    }

	public String getExternalSource() {
		return externalSource;
	}

	public void setExternalSource(String externalSource) {
		this.externalSource = externalSource;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getModelDescriptor() {
		return modelDescriptor;
	}

	public void setModelDescriptor(String modelDescriptor) {
		this.modelDescriptor = modelDescriptor;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getScreener() {
		return screener;
	}

	public void setScreener(String screener) {
		this.screener = screener;
	}
	
	public String getPrincipalInvestigator() {
		return principalInvestigator;
	}

	public void setPrincipalInvestigator(String principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        myCurrentState = null;
        externalSource = null;
        modelDescriptor = null; 
        modelId = null;
        screener = null;        
        editor = null;
        species = null;
        principalInvestigator = null;       
    } 
    
}
