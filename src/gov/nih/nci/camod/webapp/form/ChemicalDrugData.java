/*
 * Created on Aug 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;


/**
 * @author dgeorge
 * 
 * $Id: ChemicalDrugData.java,v 1.1 2005-09-27 16:52:17 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
public interface ChemicalDrugData {

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

    public String getNSCNumber();

    public void setNSCNumber(String NSCNumber);

    public String getCASNumber();

    public void setCASNumber(String CASNumber);

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
