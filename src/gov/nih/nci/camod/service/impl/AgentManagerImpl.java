/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.service.AgentManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AgentManagerImpl extends BaseManager implements AgentManager {
	
	public Agent get(String id) {
		Agent agent = null;
		
		try {
			agent = (Agent) Search.queryById(Agent.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AgentManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AgentManagerImpl.get");
			e.printStackTrace();
		}
		
		return agent;
    }

    public void save(Agent agent) {   
    	try {
			Persist.save(agent);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AgentManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AgentManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    
    	try {
			Persist.deleteById(Agent.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AgentManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AgentManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
