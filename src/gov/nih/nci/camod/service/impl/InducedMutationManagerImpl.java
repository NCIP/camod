/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.service.InducedMutationManager;
import gov.nih.nci.camod.webapp.form.InducedMutationForm;

import java.util.List;

public class InducedMutationManagerImpl extends BaseManager implements InducedMutationManager {
	
    public List getAll() throws Exception {
        log.trace("In InducedMutationManagerImpl.getAll");
        return super.getAll(InducedMutation.class);
    }

    public InducedMutation get(String id) throws Exception {
        log.trace("In InducedMutationManagerImpl.get");
        return (InducedMutation) super.get(id, InducedMutation.class);
    }

    public void save(InducedMutation InducedMutation) throws Exception {
        log.trace("In InducedMutationManagerImpl.save");
        super.save(InducedMutation);
    }

    public void remove(String id) throws Exception {
        log.trace("In InducedMutationManagerImpl.remove");
        super.remove(id, InducedMutation.class);
    }

    public InducedMutation create(InducedMutationForm inInducedMutationForm, AnimalModel inAnimalModel) throws Exception {

        log.trace("Entering InducedMutationManagerImpl.create");

        InducedMutation theInducedMutation = new InducedMutation();

        populateInducedMutation(inInducedMutationForm, theInducedMutation, inAnimalModel);
       
        log.trace("Exiting InducedMutationManagerImpl.create");
        
        return theInducedMutation;
    }

    public void update(InducedMutationForm inInducedMutationData, InducedMutation inInducedMutation, AnimalModel inAnimalModel)
            throws Exception {

        log.trace("Entering InducedMutationManagerImpl.update");
        log.debug("Updating InducedMutationForm: " + inInducedMutation.getId());

        // Populate w/ the new values and save
        populateInducedMutation(inInducedMutationData, inInducedMutation, inAnimalModel);
       // save(inInducedMutation);

        log.trace("Exiting InducedMutationManagerImpl.update");
    }

    private void populateInducedMutation(InducedMutationForm inInducedMutationData, InducedMutation inInducedMutation, AnimalModel inAnimalModel)
            throws Exception {
    	
        log.trace("Entering populateInducedMutation");
        	
        log.trace("Exiting populateInducedMutation");
    }
    
}
