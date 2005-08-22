/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.service.GenomicSegmentManager;
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
public class GenomicSegmentManagerImpl extends BaseManager implements GenomicSegmentManager {
	
	public List getAll() {		
		List genomicSegments = null;
		
		try {
			genomicSegments = Search.query(GenomicSegment.class);
		} catch (Exception e) {
			System.out.println("Exception in GenomicSegmentManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return genomicSegments;
	}
	
	public GenomicSegment get(String id) {
		GenomicSegment genomicSegment = null;
		
		try {
			genomicSegment = (GenomicSegment) Search.queryById(GenomicSegment.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in GenomicSegmentManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in GenomicSegmentManagerImpl.get");
			e.printStackTrace();
		}
		
		return genomicSegment;
    }

    public void save(GenomicSegment genomicSegment) {    
    	try {
			Persist.save(genomicSegment);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in GenomicSegmentManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in GenomicSegmentManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    	
    	try {
			Persist.deleteById(GenomicSegment.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in GenomicSegmentManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in GenomicSegmentManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
