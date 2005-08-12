/*
 * Created on Jun 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.webapp.form;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PublicationForm extends BaseForm implements Serializable {
	/**
	 * Default empty constructor
	 * @author rajputs
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public PublicationForm() {}
	
	// id, endPage, year, pmid, and startPage are of type String since they 
	// come from the presentation layer
	protected String id;
	protected String volume;
	protected String endPage;
	protected String year;
	protected String title;
	protected String pmid;
	protected String startPage;
	protected String journal;
	protected String authors;
		
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
	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
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
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
    }
}
