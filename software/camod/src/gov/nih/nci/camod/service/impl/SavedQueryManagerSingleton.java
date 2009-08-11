package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.SavedQueryManager;

public class SavedQueryManagerSingleton {

    private static SavedQueryManager ourManager = new SavedQueryManagerImpl();

    public static synchronized SavedQueryManager instance() {
        return ourManager;
    }
}
