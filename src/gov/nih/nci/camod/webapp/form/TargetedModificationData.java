package gov.nih.nci.camod.webapp.form;

public interface TargetedModificationData {
	public String getName();
	
	public void setName(String name);
	
	public String getModificationType();
	
	public void setModificationType(String modificationType);
	
	public String getOtherModificationType();
	
	public void setOtherModificationType(String otherModificationType);
	
	public String getGeneId();
	
	public void setGeneId(String geneId);
	
	public String getEsCellLineName();
	
	public void setEsCellLineName(String esCellLineName);
	
	public String getBlastocystName();
	
	public void setBlastocystName(String blastocystName);
	
	public String getConditionedBy();
	
	public void setConditionedBy(String conditionedBy);
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public String getComments();
	
	public void setComments(String comments);
	
	public String getNumberMGI();
	
	public void setNumberMGI(String numberMGI);
	
	public String getFileServerLocation();
	
	public void setFileServerLocation(String fileServerLocation);
	
	public String getTitle();
	
	public void setTitle(String title);
	
	public String getDescriptionOfConstruct();
	
	public void setDescriptionOfConstruct(String descriptionOfConstruct);
}
