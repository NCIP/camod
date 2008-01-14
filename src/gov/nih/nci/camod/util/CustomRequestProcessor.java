/**
 * 
 * $Id: CustomRequestProcessor.java,v 1.11 2008-01-14 21:03:15 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2007/09/12 19:36:14  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.9  2007/03/27 18:40:11  pandyas
 * changed log.info to log.debug to clean up output - done testing changes
 *
 * Revision 1.8  2005/12/06 19:51:32  georgeda
 * Defect #255 - add SSL
 *
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

        log.info("Entering process");
        super.process(request, response);
        log.info("Exiting process");
    }

    protected ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response,
            Action action, ActionForm form, ActionMapping mapping) throws IOException, ServletException {

        log.info("Entering processActionPerform");

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
                log.info("Accessing page: " + thePath + " without proper role");

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

        log.info("Exiting processActionPerform");
        return theForward;
    }

    protected ActionForward processException(HttpServletRequest request, HttpServletResponse response,
            Exception exception, ActionForm form, ActionMapping mapping) throws IOException, ServletException {

        log.info("Entering processException");

        ActionForward theForward = super.processException(request, response, exception, form, mapping);

        log.info("ActionForward:" + theForward);

        log.info("Exiting processException");
        return theForward;
    }

    protected boolean processValidate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
            ActionMapping mapping) throws IOException, ServletException {

        log.info("Entering processValidate");

        boolean validate = super.processValidate(request, response, form, mapping);
        log.info("Validate result: " + validate);

        log.info("Exiting validate");

        return validate;
    }

    protected boolean processForward(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException {

        log.info("Entering processForward");

        boolean forward = super.processForward(request, response, mapping);
        log.info("Forward result: " + forward);

        log.info("Exiting processForward");

        return forward;
    }

    protected void processForwardConfig(HttpServletRequest request, HttpServletResponse response, ForwardConfig config)
            throws IOException, ServletException {

        log.info("Entering processForwardConfig");

        super.processForwardConfig(request, response, config);

        log.info("The ForwardConfig: " + config);
        log.info("Exiting processForwardConfig");
    }
}
