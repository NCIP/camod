/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.service.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dgeorge
 */
public class UserManagerImpl extends BaseManager implements UserManager {

    public List getRolesForUser(String inUserName) {

        List theRoles = new ArrayList();
        theRoles.add(Constants.Admin.Roles.CONTROLLER);
        theRoles.add(Constants.Admin.Roles.EDITOR);
        theRoles.add(Constants.Admin.Roles.SCREENER);

        return theRoles;
    }

    public List getUsersForRole(String inRoleName) {

        List theUsers = new ArrayList();
        theUsers.add("camod_demo");
        theUsers.add("dgeorge");

        return theUsers;
    }
}
