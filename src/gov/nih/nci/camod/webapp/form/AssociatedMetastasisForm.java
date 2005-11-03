/**
 * 
 * @author pandyas
 * 
 * $Id: AssociatedMetastasisForm.java,v 1.2 2005-11-03 21:48:16 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/11/03 18:52:44  pandyas
 * Modified for histopathology screens
 *
 * 
 */

package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class AssociatedMetastasisForm extends BaseForm implements Serializable, AssociatedMetastasisData {

    private static final long serialVersionUID = 3257225453799404851L;
    
	/**
	 * Default empty constructor
	 * @author pandyas
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public AssociatedMetastasisForm() {}
	
	protected String histopathologyID;
	
	protected String organ;
	protected String organTissueName;
	protected String organTissueCode;	
	
	protected String diagnosisName;
    protected String diagnosisCode;
    protected String tumorClassification;
    
	protected String ageOfOnset;
    protected String ageUnit;
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
	 * @return Returns the parent histopathologyID.
	 */	
	public String getHistopathologyID() {
		return histopathologyID;
	}
	/**
	 * @param parent histopathologyID The histopathologyID to set.
	 */	
	public void setHistopathologyID( String histopathologyID ) {
		this.histopathologyID = histopathologyID;
	}	
	
	/**
	 * @return Returns the organ.
	 */	
	public String getOrgan() {
		return organ;
	}
	/**
	 * @param organ The organ to set.
	 */	
	public void setOrgan( String organ ) {
		this.organ = organ;
	}
	/**
	 * @return Returns the organTissueName.
	 */	
	public String getOrganTissueName() {
		return organTissueName;
	}
	/**
	 * @param organTissueName The organTissueName to set.
	 */	
	public void setOrganTissueName( String organTissueName ) {
		this.organTissueName = organTissueName;
	}
	/**
	 * @return Returns the organTissueCode (concept code).
	 */	
	public String getOrganTissueCode() {
		return organTissueCode;
	}
	/**
	 * @param organTissueCode The organTissueCode (concept code) to set .
	 */	
	public void setOrganTissueCode( String organTissueCode ) {
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
	 * @param ageOfOnset The ageOfOnset to set.
	 */
	public void setAgeOfOnset(String ageOfOnset) {
		this.ageOfOnset = ageOfOnset;
	}
    /**
     * @return Returns the ageUnit.
     */
    public String getAgeUnit() {
        return ageUnit;
    }

    /**
     * @param ageUnit
     *            The ageUnit to set.
     */
    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
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
    
    public String getTumorClassification() {
        return tumorClassification;
    }
    
    public void setTumorClassification(String tumorClassification) {
        this.tumorClassification = tumorClassification;
    }		
	
}
