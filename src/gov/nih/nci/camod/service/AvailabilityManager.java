/**
 *
 * @author pandyas
 * 
 * $Id: AvailabilityManager.java,v 1.4 2005-11-09 00:17:05 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/10/28 17:42:18  georgeda
 * Removed unneeded methods
 *
 * Revision 1.2  2005/10/26 20:15:43  pandyas
 * implemented model availability
 *
 * 
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalAvailability;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.webapp.form.AvailabilityData;

import java.util.List;

public interface AvailabilityManager {
	
    public List getAll() throws Exception;

    public AnimalAvailability get(String id) throws Exception;

    public void save(AnimalAvailability availability) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;

    public AnimalAvailability create(AvailabilityData inAvailabilityData) throws Exception;
    
    public AnimalAvailability createInvestigator(AvailabilityData inAvailabilityData) 
    throws Exception;    

    public void update(AvailabilityData inAvailabilityData, AnimalAvailability inAvailability)
            throws Exception;	
    
    public void updateInvestigatorAvailability(AvailabilityData inAvailabilityData, AnimalAvailability inAvailability)
    throws Exception;

}
