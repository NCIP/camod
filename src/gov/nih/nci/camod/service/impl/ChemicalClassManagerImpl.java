/**
 * @author
 * 
 * $Id: ChemicalClassManagerImpl.java,v 1.5 2006-04-17 19:11:05 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/01/18 14:24:23  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.3  2005/11/07 20:43:07  pandyas
 * modified getAll(), save(), rmove() and/or getByName (if applicable) to the current signature that throws exceptions and calls the super
 *
 * Revision 1.2  2005/10/21 17:55:31  pandyas
 * fixed exception message
 *
 * Revision 1.1  2005/09/23 14:55:06  georgeda
 * Made SexDistribution a reference table
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.ChemicalClass;
import gov.nih.nci.camod.service.ChemicalClassManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.List;

/**
 * Manager provides lookup by name
 */
public class ChemicalClassManagerImpl extends BaseManager implements ChemicalClassManager
{
    /**
     * Get the ChemicalClass by it's name
     * 
     * @param inName
     *            the name of the chemical class
     * 
     * @return the ChemicalClass that matches the name
     */
    public ChemicalClass getByName(String inName) throws Exception
    {
        ChemicalClass theChemicalClass = null;

        if (inName != null && inName.length() > 0)
        {
            try
            {
                // The following two objects are needed for eQBE.
                ChemicalClass theQueryObj = new ChemicalClass();
                theQueryObj.setName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("chemicalClass.chemicalClassName", Evaluator.EQUAL);

                List theList = Search.query(theQueryObj, theEvaluation);

                if (theList != null && theList.size() > 0)
                {
                    theChemicalClass = (ChemicalClass) theList.get(0);
                }
            }
            catch (PersistenceException pe)
            {
                log.error("PersistenceException in getByName", pe);
                throw pe;
            }
            catch (Exception e)
            {
                log.error("Exception in getByName", e);
                throw e;
            }
        }
        return theChemicalClass;
    }
}
