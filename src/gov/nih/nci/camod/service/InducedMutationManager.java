/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.webapp.form.InducedMutationForm;

import java.util.List;

public interface InducedMutationManager {
	public List getAll() throws Exception;
	public InducedMutation get(String id) throws Exception;
	public void save(InducedMutation InducedMutation) throws Exception;
	public void remove(String id) throws Exception;
	public InducedMutation create(InducedMutationForm inInducedMutationData, AnimalModel inAnimalModel) throws Exception;
	public void update(InducedMutationForm inInducedMutationData, InducedMutation inInducedMutation, AnimalModel inAnimalModel) throws Exception;
}

