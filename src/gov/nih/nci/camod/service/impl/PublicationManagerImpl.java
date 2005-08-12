/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.service.PublicationManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PublicationManagerImpl extends BaseManager implements PublicationManager {
	
	public List getPublications() {		
		List publications = null;		
		return publications;
	}
	
	public Publication getPublication(String id) {
		Publication publication = null;
		return publication;
    }

    public void savePublication(Publication publication) {    	
    }

    public void removePublication(String id) {    	
    }
}
