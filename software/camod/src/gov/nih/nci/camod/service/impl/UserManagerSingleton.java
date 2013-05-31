/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: UserManagerSingleton.java,v 1.2 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;


/**
 * @author dgeorge
 * 
 * Singleton class for the UserManagerImpl
 */
public class UserManagerSingleton {

    private static UserManagerImpl ourManager = new UserManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized UserManagerImpl instance() {
        return ourManager;
    }
}
