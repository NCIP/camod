/**
 * 
 * $Id: CriteriaTableUtil.java,v 1.3 2006-05-22 19:39:25 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/05/10 16:38:33  schroedn
 * Added save for Transient Instance
 *
 * Revision 1.1  2006/05/10 14:15:06  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.1  2006/04/28 19:22:31  schroedn
 * Defect # 261
 * Builds a HTML table the list of criteria to display on the search results jsp page
 *
 *
 */

package gov.nih.nci.camod.util;

import java.util.ResourceBundle;

import gov.nih.nci.camod.domain.SavedQueryAttribute;
import gov.nih.nci.camod.webapp.form.SearchData;

/**
 * @arthur schroedn
 *            
 */
public class CriteriaTableUtil
{
    /**
     * Creates a HTML table with all criteria formatted within
     * 
     * @param sData
     *            the SearchData to format
     * @return theDisplayTable
     *            formatted HTML table to display criteria
     * @exception Exception
     *                when anything goes wrong.
     */
    static public String buildCriteriaDisplayTable(SearchData sData) throws Exception
    {
        ResourceBundle theBundle = ResourceBundle.getBundle("ApplicationResources_en");

        String theDisplayTable = "<TABLE width=\"100%\" cellspacing=\"3\" cellpadding=\"0\">";

        // PI criteria
        if (sData.getPiName() != null)
        {
            if (sData.getPiName().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.PIName") + "</td><td class=\"formFieldNone\">" + sData.getPiName() + "</td></tr>";
            }
        }

        // Model descriptor criteria
        if (sData.getModelDescriptor() != null)
        {
            if (sData.getModelDescriptor().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.ModelDescriptor") + "</td><td class=\"formFieldNone\">" + sData.getModelDescriptor() + "</td></tr>";
            }
        }

        // Species criteria
        if (sData.getSpecies() != null)
        {
            if (sData.getSpecies().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.Species") + "</td><td class=\"formFieldNone\">" + sData.getSpecies() + "</td></tr>";
            }
        }

        // Search for organ
        if (sData.getOrganTissueName() != null)
        {
            if (sData.getOrganTissueName().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.OrganTissueName") + "</td><td class=\"formFieldNone\"><camod:shorten>" + sData.getOrganTissueName() + "</camod:shorten></td></tr>";

            }
        }

        // Search for organ
        if (sData.getOrgan() != null)
        {
            if (sData.getOrgan().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.Organ") + "</td><td class=\"formFieldNone\"><camod:shorten>" + sData.getOrgan() + "</camod:shorten></td></tr>";

            }
        }
        
        // Search for disease
        if (sData.getDiagnosisName() != null)
        {
            if (sData.getDiagnosisName().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.DiagnosisName") + "</td><td class=\"formFieldNone\"><camod:shorten>" + sData.getDiagnosisName() + "</camod:shorten></td></tr>";
            }
        }

        // Search for disease
        if (sData.getTumorClassification() != null)
        {
            if (sData.getTumorClassification().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.TumorClassification") + "</td><td class=\"formFieldNone\"><camod:shorten>" + sData.getTumorClassification() + "</camod:shorten></td></tr>";

            }
        }
        
        // ///////////////////////////////////////
        // Carcinogenic interventions
        // ///////////////////////////////////////

        if (sData.isSearchCarcinogenicInterventions() == true)
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchCarcinogenicInterventions") + "</td><td class=\"formFieldNone\">Checked</td></tr>";

            // Search for chemical/drug
            if (sData.getChemicalDrug() != null)
            {
                if (sData.getChemicalDrug().trim().length() > 0)
                {
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.ChemicalDrug") + "</td><td class=\"formFieldNone\">" + sData.getChemicalDrug() + "</td></tr>";
                }
            }

            // Search for Surgery/Other
            if (sData.getSurgery() != null)
            {
                if (sData.getSurgery().length() > 0)
                {
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.Surgery") + "</td><td class=\"formFieldNone\">" + sData.getSurgery() + "</td></tr>";
                }
            }

            // Search for Hormone
            if (sData.getHormone() != null)
            {
                if (sData.getHormone().length() > 0)
                {
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.Hormone") + "</td><td class=\"formFieldNone\">" + sData.getHormone() + "</td></tr>";
                }
            }

            // Search for Growth Factor
            if (sData.getGrowthFactor() != null)
            {
                if (sData.getGrowthFactor().length() > 0)
                {
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.GrowthFactor") + "</td><td class=\"formFieldNone\">" + sData.getGrowthFactor() + "</td></tr>";
                }
            }

            // Search for Radiation
            if (sData.getRadiation() != null)
            {
                if (sData.getRadiation().length() > 0)
                {
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.Radiation") + "</td><td class=\"formFieldNone\">" + sData.getRadiation() + "</td></tr>";
                }
            }

            // Search for Viral
            if (sData.getViral() != null)
            {
                if (sData.getViral().length() > 0)
                {
                    theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.Viral") + "</td><td class=\"formFieldNone\">" + sData.getViral() + "</td></tr>";
                }
            }

        }

