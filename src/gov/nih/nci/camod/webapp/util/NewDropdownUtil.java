package gov.nih.nci.camod.webapp.util;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.GeneDelivery;
import gov.nih.nci.camod.domain.InducedMutation;
import gov.nih.nci.camod.domain.Person;
import gov.nih.nci.camod.domain.Role;
import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.domain.Strain;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Xenograft;
import gov.nih.nci.camod.service.CurationManager;
import gov.nih.nci.camod.service.GeneDeliveryManager;
import gov.nih.nci.camod.service.InducedMutationManager;
import gov.nih.nci.camod.service.TaxonManager;
import gov.nih.nci.camod.service.XenograftManager;
import gov.nih.nci.camod.service.impl.CurationManagerImpl;
import gov.nih.nci.camod.service.impl.QueryManagerSingleton;
import gov.nih.nci.common.persistence.Search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class NewDropdownUtil {

	private static final Log log = LogFactory.getLog(NewDropdownUtil.class);

	private static Map ourFileBasedLists = new HashMap();

	public static void populateDropdown(HttpServletRequest inRequest, String inDropdownKey, String inFilter)
			throws Exception {

		log.trace("Entering NewDropdownUtil.populateDropdown");

		log.debug("Generating a dropdown for the following key: " + inDropdownKey);

		List theList = null;
		if (inDropdownKey.indexOf(".txt") != -1) {
			theList = getTextFileDropdown(inRequest, inDropdownKey);
		} else if (inDropdownKey.indexOf(".db") != -1) {
			theList = getDatabaseDropdown(inRequest, inDropdownKey, inFilter);
		}

		// Add a blank as the first line
		if (Constants.Dropdowns.ADD_BLANK_OPTION.equals(inFilter)) {
			theList.add(0, "");
		}

		// Add a blank as the first line
		if (Constants.Dropdowns.ADD_BLANK_DROPDOWN_OPTION.equals(inFilter)) {
			theList.add(0, new DropdownOption("", ""));
		}

		if (theList == null) {
			throw new IllegalArgumentException("Unknown dropdown list key: " + inDropdownKey);
		}

		System.out.println("<NewDropdownUtil populateDropdown> inDropdownKey: " + inDropdownKey);
		System.out.println("<NewDropdownUtil populateDropdown> inFilter: " + inFilter);
		System.out.println("<NewDropdownUtil populateDropdown> List: " + theList);

		inRequest.getSession().setAttribute(inDropdownKey, theList);

		log.trace("Exiting NewDropdownUtil.populateDropdown");
	}

	private static List getDatabaseDropdown(HttpServletRequest inRequest, String inDropdownKey, String inFilter)
			throws Exception {

		log.trace("Entering NewDropdownUtil.getDatabaseDropdown");

		List theReturnList = null;

		// Grab them for the first time
		if (inDropdownKey.equals(Constants.Dropdowns.SPECIESDROP)) {
			theReturnList = getSpeciesList(inRequest, inFilter);
		} 
        
        else if (inDropdownKey.equals(Constants.Dropdowns.NEWSPECIESDROP)) {
			theReturnList = getNewSpeciesList(inRequest, inFilter);
		} 
        
        else if (inDropdownKey.equals(Constants.Dropdowns.SPECIESQUERYDROP)) {
			theReturnList = getQueryOnlySpeciesList(inRequest, inFilter);
		} 
        
        else if (inDropdownKey.equals(Constants.Dropdowns.SPECIESQUERYDROP)) {
			theReturnList = getQueryOnlySpeciesList(inRequest, inFilter);
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.STRAINDROP)) {
			theReturnList = getStrainsList(inRequest, inFilter);
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.GRAFTTYPEDROP)) {
			theReturnList = getGrafttypeList(inRequest);
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.VIRALVECTORDROP)) {
			theReturnList = getViralVectorList(inRequest);
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.INDUCEDMUTATIONDROP)) {
			theReturnList = getInducedMutationList(inRequest);
		}

		// Environmental Factors - Carciogenic Interventions
		else if (inDropdownKey.equals(Constants.Dropdowns.SURGERYDROP)) {
			theReturnList = getEnvironmentalFactorList("Other");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.SURGERYQUERYDROP)) {
			theReturnList = getQueryOnlyEnvironmentalFactorList("Other");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.HORMONEDROP)) {
			theReturnList = getEnvironmentalFactorList("Hormone");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.HORMONEQUERYDROP)) {
			theReturnList = getQueryOnlyEnvironmentalFactorList("Hormone");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.GROWTHFACTORDROP)) {
			theReturnList = getEnvironmentalFactorList("Growth Factor");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.GROWTHFACTORQUERYDROP)) {
			theReturnList = getQueryOnlyEnvironmentalFactorList("Growth Factor");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.CHEMICALDRUGDROP)) {
			theReturnList = getEnvironmentalFactorList("Chemical / Drug");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.CHEMICALDRUGQUERYDROP)) {
			theReturnList = getQueryOnlyEnvironmentalFactorList("Chemical / Drug");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.VIRUSDROP)) {
			theReturnList = getEnvironmentalFactorList("Viral");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.VIRUSQUERYDROP)) {
			theReturnList = getQueryOnlyEnvironmentalFactorList("Viral");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.RADIATIONDROP)) {
			theReturnList = getEnvironmentalFactorList("Radiation");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.RADIATIONQUERYDROP)) {
			theReturnList = getQueryOnlyEnvironmentalFactorList("Radiation");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.NUTRITIONFACTORDROP)) {
			theReturnList = getEnvironmentalFactorList("Nutrition");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.ENVIRONFACTORDROP)) {
			theReturnList = getEnvironmentalFactorList("Environment");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.ENVIRONFACTORDROP)) {
			theReturnList = getEnvironmentalFactorList("Environment");
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.PRINCIPALINVESTIGATORDROP)) {
			theReturnList = getPrincipalInvestigatorList(inRequest, inFilter);
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.PRINCIPALINVESTIGATORQUERYDROP)) {
			theReturnList = getQueryOnlyPrincipalInvestigatorList(inRequest, inFilter);
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.INDUCEDMUTATIONAGENTQUERYDROP)) {
			theReturnList = getQueryOnlyInducedMutationAgentList(inRequest, inFilter);
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.USERSDROP)) {
			theReturnList = getUsersList(inRequest, inFilter);
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.CURATIONSTATESDROP)) {
			theReturnList = getCurationStatesList(inRequest, inFilter);
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.USERSFORROLEDROP)) {
			theReturnList = getUsersForRoleList(inRequest, inFilter);
		}

		else if (inDropdownKey.equals(Constants.Dropdowns.ROLESDROP)) {
			theReturnList = getRolesList(inRequest);
		}

		else {
			log.error("No matching dropdown for key: " + inDropdownKey);
			theReturnList = new ArrayList();
		}

		log.trace("Exiting NewDropdownUtil.getDatabaseDropdown");
		return theReturnList;
	}

	// Get the context so we can get to our managers
	private static WebApplicationContext getContext(HttpServletRequest inRequest) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(inRequest.getSession().getServletContext());
	}

	// Get a text file dropdown
	private static synchronized List getTextFileDropdown(HttpServletRequest inRequest, String inDropdownKey)
			throws Exception {

		log.trace("Entering NewDropdownUtil.getTextFileDropdown");

		List theReturnList = null;

		if (ourFileBasedLists.containsKey(inDropdownKey)) {
			log.debug("Dropdown already cached");
			theReturnList = (List) ourFileBasedLists.get(inDropdownKey);
		} else {

			String theFilename = inRequest.getSession().getServletContext().getRealPath("/config/dropdowns") + "/"
					+ inDropdownKey;

			List theList = readListFromFile(theFilename);

			// Built a list. Add to static hash
			if (theList.size() != 0) {
				log.debug("Caching new dropdown: " + theList);
				ourFileBasedLists.put(inDropdownKey, theList);
				theReturnList = theList;
			}
		}

		log.trace("Exiting NewDropdownUtil.getTextFileDropdown");
		return theReturnList;
	}

	// Read from a file
	static private List readListFromFile(String inFilename) throws Exception {
		List theReturnList = new ArrayList();

		log.debug("Filename to read dropdown from: " + inFilename);

		BufferedReader in = new BufferedReader(new FileReader(inFilename));

		String str;
		while ((str = in.readLine()) != null) {
			log.info("Reading value from file: " + str);
			theReturnList.add(str);
		}
		in.close();

		return theReturnList;
	}

	/**
	 * Returns a list of all Species and Strains
	 * 
	 * @return speciesNames
	 * @throws Exception
	 */
	private static List getSpeciesList(HttpServletRequest inRequest, String inAddBlank) throws Exception {

		// Get values for dropdown lists for Species, Strains
		// First get a list of all taxons
		// for each taxon, get it's scientificName (it's species name)
		// for each unique species name retrieve all (if any) strain names
		TaxonManager taxonManager = (TaxonManager) getContext(inRequest).getBean("taxonManager");

		List taxonList = taxonManager.getAll();
		List speciesNames = new ArrayList();
		Taxon tmp;

		// TODO: Fix once we know what we're doing w/ this
		speciesNames.add("Other");

		if (taxonList != null) {
			for (int i = 0; i < taxonList.size(); i++) {
				tmp = (Taxon) taxonList.get(i);

				if (tmp.getScientificName() != null) {
					// if the speciesName is not already in the List, add it
					// (only get unique names)
					if (!speciesNames.contains(tmp.getScientificName()))
						speciesNames.add(tmp.getScientificName());
				}
			}
		}
		Collections.sort(speciesNames);

		return speciesNames;
	}

	/**
	 * Returns a list of all Species and Strains
	 * 
	 * @return speciesNames
	 * @throws Exception
	 */
	private static List getNewSpeciesList(HttpServletRequest inRequest, String inAddBlank) throws Exception {

		// Get values for dropdown lists for Species, Strains
		// First get a list of all taxons
		// for each taxon, get it's scientificName (it's species name)
		// for each unique species name retrieve all (if any) strain names
		TaxonManager taxonManager = (TaxonManager) getContext(inRequest).getBean("taxonManager");

		List taxonList = taxonManager.getAll();
		List speciesNames = new ArrayList();
		Taxon tmp;

		if (taxonList != null) {
			for (int i = 0; i < taxonList.size(); i++) {
				tmp = (Taxon) taxonList.get(i);

				if (tmp.getScientificName() != null) {

					String theDisplayName = tmp.displayName();
					if (theDisplayName.length() > 0) {
						DropdownOption theOption = new DropdownOption(theDisplayName, tmp.getScientificName());
						if (!speciesNames.contains(theOption)) {
							speciesNames.add(theOption);
						}
					}
				}
			}
		}
		Collections.sort(speciesNames);

		return speciesNames;
	}

	/**
	 * Returns a list of all Species and Strains
	 * 
	 * @return speciesNames
	 * @throws Exception
	 */
	private static List getQueryOnlySpeciesList(HttpServletRequest inRequest, String inAddBlank) throws Exception {
		return QueryManagerSingleton.instance().getQueryOnlySpecies();
	}

	/**
	 * Based on a species name retrieve a list of all Strains
	 * 
	 * @param speciesName
	 * @return strainNames
	 * @throws Exception
	 */
	private static List getStrainsList(HttpServletRequest inRequest, String speciesName) throws Exception {

		TaxonManager taxonManager = (TaxonManager) getContext(inRequest).getBean("taxonManager");

		Species species = new Species();
		species.setName(speciesName);

		List strainList = new ArrayList();
		List strainNames = new ArrayList();

		strainList = taxonManager.getStrains(species);

		if (strainList != null) {
			// print out strainNames
			for (int j = 0; j < strainList.size(); j++) {
				Strain strain = (Strain) strainList.get(j);

				// strainNames.add(strain);

				if (strain.getName() != null) {

					if (!strainNames.contains(strain.getName())) {
						strainNames.add(strain.getName());
						// System.out.println("Strain Name>>" + j + ": " +
						// strain.getName());
					}
				}
			}
		}

		// Sort the list in 'abc' order
		if (strainNames.size() > 0)
			Collections.sort(strainNames);

		if (!strainNames.contains(Constants.Dropdowns.OTHER_OPTION)) {
			strainNames.add(Constants.Dropdowns.OTHER_OPTION);
		}

		return strainNames;
	}

	/**
	 * Returns a list of all Graft Types
	 * 
	 * @return graftList
	 * @throws Exception
	 */
	private static List getGrafttypeList(HttpServletRequest inRequest) throws Exception {

		// Get values for dropdown lists for Species, Strains
		XenograftManager xenograftManager = (XenograftManager) getContext(inRequest).getBean("xenograftManager");

		List xenograftList = null;

		xenograftList = xenograftManager.getAll();

		List graftList = new ArrayList();
		Xenograft tmp;

		graftList.add("Other");

		if (xenograftList != null) {
			for (int i = 0; i < xenograftList.size(); i++) {
				tmp = (Xenograft) xenograftList.get(i);

				if (tmp.getName() != null) {
					// if the speciesName is not already in the List, add it
					// (only get unique names)
					if (!graftList.contains(tmp.getName()))
						graftList.add(tmp.getName());
				}
			}
		}

		Collections.sort(graftList);
		return graftList;
	}

	/**
	 * Returns a list of all Administrative Routes
	 * 
	 * @return adminList
	 * @throws Exception
	 */
	private static List getViralVectorList(HttpServletRequest inRequest) throws Exception {

		// Get values for dropdown lists for Species, Strains
		GeneDeliveryManager geneDeliveryManager = (GeneDeliveryManager) getContext(inRequest).getBean(
				"geneDeliveryManager");

		List geneDeliveryList = null;

		geneDeliveryList = geneDeliveryManager.getAll();

		List viralVectorList = new ArrayList();
		GeneDelivery tmp;

		// TODO: Fix once we know what we're doing w/ this
		viralVectorList.add(Constants.Dropdowns.OTHER_OPTION);
		// TODO: Remive this before production - used to test other field toggle
		viralVectorList.add("Dummy Viral Vector");

		if (geneDeliveryList != null) {
			for (int i = 0; i < geneDeliveryList.size(); i++) {
				tmp = (GeneDelivery) geneDeliveryList.get(i);

				if (tmp.getViralVector() != null) {
					// if the speciesName is not already in the List, add it
					// (only get unique names)
					if (!viralVectorList.contains(tmp.getViralVector()))
						viralVectorList.add(tmp.getViralVector());
				}
			}
		}
		Collections.sort(viralVectorList);
		return viralVectorList;
	}

	/**
	 * Returns a list for a type of environmental Factore
	 * 
	 * @return envList
	 */
	private static List getEnvironmentalFactorList(String type) throws Exception {
		List theList = QueryManagerSingleton.instance().getEnvironmentalFactors(type);

		theList.add(Constants.Dropdowns.OTHER_OPTION);
		return theList;
	}

	/**
	 * Returns a list for a type of environmental Factore
	 * 
	 * @return envList
	 */
	private static List getQueryOnlyEnvironmentalFactorList(String type) throws Exception {
		List theList = QueryManagerSingleton.instance().getQueryOnlyEnvironmentalFactors(type);

		theList.add(Constants.Dropdowns.OTHER_OPTION);
		return theList;
	}

	/**
	 * Returns a list of all Principal Investigators
	 * 
	 * @return list of PI's
	 * @throws Exception
	 */
	private static List getPrincipalInvestigatorList(HttpServletRequest inRequest, String inAddBlank) throws Exception {

		log.trace("Entering NewDropdownUtil.getPrincipalInvestigatorList");

		List thePIList = QueryManagerSingleton.instance().getPrincipalInvestigators();

		List theReturnList = new ArrayList();

		if (thePIList != null) {
			for (int i = 0; i < thePIList.size(); i++) {
				Person thePerson = (Person) thePIList.get(i);
				if (thePerson.getIsPrincipalInvestigator() != null) {

					String theDisplayName = thePerson.displayName();
					if (theDisplayName.length() > 0) {
						DropdownOption theOption = new DropdownOption(theDisplayName, thePerson.getUsername());
						theReturnList.add(theOption);
					}
				}
			}
		}

		log.trace("Exiting NewDropdownUtil.getPrincipalInvestigatorList");

		return theReturnList;
	}

	/**
	 * Returns a list of all Principal Investigators
	 * 
	 * @return list of PI's
	 * @throws Exception
	 */
	private static List getQueryOnlyPrincipalInvestigatorList(HttpServletRequest inRequest, String inAddBlank)
			throws Exception {

		log.trace("Entering NewDropdownUtil.getQueryOnlyPrincipalInvestigatorList");

		return QueryManagerSingleton.instance().getQueryOnlyPrincipalInvestigators();
	}

	/**
	 * Returns a list of all Agents that were used to induce a mutation
	 * 
	 * @return list of agent names
	 * @throws Exception
	 */
	private static List getQueryOnlyInducedMutationAgentList(HttpServletRequest inRequest, String inAddBlank)
			throws Exception {

		log.trace("Entering NewDropdownUtil.getQueryOnlyInducedMutationAgentList");

		List inducedMutationList = QueryManagerSingleton.instance().getQueryOnlyInducedMutationAgents();
		inducedMutationList.add("Other");
		return inducedMutationList;
	}

	/**
	 * Returns a list of all induced mutations
	 * 
	 * @return graftList
	 */
	private static List getInducedMutationList(HttpServletRequest inRequest) throws Exception {

		// Get values for dropdown lists for Species, Strains
		InducedMutationManager inducedMutationManager = (InducedMutationManager) getContext(inRequest).getBean(
				"inducedMutationManager");

		List inducedMutationList = inducedMutationManager.getAll();

		List mutationList = new ArrayList();
		InducedMutation tmp;

		mutationList.add("Other");

		if (inducedMutationList != null) {
			for (int i = 0; i < inducedMutationList.size(); i++) {
				tmp = (InducedMutation) inducedMutationList.get(i);

				if (tmp.getName() != null) {
					// if the speciesName is not already in the List, add it
					// (only get unique names)
					if (!mutationList.contains(tmp.getName()))
						mutationList.add(tmp.getName());
				}
			}
		}

		Collections.sort(mutationList);
		return mutationList;
	}

	/**
	 * Returns a list of all users
	 * 
	 * @return list of users
	 * @throws Exception
	 */
	private static List getUsersList(HttpServletRequest inRequest, String inAddBlank) throws Exception {

		log.trace("Entering NewDropdownUtil.getUsersList");

		List thePersonList = Search.query(Person.class);

		List theReturnList = new ArrayList();

		// Add all of the display names
		if (thePersonList != null) {
			for (int i = 0; i < thePersonList.size(); i++) {
				Person thePerson = (Person) thePersonList.get(i);

				DropdownOption theOption = new DropdownOption(thePerson.displayName(), thePerson.getId().toString());
				theReturnList.add(theOption);
			}
		}

		log.trace("Exiting NewDropdownUtil.getUsersList");

		Collections.sort(theReturnList);
		return theReturnList;
	}

	/**
	 * Returns a list of all states for a curation flow
	 * 
	 * @return list of curation states
	 * 
	 * @throws Exception
	 */
	private static List getCurationStatesList(HttpServletRequest inRequest, String inWorkflow) throws Exception {

		log.trace("Entering NewDropdownUtil.getCurationStatesList");

		// Get the curation manager workflow XML
		CurationManager theCurationManager = new CurationManagerImpl(inRequest.getSession().getServletContext()
				.getRealPath("/")
				+ inWorkflow);

		return theCurationManager.getAllStateNames();
	}

	/**
	 * Returns a list of all states for a curation flow
	 * 
	 * @return list of curation states
	 * 
	 * @throws Exception
	 */
	private static List getUsersForRoleList(HttpServletRequest inRequest, String inRoleName) throws Exception {

		log.trace("Entering NewDropdownUtil.getUsersForRoleList");

		List theUserList = new ArrayList();

		Role theRole = new Role();
		theRole.setName(inRoleName);

		try {

			List theRoles = Search.query(theRole);

			if (theRoles.size() > 0) {
				theRole = (Role) theRoles.get(0);

				// Get the users for the role
				List theUsers = theRole.getPartyCollection();
				Iterator theIterator = theUsers.iterator();

				// Go through the list of returned Party objects
				while (theIterator.hasNext()) {
					Object theObject = theIterator.next();

					// Only add when it's actually a person
					if (theObject instanceof Person) {
						Person thePerson = (Person) theObject;
						theUserList.add(new DropdownOption(thePerson.displayName(), thePerson.getUsername()));
					}
				}

			} else {
				log.warn("Role not found in database: " + inRoleName);
			}
		} catch (Exception e) {
			log.error("Unable to get roles for user: ", e);
			throw e;
		}

		return theUserList;
	}

	/**
	 * Returns a list of all the known roles
	 * 
	 * @return list of roles
	 */
	private static List getRolesList(HttpServletRequest inRequest) {

		// Generate the roles list
		List theRoles = new ArrayList();
		theRoles.add(Constants.Admin.Roles.ALL);
		theRoles.add(Constants.Admin.Roles.COORDINATOR);
		theRoles.add(Constants.Admin.Roles.EDITOR);
		theRoles.add(Constants.Admin.Roles.SCREENER);

		return theRoles;
	}
}
