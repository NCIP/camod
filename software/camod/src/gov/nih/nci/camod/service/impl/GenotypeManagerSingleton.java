/**
 * 
 * $Id: GenotypeManagerSingleton.java,v 1.1 2007-02-23 21:27:00 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/10/17 16:13:47  pandyas
 * modified during development of caMOD 2.2 - various
 *
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
public class GenotypeManagerSingleton {

	private static GenotypeManager ourManager = new GenotypeManagerImpl();

	/**
	 * @return the global instance of the GenotypeManager
	 */
	public static synchronized GenotypeManager instance() {
		return ourManager;
	}

}
