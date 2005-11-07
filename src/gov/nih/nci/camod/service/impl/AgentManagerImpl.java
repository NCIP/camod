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
	
    /**
     * Get a specific Agent by id
     * 
     * @param id
     *            the unique id for a Agent
     * 
     * @return the matching Agent object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Agent get(String id) throws Exception {
        log.trace("In AgentManagerImpl.get");
        return (Agent) super.get(id, Agent.class);
    }

    /**
     * Save Agent
     * 
     * @param Agent
     *            the Agent to save
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void save(Agent agent) throws Exception {
        log.debug("In AgentManagerImpl.save");
        super.save(agent);
    }

    /**
     * Remove a specific Agent by id
     * 
     * @param id
     *            the unique id for a Agent
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void remove(String id) throws Exception {
        log.debug("In AgentManagerImpl.save");
        super.remove(id, Agent.class);
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
