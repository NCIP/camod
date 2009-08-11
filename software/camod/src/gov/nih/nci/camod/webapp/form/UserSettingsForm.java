/**
 * 
 * $Id: UserSettingsForm.java,v 1.5 2008-06-05 14:23:18 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2008/05/27 14:35:32  pandyas
 * Modified to prevent SQL injection
 * Added code to clean user input before using text
 * Re: Apps Scan run 05/23/2008
 *
 * Revision 1.3  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.util.SafeHTMLUtil;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

public class UserSettingsForm extends BaseForm implements UserSettingsData, Serializable {

    private static final long serialVersionUID = 3257165451799414811L;

    protected String username;
    protected String lastName;
    protected String firstName;
    protected String affiliation;
    protected String phone;
    protected String email;
    protected String piUsername;
    protected boolean isPrincipalInvestigator = false;
    protected String piLastName;
    protected String piFirstName;
    protected String piEmail;

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
        // Clean the parameter
        if (this.username != null && !this.username.equals(""))  {
                this.username = SafeHTMLUtil.clean(this.username);
        }         
    }

    /**
     * Reset the values of the form
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        isPrincipalInvestigator = false;
        username = null;
        lastName = null;
        firstName = null;
        affiliation = null;
        phone = null;
        email = null;
        piUsername = null;
        piLastName = null;
        piFirstName = null;
        piEmail = null;
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
        // Clean the parameter
        if (this.affiliation != null && !this.affiliation.equals(""))  {
                this.affiliation = SafeHTMLUtil.clean(this.affiliation);
        }        
    }

    /**
     * @return Returns the emailAddress.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param emailAddress
     *            The emailAddress to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            The firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
        // Clean the parameter
        if (this.firstName != null && !this.firstName.equals(""))  {
                this.firstName = SafeHTMLUtil.clean(this.firstName);
        }        
    }

    /**
     * @return Returns the isPrincipalInvestigator.
     */
    public boolean isPrincipalInvestigator() {
        return isPrincipalInvestigator;
    }

    /**
     * @param isPrincipalInvestigator
     *            The isPrincipalInvestigator to set.
     */
    public void setPrincipalInvestigator(boolean isPrincipalInvestigator) {
        this.isPrincipalInvestigator = isPrincipalInvestigator;
    }

    /**
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
        // Clean the parameter
        if (this.lastName != null && !this.lastName.equals(""))  {
                this.lastName = SafeHTMLUtil.clean(this.lastName);
        }         
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

    /**
     * @return Returns the piFirstName.
     */
    public String getPiFirstName() {
        return piFirstName;
    }

    /**
     * @param piFirstName
     *            The piFirstName to set.
     */
    public void setPiFirstName(String piFirstName) {
        this.piFirstName = piFirstName;
        // Clean the parameter
        if (this.piFirstName != null && !this.piFirstName.equals(""))  {
                this.piFirstName = SafeHTMLUtil.clean(this.piFirstName);
        }        
    }

    /**
     * @return Returns the piLastName.
     */
    public String getPiLastName() {
        return piLastName;
    }

    /**
     * @param piLastName
     *            The piLastName to set.
     */
    public void setPiLastName(String piLastName) {
        this.piLastName = piLastName;
        // Clean the parameter
        if (this.piLastName != null && !this.piLastName.equals(""))  {
                this.piLastName = SafeHTMLUtil.clean(this.piLastName);
        }        
    }

    /**
     * @return Returns the piUsername.
     */
    public String getPiUsername() {
        return piUsername;
    }

    /**
     * @param piUsername
     *            The piUsername to set.
     */
    public void setPiUsername(String piUsername) {
        this.piUsername = piUsername;
        // Clean the parameter
        if (this.piUsername != null && !this.piUsername.equals(""))  {
                this.piUsername = SafeHTMLUtil.clean(this.piUsername);
        }        
    }

    public String getPiEmail() {
        return piEmail;
    }

    public void setPiEmail(String piEmail) {
        this.piEmail = piEmail;
    }
}
