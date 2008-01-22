/**
 * 
 * $Id: GeneIdentifierManagerSingleton.java,v 1.1 2008-01-22 18:38:15 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 *  
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.GeneIdentifierManager;


/**
 * @author pandyas
 * 
 * Singleton class for the GeneIdentifierManagerSingleton
 */
public class GeneIdentifierManagerSingleton
{
    private static GeneIdentifierManager ourManager = new GeneIdentifierManagerImpl();

    /**
     * @return the global instance of the GeneIdentifierManager
     */
    public static synchronized GeneIdentifierManager instance() {
        return ourManager;
    }
}
