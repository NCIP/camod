/**
 * 
 * $Id: ModelCharacteristicsForm.java,v 1.6 2005-10-13 20:48:00 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/10/06 13:36:08  georgeda
 * Changed ModelCharacteristics interface to be consistent w/ the rest of the interfaces
 *
 * Revision 1.4  2005/09/28 15:12:22  schroedn
 * Added GeneDelivery and Xenograft/Transplant, businass logic in Managers
 *
 * Revision 1.3  2005/09/16 15:52:58  georgeda
 * Changes due to manager re-write
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

/**
 * 
 * Form for submitting/editing AnimalModel
 * 
 */
public class ModelCharacteristicsForm extends BaseForm implements ModelCharacteristicsData, Serializable {

    private static final long serialVersionUID = 3257850969634190134L;

    protected String modelDescriptor;
    protected String name;
    protected String summary;
    protected String principalInvestigator;
    protected String isToolMouse = "no";
    protected String scientificName;
    protected String ethinicityStrain;
    protected String experimentDesign;
    protected String description;
    protected String type;
    protected String breedingNotes;
    protected String url;
    protected String releaseDate = "immediately";
    protected String calendarReleaseDate;
    protected String otherEthinicityStrain;
    protected String ethnicityStrainUnctrlVocab;

    public ModelCharacteristicsForm() {
    }

    public String getModelDescriptor() {
        return modelDescriptor;
    }

    public void setModelDescriptor(String a) {
        this.modelDescriptor = a;
    }

    public String getOtherEthinicityStrain() {
        return otherEthinicityStrain;
    }

    public void setOtherEthinicityStrain(String a) {
        this.otherEthinicityStrain = a;
    }

    public String getEthnicityStrainUnctrlVocab() {
        return ethnicityStrainUnctrlVocab;
    }

    public void setEthnicityStrainUnctrlVocab(String a) {
        this.ethnicityStrainUnctrlVocab = a;
    }

    public String getCalendarReleaseDate() {
        return calendarReleaseDate;
    }

    public void setCalendarReleaseDate(String a) {
        this.calendarReleaseDate = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String a) {
        this.name = a;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String a) {
        this.summary = a;
    }

    public String getPrincipalInvestigator() {
        return principalInvestigator;
    }

    public void setPrincipalInvestigator(String a) {
        this.principalInvestigator = a;
    }

    public String getIsToolMouse() {
        return isToolMouse;
    }

    public void setIsToolMouse(String a) {
        this.isToolMouse = a;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String a) {
        this.scientificName = a;
    }

    public String getEthinicityStrain() {
        return ethinicityStrain;
    }

    public void setEthinicityStrain(String a) {
        this.ethinicityStrain = a;
    }

    public String getExperimentDesign() {
        return experimentDesign;
    }

    public void setExperimentDesign(String a) {
        this.experimentDesign = a;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String a) {
        this.description = a;
    }

    public String getType() {
        return type;
    }

    public void setType(String a) {
        this.type = a;
    }

    public String getBreedingNotes() {
        return breedingNotes;
    }

    public void setBreedingNotes(String a) {
        this.breedingNotes = a;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String a) {
        this.url = a;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String a) {
        this.releaseDate = a;
    }
}
