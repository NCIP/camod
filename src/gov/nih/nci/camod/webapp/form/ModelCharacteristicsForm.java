/**
 * 
 * $Id: ModelCharacteristicsForm.java,v 1.9 2006-10-17 16:10:47 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.7  2005/10/24 13:28:30  georgeda
 * Cleanup changes
 *
 * Revision 1.6  2005/10/13 20:48:00  georgeda
 * Correctly handle the PI
 *
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
    protected String isToolStrain = "no";
    protected String scientificName;
    protected String otherScientificName;    
    protected String ethinicityStrain;
    protected String otherEthnicityStrain;    
    protected String experimentDesign;
    protected String description;
    protected String type;
    protected String breedingNotes;
    protected String url;
    protected String releaseDate = "immediately";
    protected String calendarReleaseDate;
    protected String genotype;
    protected String nomenclature;    


    public ModelCharacteristicsForm() {
    }

    public String getModelDescriptor() {
        return modelDescriptor;
    }

    public void setModelDescriptor(String modelDescriptor) {
        this.modelDescriptor = modelDescriptor;
    }

    public String getOtherEthnicityStrain() {
        return otherEthnicityStrain;
    }

    public void setOtherEthnicityStrain(String otherEthnicityStrain) {
        this.otherEthnicityStrain = otherEthnicityStrain;
    }

    public String getCalendarReleaseDate() {
        return calendarReleaseDate;
    }

    public void setCalendarReleaseDate(String calendarReleaseDate) {
        this.calendarReleaseDate = calendarReleaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrincipalInvestigator() {
        return principalInvestigator;
    }

    public void setPrincipalInvestigator(String principalInvestigator) {
        this.principalInvestigator = principalInvestigator;
    }

    public String getIsToolStrain() {
        return isToolStrain;
    }

    public void setIsToolStrain(String isToolStrain) {
        this.isToolStrain = isToolStrain;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }
    
    public String getOtherScientificName() {
        return otherScientificName;
    }

    public void setOtherScientificName(String otherScientificName) {
        this.otherScientificName = otherScientificName;
    }    

    public String getEthinicityStrain() {
        return ethinicityStrain;
    }

    public void setEthinicityStrain(String ethinicityStrain) {
        this.ethinicityStrain = ethinicityStrain;
    }

    public String getExperimentDesign() {
        return experimentDesign;
    }

    public void setExperimentDesign(String experimentDesign) {
        this.experimentDesign = experimentDesign;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreedingNotes() {
        return breedingNotes;
    }

    public void setBreedingNotes(String breedingNotes) {
        this.breedingNotes = breedingNotes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public String getGenotype() {
        return genotype;
    }

    public void setGenotype(String genotype) {
        this.genotype = genotype;
    }   
    
    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }     
}
