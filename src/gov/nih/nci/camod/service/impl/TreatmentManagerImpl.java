/*
 * $Id: TreatmentManagerImpl.java,v 1.4 2006-04-17 19:11:05 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Treatment;
import gov.nih.nci.camod.service.TreatmentManager;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TreatmentManagerImpl extends BaseManager implements TreatmentManager {

    /**
     * Get a specific Treatment by id
     * 
     * @param id
     *            the unique id for a Treatment
     * 
     * @return the matching Treatment object, or null if not found.
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public Treatment get(String id) throws Exception {
        log.trace("In TreatmentManagerImpl.get");
        return (Treatment) super.get(id, Treatment.class);
    }

    /**
     * Save Treatment
     * 
     * @param Treatment
     *            the treatment to save
     * 
     * @exception Exception
     *                when anything goes wrong.
     */
    public void save(Treatment treatment) throws Exception {
        log.debug("In TreatmentManagerImpl.save");
        super.save(treatment);
    }

    public void remove(String id) throws Exception {
        log.trace("In TreatmentManagerImpl.remove");
        super.remove(id, Treatment.class);
    }
}
