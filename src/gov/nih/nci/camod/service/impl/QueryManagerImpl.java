package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;

import java.sql.ResultSet;

/**
 * Implementation of a wrapper around the JDBC interaface. Used until we get the
 * QBE working.
 */
public class QueryManagerImpl extends BaseManager {

    /**
     * Get the latest Log object that matches the state/user and model
     * 
     * @parameter inModel is the animal model the Log is associated with
     * @parameter inUser is the user that the model is assigned to
     * 
     * @return the latest matching Log object. null if not found
     * @throws PersistenceException
     */
    public Log getCurrentByModelAndAssigned(AnimalModel inModel, Person inUser) throws PersistenceException {

        log.trace("Entering getCurrentByModelAndAssigned");

        Log theLog = null;

        ResultSet theResultSet = null;
        try {

            String theSQLString = "select log_id from log where abs_cancer_model_id = " + inModel.getId().toString()
                    + " and type = '" + inModel.getState() + "' and party_id = " + inUser.getId()
                    + " order by timestamp desc";

            log.debug("getCurrentByModelAndAssigned - SQL: " + theSQLString);

            theResultSet = Search.query(theSQLString, new Object[0]);

            if (theResultSet.next()) {
                Long theLogId = new Long(theResultSet.getLong("log_id"));
                theLog = (Log) Search.queryById(Log.class, theLogId);
            }

        } catch (Exception e) {

            log.error("Exception in getCurrentByModelAndAssigned", e);
            throw new PersistenceException("Exception in getCurrentByModelAndAssigned: " + e);
        } finally {
            if (theResultSet != null) {
                try {
                    theResultSet.close();
                } catch (Exception e) {
                }
            }
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
     * @throws PersistenceException
     */
    public Log getCurrentByModel(AnimalModel inModel) throws PersistenceException {

        log.trace("Entering getCurrentByModel");

        Log theLog = null;
        ResultSet theResultSet = null;
        try {

            String theSQLString = "select log_id from log where abs_cancer_model_id = " + inModel.getId().toString()
                    + " and type = '" + inModel.getState() + "' order by timestamp desc";

            log.debug("getCurrentByModel - SQL: " + theSQLString);

            theResultSet = Search.query(theSQLString, new Object[0]);

            if (theResultSet.next()) {
                Long theLogId = new Long(theResultSet.getLong("log_id"));
                theLog = (Log) Search.queryById(Log.class, theLogId);
            }

        } catch (Exception e) {
            log.error("Exception in getCurrentByModel", e);
            throw new PersistenceException("Exception in getCurrentByModel: " + e);
        } finally {
            if (theResultSet != null) {
                try {
                    theResultSet.close();
                } catch (Exception e) {
                }
            }
        }

        log.trace("Exiting getCurrentByModelAndAssigned");

        return theLog;
    }
}
/*
 * $Log: not supported by cvs2svn $ Revision 1.1 2005/09/12 18:22:08 georgeda
 * Curation changes and addition of e-mail
 * 
 */