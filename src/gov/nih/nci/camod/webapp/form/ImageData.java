/**
 * 
 * $Id: ImageData.java,v 1.5 2007-04-18 19:20:23 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


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
	
	public String getOtherStainingMethod();
	
	public void setOtherStainingMethod(String otherStaining);
	
	public String getStainingMethod();
	
	public void setStainingMethod(String staining);
	
	public String getTitle();
	
	public void setTitle(String title);
    
    public String getImageId();
    
    public void setImageId(String imageId);

    public String getImageUrl();
    
    public void setImageUrl(String imageUrl);
    
    public String getThumbUrl();
    
    public void setThumbUrl(String thumbUrl);
    
    public String getStainingMethodCode();
    public void setStainingMethodCode(String stainingMethodCode);
    public String getStainingMethodName();
    public void setStainingMethodName(String stainingMethodName);    
    
}
