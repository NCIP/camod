/*
 * $Id: GenomicSegmentManager.java,v 1.9 2006-04-17 19:13:16 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
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
