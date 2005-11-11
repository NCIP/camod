/**
 * 
 * $Id: XenograftPopulateAction.java,v 1.15 2005-11-11 16:28:26 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.impl.XenograftManagerSingleton;
import gov.nih.nci.camod.webapp.form.XenograftForm;
import gov.nih.nci.camod.webapp.util.DropdownOption;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class XenograftPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form <FormName> Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Create a form to edit
		XenograftForm xenograftForm = (XenograftForm) form;

		// Grab the current Xenograft we are working with related to this
		// animalModel
		String aXenograftID = request.getParameter("aXenograftID");

		Xenograft xeno = XenograftManagerSingleton.instance().get(aXenograftID);

		if (xeno == null) {
			request.setAttribute(Constants.Parameters.DELETED, "true");
		} else {
			request.setAttribute("aXenograftID", aXenograftID);
			xenograftForm.setName(xeno.getName());
			
            // Set the other administrative site and/or the selected admin site
			// We need to comapre the stored value with the config file because we are 
			// storing 'Other' AdministrativeSite directly into the database
			NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.XENOGRAFTADMINSITESDROP, "");
			List xenoAdminSiteDropList = (List)request.getSession().getAttribute(Constants.Dropdowns.XENOGRAFTADMINSITESDROP);
			//If AdministrativeSite is from list set the value
			if (xenoAdminSiteDropList.contains(xeno.getAdministrativeSite())) {
                	System.out.println("\t otherAdministrativeSite Matched the list");
                	xenograftForm.setAdministrativeSite(xeno.getAdministrativeSite());
				} else {
				//If AdministrativeSite is not from list set the "other" and enable the text field					
	                System.out.println("\t otherAdministrativeSite Didn't Match the list");					
					xenograftForm.setAdministrativeSite(Constants.Dropdowns.OTHER_OPTION);
					xenograftForm.setOtherAdministrativeSite(xeno.getAdministrativeSite());					
			}			
			
			xenograftForm.setGeneticManipulation(xeno.getGeneticManipulation());
			xenograftForm.setModificationDescription(xeno.getModificationDescription());
			xenograftForm.setParentalCellLineName(xeno.getParentalCellLineName());
			xenograftForm.setATCCNumber(xeno.getAtccNumber());
			xenograftForm.setCellAmount(xeno.getCellAmount());

			Taxon tax = xeno.getHostSpecies();
			xenograftForm.setHostScientificName(tax.getScientificName());

			if (tax.getEthnicityStrainUnctrlVocab() != null) {
				xenograftForm.setHostEthinicityStrain(Constants.Dropdowns.OTHER_OPTION);
				xenograftForm.setOtherHostEthinicityStrain(tax.getEthnicityStrainUnctrlVocab());
			} else {
				xenograftForm.setHostEthinicityStrain(tax.getEthnicityStrain());
			}

			String outputFormatString = "MM/dd/yyyy";

			if (xeno.getHarvestDate() != null) {
				if (!xeno.getHarvestDate().equals("")) {
					// Format the date object into the new format
					DateFormat sdf = new SimpleDateFormat(outputFormatString);
					String formattedDate = sdf.format(xeno.getHarvestDate());
					xenograftForm.setHarvestDate(formattedDate);
				}
			}

			// Set the other flag or the normal graft type
			if (xeno.getGraftTypeUnctrlVocab() != null) {
				xenograftForm.setGraftType(Constants.Dropdowns.OTHER_OPTION);
				xenograftForm.setOtherGraftType(xeno.getGraftTypeUnctrlVocab());
			} else {
				xenograftForm.setGraftType(xeno.getGraftType());
			}

		}

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response, xenograftForm);

		return mapping.findForward("submitTransplantXenograft");

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
	public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<XenograftPopulateAction dropdown> Entering dropdown() ");

		// setup dropdown menus
		this.dropdown(request, response, (XenograftForm) form);

		System.out.println("<XenograftPopulateAction dropdown> before return submitTransplantXenograft ");

		return mapping.findForward("submitTransplantXenograft");

	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void dropdown(HttpServletRequest request, HttpServletResponse response, XenograftForm form)
			throws Exception {

		System.out.println("<XenograftPopulateAction dropdown> Entering void dropdown()");

		// Retrieve the AnimalModel current Species and Strain set in
		// ModelCharacteristics
		// This is displayed on the JSP page right above the HOST STRAIN /
		// SPECIES
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel animalModel = animalModelManager.get(modelID);

		Taxon theTaxon = animalModel.getSpecies();
		request.getSession().setAttribute(Constants.Dropdowns.MODELSPECIES, theTaxon.getScientificName());
		request.getSession().setAttribute(Constants.Dropdowns.MODELSTRAIN, theTaxon.getEthnicityStrain());
		
		// Prepopulate all dropdown fields, set the global Constants to the
		// following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.HOSTSPECIESDROP,
                Constants.Dropdowns.ADD_BLANK_OPTION);

		String theSpecies = form.getHostScientificName();
		if (theSpecies == null) {
			List speciesList = (List) request.getSession().getAttribute(Constants.Dropdowns.HOSTSPECIESDROP);
			DropdownOption theOption = (DropdownOption) speciesList.get(0);
			theSpecies = theOption.getValue();
		}

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, theSpecies);

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.GRAFTTYPEDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.XENOGRAFTADMINSITESDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);		

		System.out.println("<XenograftPopulateAction dropdown> Exiting void dropdown()");
	}

	/**
	 * Repopulate the Strain dropdown with it's matching species
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setStrainDropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XenograftForm xenograftForm = (XenograftForm) form;
		
		request.setAttribute("aXenograftID", request.getParameter("aXenograftID"));
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STRAINDROP, xenograftForm.getHostScientificName());

		return mapping.findForward("submitTransplantXenograft");
	}

}
