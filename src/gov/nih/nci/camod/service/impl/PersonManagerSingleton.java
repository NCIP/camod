/**
 * 
 * $Id: PersonManagerSingleton.java,v 1.3 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.PersonManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the PersonManager
 */
public class PersonManagerSingleton
{
    private static PersonManager ourManager = new PersonManagerImpl();

    /**
     * @return the global instance of the PersonManager
     */
    public static synchronized PersonManager instance()
    {
        return ourManager;
    }
}
