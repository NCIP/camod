/**
 * 
 * $Id: ChemicalDrugPopulateAction.java,v 1.12 2005-10-28 12:47:26 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2005/10/27 19:25:06  georgeda
 * Validation changes
 *
 * Revision 1.10  2005/10/20 20:37:29  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.webapp.form.ChemicalDrugForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChemicalDrugPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form ChemicalDrug Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<ChemicalDrugPopulateAction populate> ... ");

		ChemicalDrugForm chemicalDrugForm = (ChemicalDrugForm) form;

		String aTherapyID = request.getParameter("aTherapyID");
		request.setAttribute("aTherapyID", aTherapyID);

		TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");
		Therapy ty = therapyManager.get(aTherapyID);

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		chemicalDrugForm.setType(ty.getTreatment().getSexDistribution().getType());
		if (ty.getTreatment().getAgeAtTreatment() != null) {
			chemicalDrugForm.setAgeAtTreatment(ty.getTreatment().getAgeAtTreatment());
		}

		// Set the other name and/or selected name from database
		if (ty.getAgent().getNameUnctrlVocab() != null) {
			chemicalDrugForm.setName(Constants.Dropdowns.OTHER_OPTION);
			chemicalDrugForm.setOtherName(ty.getAgent().getNameUnctrlVocab());
		} else {
			chemicalDrugForm.setName(ty.getAgent().getName());
		}

		// Set the other flag or the selected administrative route from database
		if (ty.getTreatment().getAdminRouteUnctrlVocab() != null) {
			chemicalDrugForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
			chemicalDrugForm.setOtherAdministrativeRoute(ty.getTreatment().getAdminRouteUnctrlVocab());
		} else {
			chemicalDrugForm.setAdministrativeRoute(ty.getTreatment().getAdministrativeRoute());
		}

		chemicalDrugForm.setDosage(ty.getTreatment().getDosage());
		chemicalDrugForm.setRegimen(ty.getTreatment().getRegimen());
		chemicalDrugForm.setCASNumber(ty.getAgent().getCasNumber());

		if (ty.getAgent().getNscNumber() != null) {
			chemicalDrugForm.setNSCNumber(ty.getAgent().getNscNumber().toString());
		}

		return mapping.findForward("submitChemicalDrug");
	}

	/**
	 * Populate the dropdown menus for submitSurgeryOther
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

		System.out.println("<ChemicalDrugPopulateAction dropdown> ... ");

		// setup dropdown menus
		this.dropdown(request, response);

		System.out.println("<ChemicalDrugPopulateAction> exiting... ");

		return mapping.findForward("submitChemicalDrug");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("<ChemicalDrugPopulateAction dropdown> Entering... ");

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CHEMICALDRUGDROP, Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CHEMTHERAPYDOSEUNITSDROP, "");
		System.out.println("Before admin");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);

		System.out.println("Finishing dropdown");
	}

}
