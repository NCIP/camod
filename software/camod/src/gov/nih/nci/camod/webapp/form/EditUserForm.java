/**
 * 
 * $Id: EditUserForm.java,v 1.5 2006-05-15 15:45:00 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class EditUserForm extends BaseForm implements Serializable, EditUserData {

	private static final long serialVersionUID = 3257098753799404851L;

	protected String lastName;
	protected String firstName;
	protected String username;
	protected String id;
	protected String phone;
	protected String affiliation;
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

    /**
     * @return Returns the affiliation.
     */
    public String getAffiliation() {
        return affiliation;
    }

    /**
     * @param affiliation
     *            The affiliation to set.
     */
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    /**
     * @return Returns the phoneNumber.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phoneNumber
     *            The phoneNumber to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.principalInvestigator = false;
		this.lastName = null;
		this.firstName = null;
		this.username = null;
		this.phone = null;
		this.affiliation = null;
		this.id = null;
	}
}
