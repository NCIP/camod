package gov.nih.nci.camod.webapp.util;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.GeneDeliveryManager;
import gov.nih.nci.camod.service.TaxonManager;
import gov.nih.nci.camod.service.XenograftManager;
import gov.nih.nci.camod.service.impl.QueryManagerSingleton;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class NewDropdownUtil {

    private static Map ourFileBasedLists = new HashMap();

    public static void populateDropdown(HttpServletRequest inRequest, String inDropdownKey, String inFilter)
            throws Exception {

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

    private static List getDatabaseDropdown(HttpServletRequest inRequest, String inDropdownKey, String inFilter) throws Exception {

        List theReturnList = null;

        // Grab them for the first time
        if (inDropdownKey.equals( Constants.Dropdowns.SPECIESDROP )) {
            theReturnList = getSpeciesList(inRequest);
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.STRAINDROP )) {
            theReturnList = getStrainsList(inRequest, inFilter);
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.ADMINISTRATIVEROUTEDROP )) {
           // theReturnList = getAdminList(inRequest);
        }

        if (inDropdownKey.equals( Constants.Dropdowns.GRAFTTYPEDROP )) {
            theReturnList = getGrafttypeList(inRequest);
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.VIRALVECTORDROP )) {
            theReturnList = getViralVectorList(inRequest );
        } 
        
        //Environmental Factors - Carciogenic Interventions
        if (inDropdownKey.equals( Constants.Dropdowns.SURGERYDROP )) {
            theReturnList = getEnvironmentalFactorList("Other" );
        }

        if (inDropdownKey.equals( Constants.Dropdowns.HORMONEDROP )) {
            theReturnList = getEnvironmentalFactorList("Hormone" );
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.GROWTHFACTORDROP )) {
            theReturnList = getEnvironmentalFactorList("Growth Factor" );
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.CHEMICALDRUGDROP )) {
            theReturnList = getEnvironmentalFactorList("Chemical / Drug" );
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.VIRUSDROP )) {
            theReturnList = getEnvironmentalFactorList("Viral" );
        }
        
        if (inDropdownKey.equals( Constants.Dropdowns.RADIATIONDROP )) {
            theReturnList = getEnvironmentalFactorList("Radiation" );
        }        
        
        if (inDropdownKey.equals( Constants.Dropdowns.NUTRITIONFACTORDROP )) {
            theReturnList = getEnvironmentalFactorList("Nutrition" );
        }        
                
        if (inDropdownKey.equals( Constants.Dropdowns.ENVIRONFACTORDROP )) {
            theReturnList = getEnvironmentalFactorList("Environment" );
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
     * @throws Exception 
     */
    private static List getSpeciesList(HttpServletRequest inRequest) throws Exception {

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
     * @throws Exception 
     */
    private static List getStrainsList( HttpServletRequest inRequest, String speciesName ) throws Exception {
    	
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

               //strainNames.add(strain);
                
                if (strain.getName() != null) {
                    
                    if ( !strainNames.contains(strain.getName()) ) {
                    	strainNames.add(strain.getName());                    	
                    	System.out.println( "Strain Name>>" + j + ": " + strain.getName() );
                    }
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
     * Returns a list of all Graft Types
     * 
     * @return graftList
     */
    private static List getGrafttypeList(HttpServletRequest inRequest) {

        // Get values for dropdown lists for Species, Strains
        XenograftManager xenograftManager = ( XenograftManager ) getContext(inRequest).getBean("xenograftManager");
        
        List xenograftList = null;
        
        try {
        	xenograftList = xenograftManager.getAll();
        } catch (Exception e) {
           // TODO: Add error log handler here
           //log.error("Unable to getAll Xenografts ", e);
        }
        
        List graftList = new ArrayList();
        Xenograft tmp;
        
        graftList.add("Other");

        if (xenograftList != null) {
            for (int i = 0; i < xenograftList.size(); i++) {
                tmp = (Xenograft) xenograftList.get(i);

                if (tmp.getName() != null) {
                    // if the speciesName is not already in the List, add it
                    // (only get unique names)
                    if (!graftList.contains( tmp.getName() ) )
                    	graftList.add( tmp.getName() );
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
     */
    private static List getViralVectorList(HttpServletRequest inRequest) {

        // Get values for dropdown lists for Species, Strains
        GeneDeliveryManager geneDeliveryManager = ( GeneDeliveryManager ) getContext(inRequest).getBean("geneDeliveryManager");
        
        List geneDeliveryList = null;
        
        try {
        	 geneDeliveryList = geneDeliveryManager.getAll();
        } catch (Exception e) {}
        
        List viralVectorList = new ArrayList();
        GeneDelivery tmp;

        // TODO: Fix once we know what we're doing w/ this
        viralVectorList.add(Constants.Dropdowns.OTHER_OPTION);

        if (geneDeliveryList != null) {
            for (int i = 0; i < geneDeliveryList.size(); i++) {
                tmp = (GeneDelivery) geneDeliveryList.get(i);

                if (tmp.getViralVector() != null) {
                    // if the speciesName is not already in the List, add it
                    // (only get unique names)
                    if (!viralVectorList.contains(tmp.getViralVector()) )
                    	viralVectorList.add(tmp.getViralVector() );
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
}
