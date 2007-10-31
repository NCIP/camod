/**
 *
 * @author pandyas
 * 
 * $Id: ViralTreatmentForm.java,v 1.7 2007-10-31 17:26:00 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.5  2005/10/20 20:36:15  pandyas
 * added javadocs
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

public class ViralTreatmentForm extends BaseForm implements Serializable, ViralTreatmentData {
    
    private static final long serialVersionUID = 3257115453799404851L;
    
	/**
	 * Default empty constructor
	 */
	public ViralTreatmentForm() {}
	
	protected String name;
	protected String otherName;	
	protected String dosage;
    protected String dosageUnit;     
	protected String administrativeRoute;
	protected String otherAdministrativeRoute;	
	protected String regimen;
	protected String ageAtTreatment;
    protected String ageAtTreatmentUnit;    
	protected String type;
	protected String comments;	

	
	public String getAgeAtTreatmentUnit() {
		return ageAtTreatmentUnit;
	}
	
	public void setAgeAtTreatmentUnit( String ageAtTreatmentUnit) {
		this.ageAtTreatmentUnit = ageAtTreatmentUnit;
	}
	
	public String getDosageUnit() {
		return dosageUnit;
	}
	
	public void setDosageUnit(String dosageUnit ) {
		this.dosageUnit = dosageUnit;		
	}
	
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

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
}
