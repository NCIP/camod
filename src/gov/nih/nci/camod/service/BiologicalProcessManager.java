/**
 *
 * @author pandyas
 * 
 * $Id: BiologicalProcessManager.java,v 1.2 2005-10-20 20:41:49 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

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
