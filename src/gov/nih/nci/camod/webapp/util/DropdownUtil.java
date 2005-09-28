package gov.nih.nci.camod.webapp.util;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.TaxonManager;
import gov.nih.nci.camod.webapp.action.BaseAction;

import java.io.*;
import java.util.*;

public class DropdownUtil extends BaseAction {

    /**
     * Returns a list of all Species and Strains
     * 
     * @return speciesStrainList
     * @throws Exception 
     */
    public List getSpeciesList() throws Exception {
        
        // Get values for dropdown lists for Species, Strains
        // First get a list of all taxons
        // for each taxon, get it's scientificName (it's species name)
        // for each unique species name retrieve all (if any) strain names
        TaxonManager taxonManager = (TaxonManager) getBean("taxonManager");
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

        // see if this will work
        for (int i = 0; i < speciesNames.size(); i++) {
            System.out.println("Species: " + speciesNames.get(i).toString());
            this.getStrainList(speciesNames.get(i).toString());
        }

        return speciesNames;
    }

    /**
     * Based on a species name retrieve a list of all Strains
     * 
     * @param speciesName
     * @return
     * @throws Exception 
     */
    public List getStrainList(String speciesName) throws Exception {
        TaxonManager taxonManager = (TaxonManager) getBean("taxonManager");

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
     * Give a filename retrieve all lines of that file and put them into a List
     * 
     * @return dropdown
     */
    public List getDropdownListFromFile(String inFilename) {
        // Read in a config file with all available options
        List dropdown = new ArrayList();

        System.out.println("<getDropdownListFromFile> Entering... " + inFilename);

        try {
            BufferedReader in = new BufferedReader(new FileReader(inFilename));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println("<getDropdownListFromFile> Reading config file: " + inFilename + " ->" + str);
                dropdown.add(str);
            }
            in.close();
        } catch (IOException e) {
        }

        return dropdown;
    }
}

/*
 * List speciesStrainLst = new ArrayList(); Species species = new Species(); //
 * for each speciesName retrieve a list of strain names for ( int i=0; i <
 * speciesNames.size(); i++ ) {
 * 
 * species.setName( (String) speciesNames.get(i) );
 * 
 * strainList = new ArrayList(); strainList = taxonManager.getStrains( species );
 * 
 * strainNames = new ArrayList();
 * 
 * //print out strainNames for ( int j=0; j < strainList.size(); j++ ) { Strain
 * strain = (Strain) strainList.get( j );
 * 
 * if ( strain.getName() != null ) { strainNames.add( strain.getName() );
 * System.out.println( ">>" + j + ": " + strain.getName() ); } }
 * 
 * SpeciesStrain spStrainObj = new SpeciesStrain(); spStrainObj.speciesName =
 * (String) speciesNames.get(i); // Sort the list in 'abc' order if (
 * strainNames.size() > 0 ) Collections.sort( strainNames );
 * 
 * //Add additional Strain named 'Other' to every list strainNames.add( "Other" );
 * 
 * spStrainObj.strainList = strainNames; speciesStrainLst.add( spStrainObj ); }
 * 
 * //print it out, species and strains SpeciesStrain spStrainObj = new
 * SpeciesStrain(); for ( int y=0; y < speciesStrainLst.size(); y++ ) {
 * spStrainObj = (SpeciesStrain) speciesStrainLst.get(y); System.out.println(
 * "\nspecies: " + y + ": " + spStrainObj.speciesName );
 * 
 * for ( int z=0; z < spStrainObj.getStrainList().size(); z++ ){
 * System.out.println( "strain name: " + z + ": " +
 * spStrainObj.getStrainList().get(z).toString() ); } }
 * 
 * return speciesStrainLst;
 */

/*
 * List genderList = new ArrayList(); // Print a list of all the
 * SexDistributions System.out.println( "\n\n<DropdownUtil
 * getSexDistributionsList> SexDistributionNames List" );
 * 
 * SexDistributionManager sexDistributionManager = (SexDistributionManager)
 * getBean( "sexDistributionManager" ); List sexDistributionNames =
 * sexDistributionManager.getAll(); // TODO: REMOVE DUPLICATES! //sort list by
 * modelDescriptor, ignoring case //Collections.sort( sexDistributionNames, new
 * _removeDuplicates() ); //Collections.sort( sexDistributionNames );
 * 
 * Set set = new TreeSet(); //set.addAll( sexDistributionNames );
 * 
 * for( int i=0; i< sexDistributionNames.size(); i++ ) { SexDistribution
 * sexType = (SexDistribution) sexDistributionNames.get(i); set.add(
 * sexType.getType() ); }
 * 
 * Iterator iter = set.iterator(); while ( iter.hasNext() ){ genderList.add(
 * iter.next() ); }
 * 
 * return sexDistributionNames;
 */

