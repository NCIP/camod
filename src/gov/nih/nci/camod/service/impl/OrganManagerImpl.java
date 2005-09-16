/*
 * Created on Aug 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.OrganManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.List;

/**
 * @author rajputs
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class OrganManagerImpl extends BaseManager implements OrganManager {

    public List getAll() {
        List organs = null;

        try {
            organs = Search.query(Organ.class);
        } catch (Exception e) {
            System.out.println("Exception in OrganManagerImpl.getAll");
            e.printStackTrace();
        }

        return organs;
    }

    public Organ get(String id) {
        Organ organ = null;

        try {
            organ = (Organ) Search.queryById(Organ.class, new Long(id));
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in OrganManagerImpl.get");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in OrganManagerImpl.get");
            e.printStackTrace();
        }

        return organ;
    }

    public Organ getByName(String inName) {

        Organ organ = null;

        try {

            // The following two objects are needed for eQBE.
            Organ theOrgan = new Organ();
            theOrgan.setName(inName);

            // Apply evaluators to object properties
            Evaluation theEvaluation = new Evaluation();
            theEvaluation.addEvaluator("organ.name", Evaluator.EQUAL);

            List theList = Search.query(theOrgan, theEvaluation);

            if (theList != null && theList.size() > 0) {
                organ = (Organ) theList.get(0);
            }

        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in PersonManagerImpl.getByType");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in PersonManagerImpl.getByType");
            e.printStackTrace();
        }

        return organ;
    }

    public void save(Organ organ) {
        try {
            Persist.save(organ);
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in OrganManagerImpl.save");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in OrganManagerImpl.save");
            e.printStackTrace();
        }
    }

    public void remove(String id) {
        try {
            Persist.deleteById(Organ.class, new Long(id));
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in OrganManagerImpl.remove");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in OrganManagerImpl.remove");
            e.printStackTrace();
        }
    }
}
