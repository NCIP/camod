/**
 * @author pandyas
 * 
 * $Id: TransIntMethodManagerSingleton.java,v 1.1 2006-10-17 16:14:05 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/05/03 20:04:04  pandyas
 * Modified to add Morpholino object data to application
 *
 *
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.TransInterferenceMethodManager;

public class TransIntMethodManagerSingleton {
	private static TransInterferenceMethodManager ourManager = new TransInterferenceMethodManagerImpl();

	/**
	 * @return the global instance of the TransientInterferenceMethodManager
	 */
	public static synchronized TransInterferenceMethodManager instance() {
		return ourManager;
	}
}
