/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *
 * @author pandyas
 * 
 * $Id: AnimalDistributorManagerSingleton.java,v 1.2 2006-01-18 14:24:23 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/26 20:14:52  pandyas
 * implemented model availability
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.AnimalDistributorManager;

public class AnimalDistributorManagerSingleton
{
    private static AnimalDistributorManager ourManager = new AnimalDistributorManagerImpl();

    /**
     * @return the global instance of the AnimalDistributorManager
     */
    public static synchronized AnimalDistributorManager instance()
    {
        return ourManager;
    }
}
