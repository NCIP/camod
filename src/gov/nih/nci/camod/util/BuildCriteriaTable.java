/**
 * 
 * $Id: BuildCriteriaTable.java,v 1.1 2006-04-28 19:22:31 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

package gov.nih.nci.camod.util;

import gov.nih.nci.camod.webapp.form.SearchData;

public class BuildCriteriaTable
{
    public String buildCriteriaDisplayTable( SearchData sData ) throws Exception {
        String theDisplayTable = "<TABLE width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">";

        // PI criteria
        if (sData.getPiName() != null ) {
            if ( sData.getPiName().length() > 0) {

//                StringTokenizer theTokenizer = new StringTokenizer(sData.getPiName());
//                String theLastName = theTokenizer.nextToken(",").trim();
//                String theFirstName = theTokenizer.nextToken().trim();
    
                theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                   "Prinicipal Investigator" +
                                   "</td><td class=\"formFieldNone\">" +
                                   sData.getPiName() +
                                   "</td></tr>";  
            }
        }

        // Model descriptor criteria
        if (sData.getModelDescriptor() != null) {
            if ( sData.getModelDescriptor().trim().length() > 0) {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                   "Model Descriptor" +
                                   "</td><td class=\"formFieldNone\">" +
                                   sData.getModelDescriptor() +
                                   "</td></tr>";
            }
        }

        // Species criteria
        if (sData.getSpecies() != null ) {
            if ( sData.getSpecies().length() > 0) {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                    "Species" +
                                    "</td><td class=\"formFieldNone\">" +
                                    sData.getSpecies() +
                                    "</td></tr>";
            }
        }

        // Search for organ
        if (sData.getOrganTissueCode() != null ) {
                if( sData.getOrganTissueCode().length() > 0) {            
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                        "Organ Tissue" +
                                        "</td><td class=\"formFieldNone\">" +
                                        sData.getOrganTissueName() + " (" + sData.getOrganTissueCode() + ")" +
                                        "</td></tr>";            
        
                }
        }

        // Search for disease
        if (sData.getDiagnosisCode() != null ) {
            if( sData.getDiagnosisCode().trim().length() > 0) {       
                theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                    "Diagnosis Code" +
                                    "</td><td class=\"formFieldNone\">" +
                                    sData.getDiagnosisName() + " (" + sData.getDiagnosisCode() + ")" +
                                    "</td></tr>";     
            }
        }

        // ///////////////////////////////////////
        // Carcinogenic interventions
        // ///////////////////////////////////////

        if ( sData.isSearchCarcinogenicInterventions() == true ) {
    
            // Search for chemical/drug
            if ( sData.getChemicalDrug() != null ) {
                if ( sData.getChemicalDrug().trim().length() > 0) {                
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                        "Chemical Drug" +
                                        "</td><td class=\"formFieldNone\">" +
                                        sData.getChemicalDrug() +
                                        "</td></tr>";                   
                }
            }

            // Search for Surgery/Other
            if ( sData.getSurgery() != null ) {
                if( sData.getSurgery().length() > 0) {
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                        "Surgery/Other" +
                                        "</td><td class=\"formFieldNone\">" +
                                        sData.getSurgery() +
                                        "</td></tr>";                   
                }
            }

            // Search for Hormone
            if ( sData.getHormone() != null ) {
                if ( sData.getHormone().length() > 0 ) {
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                        "Hormone" +
                                        "</td><td class=\"formFieldNone\">" +
                                        sData.getHormone() +
                                        "</td></tr>";     
                }
            }

            // Search for Growth Factor
            if ( sData.getGrowthFactor() != null ) {
                if ( sData.getGrowthFactor().length() > 0 ) {
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                        "Growth Factor" +
                                        "</td><td class=\"formFieldNone\">" +
                                        sData.getGrowthFactor() +
                                        "</td></tr>";               
                }
            }

            // Search for Radiation
            if ( sData.getRadiation() != null ) {
                if ( sData.getRadiation().length() > 0 ) {
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                        "Radiation" +
                                        "</td><td class=\"formFieldNone\">" +
                                        sData.getRadiation() +
                                        "</td></tr>";            
                }
            }

            // Search for Viral
            if (sData.getViral() != null ) {
                if( sData.getViral().length() > 0 ) {
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                        "Viral" +
                                        "</td><td class=\"formFieldNone\">" +
                                        sData.getViral() +
                                        "</td></tr>";        
                }
            }
            
        }

        // Only call if some of the data is set : 
        if (sData.getGeneName() != null ) {
            if ( sData.getGeneName().trim().length() > 0 ) {            
                theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                    "Gene Name" +
                                    "</td><td class=\"formFieldNone\">" +
                                    sData.getGeneName() +
                                    "</td></tr>"; 
            }
        }
                   
        if (sData.isEngineeredTransgene()) {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                "is EngineeredTransgene" +
                                "</td><td class=\"formFieldNone\">" +
                                sData.isEngineeredTransgene() +
                                "</td></tr>";
        }
            
        if (sData.isTargetedModification()) {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                "is Targeted Modification" +
                                "</td><td class=\"formFieldNone\">" +
                                sData.isTargetedModification() +
                                "</td></tr>";   
            
        }
            
         if ( sData.getGenomicSegDesignator() != null ) {
             if ( sData.getGenomicSegDesignator().trim().length() > 0 ) {
                 theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                     "Genomic Segment Designator" +
                                     "</td><td class=\"formFieldNone\">" +
                                     sData.getGenomicSegDesignator() +
                                     "</td></tr>"; 
             }
         }
        
         if ( sData.getInducedMutationAgent() != null ) {
             if ( sData.getInducedMutationAgent().trim().length() > 0 ) {
                 theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                     "Induced Mutation Agent" +
                                     "</td><td class=\"formFieldNone\">" +
                                     sData.getInducedMutationAgent() +
                                     "</td></tr>";   
             }
         }
            
        // Search for phenotype
        if ( sData.getPhenotype() != null ) {
            if ( sData.getPhenotype().trim().length() > 0 ) {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                    "Phenotype" +
                                    "</td><td class=\"formFieldNone\">" +
                                    sData.getPhenotype() +
                                    "</td></tr>";
            }
        }

        // Search for cellline
        if ( sData.getCellLine() != null ) {
            if ( sData.getCellLine().trim().length() > 0 ) {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                    "Cell Line" +
                                    "</td><td class=\"formFieldNone\">" +
                                    sData.getCellLine() +
                                    "</td></tr>";    
            }
        }

        // Search for therapeutic approaches
        if (sData.isSearchTherapeuticApproaches()) {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                "is Therapeutic Approach" +
                                "</td><td class=\"formFieldNone\">" +
                                "checked" +
                                "</td></tr>";   
        }
        
        // Search for therapeutic approach
        if ( sData.getTherapeuticApproach() != null ) {
            if ( sData.getTherapeuticApproach().trim().length() > 0 ) {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                    "Therapeutic Approach" +
                                    "</td><td class=\"formFieldNone\">" +
                                    sData.getTherapeuticApproach() +
                                    "</td></tr>";
            }             
        }

        // Search for metastasis
        if (sData.isSearchHistoMetastasis()) {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                "Metastasis" +
                                "</td><td class=\"formFieldNone\">" +
                                "checked" +
                                "</td></tr>";   
        }

        // Search for microarray data
        if (sData.isSearchMicroArrayData()) {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                "MicroArray Data" +
                                "</td><td class=\"formFieldNone\">" +
                                "checked" +
                                "</td></tr>";               
        }

        // Search for xenograft
        if (sData.isSearchXenograft()) {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" +
                                "Xenograft" +
                                "</td><td class=\"formFieldNone\">" +
                                "checked" +
                                "</td></tr>";   
        }
               
        theDisplayTable += "</TABLE>";
                             
        return theDisplayTable;

    }
}
