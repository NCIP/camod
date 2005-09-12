package gov.nih.nci.camod.service.impl;


/**
 * @author dgeorge
 * 
 * Singleton class for the QueryManagerImpl
 */
public class QueryManagerSingleton {

    private static QueryManagerImpl ourManager = new QueryManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized QueryManagerImpl instance() {
        return ourManager;
    }
}
