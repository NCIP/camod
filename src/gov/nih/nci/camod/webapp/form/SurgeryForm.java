/*
 * $Id: SurgeryForm.java,v 1.5 2006-04-17 19:09:19 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

/**
 * @author pandyas
 * 
 * $Id: SurgeryForm.java,v 1.5 2006-04-17 19:09:19 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */
public class SurgeryForm extends BaseForm implements Serializable, SurgeryData {

    private static final long serialVersionUID = 3257065453799404851L;

    /**
     * Default empty constructor
     */
    public SurgeryForm() {
    }

    protected String name;
    protected String otherName;
    protected String regimen;
    protected String ageAtTreatment;
    protected String ageAtTreatmentUnit;    
    protected String type;


    public String getAgeAtTreatmentUnit() {
        return ageAtTreatmentUnit;
    }

    public void setAgeAtTreatmentUnit(String ageAtTreatmentUnit) {
        this.ageAtTreatmentUnit = ageAtTreatmentUnit;
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
