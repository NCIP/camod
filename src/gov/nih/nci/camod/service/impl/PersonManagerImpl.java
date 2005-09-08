/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.PersonManager;
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
public class PersonManagerImpl extends BaseManager implements PersonManager {

    public List getAll() {
        List persons = null;

        try {
            persons = Search.query(Person.class);
        } catch (Exception e) {
            System.out.println("Exception in PersonManagerImpl.getAll");
        }

        return persons;
    }

    public Person get(String id) {
        Person person = null;

        try {
            person = (Person) Search.queryById(Person.class, new Long(id));
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in PersonManagerImpl.get");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in PersonManagerImpl.get");
            e.printStackTrace();
        }

        return person;
    }

    public Person getByUsername(String inUsername) {

        Person person = null;

        try {

            // The following two objects are needed for eQBE.
            Person thePerson = new Person();
            thePerson.setUsername(inUsername);

            // Apply evaluators to object properties
            Evaluation theEvaluation = new Evaluation();
            theEvaluation.addEvaluator("person.username", Evaluator.EQUAL);

            List thePersonList = Search.query(thePerson, theEvaluation);

            if (thePersonList != null && thePersonList.size() > 0) {
                person = (Person) thePersonList.get(0);
            }
            
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in PersonManagerImpl.getByUsername");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in PersonManagerImpl.getByUsername");
            e.printStackTrace();
        }

        return person;
    }

    public void save(Person person) {
        try {
            Persist.save(person);
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in PersonManagerImpl.save");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in PersonManagerImpl.save");
            e.printStackTrace();
        }
    }

    public void remove(String id) {
        try {
            Persist.deleteById(Person.class, new Long(id));
        } catch (PersistenceException pe) {
            System.out.println("PersistenceException in PersonManagerImpl.remove");
            pe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception in PersonManagerImpl.remove");
            e.printStackTrace();
        }
    }
}
