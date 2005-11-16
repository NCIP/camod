/**
 * 
 * $Id: TherapyPopulateAction.java,v 1.12 2005-11-16 19:29:13 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2005/11/03 13:59:10  georgeda
 * Fixed delete functionality
 *
 * Revision 1.10  2005/10/31 13:46:28  georgeda
 * Updates to handle back arrow
 *
 * Revision 1.9  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.8  2005/10/27 19:42:05  georgeda
 * Cleanup
 *
 * Revision 1.7  2005/10/26 14:10:49  georgeda
 * Added other administrative route to therapy
 *
 * Revision 1.6  2005/10/25 19:42:15  georgeda
 * Finished Therapy page
 *
 * Revision 1.5  2005/10/20 20:40:21  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AgentTarget;
import gov.nih.nci.camod.domain.BiologicalProcess;
import gov.nih.nci.camod.domain.ChemicalClass;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.webapp.form.TherapyForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TherapyPopulateAction extends BaseAction {

	/**
	 * Pre-populate all field values in the form <FormName> Used by <jspName>
	 * 
	 */
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.trace("Entering TherapyPopulateAction.populate");

		// Create a form to edit
		TherapyForm therapyForm = (TherapyForm) form;

		// Grab the current Therapy we are working with related to this
		// animalModel
		String aTherapyID = request.getParameter("aTherapyID");

		TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");
		Therapy ty = therapyManager.get(aTherapyID);

		if (ty == null) {
			request.setAttribute(Constants.Parameters.DELETED, "true");
		} else {
			request.setAttribute("aTherapyID", aTherapyID);

			if (ty.getTreatment().getSexDistribution() != null) {
				therapyForm.setType(ty.getTreatment().getSexDistribution().getType());
			}
			if (ty.getTreatment().getAgeAtTreatment() != null) {
				therapyForm.setAgeAtTreatment(ty.getTreatment().getAgeAtTreatment());
			}

			therapyForm.setDosage(ty.getTreatment().getDosage());
			therapyForm.setName(ty.getAgent().getName());
			therapyForm.setAdministrativeRoute(ty.getTreatment().getAdministrativeRoute());
			therapyForm.setOtherAdministrativeRoute(ty.getTreatment().getAdminRouteUnctrlVocab());

			if (ty.getAgent().getCasNumber() != null) {
				therapyForm.setCASNumber(ty.getAgent().getCasNumber());
			}

			if (ty.getAgent().getNscNumber() != null) {
				therapyForm.setNSCNumber(ty.getAgent().getNscNumber().toString());
			}

			// Therapy object attributes
			therapyForm.setToxicityGrade(ty.getToxicityGrade());
			therapyForm.setBiomarker(ty.getBiomarker());
			therapyForm.setTumorResponse(ty.getTumorResponse());
			therapyForm.setExperiment(ty.getExperiment());
			therapyForm.setResults(ty.getResults());
			therapyForm.setComments(ty.getComments());

			// Get the collection of agent targets
			List theAgentTargetsList = ty.getAgent().getAgentTargetCollection();
			String[] theTargets = new String[theAgentTargetsList.size()];
			for (int i = 0; i < theAgentTargetsList.size(); i++) {
				AgentTarget theAgentTarget = (AgentTarget) theAgentTargetsList.get(i);
				theTargets[i] = theAgentTarget.getTargetName();
			}
			therapyForm.setSelectedTargets(theTargets);

			// Get the collection of biological processes
			List theProcessesList = ty.getAgent().getBiologicalProcessCollection();
			String[] theProcesses = new String[theProcessesList.size()];
			for (int i = 0; i < theProcessesList.size(); i++) {
				BiologicalProcess theBiologicalProcess = (BiologicalProcess) theProcessesList.get(i);
				theProcesses[i] = theBiologicalProcess.getProcessName();
			}
			therapyForm.setSelectedProcesses(theProcesses);

			// Get the collection of Chemical Classes
			List theChemicalClassesList = ty.getAgent().getChemicalClassCollection();
			String[] theChemicalClasses = new String[theChemicalClassesList.size()];
			for (int i = 0; i < theChemicalClassesList.size(); i++) {
				ChemicalClass theChemicalClass = (ChemicalClass) theChemicalClassesList.get(i);
				theChemicalClasses[i] = theChemicalClass.getChemicalClassName();
			}
			therapyForm.setSelectedChemicalClasses(theChemicalClasses);
		}

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		this.dropdown(request, response);
		log.trace("Exiting TherapyPopulateAction.populate");

		return mapping.findForward("submitTherapy");
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

		System.out.println("<TherapyPopulateAction dropdown> Entering ActionForward dropdown()");

		// setup dropdown menus
		this.dropdown(request, response);

		System.out.println("<TherapyPopulateAction dropdown> Exiting ActionForward dropdown()");

		return mapping.findForward("submitTherapy");
	}

	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("<TherapyPopulateAction dropdown> Entering void dropdown()");

		// Prepopulate all dropdown fields, set the global Constants to the
		// following
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CHEMICALCLASSESDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.BIOLOGICALPROCESSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.THERAPEUTICTARGETSDROP, "");

		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CHEMTHERAPYDOSEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP,
				Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil
				.populateDropdown(request, Constants.Dropdowns.TOXICITYGRADESDROP, Constants.Dropdowns.ADD_BLANK);
		NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP,
				Constants.Dropdowns.ADD_BLANK);

		System.out.println("<TherapyPopulateAction dropdown> Exiting void dropdown()");

	}
}
