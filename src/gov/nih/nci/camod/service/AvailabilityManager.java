package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalAvailability;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.webapp.form.AvailabilityData;

import java.util.List;

public interface AvailabilityManager {
	
    public List getAll() throws Exception;

    public AnimalAvailability get(String id) throws Exception;

    public void save(AnimalAvailability availability) throws Exception;

    public void remove(String id) throws Exception;

    public AnimalAvailability create(AvailabilityData inAvailabilityData, AnimalModel inAnimalModel) throws Exception;

    public void update(AvailabilityData inAvailabilityData, AnimalAvailability inAvailability, AnimalModel inAnimalModel)
            throws Exception;	

}
