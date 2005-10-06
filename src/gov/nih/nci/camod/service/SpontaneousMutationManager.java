package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.SpontaneousMutation;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationData;

import java.util.List;

public interface SpontaneousMutationManager {
	
    public List getAll() throws Exception; 

    public SpontaneousMutation get(String id) throws Exception;

    public void save(SpontaneousMutation SpontaneousMutation) throws Exception;

    public void remove(String id) throws Exception;

    public SpontaneousMutation create(SpontaneousMutationData inSpontaneousMutationData) throws Exception;
    
    public void update(SpontaneousMutationData inSpontaneousMutationData, SpontaneousMutation inSpontaneousMutation) throws Exception;        

}

