/**
 * 
 * $Id: GeneDeliveryManagerSingleton.java,v 1.2 2005-10-20 20:24:00 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

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
