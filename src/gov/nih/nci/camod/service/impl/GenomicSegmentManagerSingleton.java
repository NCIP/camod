/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.GenomicSegmentManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the SexDistributionManager
 */
public class GenomicSegmentManagerSingleton {

    private static GenomicSegmentManager ourManager = new GenomicSegmentManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized GenomicSegmentManager instance() {
        return ourManager;
    }
}
