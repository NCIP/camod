/**
 *
 * @author pandyas
 * 
 * $Id: BiologicalProcessManager.java,v 1.4 2006-04-19 17:37:51 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/11/07 20:43:29  pandyas
 * modified getAll(), save(), rmove() and/or getByName (if applicable) to the current signature that throws exceptions
 *
 * Revision 1.2  2005/10/20 20:41:49  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.BiologicalProcess;

/**
 * @author pandyas
 */
public interface BiologicalProcessManager {
	
    public BiologicalProcess getByName(String inName) throws Exception;

}
