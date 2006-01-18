/**
 * @author pandyas
 * 
 * $Id: ClinicalMarkerManagerSingleton.java,v 1.2 2006-01-18 14:24:24 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/11/07 19:15:17  pandyas
 * modified for clinical marker screen
 *
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.ClinicalMarkerManager;

public class ClinicalMarkerManagerSingleton
{
    private static ClinicalMarkerManager ourManager = new ClinicalMarkerManagerImpl();

    /**
     * @return the global instance of the ClinicalMarkerManager
     */
    public static synchronized ClinicalMarkerManager instance()
    {
        return ourManager;
    }
}
