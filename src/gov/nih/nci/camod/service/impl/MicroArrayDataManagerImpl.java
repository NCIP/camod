/*
 * $Id: MicroArrayDataManagerImpl.java,v 1.6 2006-04-19 17:38:26 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2006/04/17 19:11:05  pandyas
 * caMod 2.1 OM changes
 *
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.MicroArrayData;
import gov.nih.nci.camod.service.MicroArrayDataManager;

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
    public void remove(String id) throws Exception
    {
        log.debug("In MicroArrayDataManagerImpl.save");
        super.remove(id, MicroArrayData.class);
    }
}
