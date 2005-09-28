package gov.nih.nci.camod.service.impl;

public class GeneDeliveryManagerSingleton {

    private static GeneDeliveryManagerImpl ourManager = new GeneDeliveryManagerImpl();

    /**
     * @return the global instance of the GeneDelivery
     */
    public static synchronized GeneDeliveryManagerImpl instance() {
        return ourManager;
    }
}
