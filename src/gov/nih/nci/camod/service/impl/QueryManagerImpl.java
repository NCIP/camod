/**
 * @author dgeorge
 * 
 * $Id: QueryManagerImpl.java,v 1.7 2005-09-30 18:42:26 guruswas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/09/27 16:46:59  georgeda
 * Added environmental factor dropdown query
 *
 * Revision 1.5  2005/09/26 14:04:14  georgeda
 * Use HQL instead of SQL
 *
 * Revision 1.4  2005/09/16 19:30:04  guruswas
 * Display invivo data (from DTP) in the therapuetic approaches page
 *
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
import gov.nih.nci.common.persistence.hibernate.HQLParameter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

/**
 * Implementation of a wrapper around the HQL/JDBC interface. Used for more
 * complex instances where QBE does not have sufficient power.
 */
public class QueryManagerImpl extends BaseManager {

    /**
     * Return the list of environmental factor names
     * 
     * @param inType
     *            the type of environmental factor
     * 
     * @return a sorted list of unique environmental factors
     * @throws PersistenceException
     */
    public List getEnvironmentalFactors(String inType) throws PersistenceException {

        log.trace("Entering QueryManagerImpl.getAdministrativeRoutes");

        // Format the query
        HQLParameter[] theParams = new HQLParameter[1];
        theParams[0] = new HQLParameter();
        theParams[0].setName("type");
        theParams[0].setValue(inType);
        theParams[0].setType(Hibernate.STRING);

        String theHQLQuery = "select distinct ef.name from EnvironmentalFactor as ef where ef.type = :type and ef.name is not null order by ef.name asc ";

        List theList = Search.query(theHQLQuery, theParams);

        log.debug("Found matching items: " + theList.size());

        log.trace("Exiting QueryManagerImpl.getAdministrativeRoutes");
        return theList;
    }
    
    /**
     * Return the latest log for an animal model
     * 
     * @param inModel
     *            the animal model to get the latest log for
     * 
     * @return the current log for an animal model
     * @throws PersistenceException
     */
    public Log getCurrentLog(AnimalModel inModel) throws PersistenceException {

        log.trace("Entering QueryManagerImpl.getCurrentLog");

        HQLParameter[] theParams = new HQLParameter[1];
        theParams[0] = new HQLParameter();
        theParams[0].setName("abs_cancer_model_id");
        theParams[0].setValue(inModel.getId());
        theParams[0].setType(Hibernate.LONG);

        String theHQLQuery = "from Log where abs_cancer_model_id = :abs_cancer_model_id order by timestamp desc";
        log.debug("The HQL query: " + theHQLQuery);
        List theLogs = Search.query(theHQLQuery, theParams);

        Log theLog = null;
        if (theLogs != null && theLogs.size() > 0) {
            theLog = (Log) theLogs.get(0);
            log.debug("Found a matching object: " + theLog.getId());
        } else {
            log.debug("No object found.");
        }

        log.trace("Exiting QueryManagerImpl.getCurrentLog");

        return theLog;
    }

    /**
     * Return the latest log for an animal model/user combo
     * 
     * @param inModel
     *            the animal model to get the latest log for
     * @param inUser
     *            the user to get the latest log for
     * 
     * @return the current log for an animal model/user combination
     * @throws PersistenceException
     */
    public Log getCurrentLogForUser(AnimalModel inModel, Person inUser) throws PersistenceException {

        log.trace("Entering QueryManagerImpl.getCurrentLogForUser");

        // Format the query
        HQLParameter[] theParams = new HQLParameter[2];
        theParams[0] = new HQLParameter();
        theParams[0].setName("abs_cancer_model_id");
        theParams[0].setValue(inModel.getId());
        theParams[0].setType(Hibernate.LONG);
        theParams[1] = new HQLParameter();
        theParams[1].setName("party_id");
        theParams[1].setValue(inUser.getId());
        theParams[1].setType(Hibernate.LONG);

        String theHQLQuery = "from Log where abs_cancer_model_id = :abs_cancer_model_id and party_id = :party_id order by timestamp desc";
        log.debug("the HQL Query: " + theHQLQuery);
        List theLogs = Search.query(theHQLQuery, theParams);

        Log theLog = null;
        if (theLogs != null && theLogs.size() > 0) {
            theLog = (Log) theLogs.get(0);
            log.debug("Found a matching object: " + theLog.getId());
        } else {
            log.debug("No object found.");
        }

        log.trace("Exiting QueryManagerImpl.getCurrentLogForUser");
        return theLog;
    }

