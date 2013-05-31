/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.OrganManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the OrganManager
 */
public class OrganManagerSingleton {

    private static OrganManager ourManager = new OrganManagerImpl();

    /**
     * @return the global instance of the OrganManager
     */
    public static synchronized OrganManager instance() {
        return ourManager;
    }
    
}
