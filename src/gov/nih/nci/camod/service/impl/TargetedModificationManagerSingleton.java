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


