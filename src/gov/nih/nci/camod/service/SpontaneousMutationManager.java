package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.SpontaneousMutation;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationForm;

import java.util.List;

public interface SpontaneousMutationManager {
	
    public List getAll() throws Exception; 

    public SpontaneousMutation get(String id) throws Exception;

    public void save(SpontaneousMutation SpontaneousMutation) throws Exception;

    public void remove(String id) throws Exception;

    public SpontaneousMutation create(SpontaneousMutationForm inSpontaneousMutationData, AnimalModel inAnimalModel) throws Exception;
    
    public void update(SpontaneousMutationForm inSpontaneousMutationData, SpontaneousMutation inSpontaneousMutation, AnimalModel inAnimalModel) throws Exception;        

}

