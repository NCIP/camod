/**
 * 
 * $Id: GenotypeManagerImpl.java,v 1.2 2007-02-01 19:07:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/10/17 16:13:47  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Genotype;
import gov.nih.nci.camod.service.GenotypeManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.List;

/**
 * @author pandyas
 * 
 * Impl class for the GenotypeManager
 */
public class GenotypeManagerImpl extends BaseManager implements GenotypeManager
{

    public List getAll() throws Exception
    {
        log.trace("In GenotypeManagerImpl.getAll");
        return super.getAll(Genotype.class);
    }

    /**
     * Get the Genotype by it's name
     * 
     * @param inName
     *            the name of the Genotype
     * 
     * @return the Genotype that matches the name
     */
    public Genotype getByName(String inName) throws Exception
    {        
        Genotype theGenotype = null;

        if (inName != null && inName.length() > 0)
        {
            log.info("<GenotypeManagerImpl.getByName> Entered inName: " +inName);              
            try
            {
                // The following two objects are needed for eQBE.
                Genotype theQueryObj = new Genotype();
                theQueryObj.setName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("Genotype.name", Evaluator.EQUAL);

                List theList = Search.query(theQueryObj, theEvaluation);

                if (theList != null && theList.size() > 0)
                {
                    theGenotype = (Genotype) theList.get(0);
                    log.info("<GenotypeManagerImpl.getByName> theGenotype: " +theGenotype);                    
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
        return theGenotype;
    }

    public Genotype getOrCreate(String inGenotypeName) throws Exception
    {

        log.info("<GenotypeManagerImpl> Entering getOrCreate(String)");

        Genotype theQBEGenotype = new Genotype();
        theQBEGenotype.setName(inGenotypeName);

        Genotype theGenotype = null;

        try
        {
            List theList = Search.query(theQBEGenotype);

            // Does exist - get object
            if (theList != null && theList.size() > 0)
            {
                theGenotype = (Genotype) theList.get(0);
            }
            // Doesn't exist. Create object with name 
            else
            {
                log.info("<GenotypeManagerImpl> No matching genotype. Create new one");
                theGenotype = theQBEGenotype;
                if (inGenotypeName != null)
                {
                    theQBEGenotype.setName(inGenotypeName);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error querying for matching genotype object.  Creating new one.", e);
            theGenotype = theQBEGenotype;
        }
        log.info("<GenotypeManagerImpl> theGenotype: " + theGenotype.toString());
        return theGenotype;
    }
}