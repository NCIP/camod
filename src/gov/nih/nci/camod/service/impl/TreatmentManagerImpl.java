/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Treatment;
import gov.nih.nci.camod.service.TreatmentManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class TreatmentManagerImpl extends BaseManager implements TreatmentManager {

    public Treatment get(String id) {
        Treatment treatment = null;

        try {
            treatment = (Treatment) Search.queryById(Treatment.class, new Long(id));
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in TreatmentManagerImpl.get");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in TreatmentManagerImpl.get");
            e.printStackTrace();
        }

        return treatment;
    }

    public void save(Treatment treatment) {
        try {
            Persist.save(treatment);
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in TreatmentManagerImpl.save");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in TreatmentManagerImpl.save");
            e.printStackTrace();
        }
    }

    public void remove(String id) {
        try {
            Persist.deleteById(Treatment.class, new Long(id));
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in TreatmentManagerImpl.remove");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in TreatmentManagerImpl.remove");
            e.printStackTrace();
        }
    }
}
