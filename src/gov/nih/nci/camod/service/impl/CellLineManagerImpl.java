/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.service.CellLineManager;
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
public class CellLineManagerImpl extends BaseManager implements CellLineManager {
	
	public List getAll() {		
		List cellLines = null;
		
		try {
			cellLines = Search.query(CellLine.class);
		} catch (Exception e) {
			System.out.println("Exception in CellLineManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return cellLines;
	}
	
	public CellLine get(String id) {
		CellLine cellLine = null;
		
		try {
			cellLine = (CellLine) Search.queryById(CellLine.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in CellLineManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in CellLineManagerImpl.get");
			e.printStackTrace();
		}
		
		return cellLine;
    }

    public void save(CellLine cellLine) {   
    	try {
			Persist.save(cellLine);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in CellLineManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in CellLineManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    
    	try {
			Persist.deleteById(CellLine.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in CellLineManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in CellLineManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
