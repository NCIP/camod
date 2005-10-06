/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.service.GenomicSegmentManager;
import gov.nih.nci.camod.webapp.form.GenomicSegmentData;

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

	    public GenomicSegment create(GenomicSegmentData inGenomicSegmentData) throws Exception {

	        log.trace("Entering GenomicSegmentManagerImpl.create");

	        GenomicSegment inGenomicSegment= new GenomicSegment();

	        populateGenomicSegment(inGenomicSegmentData, inGenomicSegment);
	       
	        log.trace("Exiting GenomicSegmentManagerImpl.create");
	        
	        return inGenomicSegment;
	    }

	    public void update(GenomicSegmentData inGenomicSegmentData, GenomicSegment inGenomicSegment)
	            throws Exception {

	        log.trace("Entering GenomicSegmentManagerImpl.update");
	        log.debug("Updating GenomicSegmentForm: " + inGenomicSegment.getId());

	        // Populate w/ the new values and save
	        populateGenomicSegment(inGenomicSegmentData, inGenomicSegment);
	       // save(inGenomicSegment);

	        log.trace("Exiting GenomicSegmentManagerImpl.update");
	    }

	    private void populateGenomicSegment(GenomicSegmentData inGenomicSegmentData, GenomicSegment inGenomicSegment)
	            throws Exception {
	    	
	        log.trace("Entering populateGenomicSegment");
	        	
	        log.trace("Exiting populateGenomicSegment");
	    }
}
