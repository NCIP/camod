/**
 * 
 * $Id: RegulatoryElementTypeManagerSingleton.java,v 1.2 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.RegulatoryElementTypeManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the SexDistributionManager
 */
public class RegulatoryElementTypeManagerSingleton {

    private static RegulatoryElementTypeManager ourManager = new RegulatoryElementTypeManagerImpl();

    /**
     * @return the global instance of the RegulatoryElementTypeManager
     */
    public static synchronized RegulatoryElementTypeManager instance() {
        return ourManager;
    }
}
