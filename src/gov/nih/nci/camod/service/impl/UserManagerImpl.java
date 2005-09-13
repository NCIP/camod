/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
 * @author dgeorge
 */
public class UserManagerImpl extends BaseManager implements UserManager {

    private AuthorizationManager theAuthorizationMgr = null;

    /**
     * Constructor gets reference to authorization manager
     * 
     */
    UserManagerImpl() {
        try {
            theAuthorizationMgr = SecurityServiceProvider.getAuthorizationManager(Constants.UPT_CONTEXT_NAME);
        } catch (CSException ex) {
            System.out.println("CSException " + ex);
        } catch (Throwable e) {
            e.printStackTrace();
        }

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

        List theRoles = new ArrayList();

        if (theAuthorizationMgr != null) {
            theRoles.add(Constants.Admin.Roles.CONTROLLER);
            theRoles.add(Constants.Admin.Roles.EDITOR);
            theRoles.add(Constants.Admin.Roles.SCREENER);
        }

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

        List theUsers = new ArrayList();

        if (theAuthorizationMgr != null) {
            theUsers.add("camod_demo");
            theUsers.add("dgeorge");
        }

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

        String theEmail = "";

        try {
            User theCurrentUser = theAuthorizationMgr.getUser(inUsername);

            System.out.println("Username: " + theCurrentUser.getUserId() + "\nFirstName: "
                    + theCurrentUser.getFirstName() + "\nLastName: " + theCurrentUser.getLastName() + "\nPhoneNumber: "
                    + theCurrentUser.getPhoneNumber() + "\nPassword: " + theCurrentUser.getPassword());

            theEmail = theCurrentUser.getEmailId();
        } catch (Exception e) {
            log.warn("Could not fetch user from CSM", e);
        }
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

        String theEmail = "";

        try {

            // Get from default bundle
            ResourceBundle theBundle = ResourceBundle.getBundle(Constants.CAMOD_BUNDLE);
            String theController = theBundle.getString(Constants.CONTROLLER_USERNAME_KEY);

            theEmail = getEmailForUser(theController);

        } catch (Exception e) {
            log.warn("Unable to get controller username: ", e);
        }

        return theEmail;
    }
}
/* $Log* */
