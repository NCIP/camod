/**
 * 
 *   The caMOD Software License, Version 1.0
 *
 *   Copyright 2005-2006 SAIC. This software was developed in conjunction with the National Cancer
 *   Institute, and so to the extent government employees are co-authors, any rights in such works
 *   shall be subject to Title 17 of the United States Code, section 105.
 *
 *   Redistribution and use in source and binary forms, with or without modification, are permitted
 *   provided that the following conditions are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice, this list of conditions
 *   and the disclaimer of Article 3, below.  Redistributions in binary form must reproduce the above
 *   copyright notice, this list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 *   2.  The end-user documentation included with the redistribution, if any, must include the
 *   following acknowledgment:
 *
 *   "This product includes software developed by the SAIC and the National Cancer
 *   Institute."
 *
 *   If no such end-user documentation is to be included, this acknowledgment shall appear in the
 *   software itself, wherever such third-party acknowledgments normally appear.
 *
 *   3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or
 *   promote products derived from this software.
 *
 *   4. This license does not authorize the incorporation of this software into any third party proprietary
 *   programs.  This license does not authorize the recipient to use any trademarks owned by either
 *   NCI or SAIC.
 *
 *
 *   5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED
 *   WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 *   MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE
 *   DISCLAIMED.  IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR
 *   THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *   EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *   PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *   PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 *   OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *   NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *   SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * $Id: TransInterferenceMethodManagerImpl.java,v 1.2 2006-10-18 18:10:02 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
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
					log.info("theTransIntMethod.getId: "
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
