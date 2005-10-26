/**
 *
 * @author pandyas
 * 
 * $Id: AvailabilityManager.java,v 1.2 2005-10-26 20:15:43 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalAvailability;
import gov.nih.nci.camod.domain.AnimalDistributor;
import gov.nih.nci.camod.webapp.form.AvailabilityData;
import java.util.List;

public interface AvailabilityManager {
	
    public List getAll() throws Exception;

    public AnimalAvailability get(String id) throws Exception;

    public void save(AnimalAvailability availability) throws Exception;
    
    public void saveAnimalDistributor( AnimalDistributor animalDistributor );    

    public void remove(String id) throws Exception;

    public AnimalAvailability create(AvailabilityData inAvailabilityData) throws Exception;
    
    public AnimalAvailability createInvestigator(AvailabilityData inAvailabilityData) 
    throws Exception;    

    public void update(AvailabilityData inAvailabilityData, AnimalAvailability inAvailability)
            throws Exception;	
    
    public void updateInvestigatorAvailability(AvailabilityData inAvailabilityData, AnimalAvailability inAvailability)
    throws Exception;

}
