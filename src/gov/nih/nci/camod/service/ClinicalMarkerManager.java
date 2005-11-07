package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.ClinicalMarker;
import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.webapp.form.ClinicalMarkerData;

/**
 * See implementing classes for details
 */
public interface ClinicalMarkerManager {
    
    public ClinicalMarker get(String id) throws Exception;
    
    public void save(ClinicalMarker clinicalMarker) throws Exception; 
    
    public void create(ClinicalMarkerData inClinicalMarkerData, Histopathology inHistopathology)throws Exception;

    public void update(ClinicalMarkerData inClinicalMarkerData, ClinicalMarker inClinicalMarker)
    throws Exception; 
    
    public void remove(String id) throws Exception;    
}
