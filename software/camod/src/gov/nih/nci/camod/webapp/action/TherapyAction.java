/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: TherapyAction.java,v 1.20 2008-08-14 16:59:48 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.19  2007/10/31 17:13:34  pandyas
 * Modified comments for #8355 	Add comments field to every submission page
 *
 * Revision 1.18  2007/09/12 19:36:40  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.17  2007/05/16 12:29:24  pandyas
 * Added developmental stage evs tree to Therapy when species is Zebrafsih
 *
 * Revision 1.16  2006/10/27 16:33:49  pandyas
 * fixed printout on error - typo
 *
 * Revision 1.15  2006/05/04 14:28:14  pandyas
 * Fixed comment
 *
 * Revision 1.14  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.13  2005/12/29 18:28:54  pandyas
 * Clean up - remove Biomarker code
 *
 * Revision 1.12  2005/11/09 00:17:25  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.11  2005/11/02 21:48:09  georgeda
 * Fixed validate
 *
 * Revision 1.10  2005/11/02 19:02:08  pandyas
 * Added e-mail functionality
 *
 * Revision 1.9  2005/10/28 14:50:55  georgeda
 * Fixed null pointer problem
 *
 * Revision 1.8  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.7  2005/10/25 19:42:15  georgeda
 * Finished Therapy page
 *
 * Revision 1.6  2005/10/20 20:40:03  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.webapp.form.TherapyForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

/**
 * TherapyAction Class
 */
public final class TherapyAction extends BaseAction {

	/**
	 * Edit
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("<TherapyAction> Entering edit");
		if (!isTokenValid(request)) {
			return mapping.findForward("failure");
		}
		
        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		// Create a form to edit
		TherapyForm therapyForm = (TherapyForm) form;

		// Grab the current aTherapyID from the session
		String aTherapyID = request.getParameter("aTherapyID");

        log.info("<TherapyAction save> following Characteristics:" + "\n\t name: " + therapyForm.getName()
				+ "\n\t nscNumber: " + therapyForm.getNscNumber() + "\n\t casNumber: " + therapyForm.getCasNumber()
				+ "\n\t toxicityGrade: " + therapyForm.getToxicityGrade() + "\n\t chemicalClasses: "
				+ therapyForm.getSelectedChemicalClasses() + "\n\t processes: " + therapyForm.getSelectedProcesses()
				+ "\n\t targets: " + therapyForm.getSelectedTargets() + "\n\t dosage: "
                 + therapyForm.getDosage() + "\n\t dosageUnit: " + therapyForm.getDosageUnit()
                + therapyForm.getAdministrativeRoute() + "\n\t type: " + therapyForm.getType() + "\n\t age: "
				+ therapyForm.getAgeAtTreatment() + "\n\t administrativeRoute: " + therapyForm.getAdministrativeRoute()
				+ "\n\t biomarker: " + therapyForm.getBiomarker() 
				+ "\n\t experiment: " + therapyForm.getExperiment() + "\n\t results: "
                + "\n\t TumorResponse: " + therapyForm.getTumorResponse() 
        		+ "\n\t DevelopmentalStage: " + therapyForm.getDevelopmentalStage() 
        		+ "\n\t DevelopmentalStageCode: " + therapyForm.getDevelopmentalStageCode() 
        		+ "\n\t DevelopmentalStageName: " + therapyForm.getDevelopmentalStageName()                 
                + "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

		TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");

		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {
	        // retrieve animal model by it's id	        
	        AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
	        AnimalModel theAnimalModel = theAnimalModelManager.get(modelID); 			

            if ("Delete".equals(theAction)) {
				therapyManager.remove(aTherapyID, theAnimalModel);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("therapy.delete.successful"));
				saveErrors(request, msg);

			} else {
				Therapy theTherapy = therapyManager.get(aTherapyID);
				therapyManager.update(theAnimalModel, therapyForm, theTherapy);

				// Add a message to be displayed in submitOverview.jsp saying
				// you've
				// created a new model successfully
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("therapy.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {

			log.error("Unable to get add a therapy action: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		resetToken(request);

		return mapping.findForward("AnimalModelTreePopulateAction");

	}

	/**
	 * Save
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("<TherapyAction> Entering save");
		if (!isTokenValid(request)) {
			return mapping.findForward("failure");
		}

		// Grab the current modelID from the session
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		// Create a form to edit
		TherapyForm therapyForm = (TherapyForm) form;

        log.info("<TherapyAction save> following Characteristics:" 
				+ "\n\t name: " + therapyForm.getName()
				+ "\n\t NscNumber: " + therapyForm.getNscNumber() + "\n\t casNumber: " + therapyForm.getCasNumber()
				+ "\n\t toxicityGrade: " + therapyForm.getToxicityGrade() + "\n\t chemicalClasses: "
				+ therapyForm.getChemicalClasses() + "\n\t processName: " + therapyForm.getProcesses()
				+ "\n\t targetName: " + therapyForm.getType() + "\n\t dosage: " 
                + therapyForm.getDosage() + "\n\t dosageUnit: " + therapyForm.getDosageUnit()
                + therapyForm.getAdministrativeRoute()
				+ "\n\t type: " + therapyForm.getType() + "\n\t age: " + therapyForm.getAgeAtTreatment()
				+ "\n\t administrativeRoute: " + therapyForm.getAdministrativeRoute() + "\n\t biomarker: "
				+ therapyForm.getBiomarker() 
				+ "\n\t experiment: " + therapyForm.getExperiment() + "\n\t results: " + therapyForm.getResults()
				+ "\n\t comments: " + therapyForm.getComments() 
                + "\n\t TumorResponse: " + therapyForm.getTumorResponse()
        		+ "\n\t DevelopmentalStage: " + therapyForm.getDevelopmentalStage() 
        		+ "\n\t DevelopmentalStageCode: " + therapyForm.getDevelopmentalStageCode() 
        		+ "\n\t DevelopmentalStageName: " + therapyForm.getDevelopmentalStageName()                 
                + "\n\t user: "	+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
            log.debug("<TherapyAction> Entering try block");            
			animalModelManager.addTherapy(animalModel, therapyForm);

			// Add a message to be displayed in submitOverview.jsp saying you've
			// created a new model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("therapy.creation.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {

			log.error("Unable to get add a therapy action: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		resetToken(request);

		return mapping.findForward("AnimalModelTreePopulateAction");
	}
}