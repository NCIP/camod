/**
 * 
 * $Id: TargetedModificationManagerSingleton.java,v 1.2 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.TargetedModificationManager;

public class TargetedModificationManagerSingleton {

    private static TargetedModificationManager ourManager = new TargetedModificationManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized TargetedModificationManager instance() {
        return ourManager;
    }
}


