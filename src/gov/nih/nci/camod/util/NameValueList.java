package gov.nih.nci.camod.util;

import java.util.ArrayList;
import java.util.List;

public class NameValueList
{
    // stores a list of PI Names Phases
    private static List piNameList = new ArrayList();

    // stores a list of species
    private static List approvedSpeciesList = new ArrayList();

    // stores a list of induced mutation agents
    private static List inducedMutationAgentList = new ArrayList();

    // stores a list of carcinogenic interventions
    private static List carcinogenicInterventionList = new ArrayList();

    // stores a list of agent names
    private static List agentNameList = new ArrayList();

    // stores a list of external Sources
    private static List externalSourceList = new ArrayList();
    
    
    
    
    public static void generateExternalSourceList() {

        externalSourceList = new ArrayList();
        externalSourceList.add(new NameValue("Jax MTB","Jax MTB"));
    }

    public static List getExternalSourceList()  {
        generateExternalSourceList();
        return  externalSourceList ;
    }    
    
    public static void generateApprovedSpeciesList() {
        approvedSpeciesList = new ArrayList();
        // Put in order of most entered
        approvedSpeciesList.add(new NameValue("Mus musculus","Mus musculus"));  
        approvedSpeciesList.add(new NameValue("Rattus norvegicus","Rattus norvegicus"));
        approvedSpeciesList.add(new NameValue("Rattus ratus","Rattus ratus"));
        approvedSpeciesList.add(new NameValue("Danio rerio","Danio rerio"));  
        
        approvedSpeciesList.add(new NameValue("Felis catus","Felis catus"));
        approvedSpeciesList.add(new NameValue("Bos taurus","Bos taurus"));  
        approvedSpeciesList.add(new NameValue("Mesocricetus auratus","Mesocricetus auratus")); 
        approvedSpeciesList.add(new NameValue("Canis familiaris","Canis familiaris"));         
        approvedSpeciesList.add(new NameValue("Homo sapiens","Homo sapiens")); 
        approvedSpeciesList.add(new NameValue("Meriones unguiculatus","Meriones unguiculatus")); 
        approvedSpeciesList.add(new NameValue("Cavia porcellus","Cavia porcellus")); 
        approvedSpeciesList.add(new NameValue("Capra hircus","Capra hircus"));         
        approvedSpeciesList.add(new NameValue("Equus Caballus","Equus Caballus")); 
        approvedSpeciesList.add(new NameValue("Ovis aries","Ovis aries")); 
        approvedSpeciesList.add(new NameValue("Sus scrofa","Sus scrofa"));         
        approvedSpeciesList.add(new NameValue("Saccharomyces cerevisiae","Saccharomyces cerevisiae"));     

    }
    public static List getApprovedSpeciesList() {
        generateApprovedSpeciesList();
        return  approvedSpeciesList ;
    } 
    
    public static void generateInducedMutationAgentList() {

        inducedMutationAgentList = new ArrayList();
        inducedMutationAgentList.add(new NameValue("ENU","ENU"));
        inducedMutationAgentList.add(new NameValue("ethylnitrosourea","ethylnitrosourea"));
        inducedMutationAgentList.add(new NameValue("name of inducing agent","name of inducing agent"));        
    }

    public static List getInducedMutationAgentList()  {
        generateInducedMutationAgentList();
        return  inducedMutationAgentList ;
    } 
    
    public static void generateCarcinogenicInterventionList() {

        carcinogenicInterventionList = new ArrayList();
        carcinogenicInterventionList.add(new NameValue("Chemical / Drug","Chemical / Drug"));
        carcinogenicInterventionList.add(new NameValue("Environment","Environment"));
        carcinogenicInterventionList.add(new NameValue("Growth Factor","Growth Factor")); 
        carcinogenicInterventionList.add(new NameValue("Hormone","Hormone"));
        carcinogenicInterventionList.add(new NameValue("Nutrition","Nutrition")); 
        carcinogenicInterventionList.add(new NameValue("Other","Other"));
        carcinogenicInterventionList.add(new NameValue("Radiation","Radiation")); 
        carcinogenicInterventionList.add(new NameValue("Viral","Viral"));
        carcinogenicInterventionList.add(new NameValue("Antibody","Antibody")); 
        
        carcinogenicInterventionList.add(new NameValue("Bacteria","Bacteria"));
        carcinogenicInterventionList.add(new NameValue("Plasmid","Plasmid")); 
        carcinogenicInterventionList.add(new NameValue("Transposon","Transposon"));
        
    }

    public static List getCarcinogenicInterventionList()  {
        generateCarcinogenicInterventionList();
        return  carcinogenicInterventionList ;
    }   
    
    public static void generatePiNameList() {

        piNameList = new ArrayList();
        piNameList.add(new NameValue("Chemical / Drug","Chemical / Drug"));
        piNameList.add(new NameValue("Environment","Environment"));
        piNameList.add(new NameValue("Growth Factor","Growth Factor")); 
        piNameList.add(new NameValue("Hormone","Hormone"));
        piNameList.add(new NameValue("Nutrition","Nutrition")); 
        piNameList.add(new NameValue("Other","Other"));
        piNameList.add(new NameValue("Radiation","Radiation")); 
        piNameList.add(new NameValue("Viral","Viral"));
        piNameList.add(new NameValue("Antibody","Antibody"));        
        piNameList.add(new NameValue("Bacteria","Bacteria"));
        piNameList.add(new NameValue("Plasmid","Plasmid")); 
        piNameList.add(new NameValue("Transposon","Transposon"));
        
    }

    public static List getPiNameList()  {
        generatePiNameList();
        return  piNameList ;
    }    
}
