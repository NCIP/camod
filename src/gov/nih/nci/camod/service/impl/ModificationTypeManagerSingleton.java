/**
 * 
 * $Id: ModificationTypeManagerSingleton.java,v 1.3 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.ModificationTypeManager;

/**
 * 
 * Singleton class for the ModificationTypeManager
 */
public class ModificationTypeManagerSingleton
{
    private static ModificationTypeManager ourManager = new ModificationTypeManagerImpl();

    /**
     * @return the global instance of the ModificationTypeManager
     */
    public static synchronized ModificationTypeManager instance()
    {
        return ourManager;
    }
}
