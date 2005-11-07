/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.MicroArrayData;
import gov.nih.nci.camod.service.MicroArrayDataManager;
import java.util.List;

/**
 * @author rajputs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MicroArrayDataManagerImpl extends BaseManager implements MicroArrayDataManager {
	
    /**
     * Get all MicroArrayData by id
     * 
     * 
     * @return the matching MicroArrayData objects, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public List getAll() throws Exception {
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
    public MicroArrayData get(String id) throws Exception {
        log.trace("In MicroArrayDataManagerImpl.get");
        return (MicroArrayData) super.get(id, MicroArrayData.class);
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
    public void save(MicroArrayData microArrayData) throws Exception {
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
    public void remove(String id) throws Exception {
        log.debug("In MicroArrayDataManagerImpl.save");
        super.remove(id, MicroArrayData.class);
    }
}
