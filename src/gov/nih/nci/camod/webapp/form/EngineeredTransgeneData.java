package gov.nih.nci.camod.webapp.form;

import org.apache.struts.upload.FormFile;

public interface EngineeredTransgeneData {

	public String getLocationOfIntegration();

	public void setLocationOfIntegration(String locationOfIntegration);
	
	public String getName();
	
	public void setName(String name);
	
	public String getScientificName();
	
	public void setScientificName(String scientificName);
	
	public String getNumberMGI();
	
	public void setNumberMGI(String numberMGI);
	
	public String getFunction();
	
	public void setFunction(String function);
	
	public String getConditionedBy();
	
	public void setConditionedBy(String conditionedBy);
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public String getComments();
	
	public void setComments(String comments);
	
	public FormFile getFileLocation();
	
	public void setFileLocation(FormFile fileLocation);
	
	public String getFileServerLocation();
	
	public void setFileServerLocation(String fileServerLocation);
	
	public String getTitle();
	
	public void setTitle(String title);
	
	public String getOtherLocationOfIntegration();
	
	public void setOtherLocationOfIntegration(String otherLocationOfIntegration);
	
	public String getOtherScientificName();
	
	public void setOtherScientificName(String otherScientificName);
	
	public String getPolyASignal_name();
	
	public void setPolyASignal_name(String polyASignal_name);
	
	public String getPolyASignal_otherSpecies();
	
	public void setPolyASignal_otherSpecies(String polyASignal_otherSpecies);
	
	public String getPolyASignal_species();
	
	public void setPolyASignal_species(String polyASignal_species);
	
	public String getSpliceSites_name();
	
	public void setSpliceSites_name(String spliceSites_name);
	
	public String getSpliceSites_otherSpecies();
	
	public void setSpliceSites_otherSpecies(String spliceSites_otherSpecies);
	
	public String getSpliceSites_species();
	
	public void setSpliceSites_species(String spliceSites_species);
	
	public String getTranscriptional1_name();
	
	public void setTranscriptional1_name(String transcriptional1_name);
	
	public String getTranscriptional1_otherSpecies();
	
	public void setTranscriptional1_otherSpecies(String transcriptional1_otherSpecies);
	
	public String getTranscriptional1_species();
	
	public void setTranscriptional1_species(String transcriptional1_species);
	
	public String getTranscriptional2_name();
	
	public void setTranscriptional2_name(String transcriptional2_name);
	
	public String getTranscriptional2_otherSpecies();
	
	public void setTranscriptional2_otherSpecies(String transcriptional2_otherSpecies);
	
	public String getTranscriptional2_species();
	
	public void setTranscriptional2_species(String transcriptional2_species);
	
	public String getTranscriptional3_name();
	
	public void setTranscriptional3_name(String transcriptional3_name);
	
	public String getTranscriptional3_otherSpecies();
	
	public void setTranscriptional3_otherSpecies(String transcriptional3_otherSpecies);

	public String getTranscriptional3_species();
	
	public void setTranscriptional3_species(String transcriptional3_species);

	public String getGeneFunctions();
	
	public void setGeneFunctions(String geneFunctions);
	
	public String getDescriptionOfConstruct();
	
	public void setDescriptionOfConstruct(String descriptionOfConstruct);
}
