package gov.nih.nci.camod.service.impl;

public class MicroArrayDataManagerSingleton
{
    private static MicroArrayDataManagerImpl ourManager = new MicroArrayDataManagerImpl();

    /**
     * @return the global instance of the MicroArrayData
     */
    public static synchronized MicroArrayDataManagerImpl instance()
    {
        return ourManager;
    }
}
