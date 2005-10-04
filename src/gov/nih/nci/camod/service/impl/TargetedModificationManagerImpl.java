/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.service.TargetedModificationManager;
import gov.nih.nci.camod.webapp.form.TargetedModificationForm;

import java.util.List;

public class TargetedModificationManagerImpl extends BaseManager implements TargetedModificationManager {
	
	  public List getAll() throws Exception {
	        log.trace("In TargetedModificationManagerImpl.getAll");
	        return super.getAll(TargetedModification.class);
	    }

	    public TargetedModification get(String id) throws Exception {
	        log.trace("In TargetedModificationManagerImpl.get");
	        return (TargetedModification) super.get(id, TargetedModification.class);
	    }

	    public void save(TargetedModification TargetedModification) throws Exception {
	        log.trace("In TargetedModificationManagerImpl.save");
	        super.save(TargetedModification);
	    }

	    public void remove(String id) throws Exception {
	        log.trace("In TargetedModificationManagerImpl.remove");
	        super.remove(id, TargetedModification.class);
	    }

	    public EngineeredGene create(TargetedModificationForm inTargetedModificationForm, AnimalModel inAnimalModel) throws Exception {

	        log.trace("Entering TargetedModificationManagerImpl.create");

	        EngineeredGene theEngineeredGene = new TargetedModification();

	        populateTargetedModification(inTargetedModificationForm, theEngineeredGene, inAnimalModel);
	       
	        log.trace("Exiting TargetedModificationManagerImpl.create");
	        
	        return theEngineeredGene;
	    }

	    public void update(TargetedModificationForm inTargetedModificationData, EngineeredGene inEngineeredGene, AnimalModel inAnimalModel)
	            throws Exception {

	        log.trace("Entering TargetedModificationManagerImpl.update");
	        log.debug("Updating TargetedModificationForm: " + inEngineeredGene.getId());

	        // Populate w/ the new values and save
	        populateTargetedModification(inTargetedModificationData, inEngineeredGene, inAnimalModel);
	       // save(inTargetedModification);

	        log.trace("Exiting TargetedModificationManagerImpl.update");
	    }

	    private void populateTargetedModification(TargetedModificationForm inTargetedModificationData, EngineeredGene inEngineeredGene, AnimalModel inAnimalModel)
	            throws Exception {
	    	
	        log.trace("Entering populateTargetedModification");
	        	
	        log.trace("Exiting populateTargetedModification");
	    }
	    
	}