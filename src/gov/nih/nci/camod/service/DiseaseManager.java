/**
 * 
 * $Id: DiseaseManager.java,v 1.3 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Disease;


import java.util.List;

public interface DiseaseManager {
	
	public List getAll() throws Exception;
	
    public Disease getByName(String inName) throws Exception;
    
    public Disease getOrCreate(String inDiagnosisCode, String inDiseaseName) throws Exception;    

}
