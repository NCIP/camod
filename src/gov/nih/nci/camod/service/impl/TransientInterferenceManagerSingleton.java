/**
 * @author pandyas
 * 
 * $Id: TransientInterferenceManagerSingleton.java,v 1.1 2006-10-17 16:14:05 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/05/03 20:04:04  pandyas
 * Modified to add Morpholino object data to application
 *
 *
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.TransientInterferenceManager;

public class TransientInterferenceManagerSingleton {
	private static TransientInterferenceManager ourManager = new TransientInterferenceManagerImpl();

	/**
	 * @return the global instance of the TransientInterferenceManager
	 */
	public static synchronized TransientInterferenceManager instance() {
		return ourManager;
	}
}
