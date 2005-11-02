package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.ClinicalMarker;
import gov.nih.nci.camod.domain.ModificationType;
import gov.nih.nci.camod.service.ClinicalMarkerManager;
import gov.nih.nci.camod.webapp.form.ClinicalMarkerData;
import java.util.List;

public class ClinicalMarkerManagerImpl extends BaseManager implements ClinicalMarkerManager {

    /**
     * Get all of the clinical markers in the DB
     * 
     * @return the list of all clinical markers
     * 
     * @exception throws
     *                an Exception if an error occurred
     */
    public List getAll() throws Exception {
        log.info("In ClinicalMarkerManagerImpl.getAll");
        return super.getAll(ClinicalMarker.class);
    }	
	
    /**
     * Get a specific clinical marker
     * 
     * @param id
     *            The unique id for the clinical marker
     * 
     * @return the clinical marker if found, null otherwise
     * @throws Exception
     * 
     * @exception Exception
     *                if an error occurred
     */
    public ClinicalMarker get(String id) throws Exception {
        log.info("In ClinicalMarkerManagerImpl.get");
        return (ClinicalMarker) super.get(id, ClinicalMarker.class);
    }    

    /**
     * Save a clinical marker
     * 
     * @param id
     *            The unique id for the clinical marker
     * 
     * @exception Exception
     *                if an error occurred
     */
    public void save(ClinicalMarker clinicalMarker) throws Exception {
        log.info("In ClinicalMarkerManagerImpl.save");
        super.save(clinicalMarker);
    }    

    /**
     * Remove the clinical marker from the system. 
     * 
     * @param id
     *            The unique id of the clinical marker to delete
     * 
     * @throws Exception
     *             An error occurred when attempting to delete the clinical marker
     */
    public void remove(String id) throws Exception {
        log.trace("In ClinicalMarkerManagerImpl.save");
        super.remove(id, ClinicalMarker.class);
    }
    
    public ClinicalMarker create(ClinicalMarkerData inClinicalMarkerData)throws Exception{

        log.info("In ClinicalMarkerManagerImpl.create");

        ClinicalMarker theClinicalMarker = new ClinicalMarker();
        populateClinicalMarker(inClinicalMarkerData, theClinicalMarker);

        return theClinicalMarker;    	
    }    

    public void update(ClinicalMarkerData inClinicalMarkerData, ClinicalMarker inClinicalMarker) throws Exception{

    	log.info("Entering HistopathologyManagerImpl.update");
    	log.info("Updating ClinicalMarkerData: " + inClinicalMarker.getId());

    	// Populate w/ the new values and save
    	populateClinicalMarker(inClinicalMarkerData, inClinicalMarker);
    	save(inClinicalMarker);

    	log.info("Exiting HistopathologyManagerImpl.update");    	
    } 
    
    private void populateClinicalMarker(ClinicalMarkerData inClinicalMarkerData, ClinicalMarker inClinicalMarker) {

		log.info( "<HistopathologyManagerImpl> Entering populateClinicalMarker" );

	
		
    }    
}
