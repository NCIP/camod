/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 *   
 * $Id: NameValueList.java,v 1.11 2009-05-20 17:12:20 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.10  2009/05/06 15:07:47  pandyas
 * modifed for #18338  	Add rabbit to species list
 * Added scientific name to validation list for species
 *
 * Revision 1.9  2009/03/25 16:24:14  pandyas
 * modified for #17833  	Make sure all references to Tranplantation are properly named
 *
 * Revision 1.8  2008/10/01 18:26:40  pandyas
 * Modifed validation - changed code to get Induced Mutation dropdown list from database to compare with the user input.  We no longer need hard-coded list for IM validation since the flag was added to EF in caMOD 2.5.
 *
 * Additional modification for gforge #12825  	induced mutation entries need to be flagged (requires OM change) and searches for induced mutation and carcinogenic interventions need to be fixed
 *
 * Revision 1.7  2008/08/13 18:40:28  pandyas
 * Fixed #6812  	Change spelling of Equus Caballus - start Caballus with a lower case "c"
 *
 * Revision 1.6  2008/05/22 18:21:06  pandyas
 * Cleaned up data for species.scientific_name so validation would identify valid species selections for prevention of SQL injection - removed hard-coded efforts
 *
 * Revision 1.5  2008/05/21 19:04:36  pandyas
 * Modified advanced search to prevent SQL injection
 * Concolidated all utility methods in new class
 * Re: Apps Scan run 05/15/2008
 *
 * Revision 1.4  2008/05/05 15:07:45  pandyas
 * NCI security scan changes - could not get value to work without spaces - trim did not work so added another entry to validate
 *
 * Revision 1.3  2008/02/18 16:19:58  pandyas
 * Fixed typo in rat scientific name
 *
 * Revision 1.2  2007/10/18 18:26:36  pandyas
 * Added to prevent cross--site scripting attacks
 *
 *
 */
package gov.nih.nci.camod.util;

import java.util.ArrayList;
import java.util.List;


public class NameValueList
{
    // stores a list of species
    private static List approvedSpeciesList = new ArrayList<String>();

    // stores a list of induced mutation agents
    private static List inducedMutationAgentList = new ArrayList<String>();

    // stores a list of external Sources
    private static List externalSourceList = new ArrayList<String>(); 
    
    // stores a list of Table of Content query names
    private static List tableOfContentsList = new ArrayList<String>();    
    

    
    public static void generateTableOfContentsList() {

    	tableOfContentsList = new ArrayList<String>();
    	tableOfContentsList.add(new NameValue("Cardiovascular_System_Query","Cardiovascular_System_Query"));
    	tableOfContentsList.add(new NameValue("Digestive_System_Query","Digestive_System_Query"));    	
    	tableOfContentsList.add(new NameValue("Endocrine_Gland_Query","Endocrine_Gland_Query"));
    	tableOfContentsList.add(new NameValue("Integument_System_Query","Integument_System_Query"));
    	tableOfContentsList.add(new NameValue("Lymphohematopoietic_System_Query","Lymphohematopoietic_System_Query"));
    	tableOfContentsList.add(new NameValue("Musculoskeletal_System_Query","Musculoskeletal_System_Query"));
    	tableOfContentsList.add(new NameValue("Nervous_System_Query","Nervous_System_Query"));
    	tableOfContentsList.add(new NameValue("Reproductive_System_Query","Reproductive_System_Query"));
    	tableOfContentsList.add(new NameValue("Respratory_System_Query","Respratory_System_Query"));
    	tableOfContentsList.add(new NameValue("Special_Sensory_Organs_Query","Special_Sensory_Organs_Query"));
    	tableOfContentsList.add(new NameValue("Urinary_System_Query","Urinary_System_Query"));
    	tableOfContentsList.add(new NameValue("Head_or_Neck_Query","Head_or_Neck_Query"));
    	tableOfContentsList.add(new NameValue("Prostate_Lesion_Query","Prostate_Lesion_Query"));
    	tableOfContentsList.add(new NameValue("Mammary_Gland_Query","Mammary_Gland_Query"));
    	tableOfContentsList.add(new NameValue("Brain_Tumor_Query","Brain_Tumor_Query"));
    	tableOfContentsList.add(new NameValue("Liver_Tumor_Query","Liver_Tumor_Query"));
    	tableOfContentsList.add(new NameValue("Metastases_Query","Metastases_Query"));
    	tableOfContentsList.add(new NameValue("Rattus_Norvegicus_Query","Rattus_Norvegicus_Query"));
    	tableOfContentsList.add(new NameValue("Rattus_Rattus_Query","Rattus_Rattus_Query"));
    	tableOfContentsList.add(new NameValue("Transplantation_Query","Transplantation_Query"));
    	
    }

    public static List<String> getTableOfContentsList()  {
    	generateTableOfContentsList();
        return  tableOfContentsList;
    }    
    
    public static void generateExternalSourceList() {

        externalSourceList = new ArrayList<String>();
        externalSourceList.add(new NameValue("Jax MTB","Jax MTB"));
    }

    public static List<String> getExternalSourceList()  {
        generateExternalSourceList();
        return  externalSourceList;
    }    
    
    public static void generateApprovedSpeciesList() {
        approvedSpeciesList = new ArrayList<Object>();
        // Put in order of most entered
        approvedSpeciesList.add(new NameValue("Mus musculus","Mus musculus"));  
        approvedSpeciesList.add(new NameValue("Rattus norvegicus","Rattus norvegicus"));
        approvedSpeciesList.add(new NameValue("Rattus rattus","Rattus rattus"));
        approvedSpeciesList.add(new NameValue("Danio rerio","Danio rerio"));  
        approvedSpeciesList.add(new NameValue("Mesocricetus auratus","Mesocricetus auratus"));
        approvedSpeciesList.add(new NameValue("Felis catus","Felis catus"));
        approvedSpeciesList.add(new NameValue("Bos taurus","Bos taurus"));  
        approvedSpeciesList.add(new NameValue("Canis familiaris","Canis familiaris"));         
        approvedSpeciesList.add(new NameValue("Homo sapiens","Homo sapiens")); 
        approvedSpeciesList.add(new NameValue("Meriones unguiculatus","Meriones unguiculatus")); 
        approvedSpeciesList.add(new NameValue("Cavia porcellus","Cavia porcellus")); 
        approvedSpeciesList.add(new NameValue("Capra hircus","Capra hircus"));         
        approvedSpeciesList.add(new NameValue("Equus caballus","Equus caballus")); 
        approvedSpeciesList.add(new NameValue("Ovis aries","Ovis aries")); 
        approvedSpeciesList.add(new NameValue("Sus scrofa","Sus scrofa"));         
        approvedSpeciesList.add(new NameValue("Saccharomyces cerevisiae","Saccharomyces cerevisiae"));
        approvedSpeciesList.add(new NameValue("Oryctolagus cuniculus","Oryctolagus cuniculus"));

    }
    public static List getApprovedSpeciesList() {
        generateApprovedSpeciesList();
        return  approvedSpeciesList ;
    }  
 
}
