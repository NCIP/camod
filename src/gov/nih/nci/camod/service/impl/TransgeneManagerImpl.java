/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.TransgeneManager;
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
public class TransgeneManagerImpl extends BaseManager implements TransgeneManager {
	
	public List getAll() {		
		List transgenes = null;
		
		try {
			transgenes = Search.query(Transgene.class);
		} catch (Exception e) {
			System.out.println("Exception in TransgeneManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return transgenes;
	}
	
	public Transgene get(String id) {
		Transgene transgene = null;
		
		try {
			transgene = (Transgene) Search.queryById(Transgene.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TransgeneManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TransgeneManagerImpl.get");
			e.printStackTrace();
		}
		
		return transgene;
    }

    public void save(Transgene transgene) {    	
    	try {
			Persist.save(transgene);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TransgeneManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TransgeneManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    	
    	try {
			Persist.deleteById(Transgene.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TransgeneManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TransgeneManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
