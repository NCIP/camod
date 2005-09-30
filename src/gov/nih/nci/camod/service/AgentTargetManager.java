package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AgentTarget;

/**
 * @author pandyas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface AgentTargetManager {
    public AgentTarget getByName(String inName);	
	
}
