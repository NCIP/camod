/**
 * 
 * $Id: CriteriaTableUtil.java,v 1.12 2008-10-22 05:57:18 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2008/08/12 19:44:53  pandyas
 * Fixed #15053  	Search for models with transgenic or targeted modification on advanced search page confusing
 *
 * Revision 1.10  2008/01/16 18:30:04  pandyas
 * Renamed value to Transplant for #8290
 *
 * Revision 1.9  2007/11/01 13:37:03  pandyas
 * Fixed #8290     Rename graft object into transplant object
 *
 * Revision 1.8  2007/07/31 12:02:07  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.7  2007/03/28 18:02:04  pandyas
 * Modified for the following Test Track items:
 * #462 - Customized search for carcinogens for Jackson Lab data
 * #494 - Advanced search for Carcinogens for Jackson Lab data
 *
 * Revision 1.6  2006/10/17 16:11:52  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.5  2006/06/12 18:38:02  pandyas
 * removed unused import
 *
 * Revision 1.4  2006/05/22 20:10:40  schroedn
 * Added ability to save keyword searches
 *
 * Revision 1.3  2006/05/22 19:39:25  schroedn
 * Display criteria for AJAX search fields correctly
 *
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
        
        // Keyword Search
        if (sData.getKeyword() != null)
        {
            if (sData.getKeyword().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.Keyword") + "</td><td class=\"formFieldNone\">" + sData.getKeyword() + "</td></tr>";
            }
        }
        
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
        
        // PMID criteria
        if (sData.getPmid()!= null)
        {
            if (sData.getPmid().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.Pmid") + "</td><td class=\"formFieldNone\">" + sData.getPmid() + "</td></tr>";
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

        if (sData.getCarcinogenicIntervention() != null)
        {
            if (sData.getCarcinogenicIntervention().trim().length() > 0)
            {        	
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.CarcinogenicIntervention") + "</td><td class=\"formFieldNone\">" + sData.getCarcinogenicIntervention() + "</td></tr>";
            }
        }
        
        // Search for agent Name
        if (sData.getAgentName() != null)
        {
            if (sData.getAgentName().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.AgentName") + "</td><td class=\"formFieldNone\">" + sData.getAgentName() + "</td></tr>";
            }
        }        

        if (sData.isSearchEngineeredTransgene())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchEngineeredTransgene") + "</td><td class=\"formFieldNone\">" + sData.isSearchEngineeredTransgene() + "</td></tr>";
        }

        if (sData.isSearchTargetedModification())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchTargetedModification") + "</td><td class=\"formFieldNone\">" + sData.isSearchTargetedModification() + "</td></tr>";

        }
        
        if (sData.getTransgeneName() != null)
        {
            if (sData.getTransgeneName().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.TransgeneName") + "</td><td class=\"formFieldNone\">" + sData.getTransgeneName() + "</td></tr>";
            }
        }         

        if (sData.getGenomicSegDesignator() != null)
        {
            if (sData.getGenomicSegDesignator().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.GenomicSegDesignator") + "</td><td class=\"formFieldNone\">" + sData.getGenomicSegDesignator() + "</td></tr>";
            }
        }
        
        if (sData.getGeneName() != null)
        {
            if (sData.getGeneName().trim().length() > 0)
            {
                theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.GeneName") + "</td><td class=\"formFieldNone\">" + sData.getGeneName() + "</td></tr>";
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
        
        // Search for image data
        if (sData.isSearchImageData())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchImageData") + "</td><td class=\"formFieldNone\">" + "Checked" + "</td></tr>";
        }        
        
        // Search for tool strain data
        if (sData.isSearchToolStrain())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchToolStrain") + "</td><td class=\"formFieldNone\">" + "Checked" + "</td></tr>";
        }        
        
        // Search for Transient Interface data
        if (sData.isSearchTransientInterference())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchTransientInterference") + "</td><td class=\"formFieldNone\">" + "Checked" + "</td></tr>";
        }
        
        // Search for Transplant
        if (sData.isSearchTransplant())
        {
            theDisplayTable += "<tr><td class=\"formFieldNone\">" + theBundle.getString("criteria.isSearchTransplant") + "</td><td class=\"formFieldNone\">" + "Checked" + "</td></tr>";
        }

        theDisplayTable += "</TABLE>";

        return theDisplayTable;

    }
}
