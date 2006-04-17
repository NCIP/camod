/**
 * 
 * $Id: StainingMethodManagerImpl.java,v 1.1 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.StainingMethod;
import gov.nih.nci.camod.service.StainingMethodManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.List;

public class StainingMethodManagerImpl extends BaseManager implements StainingMethodManager
{

    public List getAll() throws Exception
    {
        log.trace("In StainingMethod.getAll");
        return super.getAll(StainingMethod.class);
    }    

    public StainingMethod get(String id) throws Exception
    {
        log.trace("In StainingMethodManagerImpl.get");
        return (StainingMethod) super.get(id, StainingMethod.class);
    }    

    public StainingMethod getByName(String inName) throws Exception
    {
        StainingMethod stainingMethod = null;

        if (inName != null && inName.length() > 0)
        {
            try
            {
                // The following two objects are needed for eQBE.
                StainingMethod theStainingMethod = new StainingMethod();
                theStainingMethod.setName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("organ.name", Evaluator.EQUAL);

                List theList = Search.query(theStainingMethod, theEvaluation);

                if (theList != null && theList.size() > 0)
                {
                    stainingMethod = (StainingMethod) theList.get(0);
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
        return stainingMethod;
    }    
}
