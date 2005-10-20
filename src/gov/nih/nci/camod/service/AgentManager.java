/**
 * 
 * $Id: AgentManager.java,v 1.4 2005-10-20 20:41:24 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Agent;
import java.util.Collection;
import java.util.List;


public interface AgentManager {
	
	public Agent get(String id);
    public void save(Agent agent);
    public void remove(String id);
	public Collection getClinicalProtocols(Agent a);
	public List getYeastResults(Agent a);
}
