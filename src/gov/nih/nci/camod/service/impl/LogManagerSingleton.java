package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.LogManager;

/**
 * @author dgeorge
 * 
 * Singleton class for the LogManager
 */
public class LogManagerSingleton {

    private static LogManager ourManager = new LogManagerImpl();

    /**
     * @return the global instance of the LogManager
     */
    public static synchronized LogManager instance() {
        return ourManager;
    }
}

/*
 * $Log: not supported by cvs2svn $ Revision 1.1 2005/09/08 17:37:10
 * georgeda Initial revision
 * 
 */
