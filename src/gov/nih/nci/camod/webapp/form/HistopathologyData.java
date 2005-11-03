/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyData.java,v 1.2 2005-11-03 21:48:16 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/11/03 18:52:44  pandyas
 * Modified for histopathology screens
 *
 * 
 */

package gov.nih.nci.camod.webapp.form;

public interface HistopathologyData  {

	public String getOrgan();
	public void setOrgan( String organ );
	public String getOrganTissueName();
	public void setOrganTissueName( String organTissueName );
	public String getOrganTissueCode();
	public void setOrganTissueCode( String organTissueCode );
	
	public String getDiagnosisName();
	public void setDiagnosisName(String diagnosisName);
	public void setDiagnosisCode(String diagnosisCode);
	public String getDiagnosisCode();
	public void setTumorClassification(String tumorClassification);
	public String getTumorClassification();	
    
	public String getAgeOfOnset();
	public void setAgeOfOnset(String ageOfOnset);
    public String getAgeUnit();
    public void setAgeUnit(String ageUnit);	
	public String getWeightOfTumor();
	public void setWeightOfTumor(String weightOfTumor);
	public String getVolumeOfTumor();
	public void setVolumeOfTumor(String volumeOfTumor);
	public String getSurvivalInfo();
	public void setSurvivalInfo(String survivalInfo);
	public String getGrossDescription();
	public void setGrossDescription(String grossDescription);
	public String getMicroscopicDescription();
	public void setMicroscopicDescription(String microscopicDescription);
	public String getObservation();
	public void setObservation(String observation);
	public String getMethodOfObservation();
	public void setMethodOfObservation(String methodOfObservation);
	public String getComparativeData();
	public void setComparativeData(String comparativeData);
	public String getComments();
	public void setComments(String comments); 
    public String getTumorIncidenceRate();
    public void setTumorIncidenceRate(String tumorIncidenceRate);
	
}
