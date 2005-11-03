/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyData.java,v 1.1 2005-11-03 18:52:44 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
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
	
	public String getDiseaseName();
	public void setDiseaseName(String diseaseName);
	public void setDiagnosisCode(String diagnosisCode);
	public String getDiagnosisCode();
	public void setDiagnosisName(String diagnosisName);
	public String getDiagnosisName();	
	
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
