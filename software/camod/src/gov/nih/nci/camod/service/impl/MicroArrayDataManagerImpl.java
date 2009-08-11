/*
 * $Id: MicroArrayDataManagerImpl.java,v 1.8 2008-08-14 20:09:18 schroedn Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2008/08/14 06:28:21  schroedn
 * Microarraydata url
 *
 * Revision 1.6  2006/04/19 17:38:26  pandyas
 * Removed TODO text
 *
 * Revision 1.5  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CellLine;
import gov.nih.nci.camod.domain.MicroArrayData;
import gov.nih.nci.camod.service.MicroArrayDataManager;
import gov.nih.nci.camod.webapp.form.CellLineData;
import gov.nih.nci.camod.webapp.form.MicroArrayDataData;

import java.util.List;

/**
 * @author rajputs
 */
public class MicroArrayDataManagerImpl extends BaseManager implements MicroArrayDataManager
{
    /**
     * Get all MicroArrayData by id
     * 
     * 
     * @return the matching MicroArrayData objects, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public List getAll() throws Exception
    {
        log.trace("In MicroArrayDataManagerImpl.getAll");
        return super.getAll(MicroArrayData.class);
    }

    /**
     * Get a specific MicroArrayData by id
     * 
     * @param id
     *            the unique id for a MicroArrayData
     * 
     * @return the matching MicroArrayData object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public MicroArrayData get(String id) throws Exception
    {
        log.trace("In MicroArrayDataManagerImpl.get");
        return (MicroArrayData) super.get(id, MicroArrayData.class);
    }

	public MicroArrayData create(MicroArrayDataData inMicroArrayData) throws Exception {
		log.debug("Entering MicroArrayDataImpl.create");

		MicroArrayData theMicroArrayData = new MicroArrayData();
		populateMicroArrayData(inMicroArrayData, theMicroArrayData);
		
		log.debug("Exiting MicroArrayDataImpl.create");

		return theMicroArrayData;
	}

	
    /**
     * Save MicroArrayData
     * 
     * @param MicroArrayData
     *            the MicroArrayData to save
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void save(MicroArrayData microArrayData) throws Exception
    {
        log.debug("In MicroArrayDataManagerImpl.save");
        super.save(microArrayData);
    }

    /**
     * Remove a specific MicroArrayData by id
     * 
     * @param id
     *            the unique id for a MicroArrayData
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void remove(String inId, AnimalModel inAnimalModel) throws Exception
    {
        log.debug("In MicroArrayDataManagerImpl.save");
		inAnimalModel.getMicroArrayDataCollection().remove(get(inId));
		super.save(inAnimalModel);
    }   
    
	public void update(MicroArrayDataData inMicroArrayDataData, MicroArrayData inMicroArrayData)
		throws Exception {
		
		log.debug("Entering MicroArrayDataManagerImpl.update");
			
		// Populate w/ the new values and save
		populateMicroArrayData(inMicroArrayDataData, inMicroArrayData);
		
		save(inMicroArrayData);
		
		log.debug("Exiting MicroArrayDataManagerImpl.update");
	}   
	
	// Common method used to populate a CellLine object
	private void populateMicroArrayData(MicroArrayDataData inMicroArrayDataData,
			MicroArrayData inMicroArrayData) throws Exception {
		log.debug("Entering populateMicroArrayData");

		inMicroArrayData.setExperimentName(inMicroArrayDataData.getExperimentName());
		inMicroArrayData.setUrl(inMicroArrayDataData.getUrl());

		log.debug("Exiting populateMicroArrayData");
	}
}
