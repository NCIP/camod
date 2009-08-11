/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyManagerSingleton.java,v 1.2 2006-01-18 14:24:24 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/11/03 18:54:29  pandyas
 * Modified for histopathology screens
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.HistopathologyManager;

public class HistopathologyManagerSingleton
{
    private static HistopathologyManager ourManager = new HistopathologyManagerImpl();

    /**
     * @return the global instance of the HistopathologyManager
     */
    public static synchronized HistopathologyManager instance()
    {
        return ourManager;
    }
}
