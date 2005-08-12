/*
 * LogonForm.java
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

public class LoginForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author nschroedl
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public LoginForm() {}
	
	protected String username;
	protected String password;
	
	/**
	 * @return Returns the username.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username The username to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
            // reset any boolean data types to false
        }
}
