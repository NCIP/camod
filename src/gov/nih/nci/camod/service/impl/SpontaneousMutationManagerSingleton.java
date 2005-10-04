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


