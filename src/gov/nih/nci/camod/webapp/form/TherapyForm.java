/**
 *
 * @author pandyas
 * 
 * $Id: TherapyForm.java,v 1.7 2005-10-26 14:10:49 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/10/25 19:42:15  georgeda
 * Finished Therapy page
 *
 * Revision 1.5  2005/10/20 20:35:52  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class TherapyForm extends BaseForm implements Serializable, TherapyData {

    private static final long serialVersionUID = 3257095453799404851L;

    protected String name;
    protected String NSCNumber;
    protected String CASNumber;
    protected String toxicityGrade;
    protected String[] chemicalClasses;
    protected String[] selectedChemicalClasses;
    protected String[] processes;
    protected String[] selectedProcesses;
    protected String[] targets;
    protected String[] selectedTargets;
    protected String dosage;
    protected String doseUnit;
    protected String type;
    protected String ageAtTreatment;
    protected String ageUnit;
    protected String administrativeRoute;
    protected String otherAdministrativeRoute;
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
    public String[] getChemicalClasses() {
        return chemicalClasses;
    }

    /**
     * @param chemicalClassName
     *            The chemicalClassName to set.
     */
    public void setChemicalClasses(String[] chemicalClasses) {
        this.chemicalClasses = chemicalClasses;
    }

    /**
     * @return Returns the selectedChemicalClassName.
     */
    public String[] getSelectedChemicalClasses() {
        return selectedChemicalClasses;
    }

    /**
     * @param selectedChemicalClassName
     *            The selectedChemicalClassName to set.
     */
    public void setSelectedChemicalClasses(String[] selectedChemicalClasses) {
        this.selectedChemicalClasses = selectedChemicalClasses;
    }

    /**
     * @return Returns the processName.
     */
    public String[] getProcesses() {
        return processes;
    }

    /**
     * @param processName
     *            The processName to set.
     */
    public void setProcesses(String[] processName) {
        this.processes = processName;
    }

    /**
     * @return Returns the selectedProcessName.
     */
    public String[] getSelectedProcesses() {
        return selectedProcesses;
    }

    /**
     * @param selectedProcessName
     *            The selectedProcessName to set.
     */
    public void setSelectedProcesses(String[] selectedProcesses) {
        this.selectedProcesses = selectedProcesses;
    }

    /**
     * @return Returns the targetName.
     */
    public String[] getTargets() {
        return targets;
    }

    /**
     * @param targetName
     *            The targetName to set.
     */
    public void setTargets(String[] targets) {
        this.targets = targets;
    }

    /**
     * @return Returns the selectedTargetName.
     */
    public String[] getSelectedTargets() {
        return selectedTargets;
    }

    /**
     * @param selectedTargetName
     *            The selectedTargetName to set.
     */
    public void setSelectedTargets(String[] selectedTargets) {
        this.selectedTargets = selectedTargets;
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
     * @param doseUnit
     *            The dosageUnit to set.
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
     * @param ageUnit
     *            The ageUnit to set.
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
     * @param tumorAgeUnit
     *            The tumorAgeUnit to set.
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
     * @param experiment
     *            The experiment to set.
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
     * @param results
     *            The results to set.
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
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    /**
     * @return Returns the other administrative route
     */
    public String getOtherAdministrativeRoute() {
        return otherAdministrativeRoute;
    }

    /**
     * @param otherAdministrativeRoute
     *            The other administrative route to set
     */
    public void setOtherAdministrativeRoute(String otherAdministrativeRoute) {
        this.otherAdministrativeRoute = otherAdministrativeRoute;
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        name = null;
        NSCNumber = null;
        CASNumber = null;
        toxicityGrade = null;
        chemicalClasses = new String[0];
        selectedChemicalClasses = new String[0];
        processes = new String[0];
        selectedProcesses = new String[0];
        targets = new String[0];
        selectedTargets = new String[0];
        dosage = null;
        doseUnit = null;
        type = null;
        ageAtTreatment = null;
        ageUnit = null;
        administrativeRoute = null;
        biomarker = null;
        tumorResponse = null;
        tumorAgeUnit = null;
        experiment = null;
        results = null;
        comments = null;
    }
}
