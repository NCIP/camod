/*
 * Created on Sept 2nd, 2005
 *
 */
package gov.nih.nci.camod.service;

import java.util.List;


public interface UserManager {
	public List getRolesForUser(String inUsername);
    public List getUsersForRole(String inRole);
    public String getEmailForUser(String inUsername);
    public String getEmailForController();
}
