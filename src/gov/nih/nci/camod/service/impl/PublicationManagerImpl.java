/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.service.PublicationManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PublicationManagerImpl extends BaseManager implements PublicationManager {
	
	public List getAll() {		
		List publications = null;
		
		try {
			publications = Search.query(Publication.class);
		} catch (Exception e) {
			System.out.println("Exception in PublicationManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return publications;
	}
	
	public Publication get(String id) {
		Publication publication = null;
		
		try {
			publication = (Publication) Search.queryById(Publication.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in PublicationManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in PublicationManagerImpl.get");
			e.printStackTrace();
		}
		
		return publication;
    }

    public void save(Publication publication) {    	
    	try {
			Persist.save(publication);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in PublicationManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in PublicationManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    	
    	try {
			Persist.deleteById(Publication.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in PublicationManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in PublicationManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
