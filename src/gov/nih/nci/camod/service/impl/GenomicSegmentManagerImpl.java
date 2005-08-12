/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.service.GenomicSegmentManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GenomicSegmentManagerImpl extends BaseManager implements GenomicSegmentManager {
	
	public List getGenomicSegments() {		
		List genomicSegments = null;		
		return genomicSegments;
	}
	
	public GenomicSegment getGenomicSegment(String id) {
		GenomicSegment genomicSegment = null;
		return genomicSegment;
    }

    public void saveGenomicSegment(GenomicSegment genomicSegment) {    	
    }

    public void removeGenomicSegment(String id) {    	
    }
}
