/**
 * @author dgeorge
 * 
 * $Id: RolesAssignmentForm.java,v 1.2 2005-10-24 13:28:30 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/17 13:11:44  georgeda
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
import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * Form for setting/querying for roles
 * 
 */
public class RolesAssignmentForm extends ValidatorForm implements Serializable {

	private static final long serialVersionUID = 6227851900634170134L;
	
	protected String myPersonId;
	
	protected String myDisplayName;

	protected String myCurrentRole;

	protected boolean coordinator = false;

	protected boolean screener = false;

	protected boolean editor = false;

	public String getDisplayName() {
		return myDisplayName;
	}

	public void setDisplayName(String inDisplayName) {
		myDisplayName = inDisplayName;
	}
	
	public String getPersonId() {
		return myPersonId;
	}

	public void setPersonId(String inPersonId) {
		myPersonId = inPersonId;
	}
	
	public String getCurrentRole() {
		return myCurrentRole;
	}

	public void setCurrentRole(String inCurrentRole) {
		myCurrentRole = inCurrentRole;
	}

	public boolean isCoordinator() {
		return coordinator;
	}

	public void setCoordinator(boolean coordinator) {
		this.coordinator = coordinator;
	}

	public boolean isEditor() {
		return editor;
	}

	public void setEditor(boolean isEditor) {
		this.editor = isEditor;
	}

	public boolean isScreener() {
		return screener;
	}

	public void setScreener(boolean isScreener) {
		this.screener = isScreener;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.myPersonId = null;
		this.myCurrentRole = null;
		this.myDisplayName = null;
		this.screener = false;
		this.coordinator = false;
		this.editor = false;
    }
}
