/**
 * 
 * $Id: TransplantPopulateAction.java,v 1.1 2008-01-16 18:29:57 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2007/10/31 17:48:23  pandyas
 * Fixed #8290 	Rename graft object into transplant object
 *
 * Revision 1.5  2007/09/12 19:36:40  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.4  2007/08/14 17:07:02  pandyas
 * Bug #8414:  getEVSPreferredDiscription needs to be implemented for Zebrafish vocabulary source
 *
 * Bug #8402:  Selecting "other" species on graft page gets only blank page on dev server
 *
 * Revision 1.3  2007/08/07 19:36:13  pandyas
 * Removed reference to Transplant as per VCDE comments and after modification to object definition for CDE
 *
 * Revision 1.2  2007/08/07 18:27:50  pandyas
 * Renamed to GRAFT as per VCDE comments
 *
 * Revision 1.1  2007/07/31 12:02:55  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.37  2007/06/25 17:48:50  pandyas
 * Fixed save and edit for text
 *
 * Revision 1.36  2007/06/21 20:49:14  pandyas
 * Minor modification in print out of log statement
 *
 * Revision 1.35  2007/06/18 16:09:31  pandyas
 * Fixed re-populate
 * When entering mutilple xenografts the constant used to choose the tree being displayed needs to be set in the populate method for each species entry
 *
 * Revision 1.34  2007/05/18 15:33:49  pandyas
 * Modified species and organ tree to default to no organ button and to work when user goes from species to empty selection
 *
 * Revision 1.33  2007/05/17 19:11:33  pandyas
 * Modified method name and code to cover strain and organ tree populate functions based on the species selected by the user
 *
 * Revision 1.32  2007/05/10 02:20:48  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 *
 * Revision 1.31  2007/04/04 13:19:27  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.30  2007/03/26 12:02:31  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.29  2006/09/12 15:34:35  georgeda
 * Removed work around now that fix is working
 *
 * Revision 1.28  2006/09/11 19:21:23  georgeda
 * work around for getPreferredDescription bug
 *
 * Revision 1.27  2006/09/11 16:55:23  georgeda
 * work around for getPreferredDescription bug
 *
 * Revision 1.26  2006/05/23 18:16:20  georgeda
 * Added other into species dropdown
 *
 * Revision 1.25  2006/05/19 16:40:44  pandyas
 * Defect #249 - add other to species on the Xenograft screen
 *
 * Revision 1.24  2006/04/27 15:06:29  pandyas
 * cleaned up as a result of testing
 *
 * Revision 1.23  2006/04/20 19:46:14  pandyas
 * Modified host species/  host strain / otherHostStrain text on Xenograft screen
 *
 * Revision 1.22  2006/04/20 18:11:16  pandyas
 * Cleaned up Species or Strain save of Other in DB
 *
 * Revision 1.21  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.20  2005/12/12 17:33:37  georgeda
 * Defect #265, store host/origin species in correct places
 *
 * Revision 1.19  2005/11/28 22:52:05  pandyas
 * Defect #186: Added organ/tissue to Xenograft page, modified search page to display multiple Xenografts with headers, modified XenograftManagerImpl so it does not create or save an organ object if not organ is selected
 *
 * Revision 1.18  2005/11/28 13:51:20  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.17  2005/11/18 22:50:02  georgeda
 * Defect #184.  Cleanup editing of old models
 *
 * Revision 1.16  2005/11/14 14:22:37  georgeda
 * Cleanup
 *
 * Revision 1.15  2005/11/11 16:28:26  pandyas
 * expanded species list
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Species;
import gov.nih.nci.camod.domain.Transplant;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.impl.TransplantManagerSingleton;
import gov.nih.nci.camod.service.impl.SpeciesManagerSingleton;
import gov.nih.nci.camod.webapp.form.TransplantForm;
import gov.nih.nci.camod.webapp.util.DropdownOption;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TransplantPopulateAction extends BaseAction
{

    /**
     * Pre-populate all field values in the form <FormName> Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {

        // Create a form to edit
    	TransplantForm transplantForm = (TransplantForm) form;

        // Grab the current Transplantion we are working with related to this AM
        String aTransplantID = request.getParameter("aTransplantID");

        Transplant transplant = TransplantManagerSingleton.instance().get(aTransplantID);

        if (transplant == null)
        {
            request.setAttribute(Constants.Parameters.DELETED, "true");
        }
        else
        {
            request.setAttribute("aTransplantID", aTransplantID);
            transplantForm.setName(transplant.getName());

            // Set the other flag or the selected administrative site from the DB
            if (transplant.getAdminSiteAlternEntry() != null)
            {
            	transplantForm.setAdministrativeSite(Constants.Dropdowns.OTHER_OPTION);
            	transplantForm.setOtherAdministrativeSite(transplant.getAdminSiteAlternEntry());
            }
            else
            {
            	transplantForm.setAdministrativeSite(transplant.getAdministrativeSite());
            }
            
            // Set the other flag or the selected ConditioningRegimen from the DB
            if (transplant.getCondRegimenAlternEntry() != null)
            {
            	transplantForm.setConditioningRegimen(Constants.Dropdowns.OTHER_OPTION);
            	transplantForm.setOtherConditioningRegimen(transplant.getCondRegimenAlternEntry());
            }
            else
            {
            	transplantForm.setConditioningRegimen(transplant.getConditioningRegimen());
            }            

            transplantForm.setGeneticManipulation(transplant.getGeneticManipulation());
            transplantForm.setModificationDescription(transplant.getModificationDescription());
            transplantForm.setParentalCellLineName(transplant.getParentalCellLineName());
            transplantForm.setAtccNumber(transplant.getAtccNumber());
            transplantForm.setCellAmount(transplant.getCellAmount());
            transplantForm.setGrowthPeriod(transplant.getGrowthPeriod());
            
            if(transplant.getComments() !=null && transplant.getComments().length()>0) {
            transplantForm.setComments(transplant.getComments());
            }
            // When populating multiple Transplant, this constant needs to be reset for each species entry
            request.getSession().setAttribute(Constants.DONORSPECIESCOMMONNAME, transplant.getStrain().getSpecies().getCommonName());
            log.debug("transplant.getStrain().getSpecies().getCommonName(): " + transplant.getStrain().getSpecies().getCommonName());            
            
            // Species was required in previous versions of caMod and is stored in donorSpecies column
            // The species and strain are required for 2.1 and strain_id is stored for all future versions
            // Therefore, we must search in both to populate correctly
            if (transplant.getDonorSpecies() != null)
            {
                // Populate species if uncontrolled vocab - old models
                if (transplant.getDonorSpecies().getScientificNameAlternEntry() != null)
                {
                	transplantForm.setDonorScientificName(Constants.Dropdowns.OTHER_OPTION);
                	transplantForm.setOtherDonorScientificName(transplant.getDonorSpecies().getScientificNameAlternEntry());
                }
                else
                {
                	transplantForm.setDonorScientificName(transplant.getDonorSpecies().getScientificName());
                }
            }
            else if (transplant.getStrain() != null)
            {
                //Populate strain for all new models - check for uncontrolled vocab
                if (transplant.getStrain().getNameAlternEntry() != null)
                {
                	transplantForm.setDonorEthinicityStrain(Constants.Dropdowns.OTHER_OPTION);
                	transplantForm.setOtherDonorEthinicityStrain(transplant.getStrain().getNameAlternEntry());

                }
                else
                {
                	transplantForm.setDonorEthinicityStrain(transplant.getStrain().getName());
                }
                // Populate species if strain_id is used -  models submitted after 2.0 
                // check for uncontrolled vocab
                if (transplant.getStrain().getSpecies().getScientificNameAlternEntry() != null)
                {
                	transplantForm.setDonorScientificName(Constants.Dropdowns.OTHER_OPTION);
                	transplantForm.setOtherDonorScientificName(transplant.getStrain().getSpecies().getScientificNameAlternEntry());
                }
                else
                {
                	transplantForm.setDonorScientificName(transplant.getStrain().getSpecies().getScientificName());
                }
            }

            // since we are always querying from concept code (save and edit),
            // simply display EVSPreferredDescription
            if (transplant.getOrgan() != null)
            {            
                // since we are always querying from concept code (save and edit),
                // simply display EVSPreferredDescription, unless concept code is '00000'
                if (transplant.getOrgan().getConceptCode().equals(Constants.Dropdowns.CONCEPTCODEZEROS)) {
                	transplantForm.setOrgan(transplant.getOrgan().getEVSPreferredDescription());
                    log.debug("<TransplantPopulateAction> setOrgan= " + transplant.getOrgan().getName());
                    transplantForm.setOrganTissueCode(transplant.getOrgan().getConceptCode());
                    log.debug("<TransplantPopulateAction> OrganTissueCode= " + transplant.getOrgan().getConceptCode());           
                    
                } else {
                	transplantForm.setOrgan(transplant.getOrgan().getEVSPreferredDescription());
                    log.debug("<TransplantPopulateAction> setOrgan= " + transplant.getOrgan().getEVSPreferredDescription());
                    transplantForm.setOrganTissueCode(transplant.getOrgan().getConceptCode());
                    log.debug("<TransplantPopulateAction> OrganTissueCode= " + transplant.getOrgan().getConceptCode());
                }
            }


            // Set the other flag or the normal source type
            if (transplant.getSourceTypeAlternEntry() != null)
            {
            	transplantForm.setSourceType(Constants.Dropdowns.OTHER_OPTION);
            	transplantForm.setOtherSourceType(transplant.getSourceTypeAlternEntry());
            }
            else
            {
            	transplantForm.setSourceType(transplant.getSourceType());
            }

        }

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        this.dropdown(request, response, transplantForm);

        return mapping.findForward("submitTransplant");

    }

    /**
     * Populate the dropdown menus for submitEnvironmentalFactors
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward dropdown(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {

        log.debug("<TransplantPopulateAction dropdown> Entering dropdown() ");

        TransplantForm theForm = (TransplantForm) form;

        // setup dropdown menus
        this.dropdown(request, response, theForm);

        log.debug("<TransplantPopulateAction dropdown> before return submitTransplant ");

        return mapping.findForward("submitTransplant");

    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    private void dropdown(HttpServletRequest request,
                          HttpServletResponse response,
                          TransplantForm inForm) throws Exception
    {

        log.debug("<TransplantPopulateAction dropdown> Entering void dropdown()");

        // Set the Species drop drop for the Transplant form
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SPECIESQUERYDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER_OPTION);

        // theSpecies will be null the first time (submitTransplant screen) - default to full list of strains
        // but this is needed to populate the strain on the submitTransplant screen
        String theSpecies = inForm.getDonorScientificName();
        if (theSpecies == null)
        {
            List speciesList = (List) request.getSession().getAttribute(Constants.Dropdowns.SPECIESQUERYDROP);
            DropdownOption theOption = (DropdownOption) speciesList.get(0);
            theSpecies = theOption.getValue();
        }

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, theSpecies);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SOURCETYPEDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.TRANSPLANTADMINSITESDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CONDITIONINGREGIMEN, Constants.Dropdowns.ADD_BLANK_AND_OTHER);


        // Retrieve the Species and Strain set for the AnimalModel (via submitNewModel.jsp)
        // This is displayed on the JSP page as the HOST SPECIES / STRAIN
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel animalModel = animalModelManager.get(modelID);

        if (animalModel.getStrain() != null)
        {
            Species theAMSpecies = animalModel.getStrain().getSpecies();
            request.getSession().setAttribute(Constants.Dropdowns.MODELSPECIES, theAMSpecies.getScientificName());

            // If the animal model has an 'Other' strain selected, display the otherName on the Graft screen
            if (animalModel.getStrain().getNameAlternEntry() != null)
            {
                request.getSession().setAttribute(Constants.Dropdowns.OTHERMODELSTRAIN, animalModel.getStrain().getNameAlternEntry());
            }
            else
            {
                request.getSession().setAttribute(Constants.Dropdowns.MODELSTRAIN, animalModel.getStrain().getName());
            }
        }


        log.debug("<TransplantPopulateAction dropdown> Exiting void dropdown()");
    }

    /**
     * Repopulate the strain dropdown and organ tree with it's matching species
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward setStrainOrganValues(ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception
    {

    	TransplantForm transplantForm = (TransplantForm) form;
        String theDonorSpecies =null;
        
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, transplantForm.getDonorScientificName());
        
        log.debug("transplantForm.getDonorScientificName(): " + transplantForm.getDonorScientificName());
        
        // Check if null - if user goes from species to empty this correctly redirects to screen  
        if (!transplantForm.getDonorScientificName().equals(Constants.Dropdowns.OTHER_OPTION)) {
            if(transplantForm.getDonorScientificName() != null && transplantForm.getDonorScientificName().length() > 0){
    	        // Set Donor species to a constant to determine which organ tree displays 
    	        // using common name because Rat has two species
    
    	        Species species = SpeciesManagerSingleton.instance().getByName(transplantForm.getDonorScientificName());
    	        theDonorSpecies = species.getCommonName();
    	        log.debug("<setStrainDropdown> theDonorSpecies: "+ theDonorSpecies);
            }  
        }
        request.getSession().setAttribute(Constants.DONORSPECIESCOMMONNAME, theDonorSpecies);
        
        // Must Reset both fields when new species is chosen or edited
        transplantForm.setOrganTissueCode(null);
        transplantForm.setOrgan(null);
        
        // Must Reset both fields when new species is chosen or edited
        transplantForm.setDonorEthinicityStrain("");
        transplantForm.setOtherDonorEthinicityStrain("");

        return mapping.findForward("submitTransplant");
    }

}