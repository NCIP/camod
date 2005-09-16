/**
 *  @author dgeorge
 *  
 *  $Id: LogManagerImpl.java,v 1.6 2005-09-16 15:52:57 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $ 
 *  Revision 1.5 2005/09/13 20:45:01 georgeda 
 *  More changes 
 *  Revision 1.4 2005/09/12 18:22:14 georgeda 
 *  Curation changes and addition of e-mail
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.LogManager;
import gov.nih.nci.common.persistence.Persist;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.HibernateUtil;

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

        log.trace("Entering getCurrentByModelAndAssigned");

        Log theLog = null;

        try {
            theLog = QueryManagerSingleton.instance().getCurrentByModelAndAssigned(inModel, inUser);

            /*
             * // The following objects are needed for eQBE. Log theQBELog = new
             * Log(); // Build the log object theQBELog.setCancerModel(inModel);
             * theQBELog.setType(inModel.getState()); // TODO: should work? //
             * theQBELog.setSubmitter(theAssignedPerson); // Apply evaluators to
             * object properties // Evaluation theEvaluation = new Evaluation(); //
             * theEvaluation.addEvaluator("log.type", Evaluator.EQUAL); //
             * theEvaluation.addEvaluator("log.cancerModel", Evaluator.EQUAL); //
             * TODO: Should work //
             * theEvaluation.addEvaluator("log.submitter.id", Evaluator.EQUAL); //
             * List theLogs = Search.query(theLog, theEvaluation);
             * System.out.println("Before query in
             * getCurrentByModelAndAssigned"); List theLogs =
             * Search.query(theQBELog);
             * 
             * System.out.println("Number found in query: " + theLogs.size());
             * Date theNewestDate = null; // Grab the latest record if the user
             * matches for (int i = 0; i < theLogs.size(); i++) {
             * 
             * if (theNewestDate == null) { Log theCurrentLog = (Log)
             * theLogs.get(i);
             * 
             * if (theCurrentLog.getSubmitter().getId().equals(inUser.getId())) {
             * theLog = theCurrentLog; theNewestDate = new
             * Date(theLog.getTimestamp()); } } else { Log theCurrentLog = (Log)
             * theLogs.get(i); if
             * (theCurrentLog.getSubmitter().getId().equals(inUser.getId())) {
             * 
             * Date theDate = new Date(theLog.getTimestamp());
             * 
             * if (theDate.after(theNewestDate)) { theNewestDate = theDate;
             * theLog = theCurrentLog; } } } }
             * 
             */

        } catch (PersistenceException e) {
            log.error("Caught a PersistentException: " + e);
            throw e;
        } catch (Exception e) {
            log.error("Caught an Exception: " + e);
            throw e;
        }
        log.trace("Exiting getCurrentByModelAndAssigned");

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

        log.trace("Entering getCurrentByModel");
        Log theLog = null;

        try {
            theLog = QueryManagerSingleton.instance().getCurrentByModel(inModel);

        } catch (PersistenceException e) {
            log.error("Caught a PersistentException: " + e);
            throw e;
        } catch (Exception e) {
            log.error("Caught an Exception: " + e);
            throw e;
        }

        log.trace("Exiting getCurrentByModel");
        return theLog;
    }

    /**
     * Get all the Log objects in the system
     * 
     * @return all the Log objects in the system
     * @throws Exception
     */
    public List getAll() throws Exception {

        log.trace("Entering getAll");

        List theLogs = null;

        try {
            theLogs = Search.query(Log.class);
        } catch (PersistenceException pe) {
            log.error("Exception in getAll", pe);
            throw pe;
        } catch (Exception e) {
            log.error("Exception in getAll", e);
            throw e;
        }

        log.trace("Exiting getAll");

        return theLogs;
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

        log.trace("Entering get");

        Log theLog = null;

        try {
            theLog = (Log) Search.queryById(Log.class, new Long(inId));
        } catch (PersistenceException pe) {
            log.error("Exception in LogManagerImpl.getAll", pe);
            throw pe;
        } catch (Exception e) {
            log.error("Exception in LogManagerImpl.getAll", e);
            throw e;
        }

        log.trace("Entering get");

        return theLog;
    }

    /**
     * Save the log object
     * 
     * @throws Exception
     * 
     * @parameter inLog is the log object to save
     */
    public void save(Log inLog) throws Exception {

        log.trace("Entering LogManagerImpl.save");

        try {
            // TODO: Commit for now until whole transaction mess it dealt with
            HibernateUtil.beginTransaction();
            Persist.save(inLog);
            HibernateUtil.commitTransaction();

        } catch (PersistenceException pe) {
            log.error("Exception in save", pe);
            throw pe;
        } catch (Exception e) {
            log.error("Exception in save", e);
            throw e;
        }

        log.trace("Exiting LogManagerImpl.save");
    }

    /**
     * Save the log object
     * @throws Exception 
     * 
     * @parameter inAssignedPerson is the person who the model is assigned to
     * @parameter inModelId is the model assigned to the person
     * @parameter inState is the current state of the object
     * @parameter inNotes is any note(s) associated w/ the state transition
     */
    public Log create(String inAssignedPerson, String inModelId, String inState, String inNotes) throws Exception {

        log.trace("Entering create");

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

        log.trace("Exiting create");

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

        log.trace("Entering remove");

        try {
            log.debug("Removing id: " + inId);
            Persist.deleteById(Log.class, new Long(inId));
        } catch (PersistenceException pe) {
            log.error("Exception removing a log object: ", pe);
            throw pe;
        } catch (Exception e) {
            log.error("Exception removing a log object: ", e);
            throw e;
        }

        log.trace("Exiting remove");
    }
}