package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.PublicationStatus;
import gov.nih.nci.camod.webapp.form.PublicationData;

import java.util.List;

public interface PublicationManager {

	public Publication create(PublicationData inPublicationData) throws Exception;

	public void update(PublicationData inPublicationData, Publication inPublication) throws Exception;

	public List getAll() throws Exception;

	public Publication get(String id) throws Exception;

	public void save(Publication publication) throws Exception;

	public void remove(String id) throws Exception;

	public PublicationStatus getPublicationStatusByName(String inName) throws Exception;
}
