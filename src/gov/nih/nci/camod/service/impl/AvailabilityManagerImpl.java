package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalAvailability;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AvailabilityManager;
import gov.nih.nci.camod.webapp.form.AvailabilityData;
import java.util.List;

public class AvailabilityManagerImpl extends BaseManager implements AvailabilityManager {

    public List getAll() throws Exception {
        log.trace("In AvailabilityManagerImpl.getAll");
        return super.getAll(AnimalAvailability.class);
    }

    public AnimalAvailability get(String id) throws Exception {
        log.trace("In AvailabilityManagerImpl.get");
        return (AnimalAvailability) super.get(id, AnimalAvailability.class);
    }
    public void save(AnimalAvailability availability) throws Exception {
        log.trace("In AvailabilityManagerImpl.save");
        super.save(availability);
    }

    public void remove(String id) throws Exception {
        log.trace("In AvailabilityManagerImpl.remove");
        super.remove(id, AnimalAvailability.class);
    }
    
    public AnimalAvailability create(AvailabilityData inAvailabilityData, AnimalModel inAnimalModel) 
    throws Exception {
  
        log.trace("Entering AvailabilityManagerImpl.create");

        AnimalAvailability theAvailability = new AnimalAvailability();

        log.trace("Exiting AvailabilityManagerImpl.create");
        populateAvailability(inAvailabilityData, theAvailability, inAnimalModel);

        return theAvailability; 
    }

    public void update(AvailabilityData inAvailabilityData, AnimalAvailability inAvailability, AnimalModel inAnimalModel) 
    throws Exception {

        log.debug("Entering AvailabilityManagerImpl.update");
        log.debug("Updating AvailabilityData: " + inAvailability.getId());

        // Populate w/ the new values and save
        populateAvailability(inAvailabilityData, inAvailability, inAnimalModel);
        save(inAvailability);

        log.debug("Exiting AvailabilityManagerImpl.update");       	
    }
    
    private void populateAvailability(AvailabilityData inAvailabilityData, AnimalAvailability inAvailability, AnimalModel inAnimalModel)
    throws Exception {

    	log.debug("Entering populateAvailability");
    	
    	
    	
    	inAvailability.setName(inAvailabilityData.getName());
    	inAvailability.setStockNumber(inAvailabilityData.getStockNumber());    	

        log.debug("Exiting populateAvailability");    	
    }
   
}
