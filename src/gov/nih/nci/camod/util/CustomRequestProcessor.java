/**
 * 
 * $Id: CustomRequestProcessor.java,v 1.8 2005-12-06 19:51:32 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.util;

import gov.nih.nci.camod.Constants;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;
import org.apache.struts.config.ForwardConfig;

/**
 * Checks for User Login on any struts request Overrides the
 * processActionPerform() method
 * 
 * @author schroedlni
 * 
 */
public class CustomRequestProcessor extends SecureRequestProcessor {

    protected final Log log = LogFactory.getLog(getClass());

    public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        log.trace("Entering process");
        super.process(request, response);
        log.trace("Exiting process");
    }

    protected ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response,
            Action action, ActionForm form, ActionMapping mapping) throws IOException, ServletException {

        log.trace("Entering processActionPerform");

        // Check custom ActionMapping Parameter
        if (mapping.getParameter().toString().equals("method") || mapping.getParameter().toString().equals("protected")) {

            HttpSession session = request.getSession();
            String user = (String) session.getAttribute(Constants.CURRENTUSER);

            if (user == null) {
                log.info("User not authorized.  Sending to login page");
                request.getSession().setAttribute(Constants.NOTLOGGEDIN, "true");
                return mapping.findForward("login");
            }
        }

        // Check to make sure the user has the correct privleges
        String thePath = mapping.getPath();

        // Controlled access; check roles
        if (thePath.indexOf("AdminUserManagementPopulateAction") != -1 || thePath.indexOf("AdminRolesAssignment") != -1
                || thePath.indexOf("adminEditUserRoles") != -1 || thePath.indexOf("adminEditUser") != -1
                || thePath.indexOf("adminUserManagement") != -1
                || thePath.indexOf("AdminEditUserRolesPopulateAction") != -1
                || thePath.indexOf("AdminEditUserRolesAction") != -1
                || thePath.indexOf("AdminEditUserPopulateAction") != -1 || thePath.indexOf("AdminEditUserAction") != -1) {

            List theRoles = (List) request.getSession().getAttribute(Constants.CURRENTUSERROLES);

            if (theRoles == null || !theRoles.contains(Constants.Admin.Roles.COORDINATOR)) {
                log.debug("Accessing page: " + thePath + " without proper role");

                return mapping.findForward("noAccess");
            }
        }

        // Print the forwards and mapping
        String[] theForwards = mapping.findForwards();
        for (int i = 0, j = theForwards.length; i < j; i++) {
            log.info("Forward: " + theForwards[i]);
        }
        log.info("Mapping: " + mapping);

        // Process the action
        ActionForward theForward = super.processActionPerform(request, response, action, form, mapping);
        log.info("ActionForward : " + theForward);

        log.trace("Exiting processActionPerform");
        return theForward;
    }

    protected ActionForward processException(HttpServletRequest request, HttpServletResponse response,
            Exception exception, ActionForm form, ActionMapping mapping) throws IOException, ServletException {

        log.trace("Entering processException");

        ActionForward theForward = super.processException(request, response, exception, form, mapping);

        log.info("ActionForward:" + theForward);

        log.trace("Exiting processException");
        return theForward;
    }

    protected boolean processValidate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
            ActionMapping mapping) throws IOException, ServletException {

        log.trace("Entering processValidate");

        boolean validate = super.processValidate(request, response, form, mapping);
        log.info("Validate result: " + validate);

        log.trace("Exiting validate");

        return validate;
    }

    protected boolean processForward(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException {

        log.trace("Entering processForward");

        boolean forward = super.processForward(request, response, mapping);
        log.info("Forward result: " + forward);

        log.trace("Exiting processForward");

        return forward;
    }

    protected void processForwardConfig(HttpServletRequest request, HttpServletResponse response, ForwardConfig config)
            throws IOException, ServletException {

        log.trace("Entering processForwardConfig");

        super.processForwardConfig(request, response, config);

        log.info("The ForwardConfig: " + config);
        log.trace("Exiting processForwardConfig");
    }
}
