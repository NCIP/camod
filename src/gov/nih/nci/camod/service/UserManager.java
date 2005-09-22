/**
 * @author dgeorge
 * 
 * $Id: UserManager.java,v 1.3 2005-09-22 15:13:17 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * Public interface for the user manager. See implementation(s) for details.
 * 
 */
public interface UserManager {

    public List getRolesForUser(String inUsername) throws Exception;

    public List getUsersForRole(String inRole) throws Exception;

    public String getEmailForUser(String inUsername) throws Exception;

    public String getEmailForCoordinator() throws Exception;

    boolean login(String inUsername, String inPassword, HttpServletRequest inRequest);
}
