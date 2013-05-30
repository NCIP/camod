/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *
 * $Id: DevelopmentalStageManagerSingleton.java,v 1.1 2007-05-16 12:29:10 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.DevelopmentalStageManager;

/**
 * Singleton class for the DevelopmentalStageManager
 */
public class DevelopmentalStageManagerSingleton {

	
    private static DevelopmentalStageManager ourManager = new DevelopmentalStageManagerImpl();

    /**
     * @return the global instance of the OrganManager
     */
    public static synchronized DevelopmentalStageManager instance()
    {
        return ourManager;
    }
    
}
