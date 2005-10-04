/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.SpontaneousMutationManager;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationForm;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;

public class SpontaneousMutationManagerImpl extends BaseManager implements SpontaneousMutationManager {

    public List getAll() throws Exception {
        log.trace("In SpontaneousMutationManagerImpl.getAll");
        return super.getAll(SpontaneousMutation.class);
    }

    public SpontaneousMutation get(String id) throws Exception {
        log.trace("In SpontaneousMutationManagerImpl.get");
        return (SpontaneousMutation) super.get(id, SpontaneousMutation.class);
    }

    public void save(SpontaneousMutation SpontaneousMutation) throws Exception {
        log.trace("In SpontaneousMutationManagerImpl.save");
        super.save(SpontaneousMutation);
    }

    public void remove(String id) throws Exception {
        log.trace("In SpontaneousMutationManagerImpl.remove");
        super.remove(id, SpontaneousMutation.class);
    }

    public SpontaneousMutation create(SpontaneousMutationForm inSpontaneousMutationData, AnimalModel inAnimalModel) throws Exception {

        log.trace("Entering SpontaneousMutationManagerImpl.create");

        SpontaneousMutation theSpontaneousMutation = new SpontaneousMutation();

        log.trace("Exiting SpontaneousMutationManagerImpl.create");
        
        populateSpontaneousMutation(inSpontaneousMutationData, theSpontaneousMutation, inAnimalModel);

        return theSpontaneousMutation;
    }

    public void update(SpontaneousMutationForm inSpontaneousMutationData, SpontaneousMutation inSpontaneousMutation, AnimalModel inAnimalModel)
            throws Exception {

        log.trace("Entering SpontaneousMutationManagerImpl.update");
        log.debug("Updating SpontaneousMutationForm: " + inSpontaneousMutation.getId());

        // Populate w/ the new values and save
        populateSpontaneousMutation(inSpontaneousMutationData, inSpontaneousMutation, inAnimalModel);
       // save(inSpontaneousMutation);

        log.trace("Exiting SpontaneousMutationManagerImpl.update");
    }

    private void populateSpontaneousMutation(SpontaneousMutationForm inSpontaneousMutationData, SpontaneousMutation inSpontaneousMutation, AnimalModel inAnimalModel)
            throws Exception {
    	
        log.trace("Entering populateSpontaneousMutation");
        	
        log.trace("Exiting populateSpontaneousMutation");
    }
}
