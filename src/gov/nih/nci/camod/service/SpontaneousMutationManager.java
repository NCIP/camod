/**
 * 
 * $Id: SpontaneousMutationManager.java,v 1.5 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.SpontaneousMutation;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationData;

import java.util.List;

public interface SpontaneousMutationManager {
	
    public List getAll() throws Exception; 

    public SpontaneousMutation get(String id) throws Exception;

    public void save(SpontaneousMutation SpontaneousMutation) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;

    public SpontaneousMutation create(SpontaneousMutationData inSpontaneousMutationData) throws Exception;
    
    public void update(SpontaneousMutationData inSpontaneousMutationData, SpontaneousMutation inSpontaneousMutation) throws Exception;        

}

