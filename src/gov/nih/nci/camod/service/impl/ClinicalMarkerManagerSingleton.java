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
 * $Id: ClinicalMarkerManagerSingleton.java,v 1.1 2005-11-07 19:15:17 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.ClinicalMarkerManager;

public class ClinicalMarkerManagerSingleton {
	
    private static ClinicalMarkerManager ourManager = new ClinicalMarkerManagerImpl();

    /**
     * @return the global instance of the ClinicalMarkerManager
     */
    public static synchronized ClinicalMarkerManager instance() {
        return ourManager;
    }	

}
