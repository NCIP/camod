/**
 * 
 * $Id: CellLineManagerSingleton.java,v 1.2 2005-10-20 20:29:38 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

public class CellLineManagerSingleton {

    private static CellLineManagerImpl ourManager = new CellLineManagerImpl();

    /**
     * @return the global instance of the CellLine
     */
    public static synchronized CellLineManagerImpl instance() {
        return ourManager;
    }	
	
}
