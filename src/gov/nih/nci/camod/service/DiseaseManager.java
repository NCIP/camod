package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Disease;
import java.util.List;

public interface DiseaseManager {
	
	public List getAll() throws Exception;
	
    public Disease getByName(String inName) throws Exception;	

}
