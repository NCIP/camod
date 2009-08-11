/**
 * 
 * $Id: AssociatedExpressionManagerSingleton.java,v 1.3 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

public class AssociatedExpressionManagerSingleton
{
    private static AssociatedExpressionManagerImpl ourManager = new AssociatedExpressionManagerImpl();

    /**
     * @return the global instance of the AssociatedExpressionManager
     */
    public static synchronized AssociatedExpressionManagerImpl instance()
    {
        return ourManager;
    }
}
