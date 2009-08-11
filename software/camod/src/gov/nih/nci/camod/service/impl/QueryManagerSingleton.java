/**
 * 
 * $Id: QueryManagerSingleton.java,v 1.2 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;


/**
 * @author dgeorge
 * 
 * Singleton class for the QueryManagerImpl
 */
public class QueryManagerSingleton {

    private static QueryManagerImpl ourManager = new QueryManagerImpl();

    /**
     * @return the global instance of the SexDistributionManager
     */
    public static synchronized QueryManagerImpl instance() {
        return ourManager;
    }
}
