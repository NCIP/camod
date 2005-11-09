/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.webapp.form.GenomicSegmentData;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface GenomicSegmentManager {
	
	public List getAll() throws Exception;
	
	public GenomicSegment get(String id) throws Exception;
	
	public void save(GenomicSegment GenomicSegment) throws Exception;
	
	public void remove(String id, AnimalModel inAnimalModel) throws Exception;
	
	public GenomicSegment create(AnimalModel inAnimalModel, GenomicSegmentData inGenomicSegmentData, HttpServletRequest request) throws Exception;
	
	public void update(AnimalModel inAnimalModel, GenomicSegmentData inGenomicSegmentData, GenomicSegment inGenomicSegment, HttpServletRequest request) throws Exception;
}
