package gov.nih.nci.camod.service.impl;

/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import gov.nih.nci.camod.domain.AgentTarget;
import gov.nih.nci.camod.service.AgentTargetManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.List;

/**
 * @author pandyas
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AgentTargetManagerImpl extends BaseManager implements AgentTargetManager {

    /**
     * Get the ChemicalClass by it's name
     * 
     * @param inName
     *            the name of the chemical class
     * 
     * @return the ChemicalClass that matches the name
     */
    public AgentTarget getByName(String inName) {

    	AgentTarget theAgentTarget = null;

        try {

            // The following two objects are needed for eQBE.
        	AgentTarget theQueryObj = new AgentTarget();
            theQueryObj.setTargetName(inName);

            // Apply evaluators to object properties
            Evaluation theEvaluation = new Evaluation();
            theEvaluation.addEvaluator("chemicalClass.chemicalClassName", Evaluator.EQUAL);

            List theList = Search.query(theQueryObj, theEvaluation);

            if (theList != null && theList.size() > 0) {
                theAgentTarget = (AgentTarget) theList.get(0);
            }

        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in AgentTargetManagerImpl.getByType");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in AgentTargetManagerImpl.getByType");
            e.printStackTrace();
        }

        return theAgentTarget;
    }	
	
	public List getAll() {		
		List agents = null;
		
		try {
			agents = Search.query(AgentTarget.class);
		} catch (Exception e) {
			System.out.println("Exception in AgentTargetManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return agents;
	}
	
	public AgentTarget get(String id) {
		AgentTarget agentTarget = null;
		
		try {
			agentTarget = (AgentTarget) Search.queryById(AgentTarget.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AgentTargetManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AgentTargetManagerImpl.get");
			e.printStackTrace();
		}
		
		return agentTarget;
    }

    public void save(AgentTarget agentTarget) {   
    	try {
			Persist.save(agentTarget);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AgentTargetManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AgentTargetManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    
    	try {
			Persist.deleteById(AgentTarget.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in AgentTargetManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in AgentTargetManagerImpl.remove");
			e.printStackTrace();
		}
    }
}

