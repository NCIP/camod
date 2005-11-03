/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyManager.java,v 1.1 2005-11-03 18:54:43 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.webapp.form.AssociatedMetastasisData;
import gov.nih.nci.camod.webapp.form.ClinicalMarkerData;
import gov.nih.nci.camod.webapp.form.HistopathologyData;
import java.util.List;

public interface HistopathologyManager {

    public List getAll() throws Exception;
    
    public Histopathology get(String id) throws Exception;
    
    public Histopathology createHistopathology(HistopathologyData inHistopathologyData)throws Exception;
    
    public void createAssociatedMetastasis(AssociatedMetastasisData inAssociatedMetastasisData, Histopathology inHistopathology)throws Exception;
    
    public Histopathology createClinicalMarker(ClinicalMarkerData inClinicalMarkerData)throws Exception;    
    
    public void updateHistopathology(HistopathologyData inHistopathologyData, Histopathology theHistopathology) throws Exception;
    
    public void updateAssociatedMetastasis(AssociatedMetastasisData inAssociatedMetastasisData, Histopathology inHistopathology, String inAssociatedMetastasisID) throws Exception;    

    public void updateClinicalMarker(ClinicalMarkerData inClinicalMarkerData, Histopathology inHistopathology) throws Exception;    
    
    public void remove(String id) throws Exception; 
    
    public void save(Histopathology histopathology) throws Exception;

}
