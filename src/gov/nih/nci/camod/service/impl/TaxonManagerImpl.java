/*
 * Created on Aug 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.domain.Strain;
import gov.nih.nci.camod.service.TaxonManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import org.hibernate.eqbe.*;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TaxonManagerImpl extends BaseManager implements TaxonManager {

	public List getAll() {
		List taxons = null;
		
		try {
			taxons = Search.query(Taxon.class);
		} catch (Exception e) {
			System.out.println("Exception in TaxonManagerImpl.getAll");
			e.printStackTrace();
		}
		
		return taxons;
	}
	
	public Taxon get(String id) {
		Taxon taxon = null;
		
		try {
			taxon = (Taxon) Search.queryById(Taxon.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TaxonManagerImpl.get");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TaxonManagerImpl.get");
			e.printStackTrace();
		}
		
		return taxon;
	}
	
    public void save(Taxon taxon) {
    	try {
			Persist.save(taxon);			
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TaxonManagerImpl.save");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TaxonManagerImpl.save");
			e.printStackTrace();
		}
    }
    
    public void remove(String id) {
    	try {
			Persist.deleteById(Taxon.class, new Long(id));
		} catch (PersistenceException pe) {
			System.out.println("PersistenceException in TaxonManagerImpl.remove");
			pe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception in TaxonManagerImpl.remove");
			e.printStackTrace();
		}
    }	
    
    public List getStrains(Species species) {
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
			System.out.println("Exception in TaxonManagerImpl.getStrains");
			e.printStackTrace();
		}
		
		return strains;
    }
}
