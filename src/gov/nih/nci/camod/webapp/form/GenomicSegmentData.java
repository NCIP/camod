package gov.nih.nci.camod.webapp.form;

public interface GenomicSegmentData {
	
	public String getLocationOfIntegration();
	
	public void setLocationOfIntegration(String locationOfIntegration);
	
	public String getSegmentName();
	
	public void setSegmentName(String segmentName);
	
	public String getOtherSegmentName();
	
	public void setOtherSegmentName(String otherSegmentName);
	
	public String getSegmentSize();
	
	public void setSegmentSize(String segmentSize);
	
	public String getCloneDesignator();
	
	public void setCloneDesignator(String cloneDesignator);
	
	public String getComments();
	
	public void setComments(String comments);
	
	public String getNumberMGI();
	
	public void setNumberMGI(String numberMGI);
	
	public String getFileServerLocation();
	
	public void setFileServerLocation(String fileServerLocation);
	
	public String getTitle();
	
	public void setTitle(String title);
	
	public String getDescription();
	
	public void setDescription(String description);
	
	public String getGenes();
	
	public void setGenes(String genes);
	
	public String getMarkers();
	
	public void setMarkers(String markers) ;
	
	public String getDescriptionOfConstruct();
	
	public void setDescriptionOfConstruct(String descriptionOfConstruct);
}
