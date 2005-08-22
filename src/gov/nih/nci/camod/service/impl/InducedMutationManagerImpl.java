/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.service.InducedMutationManager;
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
public class InducedMutationManagerImpl extends BaseManager implements InducedMutationManager {
	
	public List getAll() {		
		List inducedMutations = null;
		
		try {
			inducedMutations = Search.query(InducedMutation.class);
		} catch (Exception e) {
			System.out.println("Exception in InducedMutationManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return inducedMutations;
	}
	
	public InducedMutation get(String id) {
		InducedMutation inducedMutation = null;
		
		try {
			inducedMutation = (InducedMutation) Search.queryById(InducedMutation.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in InducedMutationManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in InducedMutationManagerImpl.get");
			e.printStackTrace();
		}
		
		return inducedMutation;
    }

    public void save(InducedMutation inducedMutation) {    	
    	try {
			Persist.save(inducedMutation);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in InducedMutationManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in InducedMutationManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    	
    	try {
			Persist.deleteById(InducedMutation.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in InducedMutationManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in InducedMutationManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
