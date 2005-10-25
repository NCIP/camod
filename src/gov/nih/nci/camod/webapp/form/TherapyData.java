/**
 *  @author pandyas
 *  
 *  $Id: TherapyData.java,v 1.2 2005-10-25 19:42:15 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.1  2005/10/06 19:27:25  pandyas
 *  modified for Therapy screen
 *
 *  Revision 1.1  2005/09/26 15:52:58  pandyas
 *  Changes due to manager re-write
 *
 *  
 */
package gov.nih.nci.camod.webapp.form;

import gov.nih.nci.camod.webapp.form.cibase.AgeGenderData;
import gov.nih.nci.camod.webapp.form.cibase.DoseData;

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

    public String[] getChemicalClasses();

    public void setChemicalClasses(String[] chemicalClasses);

    public String[] getSelectedChemicalClasses();

    public void setSelectedChemicalClasses(String[] selectedChemicalClasses);

    public String[] getProcesses();

    public void setProcesses(String[] processes);

    public String[] getSelectedProcesses();

    public void setSelectedProcesses(String[] selectedProcesses);

    public String[] getTargets();

    public void setTargets(String[] target);

    public String[] getSelectedTargets();

    public void setSelectedTargets(String[] selectedTargets);

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
