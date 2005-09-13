/*
 * LoginAction.java
 *
 * Created on June 24, 2005, 11:31 AM
 *
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.UserManager;
import gov.nih.nci.camod.webapp.form.LoginForm;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSException;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public final class LoginAction extends BaseAction {

    private static AuthenticationManager ourAuthorizationMgr = null;

    /**
     * Authenticates the user using the CSM AuthenticationManager and stores in
     * the session.
     * 
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        LoginForm loginForm = (LoginForm) form;

        log.debug("Logon Username: " + loginForm.getUsername());
        log.debug("System Config file is: " + System.getProperty("gov.nih.nci.security.configFile"));

        // check login credentials using Authentication Mangager
        boolean loginOK = false;

        try {
            loginOK = getAuthenticationManager().login(loginForm.getUsername(), loginForm.getPassword());
            UserManager theUserManager = (UserManager) getBean("userManager");
            List theRoles = theUserManager.getRolesForUser(loginForm.getUsername());
            request.getSession().setAttribute(Constants.CURRENTUSERROLES, theRoles);

        } catch (Exception ex) {
            loginOK = false;
            log.debug("The user was denied access to the csm application.", ex);
        }

        String forward = Constants.FAILURE;

        if (loginOK) {
            log.debug("Successful login");
            forward = "success";
            request.getSession().setAttribute(Constants.CURRENTUSER, loginForm.getUsername());

        } else {
            log.debug("Failed login");
            forward = "login";

            ActionMessages errors = new ActionMessages();
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.validation.header"));
            saveErrors(request, errors);
        }

        // Forward control to the specified success URI
        return mapping.findForward(forward);
    }

    /**
     * Returns the AuthenticationManager for the CSM RI. This method follows the
     * singleton pattern so that only one AuthenticationManager is created for
     * the CSM RI.
     * 
     * @return the authentication manager instance
     * @throws CSException
     */
    protected AuthenticationManager getAuthenticationManager() throws CSException {
        if (ourAuthorizationMgr == null) {
            synchronized (LoginAction.class) {
                if (ourAuthorizationMgr == null) {
                    ourAuthorizationMgr = SecurityServiceProvider.getAuthenticationManager(Constants.UPT_CONTEXT_NAME);
                }
            }
        }
        return ourAuthorizationMgr;
    }
}