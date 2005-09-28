package gov.nih.nci.camod.service.impl;

public class TaxonManagerSingleton {

    private static TaxonManagerImpl ourManager = new TaxonManagerImpl();

    /**
     * @return the global instance of the TaxonManager
     */
    public static synchronized TaxonManagerImpl instance() {
        return ourManager;
    }
}
