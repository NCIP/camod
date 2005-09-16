/**
 * @author dgeorge
 * 
 * $Id: QueryManagerImpl.java,v 1.4 2005-09-16 19:30:04 guruswas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/09/16 15:52:57  georgeda
 * Changes due to manager re-write
 *
 * 
 */
package gov.nih.nci.camod.service.impl;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.util.DrugScreenResult;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
    /**
     * Get yeast screen result data (ave inibition etc.) for a given Agent (drug)
     * 
     * @param agent refers to the compound that was used in the yeast experiment
     * @param stage is the stage of the experiment (0, 1, or 2) 
     * 
     * @return the results
     * @throws PersistenceException
     */
    public DrugScreenResult getYeastScreenResults(Agent agent, String stage) throws PersistenceException {
		DrugScreenResult dsr = new DrugScreenResult();
		
        ResultSet theResultSet = null;
        try {

            String theSQLString = 
			"select tx.ethnicity_strain," + "\n" +
			"       t.dosage," + "\n" +
			"       sr.aveinh aveinh," + "\n" +
			"       sr.diffinh diffinh" + "\n" +
			"  from screening_Result sr," + "\n" +
			"       env_factor a," + "\n" +
			"       YST_MDL_SCRNING_RESULT ymsr," + "\n" +
			"       abs_cancer_model acm," + "\n" +
			"       treatment t," + "\n" +
			"       taxon tx" + "\n" +
			" where sr.agent_id = a.env_factor_id" + "\n" +
			"   and sr.screening_result_id = ymsr.screening_result_id" + "\n" +
			"   and sr.treatment_id = t.treatment_id" + "\n" +
			"   and ymsr.abs_cancer_model_id = acm.abs_cancer_model_id" + "\n" +
			"   and acm.taxon_id = tx.taxon_id" + "\n" +
			"   and a.nsc_number = ?" + "\n" +
			"   and sr.stage = ?" + "\n" +
			" order by 1, 2";
            log.info("getYeastScreenResults - SQL: " + theSQLString);

			Object[] params = new Object[2];
			params[0] = agent.getNscNumber();
			params[1] = stage;
            theResultSet = Search.query(theSQLString, params);
			while(theResultSet.next()) {
				final String strain = theResultSet.getString(1);  
				final String dosage = theResultSet.getString(2);
				final float aveinh = theResultSet.getFloat(3);
				final float diffinh = theResultSet.getFloat(4);
				dsr.addEntry(strain, dosage, aveinh, diffinh);
			}
			log.info("Got " + dsr.strainCount + " strains");
        } catch (Exception e) {
            log.error("Exception in getYeastScreenResults", e);
            throw new PersistenceException("Exception in getYeastScreenResults: " + e);
        } finally {
            if (theResultSet != null) {
                try {
                    theResultSet.close();
                } catch (Exception e) {
                }
            }
        }
		return dsr;
    }	

    /**
     * Get the invivo (Xenograft) data (from DTP) for a given Agent.nscNumber (drug)
     * 
     * @param agent refers to the compound that was used in the xenograft experiment
     * 
     * @return the results (list of abs_cancer_model_id, model_descriptor, and # of records
     * @throws PersistenceException
     */
    public List getInvivoResults(Agent agent) throws PersistenceException {
		List results = new ArrayList();
		int cc = 0;
        ResultSet theResultSet = null;
        try {

            String theSQLString = 
			"select acm.abs_cancer_model_id," + "\n" +
			"       acm.model_descriptor," + "\n" +
			"       tx.ethnicity_strain," + "\n" +
			"       count(*)" + "\n" +
			"  from invivo_Result sr," + "\n" +
			"       env_factor a," + "\n" +
			"       XENOGRAFT_INVIVO_RESULT ymsr," + "\n" +
			"       abs_cancer_model acm," + "\n" +
			"       treatment t," + "\n" +
			"       taxon tx" + "\n" +
			" where sr.agent_id = a.env_factor_id" + "\n" +
			"   and sr.invivo_result_id = ymsr.invivo_result_id" + "\n" +
			"   and sr.treatment_id = t.treatment_id" + "\n" +
			"   and ymsr.abs_cancer_model_id = acm.abs_cancer_model_id" + "\n" +
			"   and acm.taxon_id = tx.taxon_id" + "\n" +
			"   and a.nsc_number = ?" + "\n" +
			" group by acm.abs_cancer_model_id, acm.model_descriptor, tx.ethnicity_strain" + "\n" +
			" order by 3, 2";
            log.info("getInvivoResults - SQL: " + theSQLString);

			Object[] params = new Object[1];
			params[0] = agent.getNscNumber();
            theResultSet = Search.query(theSQLString, params);
			while(theResultSet.next()) {
				String[] item = new String[4];
				item[0] = theResultSet.getString(1); // the id
				item[1] = theResultSet.getString(2); // model descriptor
				item[2] = theResultSet.getString(3); // strain
				item[3] = theResultSet.getString(4); // record count
				results.add(item);
				cc++;
			}
			log.info("Got " + cc + " xenograft models");
        } catch (Exception e) {
            log.error("Exception in getYeastScreenResults", e);
            throw new PersistenceException("Exception in getYeastScreenResults: " + e);
        } finally {
            if (theResultSet != null) {
                try {
                    theResultSet.close();
                } catch (Exception e) {
                }
            }
        }
		return results;
    }
}
/*
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/09/16 15:52:57  georgeda
 * Changes due to manager re-write
 *
 * Revision 1.2  2005/09/13 20:44:55  georgeda
 * More changes
 * Revision 1.1 2005/09/12 18:22:08 georgeda
 * Curation changes and addition of e-mail
 * 
 */