/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * @author pandyas
 * 
 * $Id: ChemicalClassManagerSingleton.java,v 1.3 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.ChemicalClassManager;

/**
 * @author pandyas
 * 
 * Singleton class for the SexDistributionManager
 */

public class ChemicalClassManagerSingleton
{
    private static ChemicalClassManager ourManager = new ChemicalClassManagerImpl();

    /**
     * @return the global instance of the ChemicalClassManager
     */
    public static synchronized ChemicalClassManager instance()
    {
        return ourManager;
    }
}
