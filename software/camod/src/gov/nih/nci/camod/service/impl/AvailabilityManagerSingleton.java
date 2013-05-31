/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: AvailabilityManagerSingleton.java,v 1.3 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

public class AvailabilityManagerSingleton
{
    private static AvailabilityManagerImpl ourManager = new AvailabilityManagerImpl();

    /**
     * @return the global instance of the AvailabilityManager
     */
    public static synchronized AvailabilityManagerImpl instance()
    {
        return ourManager;
    }
}
