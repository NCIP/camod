/**
 * 
 * $Id: NutritionalFactorPopulateAction.java,v 1.8 2005-10-28 12:47:26 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2005/10/27 19:25:06  georgeda
 * Validation changes
 *
 * Revision 1.6  2005/10/20 20:39:17  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.webapp.form.NutritionalFactorForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class NutritionalFactorPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form <FormName> Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<NutritionalFactorPopulateAction populate> Entering populate() ");

		// Create a form to edit
		NutritionalFactorForm nutritForm = (NutritionalFactorForm) form;

		// Grab the current Therapy we are working with related to this
		// animalModel
		String aTherapyID = request.getParameter("aTherapyID");
		request.setAttribute("aTherapyID", aTherapyID);

		// Grab the current modelID from the session
		String modelID = "" + request.getSession().getAttribute(Constants.MODELID);
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel am = animalModelManager.get(modelID);

		// retrieve the list of all therapies from the current animalModel
		List therapyList = am.getTherapyCollection();

		Therapy therapy = new Therapy();

		// find the specific one we need
		for (int i = 0; i < therapyList.size(); i++) {
			therapy = (Therapy) therapyList.get(i);
			if (therapy.getId().toString().equals(aTherapyID))
				break;
		}

		// Set the otherName and/or the selected name attribute
		if (therapy.getAgent().getNameUnctrlVocab() != null) {
			nutritForm.setName(Constants.Dropdowns.OTHER_OPTION);
			nutritForm.setOtherName(therapy.getAgent().getNameUnctrlVocab());
		} else {
			nutritForm.setName(therapy.getAgent().getName());
		}

		if (therapy.getTreatment().getSexDistribution() != null) {
			nutritForm.setType(therapy.getTreatment().getSexDistribution().getType());
		}
		nutritForm.setDosage(therapy.getTreatment().getDosage());
		nutritForm.setRegimen(therapy.getTreatment().getRegimen());
		nutritForm.setAgeAtTreatment(therapy.getTreatment().getAgeAtTreatment());

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);

		// Store the Form in session to be used by the JSP
		request.getSession().setAttribute(Constants.FORMDATA, nutritForm);

		return mapping.findForward("submitNutritionalFactors");
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

		System.out.println("<NutritionalFactorPopulateAction dropdown> Entering dropdown() ");

		// blank out the FORMDATA Constant field
		NutritionalFactorForm nutritForm = (NutritionalFactorForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, nutritForm);

		// setup dropdown menus
		this.dropdown(request, response);

		System.out.println("<NutritionalFactorPopulateAction dropdown> before return submitRadiation ");

		return mapping.findForward("submitNutritionalFactors");

	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("<NutritionalFactorPopulateAction dropdown> Entering void dropdown()");

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.NUTRITIONFACTORDROP,
				Constants.Dropdowns.ADD_BLANK);

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.NUTFACTORUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);

		System.out.println("<NutritionalFactorPopulateAction dropdown> Exiting void dropdown()");

	}
}
