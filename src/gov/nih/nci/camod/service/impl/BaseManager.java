/**
 * @author dgeorge
 * 
 * $Id: BaseManager.java,v 1.2 2005-09-26 14:02:38 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.service.Manager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.HibernateUtil;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Base class for managers.  Provides the generic Object based calls.
 */
public class BaseManager implements Manager {
    protected final Log log = LogFactory.getLog(getClass());
    
    /**
     * Get all of the objects models in the DB
     * 
     * @return the list of all objects
     * 
     * @exception throws
     *                an Exception if an error occurred
     */
    public List getAll(Class inClass) throws Exception {

        log.trace("Entering BaseManager.getAll");

        List theObjects = null;

        try {
            theObjects = Search.query(inClass);
        } catch (Exception e) {
            log.error("Exception occurred in BaseManager.getAll", e);
            throw e;
        }

        log.trace("Exiting BaseManager.getAll");

        return theObjects;
    }

    /**
     * Get a specific object by unique ID
     * 
     * @param id
     *            The unique id for the object
     * 
     * @return the object if found, null otherwise
     * @throws Exception
     * 
     * @exception Exception
     *                if an error occurred
     */
    public Object get(String inId, Class inClass) throws Exception {

        log.trace("Entering BaseManager.get");

        Object theObject = null;

        try {
            log.debug("Querying for id: " + inId);
            theObject = Search.queryById(inClass, new Long(inId));
        } catch (PersistenceException pe) {
            log.error("Exception occurred in BaseManager.get", pe);
            throw pe;
        } catch (Exception e) {
            log.error("Exception occurred in BaseManager.get", e);
            throw e;
        }

        log.trace("Exiting BaseManager.get");
        return theObject;
    }

    /**
     * Save an object
     * 
     * @param inObject
     *            The object to save
     * 
     * @exception Exception
     *                if an error occurred
     */
    public void save(Object inObject) throws Exception {

        log.trace("Entering BaseManager.save");

        try {
            // Begin an transaction
            HibernateUtil.beginTransaction();

            //Save the object
            log.debug("Saving object");
            Persist.save(inObject);

            // Commit all changes or none
            HibernateUtil.commitTransaction();

        } catch (PersistenceException pe) {
            HibernateUtil.rollbackTransaction();
            log.error("PersistenceException in BaseManager.save", pe);
            throw pe;
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            log.error("Exception in BaseManager.save", e);
            throw e;
        }
    }

    /**
     * Remove an object from the system. Should remove all associated
     * data as well
     * 
     * @param inId
     *            The unique id of the object to delete
     * 
     * @throws Exception
     *             An error occurred when attempting to delete the object
     */
    public void remove(String inId, Class inClass) throws Exception {

        log.trace("Entering BaseManager.remove");

        try {
            log.debug("Removing object: " + inId);
            Persist.deleteById(inClass, new Long(inId));
        } catch (PersistenceException pe) {
            log.error("Unable to delete object: ", pe);
            throw pe;
        } catch (Exception e) {
            log.error("Unable to delete object: ", e);
            throw e;
        }

        log.trace("Exiting BaseManager.remove");
    }
}
