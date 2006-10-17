/**
 * $Id: TransInterferenceMethodManager.java,v 1.1 2006-10-17 16:14:18 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.TransientInterferenceMethod;
import java.util.List;

public interface TransInterferenceMethodManager {

	public List getAll() throws Exception;
	
    public TransientInterferenceMethod getByConceptCode(String inConceptCode) throws Exception;
 
	
}
