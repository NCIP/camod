package gov.nih.nci.camod.service.impl;

public class EngineeredTransgeneManagerSingleton
{
    private static EngineeredTransgeneManagerImpl ourManager = new EngineeredTransgeneManagerImpl();

    /**
     * @return the global instance of the GeneDelivery
     */
    public static synchronized EngineeredTransgeneManagerImpl instance()
    {
        return ourManager;
    }
}
