package gov.nih.nci.camod.service.impl;
/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import gov.nih.nci.camod.domain.BiologicalProcess;
import gov.nih.nci.camod.service.BiologicalProcessManager;
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
public class BiologicalProcessManagerImpl extends BaseManager implements BiologicalProcessManager {

    /**
     * Get the BiologicalProcess by it's name
     * 
     * @param inName
     *            the name of the Biological Processs
     * 
     * @return the BiologicalProcess that matches the name
     */
    public BiologicalProcess getByName(String inName) {

    	BiologicalProcess theBiologicalProcess = null;

        try {

            // The following two objects are needed for eQBE.
        	BiologicalProcess theQueryObj = new BiologicalProcess();
            theQueryObj.setProcessName(inName);

            // Apply evaluators to object properties
            Evaluation theEvaluation = new Evaluation();
            theEvaluation.addEvaluator("biologicalProcess.biologicalProcessName", Evaluator.EQUAL);

            List theList = Search.query(theQueryObj, theEvaluation);

            if (theList != null && theList.size() > 0) {
                theBiologicalProcess = (BiologicalProcess) theList.get(0);
            }

        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in BiologicalProcessManagerImpl.getByType");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in BiologicalProcessManagerImpl.getByType");
            e.printStackTrace();
        }

        return theBiologicalProcess;
    }	
	
	public List getAll() {		
		List bioProcess = null;
		
		try {
			bioProcess = Search.query(BiologicalProcess.class);
		} catch (Exception e) {
			System.out.println("Exception in BiologicalProcessManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return bioProcess;
	}
	
	public BiologicalProcess get(String id) {
		BiologicalProcess bioProcess = null;
		
		try {
			bioProcess = (BiologicalProcess) Search.queryById(BiologicalProcess.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in BiologicalProcessManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in BiologicalProcessManagerImpl.get");
			e.printStackTrace();
		}
		
		return bioProcess;
    }

    public void save(BiologicalProcess bioProcess) {   
    	try {
			Persist.save(bioProcess);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in BiologicalProcessManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in BiologicalProcessManagerImpl.save");
			e.printStackTrace();
		}
    }

    public void remove(String id) {    
    	try {
			Persist.deleteById(BiologicalProcess.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in BiologicalProcessManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in BiologicalProcessManagerImpl.remove");
			e.printStackTrace();
		}
    }
}
