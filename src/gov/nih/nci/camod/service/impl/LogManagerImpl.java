/**
 *  @author dgeorge
 *  
 *  $Id: LogManagerImpl.java,v 1.8 2005-10-10 14:09:00 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.7  2005/09/26 14:09:36  georgeda
 *  Cleanup for common manager code
 *
 *  Revision 1.6  2005/09/16 15:52:57  georgeda
 *  Changes due to manager re-write
 * 
 *  Revision 1.5 2005/09/13 20:45:01 georgeda 
 *  More changes 
 *  Revision 1.4 2005/09/12 18:22:14 georgeda 
 *  Curation changes and addition of e-mail
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.LogManager;
import gov.nih.nci.common.persistence.exception.PersistenceException;

import java.util.Date;
import java.util.List;

/**
 * Implementation of the manager which stores/retrieves Log objects
 */
public class LogManagerImpl extends BaseManager implements LogManager {

	/**
	 * Get the latest Log object that matches the state/user and model
	 * 
	 * @parameter inModel is the animal model the Log is associated with
	 * @parameter inUser is the user that the model is assigned to
	 * 
	 * @return the latest matching Log object. null if not found
	 * @throws Exception
	 */
	public Log getCurrentByModelAndAssigned(AnimalModel inModel, Person inUser) throws Exception {

		log.trace("Entering LogManagerImpl.getCurrentByModelAndAssigned");

		Log theLog = null;

		try {

			theLog = QueryManagerSingleton.instance().getCurrentLogForUser(inModel, inUser);

		} catch (PersistenceException e) {
			log.error("Caught a PersistentException: ", e);
			throw e;
		} catch (Exception e) {
			log.error("Caught an Exception: ", e);
			throw e;
		}
		log.trace("Exiting LogManagerImpl.getCurrentByModelAndAssigned");

		return theLog;
	}

	/**
	 * Get the latest Log object that matches the state/user and comments
	 * 
	 * @parameter inComments is the comments the Log is associated with
	 * @parameter inUser is the user that the model is assigned to
	 * 
	 * @return the latest matching Log object. null if not found
	 * @throws Exception
	 */
	public Log getCurrentByCommentsAndAssigned(Comments inComments, Person inUser) throws Exception {

		log.trace("Entering LogManagerImpl.getCurrentByCommentsAndAssigned");

		Log theLog = null;

		try {
			theLog = QueryManagerSingleton.instance().getCurrentLogForUser(inComments, inUser);
		} catch (PersistenceException e) {
			log.error("Caught a PersistentException: ", e);
			throw e;
		} catch (Exception e) {
			log.error("Caught an Exception: ", e);
			throw e;
		}
		log.trace("Exiting LogManagerImpl.getCurrentByModelAndAssigned");

		return theLog;
	}

	/**
	 * Get the latest Log object that matches the state/user and model
	 * 
	 * @parameter inModelId is the animal model the Log is associated with
	 * 
	 * @return the latest matching Log object. null if not found
	 * @throws Exception
	 */
	public Log getCurrentByModel(AnimalModel inModel) throws Exception {

		log.trace("Entering LogManagerImpl.getCurrentByModel");
		Log theLog = null;

		try {
			theLog = QueryManagerSingleton.instance().getCurrentLog(inModel);
		} catch (PersistenceException e) {
			log.error("Caught a PersistentException: ", e);
			throw e;
		} catch (Exception e) {
			log.error("Caught an Exception: ", e);
			throw e;
		}

		log.trace("Exiting LogManagerImpl.getCurrentByModel");
		return theLog;
	}

	/**
	 * Get all the Log objects in the system
	 * 
	 * @return all the Log objects in the system
	 * @throws Exception
	 */
	public List getAll() throws Exception {
		log.trace("In  LogManagerImpl.getAll");
		return super.getAll(Log.class);
	}

	/**
	 * Get a log object by unique ID
	 * 
	 * @parameter inId the unique ID for the log object to fetch
	 * 
	 * @return the specific log object
	 * @throws Exception
	 */
	public Log get(String inId) throws Exception {

		log.trace("In LogManagerImpl.get");
		return (Log) super.get(inId, Log.class);
	}

	/**
	 * Save the log object
	 * 
	 * @throws Exception
	 * 
	 * @parameter inLog is the log object to save
	 */
	public void save(Log inLog) throws Exception {
		log.trace("In LogManagerImpl.save");
		super.save(inLog);
	}

	/**
	 * Save the log object
	 * 
	 * @throws Exception
	 * 
	 * @parameter inAssignedPerson is the person who the model is assigned to
	 * @parameter inModelId is the model assigned to the person
	 * @parameter inState is the current state of the object
	 * @parameter inNotes is any note(s) associated w/ the state transition
	 */
	public Log create(String inAssignedPerson, String inModelId, String inState, String inNotes) throws Exception {

		log.trace("Entering LogManagerImpl.create");

		Log theLog = new Log();

		try {

			log.debug("Person: " + inAssignedPerson);
			log.debug("Model: " + inModelId);
			log.debug("State: " + inState);
			log.debug("Notes: " + inNotes);

			Person theAssignedPerson = PersonManagerSingleton.instance().getByUsername(inAssignedPerson);
			AnimalModel theAnimalModel = AnimalModelManagerSingleton.instance().get(inModelId);

			theLog.setCancerModel(theAnimalModel);
			theLog.setSubmitter(theAssignedPerson);
			theLog.setTimestamp((new Date()).toString());
			theLog.setType(inState);
			theLog.setNotes(inNotes);

		} catch (Exception e) {
			log.error("Exception in save", e);
			throw e;
		}

		log.trace("Exiting LogManagerImpl.create");

		return theLog;
	}

	/**
	 * Remove the log object
	 * 
	 * @throws Exception
	 * 
	 * @parameter inId the unique ID for the log object to remove
	 */
	public void remove(String inId) throws Exception {
		log.trace("In LogManagerImpl.remove");
		super.remove(inId, Log.class);
	}
}