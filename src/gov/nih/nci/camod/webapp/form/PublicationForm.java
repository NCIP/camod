/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PublicationForm extends BaseForm implements PublicationData, Serializable {
    
    private static final long serialVersionUID = 3257155453799404851L;
    
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public PublicationForm() {}
	
	protected String authors;
	protected String name;
	protected String pmid;
	protected String title;
	protected String year;
	protected String journal;
	protected String volume;
	protected String startPage;	
	protected String endPage;
	protected String firstTimeReported;
	
	/**
	 * @return Returns the authors.
	 */
	public String getFirstTimeReported() {
		return firstTimeReported;
	}
	/**
	 * @param authors The authors to set.
	 */
	public void setFirstTimeReported(String firstTimeReported) {
		this.firstTimeReported = firstTimeReported;
	}
	
	/**
	 * @return Returns the authors.
	 */
	public String getAuthors() {
		return authors;
	}
	/**
	 * @param authors The authors to set.
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the pmid.
	 */
	public String getPmid() {
		return pmid;
	}
	/**
	 * @param pmid The pmid to set.
	 */
	public void setPmid(String pmid) {
		this.pmid = pmid;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return Returns the year.
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year The year to set.
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return Returns the journal.
	 */
	public String getJournal() {
		return journal;
	}
	/**
	 * @param journal The journal to set.
	 */
	public void setJournal(String journal) {
		this.journal = journal;
	}
	/**
	 * @return Returns the volume.
	 */
	public String getVolume() {
		return volume;
	}
	/**
	 * @param volume The volume to set.
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}	
	/**
	 * @return Returns the startPage.
	 */
	public String getStartPage() {
		return startPage;
	}
	/**
	 * @param startPage The startPage to set.
	 */
	public void setStartPage(String startPage) {
		this.startPage = startPage;
	}	
	/**
	 * @return Returns the endPage.
	 */
	public String getEndPage() {
		return endPage;
	}
	/**
	 * @param endPage The endPage to set.
	 */
	public void setEndPage(String endPage) {
		this.endPage = endPage;
	}
}
