/**
 * 
 * $Id: StrainManagerSingleton.java,v 1.1 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.StrainManager;

public class StrainManagerSingleton
{
    private static StrainManager ourManager = new StrainManagerImpl();

    /**
     * @return the global instance of the OrganManager
     */
    public static synchronized StrainManager instance()
    {
        return ourManager;
    }
}