    /**
     * Get yeast screen result data (ave inibition etc.) for a given Agent
     * (drug)
     * 
     * @param agent
     *            refers to the compound that was used in the yeast experiment
     * @param stage
     *            is the stage of the experiment (0, 1, or 2)
     * 
     * @return the results
     * @throws PersistenceException
     */
    public DrugScreenResult getYeastScreenResults(Agent agent, String stage) throws PersistenceException {
        DrugScreenResult dsr = new DrugScreenResult();

        ResultSet theResultSet = null;
        try {

            String theSQLString = "select tx.ethnicity_strain," + "\n" + "       t.dosage," + "\n"
                    + "       sr.aveinh aveinh," + "\n" + "       sr.diffinh diffinh" + "\n"
                    + "  from screening_Result sr," + "\n" + "       env_factor a," + "\n"
                    + "       YST_MDL_SCRNING_RESULT ymsr," + "\n" + "       abs_cancer_model acm," + "\n"
                    + "       treatment t," + "\n" + "       taxon tx" + "\n" + " where sr.agent_id = a.env_factor_id"
                    + "\n" + "   and sr.screening_result_id = ymsr.screening_result_id" + "\n"
                    + "   and sr.treatment_id = t.treatment_id" + "\n"
                    + "   and ymsr.abs_cancer_model_id = acm.abs_cancer_model_id" + "\n"
                    + "   and acm.taxon_id = tx.taxon_id" + "\n" + "   and a.nsc_number = ?" + "\n"
                    + "   and sr.stage = ?" + "\n" + " order by 1, 2";
            log.info("getYeastScreenResults - SQL: " + theSQLString);

            Object[] params = new Object[2];
            params[0] = agent.getNscNumber();
            params[1] = stage;
            theResultSet = Search.query(theSQLString, params);
            while (theResultSet.next()) {
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
     * Get the invivo (Xenograft) data (from DTP) for a given Agent.nscNumber
     * (drug)
     * 
     * @param agent
     *            refers to the compound that was used in the xenograft
     *            experiment
     * 
     * @return the results (list of abs_cancer_model_id, model_descriptor, and #
     *         of records
     * @throws PersistenceException
     */
    public List getInvivoResults(Agent agent) throws PersistenceException {
        List results = new ArrayList();
        int cc = 0;
        ResultSet theResultSet = null;
        try {

            String theSQLString = "select acm.abs_cancer_model_id," + "\n" + "       acm.model_descriptor || ' ' || acm.ADMINISTRATIVE_SITE || ' '," + "\n"
                    + "       tx.ethnicity_strain," + "\n" + "       count(*)" + "\n" + "  from invivo_Result sr,"
                    + "\n" + "       env_factor a," + "\n" + "       XENOGRAFT_INVIVO_RESULT ymsr," + "\n"
                    + "       abs_cancer_model acm," + "\n" + "       treatment t," + "\n" + "       taxon tx" + "\n"
                    + " where sr.agent_id = a.env_factor_id" + "\n"
                    + "   and sr.invivo_result_id = ymsr.invivo_result_id" + "\n"
                    + "   and sr.treatment_id = t.treatment_id" + "\n"
                    + "   and ymsr.abs_cancer_model_id = acm.abs_cancer_model_id" + "\n"
                    + "   and acm.taxon_id = tx.taxon_id" + "\n" + "   and a.nsc_number = ?" + "\n"
                    + " group by acm.abs_cancer_model_id, acm.model_descriptor || ' ' || acm.ADMINISTRATIVE_SITE || ' ', tx.ethnicity_strain" + "\n"
                    + " order by 2, 3";
            log.info("getInvivoResults - SQL: " + theSQLString);

            Object[] params = new Object[1];
            params[0] = agent.getNscNumber();
            theResultSet = Search.query(theSQLString, params);
            while (theResultSet.next()) {
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

    public static void main(String[] inArgs) {

        // Format the query
        HQLParameter[] theParams = new HQLParameter[0];
        String theHQLQuery = "select distinct tr.administrativeRoute from Treatment as tr where tr.administrativeRoute is not null order by tr.administrativeRoute asc ";
        

        try {
            List theList = Search.query(theHQLQuery, theParams);
            System.out.println("Num returned: " + theList.size());
            System.out.println("Num 1: " + theList.get(0));
            System.out.println("Num 2: " + theList.get(1));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }
}
/*
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/09/27 16:46:59  georgeda
 * Added environmental factor dropdown query
 * Revision 1.5 2005/09/26 14:04:14 georgeda Use
 * HQL instead of SQL Revision 1.4 2005/09/16 19:30:04 guruswas Display invivo
 * data (from DTP) in the therapuetic approaches page
 * 
 * Revision 1.3 2005/09/16 15:52:57 georgeda Changes due to manager re-write
 * 
 * Revision 1.2 2005/09/13 20:44:55 georgeda More changes Revision 1.1
 * 2005/09/12 18:22:08 georgeda Curation changes and addition of e-mail
 * 
 */