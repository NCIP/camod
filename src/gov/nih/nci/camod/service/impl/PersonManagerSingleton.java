package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.PersonManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the PersonManager
 */
public class PersonManagerSingleton
{
    private static PersonManager ourManager = new PersonManagerImpl();

    /**
     * @return the global instance of the PersonManager
     */
    public static synchronized PersonManager instance()
    {
        return ourManager;
    }
}
