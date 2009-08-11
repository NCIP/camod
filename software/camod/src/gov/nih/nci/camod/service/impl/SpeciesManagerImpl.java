/**
 * 
 * $Id: SpeciesManagerImpl.java,v 1.6 2007-10-31 19:09:28 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2007/09/12 19:36:03  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.4  2006/05/03 20:03:41  pandyas
 * Removed commonName method since it is not used
 *
 * Revision 1.3  2006/04/20 18:11:30  pandyas
 * Cleaned up Species or Strain save of Other in DB
 *
 * Revision 1.2  2006/04/17 19:47:59  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.1  2006/04/17 19:11:06  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.service.SpeciesManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.*;

public class SpeciesManagerImpl extends BaseManager implements SpeciesManager
{

    public List getAll() throws Exception
    {
        log.trace("In SpeciesManagerImpl.getAll");
        return super.getAll(Species.class);
    }


    public Species get(String id) throws Exception
    {
        log.trace("In SpeciesManagerImpl.get");
        log.debug("Looking for Species with id: " + id);
        return (Species) super.get(id, Species.class);
    }

    /**
     * Get a specific Species by name
     * 
     * @param name
     *            the unique name for a Species
     * 
     * @return the matching Species, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Species getByName(String inName) throws Exception
    {
        Species species = null;

        if (inName != null && inName.length() > 0)
        {
            try
            {
                // The following two objects are needed for eQBE.
                Species theSpecies = new Species();
                theSpecies.setScientificName(inName);

                // Apply evaluators to object properties
                Evaluation theEvaluation = new Evaluation();
                theEvaluation.addEvaluator("species.ScientificName", Evaluator.EQUAL);

                List theSpeciesList = Search.query(theSpecies, theEvaluation);

                if (theSpeciesList != null && theSpeciesList.size() > 0)
                {
                    species = (Species) theSpeciesList.get(0);
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
        return species;
    }

    /**
     * Get a specific Species by name or create one
     * 
     * @param name
     *            the unique name for a Species
     * 
     * @return the matching Species, or create one if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     *                
     * Note:  Free text entered for otherSpecies will be saved as CommonNameAlternEntry               
     */
    public Species getOrCreate(String inScientificName,
                               String inOtherName) throws Exception
    {
        log.debug("<SpeciesManagerImpl> Entering getOrCreate");

        Species theQBESpecies = new Species();

        // If Other is selected, look for a match to the uncontrolled vocab
        // create new one either way if not found
        if (inScientificName != null && !inScientificName.equals(Constants.Dropdowns.OTHER_OPTION))
        {
            theQBESpecies.setScientificName(inScientificName);
        }
        else
        {
            theQBESpecies.setScientificNameAlternEntry(inOtherName);
        }

        Species theSpecies = null;
        try
        {
            List theList = Search.query(theQBESpecies);

            // Does exist, return object. 
            if (theList != null && theList.size() > 0)
            {
                theSpecies = (Species) theList.get(0);
            }
            //  Doesn't exist. Create object with either name or otherName set, don't save the word 'Other'
            else
            {
                theSpecies = theQBESpecies;
                if (inScientificName != null && !inScientificName.equals(Constants.Dropdowns.OTHER_OPTION))
                {
                    theQBESpecies.setScientificName(inScientificName);
                }
                else
                {
                    theQBESpecies.setScientificNameAlternEntry(inOtherName);
                }

            }
        }
        catch (Exception e)
        {
            log.error("Error querying for matching taxon object.  Creating new one.", e);
            theSpecies = theQBESpecies;
        }

        return theSpecies;
    }


}
