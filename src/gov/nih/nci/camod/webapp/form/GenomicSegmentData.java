/**
 * 
 * $Id: GenomicSegmentData.java,v 1.6 2006-04-17 19:09:19 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.webapp.form;

import org.apache.struts.upload.FormFile;

public interface GenomicSegmentData extends TransgeneData {
	
	public String getLocationOfIntegration();
	
	public void setLocationOfIntegration(String locationOfIntegration);
	
    public String getIsRandom();
	
    public void setIsRandom(String isRandom);
	
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
	
	public String getMgiNumber();
	
	public void setMgiNumber(String mgiNumber);
	
	public FormFile getFileLocation();
	
	public void setFileLocation(FormFile fileLocation);
	
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
    
    public String getSegmentId();
    
    public void setSegmentId(String segmentId);
    
    public String getImageUrl();
    
    public void setImageUrl(String imageUrl);
    
    public String getThumbUrl();
    
    public void setThumbUrl(String thumbUrl);
}
