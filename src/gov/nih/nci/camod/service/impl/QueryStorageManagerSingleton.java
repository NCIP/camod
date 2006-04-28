package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.QueryStorageManager;

public class QueryStorageManagerSingleton {

    private static QueryStorageManager ourManager = new QueryStorageManagerImpl();

    public static synchronized QueryStorageManager instance() {
        return ourManager;
    }
}
