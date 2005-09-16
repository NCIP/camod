/**
 * @author dgeorge
 * 
 * $Id: UserManagerImpl.java,v 1.3 2005-09-16 15:52:57 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.UserManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.exceptions.CSException;

import java.util.*;

/**
 * Implementation of class used to wrap the CSM implementation
 */
public class UserManagerImpl extends BaseManager implements UserManager {

    private AuthorizationManager theAuthorizationMgr = null;

    /**
     * Constructor gets reference to authorization manager
     */
    UserManagerImpl() {

        log.trace("Entering UserManagerImpl");

        try {
            theAuthorizationMgr = SecurityServiceProvider.getAuthorizationManager(Constants.UPT_CONTEXT_NAME);
        } catch (CSException ex) {
            log.error("Error getting authorization manager", ex);
        } catch (Throwable e) {
            log.error("Error getting authorization manager", e);
        }

        log.trace("Exiting UserManagerImpl");
    }

    /**
     * Get a list of roles for a user
     * 
     * @param inUsername
     *            the login name of the user
     * 
     * @return the list of roles associated with the user
     */
    public List getRolesForUser(String inUsername) {

        log.trace("Entering getRolesForUser");

        List theRoles = new ArrayList();

        if (theAuthorizationMgr != null) {
            theRoles.add(Constants.Admin.Roles.CONTROLLER);
            theRoles.add(Constants.Admin.Roles.EDITOR);
            theRoles.add(Constants.Admin.Roles.SCREENER);
        }
        log.info("User: " + inUsername + " and roles: " + theRoles);

        log.trace("Exiting getRolesForUser");

        return theRoles;
    }

    /**
     * Get a list of users for a particular role
     * 
     * @param inRoleName
     *            is the name of the role
     * 
     * @return the list of users associated with the role
     */
    public List getUsersForRole(String inRoleName) {

        log.trace("Entering getUsersForRole");

        List theUsers = new ArrayList();

        if (theAuthorizationMgr != null) {
            theUsers.add("camod_demo");
            theUsers.add("dgeorge");
        }

        log.info("Role: " + inRoleName + " and users: " + theUsers);

        log.trace("Exiting getUsersForRole");

        return theUsers;
    }

    /**
     * Get an e-mail address for a user
     * 
     * @param inUsername
     *            is the login name of the user
     * 
     * @return the list of users associated with the role
     */
    public String getEmailForUser(String inUsername) {

        log.trace("Entering getEmailForUser");

        String theEmail = "";

        try {
            User theCurrentUser = theAuthorizationMgr.getUser(inUsername);

            log.info("Username: " + theCurrentUser.getUserId() + "\nFirstName: " + theCurrentUser.getFirstName()
                    + "\nLastName: " + theCurrentUser.getLastName() + "\nPhoneNumber: "
                    + theCurrentUser.getPhoneNumber() + "\nEmail: " + theCurrentUser.getEmailId());

            theEmail = theCurrentUser.getEmailId();

        } catch (Exception e) {
            log.warn("Could not fetch user from CSM", e);
        }

        log.trace("Exiting getEmailForUser");

        return theEmail;

    }

    /**
     * Get an e-mail address for a user
     * 
     * @param inUsername
     *            is the login name of the user
     * 
     * @return the list of users associated with the role
     */
    public String getEmailForController() {

        log.trace("Entering getEmailForController");

        String theEmail = "";

        try {

            // Get from default bundle
            ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
            String theController = theBundle.getString(Constants.CONTROLLER_USERNAME_KEY);

            theEmail = getEmailForUser(theController);

        } catch (Exception e) {
            log.warn("Unable to get controller username: ", e);
        }

        log.trace("Exiting getEmailForController");

        return theEmail;
    }
}