package gov.nih.nci.camod.webapp.form;

public interface InducedMutationData {
	
	public String getType();
	
	public void setType(String type);
	
	public String getOtherType();
	
	public void setOtherType(String otherType);
	
	public String getCASNumber();
	
	public void setCASNumber(String CASNumber);
	
	public String getGeneId();
	
	public void setGeneId(String geneId);
	
	public String getName();
	
	public void setName(String name);
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public String getObservation();
	
	public void setObservation(String observation);
	
	public String getMethodOfObservation();
	
	public void setMethodOfObservation(String methodOfObservation);
	
	public String getNumberMGI();
	
	public void setNumberMGI(String numberMGI);

}