/**
 * @author dgeorge
 * 
 * $Id: PersonManagerImpl.java,v 1.7 2005-10-17 13:14:11 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/10/11 18:13:36  georgeda
 * Return a null pointer if the username is null
 *
 * Revision 1.5  2005/09/23 14:55:12  georgeda
 * Made SexDistribution a reference table
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.service.PersonManager;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluation;
import gov.nih.nci.common.persistence.hibernate.eqbe.Evaluator;

import java.util.ArrayList;
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
    	log.trace("In PersonManagerImpl.getAll");
    	return super.getAll(Person.class);
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
    	log.trace("In PersonManagerImpl.get");
    	return (Person) super.get(id, Person.class);
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

        if (inUsername != null && inUsername.length() > 0) {
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
        }
        return person;
    }

    /**
     * Get all people assigned to a certain role.
     * 
     * @param inRole
     *            The role 
     * 
     * @returns a list of Person objects corresponding to the role
     * 
     * @exception Exception
     *                If an error occurrs fetching the user
     */
    public List getByRole(String inRole) throws Exception {

        List thePeople = new ArrayList();

        if (inRole != null && inRole.length() > 0) {
            try {
                thePeople = QueryManagerSingleton.instance().getPeopleByRole(inRole);
               
            } catch (PersistenceException pe) {
                log.error("PersistenceException in getByRole", pe);
                throw pe;
            } catch (Exception e) {
                log.error("Exception in getByRole", e);
                throw e;
            }
        }
        return thePeople;
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
    	log.trace("In PersonManagerImpl.save");
    	super.save(person);
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
    	log.trace("In PersonManagerImpl.remove");
        super.remove(id, Person.class);
    }
}
