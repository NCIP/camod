/**
 * 
 * $Id: EditUserForm.java,v 1.4 2006-04-17 19:09:19 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class EditUserForm extends BaseForm implements Serializable {

	private static final long serialVersionUID = 3257098753799404851L;

	protected String lastName;

	protected String firstName;

	protected String username;

	protected String id;

	protected boolean principalInvestigator;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isPrincipalInvestigator() {
		return principalInvestigator;
	}

	public void setPrincipalInvestigator(boolean principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.principalInvestigator = false;
		this.lastName = null;
		this.firstName = null;
		this.username = null;
		this.id = null;
	}
}
