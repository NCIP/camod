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
