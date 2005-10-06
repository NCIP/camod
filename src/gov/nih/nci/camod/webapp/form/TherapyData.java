/**
 *  @author pandyas
 *  
 *  $Id: TherapyData.java,v 1.1 2005-10-06 19:27:25 pandyas Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.1  2005/09/26 15:52:58  pandyas
 *  Changes due to manager re-write
 *
 *  
 */
package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.webapp.form.cibase.*;
/**
 * 
 * Interface describing fields for a therapy
 *
 */
public interface TherapyData extends DoseData, AgeGenderData {

    public String getName();
    public void setName(String name);	
    public String getNSCNumber();
    public void setNSCNumber(String NSCNumber);
    public String getCASNumber();
    public void setCASNumber(String CASNumber);
    
    public String getToxicityGrade();
    public void setToxicityGrade(String toxicityGrade);
    public String getChemicalClassName();
    public void setChemicalClassName(String chemicalClassName);
    public String getSelectedChemicalClassName(); 
    public void setSelectedChemicalClassName(String selectedChemicalClassName);    
    public String getProcessName();
    public void setProcessName(String processName);
    public String getSelectedProcessName(); 
    public void setSelectedProcessName(String selectedProcessName);    
    public String getTargetName();
    public void setTargetName(String targetName);
    public String getSelectedTargetName(); 
    public void setSelectedTargetName(String selectedTargetName);    
    public String getAdministrativeRoute();
    public void setAdministrativeRoute(String administrativeRoute);
    public String getBiomarker();
    public void setBiomarker(String biomarker);
    public String getTumorResponse();
    public void setTumorResponse(String tumorResponse);
	public String getTumorAgeUnit();
	public void setTumorAgeUnit(String tumorAgeUnit);
	public String getExperiment();
	public void setExperiment(String experiment);
	public String getResults();
	public void setResults(String results);
	public String getComments();
	public void setComments(String comments);	
	
}
