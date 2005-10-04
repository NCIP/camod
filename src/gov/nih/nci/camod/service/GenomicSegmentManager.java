/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.EngineeredGene;
import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.webapp.form.GenomicSegmentForm;

import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface GenomicSegmentManager {
	public List getAll() throws Exception;
	public GenomicSegment get(String id) throws Exception;
	public void save(GenomicSegment GenomicSegment) throws Exception;
	public void remove(String id) throws Exception;
	public EngineeredGene create(GenomicSegmentForm inGenomicSegmentForm, AnimalModel inAnimalModel) throws Exception;
	public void update(GenomicSegmentForm inGenomicSegmentData, EngineeredGene inEngineeredGene, AnimalModel inAnimalModel) throws Exception;
}
