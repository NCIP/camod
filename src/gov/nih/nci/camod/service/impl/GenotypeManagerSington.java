/**
 * 
 * $Id: GenotypeManagerSington.java,v 1.1 2006-10-17 16:13:47 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.GenotypeManager;

/**
 * @author pandyas
 * 
 * Singleton class for the GenotypeManagerSington
 */
public class GenotypeManagerSington {

	private static GenotypeManager ourManager = new GenotypeManagerImpl();

	/**
	 * @return the global instance of the GenotypeManager
	 */
	public static synchronized GenotypeManager instance() {
		return ourManager;
	}

}
