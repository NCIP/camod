/**
 * 
 * $Id: StainingMethodManagerSingleton.java,v 1.1 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.StainingMethodManager;

public class StainingMethodManagerSingleton
{
    private static StainingMethodManager ourManager = new StainingMethodManagerImpl();

    /**
     * @return the global instance of the OrganManager
     */
    public static synchronized StainingMethodManager instance()
    {
        return ourManager;
    }
}
