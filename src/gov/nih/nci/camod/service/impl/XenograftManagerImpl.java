/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.XenograftManager;
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
public class XenograftManagerImpl extends BaseManager implements XenograftManager {
	
	public List getAll() {		
		List xenografts = null;
		
		try {
			xenografts = Search.query(Xenograft.class);
		} catch (Exception e) {
			System.out.println("Exception in XenograftManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return xenografts;
	}
	
	public Xenograft get(String id) {
		Xenograft xenograft = null;
		
		try {
			xenograft = (Xenograft) Search.queryById(Xenograft.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in XenograftManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in XenograftManagerImpl.get");
			e.printStackTrace();
		}
		
		return xenograft;
    }

    public void save(Xenograft xenograft) {    	
    	try {
			Persist.save(xenograft);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in XenograftManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in XenograftManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    	
    	try {
			Persist.deleteById(Xenograft.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in XenograftManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in XenograftManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
