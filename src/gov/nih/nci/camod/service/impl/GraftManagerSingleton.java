/**
 * 
 * $Id: GraftManagerSingleton.java,v 1.1 2007-07-31 12:02:21 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2005/11/11 16:27:53  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

/**
 * @author dgeorge
 * 
 * Singleton class for the XenograftManagerImpl
 */
public class GraftManagerSingleton {

    private static GraftManagerImpl ourManager = new GraftManagerImpl();

    /**
     * @return the global instance of the GraftManager
     */
    public static synchronized GraftManagerImpl instance() {
        return ourManager;
    }
}
