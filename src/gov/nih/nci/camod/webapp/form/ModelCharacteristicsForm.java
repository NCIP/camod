/**
 * 
 * $Id: ModelCharacteristicsForm.java,v 1.3 2005-09-16 15:52:58 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

/**
 * 
 * Form for submitting/editing AnimalModel
 * 
 */
public class ModelCharacteristicsForm extends BaseForm implements ModelCharacteristics, Serializable {

    private static final long serialVersionUID = 3257850969634190134L;

    protected String modelDescriptor;
    protected String name;
    protected String summary;
    protected String email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String a) {
        this.email = a;
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
