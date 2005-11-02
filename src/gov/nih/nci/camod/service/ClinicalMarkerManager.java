package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.ClinicalMarker;

import java.util.List;

public interface ClinicalMarkerManager {
	
    public List getAll() throws Exception;
    
    public ClinicalMarker get(String id) throws Exception;

    public void save(ClinicalMarker clinicalMarker) throws Exception;

    //public void updateAndAddLog(ClinicalMarker inClinicalMarker, Log inLog) throws Exception;

    //public void update(ClinicalMarkerData inClinicalMarkerData, ClinicalMarker inClinicalMarker) throws Exception;

    public void remove(String id) throws Exception;

}
