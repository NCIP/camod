package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.PublicationManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the PublicationManager
 */
public class PublicationManagerSingleton
{
    private static PublicationManager ourManager = new PublicationManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized PublicationManager instance()
    {
        return ourManager;
    }
}
