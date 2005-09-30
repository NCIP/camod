package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.BiologicalProcess;

/**
 * @author pandyas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface BiologicalProcessManager {
	
    public BiologicalProcess getByName(String inName);

}
