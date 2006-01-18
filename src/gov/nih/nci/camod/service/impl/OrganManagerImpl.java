/**
 * 
 * $Id: OrganManagerImpl.java,v 1.6 2006-01-18 14:24:24 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/11/07 20:43:07  pandyas
 * modified getAll(), save(), rmove() and/or getByName (if applicable) to the current signature that throws exceptions and calls the super
 *
 * Revision 1.4  2005/11/03 18:55:44  pandyas
 * added Docs
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.OrganManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.List;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class OrganManagerImpl extends BaseManager implements OrganManager
{
    /**
     * Get all Organ by id
     * 
     * 
     * @return the matching Organ objects, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public List getAll() throws Exception
    {
        log.trace("In OrganManagerImpl.getAll");
        return super.getAll(Organ.class);
    }

    /**
     * Get a specific Organ by id
     * 
     * @param id
     *            the unique id for a Organ
     * 
     * @return the matching Organ object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Organ get(String id) throws Exception
    {
        log.trace("In OrganManagerImpl.get");
        return (Organ) super.get(id, Organ.class);
    }

    /**
     * Get a specific Organ by name
     * 
     * @param name
     *            the unique name for a Organ
     * 
     * @return the matching Organ, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Organ getByName(String inName) throws Exception
    {
        Organ organ = null;

        if (inName != null && inName.length() > 0)
        {
            try
            {
                // The following two objects are needed for eQBE.
                Organ theOrgan = new Organ();
                theOrgan.setName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("organ.name", Evaluator.EQUAL);

                List theList = Search.query(theOrgan, theEvaluation);

                if (theList != null && theList.size() > 0)
                {
                    organ = (Organ) theList.get(0);
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
        return organ;
    }

    /**
     * Save Organ
     * 
     * @param Organ
     *            the Organ to save
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void save(Organ organ) throws Exception
    {
        log.debug("In OrganManagerImpl.save");
        super.save(organ);
    }
}
