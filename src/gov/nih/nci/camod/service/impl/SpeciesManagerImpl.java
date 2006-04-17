/**
 * 
 * $Id: SpeciesManagerImpl.java,v 1.1 2006-04-17 19:11:06 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.service.SpeciesManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;
import java.util.*;

public class SpeciesManagerImpl extends BaseManager implements SpeciesManager
{

    public List getAll() throws Exception {
        log.trace("In SpeciesManagerImpl.getAll");
        return super.getAll(Species.class);
    }
    

    public Species get(String id) throws Exception {
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


    public Species getOrCreate(String inScientificName) throws Exception {
        log.info("<SpeciesManagerImpl> Entering getOrCreate");
        
        Species theQBETaxon = new Species();
        theQBETaxon.setScientificName(inScientificName);

        Species theSpecies = null;
        try {
            List theList = Search.query(theQBETaxon);
            
            // Doesn't exist. Use the QBE taxon since it has the same data
            if (theList != null && theList.size() > 0) {
                theSpecies = (Species) theList.get(0);
            }
            else {
                theSpecies = theQBETaxon;
                theSpecies.setCommonName(getCommonNameFromScientificName(inScientificName));
            }
        } catch (Exception e) {
            log.error("Error querying for matching taxon object.  Creating new one.", e);
            theSpecies = theQBETaxon;
            theSpecies.setCommonName(getCommonNameFromScientificName(inScientificName));
        }

        return theSpecies;
    } 
 
    
    private String getCommonNameFromScientificName(String inScientificName) {

        log.info("<SpeciesManagerImpl> Entering getCommonNameFromScientificName");        
        String theCommonName = "";

        try {
            // List of species matching the scientificName in the species
            List species = null;

            // Species example to be used for searching
            Species spec = new Species();
            spec.setScientificName(inScientificName);

            // Get the matching Species
            species = Search.query(spec);

            if (species != null) {

                for (int i = 0; i < species.size(); i++) {
                    Species theSpecies = (Species) species.get(i);

                    if (theSpecies.getCommonName() != null && theSpecies.getCommonName().length() > 0) {
                        theCommonName = theSpecies.getCommonName();
                        break;
                    }
                }
            }

        } catch (Exception e) {
            log.error("Exception when fetching common name for species: " + e);
        }

        return theCommonName;
    }    

}
