/*
 * $Id: InducedMutationManager.java,v 1.7 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.webapp.form.InducedMutationData;

import java.util.List;

public interface InducedMutationManager {
	public List getAll() throws Exception;
	
	public InducedMutation get(String id) throws Exception;
	
	public void save(InducedMutation InducedMutation) throws Exception;
	
	public void remove(String id, AnimalModel inAnimalModel) throws Exception;
	
	public InducedMutation create(AnimalModel inAnimalModel, InducedMutationData inInducedMutationData) throws Exception;
	
	public void update(AnimalModel inAnimalModel, InducedMutationData inInducedMutationData, InducedMutation inInducedMutation) throws Exception;
}

