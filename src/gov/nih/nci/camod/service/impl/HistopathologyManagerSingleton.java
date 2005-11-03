/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyManagerSingleton.java,v 1.1 2005-11-03 18:54:29 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.HistopathologyManager;

public class HistopathologyManagerSingleton {

    private static HistopathologyManager ourManager = new HistopathologyManagerImpl();

    /**
     * @return the global instance of the HistopathologyManager
     */
    public static synchronized HistopathologyManager instance() {
        return ourManager;
    }	
	
}
