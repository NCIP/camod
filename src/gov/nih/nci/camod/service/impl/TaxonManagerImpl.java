/*
 * Created on Aug 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.TaxonManager;
import gov.nih.nci.common.persistence.Search;

import java.util.*;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TaxonManagerImpl extends BaseManager implements TaxonManager {

    public Taxon create(String inScientificName, String inStrain, String inOtherStrain) {

        Taxon theTaxon = new Taxon();
        populate(theTaxon, inScientificName, inStrain, inOtherStrain);

        return theTaxon;
    }

    public void update(String inScientificName, String inStrain, String inOtherStrain, Taxon inTaxon) {
        populate(inTaxon, inScientificName, inStrain, inOtherStrain);
    }

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

            // Get the matching taxons
            taxons = Search.query(taxon);

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

    private void populate(Taxon inTaxon, String inScientificName, String inStrain, String inOtherStrain) {
        inTaxon.setScientificName(inScientificName);
        inTaxon.setCommonName(getCommonNameFromScientificName(inScientificName));
        inTaxon.setEthnicityStrain(inStrain);

        // Other is not selected, null out the uncontrolled vocab
        if (!inStrain.equals(Constants.Dropdowns.OTHER_OPTION)) {
            inTaxon.setEthnicityStrainUnctrlVocab(null);
        } else {
            inTaxon.setEthnicityStrainUnctrlVocab(inOtherStrain);
        }
    }

    private String getCommonNameFromScientificName(String inScientificName) {

        String theCommonName = "";

        try {
            // List of taxons matching the scientificName in the species
            List taxons = null;

            // Taxon example to be used for searching
            Taxon taxon = new Taxon();
            taxon.setScientificName(inScientificName);

            // Get the matching taxons
            taxons = Search.query(taxon);

            if (taxons != null) {

                for (int i = 0; i < taxons.size(); i++) {
                    Taxon theTaxon = (Taxon) taxons.get(i);

                    if (theTaxon.getCommonName() != null && theTaxon.getCommonName().length() > 0) {
                        theCommonName = theTaxon.getCommonName();
                        break;
                    }
                }
            }

        } catch (Exception e) {
            log.error("Exception when fetching common name: " + e);
        }

        return theCommonName;
    }
}
