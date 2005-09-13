/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.PublicationStatus;

import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface PublicationManager {
	public List getAll();
	public Publication get(String id);
    public void save(Publication publication, PublicationStatus publicationStatus );
    public void savePublicationStatus( PublicationStatus publicationStatus );
    public void remove(String id);
    public PublicationStatus getByName(String inName);
}
