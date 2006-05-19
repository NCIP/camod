/**
 * 
 * $Id: StrainManagerImpl.java,v 1.4 2006-05-19 16:39:33 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2006/04/20 18:11:30  pandyas
 * Cleaned up Species or Strain save of Other in DB
 *
 * Revision 1.2  2006/04/19 17:38:26  pandyas
 * Removed TODO text
 *
 * Revision 1.1  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.domain.Strain;
import gov.nih.nci.camod.service.StrainManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.*;


public class StrainManagerImpl extends BaseManager implements StrainManager
{

    public List getAll() throws Exception
    {
        log.trace("In StrainManagerImpl.getAll");
        return super.getAll(Strain.class);
    }


    public Strain get(String id) throws Exception
    {
        log.trace("In StrainManagerImpl.get");
        log.debug("Looking for Strain with id: " + id);
        return (Strain) super.get(id, Strain.class);
    }

    /**
     * Get a specific Strain by name
     * 
     * @param name
     *            the unique name for a Strain
     * 
     * @return the matching Strain, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Strain getByName(String inName) throws Exception
    {
        Strain strain = null;

        if (inName != null && inName.length() > 0)
        {
            try
            {
                // The following two objects are needed for eQBE.
                Strain theStrain = new Strain();
                theStrain.setName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("strain.name", Evaluator.EQUAL);

                List theList = Search.query(theStrain, theEvaluation);

                if (theList != null && theList.size() > 0)
                {
                    strain = (Strain) theList.get(0);
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
        return strain;
    }

    /**
     * Get or create a specific Strain by matching attributes
     * 
     * @param inStrainName
     *            the unique name for a Strain
     * @param inOtherStrainName
     *            the unique name for a Strain
     * @param inSpeciesName
     *            the unique scientific name for a Species
     * 
     * @return the matching Strain, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Strain getOrCreate(String inStrainName,
                              String inOtherStrainName,
                              String inSpeciesName) throws Exception
    {

        log.info("<StrainManagerImpl> Entering getOrCreate(String, String, String)");

        // need species_id to get correct strain - works for 'Not specified'
        Species selectedSpecies = SpeciesManagerSingleton.instance().getByName(inSpeciesName);

        Strain theQBEStrain = new Strain();
        theQBEStrain.setSpecies(selectedSpecies);

        // If Other is selected, look for a match to the uncontrolled vocab
        // create new one either way if not found
        if (inStrainName != null && !inStrainName.equals(Constants.Dropdowns.OTHER_OPTION))
        {
            theQBEStrain.setName(inStrainName);
        }
        else
        {
            theQBEStrain.setNameUnctrlVocab(inOtherStrainName);
        }

        Strain theStrain = null;
        try
        {
            List theList = Search.query(theQBEStrain);

            // Does exist - get object
            if (theList != null && theList.size() > 0)
            {
                theStrain = (Strain) theList.get(0);
            }
            // Doesn't exist. Create object with either name or otherName set, don't save the word 'Other'
            else
            {
                log.info("<StrainManagerImpl> No matching strains. Create new one");
                theStrain = theQBEStrain;
                if (inStrainName != null && !inStrainName.equals(Constants.Dropdowns.OTHER_OPTION))
                {
                    theQBEStrain.setName(inStrainName);
                }
                else
                {
                    theQBEStrain.setNameUnctrlVocab(inOtherStrainName);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error querying for matching strain object.  Creating new one.", e);
            theStrain = theQBEStrain;
        }

        return theStrain;
    }


    /**
     * Create a specific Strain by matching attributes
     * 
     * @param inStrainName
     *            the unique name for a Strain
     * @param inOtherStrainName
     *            the unctrl vocab for a Strain
     * @param inSpeciesName
     *            the unique name for a Species
     * @param inOtherSpeciesName
     *            the unctrl vocab for a Species
     *                  
     * @return the matching Strain, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     *                
     * This method is used to send in the newly created Species
     * when the user specifies other for species and Not Specified
     * for strain in the XenograftManagerImpl.  Reuse object if other text
     * is exactly the same (rare occurrance).               
     */
    public Strain getOrCreate(String inStrainName,
                              String inOtherStrainName,
                              String inSpeciesName,
                              String inOtherSpeciesName) throws Exception
    {

        log.info("<StrainManagerImpl> Entering getOrCreate(String, String, String)");

        // need species_id to get correct strain - works for 'Not specified'
        Species species = SpeciesManagerSingleton.instance().getOrCreate(inSpeciesName, inOtherSpeciesName);

        Strain theQBEStrain = new Strain();
        theQBEStrain.setSpecies(species);

        // If Other is selected, look for a match to the uncontrolled vocab
        // create new one either way if not found
        if (inStrainName != null && !inStrainName.equals(Constants.Dropdowns.OTHER_OPTION))
        {
            theQBEStrain.setName(inStrainName);
        }
        else
        {
            theQBEStrain.setNameUnctrlVocab(inOtherStrainName);
        }

        Strain theStrain = null;
        try
        {
            List theList = Search.query(theQBEStrain);

            // Does exist - get object
            if (theList != null && theList.size() > 0)
            {
                theStrain = (Strain) theList.get(0);
            }
            // Doesn't exist. Create object with either name or otherName set, don't save the word 'Other'
            else
            {
                log.info("<StrainManagerImpl> No matching strains. Create new one");
                theStrain = theQBEStrain;
                if (inStrainName != null && !inStrainName.equals(Constants.Dropdowns.OTHER_OPTION))
                {
                    theQBEStrain.setName(inStrainName);
                }
                else
                {
                    theQBEStrain.setNameUnctrlVocab(inOtherStrainName);
                }
            }
        }
        catch (Exception e)
        {
            log.error("Error querying for matching strain object.  Creating new one.", e);
            theStrain = theQBEStrain;
        }

        return theStrain;
    }

}
