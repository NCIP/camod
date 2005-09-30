/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.cabio.domain.impl.AgentImpl;
import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.service.AgentManager;
import gov.nih.nci.camod.util.DrugScreenResult;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.system.applicationservice.ApplicationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AgentManagerImpl extends BaseManager implements AgentManager {
	
	public Agent get(String id) {
		Agent agent = null;
		
		try {
			agent = (Agent) Search.queryById(Agent.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AgentManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AgentManagerImpl.get");
			e.printStackTrace();
		}
		
		return agent;
    }

    public void save(Agent agent) {   
    	try {
			Persist.save(agent);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AgentManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AgentManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    
    	try {
			Persist.deleteById(Agent.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AgentManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AgentManagerImpl.remove");
			e.printStackTrace();
		}
    }

	public Collection getClinicalProtocols(Agent a) {
		ApplicationService appService = EvsTreeUtil.getApplicationService();
		Collection protocols = null;
		if (a != null) {
			Long nscNumber = a.getNscNumber();
			if (nscNumber != null) {
				// get clinical protocols from caBIO
				gov.nih.nci.cabio.domain.Agent agt = new AgentImpl();
				agt.setNSCNumber(nscNumber);
			    try {
			        List resultList = appService.search(gov.nih.nci.cabio.domain.Agent.class, agt);
					final int resultCount = (resultList!=null)?resultList.size():0;
					log.info("Got " + resultCount + " results....");
					for (Iterator resultsIterator = resultList.iterator();
		                resultsIterator.hasNext();) {
						gov.nih.nci.cabio.domain.Agent returnedAgt = 
								(gov.nih.nci.cabio.domain.Agent) resultsIterator.next();
						log.info("Returned Agent: " + returnedAgt.getNSCNumber());
						protocols = returnedAgt.getClinicalTrialProtocolCollection();
						if (protocols !=null) {
							log.info("Agent:" + returnedAgt.getName()
									+ "Protocols.size()" + protocols.size());
						}
					}
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		}
		return protocols;
	}

	public List getYeastResults(Agent a) {
		// get yeast results
		ArrayList yeastStages = new ArrayList();
		for(int k=0; k<=2; k++) {
			// do the query
			String stg = String.valueOf(k);
			log.info ("Calling getYeastScreenResults:" + a.getNscNumber() + " stage=" + stg);
			DrugScreenResult dsr=null;
			try {
				dsr = QueryManagerSingleton.instance().getYeastScreenResults(a, stg);
			} catch (PersistenceException e) {
				e.printStackTrace();
			}
			yeastStages.add(dsr);
		}
		return yeastStages;
	}
}
