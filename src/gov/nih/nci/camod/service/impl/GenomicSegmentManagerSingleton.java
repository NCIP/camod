/**
 * 
 * $Id: GenomicSegmentManagerSingleton.java,v 1.3 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.GenomicSegmentManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the SexDistributionManager
 */
public class GenomicSegmentManagerSingleton
{
    private static GenomicSegmentManager ourManager = new GenomicSegmentManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized GenomicSegmentManager instance()
    {
        return ourManager;
    }
}
