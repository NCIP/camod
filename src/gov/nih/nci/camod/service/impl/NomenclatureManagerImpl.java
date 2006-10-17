/**
 * 
 * $Id: NomenclatureManagerImpl.java,v 1.1 2006-10-17 16:13:46 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Nomenclature;
import gov.nih.nci.camod.service.NomenclatureManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.List;

/**
 * @author pandyas
 * 
 * Impl class for the NomenclatureManager
 */
public class NomenclatureManagerImpl extends BaseManager implements NomenclatureManager {

	public List getAll() throws Exception {
		log.trace("In NomenclatureManagerImpl.getAll");
		return super.getAll(Nomenclature.class);
	}

	/**
	 * Get the Nomenclature by it's name
	 * 
	 * @param inName
	 *            the name of the Nomenclature
	 * 
	 * @return the Nomenclature that matches the name
	 */
	public Nomenclature getByName(String inName) throws Exception {
		Nomenclature theNomenclature = null;

		if (inName != null && inName.length() > 0) {
			try {
				// The following two objects are needed for eQBE.
				Nomenclature theQueryObj = new Nomenclature();
				theQueryObj.setName(inName);

				// Apply evaluators to object properties
				Evaluation theEvaluation = new Evaluation();
				theEvaluation.addEvaluator("Nomenclature.inName",
						Evaluator.EQUAL);

				List theList = Search.query(theQueryObj, theEvaluation);

				if (theList != null && theList.size() > 0) {
					theNomenclature = (Nomenclature) theList.get(0);
				}
			} catch (PersistenceException pe) {
				log.error("PersistenceException in getByName", pe);
				throw pe;
			} catch (Exception e) {
				log.error("Exception in getByName", e);
				throw e;
			}
		}
		return theNomenclature;
	}
	
	public Nomenclature getOrCreate(String inName) throws Exception {

		log.info("<NomenclatureManagerImpl> Entering getOrCreate(String, String, String)");

		Nomenclature theQBENomenclature = new Nomenclature();
		theQBENomenclature.setName(inName);

		Nomenclature theNomenclature = null;
		try {
			List theList = Search.query(theQBENomenclature);

			// Does exist - get object
			if (theList != null && theList.size() > 0) {
				theNomenclature = (Nomenclature) theList.get(0);
			}
			// Doesn't exist. Create object with either name or otherName set,
			// don't save the word 'Other'
			else {
				log.info("<StrainManagerImpl> No matching strains. Create new one");
				theNomenclature = theQBENomenclature;
				if (inName != null
						&& !inName
								.equals(Constants.Dropdowns.OTHER_OPTION)) {
					theQBENomenclature.setName(inName);
				} else {
					// theQBENomenclature.setNameUnctrlVocab(inOtherNomenclatureName);
				}
			}
		} catch (Exception e) {
			log
					.error(
							"Error querying for matching strain object.  Creating new one.",
							e);
			theNomenclature = theQBENomenclature;
		}

		return theNomenclature;
	}
	
	
}
