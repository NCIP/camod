/**
 * $Id: CommentsManagerSingleton.java,v 1.2 2006-01-18 14:24:23 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/10/10 14:07:31  georgeda
 * Initial revision
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.CommentsManager;

/**
 * 
 * Singleton class for the CommentsManager
 */
public class CommentsManagerSingleton
{
    private static CommentsManager ourManager = new CommentsManagerImpl();

    /**
     * @return the global instance of the AnimalModelManager
     */
    public static synchronized CommentsManager instance()
    {
        return ourManager;
    }
}
