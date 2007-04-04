/*
 * @author pandyas
 *
 * $Id: EngineeredTransgeneForm.java,v 1.10 2007-04-04 13:23:37 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2007/03/26 12:03:10  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.8  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

public class EngineeredTransgeneForm extends BaseForm implements Serializable, EngineeredTransgeneData {
    
    private static final long serialVersionUID = 3257305453799404851L;
    
	/**
	 * Default empty constructor
	 * @author pandyas
	 */
	public EngineeredTransgeneForm() {}

    protected String constructSequence;    
    protected String isRandom;
    protected String locationOfIntegration;
	protected String name;
	protected String scientificName;
	protected String otherScientificName;
	protected String mgiId;
	protected String zfinId;
	protected String rgdId;	
	protected String function;
	protected String conditionedBy;
	protected String description;
	protected String comments;
	protected String fileServerLocation;	
	protected FormFile fileLocation;	
	protected String title;
	protected String descriptionOfConstruct;
	protected String geneFunctions;
	protected String transcriptional1_name;
	protected String transcriptional1_species;
	protected String transcriptional1_otherSpecies;
	protected String transcriptional2_name;
	protected String transcriptional2_species;
	protected String transcriptional2_otherSpecies;
	protected String transcriptional3_name;
	protected String transcriptional3_species;
	protected String transcriptional3_otherSpecies;
	protected String polyASignal_name;
	protected String polyASignal_species;
	protected String polyASignal_otherSpecies;
	protected String spliceSites_name;
	protected String spliceSites_species;
	protected String spliceSites_otherSpecies;
    protected String transgeneId;
    protected String imageUrl;
    protected String thumbUrl;

    /**
     * @return Returns the constructSequence.
     */
    public String getConstructSequence()
    {
        return constructSequence;
    }

    /**
     * @param constructSequence The constructSequence to set.
     */
    public void setConstructSequence(String constructSequence)
    {
        this.constructSequence = constructSequence;
    }    
	/**
	 * @return Returns the locationOfIntegration.
	 */
	public String getLocationOfIntegration() {
		return locationOfIntegration;
	}
	/**
	 * @param locationOfIntegration The locationOfIntegration to set.
	 */
	public void setLocationOfIntegration(String locationOfIntegration) {
		this.locationOfIntegration = locationOfIntegration;
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
	 * @return Returns the scientificName.
	 */
	public String getScientificName() {
		return scientificName;
	}
	/**
	 * @param scientificName The scientificName to set.
	 */
	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}	

	/**
	 * @return Returns the function.
	 */
	public String getFunction() {
		return function;
	}
	/**
	 * @param function The function to set.
	 */
	public void setFunction(String function) {
		this.function = function;
	}
	/**
	 * @return Returns the conditionedBy.
	 */
	public String getConditionedBy() {
		return conditionedBy;
	}
	/**
	 * @param conditionedBy The conditionedBy to set.
	 */
	public void setConditionedBy(String conditionedBy) {
		this.conditionedBy = conditionedBy;
	}	
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsRandom() {
		return isRandom;
	}
	public void setIsRandom(String isRandom) {
		this.isRandom = isRandom;
	}
	public String getOtherScientificName() {
		return otherScientificName;
	}
	public void setOtherScientificName(String otherScientificName) {
		this.otherScientificName = otherScientificName;
	}
	public String getPolyASignal_name() {
		return polyASignal_name;
	}
	public void setPolyASignal_name(String polyASignal_name) {
		this.polyASignal_name = polyASignal_name;
	}
	public String getPolyASignal_otherSpecies() {
		return polyASignal_otherSpecies;
	}
	public void setPolyASignal_otherSpecies(String polyASignal_otherSpecies) {
		this.polyASignal_otherSpecies = polyASignal_otherSpecies;
	}
	public String getPolyASignal_species() {
		return polyASignal_species;
	}
	public void setPolyASignal_species(String polyASignal_species) {
		this.polyASignal_species = polyASignal_species;
	}
	public String getSpliceSites_name() {
		return spliceSites_name;
	}
	public void setSpliceSites_name(String spliceSites_name) {
		this.spliceSites_name = spliceSites_name;
	}
	public String getSpliceSites_otherSpecies() {
		return spliceSites_otherSpecies;
	}
	public void setSpliceSites_otherSpecies(String spliceSites_otherSpecies) {
		this.spliceSites_otherSpecies = spliceSites_otherSpecies;
	}
	public String getSpliceSites_species() {
		return spliceSites_species;
	}
	public void setSpliceSites_species(String spliceSites_species) {
		this.spliceSites_species = spliceSites_species;
	}
	public String getTranscriptional1_name() {
		return transcriptional1_name;
	}
	public void setTranscriptional1_name(String transcriptional1_name) {
		this.transcriptional1_name = transcriptional1_name;
	}
	public String getTranscriptional1_otherSpecies() {
		return transcriptional1_otherSpecies;
	}
	public void setTranscriptional1_otherSpecies(
			String transcriptional1_otherSpecies) {
		this.transcriptional1_otherSpecies = transcriptional1_otherSpecies;
	}
	public String getTranscriptional1_species() {
		return transcriptional1_species;
	}
	public void setTranscriptional1_species(String transcriptional1_species) {
		this.transcriptional1_species = transcriptional1_species;
	}
	public String getTranscriptional2_name() {
		return transcriptional2_name;
	}
	public void setTranscriptional2_name(String transcriptional2_name) {
		this.transcriptional2_name = transcriptional2_name;
	}
	public String getTranscriptional2_otherSpecies() {
		return transcriptional2_otherSpecies;
	}
	public void setTranscriptional2_otherSpecies(
			String transcriptional2_otherSpecies) {
		this.transcriptional2_otherSpecies = transcriptional2_otherSpecies;
	}
	public String getTranscriptional2_species() {
		return transcriptional2_species;
	}
	public void setTranscriptional2_species(String transcriptional2_species) {
		this.transcriptional2_species = transcriptional2_species;
	}
	public String getTranscriptional3_name() {
		return transcriptional3_name;
	}
	public void setTranscriptional3_name(String transcriptional3_name) {
		this.transcriptional3_name = transcriptional3_name;
	}
	public String getTranscriptional3_otherSpecies() {
		return transcriptional3_otherSpecies;
	}
	public void setTranscriptional3_otherSpecies(
			String transcriptional3_otherSpecies) {
		this.transcriptional3_otherSpecies = transcriptional3_otherSpecies;
	}
	public String getTranscriptional3_species() {
		return transcriptional3_species;
	}
	public void setTranscriptional3_species(String transcriptional3_species) {
		this.transcriptional3_species = transcriptional3_species;
	}
	public String getGeneFunctions() {
		return geneFunctions;
	}
	public void setGeneFunctions(String geneFunctions) {
		this.geneFunctions = geneFunctions;
	}
	public String getDescriptionOfConstruct() {
		return descriptionOfConstruct;
	}
	public void setDescriptionOfConstruct(String descriptionOfConstruct) {
		this.descriptionOfConstruct = descriptionOfConstruct;
	}
	public FormFile getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(FormFile fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getFileServerLocation() {
		return fileServerLocation;
	}
	public void setFileServerLocation(String fileServerLocation) {
		this.fileServerLocation = fileServerLocation;
	}
    
    public String getTransgeneId() {
        return transgeneId;
    }
    
    public void setTransgeneId(String transgeneId) {
        this.transgeneId = transgeneId;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

	public String getMgiId() {
		return mgiId;
	}

	public void setMgiId(String mgiId) {
		this.mgiId = mgiId;
	}

	public String getRgdId() {
		return rgdId;
	}

	public void setRgdId(String rgdId) {
		this.rgdId = rgdId;
	}

	public String getZfinId() {
		return zfinId;
	}

	public void setZfinId(String zfinId) {
		this.zfinId = zfinId;
	}
}
