/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: CustomRequestProcessor.java,v 1.13 2009-05-28 18:48:01 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.12  2008/01/15 19:31:18  pandyas
 * Modified debug statements to build to dev tier
 *
 * Revision 1.11  2008/01/14 21:03:15  pandyas
 * Enabled logging for dev tier instability issue testing
 *
 * Revision 1.10  2007/09/12 19:36:14  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.9  2007/03/27 18:40:11  pandyas
 * changed log statements to clean up output - done testing changes
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

        log.debug("Entering process");
        log.debug("process current user= " + request.getSession().getAttribute(Constants.CURRENTUSER));
        super.process(request, response);
        log.debug("Exiting process");
    }

    protected ActionForward processActionPerform(HttpServletRequest request, HttpServletResponse response,
            Action action, ActionForm form, ActionMapping mapping) throws IOException, ServletException {

        log.debug("Entering processActionPerform");
        log.debug("processActionPerform current user= " + request.getSession().getAttribute(Constants.CURRENTUSER));
        log.debug("processActionPerform mapping.getParameter().toString()= " + mapping.getParameter().toString());
        log.debug("processActionPerform loggedin= " + request.getSession().getAttribute(Constants.LOGGEDIN));
        
        // Check custom ActionMapping Parameter
        if (mapping.getParameter().toString().equals("method") || mapping.getParameter().toString().equals("protected")) {

            HttpSession session = request.getSession();
            String user = (String) session.getAttribute(Constants.CURRENTUSER);
            log.debug("processActionPerform user= " + request.getSession().getAttribute(Constants.CURRENTUSER));
            
            if (user == null ) {                
                log.debug("User not authorized (processActionPerform).  Sending to login page");
                request.getSession().setAttribute(Constants.LOGGEDIN, "false");
                return mapping.findForward("login");
            }
        }

        // Check to make sure the user has the correct privileges
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
            log.debug("Forward: " + theForwards[i]);
        }
        log.debug("processActionPerform Mapping: " + mapping);

        // Process the action
        ActionForward theForward = super.processActionPerform(request, response, action, form, mapping);
        log.debug("processActionPerform ActionForward : " + theForward);

        log.debug("Exiting processActionPerform");
        return theForward;
    }

    protected ActionForward processException(HttpServletRequest request, HttpServletResponse response,
            Exception exception, ActionForm form, ActionMapping mapping) throws IOException, ServletException {

        log.debug("Entering processException");

        ActionForward theForward = super.processException(request, response, exception, form, mapping);

        log.debug("processException ActionForward:" + theForward);

        log.debug("Exiting processException");
        return theForward;
    }

    protected boolean processValidate(HttpServletRequest request, HttpServletResponse response, ActionForm form,
            ActionMapping mapping) throws IOException, ServletException {

        log.debug("Entering processValidate");
        log.debug("processValidate current user= " + request.getSession().getAttribute(Constants.CURRENTUSER));

        boolean validate = super.processValidate(request, response, form, mapping);
        log.debug("processValidate Validate result: " + validate);

        log.debug("Exiting validate");

        return validate;
    }

    protected boolean processForward(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
            throws IOException, ServletException {

        log.debug("Entering processForward");
        log.debug("processForward current user= " + request.getSession().getAttribute(Constants.CURRENTUSER));

        boolean forward = super.processForward(request, response, mapping);
        log.debug("processForward Forward result: " + forward);

        log.debug("Exiting processForward");

        return forward;
    }

    protected void processForwardConfig(HttpServletRequest request, HttpServletResponse response, ForwardConfig config)
            throws IOException, ServletException {

        log.debug("Entering processForwardConfig");
        log.debug("processForwardConfig current user= " + request.getSession().getAttribute(Constants.CURRENTUSER));

        super.processForwardConfig(request, response, config);

        log.debug("processForwardConfig The ForwardConfig: " + config);
        log.debug("Exiting processForwardConfig");
    }
}
