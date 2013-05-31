/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *
 * $Id: OrganManagerSingleton.java,v 1.6 2006-05-08 13:33:28 georgeda Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.OrganManager;

/**
 * Singleton class for the OrganManager
 */
public class OrganManagerSingleton
{
    private static OrganManager ourManager = new OrganManagerImpl();

    /**
     * @return the global instance of the OrganManager
     */
    public static synchronized OrganManager instance()
    {
        return ourManager;
    }
}
