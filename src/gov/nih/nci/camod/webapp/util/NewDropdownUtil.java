/**
 * 
 * $Id: NewDropdownUtil.java,v 1.42 2006-05-15 15:45:40 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.41  2006/05/10 14:16:14  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.40  2006/04/17 19:08:38  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.39  2005/11/29 20:47:21  georgeda
 * Removed system.out
 *
 * Revision 1.38  2005/11/16 21:36:40  georgeda
 * Defect #47, Clean up EF querying
 *
 * Revision 1.37  2005/11/16 19:26:30  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.util;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.*;
import gov.nih.nci.camod.service.impl.CurationManagerImpl;
import gov.nih.nci.camod.service.impl.QueryManagerSingleton;
import gov.nih.nci.common.persistence.Search;
import gov.nih.nci.camod.service.SpeciesManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class NewDropdownUtil
{

    private static final Log log = LogFactory.getLog(NewDropdownUtil.class);

    private static Map ourFileBasedLists = new HashMap();

    public static void populateDropdown(HttpServletRequest inRequest,
                                        String inDropdownKey,
                                        String inFilter) throws Exception
    {

        log.trace("Entering NewDropdownUtil.populateDropdown");

        log.debug("Generating a dropdown for the following key: " + inDropdownKey);

        List theList = null;
        if (inDropdownKey.indexOf(".txt") != -1)
        {
            theList = getTextFileDropdown(inRequest, inDropdownKey);
        }
        else if (inDropdownKey.indexOf(".db") != -1)
        {
            theList = getDatabaseDropdown(inRequest, inDropdownKey, inFilter);
        }

        // Add a blank as the first line
        if (Constants.Dropdowns.ADD_BLANK.equals(inFilter))
        {
            addBlank(theList);
        }

        // Add a blank as the first line
        else if (Constants.Dropdowns.ADD_BLANK_OPTION.equals(inFilter))
        {
            addBlankOption(theList);
        }

        // Add a blank as the first line
        else if (Constants.Dropdowns.ADD_OTHER.equals(inFilter))
        {
            addOther(theList);
        }

        // Add a blank as the first line
        else if (Constants.Dropdowns.ADD_OTHER_OPTION.equals(inFilter))
        {
            addOtherOption(theList);
        }

        else if (Constants.Dropdowns.ADD_BLANK_AND_OTHER.equals(inFilter))
        {
            addOther(theList);
            addBlank(theList);
        }

        else if (Constants.Dropdowns.ADD_BLANK_AND_OTHER_OPTION.equals(inFilter))
        {
            addOtherOption(theList);
            addBlankOption(theList);
        }

        if (theList == null)
        {
            throw new IllegalArgumentException("Unknown dropdown list key: " + inDropdownKey);
        }

        inRequest.getSession().setAttribute(inDropdownKey, theList);

        log.trace("Exiting NewDropdownUtil.populateDropdown");
    }

    private static List getDatabaseDropdown(HttpServletRequest inRequest,
                                            String inDropdownKey,
                                            String inFilter) throws Exception
    {

        log.info("Entering NewDropdownUtil.getDatabaseDropdown");

        List theReturnList = null;

        // Grab them for the first time
        if (inDropdownKey.equals(Constants.Dropdowns.SPECIESDROP))
        {
            theReturnList = getSpeciesList(inRequest, inFilter);
        }
        //modified for species from DB
        else if (inDropdownKey.equals(Constants.Dropdowns.SPECIESQUERYDROP))
        {
            theReturnList = getQueryOnlySpeciesList(inRequest, inFilter);
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.STRAINDROP))
        {
            theReturnList = getStrainsList(inRequest, inFilter);
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.VIRALVECTORDROP))
        {
            theReturnList = getViralVectorList(inRequest);
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.EXPRESSIONLEVELDROP))
        {
            theReturnList = getExpressionLevelList(inRequest);
        }

        // Environmental Factors - Carciogenic Interventions
        else if (inDropdownKey.equals(Constants.Dropdowns.SURGERYDROP))
        {
            theReturnList = getEnvironmentalFactorList("Other");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.SURGERYQUERYDROP))
        {
            theReturnList = getQueryOnlyEnvironmentalFactorList("Other");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.HORMONEDROP))
        {
            theReturnList = getEnvironmentalFactorList("Hormone");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.HORMONEQUERYDROP))
        {
            theReturnList = getQueryOnlyEnvironmentalFactorList("Hormone");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.GROWTHFACTORDROP))
        {
            theReturnList = getEnvironmentalFactorList("Growth Factor");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.GROWTHFACTORQUERYDROP))
        {
            theReturnList = getQueryOnlyEnvironmentalFactorList("Growth Factor");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.CHEMICALDRUGDROP))
        {
            theReturnList = getEnvironmentalFactorList("Chemical / Drug");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.CHEMICALDRUGQUERYDROP))
        {
            theReturnList = getQueryOnlyEnvironmentalFactorList("Chemical / Drug");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.VIRUSDROP))
        {
            theReturnList = getEnvironmentalFactorList("Viral");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.VIRUSQUERYDROP))
        {
            theReturnList = getQueryOnlyEnvironmentalFactorList("Viral");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.RADIATIONDROP))
        {
            theReturnList = getEnvironmentalFactorList("Radiation");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.RADIATIONQUERYDROP))
        {
            theReturnList = getQueryOnlyEnvironmentalFactorList("Radiation");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.NUTRITIONFACTORDROP))
        {
            theReturnList = getEnvironmentalFactorList("Nutrition");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.ENVIRONFACTORDROP))
        {
            theReturnList = getEnvironmentalFactorList("Environment");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.ENVIRONFACTORDROP))
        {
            theReturnList = getEnvironmentalFactorList("Environment");
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.PRINCIPALINVESTIGATORDROP))
        {
            theReturnList = getPrincipalInvestigatorList(inRequest, inFilter);
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.PRINCIPALINVESTIGATORQUERYDROP))
        {
            theReturnList = getQueryOnlyPrincipalInvestigatorList(inRequest, inFilter);
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.INDUCEDMUTATIONAGENTQUERYDROP))
        {
            theReturnList = getQueryOnlyInducedMutationAgentList(inRequest, inFilter);
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.USERSDROP))
        {
            theReturnList = getUsersList(inRequest, inFilter);
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.CURATIONSTATESDROP))
        {
            theReturnList = getCurationStatesList(inRequest, inFilter);
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.USERSFORROLEDROP))
        {
            theReturnList = getUsersForRoleList(inRequest, inFilter);
        }

        else if (inDropdownKey.equals(Constants.Dropdowns.ROLESDROP))
        {
            theReturnList = getRolesList(inRequest);
        }

        else
        {
            log.error("No matching dropdown for key: " + inDropdownKey);
            theReturnList = new ArrayList();
        }

        log.info("Exiting NewDropdownUtil.getDatabaseDropdown");
        return theReturnList;
    }

    // Get the context so we can get to our managers
    private static WebApplicationContext getContext(HttpServletRequest inRequest)
    {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(inRequest.getSession().getServletContext());
    }

    // Get a text file dropdown
    private static synchronized List getTextFileDropdown(HttpServletRequest inRequest,
                                                         String inDropdownKey) throws Exception
    {
        log.trace("Entering NewDropdownUtil.getTextFileDropdown");

        List theReturnList = new ArrayList();

        if (ourFileBasedLists.containsKey(inDropdownKey))
        {
            log.debug("Dropdown already cached");
            List theCachedList = (List) ourFileBasedLists.get(inDropdownKey);
            theReturnList.addAll(theCachedList);
        }
        else
        {
            String theFilename = inRequest.getSession().getServletContext().getRealPath("/config/dropdowns") + "/" + inDropdownKey;

            List theList = readListFromFile(theFilename);

            // Built a list. Add to static hash
            if (theList.size() != 0)
            {
                log.debug("Caching new dropdown: " + theList);
                ourFileBasedLists.put(inDropdownKey, theList);
                theReturnList.addAll(theList);
            }
        }

        log.trace("Exiting NewDropdownUtil.getTextFileDropdown");
        return theReturnList;
    }

    // Read from a file
    static private List readListFromFile(String inFilename) throws Exception
    {
        List theReturnList = new ArrayList();

        log.debug("Filename to read dropdown from: " + inFilename);

        BufferedReader in = new BufferedReader(new FileReader(inFilename));

        boolean isDropdownOption = false;

        String str;
        while ((str = in.readLine()) != null)
        {
            log.info("readListFromFile method: Reading value from file: " + str);

            // It's a DropdownOption file
            if (str.indexOf("DROPDOWN_OPTION") > 0)
            {
                isDropdownOption = true;
            }
            else if (isDropdownOption == true)
            {
                StringTokenizer theTokenizer = new StringTokenizer(str);
                String theLabel = theTokenizer.nextToken(",");
                String theValue = theTokenizer.nextToken(",");

                DropdownOption theDropdownOption = new DropdownOption(theLabel, theValue);
                theReturnList.add(theDropdownOption);
            }
            else
            {
                theReturnList.add(str);
            }
        }
        in.close();

        return theReturnList;
    }

    /**
     * Returns a list of Species and Strains that are edited-approved (3 currently)
     * Used for search screens
     * 
     * @return speciesNames
     * @throws Exception
     */
    protected static List getSpeciesList(HttpServletRequest inRequest,
                                         String inAddBlank) throws Exception
    { 
        log.trace("Entering NewDropdownUtil.getSpeciesList");
        
        // Get values for dropdown lists for Species
        // for each Species, get it's commonName (scientificName)
        List theSpeciesList = QueryManagerSingleton.instance().getApprovedSpecies(inRequest);
        List<DropdownOption> theReturnList = new ArrayList<DropdownOption>();         
        
        if (theSpeciesList != null)
        {
            for (int i = 0; i < theSpeciesList.size(); i++)
            {
                Species theSpecies = (Species) theSpeciesList.get(i);
                if (theSpecies.getScientificName() != null)
                {
                    String theDisplayName = theSpecies.getDisplayName();
                    if (theDisplayName.length() > 0)
                    {
                        DropdownOption theOption = new DropdownOption(theDisplayName, theSpecies.getScientificName());
                        theReturnList.add(theOption);
                    }
                }
            } 
        } 
        log.trace("Exiting NewDropdownUtil.getQueryOnlySpeciesList");
        return theReturnList; 
    }

    /**
     * Returns a list of all Species and Strains 
     * Used for search screens
     * 
     * @return speciesNames
     * @throws Exception
     */
    private static List getQueryOnlySpeciesList(HttpServletRequest inRequest,
                                                String inAddBlank) throws Exception
    {
        log.trace("Entering NewDropdownUtil.getQueryOnlySpeciesList");
      
        // Get values for dropdown lists for Species
        // for each Species, get it's commonName (scientificName)
        List theSpeciesList = QueryManagerSingleton.instance().getQueryOnlySpecies(inRequest);
        List<DropdownOption> theReturnList = new ArrayList<DropdownOption>();         
        
        if (theSpeciesList != null)
        {
            for (int i = 0; i < theSpeciesList.size(); i++)
            {
                Species theSpecies = (Species) theSpeciesList.get(i);
                if (theSpecies.getScientificName() != null)
                {
                    String theDisplayName = theSpecies.getDisplayName();
                    if (theDisplayName.length() > 0)
                    {
                        DropdownOption theOption = new DropdownOption(theDisplayName, theSpecies.getScientificName());
                        theReturnList.add(theOption);
                    }
                }
            } 
        } 
        log.trace("Exiting NewDropdownUtil.getQueryOnlySpeciesList");
        return theReturnList; 
    }
    

    /**
     * Based on a species name retrieve a list of all Strains
     * 
     * @param speciesName
     * @return strainNames
     * @throws Exception
     */
    private static List getStrainsList(HttpServletRequest inRequest,
                                       String speciesName) throws Exception
    {
        log.info("Entering NewDropdownUtil.getStrainsList");

        //Set constant for the Animal Model species here 
        inRequest.getSession().setAttribute(Constants.AMMODELSPECIES, speciesName);

        SpeciesManager speciesManager = (SpeciesManager) getContext(inRequest).getBean("speciesManager");
        Species species = null;
        
        List<Strain> strainList = new ArrayList<Strain>();
        List<String> strainNames = new ArrayList<String>();        

      if (speciesName != null && speciesName.length() > 0) {
            species =  speciesManager.getByName(speciesName);
            strainList = new ArrayList<Strain>(species.getStrainCollection());
            
         if (strainList.size() > 0)
         {
                //print out strain names                  
                for (int i = 0; i < strainList.size(); i++)
                {
                    Strain strain = (Strain) strainList.get(i);

                    if (strain.getName() != null && !strainNames.contains(strain.getName()))
                    {
                            System.out.println("strain.getName(): " + strain.getName());                            
                            strainNames.add(strain.getName());
                    }
                }
            // Sort the list in 'abc' order
            Collections.sort(strainNames);
            addOther(strainNames);
            addBlank(strainNames);

        }  else
            {
                addBlank(strainNames);
                addOther(strainNames);                
            }
        }        
        return strainNames;
    }


    /**
     * Returns a list of all Administrative Routes
     * 
     * @return adminList
     * @throws Exception
     */
    private static List getViralVectorList(HttpServletRequest inRequest) throws Exception
    {

        // Get values for dropdown lists for Species, Strains
        GeneDeliveryManager geneDeliveryManager = (GeneDeliveryManager) getContext(inRequest).getBean("geneDeliveryManager");

        List<GeneDelivery> geneDeliveryList = null;

        geneDeliveryList = geneDeliveryManager.getAll();

        List<String> viralVectorList = new ArrayList<String>();
        GeneDelivery tmp;

        if (geneDeliveryList != null)
        {
            for (int i = 0; i < geneDeliveryList.size(); i++)
            {
                tmp = (GeneDelivery) geneDeliveryList.get(i);

                if (tmp.getViralVector() != null)
                {
                    // if the name is not already in the List, add it
                    // (only get unique names)
                    if (!viralVectorList.contains(tmp.getViralVector()))
                        viralVectorList.add(tmp.getViralVector());
                }
            }
        }
        Collections.sort(viralVectorList);

        addOther(viralVectorList);

        return viralVectorList;
    }

    /**
     * Returns a list for a type of environmental Factors
     * 
     * @return envList
     */
    private static List getEnvironmentalFactorList(String type) throws Exception
    {
        List theEnvFactorList = QueryManagerSingleton.instance().getEnvironmentalFactors(type);

        addOther(theEnvFactorList);
        return theEnvFactorList;
    }

    /**
     * Returns a list for a type of environmental Factore
     * 
     * @return envList
     */
    private static List getQueryOnlyEnvironmentalFactorList(String type) throws Exception
    {
        List theEnvFactorList = QueryManagerSingleton.instance().getQueryOnlyEnvironmentalFactors(type);
        return theEnvFactorList;
    }

    /**
     * Returns a list of all Principal Investigators
     * 
     * @return list of PI's
     * @throws Exception
     */
    private static List getPrincipalInvestigatorList(HttpServletRequest inRequest,
                                                     String inAddBlank) throws Exception
    {
        log.trace("Entering NewDropdownUtil.getPrincipalInvestigatorList");

        List thePIList = QueryManagerSingleton.instance().getPrincipalInvestigators();

        List<DropdownOption> theReturnList = new ArrayList<DropdownOption>();

        if (thePIList != null)
        {
            for (int i = 0; i < thePIList.size(); i++)
            {
                Person thePerson = (Person) thePIList.get(i);
                if (thePerson.getIsPrincipalInvestigator() != null)
                {
                    String theDisplayName = thePerson.getDisplayName();
                    if (theDisplayName.length() > 0)
                    {
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
    private static List getQueryOnlyPrincipalInvestigatorList(HttpServletRequest inRequest,
                                                              String inAddBlank) throws Exception
    {

        log.info("Entering NewDropdownUtil.getQueryOnlyPrincipalInvestigatorList");

        return QueryManagerSingleton.instance().getQueryOnlyPrincipalInvestigators();
    }

    /**
     * Returns a list of all Agents that were used to induce a mutation
     * 
     * @return list of agent names
     * @throws Exception
     */
    private static List getQueryOnlyInducedMutationAgentList(HttpServletRequest inRequest,
                                                             String inAddBlank) throws Exception
    {

        log.trace("Entering NewDropdownUtil.getQueryOnlyInducedMutationAgentList");

        List inducedMutationList = QueryManagerSingleton.instance().getQueryOnlyInducedMutationAgents();
        addOther(inducedMutationList);
        return inducedMutationList;
    }

    /**
     * Returns a list of all users
     * 
     * @return list of users
     * @throws Exception
     */
    private static List getUsersList(HttpServletRequest inRequest,
                                     String inAddBlank) throws Exception
    {
        log.trace("Entering NewDropdownUtil.getUsersList");

        List thePersonList = Search.query(Person.class);

        List<DropdownOption> theReturnList = new ArrayList<DropdownOption>();

        // Add all of the display names
        if (thePersonList != null)
        {
            for (int i = 0; i < thePersonList.size(); i++)
            {
                Person thePerson = (Person) thePersonList.get(i);

                DropdownOption theOption = new DropdownOption(thePerson.getDisplayNameWithOrg(), thePerson.getId().toString());
                theReturnList.add(theOption);
            }
        }

        log.trace("Exiting NewDropdownUtil.getUsersList");

        Collections.sort(theReturnList);
        return theReturnList;
    }

    /**
     * Returns a list of all Expression Level Descriptions
     * 
     * @return list of users
     * @throws Exception
     */
    private static List getExpressionLevelList(HttpServletRequest inRequest) throws Exception
    {
        // Get values for dropdown lists for Expression Level
        ExpressionLevelDescManager expressionLevelDescManager = (ExpressionLevelDescManager) getContext(inRequest).getBean(
                                                                                                                           "expressionLevelDescManager");

        List expList = null;

        expList = expressionLevelDescManager.getAll();

        List<String> expressionLevelList = new ArrayList<String>();
        ExpressionLevelDesc tmp;

        if (expList != null)
        {
            for (int i = 0; i < expList.size(); i++)
            {
                tmp = (ExpressionLevelDesc) expList.get(i);

                if (tmp.getExpressionLevel() != null)
                {
                    // if the speciesName is not already in the List, add it
                    // (only get unique names)
                    if (!expressionLevelList.contains(tmp.getExpressionLevel()))
                        expressionLevelList.add(tmp.getExpressionLevel());
                }
            }
        }
        Collections.sort(expressionLevelList);
        return expressionLevelList;

    }

    /**
     * Returns a list of all states for a curation flow
     * 
     * @return list of curation states
     * 
     * @throws Exception
     */
    private static List getCurationStatesList(HttpServletRequest inRequest,
                                              String inWorkflow) throws Exception
    {

        log.trace("Entering NewDropdownUtil.getCurationStatesList");

        // Get the curation manager workflow XML
        CurationManager theCurationManager = new CurationManagerImpl(inRequest.getSession().getServletContext().getRealPath("/") + inWorkflow);

        return theCurationManager.getAllStateNames();
    }

    /**
     * Returns a list of all states for a curation flow
     * 
     * @return list of curation states
     * 
     * @throws Exception
     */
    private static List getUsersForRoleList(HttpServletRequest inRequest,
                                            String inRoleName) throws Exception
    {

        log.trace("Entering NewDropdownUtil.getUsersForRoleList");

        List theUserList = new ArrayList();

        Role theRole = new Role();
        theRole.setName(inRoleName);

        try
        {

            List theRoles = Search.query(theRole);

            if (theRoles.size() > 0)
            {
                theRole = (Role) theRoles.get(0);

                // Get the users for the role
                Set<Party> theUsers = theRole.getPartyCollection();
                Iterator theIterator = theUsers.iterator();

                // Go through the list of returned Party objects
                while (theIterator.hasNext())
                {
                    Object theObject = theIterator.next();

                    // Only add when it's actually a person
                    if (theObject instanceof Person)
                    {
                        Person thePerson = (Person) theObject;
                        theUserList.add(new DropdownOption(thePerson.getDisplayName(), thePerson.getUsername()));
                    }
                }

            }
            else
            {
                log.warn("Role not found in database: " + inRoleName);
            }
        }
        catch (Exception e)
        {
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
    private static List getRolesList(HttpServletRequest inRequest)
    {

        // Generate the roles list
        List<String> theRoles = new ArrayList<String>();
        theRoles.add(Constants.Admin.Roles.ALL);
        theRoles.add(Constants.Admin.Roles.COORDINATOR);
        theRoles.add(Constants.Admin.Roles.EDITOR);
        theRoles.add(Constants.Admin.Roles.SCREENER);

        return theRoles;
    }

    /**
     * Add Other to the list in the first spot if it's not already there.
     * Removes it and put's it in the first spot if it is.
     */
    private static void addOther(List inList)
    {

        if (!inList.contains(Constants.Dropdowns.OTHER_OPTION))
        {
            inList.add(0, Constants.Dropdowns.OTHER_OPTION);
        }
        else
        {
            inList.remove(Constants.Dropdowns.OTHER_OPTION);
            inList.add(0, Constants.Dropdowns.OTHER_OPTION);
        }
    }

    /**
     * Add Other to the list in the first spot if it's not already there.
     * Removes it and put's it in the first spot if it is.
     */
    private static void addOtherOption(List inList)
    {

        DropdownOption theDropdownOption = new DropdownOption(Constants.Dropdowns.OTHER_OPTION, Constants.Dropdowns.OTHER_OPTION);

        if (!inList.contains(theDropdownOption))
        {
            inList.add(0, theDropdownOption);
        }
        else
        {
            inList.remove(theDropdownOption);
            inList.add(0, theDropdownOption);
        }
    }

    /**
     * Add "" to the list in the first spot if it's not already there. Removes
     * it and put's it in the first spot if it is.
     */
    private static void addBlank(List inList)
    {

        if (!inList.contains(""))
        {
            inList.add(0, "");
        }
        else
        {
            inList.remove("");
            inList.add(0, "");
        }
    }

    /**
     * Add "" to the list in the first spot if it's not already there. Removes
     * it and put's it in the first spot if it is.
     */
    private static void addBlankOption(List inList)
    {

        DropdownOption theDropdownOption = new DropdownOption("", "");

        if (!inList.contains(theDropdownOption))
        {
            inList.add(0, theDropdownOption);
        }
        else
        {
            inList.remove(theDropdownOption);
            inList.add(0, theDropdownOption);
        }
    }

}
