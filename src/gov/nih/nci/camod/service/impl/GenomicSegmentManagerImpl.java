/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.service.GenomicSegmentManager;
import gov.nih.nci.camod.webapp.form.GenomicSegmentForm;
import java.util.List;

public class GenomicSegmentManagerImpl extends BaseManager implements GenomicSegmentManager {
	
	  public List getAll() throws Exception {
	        log.trace("In GenomicSegmentManagerImpl.getAll");
	        return super.getAll(GenomicSegment.class);
	    }

	    public GenomicSegment get(String id) throws Exception {
	        log.trace("In GenomicSegmentManagerImpl.get");
	        return (GenomicSegment) super.get(id, GenomicSegment.class);
	    }

	    public void save(GenomicSegment GenomicSegment) throws Exception {
	        log.trace("In GenomicSegmentManagerImpl.save");
	        super.save(GenomicSegment);
	    }

	    public void remove(String id) throws Exception {
	        log.trace("In GenomicSegmentManagerImpl.remove");
	        super.remove(id, GenomicSegment.class);
	    }

	    public EngineeredGene create(GenomicSegmentForm inGenomicSegmentForm, AnimalModel inAnimalModel) throws Exception {

	        log.trace("Entering GenomicSegmentManagerImpl.create");

	        EngineeredGene theEngineeredGene = new GenomicSegment();

	        populateGenomicSegment(inGenomicSegmentForm, theEngineeredGene, inAnimalModel);
	       
	        log.trace("Exiting GenomicSegmentManagerImpl.create");
	        
	        return theEngineeredGene;
	    }

	    public void update(GenomicSegmentForm inGenomicSegmentData, EngineeredGene inEngineeredGene, AnimalModel inAnimalModel)
	            throws Exception {

	        log.trace("Entering GenomicSegmentManagerImpl.update");
	        log.debug("Updating GenomicSegmentForm: " + inEngineeredGene.getId());

	        // Populate w/ the new values and save
	        populateGenomicSegment(inGenomicSegmentData, inEngineeredGene, inAnimalModel);
	       // save(inGenomicSegment);

	        log.trace("Exiting GenomicSegmentManagerImpl.update");
	    }

	    private void populateGenomicSegment(GenomicSegmentForm inGenomicSegmentData, EngineeredGene inEngineeredGene, AnimalModel inAnimalModel)
	            throws Exception {
	    	
	        log.trace("Entering populateGenomicSegment");
	        	
	        log.trace("Exiting populateGenomicSegment");
	    }
}
