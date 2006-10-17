/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyForm.java,v 1.8 2006-10-17 16:10:47 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.6  2005/11/03 21:48:16  georgeda
 * Cleanup
 *
 * Revision 1.5  2005/11/03 18:52:44  pandyas
 * Modified for histopathology screens
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

/**
 * 
 * $Id: HistopathologyForm.java,v 1.8 2006-10-17 16:10:47 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $ Revision 1.7 2006/04/17 19:09:19 pandyas
 * caMod 2.1 OM changes
 * 
 * 
 */
public class HistopathologyForm extends BaseForm implements Serializable,
		HistopathologyData {

	private static final long serialVersionUID = 3257225453799404851L;

	/**
	 * Default empty constructor
	 * 
	 * @author pandyas
	 */
	public HistopathologyForm() {
	}

	protected String organ;

	protected String organTissueName;

	protected String organTissueCode;

	protected String diagnosisName;

	protected String diagnosisCode;

	protected String tumorClassification;

	protected String ageOfOnset;

	protected String ageOfOnsetUnit;

	protected String ageOfDetection;

	protected String ageOfDetectionUnit;

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
	 * @return Returns the organ.
	 */
	public String getOrgan() {
		return organ;
	}

	/**
	 * @param organ
	 *            The organ to set.
	 */
	public void setOrgan(String organ) {
		this.organ = organ;
	}

	/**
	 * @return Returns the organTissueName.
	 */
	public String getOrganTissueName() {
		return organTissueName;
	}

	/**
	 * @param organTissueName
	 *            The organTissueName to set.
	 */
	public void setOrganTissueName(String organTissueName) {
		this.organTissueName = organTissueName;
	}

	/**
	 * @return Returns the organTissueCode (concept code).
	 */
	public String getOrganTissueCode() {
		return organTissueCode;
	}

	/**
	 * @param organTissueCode
	 *            The organTissueCode (concept code) to set .
	 */
	public void setOrganTissueCode(String organTissueCode) {
		this.organTissueCode = organTissueCode;
	}

	public String getDiagnosisCode() {
		return diagnosisCode;
	}

	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}

	public String getDiagnosisName() {
		return diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	/**
	 * @return Returns the ageOfOnset.
	 */
	public String getAgeOfOnset() {
		return ageOfOnset;
	}

	/**
	 * @param ageOfOnset
	 *            The ageOfOnset to set.
	 */
	public void setAgeOfOnset(String ageOfOnset) {
		this.ageOfOnset = ageOfOnset;
	}

	/**
	 * @return Returns the ageOfOnsetUnit.
	 */
	public String getAgeOfOnsetUnit() {
		return ageOfOnsetUnit;
	}

	/**
	 * @param ageOfOnsetUnit
	 *            The ageOfOnsetUnit to set.
	 */
	public void setAgeOfOnsetUnit(String ageOfOnsetUnit) {
		this.ageOfOnsetUnit = ageOfOnsetUnit;
	}

	/**
	 * @return Returns the ageOfDetection.
	 */
	public String getAgeOfDetection() {
		return ageOfDetection;
	}

	/**
	 * @param ageOfDetection
	 *            The ageOfDetection to set.
	 */
	public void setAgeOfDetection(String ageOfDetection) {
		this.ageOfDetection = ageOfDetection;
	}

	/**
	 * @return Returns the ageOfDetectionUnit.
	 */
	public String getAgeOfDetectionUnit() {
		return ageOfDetectionUnit;
	}

	/**
	 * @param ageOfDetectionUnit
	 *            The ageOfDetectionUnit to set.
	 */
	public void setAgeOfDetectionUnit(String ageOfDetectionUnit) {
		this.ageOfDetectionUnit = ageOfDetectionUnit;
	}

	/**
	 * @return Returns the weightOfTumor.
	 */
	public String getWeightOfTumor() {
		return weightOfTumor;
	}

	/**
	 * @param weightOfTumor
	 *            The weightOfTumor to set.
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
	 * @param volumeOfTumor
	 *            The volumeOfTumor to set.
	 */
	public void setVolumeOfTumor(String volumeOfTumor) {
		this.volumeOfTumor = volumeOfTumor;
	}

	/**
	 * @return Returns the tumorIncidenceRate.
	 */
	public String getTumorIncidenceRate() {
		return tumorIncidenceRate;
	}

	/**
	 * @param tumorIncidenceRate
	 *            The tumorIncidenceRate to set.
	 */
	public void setTumorIncidenceRate(String tumorIncidenceRate) {
		this.tumorIncidenceRate = tumorIncidenceRate;
	}

	/**
	 * @return Returns the survivalInfo.
	 */
	public String getSurvivalInfo() {
		return survivalInfo;
	}

	/**
	 * @param survivalInfo
	 *            The survivalInfo to set.
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
	 * @param grossDescription
	 *            The grossDescription to set.
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
	 * @param microscopicDescription
	 *            The microscopicDescription to set.
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
	 * @param observation
	 *            The observation to set.
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
	 * @param methodOfObservation
	 *            The methodOfObservation to set.
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
	 * @param comparativeData
	 *            The comparativeData to set.
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
	 * @param comments
	 *            The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getTumorClassification() {
		return tumorClassification;
	}

	public void setTumorClassification(String tumorClassification) {
		this.tumorClassification = tumorClassification;
	}
}
