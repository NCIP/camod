/**
 *
 * @author pandyas
 * 
 * $Id: AnimalDistributorManagerImpl.java,v 1.1 2005-10-26 20:14:52 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import java.util.List;
import gov.nih.nci.camod.domain.AnimalDistributor;
import gov.nih.nci.camod.service.AnimalDistributorManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

public class AnimalDistributorManagerImpl extends BaseManager implements AnimalDistributorManager {

    /**
     * Get a AnimalDistributor object by Name
     * 
     * @param inName
     *            The Name of the AnimalDistributor to be fetched
     * 
     * @returns the AnimalDistributor object corresponding to the Name
     * 
     * @exception Exception
     *                If an error occurrs fetching the Name
     */	
    public AnimalDistributor getByName(String inName) throws Exception {
    	AnimalDistributor animalDistributor = null;

        if (inName != null && inName.length() > 0) {
            try {

                // The following two objects are needed for eQBE.
            	AnimalDistributor theAnimalDistributor = new AnimalDistributor();
            	theAnimalDistributor.setName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("animalDistributor.name", Evaluator.EQUAL);

                List theDistributorList = Search.query(theAnimalDistributor, theEvaluation);

                if (theDistributorList != null && theDistributorList.size() > 0) {
                	animalDistributor = (AnimalDistributor) theDistributorList.get(0);
                }

            } catch (PersistenceException pe) {
                log.error("PersistenceException in AnimalDistributorManagerImpl.getByName", pe);
                throw pe;
            } catch (Exception e) {
                log.error("Exception in AnimalDistributorManagerImpl.getByName", e);
                throw e;
            }
        }
        return animalDistributor;
    }	

}
