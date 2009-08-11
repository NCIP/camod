/**
 * 
 * $Id: SexDistributionManagerSingleton.java,v 1.2 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.SexDistributionManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the SexDistributionManager
 */
public class SexDistributionManagerSingleton {

    private static SexDistributionManager ourManager = new SexDistributionManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized SexDistributionManager instance() {
        return ourManager;
    }
}
