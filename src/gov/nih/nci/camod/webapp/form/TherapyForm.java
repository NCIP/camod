/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

/**
 * @author pandyas
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TherapyForm extends BaseForm implements Serializable, TherapyData {
    
    private static final long serialVersionUID = 3257095453799404851L;

    /**
     * Default empty constructor
     * 
     * @author pandyas
     * 
     * TODO To change the template for this generated type comment go to Window -
     * Preferences - Java - Code Style - Code Templates
     */
    public TherapyForm() {}

    protected String name;
    protected String NSCNumber;
    protected String CASNumber;
    protected String toxicityGrade;
    protected String chemicalClassName;
    protected String selectedChemicalClassName;
    protected String processName;
    protected String selectedProcessName;
    protected String targetName;
    protected String selectedTargetName;
    protected String dosage;
	protected String doseUnit;    
    protected String type;
    protected String ageAtTreatment;
	protected String ageUnit;    
    protected String administrativeRoute;
    protected String biomarker;
    protected String tumorResponse;
	protected String tumorAgeUnit;    
    protected String experiment;
    protected String results;
    protected String comments;

    
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the NSCNumber.
     */
    public String getNSCNumber() {
        return NSCNumber;
    }
    /**
     * @param NSCNumber
     *            The NSCNumber to set.
     */
    public void setNSCNumber(String NSCNumber) {
        this.NSCNumber = NSCNumber;
    }
    /**
     * @return Returns the CASNumber.
     */
    public String getCASNumber() {
        return CASNumber;
    }
    /**
     * @param CASNumber
     *            The CASNumber to set.
     */
    public void setCASNumber(String CASNumber) {
        this.CASNumber = CASNumber;
    }
    /**
     * @return Returns the toxicityGrade.
     */
    public String getToxicityGrade() {
        return toxicityGrade;
    }
    /**
     * @param toxicityGrade
     *            The toxicityGrade to set.
     */
    public void setToxicityGrade(String toxicityGrade) {
        this.toxicityGrade = toxicityGrade;
    }
    /**
     * @return Returns the chemicalClassName.
     */
    public String getChemicalClassName() {
        return chemicalClassName;
    }
    /**
     * @param chemicalClassName
     *            The chemicalClassName to set.
     */
    public void setChemicalClassName(String chemicalClassName) {
        this.chemicalClassName = chemicalClassName;
    }
    /**
     * @return Returns the selectedChemicalClassName.
     */
    public String getSelectedChemicalClassName() {
        return selectedChemicalClassName;
    }
    /**
     * @param selectedChemicalClassName
     *            The selectedChemicalClassName to set.
     */
    public void setSelectedChemicalClassName(String selectedChemicalClassName) {
        this.selectedChemicalClassName = selectedChemicalClassName;
    }    
    /**
     * @return Returns the processName.
     */
    public String getProcessName() {
        return processName;
    }
    /**
     * @param processName
     *            The processName to set.
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    }
    /**
     * @return Returns the selectedProcessName.
     */
    public String getSelectedProcessName() {
        return selectedProcessName;
    }
    /**
     * @param selectedProcessName
     *            The selectedProcessName to set.
     */
    public void setSelectedProcessName(String selectedProcessName) {
        this.selectedProcessName = selectedProcessName;
    }    
    /**
     * @return Returns the targetName.
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * @param targetName
     *            The targetName to set.
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
    /**
     * @return Returns the selectedTargetName.
     */
    public String getSelectedTargetName() {
        return selectedTargetName;
    }

    /**
     * @param selectedTargetName
     *            The selectedTargetName to set.
     */
    public void setSelectedTargetName(String selectedTargetName) {
        this.selectedTargetName = selectedTargetName;
    }
    /**
     * @return Returns the dosage.
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * @param dosage
     *            The dosage to set.
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
	/**
	 * @return Returns the doseUnit.
	 */
	public String getDoseUnit() {
		return doseUnit;
	}
	/**
	 * @param doseUnit The dosageUnit to set.
	 */
	public void setDoseUnit(String doseUnit) {
		this.doseUnit = doseUnit;
	}
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return Returns the ageAtTreatment.
     */
    public String getAgeAtTreatment() {
        return ageAtTreatment;
    }
    /**
     * @param ageAtTreatment
     *            The ageAtTreatment to set.
     */
    public void setAgeAtTreatment(String ageAtTreatment) {
        this.ageAtTreatment = ageAtTreatment;
    }
	/**
	 * @return Returns the ageUnit.
	 */
	public String getAgeUnit() {
		return ageUnit;
	}
	/**
	 * @param ageUnit The ageUnit to set.
	 */
	public void setAgeUnit(String ageUnit) {
		this.ageUnit = ageUnit;
	}
    /**
     * @return Returns the administrativeRoute.
     */
    public String getAdministrativeRoute() {
        return administrativeRoute;
    }
    /**
     * @param administrativeRoute
     *            The administrativeRoute to set.
     */
    public void setAdministrativeRoute(String administrativeRoute) {
        this.administrativeRoute = administrativeRoute;
    }
    /**
     * @return Returns the biomarker.
     */
    public String getBiomarker() {
        return biomarker;
    }
    /**
     * @param biomarker
     *            The biomarker to set.
     */
    public void setBiomarker(String biomarker) {
        this.biomarker = biomarker;
    }
    /**
     * @return Returns the tumorResponse.
     */
    public String getTumorResponse() {
        return tumorResponse;
    }
    /**
     * @param tumorResponse
     *            The tumorResponse to set.
     */
    public void setTumorResponse(String tumorResponse) {
        this.tumorResponse = tumorResponse;
    }
	/**
	 * @return Returns the tumorAgeUnit.
	 */
	public String getTumorAgeUnit() {
		return tumorAgeUnit;
	}
	/**
	 * @param tumorAgeUnit The tumorAgeUnit to set.
	 */
	public void setTumorAgeUnit(String tumorAgeUnit) {
		this.tumorAgeUnit = tumorAgeUnit;
	}
	/**
	 * @return Returns the experiment.
	 */
	public String getExperiment() {
		return experiment;
	}
	/**
	 * @param experiment The experiment to set.
	 */
	public void setExperiment(String experiment) {
		this.experiment = experiment;
	}
	/**
	 * @return Returns the results.
	 */
	public String getResults() {
		return results;
	}
	/**
	 * @param results The results to set.
	 */
	public void setResults(String results) {
		this.results = results;
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
