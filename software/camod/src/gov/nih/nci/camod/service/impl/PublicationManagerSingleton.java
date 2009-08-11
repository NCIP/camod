/**
 * 
 * $Id: PublicationManagerSingleton.java,v 1.3 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.PublicationManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the PublicationManager
 */
public class PublicationManagerSingleton
{
    private static PublicationManager ourManager = new PublicationManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized PublicationManager instance()
    {
        return ourManager;
    }
}
