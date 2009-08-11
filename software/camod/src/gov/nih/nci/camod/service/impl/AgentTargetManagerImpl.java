/**
 * 
 * $Id: AgentTargetManagerImpl.java,v 1.5 2006-10-18 18:09:42 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2006/04/17 19:11:06  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

/*
 * Jun 17, 2005
 *
 * @author pandyas
 *
 * $Id: AgentTargetManagerImpl.java,v 1.5 2006-10-18 18:09:42 pandyas Exp $
 *
 *  $Log: not supported by cvs2svn $
 *  Revision 1.4  2006/04/17 19:11:06  pandyas
 *  caMod 2.1 OM changes
 *
 *  Revision 1.3  2006/01/18 14:24:24  georgeda
 *  TT# 376 - Updated to use new Java 1.5 features
 *
 *  Revision 1.2  2005/11/07 20:43:07  pandyas
 *  modified getAll(), save(), rmove() and/or getByName (if applicable) to the current signature that throws exceptions and calls the super
 *
 */

import gov.nih.nci.camod.domain.AgentTarget;
import gov.nih.nci.camod.service.AgentTargetManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.List;


public class AgentTargetManagerImpl extends BaseManager implements AgentTargetManager
{
    /**
     * Get the AgentTarget by it's name
     * 
     * @param inName
     *            the name of the AgentTarget
     * 
     * @return the AgentTarget that matches the name
     */
    public AgentTarget getByName(String inName) throws Exception
    {
        AgentTarget theAgentTarget = null;

        if (inName != null && inName.length() > 0)
        {
            try
            {
                // The following two objects are needed for eQBE.
                AgentTarget theQueryObj = new AgentTarget();
                theQueryObj.setName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("agentTarget.name", Evaluator.EQUAL);

                List theList = Search.query(theQueryObj, theEvaluation);

                if (theList != null && theList.size() > 0)
                {
                    theAgentTarget = (AgentTarget) theList.get(0);
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
        return theAgentTarget;
    }
}
