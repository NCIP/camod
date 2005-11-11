/**
 * 
 * $Id: XenograftManagerSingleton.java,v 1.2 2005-11-11 16:27:53 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service.impl;

/**
 * @author dgeorge
 * 
 * Singleton class for the XenograftManagerImpl
 */
public class XenograftManagerSingleton {

    private static XenograftManagerImpl ourManager = new XenograftManagerImpl();

    /**
     * @return the global instance of the XenograftManager
     */
    public static synchronized XenograftManagerImpl instance() {
        return ourManager;
    }
}
