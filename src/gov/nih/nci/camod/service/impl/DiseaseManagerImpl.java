/**
 * 
 * @author pandyas
 * 
 * $Id: DiseaseManagerImpl.java,v 1.1 2005-11-03 18:54:29 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Disease;
import gov.nih.nci.camod.service.DiseaseManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.List;

public class DiseaseManagerImpl extends BaseManager implements DiseaseManager {

	public List getAll() throws Exception {
		log.trace("In DiseaseManagerImpl.getAll");
		return super.getAll(Disease.class);
	}
	
	/**
     * Get the Disease by it's name
     * 
     * @param inName
     *            the name of the Disease
     * 
     * @return the Disease that matches the name
     */
    public Disease getByName(String inName) {

    	Disease theDisease = null;

        try {

            // The following two objects are needed for eQBE.
        	Disease theQueryObj = new Disease();
            theQueryObj.setName(inName);

            // Apply evaluators to object properties
            Evaluation theEvaluation = new Evaluation();
            theEvaluation.addEvaluator("Disease.inName", Evaluator.EQUAL);

            List theList = Search.query(theQueryObj, theEvaluation);

            if (theList != null && theList.size() > 0) {
                theDisease = (Disease) theList.get(0);
            }

        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in DiseaseManagerImpl.getByName");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in DiseaseManagerImpl.getByName");
            e.printStackTrace();
        }

        return theDisease;
    }	
	
}
