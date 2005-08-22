/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.TherapyManager;
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
public class TherapyManagerImpl extends BaseManager implements TherapyManager {
	
	public List getAll() {		
		List therapies = null;
		
		try {
			therapies = Search.query(Therapy.class);
		} catch (Exception e) {
			System.out.println("Exception in TherapyManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return therapies;
	}
	
	public Therapy get(String id) {
		Therapy therapy = null;
		
		try {
			therapy = (Therapy) Search.queryById(Therapy.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TherapyManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TherapyManagerImpl.get");
			e.printStackTrace();
		}
		
		return therapy;
    }

    public void save(Therapy therapy) {    	
    	try {
			Persist.save(therapy);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TherapyManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TherapyManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    	
    	try {
			Persist.deleteById(Therapy.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TherapyManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TherapyManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
