package gov.nih.nci.camod.webapp.util;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.service.TaxonManager;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class NewDropdownUtil {

    private static Map ourFileBasedLists = new HashMap();

    public static void populateDropdown(HttpServletRequest inRequest, String inDropdownKey)
            throws IllegalArgumentException {

        List theList = getTextFileDropdown(inRequest, inDropdownKey);

        if (theList == null) {
            theList = getDatabaseDropdown(inRequest, inDropdownKey);
        }

        if (theList == null) {
            throw new IllegalArgumentException("Unknown dropdown list key: " + inDropdownKey);
        }

        System.out.println("List: " + theList);
        inRequest.setAttribute(inDropdownKey, theList);
    }

    private static List getDatabaseDropdown(HttpServletRequest inRequest, String inDropdownKey) {

        List theReturnList = null;

        // Grab them for the first time
        if (inDropdownKey.equals(Constants.SPECIESDROP)) {

            theReturnList = getSpeciesList(inRequest);
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

            String theBasePath = inRequest.getSession().getServletContext().getRealPath("/config/dropdowns");

            List theList = new ArrayList();

            // Grab them for the first time
            if (inDropdownKey.equals(Constants.SEXDISTRIBUTIONDROP)) {

                try {

                    String theFilename = theBasePath + "/SexDistributions.txt";
                    System.out.println("The filename: " + theFilename);
                    BufferedReader in = new BufferedReader(new FileReader(theFilename));

                    String str;
                    while ((str = in.readLine()) != null) {
                        System.out.println("<getDropdownListFromFile> Reading config file: " + theFilename + " ->"
                                + str);
                        theList.add(str);
                    }
                    in.close();

                } catch (IOException e) {
                    // TODO: Should propagate exception
                }
            }

            // Built a list. Add to static hash
            if (theList.size() != 0) {

                ourFileBasedLists.put(inDropdownKey, theList);
                theReturnList = theList;
            }
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
                    // (only
                    // get unique names)
                    if (!speciesNames.contains(tmp.getScientificName()))
                        speciesNames.add(tmp.getScientificName());
                }
            } // + " (" + tmp.getCommonName() + ")"
        }
        Collections.sort(speciesNames);

        return speciesNames;
    }
}
