/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TherapyForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public TherapyForm() {}
	
	protected String name;
	protected String NSCNumber;
	protected String CASNumber;
	protected String toxicityGrade;
	protected String chemicalClassName;
	protected String processName;
	protected String targetName;
	protected String dosage;
	protected String type;
	protected String age;
	protected String administrativeRoute;
	protected String biomarker;
	protected String tumorResponse;
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
	 * @param name The name to set.
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
	 * @param NSCNumber The NSCNumber to set.
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
	 * @param CASNumber The CASNumber to set.
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
	 * @param toxicityGrade The toxicityGrade to set.
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
	 * @param chemicalClassName The chemicalClassName to set.
	 */
	public void setChemicalClassName(String chemicalClassName) {
		this.chemicalClassName = chemicalClassName;
	}	
	/**
	 * @return Returns the processName.
	 */
	public String getProcessName() {
		return processName;
	}
	/**
	 * @param processName The processName to set.
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}	
	/**
	 * @return Returns the targetName.
	 */
	public String getTargetName() {
		return targetName;
	}
	/**
	 * @param targetName The targetName to set.
	 */
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}	
	/**
	 * @return Returns the dosage.
	 */
	public String getDosage() {
		return dosage;
	}
	/**
	 * @param dosage The dosage to set.
	 */
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}	
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}	
	/**
	 * @return Returns the age.
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age The age to set.
	 */
	public void setAge(String age) {
		this.age = age;
	}	
	/**
	 * @return Returns the administrativeRoute.
	 */
	public String getAdministrativeRoute() {
		return administrativeRoute;
	}
	/**
	 * @param administrativeRoute The administrativeRoute to set.
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
	 * @param biomarker The biomarker to set.
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
	 * @param tumorResponse The tumorResponse to set.
	 */
	public void setTumorResponse(String tumorResponse) {
		this.tumorResponse = tumorResponse;
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
