package gov.nih.nci.camod.service.impl;

public class AvailabilityManagerSingleton
{
    private static AvailabilityManagerImpl ourManager = new AvailabilityManagerImpl();

    /**
     * @return the global instance of the AvailabilityManager
     */
    public static synchronized AvailabilityManagerImpl instance()
    {
        return ourManager;
    }
}
