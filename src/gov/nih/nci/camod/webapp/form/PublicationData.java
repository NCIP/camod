/*
 * $Id: PublicationData.java,v 1.7 2008-08-14 19:00:17 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2008/08/14 06:29:54  schroedn
 * publication rgd number for rat feature added
 *
 * Revision 1.5  2007/10/31 17:29:04  pandyas
 * Fixed #8355 	Add comments field to every submission page
 *
 * Revision 1.4  2007/05/07 16:52:08  pandyas
 * Added code to save, edit and populate zfinPubId from Publication object for pulications from zfin.org
 *
 * Revision 1.3  2006/04/17 19:09:19  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.2  2005/11/01 18:14:28  schroedn
 * Implementing 'Enter Publication' for CellLines and Therapy, fixed many bugs with Publication. Remaining known bug with "Fill in Fields" button
 *
 * Revision 1.1  2005/10/27 12:53:09  georgeda
 * Refactor of publication manager
 *
 * 
 */
package gov.nih.nci.camod.webapp.form;


/**
 * 
 * Interface for publication data
 * 
 */
public interface PublicationData
{

    public String getAuthors();

    public void setAuthors(String authors);

    public String getName();

    public void setName(String name);

    public String getPmid();

    public void setPmid(String pmid);

    public String getTitle();

    public void setTitle(String title);

    public String getYear();

    public void setYear(String year);

    public String getJournal();

    public void setJournal(String journal);

    public String getVolume();

    public void setVolume(String volume);

    public String getStartPage();

    public void setStartPage(String startPage);

    public String getEndPage();

    public void setEndPage(String endPage);

    public String getFirstTimeReported();

    public void setFirstTimeReported(String firstTimeReported);

    public String getJaxJNumber();

    public void setJaxJNumber(String jaxJNumber);

    public String getACellID();

    public void setACellID(String aCellID);

    public String getAPubID();

    public void setAPubID(String aPubID);

    public String getATherapyID();

    public void setATherapyID(String aTherapyID);
    
	public String getZfinPubId(); 

	public void setZfinPubId(String zfinPubId); 
	
	public String getComments();

	public void setComments(String comments);	
	
	public String getRgdPubId();
	
	public void setRgdPubId(String rgdPubId);
}
