/*
 * Created on Aug 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.TaxonManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TaxonManagerImpl extends BaseManager implements TaxonManager {

    public List getAll() throws Exception {
        log.trace("In TaxonManagerImpl.getAll");
        return super.getAll(Taxon.class);
    }

    public Taxon get(String id) throws Exception {
        log.trace("In TaxonManagerImpl.get");
        log.debug("Looking for taxon with id: " + id);
        return (Taxon) super.get(id, Taxon.class);
    }

    public void save(Taxon taxon) throws Exception {
        log.trace("In TaxonManagerImpl.save");
        super.save(taxon);
    }

    public void remove(String id) throws Exception {
        log.trace("In TaxonManagerImpl.remove");
        log.debug("Removing Taxon with id: " + id);
        super.remove(id, Taxon.class);
    }

    public List getStrains(Species species) throws Exception {
        List strains = null;

        try {
            // List of taxons matching the scientificName in the species
            List taxons = null;

            // Taxon example to be used for searching
            Taxon taxon = new Taxon();
            taxon.setScientificName(species.getName());

            // Apply evaluators to object properties
            Evaluation evaluation = new Evaluation();
            evaluation.addEvaluator("scientificName", Evaluator.ILIKE_ANYWHERE);

            // Get the matching taxons
            taxons = Search.query(taxon, evaluation);

            if (taxons != null && !taxons.isEmpty()) {
                strains = new ArrayList();

                Iterator taxonIterator = taxons.iterator();
                while (taxonIterator.hasNext()) {
                    taxon = (Taxon) taxonIterator.next();

                    Strain strain = new Strain();
                    strain.setName(taxon.getEthnicityStrain());
                    strains.add(strain);
                }
            }

        } catch (Exception e) {
            log.error("Exception when fetching strains: " + e);
            throw e;
        }

        return strains;
    }
    
    public String getCommonNameFromScientificName(String inScientificName) throws Exception {
        
    	String theCommonName = "";

        try {
            // List of taxons matching the scientificName in the species
            List taxons = null;

            // Taxon example to be used for searching
            Taxon taxon = new Taxon();
            taxon.setScientificName(inScientificName);

            // Get the matching taxons
            taxons = Search.query(taxon);

            if (taxons != null && !taxons.isEmpty()) {
            	
            	Taxon theTaxon = (Taxon) taxons.get(0);
            	theCommonName = theTaxon.getCommonName();
            }

        } catch (Exception e) {
            log.error("Exception when fetching common name: " + e);
            throw e;
        }

        return theCommonName;
    }
}
