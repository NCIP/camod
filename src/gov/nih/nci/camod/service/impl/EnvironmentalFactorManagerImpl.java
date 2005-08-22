/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.EnvironmentalFactor;
import gov.nih.nci.camod.service.EnvironmentalFactorManager;
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
public class EnvironmentalFactorManagerImpl extends BaseManager implements EnvironmentalFactorManager {
	
	public List getAll() {		
		List environmentalFactors = null;
		
		try {
			environmentalFactors = Search.query(EnvironmentalFactor.class);
		} catch (Exception e) {
			System.out.println("Exception in EnvironmentalFactorManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return environmentalFactors;
	}
	
	public EnvironmentalFactor get(String id) {
		EnvironmentalFactor environmentalFactor = null;
		
		try {
			environmentalFactor = (EnvironmentalFactor) 
				Search.queryById(EnvironmentalFactor.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in EnvironmentalFactorManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in EnvironmentalFactorManagerImpl.get");
			e.printStackTrace();
		}
		
		return environmentalFactor;
    }

    public void save(EnvironmentalFactor environmentalFactor) {    	
    	try {
			Persist.save(environmentalFactor);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in EnvironmentalFactorManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in EnvironmentalFactorManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) { 
    	try {
			Persist.deleteById(EnvironmentalFactor.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in EnvironmentalFactorManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in EnvironmentalFactorManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
