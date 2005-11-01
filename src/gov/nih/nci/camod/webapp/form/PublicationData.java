/*
 * $Id: PublicationData.java,v 1.2 2005-11-01 18:14:28 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
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
public interface PublicationData {

	public String getFirstTimeReported();

	public void setFirstTimeReported(String firstTimeReported);

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
	
	public String getACellID();
	
	public void setACellID(String aCellID);
	
	public String getAPubID();
	
	public void setAPubID(String aPubID);
	
	public String getATherapyID();
	
	public void setATherapyID(String aTherapyID);
}
