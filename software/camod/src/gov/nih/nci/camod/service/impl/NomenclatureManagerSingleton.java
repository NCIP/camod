/**
 * 
 * $Id: NomenclatureManagerSingleton.java,v 1.1 2006-10-17 16:13:46 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.NomenclatureManager;

/**
 * @author pandyas
 * 
 * interface for the NomenclatureManager
 */
public class NomenclatureManagerSingleton {

	private static NomenclatureManager ourManager = new NomenclatureManagerImpl();

	/**
	 * @return the global instance of the NomenclatureManager
	 */
	public static synchronized NomenclatureManager instance() {
		return ourManager;
	}

}
