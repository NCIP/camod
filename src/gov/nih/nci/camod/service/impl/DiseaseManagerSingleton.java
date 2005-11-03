/**
 * 
 * @author pandyas
 * 
 * $Id: DiseaseManagerSingleton.java,v 1.1 2005-11-03 18:54:29 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.DiseaseManager;

public class DiseaseManagerSingleton {
	
		
	private static DiseaseManager ourManager = new DiseaseManagerImpl();

	/**
	* @return the global instance of the DiseaseManager
	*/
	public static synchronized DiseaseManager instance() {
	   return ourManager;
	}	
	

}
