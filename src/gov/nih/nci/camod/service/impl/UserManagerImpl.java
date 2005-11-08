/**
 * @author dgeorge
 * 
 * $Id: UserManagerImpl.java,v 1.11 2005-11-08 22:32:44 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2005/11/07 13:58:29  georgeda
 * Dynamically update roles
 *
 * Revision 1.9  2005/10/24 13:28:06  georgeda
 * Cleanup changes
 *
 * Revision 1.8  2005/10/17 13:10:16  georgeda
 * Get contact information
 *
 * Revision 1.7  2005/10/13 17:00:06  georgeda
 * Cleanup
 *
 * Revision 1.6  2005/09/30 19:49:58  georgeda
 * Make sure user is in db
 *
 * Revision 1.5  2005/09/22 18:55:49  georgeda
 * Get coordinator from user in properties file
 *
 * Revision 1.4  2005/09/22 15:15:17  georgeda
 * More changes
 *
 * Revision 1.3  2005/09/16 15:52:57  georgeda
 * Changes due to manager re-write
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.UserManager;
import gov.nih.nci.camod.util.LDAPUtil;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSException;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Implementation of class used to wrap the CSM implementation
 */
public class UserManagerImpl extends BaseManager implements UserManager {

    private AuthenticationManager theAuthenticationMgr = null;

    /**
     * Constructor gets reference to authorization manager
     */
    UserManagerImpl() {

        log.trace("Entering UserManagerImpl");

        try {
            theAuthenticationMgr = SecurityServiceProvider.getAuthenticationManager(Constants.UPT_CONTEXT_NAME);
        } catch (CSException ex) {
            log.error("Error getting authentication managers", ex);
        } catch (Throwable e) {
            log.error("Error getting authentication managers", e);
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
     * @throws Exception
     */
    public List getRolesForUser(String inUsername) throws Exception {

        log.trace("Entering getRolesForUser");

        List theRoles = new ArrayList();

        try {
            Person thePerson = new Person();
            thePerson.setUsername(inUsername);

            List thePeople = Search.query(thePerson);

            if (thePeople.size() > 0) {
                thePerson = (Person) thePeople.get(0);

                List theRoleList = thePerson.getRoleCollection();

                Iterator theIterator = theRoleList.iterator();

                while (theIterator.hasNext()) {
                    Role theRole = (Role) theIterator.next();
                    theRoles.add(theRole.getName());
                }
            } else {
                throw new IllegalArgumentException("User: " + inUsername + " not in caMOD database");
            }

        } catch (Exception e) {
            log.error("Unable to get roles for user (" + inUsername + ": ", e);
            throw e;
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
     * @throws Exception
     */
    public List getUsersForRole(String inRoleName) throws Exception {

        log.trace("Entering getUsersForRole");

        List theUsersForRole = new ArrayList();

        Role theRole = new Role();
        theRole.setName(inRoleName);

        try {

            List theRoles = Search.query(theRole);

            if (theRoles.size() > 0) {
                theRole = (Role) theRoles.get(0);

                // Get the users for the role
                List theUsers = theRole.getPartyCollection();
                Iterator theIterator = theUsers.iterator();

                // Go through the list of returned Party objects
                while (theIterator.hasNext()) {
                    Object theObject = theIterator.next();

                    // Only add when it's actually a person
                    if (theObject instanceof Person) {
                        Person thePerson = (Person) theObject;
                        theUsersForRole.add(thePerson.getUsername());
                    }
                }

            } else {
                log.warn("Role not found in database: " + inRoleName);
            }

        } catch (Exception e) {
            log.error("Unable to get roles for user: ", e);
            throw e;
        }

        log.info("Role: " + inRoleName + " and users: " + theUsersForRole);

        log.trace("Exiting getUsersForRole");

        return theUsersForRole;
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

        log.debug("Username: " + inUsername);

        String theEmail = "";

        try {
            theEmail = LDAPUtil.getEmailAddressForUser(inUsername);
        } catch (Exception e) {
            log.warn("Could not fetch user from LDAP", e);
        }

        log.trace("Exiting getEmailForUser");

        return theEmail;

    }

    /**
     * Update the roles for the current user
     */
    public void updateCurrentUserRoles(HttpServletRequest inRequest) {
        String theCurrentUser = (String) inRequest.getSession().getAttribute(Constants.CURRENTUSER);
        try {
            List theRoles = getRolesForUser(theCurrentUser);
            inRequest.getSession().setAttribute(Constants.CURRENTUSERROLES, theRoles);
        } catch (Exception e) {
            log.info("Unable to update user roles for " + theCurrentUser, e);
        }
    }

    /**
     * Get an e-mail address for a user
     * 
     * @param inUsername
     *            is the login name of the user
     * 
     * @return the list of users associated with the role
     */
    public ContactInfo getContactInformationForUser(String inUsername) {

        log.trace("Entering getContactInformationForUser");

        log.debug("Username: " + inUsername);

        ContactInfo theContactInfo = new ContactInfo();

        try {
            theContactInfo.setInstitute("");
            theContactInfo.setEmail(LDAPUtil.getEmailAddressForUser(inUsername));
            theContactInfo.setPhone("");

        } catch (Exception e) {
            log.warn("Could not fetch user from LDAP", e);
        }

        log.trace("Exiting getEmailForUser");

        return theContactInfo;

    }

    /**
     * Get an e-mail address for the coordinator
     * 
     * @return the list of users associated with the role
     */
    public String getEmailForCoordinator() {

        log.trace("Entering getEmailForCoordinator");

        String theEmail = "";

        try {

            // Get from default bundle
            ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
            String theCoordinator = theBundle.getString(Constants.BundleKeys.COORDINATOR_USERNAME_KEY);

            theEmail = getEmailForUser(theCoordinator);

        } catch (Exception e) {
            log.warn("Unable to get coordinator email: ", e);
        }

        log.trace("Exiting getEmailForCoordinator");

        return theEmail;
    }

    /**
     * Log in a user and get roles.
     * 
     * @param inUsername
     *            is the login name of the user
     * @param inPassword
     *            password
     * @param inRequest
     *            Used to store the roles
     * 
     * @return the list of users associated with the role
     */
    public boolean login(String inUsername, String inPassword, HttpServletRequest inRequest) {

        boolean loginOk = false;
        try {

            loginOk = theAuthenticationMgr.login(inUsername, inPassword);

            // Does the user exist? Must also be in our database to login
            List theRoles = getRolesForUser(inUsername);
            inRequest.getSession().setAttribute(Constants.CURRENTUSERROLES, theRoles);

        } catch (Exception e) {
            log.error("Error logging in user: ", e);
            loginOk = false;
        }

        return loginOk;
    }
}