/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * @author pandyas
 * 
 * $Id: DiseaseManagerImpl.java,v 1.2 2005-11-07 20:43:07 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/11/03 18:54:29  pandyas
 * Modified for histopathology screens
 *
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
    public Disease getByName(String inName) throws Exception {

    	Disease theDisease = null;
    	
        if (inName != null && inName.length() > 0) {    	

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
        		log.error("PersistenceException in getByName", pe);
        		throw pe;
        	} catch (Exception e) {
        		log.error("Exception in getByName", e);
        		throw e;
        	}
        }
        return theDisease;
    }	
	
}
