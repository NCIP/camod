/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.webapp.form.TargetedModificationForm;

import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface TargetedModificationManager {
	public List getAll() throws Exception;
	public TargetedModification get(String id) throws Exception;
	public void save(TargetedModification TargetedModification) throws Exception;
	public void remove(String id) throws Exception;
	public EngineeredGene create(TargetedModificationForm inTargetedModificationForm, AnimalModel inAnimalModel) throws Exception;
	public void update(TargetedModificationForm inTargetedModificationData,  EngineeredGene inEngineeredGene, AnimalModel inAnimalModel) throws Exception;	
}