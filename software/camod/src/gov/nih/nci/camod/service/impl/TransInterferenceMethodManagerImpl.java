/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: TransInterferenceMethodManagerImpl.java,v 1.3 2007-09-12 19:36:03 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/10/18 18:10:02  pandyas
 * removed getAll - not used
 *
 * Revision 1.1  2006/10/17 16:14:05  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.6  2006/05/08 13:33:28  georgeda
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.TransientInterferenceMethod;
import gov.nih.nci.camod.service.TransInterferenceMethodManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.List;

public class TransInterferenceMethodManagerImpl extends BaseManager implements
		TransInterferenceMethodManager {


	/**
	 * Get the TransientInterferenceMethod by it's ConceptCode
	 * 
	 * @param inConceptCode
	 *            the ConceptCode of the TransientInterferenceMethod
	 * 
	 * @return the TransientInterferenceMethod that matches the ConceptCode
	 */
	public TransientInterferenceMethod getByConceptCode(String inConceptCode)
			throws Exception {
        
		TransientInterferenceMethod theTransIntMethod = null;
        
        log .info("<TransientInterferenceMethod> inConceptCode" + inConceptCode);

		if (inConceptCode != null && inConceptCode.length() > 0) {
			try {
				log	.info("<TransientInterferenceMethod> inside try");
				// The following two objects are needed for eQBE.
				TransientInterferenceMethod theQueryObj = new TransientInterferenceMethod();
				theQueryObj.setConceptCode(inConceptCode);

				// Apply evaluators to object properties
				Evaluation theEvaluation = new Evaluation();
				theEvaluation.addEvaluator(
						"transientInterferenceMethod.conceptCode",
						Evaluator.EQUAL);

				List theList = Search.query(theQueryObj, theEvaluation);

				if (theList != null && theList.size() > 0) {
					theTransIntMethod = (TransientInterferenceMethod) theList
							.get(0);
					log.debug("theTransIntMethod.getId: "
							+ theTransIntMethod.getId());
				}
			} catch (PersistenceException pe) {
				log.error("PersistenceException in getByConceptCode", pe);
				throw pe;
			} catch (Exception e) {
				log.error("Exception in getByConceptCode", e);
				throw e;
			}
		}
		return theTransIntMethod;
	}

}
