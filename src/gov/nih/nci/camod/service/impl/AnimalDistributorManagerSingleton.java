/**
 *
 * @author pandyas
 * 
 * $Id: AnimalDistributorManagerSingleton.java,v 1.1 2005-10-26 20:14:52 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.AnimalDistributorManager;

public class AnimalDistributorManagerSingleton {
	
    private static AnimalDistributorManager ourManager = new AnimalDistributorManagerImpl();

    /**
     * @return the global instance of the AnimalDistributorManager
     */
    public static synchronized AnimalDistributorManager instance() {
        return ourManager;
    }	

}
