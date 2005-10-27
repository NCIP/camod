/*
 * $Id: PublicationData.java,v 1.1 2005-10-27 12:53:09 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
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
}
