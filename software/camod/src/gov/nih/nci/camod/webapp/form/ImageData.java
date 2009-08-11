/**
 * 
 * $Id: ImageData.java,v 1.10 2008-08-14 19:00:47 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.9  2008/08/14 06:29:18  schroedn
 * Image url feature added
 *
 * Revision 1.8  2007/10/31 17:28:21  pandyas
 * Fixed #8355 	Add comments field to every submission page
 *
 * Revision 1.7  2007/07/31 12:01:41  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.6  2007/04/20 17:51:04  pandyas
 * Modified to add Staining Method tree to Image submission
 *
 * Revision 1.5  2007/04/18 19:20:23  pandyas
 * Modified to add Staining Method tree to Image submission
 *
 * Revision 1.4  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.form;

import org.apache.struts.upload.FormFile;

public interface ImageData
{

    public String getDescription();

    public void setDescription(String description);

    public String getDescriptionOfConstruct();

    public void setDescriptionOfConstruct(String descriptionOfConstruct);

    public FormFile getFileLocation();

    public void setFileLocation(FormFile fileLocation);

	public String getUrl();

	public void setUrl(String url);

	public String getUrlAlternEntry();
	
	public void setUrlAlternEntry(String urlAlternEntry);
	
    public String getTitle();

    public void setTitle(String title);

    public String getImageId();

    public void setImageId(String imageId);

    public String getImageUrl();

    public void setImageUrl(String imageUrl);

    public String getThumbUrl();

    public void setThumbUrl(String thumbUrl);

    public String getStainingMethod();

    public void setStainingMethod(String stainingMethod);

    public String getStainingMethodCode();

    public void setStainingMethodCode(String stainingMethodCode);

    public String getStainingMethodName();

    public void setStainingMethodName(String stainingMethodName);
    
	public String getComments(); 

	public void setComments(String comments);

}
