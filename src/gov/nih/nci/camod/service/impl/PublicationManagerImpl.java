/*
 * $Id: PublicationManagerImpl.java,v 1.7 2005-10-27 12:52:50 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.PublicationStatus;
import gov.nih.nci.camod.service.PublicationManager;
import gov.nih.nci.camod.webapp.form.PublicationData;
import gov.nih.nci.common.persistence.Search;

import java.util.List;

/**
 * Manager for Publication objects
 */
public class PublicationManagerImpl extends BaseManager implements PublicationManager {

	public Publication create(PublicationData inPublicationData) throws Exception {
		Publication thePublication = new Publication();
		populate(inPublicationData, thePublication);

		return thePublication;
	}

	public void update(PublicationData inPublicationData, Publication inPublication) throws Exception {
		populate(inPublicationData, inPublication);
	}

	private void populate(PublicationData inPublicationData, Publication inPublication) throws Exception {

		inPublication.setAuthors(inPublicationData.getAuthors());
		inPublication.setTitle(inPublicationData.getTitle());
		inPublication.setJournal(inPublicationData.getJournal());
		inPublication.setVolume(inPublicationData.getVolume());

		String strPub = inPublicationData.getPmid().trim();
		inPublication.setPmid(Long.valueOf(strPub));

		strPub = inPublicationData.getStartPage().trim();
		inPublication.setStartPage(Long.valueOf(strPub));

		strPub = inPublicationData.getEndPage().trim();
		inPublication.setEndPage(Long.valueOf(strPub));

		strPub = inPublicationData.getYear().trim();
		inPublication.setYear(Long.valueOf(strPub));

		if (inPublicationData.getFirstTimeReported() != null && inPublicationData.getFirstTimeReported().equals("yes")) {
			inPublication.setFirstTimeReported(new Boolean(true));
		} else {
			inPublication.setFirstTimeReported(new Boolean(false));
		}

		PublicationStatus pubStatus = PublicationManagerSingleton.instance().getPublicationStatusByName(
				inPublicationData.getName());
		inPublication.setPublicationStatus(pubStatus);
	}

	public List getAll() throws Exception {

		log.trace("In PublicationManagerImpl.getAll");

		return super.getAll(Publication.class);
	}

	public Publication get(String id) throws Exception {
		log.trace("In PublicationManagerImpl.get");
		return (Publication) super.get(id, Publication.class);
	}

	public void save(Publication publication) throws Exception {
		log.trace("In PublicationManagerImpl.save");
		super.save(publication);
	}

	public PublicationStatus getPublicationStatusByName(String inName) throws Exception {

		log.trace("In PublicationManagerImpl.getPublicationStatusByName");

		PublicationStatus pubStatus = null;

		// The following two objects are needed for eQBE.
		PublicationStatus thePubStatus = new PublicationStatus();
		thePubStatus.setName(inName);

		List theList = Search.query(thePubStatus);

		if (theList != null && theList.size() > 0) {
			pubStatus = (PublicationStatus) theList.get(0);
		}

		return pubStatus;
	}

	public void remove(String id) throws Exception {
		log.trace("In PublicationManagerImpl.remove");
		super.remove(id, Publication.class);
	}
}
