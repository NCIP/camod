/**
 * 
 * $Id: LoginAction.java,v 1.6 2005-09-22 15:18:13 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/09/16 15:52:55  georgeda
 * Changes due to manager re-write
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.impl.UserManagerSingleton;
import gov.nih.nci.camod.webapp.form.LoginForm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public final class LoginAction extends BaseAction {

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
        boolean loginOK = UserManagerSingleton.instance().login(loginForm.getUsername(), loginForm.getPassword(), request);

        System.out.println("Login: " + loginOK);
        String forward = "login";

        if (loginOK) {
            log.debug("Successful login");
            forward = "success";
            request.getSession().setAttribute(Constants.CURRENTUSER, loginForm.getUsername());

        } else {
            ActionMessages errors = new ActionMessages();
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.validation.header"));
            saveErrors(request, errors);
        }

        // Forward control to the specified success URI
        return mapping.findForward(forward);
    }
}