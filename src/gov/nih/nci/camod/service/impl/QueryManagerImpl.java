/**
 * @author dgeorge
 * 
 * $Id: QueryManagerImpl.java,v 1.8 2005-10-03 13:51:48 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2005/09/30 18:42:26  guruswas
 * intial implementation of drug screening search and display page
 *
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

import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Log;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.util.DrugScreenResult;
import gov.nih.nci.camod.webapp.form.SearchData;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.common.persistence.exception.PersistenceException;
import gov.nih.nci.common.persistence.hibernate.HQLParameter;
import gov.nih.nci.common.persistence.hibernate.HibernateUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Hibernate;
import org.hibernate.Query;

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
	 * Return the list of environmental factor names
	 * 
	 * @param inType
	 *            the type of environmental factor
	 * 
	 * @return a sorted list of unique environmental factors
	 * @throws PersistenceException
	 */
	public List getQueryOnlyEnvironmentalFactors(String inType) throws PersistenceException {

		log.trace("Entering QueryManagerImpl.getQueryOnlyEnvironmentalFactors");
		ResultSet theResultSet = null;
		List theEnvFactors = new ArrayList();
		try {

			// Format the query
			String theSQLQuery = "SELECT distinct ef.name " + "FROM env_factor ef " + "WHERE ef.type = ? "
					+ "  AND ef.name IS NOT null " + "  AND ef.env_factor_id IN " + "    (SELECT t.env_factor_id "
					+ "     FROM therapy t, animal_model_therapy at " + "     WHERE t.therapeutic_experiment = 0 "
					+ "       AND t.therapy_id = at.therapy_id) " + "ORDER BY ef.name asc ";

			Object[] theParams = new Object[1];
			theParams[0] = inType;
			theResultSet = Search.query(theSQLQuery, theParams);

			while (theResultSet.next()) {
				theEnvFactors.add(theResultSet.getString(1));
			}

			log.trace("Exiting QueryManagerImpl.getQueryOnlyEnvironmentalFactors");
		} catch (Exception e) {
			log.error("Exception in getQueryOnlyEnvironmentalFactors", e);
			throw new PersistenceException("Exception in getQueryOnlyEnvironmentalFactors: " + e);
		} finally {
			if (theResultSet != null) {
				try {
					theResultSet.close();
				} catch (Exception e) {
				}
			}
		}
		return theEnvFactors;
	}

	/**
	 * Return the list of species associated with animal models
	 * 
	 * @return a sorted list of unique species
	 * 
	 * @throws PersistenceException
	 */
	public List getQueryOnlySpecies() throws PersistenceException {

		log.trace("Entering QueryManagerImpl.getQueryOnlyEnvironmentalFactors");

		// Format the query
		String theSQLString = "SELECT distinct scientific_name FROM taxon WHERE scientific_name IS NOT NULL AND taxon_id IN (select distinct taxon_id from abs_cancer_model)";

		ResultSet theResultSet = null;

		List theSpeciesList = new ArrayList();

		try {

			log.info("getModelsIds - SQL: " + theSQLString);

			Object[] params = new Object[0];
			theResultSet = Search.query(theSQLString, params);

			while (theResultSet.next()) {
				theSpeciesList.add(theResultSet.getString(1));
			}

		} catch (Exception e) {
			log.error("Exception in getQueryOnlySpecies", e);
			throw new PersistenceException("Exception in getQueryOnlySpecies: " + e);
		} finally {
			if (theResultSet != null) {
				try {
					theResultSet.close();
				} catch (Exception e) {
				}
			}
		}
		return theSpeciesList;
	}

	/**
	 * Return the list of PI's sorted by last name
	 * 
	 * @return a sorted list of People objects
	 * @throws PersistenceException
	 */
	public List getPrincipalInvestigators() throws PersistenceException {

		log.trace("Entering QueryManagerImpl.getPrincipalInvestigators");

		// Format the query
		HQLParameter[] theParams = new HQLParameter[0];
		String theHQLQuery = "from Person where is_principal_investigator = 1 order by last_name asc";

		List theList = Search.query(theHQLQuery, theParams);

		log.debug("Found matching items: " + theList.size());

		log.trace("Exiting QueryManagerImpl.getPrincipalInvestigators");
		return theList;
	}

	/**
	 * Return the list of species associated with animal models
	 * 
	 * @return a sorted list of unique species
	 * 
	 * @throws PersistenceException
	 */
	public List getQueryOnlyPrincipalInvestigators() throws PersistenceException {

		log.trace("Entering QueryManagerImpl.getQueryOnlyPrincipalInvestigators");

		// Format the query
		String theSQLString = "SELECT last_name, first_name "
				+ "FROM party "
				+ "WHERE is_principal_investigator = 1 "
				+ "  AND first_name IS NOT NULL "
				+ "  AND last_name IS NOT NULL "
				+ "  AND party_id IN (SELECT DISTINCT principal_investigator_id FROM abs_cancer_model WHERE state = 'Edited-approved')"
				+ "ORDER BY last_name ASC";

		ResultSet theResultSet = null;

		List thePIList = new ArrayList();

		try {

			log.info("getQueryOnlyPrincipalInvestigators - SQL: " + theSQLString);

			Object[] params = new Object[0];
			theResultSet = Search.query(theSQLString, params);

			while (theResultSet.next()) {
				String thePIEntry = theResultSet.getString(1) + "," + theResultSet.getString(2);
				thePIList.add(thePIEntry);
			}

		} catch (Exception e) {
			log.error("Exception in getQueryOnlyPrincipalInvestigators", e);
			throw new PersistenceException("Exception in getQueryOnlyPrincipalInvestigators: " + e);
		} finally {
			if (theResultSet != null) {
				try {
					theResultSet.close();
				} catch (Exception e) {
				}
			}
		}
		return thePIList;
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

	/**
	 * Get the model id's for any model that has a histopathology associated
	 * with a specific organ.
	 * 
	 * @param inOrgan
	 *            the organ to search for
	 * 
	 * @return a list of matching model id
	 * 
	 * @throws PersistenceException
	 */
	private String getModelIdsForHistopathologyOrgan(String inOrgan) throws PersistenceException {

		String theSQLString = "SELECT ani_hist.abs_cancer_model_id " + "FROM ani_mod_histopathology ani_hist "
				+ "WHERE ani_hist.histopathology_id IN " + "    (SELECT h.histopathology_id "
				+ "     FROM histopathology h, organ_histopathology oh, organ o "
				+ "     WHERE h.histopathology_id = oh.histopathology_id " + "         AND oh.organ_id = o.organ_id "
				+ "         AND o.name LIKE '%" + inOrgan + "%')";

		return getModelIds(theSQLString);

	}

	/**
	 * Get the model id's for any model that has a cellline w/ a matching name
	 * 
	 * @param inCellLineName
	 *            the text to search for in the cell-line
	 * 
	 * @return a list of matching model id
	 * 
	 * @throws PersistenceException
	 */
	private String getModelIdsForCellLine(String inCellLineName) throws PersistenceException {

		String theSQLString = "SELECT ani_cell.abs_cancer_model_id " + "FROM ani_mod_cell_line ani_cell "
				+ "WHERE ani_cell.cell_line_id IN " + "    (SELECT c.cell_line_id " + "     FROM cell_line c "
				+ "     WHERE c.name LIKE '%" + inCellLineName + "%')";

		return getModelIds(theSQLString);

	}

	/**
	 * Get the model id's for any model that has a histopathology associated
	 * with a specific organ.
	 * 
	 * @param inDisease
	 *            the disease to search for
	 * 
	 * @return a list of matching model id
	 * 
	 * @throws PersistenceException
	 */
	private String getModelIdsForHistopathologyDisease(String inDisease) throws PersistenceException {

		String theSQLString = "SELECT ani_hist.abs_cancer_model_id " + "FROM ani_mod_histopathology ani_hist "
				+ "WHERE ani_hist.histopathology_id IN " + "    (SELECT h.histopathology_id "
				+ "     FROM histopathology h, histopathology_disease hd, disease d "
				+ "     WHERE h.histopathology_id = hd.histopathology_id "
				+ "         AND hd.disease_id = d.disease_id " + "         AND d.name LIKE '%" + inDisease + "%')";

		return getModelIds(theSQLString);

	}

	/**
	 * Get the model id's for any model that has a histopathology associated
	 * with a specific organ.
	 * 
	 * @param inType
	 *            the EF type
	 * @param inName
	 *            the name to look for
	 * 
	 * @return a list of matching model id
	 * 
	 * @throws PersistenceException
	 */
	private String getModelIdsForEnvironmentalFactor(String inType, String inName) throws PersistenceException {

		String theSQLString = "SELECT ani_th.abs_cancer_model_id " + "FROM animal_model_therapy ani_th "
				+ "WHERE ani_th.therapy_id IN " + "    (SELECT t.therapy_id " + "     FROM therapy t, env_factor ef"
				+ "     WHERE t.env_factor_id = ef.env_factor_id AND ef.name = '" + inName + "')";

		return getModelIds(theSQLString);
	}

	public List searchForAnimalModels(SearchData inSearchData) throws Exception {

		List theAnimalModels = null;

		System.out.println("Search data: " + inSearchData.getModelDescriptor());
		System.out.println("Search data: " + inSearchData.getSpecies());
		System.out.println("Search data: " + inSearchData.getPiName());

		String theHQLQuery = "from AnimalModel as am where am.state = 'Edited-approved' ";

		// PI criteria
		if (inSearchData.getPiName() != null && inSearchData.getPiName().length() > 0) {

			StringTokenizer theTokenizer = new StringTokenizer(inSearchData.getPiName());
			String theLastName = theTokenizer.nextToken(",").trim();
			String theFirstName = theTokenizer.nextToken().trim();

			theHQLQuery += " AND am.principalInvestigator IN (from Person as p where p.lastName = '" + theLastName
					+ "' AND p.firstName = '" + theFirstName + "')";
		}

		// Model descriptor criteria
		if (inSearchData.getModelDescriptor() != null && inSearchData.getModelDescriptor().length() > 0) {

			theHQLQuery += " AND am.modelDescriptor like '%" + inSearchData.getModelDescriptor() + "%'";
		}

		// Species criteria
		if (inSearchData.getSpecies() != null && inSearchData.getSpecies().length() > 0) {

			theHQLQuery += "AND am.species IN (from Taxon as t where t.scientificName = '" + inSearchData.getSpecies()
					+ "')";
		}

		// Search for organ
		if (inSearchData.getOrgan() != null && inSearchData.getOrgan().length() > 0) {
			theHQLQuery += " AND abs_cancer_model_id IN (" + getModelIdsForHistopathologyOrgan(inSearchData.getOrgan())
					+ ")";
		}

		// Search for disease
		if (inSearchData.getDisease() != null && inSearchData.getDisease().length() > 0) {
			theHQLQuery += " AND abs_cancer_model_id IN ("
					+ getModelIdsForHistopathologyDisease(inSearchData.getDisease()) + ")";
		}

		// ///////////////////////////////////////
		// Carcinogenic interventions
		// ///////////////////////////////////////

		// Search for chemical/drug
		if (inSearchData.getChemicalDrug() != null && inSearchData.getChemicalDrug().length() > 0) {
			theHQLQuery += " AND abs_cancer_model_id IN ("
					+ getModelIdsForEnvironmentalFactor("Chemical / Drug", inSearchData.getChemicalDrug()) + ")";
		}

		// Search for Surgery
		if (inSearchData.getSurgery() != null && inSearchData.getSurgery().length() > 0) {
			theHQLQuery += " AND abs_cancer_model_id IN ("
					+ getModelIdsForEnvironmentalFactor("Surgery", inSearchData.getSurgery()) + ")";
		}

		// Search for Hormone
		if (inSearchData.getHormone() != null && inSearchData.getHormone().length() > 0) {
			theHQLQuery += " AND abs_cancer_model_id IN ("
					+ getModelIdsForEnvironmentalFactor("Hormone", inSearchData.getHormone()) + ")";
		}

		// Search for Growth Factor
		if (inSearchData.getGrowthFactor() != null && inSearchData.getGrowthFactor().length() > 0) {
			theHQLQuery += " AND abs_cancer_model_id IN ("
					+ getModelIdsForEnvironmentalFactor("Growth Factor", inSearchData.getGrowthFactor()) + ")";
		}

		// Search for Radiation
		if (inSearchData.getRadiation() != null && inSearchData.getRadiation().length() > 0) {
			theHQLQuery += " AND abs_cancer_model_id IN ("
					+ getModelIdsForEnvironmentalFactor("Radiation", inSearchData.getRadiation()) + ")";
		}

		// Search for Viral
		if (inSearchData.getViral() != null && inSearchData.getViral().length() > 0) {
			theHQLQuery += " AND abs_cancer_model_id IN ("
					+ getModelIdsForEnvironmentalFactor("Viral", inSearchData.getViral()) + ")";
		}

		// Search for phenotype
		if (inSearchData.getPhenotype() != null && inSearchData.getPhenotype().length() > 0) {
			theHQLQuery += " AND am.phenotype IN (from Phenotype as p where p.description like '%"
					+ inSearchData.getPhenotype() + "%')";
		}

		// Search for cellline
		if (inSearchData.getCellLine() != null && inSearchData.getCellLine().length() > 0) {
			theHQLQuery += " AND abs_cancer_model_id IN (" + getModelIdsForCellLine(inSearchData.getCellLine()) + ")";
		}

		// Add order by clause
		theHQLQuery += " ORDER BY am.modelDescriptor asc";

		try {
			log.info("HQL Query: " + theHQLQuery);
			// HQLParameter[] theParameters = new HQLParameter[0];
			// animalModels = Search.query(theHQLQuery, theParameters);
			Query theQuery = HibernateUtil.getSession().createQuery(theHQLQuery);
			theAnimalModels = theQuery.list();
		} catch (Exception e) {
			log.error("Exception occurred searching for models", e);
			throw e;
		}

		return theAnimalModels;
	}

	/**
	 * Get the model id's for any model that has a histopathology associated
	 * with a specific organ.
	 * 
	 * @param inType
	 *            the EF type
	 * @param inName
	 *            the name to look for
	 * 
	 * @return a list of matching model id
	 * 
	 * @throws PersistenceException
	 */
	private String getModelIds(String inSQLString) throws PersistenceException {

		String theModelIds = "";

		ResultSet theResultSet = null;
		try {

			log.info("getModelsIds - SQL: " + inSQLString);

			Object[] params = new Object[0];
			theResultSet = Search.query(inSQLString, params);

			if (theResultSet.next()) {
				theModelIds += theResultSet.getString(1);
			}

			while (theResultSet.next()) {
				theModelIds += "," + theResultSet.getString(1);
			}

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

		if (theModelIds.equals("")) {
			theModelIds = "-1";
		}
		return theModelIds;
	}

	public static void main(String[] inArgs) {

		// Format the query
		// HQLParameter[] theParams = new HQLParameter[0];
		// String theHQLQuery = "select distinct tr.administrativeRoute from
		// Treatment as tr where tr.administrativeRoute is not null order by
		// tr.administrativeRoute asc ";
		//        
		//
		// try {
		// List theList = Search.query(theHQLQuery, theParams);
		// System.out.println("Num returned: " + theList.size());
		// System.out.println("Num 1: " + theList.get(0));
		// System.out.println("Num 2: " + theList.get(1));
		// } catch (Exception e) {
		// System.out.println("Exception: " + e);
		// e.printStackTrace();
		// }

		try {
			System.out.println("Model ids: "
					+ QueryManagerSingleton.instance().getModelIdsForHistopathologyOrgan("Skin"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
/*
 * $Log: not supported by cvs2svn $ Revision 1.6 2005/09/27 16:46:59 georgeda
 * Added environmental factor dropdown query Revision 1.5 2005/09/26 14:04:14
 * georgeda Use HQL instead of SQL Revision 1.4 2005/09/16 19:30:04 guruswas
 * Display invivo data (from DTP) in the therapuetic approaches page
 * 
 * Revision 1.3 2005/09/16 15:52:57 georgeda Changes due to manager re-write
 * 
 * Revision 1.2 2005/09/13 20:44:55 georgeda More changes Revision 1.1
 * 2005/09/12 18:22:08 georgeda Curation changes and addition of e-mail
 * 
 */