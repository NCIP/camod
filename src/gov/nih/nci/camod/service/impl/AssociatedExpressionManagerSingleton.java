package gov.nih.nci.camod.service.impl;

public class AssociatedExpressionManagerSingleton
{
    private static AssociatedExpressionManagerImpl ourManager = new AssociatedExpressionManagerImpl();

    /**
     * @return the global instance of the AssociatedExpressionManager
     */
    public static synchronized AssociatedExpressionManagerImpl instance()
    {
        return ourManager;
    }
}
