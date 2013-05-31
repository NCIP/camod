/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.RegulatoryElementTypeManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the SexDistributionManager
 */
public class RegulatoryElementTypeManagerSingleton {

    private static RegulatoryElementTypeManager ourManager = new RegulatoryElementTypeManagerImpl();

    /**
     * @return the global instance of the RegulatoryElementTypeManager
     */
    public static synchronized RegulatoryElementTypeManager instance() {
        return ourManager;
    }
}
