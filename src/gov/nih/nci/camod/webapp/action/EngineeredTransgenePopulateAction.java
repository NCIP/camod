package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.impl.EngineeredTransgeneManagerSingleton;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneForm;
import gov.nih.nci.camod.webapp.util.DropdownOption;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class EngineeredTransgenePopulateAction extends BaseAction {

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<EngineeredTransgenePopulateAction populate> Entering populate() ");

        EngineeredTransgeneForm engineeredTransgeneForm = (EngineeredTransgeneForm) form;

        String aEngineeredTransgeneID = request.getParameter("aEngineeredTransgeneID");
        engineeredTransgeneForm.setTransgeneId(aEngineeredTransgeneID);
        
        Transgene theEngineeredTransgene = EngineeredTransgeneManagerSingleton.instance().get(aEngineeredTransgeneID);

        if (theEngineeredTransgene == null) {
        	request.setAttribute(Constants.Parameters.DELETED, "true");
        } else {

            // Tansgene Integration
            if (theEngineeredTransgene.getLocationOfIntegration().equals("Random")) {
                engineeredTransgeneForm.setLocationOfIntegration(theEngineeredTransgene.getLocationOfIntegration());
            } else {
                engineeredTransgeneForm.setLocationOfIntegration("Targeted");
                engineeredTransgeneForm
                        .setOtherLocationOfIntegration(theEngineeredTransgene.getLocationOfIntegration());
            }

            // Transgene (coding sequence only)
            List taxList = theEngineeredTransgene.getTaxonCollection();
            Taxon tax = (Taxon) taxList.get(0);

            // NOTE: This is needed because we are storing 'Other' Species
            // selections directly into the database
            // and are retrieving the dropdowns for this page from a text file (
            // HostSpecies.txt)
            // We need to get the list from
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.HOSTSPECIESDROP, "");
            List dropdownTaxList = (List) request.getSession().getAttribute(Constants.Dropdowns.HOSTSPECIESDROP);

            engineeredTransgeneForm.setName(theEngineeredTransgene.getName());

            DropdownOption theOption = new DropdownOption("",tax.getScientificName());
            
            if (dropdownTaxList.contains(theOption)) {
                System.out.println("\t(Transgene) Matched the list");
                engineeredTransgeneForm.setScientificName(tax.getScientificName());
            } else {
                System.out.println("\t(Transgene) Didn't Match the list");
                engineeredTransgeneForm.setScientificName("Other");
                engineeredTransgeneForm.setOtherScientificName(tax.getScientificName());
            }

            // Transcriptional (Promoter) 1
            List regElementList = theEngineeredTransgene.getRegulatoryElementCollection();
            RegulatoryElement regElement = null;
            Taxon tax_reg1 = null;

            for (int i = 0; i < regElementList.size(); i++) {
                regElement = (RegulatoryElement) regElementList.get(i);

                if (regElement.getRegulatoryElementType().getName().equals("Transcriptional 1")) {
                    engineeredTransgeneForm.setTranscriptional1_name(regElement.getName());
                    tax_reg1 = regElement.getTaxon();

                    theOption = new DropdownOption("",tax_reg1.getScientificName());
                    if (dropdownTaxList.contains(theOption)) {
                        engineeredTransgeneForm.setTranscriptional1_species(tax_reg1.getScientificName());
                    } else {
                        engineeredTransgeneForm.setTranscriptional1_species("Other");
                        engineeredTransgeneForm.setTranscriptional1_otherSpecies(tax_reg1.getScientificName());
                    }
                }

                if (regElement.getRegulatoryElementType().getName().equals("Transcriptional 2")) {
                    engineeredTransgeneForm.setTranscriptional2_name(regElement.getName());
                    tax_reg1 = regElement.getTaxon();

                    theOption = new DropdownOption("",tax_reg1.getScientificName());
                    if (dropdownTaxList.contains(theOption)) {
                        engineeredTransgeneForm.setTranscriptional2_species(tax_reg1.getScientificName());
                    } else {
                        engineeredTransgeneForm.setTranscriptional2_species("Other");
                        engineeredTransgeneForm.setTranscriptional2_otherSpecies(tax_reg1.getScientificName());
                    }
                }

                if (regElement.getRegulatoryElementType().getName().equals("Transcriptional 3")) {
                    engineeredTransgeneForm.setTranscriptional3_name(regElement.getName());
                    tax_reg1 = regElement.getTaxon();
                    
                    theOption = new DropdownOption("",tax_reg1.getScientificName());
                    if (dropdownTaxList.contains(theOption)) {
                        engineeredTransgeneForm.setTranscriptional3_species(tax_reg1.getScientificName());
                    } else {
                        engineeredTransgeneForm.setTranscriptional3_species("Other");
                        engineeredTransgeneForm.setTranscriptional3_otherSpecies(tax_reg1.getScientificName());
                    }
                }

                if (regElement.getRegulatoryElementType().getName().equals("Poly A Signal")) {
                    engineeredTransgeneForm.setPolyASignal_name(regElement.getName());
                    tax_reg1 = regElement.getTaxon();

                    theOption = new DropdownOption("",tax_reg1.getScientificName());
                    if (dropdownTaxList.contains(theOption)) {
                        engineeredTransgeneForm.setPolyASignal_species(tax_reg1.getScientificName());
                    } else {
                        engineeredTransgeneForm.setPolyASignal_species("Other");
                        engineeredTransgeneForm.setPolyASignal_otherSpecies(tax_reg1.getScientificName());
                    }
                }

                if (regElement.getRegulatoryElementType().getName().equals("Splice Site")) {
                    engineeredTransgeneForm.setSpliceSites_name(regElement.getName());
                    tax_reg1 = regElement.getTaxon();

                    theOption = new DropdownOption("", tax_reg1.getScientificName());
                    if (dropdownTaxList.contains(theOption)) {
                        engineeredTransgeneForm.setSpliceSites_species(tax_reg1.getScientificName());
                    } else {
                        engineeredTransgeneForm.setSpliceSites_species("Other");
                        engineeredTransgeneForm.setSpliceSites_otherSpecies(tax_reg1.getScientificName());
                    }
                }
            }

            // MGI Number
            MutationIdentifier inMutationIdentifier = theEngineeredTransgene.getMutationIdentifier();
            if (inMutationIdentifier != null)
                engineeredTransgeneForm.setNumberMGI(inMutationIdentifier.getNumberMGI().toString());

            // Gene Function
            Object[] geneList = theEngineeredTransgene.getGeneFunctionCollection().toArray();
            System.out.println("\t collection.size=" + theEngineeredTransgene.getGeneFunctionCollection().size());
            String geneFunction = "";

            for (int i = 0; i < geneList.length; i++) {
                GeneFunction inGeneFunction = (GeneFunction) geneList[i];
                geneFunction += inGeneFunction.getFunction();
                if (i != geneList.length - 1)
                    geneFunction += ", ";
                System.out.println("inGeneFunction.getFunction()" + inGeneFunction.getFunction());
            }
            engineeredTransgeneForm.setGeneFunctions(geneFunction);

            // Conditionality
            Conditionality inConditionality = theEngineeredTransgene.getConditionality();
            engineeredTransgeneForm.setConditionedBy(inConditionality.getConditionedBy());
            engineeredTransgeneForm.setDescription(inConditionality.getDescription());

            // Additional Features / Comments
            engineeredTransgeneForm.setComments(theEngineeredTransgene.getComments());

            // Image
            Image inImage = theEngineeredTransgene.getImage();
            if (inImage != null) {
                engineeredTransgeneForm.setTitle(inImage.getTitle());
                engineeredTransgeneForm.setFileServerLocation(inImage.getFileServerLocation());
                engineeredTransgeneForm.setDescriptionOfConstruct(inImage.getDescription());
            }

        }
        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitEngineeredTransgene");
    }

    public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<EngineeredTransgenePopulateAction dropdown> Entering dropdown()");

        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitEngineeredTransgene");
    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("<EngineeredTransgenePopulateAction dropdown> Entering void dropdown()");

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.HOSTSPECIESDROP,
                Constants.Dropdowns.ADD_BLANK_AND_OTHER_OPTION);

        System.out.println("<EngineeredTransgenePopulateAction dropdown> Exiting void dropdown()");
    }
}
