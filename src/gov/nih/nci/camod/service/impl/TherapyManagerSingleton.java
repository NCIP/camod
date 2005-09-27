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
