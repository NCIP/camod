/**
 *
 * $Id: ChemicalDrugForm.java,v 1.5 2005-10-20 20:32:12 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;


public class ChemicalDrugForm extends BaseForm implements Serializable, ChemicalDrugData {

    private static final long serialVersionUID = 3257335453799404851L;

    /**
     * Default empty constructor
     * 
     * @author pandyas
     * 
     * TODO To change the template for this generated type comment go to Window -
     * Preferences - Java - Code Style - Code Templates
     */
    public ChemicalDrugForm() {
    }

    protected String name;
    protected String otherName;
    protected String dosage;
    protected String NSCNumber;
    protected String CASNumber;
    protected String administrativeRoute;
    protected String otherAdministrativeRoute;
    protected String regimen;
    protected String ageAtTreatment;
    protected String type;
    protected String doseUnit;
    protected String ageUnit;

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public String getDoseUnit() {
        return doseUnit;
    }

    public void setDoseUnit(String d) {
        this.doseUnit = d;
    }

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
     * @return Returns the otherName.
     */
    public String getOtherName() {
        return otherName;
    }

    /**
     * @param otherName
     *            The otherName to set.
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
     * @param dosage
     *            The dosage to set.
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
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
     * @return Returns the otherAdministrativeRoute.
     */
    public String getOtherAdministrativeRoute() {
        return otherAdministrativeRoute;
    }

    /**
     * @param otherAdministrativeRoute
     *            The otherAdministrativeRoute to set.
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
     * @param regimen
     *            The regimen to set.
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
     * @param ageAtTreatment
     *            The ageAtTreatment to set.
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
     * @param type
     *            The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

}
