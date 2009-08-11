/**
 * 
 * @author pandyas
 * 
 * $Id: DiseaseManagerSingleton.java,v 1.2 2006-01-18 14:24:24 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/11/03 18:54:29  pandyas
 * Modified for histopathology screens
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.DiseaseManager;

public class DiseaseManagerSingleton
{
    private static DiseaseManager ourManager = new DiseaseManagerImpl();

    /**
     * @return the global instance of the DiseaseManager
     */
    public static synchronized DiseaseManager instance()
    {
        return ourManager;
    }
}
