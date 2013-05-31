/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.TherapyManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the TherapyManager
 */
public class TherapyManagerSingleton {

    private static TherapyManager ourManager = new TherapyManagerImpl();

    /**
     * @return the global instance of the TherapyManager
     */
    public static synchronized TherapyManager instance() {
        return ourManager;
    }
}
