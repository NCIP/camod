/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.MicroArrayData;
import gov.nih.nci.camod.service.MicroArrayDataManager;
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
public class MicroArrayDataManagerImpl extends BaseManager implements MicroArrayDataManager {
	
	public List getAll() {		
		List microArrayDatas = null;
		
		try {
			microArrayDatas = Search.query(MicroArrayData.class);
		} catch (Exception e) {
			System.out.println("Exception in MicroArrayDataManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return microArrayDatas;
	}
	
	public MicroArrayData get(String id) {
		MicroArrayData microArrayData = null;
		
		try {
			microArrayData = (MicroArrayData) Search.queryById(MicroArrayData.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in MicroArrayDataManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in MicroArrayDataManagerImpl.get");
			e.printStackTrace();
		}
		
		return microArrayData;
    }

    public void save(MicroArrayData microArrayData) {    	
    	try {
			Persist.save(microArrayData);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in MicroArrayDataManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in MicroArrayDataManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    	
    	try {
			Persist.deleteById(MicroArrayData.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in MicroArrayDataManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in MicroArrayDataManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
