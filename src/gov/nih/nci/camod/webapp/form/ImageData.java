package gov.nih.nci.camod.webapp.form;

import org.apache.struts.upload.FormFile;

public interface ImageData {

	public String getDescription();
	
	public void setDescription(String description);
	
	public String getDescriptionOfConstruct();
	
	public void setDescriptionOfConstruct(String descriptionOfConstruct);
	
	public FormFile getFileLocation();
	
	public void setFileLocation(FormFile fileLocation);
	
	public String getFileServerLocation();
	
	public void setFileServerLocation(String fileServerLocation);
	
	public String getOtherStaining();
	
	public void setOtherStaining(String otherStaining);
	
	public String getStaining();
	
	public void setStaining(String staining);
	
	public String getTitle();
	
	public void setTitle(String title);
    
    public String getImageId();
    
    public void setImageId(String imageId);

    public String getImageUrl();
    
    public void setImageUrl(String imageUrl);
    
    public String getThumbUrl();
    
    public void setThumbUrl(String thumbUrl);
    
}
