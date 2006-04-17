/**
 * 
 * $Id: OrganManagerSingleton.java,v 1.5 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.OrganManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the OrganManager
 */
public class OrganManagerSingleton
{
    private static OrganManager ourManager = new OrganManagerImpl();

    /**
     * @return the global instance of the OrganManager
     */
    public static synchronized OrganManager instance()
    {
        return ourManager;
    }
}
