/**
 *  @author pandyas
 *  
 *  $Id: TherapyData.java,v 1.5 2006-04-17 19:09:19 pandyas Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.4  2005/12/29 18:29:49  pandyas
 *  Clean up - removed code for TumorResponse and TumorAgeUnit
 *
 *  Revision 1.3  2005/10/26 14:10:49  georgeda
 *  Added other administrative route to therapy
 *
 *  Revision 1.2  2005/10/25 19:42:15  georgeda
 *  Finished Therapy page
 *
 *  Revision 1.1  2005/10/06 19:27:25  pandyas
 *  modified for Therapy screen
 *
 *  Revision 1.1  2005/09/26 15:52:58  pandyas
 *  Changes due to manager re-write
 *
 *  
 */
package gov.nih.nci.camod.webapp.form;

/**
 * 
 * Interface describing fields for a therapy
 * 
 */
public interface TherapyData  {

    public String getName();
    public void setName(String name);
    public String getNscNumber();
    public void setNscNumber(String nscNumber);
    public String getCasNumber();
    public void setCasNumber(String casNumber);
    public String getToxicityGrade();
    public void setToxicityGrade(String toxicityGrade);
    public String[] getChemicalClasses();
    public void setChemicalClasses(String[] chemicalClasses);
    public String[] getSelectedChemicalClasses();
    public void setSelectedChemicalClasses(String[] selectedChemicalClasses);
    public String[] getProcesses();
    public void setProcesses(String[] processName);
    public String[] getSelectedProcesses();
    public void setSelectedProcesses(String[] selectedProcesses);
    public String[] getTargets();
    public void setTargets(String[] targets);
    public String[] getSelectedTargets();
    public void setSelectedTargets(String[] selectedTargets);
    public String getDosage();
    public void setDosage(String dosage);
    public String getDosageUnit();
    public void setDosageUnit(String dosageUnit);
    public String getType();
    public void setType(String type);
    public String getAgeAtTreatment();
    public void setAgeAtTreatment(String ageAtTreatment);
    public String getAgeAtTreatmentUnit();
    public void setAgeAtTreatmentUnit(String ageAtTreatmentUnit);
    public String getAdministrativeRoute();
    public void setAdministrativeRoute(String administrativeRoute);
    public String getBiomarker();
    public void setBiomarker(String biomarker);
    public String getExperiment();
    public void setExperiment(String experiment);
    public String getResults();
    public void setResults(String results);
    public String getComments();
    public void setComments(String comments);
    public String getTumorResponse();
    public void setTumorResponse(String tumorResponse);
    public String getOtherAdministrativeRoute();
    public void setOtherAdministrativeRoute(String otherAdministrativeRoute);

}
