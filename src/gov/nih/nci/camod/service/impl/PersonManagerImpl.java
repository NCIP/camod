/**
 * @author dgeorge
 * 
 * $Id: PersonManagerImpl.java,v 1.5 2005-09-23 14:55:12 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.PersonManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.HibernateUtil;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.List;

/**
 * Class that handles saving/fetching Person objects
 */
public class PersonManagerImpl extends BaseManager implements PersonManager {

    /**
     * Get all people in the system
     * 
     * @returns a list of all people in the system
     * 
     * @exception Exception
     *                If an error occurrs fetching the users
     */
    public List getAll() throws Exception {
        List persons = null;

        try {
            persons = Search.query(Person.class);
        } catch (Exception e) {
            log.error("Exception in getAll", e);
            throw e;
        }

        return persons;
    }

    /**
     * Get a person object by unique id
     * 
     * @param id
     *            The username of the person to be fetched
     * 
     * @returns the Person object corresponding to the id
     * 
     * @exception Exception
     *                If an error occurrs fetching the user
     */
    public Person get(String id) throws Exception {
        Person person = null;

        try {
            person = (Person) Search.queryById(Person.class, new Long(id));
        } catch (PersistenceException pe) {
            log.error("PersistenceException in get", pe);
            throw pe;
        } catch (Exception e) {
            log.error("Exception in get", e);
            throw e;
        }

        return person;
    }

    /**
     * Get a person object by username
     * 
     * @param inUsername
     *            The username of the person to be fetched
     * 
     * @returns the Person object corresponding to the username
     * 
     * @exception Exception
     *                If an error occurrs fetching the user
     */
    public Person getByUsername(String inUsername) throws Exception {

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
            log.error("PersistenceException in getByUsername", pe);
            throw pe;
        } catch (Exception e) {
            log.error("Exception in getByUsername", e);
            throw e;
        }

        return person;
    }

    /**
     * Save the person object
     * 
     * @param person
     *            The person to be saved
     * 
     * @exception Exception
     *                If we're unable to save the person
     */
    public void save(Person person) throws Exception {

        log.trace("Entering save");

        try {

            // Begin an transaction
            HibernateUtil.beginTransaction();

            // Save the object
            Persist.save(person);

            // Commit an transaction
            HibernateUtil.commitTransaction();

        } catch (Exception e) {

            log.error("Exception in save", e);

            // Rollback a transaction
            HibernateUtil.rollbackTransaction();

            throw e;
        }

        log.trace("Exiting save");
    }

    /**
     * Remove the person based on the id
     * 
     * @param id
     *            The unique id for the Person
     * 
     * @exception Exception
     *                If it was unable to remove the person
     */
    public void remove(String id) throws Exception {
        try {
            // Begin an transaction
            HibernateUtil.beginTransaction();

            Persist.deleteById(Person.class, new Long(id));

            // Commit an transaction
            HibernateUtil.commitTransaction();

        } catch (Exception e) {
            log.error("Exception in getByUsername", e);

            // Rollback a transaction
            HibernateUtil.rollbackTransaction();

            throw e;
        }
    }
}
