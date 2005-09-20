/*
 * Created on Aug 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
/**
 * @author pandyas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HistopathologyForm extends BaseForm implements Serializable {
    
    private static final long serialVersionUID = 3257225453799404851L;
    
	/**
	 * Default empty constructor
	 * @author pandyas
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public HistopathologyForm() {}
	
	protected String organName;
	protected String diseaseName;	
	protected String ageOfOnset;
	protected String weightOfTumor;
	protected String volumeOfTumor;
	protected String tumorIncidenceRate;
	protected String survivalInfo;
	protected String grossDescription;
	protected String microscopicDescription;
	protected String observation;
	protected String methodOfObservation;
	protected String comparativeData;
	protected String comments;

	/**
	 * @return Returns the organName.
	 */
	public String getOrganName() {
		return organName;
	}
	/**
	 * @param organName The organName to set.
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	/**
	 * @return Returns the diseaseName.
	 */
	public String getDiseaseName() {
		return diseaseName;
	}
	/**
	 * @param diseaseName The diseaseName to set.
	 */
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}	
	/**
	 * @return Returns the ageOfOnset.
	 */
	public String getAgeOfOnset() {
		return ageOfOnset;
	}
	/**
	 * @param ageOfOnset The ageOfOnset to set.
	 */
	public void setAgeOfOnset(String ageOfOnset) {
		this.ageOfOnset = ageOfOnset;
	}	
	/**
	 * @return Returns the weightOfTumor.
	 */
	public String getWeightOfTumor() {
		return weightOfTumor;
	}
	/**
	 * @param weightOfTumor The weightOfTumor to set.
	 */
	public void setWeightOfTumor(String weightOfTumor) {
		this.weightOfTumor = weightOfTumor;
	}	
	/**
	 * @return Returns the volumeOfTumor.
	 */
	public String getVolumeOfTumor() {
		return volumeOfTumor;
	}
	/**
	 * @param volumeOfTumor The volumeOfTumor to set.
	 */
	public void setVolumeOfTumor(String volumeOfTumor) {
		this.volumeOfTumor = volumeOfTumor;
	}	
	/**
	 * @return Returns the survivalInfo.
	 */
	public String getSurvivalInfo() {
		return survivalInfo;
	}
	/**
	 * @param survivalInfo The survivalInfo to set.
	 */
	public void setSurvivalInfo(String survivalInfo) {
		this.survivalInfo = survivalInfo;
	}	
	/**
	 * @return Returns the grossDescription.
	 */
	public String getGrossDescription() {
		return grossDescription;
	}
	/**
	 * @param grossDescription The grossDescription to set.
	 */
	public void setGrossDescription(String grossDescription) {
		this.grossDescription = grossDescription;
	}	
	/**
	 * @return Returns the microscopicDescription.
	 */
	public String getMicroscopicDescription() {
		return microscopicDescription;
	}
	/**
	 * @param microscopicDescription The microscopicDescription to set.
	 */
	public void setMicroscopicDescription(String microscopicDescription) {
		this.microscopicDescription = microscopicDescription;
	}	
	/**
	 * @return Returns the observation.
	 */
	public String getObservation() {
		return observation;
	}
	/**
	 * @param observation The observation to set.
	 */
	public void setObservation(String observation) {
		this.observation = observation;
	}
	/**
	 * @return Returns the methodOfObservation.
	 */
	public String getMethodOfObservation() {
		return methodOfObservation;
	}
	/**
	 * @param methodOfObservation The methodOfObservation to set.
	 */
	public void setMethodOfObservation(String methodOfObservation) {
		this.methodOfObservation = methodOfObservation;
	}	
	/**
	 * @return Returns the comparativeData.
	 */
	public String getComparativeData() {
		return comparativeData;
	}
	/**
	 * @param comparativeData The comparativeData to set.
	 */
	public void setComparativeData(String comparativeData) {
		this.comparativeData = comparativeData;
	}	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}	
}
