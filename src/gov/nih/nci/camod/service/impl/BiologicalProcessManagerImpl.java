/*
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/01/18 14:24:23  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.3  2005/11/14 14:18:13  georgeda
 * Cleanup
 *
 * 
 * $Id: BiologicalProcessManagerImpl.java,v 1.5 2006-04-17 19:11:05 pandyas Exp $
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.BiologicalProcess;
import gov.nih.nci.camod.service.BiologicalProcessManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.List;

public class BiologicalProcessManagerImpl extends BaseManager implements BiologicalProcessManager
{
    /**
     * Get the BiologicalProcess by it's name
     * 
     * @param inName
     *            the name of the Biological Processs
     * 
     * @return the BiologicalProcess that matches the name
     */
    public BiologicalProcess getByName(String inName) throws Exception
    {
        BiologicalProcess theBiologicalProcess = null;

        if (inName != null && inName.length() > 0)
        {
            try
            {
                // The following two objects are needed for eQBE.
                BiologicalProcess theQueryObj = new BiologicalProcess();
                theQueryObj.setName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("biologicalProcess.biologicalProcessName", Evaluator.EQUAL);

                List theList = Search.query(theQueryObj, theEvaluation);

                if (theList != null && theList.size() > 0)
                {
                    theBiologicalProcess = (BiologicalProcess) theList.get(0);
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
        return theBiologicalProcess;
    }

    /**
     * Get all BiologicalProcess by id
     * 
     * 
     * @return the matching BiologicalProcess objects, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public List getAll() throws Exception
    {
        log.trace("In BiologicalProcessManagerImpl.getAll");
        return super.getAll(BiologicalProcess.class);
    }

    /**
     * Get a specific BiologicalProcess by id
     * 
     * @param id
     *            the unique id for a BiologicalProcess
     * 
     * @return the matching BiologicalProcess object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public BiologicalProcess get(String id) throws Exception
    {
        log.trace("In BiologicalProcessManagerImpl.get");
        return (BiologicalProcess) super.get(id, BiologicalProcess.class);
    }

    /**
     * Save BiologicalProcess
     * 
     * @param BiologicalProcess
     *            the BiologicalProcess to save
     * 
     * @exception Exception
     *                when anything goes wrong.

    public void save(BiologicalProcess biologicalProcess) throws Exception
    {
        log.debug("In BiologicalProcessManagerImpl.save");
        super.save(biologicalProcess);
    }
    */
}
