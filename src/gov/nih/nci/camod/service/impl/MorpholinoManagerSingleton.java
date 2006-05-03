/**
 * @author pandyas
 * 
 * $Id: MorpholinoManagerSingleton.java,v 1.1 2006-05-03 20:04:04 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.MorpholinoManager;

public class MorpholinoManagerSingleton
{
    private static MorpholinoManager ourManager = new MorpholinoManagerImpl();

    /**
     * @return the global instance of the MorpholinoManager
     */
    public static synchronized MorpholinoManager instance()
    {
        return ourManager;
    }
}
