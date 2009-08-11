/**
 * 
 * $Id: AgentTargetManagerSingleton.java,v 1.3 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.AgentTargetManager;

/**
 * @author pandyas
 * 
 * Singleton class for the AgentTargetManager
 */
public class AgentTargetManagerSingleton
{
    private static AgentTargetManager ourManager = new AgentTargetManagerImpl();

    /**
     * @return the global instance of the AgentTargetManager
     */
    public static synchronized AgentTargetManager instance()
    {
        return ourManager;
    }
}
