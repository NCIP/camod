/**
 * 
 * $Id: AgentManager.java,v 1.5 2005-11-07 20:43:29 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2005/10/20 20:41:24  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Agent;
import java.util.Collection;
import java.util.List;


public interface AgentManager {
	
	public Agent get(String id) throws Exception;
    public void save(Agent agent) throws Exception;
    public void remove(String id) throws Exception;
	public Collection getClinicalProtocols(Agent a);
	public List getYeastResults(Agent a);
}
