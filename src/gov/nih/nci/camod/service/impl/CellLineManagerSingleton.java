package gov.nih.nci.camod.service.impl;

public class CellLineManagerSingleton {

    private static CellLineManagerImpl ourManager = new CellLineManagerImpl();

    /**
     * @return the global instance of the GeneDelivery
     */
    public static synchronized CellLineManagerImpl instance() {
        return ourManager;
    }	
	
}
