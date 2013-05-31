/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.TargetedModificationManager;

public class TargetedModificationManagerSingleton {

    private static TargetedModificationManager ourManager = new TargetedModificationManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized TargetedModificationManager instance() {
        return ourManager;
    }
}


