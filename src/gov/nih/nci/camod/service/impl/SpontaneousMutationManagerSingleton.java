/**
 * 
 * $Id: SpontaneousMutationManagerSingleton.java,v 1.2 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.SpontaneousMutationManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the SexDistributionManager
 */
public class SpontaneousMutationManagerSingleton {

    private static SpontaneousMutationManager ourManager = new SpontaneousMutationManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized SpontaneousMutationManager instance() {
        return ourManager;
    }
}


