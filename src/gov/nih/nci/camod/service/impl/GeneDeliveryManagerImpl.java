/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.service.GeneDeliveryManager;
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
public class GeneDeliveryManagerImpl extends BaseManager implements GeneDeliveryManager {
	
	public List getAll() {		
		List geneDeliveries = null;
		
		try {
			geneDeliveries = Search.query(GeneDelivery.class);
		} catch (Exception e) {
			System.out.println("Exception in GeneDeliveryManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return geneDeliveries;
	}
	
	public GeneDelivery get(String id) {
		GeneDelivery geneDelivery = null;
		
		try {
			geneDelivery = (GeneDelivery) Search.queryById(GeneDelivery.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in GeneDeliveryManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in GeneDeliveryManagerImpl.get");
			e.printStackTrace();
		}
		
		return geneDelivery;
    }

    public void save(GeneDelivery geneDelivery) {   
    	try {
			Persist.save(geneDelivery);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in GeneDeliveryManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in GeneDeliveryManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    	
    	try {
			Persist.deleteById(GeneDelivery.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in GeneDeliveryManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in GeneDeliveryManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
