package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class EditUserForm extends BaseForm implements Serializable {
    
    private static final long serialVersionUID = 3257098753799404851L;
	
	protected String lastName;
	protected String firstName;
	protected String username;
	protected String id;
	protected boolean isPrincipalInvestigator;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public boolean isPrincipalInvestigator() {
		return isPrincipalInvestigator;
	}
	public void setPrincipalInvestigator(boolean isPrincipalInvestigator) {
		this.isPrincipalInvestigator = isPrincipalInvestigator;
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
}
