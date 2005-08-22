/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.service.TargetedModificationManager;
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
public class TargetedModificationManagerImpl extends BaseManager implements TargetedModificationManager {
	
	public List getAll() {		
		List targetedModifications = null;
		
		try {
			targetedModifications = Search.query(TargetedModification.class);
		} catch (Exception e) {
			System.out.println("Exception in TargetedModificationManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return targetedModifications;
	}
	
	public TargetedModification get(String id) {
		TargetedModification targetedModification = null;
		
		try {
			targetedModification = (TargetedModification) 
				Search.queryById(TargetedModification.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TargetedModificationManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TargetedModificationManagerImpl.get");
			e.printStackTrace();
		}
		
		return targetedModification;
    }

    public void save(TargetedModification targetedModification) {    
    	try {
			Persist.save(targetedModification);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TargetedModificationManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TargetedModificationManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    	
    	try {
			Persist.deleteById(TargetedModification.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TargetedModificationManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TargetedModificationManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
