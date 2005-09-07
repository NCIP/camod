package gov.nih.nci.camod.webapp.util;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.domain.Strain;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Treatment;
import gov.nih.nci.camod.service.AgentManager;
import gov.nih.nci.camod.service.TaxonManager;
import gov.nih.nci.camod.service.TreatmentManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class NewDropdownUtil {

    private static Map ourFileBasedLists = new HashMap();

    public static void populateDropdown(HttpServletRequest inRequest, String inDropdownKey, String inFilter)
            throws IllegalArgumentException {

        List theList = null;
        if (inDropdownKey.indexOf(".txt") != -1) {
            theList = getTextFileDropdown(inRequest, inDropdownKey);
        } else if (inDropdownKey.indexOf(".db") != -1) {
            theList = getDatabaseDropdown(inRequest, inDropdownKey, inFilter);
        }

        if (theList == null) {
            throw new IllegalArgumentException("Unknown dropdown list key: " + inDropdownKey);
        }

        System.out.println("<NewDropdownUtil populateDropdown> inDropdownKey: " + inDropdownKey);
        System.out.println("<NewDropdownUtil populateDropdown> inFilter: " + inFilter);
        System.out.println("<NewDropdownUtil populateDropdown> List: " + theList);

        inRequest.setAttribute(inDropdownKey, theList);
    }

    private static List getDatabaseDropdown(HttpServletRequest inRequest, String inDropdownKey, String inFilter) {

        List theReturnList = null;

        // Grab them for the first time
        if (inDropdownKey.equals( Constants.Dropdowns.SPECIESDROP )) {
            theReturnList = getSpeciesList(inRequest);
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.STRAINDROP )) {
            theReturnList = getStrainsList(inRequest, inFilter);
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.ADMINISTRATIVEROUTEDROP )) {
            theReturnList = getAdminList(inRequest);
        }

        //Environmental Factors - Carciogenic Interventions
        if (inDropdownKey.equals( Constants.Dropdowns.SURGERYDROP )) {
            theReturnList = getEnvironmentalFactorList(inRequest, "Other" );
        }

        if (inDropdownKey.equals( Constants.Dropdowns.HORMONEDROP )) {
            theReturnList = getEnvironmentalFactorList(inRequest, "Hormone" );
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.GROWTHFACTORDROP )) {
            theReturnList = getEnvironmentalFactorList(inRequest, "Growth Factor" );
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.CHECMICALDRUGDROP )) {
            theReturnList = getEnvironmentalFactorList(inRequest, "Chemical / Drug" );
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.VIRUSDROP )) {
            theReturnList = getEnvironmentalFactorList(inRequest, "Viral" );
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.ENVIRONFACTORDROP )) {
            theReturnList = getEnvironmentalFactorList(inRequest, "Environment" );
        } 
        
        if (inDropdownKey.equals( Constants.Dropdowns.RADIATIONDROP )) {
            theReturnList = getEnvironmentalFactorList(inRequest, "Radiation" );
        } 
      
                   
        return theReturnList;
    }

    private static WebApplicationContext getContext(HttpServletRequest inRequest) {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(inRequest.getSession().getServletContext());
    }

    private static synchronized List getTextFileDropdown(HttpServletRequest inRequest, String inDropdownKey) {

        List theReturnList = null;

        if (ourFileBasedLists.containsKey(inDropdownKey)) {
            theReturnList = (List) ourFileBasedLists.get(inDropdownKey);
        } else {

            String theFilename = inRequest.getSession().getServletContext().getRealPath("/config/dropdowns") + "/"
                    + inDropdownKey;

            List theList = readListFromFile(theFilename);

            // Built a list. Add to static hash
            if (theList.size() != 0) {
                ourFileBasedLists.put(inDropdownKey, theList);
                theReturnList = theList;
            }
        }

        return theReturnList;
    }

    static private List readListFromFile(String inFilename) {
        List theReturnList = new ArrayList();

        try {
            System.out.println("The filename: " + inFilename);
            BufferedReader in = new BufferedReader(new FileReader(inFilename));

            String str;
            while ((str = in.readLine()) != null) {
                System.out.println("<getDropdownListFromFile> Reading config file: " + inFilename + " ->" + str);
                theReturnList.add(str);
            }
            in.close();

        } catch (IOException e) {
            // TODO: Should propagate exception
        }

        return theReturnList;
    }

    /**
     * Returns a list of all Species and Strains
     * 
     * @return speciesNames
     */
    private static List getSpeciesList(HttpServletRequest inRequest) {

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
     * Based on a species name retrieve a list of all Strains
     * 
     * @param speciesName
     * @return strainNames
     */
    private static List getStrainsList( HttpServletRequest inRequest, String speciesName ) {
    	
        TaxonManager taxonManager = ( TaxonManager) getContext(inRequest).getBean("taxonManager");

        Species species = new Species();
        species.setName(speciesName);

        List strainList = new ArrayList();
        List strainNames = new ArrayList();

        strainList = taxonManager.getStrains(species);

        if (strainList != null) {
            // print out strainNames
            for (int j = 0; j < strainList.size(); j++) {
                Strain strain = (Strain) strainList.get(j);

                if (strain.getName() != null) {
                    strainNames.add(strain.getName());
                    System.out.println("Strain Name>>" + j + ": " + strain.getName());
                }
            }
        }

        // Sort the list in 'abc' order
        if (strainNames.size() > 0)
            Collections.sort(strainNames);

        strainNames.add("Other");

        return strainNames;
    }
    
    /**
     * Returns a list of all Administrative Routese
     * 
     * @return adminList
     */
    private static List getAdminList(HttpServletRequest inRequest) {

        // Get values for dropdown lists for Species, Strains
        TreatmentManager treatmentManager = ( TreatmentManager ) getContext(inRequest).getBean("treatmentManager");
        
        List treatmentList = treatmentManager.getAll();
        List adminList = new ArrayList();
        Treatment tmp;

        // TODO: Fix once we know what we're doing w/ this
        adminList.add("Other");

        if (treatmentList != null) {
            for (int i = 0; i < treatmentList.size(); i++) {
                tmp = (Treatment) treatmentList.get(i);

                if (tmp.getAdministrativeRoute() != null) {
                    // if the speciesName is not already in the List, add it
                    // (only get unique names)
                    if (!adminList.contains(tmp.getAdministrativeRoute()) )
                    	adminList.add(tmp.getAdministrativeRoute() );
                }
            } 
        }
        
        Collections.sort(adminList);
        
        return adminList;
    }
    
    /**
     * Returns a list for a type of environmental Factore
     * 
     * @return envList
     */
    private static List getEnvironmentalFactorList(HttpServletRequest inRequest, String type) {

        AgentManager agentManager = (AgentManager) getContext(inRequest).getBean("agentManager");
        
        List agentList = agentManager.getAll();
        List envList = new ArrayList();
        Agent tmp;

        // TODO: Fix once we know what we're doing w/ this
        envList.add("Other");

        if (agentList != null) {
            for (int i = 0; i < agentList.size(); i++) {
                tmp = (Agent) agentList.get(i);
                
                if (tmp.getName() != null) {
                    // if the surgery is not already in the List, add it
                    // (only get unique names)
                    if ( !envList.contains(tmp.getName()) && tmp.getType().equals( type ) )
                    	envList.add(tmp.getName());
                }
            }
        }
            
        Collections.sort(envList);

        return envList;
    }
    
}
