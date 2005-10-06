/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.webapp.form.GenomicSegmentData;

import java.util.List;

public interface GenomicSegmentManager {
	public List getAll() throws Exception;
	
	public GenomicSegment get(String id) throws Exception;
	
	public void save(GenomicSegment GenomicSegment) throws Exception;
	
	public void remove(String id) throws Exception;
	
	public GenomicSegment create(GenomicSegmentData inGenomicSegmentData) throws Exception;
	
	public void update(GenomicSegmentData inGenomicSegmentData, GenomicSegment inGenomicSegment) throws Exception;
}