        // Only call if some of the data is set : 
        if (sData.getGeneName() != null)
        {
            if (sData.getGeneName().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.GeneName") + "</td><td class=\"formFieldNone\">" + sData.getGeneName() + "</td></tr>";
            }
        }

        if (sData.isEngineeredTransgene())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isEngineeredTransgene") + "</td><td class=\"formFieldNone\">" + sData.isEngineeredTransgene() + "</td></tr>";
        }

        if (sData.isTargetedModification())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isTargetedModification") + "</td><td class=\"formFieldNone\">" + sData.isTargetedModification() + "</td></tr>";

        }

        if (sData.getGenomicSegDesignator() != null)
        {
            if (sData.getGenomicSegDesignator().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.GenomicSegDesignator") + "</td><td class=\"formFieldNone\">" + sData.getGenomicSegDesignator() + "</td></tr>";
            }
        }

        if (sData.getInducedMutationAgent() != null)
        {
            if (sData.getInducedMutationAgent().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.InducedMutationAgent") + "</td><td class=\"formFieldNone\">" + sData.getInducedMutationAgent() + "</td></tr>";
            }
        }

        // Search for phenotype
        if (sData.getPhenotype() != null)
        {
            if (sData.getPhenotype().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.Phenotype") + "</td><td class=\"formFieldNone\">" + sData.getPhenotype() + "</td></tr>";
            }
        }

        // Search for cellline
        if (sData.getCellLine() != null)
        {
            if (sData.getCellLine().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.CellLine") + "</td><td class=\"formFieldNone\">" + sData.getCellLine() + "</td></tr>";
            }
        }

        // Search for therapeutic approaches
        if (sData.isSearchTherapeuticApproaches())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchTherapeuticApproaches") + "</td><td class=\"formFieldNone\">" + "Checked" + "</td></tr>";
        }

        // Search for therapeutic approach
        if (sData.getTherapeuticApproach() != null)
        {
            if (sData.getTherapeuticApproach().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.TherapeuticApproach") + "</td><td class=\"formFieldNone\">" + sData.getTherapeuticApproach() + "</td></tr>";
            }
        }

        // Search for metastasis
        if (sData.isSearchHistoMetastasis())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchHistoMetastasis") + "</td><td class=\"formFieldNone\">" + "Checked" + "</td></tr>";
        }

        // Search for microarray data
        if (sData.isSearchMicroArrayData())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchMicroArrayData") + "</td><td class=\"formFieldNone\">" + "Checked" + "</td></tr>";
        }
        
        // Search for Transient Interface data
        if (sData.isSearchTransientInterference())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchTransientInterference") + "</td><td class=\"formFieldNone\">" + "Checked" + "</td></tr>";
        }
        
        // Search for xenograft
        if (sData.isSearchXenograft())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchXenograft") + "</td><td class=\"formFieldNone\">" + "Checked" + "</td></tr>";
        }

        theDisplayTable += "</TABLE>";

        return theDisplayTable;

    }
}
