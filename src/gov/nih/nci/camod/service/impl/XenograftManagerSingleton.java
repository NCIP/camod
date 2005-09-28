package gov.nih.nci.camod.service.impl;

/**
 * @author dgeorge
 * 
 * Singleton class for the XenograftManagerImpl
 */
public class XenograftManagerSingleton {

    private static XenograftManagerImpl ourManager = new XenograftManagerImpl();

    /**
     * @return the global instance of the XenograftManager
     */
    public static synchronized XenograftManagerImpl instance() {
        return ourManager;
    }
}
