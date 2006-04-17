/**
 * 
 * $Id: EngineeredTransgenePopulateAction.java,v 1.17 2006-04-17 19:09:40 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.impl.EngineeredTransgeneManagerSingleton;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

public class EngineeredTransgenePopulateAction extends BaseAction
{

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
            

            if (theEngineeredTransgene.getIsRandom().booleanValue()) {
                engineeredTransgeneForm.setIsRandom("yes");
                engineeredTransgeneForm.setLocationOfIntegration(theEngineeredTransgene.getLocationOfIntegration());                
            } else {
                engineeredTransgeneForm.setIsRandom("no");
            }            

            engineeredTransgeneForm.setName(theEngineeredTransgene.getName());

            // Transgene (coding sequence only)
            Species species = theEngineeredTransgene.getSpecies();
            //DropdownOption theOption = null;
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SPECIESQUERYDROP, "");            
            List dropdownTaxList = (List) request.getSession().getAttribute(Constants.Dropdowns.SPECIESQUERYDROP);
            
            log.info("<populate method> Species is: " + theEngineeredTransgene.getSpecies());
            engineeredTransgeneForm.setScientificName(theEngineeredTransgene.getSpecies().getScientificName());
            engineeredTransgeneForm.setOtherScientificName(theEngineeredTransgene.getSpecies().getScientificNameUnctrlVocab());

            // Transcriptional (Promoter) 1
            List regElementList = new ArrayList(theEngineeredTransgene.getRegulatoryElementCollection());
            RegulatoryElement regElement = null;
            Species tax_reg1 = null;

            for (int i = 0; i < regElementList.size(); i++) {
                regElement = (RegulatoryElement) regElementList.get(i);

                if (regElement.getRegulatoryElementType().getName().equals("Transcriptional 1")) {
                    engineeredTransgeneForm.setTranscriptional1_name(regElement.getName());
                    tax_reg1 = regElement.getSpecies();

                    if (tax_reg1 != null) {
                        //theOption = new DropdownOption("", tax_reg1.getScientificName());
                        //if (dropdownTaxList.contains(theOption)) {                            
                        if (tax_reg1.getScientificNameUnctrlVocab() != null) {
                            engineeredTransgeneForm.setTranscriptional1_species("Other");
                            engineeredTransgeneForm.setTranscriptional1_otherSpecies(tax_reg1.getScientificName());                            
                        } else {
                         engineeredTransgeneForm.setTranscriptional1_species(tax_reg1.getScientificName());                         
                        }
                    }

                }

                if (regElement.getRegulatoryElementType().getName().equals("Transcriptional 2")) {
                    engineeredTransgeneForm.setTranscriptional2_name(regElement.getName());
                    tax_reg1 = regElement.getSpecies();

                    if (tax_reg1 != null) {
                        if (tax_reg1.getScientificNameUnctrlVocab() != null) {
                            engineeredTransgeneForm.setTranscriptional2_species("Other");
                            engineeredTransgeneForm.setTranscriptional2_otherSpecies(tax_reg1.getScientificName());
                        } else {
                            engineeredTransgeneForm.setTranscriptional2_species(tax_reg1.getScientificName());                            
                        }
                    }
                }

                if (regElement.getRegulatoryElementType().getName().equals("Transcriptional 3")) {
                    engineeredTransgeneForm.setTranscriptional3_name(regElement.getName());
                    tax_reg1 = regElement.getSpecies();

                    if (tax_reg1 != null) {
                        if (tax_reg1.getScientificNameUnctrlVocab() != null) {
                            engineeredTransgeneForm.setTranscriptional3_species("Other");
                            engineeredTransgeneForm.setTranscriptional3_otherSpecies(tax_reg1.getScientificName());
                        } else {
                            engineeredTransgeneForm.setTranscriptional3_species(tax_reg1.getScientificName());
                        }
                    }
                }

                if (regElement.getRegulatoryElementType().getName().equals("Poly A Signal")) {
                    engineeredTransgeneForm.setPolyASignal_name(regElement.getName());
                    tax_reg1 = regElement.getSpecies();

                    if (tax_reg1 != null) {
                        if (tax_reg1.getScientificNameUnctrlVocab() != null) {
                            engineeredTransgeneForm.setPolyASignal_species("Other");
                            engineeredTransgeneForm.setPolyASignal_otherSpecies(tax_reg1.getScientificName());
                        } else {
                            engineeredTransgeneForm.setPolyASignal_species(tax_reg1.getScientificName());
                        }
                    }
                }

                if (regElement.getRegulatoryElementType().getName().equals("Splice Site")) {
                    engineeredTransgeneForm.setSpliceSites_name(regElement.getName());
                    tax_reg1 = regElement.getSpecies();

                    if (tax_reg1 != null) {
                        if (tax_reg1.getScientificNameUnctrlVocab() != null) {
                            engineeredTransgeneForm.setSpliceSites_species("Other");
                            engineeredTransgeneForm.setSpliceSites_otherSpecies(tax_reg1.getScientificName());
                        } else {
                            engineeredTransgeneForm.setSpliceSites_species(tax_reg1.getScientificName());
                        }
                    }
                }

            // MGI Number
            MutationIdentifier inMutationIdentifier = theEngineeredTransgene.getMutationIdentifier();
            if (inMutationIdentifier != null)
                engineeredTransgeneForm.setMgiNumber(inMutationIdentifier.getMgiNumber());

            // Gene Function
            Object[] geneList = theEngineeredTransgene.getGeneFunctionCollection().toArray();
            //System.out.println("\t collection.size=" + theEngineeredTransgene.getGeneFunctionCollection().size());
            String geneFunction = "";

            for (int j = 0; j < geneList.length; j++) {
                GeneFunction inGeneFunction = (GeneFunction) geneList[j];
                geneFunction += inGeneFunction.getFunction();
                if (j != geneList.length - 1)
                    geneFunction += ", ";
                //System.out.println("inGeneFunction.getFunction()" + inGeneFunction.getFunction());
            }
            engineeredTransgeneForm.setGeneFunctions(geneFunction);

            // Conditionality
            Conditionality theConditionality = theEngineeredTransgene.getConditionality();
            if (theConditionality != null) {
                if ("1".equals(theConditionality.getConditionedBy())) {
                    //System.out.println("Setting conditionality in ETG");
                	
                    engineeredTransgeneForm.setConditionedBy(Constants.CONDITIONAL);
                    engineeredTransgeneForm.setDescription(theConditionality.getDescription());
                    //System.out.println("Setting condition description to: " + theConditionality.getDescription());
                } else {
                    engineeredTransgeneForm.setConditionedBy(Constants.NOT_CONDITIONAL);
                }
           }

            // Additional Features / Comments
            engineeredTransgeneForm.setComments(theEngineeredTransgene.getComments());

            // Image
            Image inImage = theEngineeredTransgene.getImage();
            if (inImage != null) {
                engineeredTransgeneForm.setTitle(inImage.getTitle());
                engineeredTransgeneForm.setFileServerLocation(inImage.getFileServerLocation());
                engineeredTransgeneForm.setDescriptionOfConstruct(inImage.getDescription());
                engineeredTransgeneForm.setImageUrl(inImage.getImageUrl());
                engineeredTransgeneForm.setThumbUrl(inImage.getThumbUrl());
            }

        }
        }
        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitEngineeredTransgene");
    }    public ActionForward dropdown(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {

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
    public void dropdown(HttpServletRequest request,
                         HttpServletResponse response) throws Exception
    {

        System.out.println("<EngineeredTransgenePopulateAction dropdown> Entering void dropdown()");

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SPECIESQUERYDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER_OPTION);

        System.out.println("<EngineeredTransgenePopulateAction dropdown> Exiting void dropdown()");
    }
}
