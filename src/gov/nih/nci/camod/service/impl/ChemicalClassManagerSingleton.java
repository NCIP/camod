/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.ChemicalClassManager;

/**
 * @author pandyas
 * 
 * Singleton class for the SexDistributionManager
 */

public class ChemicalClassManagerSingleton {

    private static ChemicalClassManager ourManager = new ChemicalClassManagerImpl();

    /**
     * @return the global instance of the ChemicalClassManager
     */
    public static synchronized ChemicalClassManager instance() {
        return ourManager;
    }	
}
