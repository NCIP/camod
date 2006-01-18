package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.OrganManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the OrganManager
 */
public class OrganManagerSingleton
{
    private static OrganManager ourManager = new OrganManagerImpl();

    /**
     * @return the global instance of the OrganManager
     */
    public static synchronized OrganManager instance()
    {
        return ourManager;
    }
}
