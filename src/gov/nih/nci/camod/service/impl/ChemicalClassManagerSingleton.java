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
