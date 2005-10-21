/*
 * Created on May 4, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.domain;

import java.io.Serializable;

import gov.nih.nci.camod.util.Duplicatable;
/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Treatment extends BaseObject implements Serializable, Duplicatable {

    private static final long serialVersionUID = 3258485453799404851L;
    
    private String regimen;
    private String dosage;
    private String administrativeRoute;
    private String adminRouteUnctrlVocab;
    private String ageAtTreatment;
    private SexDistribution sexDistribution;

    /**
     * @return Returns the sexDistribution.
     */
    public SexDistribution getSexDistribution() {
        return sexDistribution;
    }

    /**
     * @param sexDistribution
     *            The sexDistribution to set.
     */
    public void setSexDistribution(SexDistribution sexDistribution) {
        this.sexDistribution = sexDistribution;
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
     * @return Returns the adminRouteUnctrlVocab.
     */
    public String getAdminRouteUnctrlVocab() {
        return adminRouteUnctrlVocab;
    }

    /**
     * @param adminRouteUnctrlVocab
     *            The adminRouteUnctrlVocab to set.
     */
    public void setAdminRouteUnctrlVocab(String adminRouteUnctrlVocab) {
        this.adminRouteUnctrlVocab = adminRouteUnctrlVocab;
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
     * @see java.lang.Object#toString()
     */
     public String toString() {
       String result = super.toString() + " - ";      
       result += this.getRegimen()+" - "+this.getDosage();
       return result;
     }  
    
    public boolean equals(Object o) {
      if (!super.equals(o)) return false;            
      if (!(this.getClass().isInstance(o))) return false;           
      return true;
    }
}
