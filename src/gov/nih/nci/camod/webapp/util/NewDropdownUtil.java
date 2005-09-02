package gov.nih.nci.camod.webapp.util;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.domain.Strain;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.service.AgentManager;
import gov.nih.nci.camod.service.TaxonManager;

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

        if (inDropdownKey.equals( Constants.Dropdowns.SURGERYDROP )) {
            theReturnList = getSurgeryList(inRequest);
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.STRAINDROP )) {
            theReturnList = getStrainsList(inRequest, inFilter);
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
     * @return speciesStrainList
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
     * @return
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
     * Returns a list of all types of surgeries
     * 
     */
    private static List getSurgeryList(HttpServletRequest inRequest) {

        AgentManager agentManager = (AgentManager) getContext(inRequest).getBean("agentManager");
        
        List agentList = agentManager.getAll();
        List surgeryList = new ArrayList();
        Agent tmp;

        // TODO: Fix once we know what we're doing w/ this
        surgeryList.add("Other");

        if (agentList != null) {
            for (int i = 0; i < agentList.size(); i++) {
                tmp = (Agent) agentList.get(i);
                
                if (tmp.getName() != null) {
                    // if the surgery is not already in the List, add it
                    // (only get unique names)
                    if ( !surgeryList.contains(tmp.getName()) && tmp.getType().equals( "Other" ) )
                    		surgeryList.add(tmp.getName());
                }
            }
        }
            
        Collections.sort(surgeryList);

        return surgeryList;
    }
}
