/**
 * 
 * @author pandyas
 * 
 * $Id: DiseaseManagerImpl.java,v 1.6 2007-09-12 19:36:03 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/04/21 13:39:12  georgeda
 * Cleanup
 *
 * Revision 1.4  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.3  2006/01/18 14:24:23  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.2  2005/11/07 20:43:07  pandyas
 * modified getAll(), save(), rmove() and/or getByName (if applicable) to the current signature that throws exceptions and calls the super
 *
 * Revision 1.1  2005/11/03 18:54:29  pandyas
 * Modified for histopathology screens
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Disease;
import gov.nih.nci.camod.service.DiseaseManager;
import gov.nih.nci.camod.util.EvsTreeUtil;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.List;

public class DiseaseManagerImpl extends BaseManager implements DiseaseManager
{
    public List getAll() throws Exception
    {
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
    public Disease getByName(String inName) throws Exception
    {
        Disease theDisease = null;

        if (inName != null && inName.length() > 0)
        {
            try
            {
                // The following two objects are needed for eQBE.
                Disease theQueryObj = new Disease();
                theQueryObj.setName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("Disease.inName", Evaluator.EQUAL);

                List theList = Search.query(theQueryObj, theEvaluation);

                if (theList != null && theList.size() > 0)
                {
                    theDisease = (Disease) theList.get(0);
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
        return theDisease;
    }

    public Disease getOrCreate(String inConceptCode,
                               String inDiseaseName) throws Exception
    {

        log.debug("<DiseaseManagerImpl> Entering getOrCreate");

        Disease theQBEDisease = new Disease();

        // Covers hack for text input of diseases, but reuses text match if found
        if (inConceptCode != null && !inConceptCode.contains("000000"))
        {
            theQBEDisease.setConceptCode(inConceptCode);
        }
        else
        {
            theQBEDisease.setConceptCode("000000");
            theQBEDisease.setName(inDiseaseName);
        }

        Disease theDisease = null;

        List theList = Search.query(theQBEDisease);

        // Doesn't exist. Use the QBE disease since it has the same data
        if (theList != null && theList.size() > 0)
        {
            theDisease = (Disease) theList.get(0);
        }
        else
        {
            log.debug("<DiseaseManagerImpl> No matching disease. Create new one");
            theDisease = theQBEDisease;

            // Get the preferred description
            if (inConceptCode != null && !inConceptCode.contains("000000"))
            {
                String thePreferredDiscription = EvsTreeUtil.getEVSPreferedDescription(inConceptCode);

                if (thePreferredDiscription != null && thePreferredDiscription.length() > 0)
                {
                    theDisease.setName(thePreferredDiscription);
                }
                else
                {
                    theDisease.setName(inDiseaseName);
                }
            }
        }

        return theDisease;
    }
}
