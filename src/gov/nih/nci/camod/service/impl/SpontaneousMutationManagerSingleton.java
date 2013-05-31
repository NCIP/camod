/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.SpontaneousMutationManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the SexDistributionManager
 */
public class SpontaneousMutationManagerSingleton {

    private static SpontaneousMutationManager ourManager = new SpontaneousMutationManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized SpontaneousMutationManager instance() {
        return ourManager;
    }
}


