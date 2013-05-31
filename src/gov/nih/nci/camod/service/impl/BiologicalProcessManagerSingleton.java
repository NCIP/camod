/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.BiologicalProcessManager;

/**
 * @author pandyas
 * 
 * Singleton class for the BiologicalProcessManager
 */
public class BiologicalProcessManagerSingleton {

	
    private static BiologicalProcessManager ourManager = new BiologicalProcessManagerImpl();

    /**
     * @return the global instance of the BiologicalProcessManager
     */
    public static synchronized BiologicalProcessManager instance() {
        return ourManager;
    }	
}
