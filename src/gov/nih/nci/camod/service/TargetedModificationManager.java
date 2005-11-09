/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.webapp.form.TargetedModificationData;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface TargetedModificationManager {
	public List getAll() throws Exception;
	
	public TargetedModification get(String id) throws Exception;
	
	public void save(TargetedModification TargetedModification) throws Exception;
	
	public void remove(String id, AnimalModel inAnimalModel) throws Exception;
	
	public TargetedModification create(AnimalModel inAnimalModel, TargetedModificationData inTargetedModificationForm, HttpServletRequest request) throws Exception;
	
	public void update(AnimalModel inAnimalModel, TargetedModificationData inTargetedModificationData, TargetedModification theTargetedModification, HttpServletRequest request) throws Exception;	
}