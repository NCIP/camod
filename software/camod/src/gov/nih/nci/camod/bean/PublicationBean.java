/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *
 */
package gov.nih.nci.camod.bean;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.util.DateUtils;
import gov.nih.nci.camod.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Publication view bean
 *
 * @author tanq, pansu
 *
 */
public class PublicationBean extends FileBean {
	private static final String delimiter = ";";

	private List<Author> authors = new ArrayList<Author>();
	private Author theAuthor = new Author();
	private String primaryAuthor;
	
	protected String createdBy;


	private String displayName = "";

	public PublicationBean() {
		domainFile = new XmlPublication();
		domainFile.setUriExternal(false);
	}

	public PublicationBean(String id) {
		domainFile.setId(new Long(id));
	}

	public PublicationBean(XmlPublication publication) {
		super(publication);
		this.domainFile = publication;

		Collection<Author> authorCollection = publication.getAuthorCollection();
		if (authorCollection != null && authorCollection.size() > 0) {
			List<Author> authorslist = new ArrayList<Author>(authorCollection);
			Collections.sort(authorslist, new Comparator<Author>() {
				public int compare(Author o1, Author o2) {
					return (int) (o1.getCreatedDate().compareTo(o2
							.getCreatedDate()));
				}
			});
			authors = authorslist;
		}
	}

	public PublicationBean(XmlPublication publication, String[] sampleNames) {
		this(publication);
	}

	/**
	 * Copy PubMed data from source PublicationBean to this PublicationBean.
	 *
	 * @param source
	 * @param taget
	 */
	public void copyPubMedFieldsFromPubMedXML(PublicationBean source) {
		XmlPublication oldPub = (XmlPublication) this.getDomainFile();
		XmlPublication xmlPub = (XmlPublication) source.getDomainFile();

		oldPub.setPubMedId(xmlPub.getPubMedId());
		oldPub.setDescription(xmlPub.getDescription());
		oldPub.setTitle(xmlPub.getTitle());
		oldPub.setJournalName(xmlPub.getJournalName());
		oldPub.setStartPage(xmlPub.getStartPage());
		oldPub.setEndPage(xmlPub.getEndPage());
		oldPub.setVolume(xmlPub.getVolume());
		oldPub.setYear(xmlPub.getYear());
		this.setAuthors(source.getAuthors());
		
		if (authors != null && authors.size() > 0 ) {
			Author author = authors.get(0);
			setPrimaryAuthor( author.getFirstName() + " " + author.getLastName() + " " + author.getInitial() );
		}
	}

	public boolean equals(Object obj) {
		boolean eq = false;
		if (obj instanceof PublicationBean) {
			PublicationBean c = (PublicationBean) obj;
			Long thisId = this.domainFile.getId();
			if (thisId != null && thisId.equals(c.getDomainFile().getId())) {
				eq = true;
			}
		}
		return eq;
	}


	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}

	/**
	 * @param authors
	 *            the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Author getTheAuthor() {
		return theAuthor;
	}

	public void setTheAuthor(Author theAuthor) {
		this.theAuthor = theAuthor;
	}

		public void resetDomainCopy(String createdBy, XmlPublication copy) {
		// don't need to reset anything because publications can be shared
	}

	public String getPrimaryAuthor() {
		return primaryAuthor;
	}

	public void setPrimaryAuthor(String primaryAuthor) {
		this.primaryAuthor = primaryAuthor;
	}
	
	
}
