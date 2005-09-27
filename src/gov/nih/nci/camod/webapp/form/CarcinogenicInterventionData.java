/**
 * @author dgeorge
 * 
 * $Id: CarcinogenicInterventionData.java,v 1.1 2005-09-27 19:17:08 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */
package gov.nih.nci.camod.webapp.form;


/**
 Interface for most CarcinogenicIntervention screens
 */
public interface CarcinogenicInterventionData  {

    public String getAgeUnit();

    public void setAgeUnit(String ageUnit);

    public String getDoseUnit();

    public void setDoseUnit(String d);

    public String getName();

    public void setName(String name);

    public String getOtherName();

    public void setOtherName(String otherName);

    public String getDosage();

    public void setDosage(String dosage);

    public String getAdministrativeRoute();

    public void setAdministrativeRoute(String administrativeRoute);

    public String getOtherAdministrativeRoute();

    public void setOtherAdministrativeRoute(String otherAdministrativeRoute);

    public String getRegimen();

    public void setRegimen(String regimen);

    public String getAgeAtTreatment();

    public void setAgeAtTreatment(String ageAtTreatment);

    public String getType();

    public void setType(String type);
}
