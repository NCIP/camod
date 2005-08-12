/*
 * Created on Aug 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import java.util.List;
import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.service.SexDistributionManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SexDistributionManagerImpl extends BaseManager implements SexDistributionManager {
	
	public List getSexDistributions() {
		List sexDistributions = null;
		
		try {
			sexDistributions = Search.query(SexDistribution.class);
		} catch (Exception e) {
			System.out.println("Exception in SexDistributionManagerImpl.getSexDistributions()");
			e.printStackTrace();
		}
		
		return sexDistributions;
	}
	
	public SexDistribution getSexDistribution(String id) {
		SexDistribution sexDistribution = null;
		
		try {
			sexDistribution = (SexDistribution) Search.queryById(SexDistribution.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in SexDistributionManagerImpl.getSexDistribution(String id)");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in SexDistributionManagerImpl.getSexDistribution(String id)");
			e.printStackTrace();
		}
		
		return sexDistribution;
	}
	
    public void saveSexDistribution(SexDistribution sexDistribution) {
    	try {
			Persist.save(sexDistribution);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in SexDistributionManagerImpl.saveSexDistribution");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in SexDistributionManagerImpl.saveSexDistribution");
			e.printStackTrace();
		}
    }
    
    public void removeSexDistribution(String id) {
    	try {
			Persist.deleteById(SexDistribution.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in SexDistributionManagerImpl.removeSexDistribution");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in SexDistributionManagerImpl.removeSexDistribution");
			e.printStackTrace();
		}
    }	
}
