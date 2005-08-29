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
public class EnvironmentalFactorForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author pandyas
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public EnvironmentalFactorForm() {}
	
	protected String name;
	protected String otherName;	
	protected String dosage;
	protected String administrativeRoute;
	protected String otherAdministrativeRoute;	
	protected String regimen;
	protected String ageAtTreatment;
	protected String type;
	
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
	 * @return Returns the otherName.
	 */
	public String getOtherName() {
		return otherName;
	}
	/**
	 * @param otherName The otherName to set.
	 */
	public void setOtherName(String otherName) {
		this.otherName = otherName;
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
	 * @return Returns the otherAdministrativeRoute.
	 */
	public String getOtherAdministrativeRoute() {
		return otherAdministrativeRoute;
	}
	/**
	 * @param otherAdministrativeRoute The otherAdministrativeRoute to set.
	 */
	public void setOtherAdministrativeRoute(String otherAdministrativeRoute) {
		this.otherAdministrativeRoute = otherAdministrativeRoute;
	}	
	/**
	 * @return Returns the regimen.
	 */
	public String getRegimen() {
		return regimen;
	}
	/**
	 * @param regimen The regimen to set.
	 */
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}	
	/**
	 * @return Returns the ageAtTreatment.
	 */
	public String getAgeAtTreatment() {
		return ageAtTreatment;
	}
	/**
	 * @param ageAtTreatment The ageAtTreatment to set.
	 */
	public void setAgeAtTreatment(String ageAtTreatment) {
		this.ageAtTreatment = ageAtTreatment;
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
}
