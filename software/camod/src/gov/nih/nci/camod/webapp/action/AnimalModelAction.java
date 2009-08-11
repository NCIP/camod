/**
 * 
 * $Id: AnimalModelAction.java,v 1.31 2009-05-28 18:48:41 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.30  2008/06/13 17:35:23  pandyas
 * Modified to prevent Cross-Site Scripting
 * Cleaned parameter name before proceeding
 * Re: Apps Scan run 06/12/2008
 *
 * Revision 1.29  2008/06/11 17:43:23  pandyas
 * Format change only
 *
 * Revision 1.28  2008/06/03 00:27:28  pandyas
 * Modified to prevent SQL injection
 * Cleaned parameter named "method"  before proceeding
 * Re: Apps Scan run 05/29/2008
 *
 * Revision 1.27  2008/05/27 14:37:02  pandyas
 * Modified to prevent SQL injection
 * Cleaned method name before proceeding
 * Re: Apps Scan run 05/23/2008
 *
 * Revision 1.26  2007/10/31 17:08:23  pandyas
 * Modified commetns for #8188 	Rename UnctrlVocab items to text entries
 *
 * Revision 1.25  2007/09/12 19:36:40  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.24  2007/07/23 17:40:42  pandyas
 * Fixed typo in word occurred
 *
 * Revision 1.23  2007/04/04 13:21:00  pandyas
 * Added reset for animal model species common name in edit (also in save) method
 *
 * Revision 1.22  2007/03/26 12:02:31  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.21  2007/02/23 21:20:08  pandyas
 * Fixed Genotype and Nomenclature - split objects and cleaned up database
 *
 * Revision 1.20  2006/10/17 16:11:00  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.19  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.18  2005/11/18 15:20:21  georgeda
 * Defect #120, gracefully handle the deletion of models.
 *
 * Revision 1.17  2005/11/10 18:41:53  pandyas
 * minor change to comments
 *
 * Revision 1.16  2005/10/24 17:11:00  georgeda
 * First pass at duplicate
 *
 * Revision 1.15  2005/10/24 13:28:17  georgeda
 * Cleanup changes
 *
 * Revision 1.14  2005/10/13 20:47:42  georgeda
 * Correctly handle the PI
 *
 * Revision 1.13  2005/09/16 15:53:49  georgeda
 * Changes due to manager re-write
 *
 * 
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.CurationManager;
import gov.nih.nci.camod.service.impl.CurationManagerImpl;
import gov.nih.nci.camod.util.SafeHTMLUtil;
import gov.nih.nci.camod.webapp.form.ModelCharacteristicsForm;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

/**
 * 
 * Handles the edit/delete/creation of the AnimalModels
 * 
 */
public final class AnimalModelAction extends BaseAction {

	/**
	 * Save a new AnimalModel
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.trace("Entering save");

		ModelCharacteristicsForm theModelChar = (ModelCharacteristicsForm) form;

		log
				.info("<AnimalModelAction saveNewModel> New Model Being created with following Characteristics:"
						+ "\n\t description: "						+ theModelChar.getDescription()
						+ "\n\t breedingNotes: "						+ theModelChar.getBreedingNotes()
						+ "\n\t PI: "						+ theModelChar.getPrincipalInvestigator()
						+ "\n\t experimentDesign: "						+ theModelChar.getExperimentDesign()
						+ "\n\t isToolStrain: "						+ theModelChar.getIsToolStrain()
						+ "\n\t modelDescriptor: "						+ theModelChar.getModelDescriptor()
						+ "\n\t name: "						+ theModelChar.getName()
						+ "\n\t releaseDate: "						+ theModelChar.getReleaseDate()
						+ "\n\t scientificName: "						+ theModelChar.getScientificName()
						+ "\n\t ethinicityStrain: "						+ theModelChar.getEthinicityStrain()
						+ "\n\t otherEthnicityStrain: "						+ theModelChar.getOtherEthnicityStrain()
						+ "\n\t summary: "						+ theModelChar.getSummary()
						+ "\n\t genotype: "						+ theModelChar.getGenotype()
						+ "\n\t nomenclature: "						+ theModelChar.getNomenclature()						
						+ "\n\t type: "						+ theModelChar.getType()
						+ "\n\t url: "						+ theModelChar.getUrl()
						+ "\n\t calendarReleaseDate: "						+ theModelChar.getCalendarReleaseDate()
						+ "\n\t Genotype: "						+ theModelChar.getGenotype()
						+ "\n\t Nomenclature: "						+ theModelChar.getNomenclature()
						+ "\n\t Comment: "						+ theModelChar.getComments()						
						+ "\n\t currentUser: "						+ (String) request.getSession().getAttribute(
								"camod.loggedon.username"));

		String theForward = "AnimalModelTreePopulateAction";

		try {
			
	        // Get and clean method to prevent Cross-Site Scripting
	        String methodName = request.getParameter("method");
	        log.debug("methodName: " + methodName);
	        if (!methodName.equals("save")){
		        methodName = SafeHTMLUtil.clean(methodName);
		        log.debug("methodName: " + methodName);
	        }

			// Get the user
			String theUsername = (String) request.getSession().getAttribute(
					Constants.CURRENTUSER);

			// Get the manager and create the animal model
			AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
			AnimalModel theAnimalModel = theAnimalModelManager.create(
					theModelChar, theUsername);
			
			// Set the animal model to the default state
			if (theAnimalModel.getState() == null) {

				// Get the curation manager workflow XML
				CurationManager theCurationManager = new CurationManagerImpl(
						getServlet().getServletContext().getRealPath("/")
								+ Constants.Admin.MODEL_CURATION_WORKFLOW);

				theAnimalModel.setState(theCurationManager.getDefaultState());
			}

			// save the model
			theAnimalModelManager.save(theAnimalModel);

			log.debug("New model created with id: " + theAnimalModel.getId());

			// Setup global constants to use for submission / editing process
			request.getSession().setAttribute(Constants.MODELID,
					theAnimalModel.getId().toString());
			request.getSession().setAttribute(Constants.MODELDESCRIPTOR,
					theAnimalModel.getModelDescriptor());
			request.getSession().setAttribute(Constants.MODELSTATUS,
					theAnimalModel.getState());
			// Set the species common name to use on the Genetic Description submission screens (mgi, zfin, rgd id's)
			request.getSession().setAttribute(Constants.AMMODELSPECIESCOMMONNAME, 
					theAnimalModel.getStrain().getSpecies().getCommonName());

			// Add a message to be displayed in submitOverview.jsp saying you've
			// created a new model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"creation.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {

			log.error("Exception occurred creating model", e);

			// Encountered an error saving the model.
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.admin.message"));
			saveErrors(request, msg);

			theForward = "failure";
		}

		log.trace("Exiting save");
		return mapping.findForward(theForward);
	}

	/**
	 * Used to update a animalModel.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.trace("Entering edit");

		// Grab the current modelID from the session
		String theModelId = (String) request.getSession().getAttribute(
				Constants.MODELID);

		ModelCharacteristicsForm theModelChar = (ModelCharacteristicsForm) form;

		log
				.info("editExistingModel - New Model Being created with following Characteristics:"
						+ "\n\t description: "
						+ theModelChar.getDescription()
						+ "\n\t breedingNotes: "
						+ theModelChar.getBreedingNotes()
						+ "\n\t PI: "
						+ theModelChar.getPrincipalInvestigator()
						+ "\n\t experimentDesign: "
						+ theModelChar.getExperimentDesign()
						+ "\n\t isToolStrain: "
						+ theModelChar.getIsToolStrain()
						+ "\n\t modelDescriptor: "
						+ theModelChar.getModelDescriptor()
						+ "\n\t name: "
						+ theModelChar.getName()
						+ "\n\t releaseDate: "
						+ theModelChar.getReleaseDate()
						+ "\n\t scientificName: "
						+ theModelChar.getScientificName()
						+ "\n\t ethinicityStrain: "
						+ theModelChar.getEthinicityStrain()
						+ "\n\t OtherEthnicityStrain: "
						+ theModelChar.getOtherEthnicityStrain()
						+ "\n\t summary: "
						+ theModelChar.getSummary()
						+ "\n\t genotype: "
						+ theModelChar.getGenotype()
						+ "\n\t nomenclature: "
						+ theModelChar.getNomenclature()						
						+ "\n\t type: "
						+ theModelChar.getType()
						+ "\n\t url: "
						+ theModelChar.getUrl()
						+ "\n\t calendarReleaseDate: "
						+ theModelChar.getCalendarReleaseDate()
						+ "\n\t Genotype: "
						+ theModelChar.getGenotype()
						+ "\n\t Nomenclature: "
						+ theModelChar.getNomenclature()
						+ "\n\t Comment: "
						+ theModelChar.getComments()						
						+ "\n\t currentUser: "
						+ (String) request.getSession().getAttribute(
								"camod.loggedon.username"));

		String theForward = "AnimalModelTreePopulateAction";

		try {
			
	        // Get and clean method to prevent Cross-Site Scripting
	        String methodName = request.getParameter("method");
	        log.debug("methodName: " + methodName);
	        if (!methodName.equals("edit")){
		        methodName = SafeHTMLUtil.clean(methodName);
		        log.debug("methodName: " + methodName);
	        }				

			AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");

			// retrieve model and update w/ new values
			AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
			theAnimalModelManager.update(theModelChar, theAnimalModel);

			// Setup global constants to use for submission / editing process
			request.getSession().setAttribute(Constants.MODELID,
					theAnimalModel.getId().toString());
			request.getSession().setAttribute(Constants.MODELDESCRIPTOR,
					theAnimalModel.getModelDescriptor());
			request.getSession().setAttribute(Constants.MODELSTATUS,
					theAnimalModel.getState());

			// Reset the species common name to use on the Genetic Description submission screens (mgi, zfin, rgd id's)
			// in case the species is modified after the model is created
			request.getSession().setAttribute(Constants.AMMODELSPECIESCOMMONNAME, 
					theAnimalModel.getStrain().getSpecies().getCommonName());

			// Add a message to be displayed in submitOverview.jsp saying you've
			// edited a model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"modelchar.edit.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {

			log.error("Exception ocurred editing model", e);

			// Encountered an error saving the model.
			// created a new model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.admin.message"));
			saveErrors(request, msg);

			theForward = "failure";
		}

		log.trace("Exiting edit");

		return mapping.findForward(theForward);
	}

	/**
	 * Creates an exact duplicate of a AnimalModel
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward duplicate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("<AnimalModelAction duplicate> modelID="
				+ request.getParameter("aModelID"));

		String theForward = "duplicatesuccessful";

		try {
			
	        // Get and clean method to prevent Cross-Site Scripting
	        String methodName = request.getParameter("method");
	        log.debug("methodName: " + methodName);
	        if (!methodName.equals("duplicate")){
		        methodName = SafeHTMLUtil.clean(methodName);
		        log.debug("methodName: " + methodName);
	        }				

			String modelID = request.getParameter("aModelID");

			AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

			// retrieve model by it's id
			AnimalModel animalModel = animalModelManager.get(modelID);

			AnimalModel theDuplicatedModel = animalModelManager
					.duplicate(animalModel);

			log.debug("Duplicated model id: " + theDuplicatedModel.getId());

		} catch (Exception e) {

			log.error("Exception ocurred duplicating model", e);

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.admin.message"));
			saveErrors(request, msg);

			theForward = "failure";
		}

		return mapping.findForward(theForward);
	}

	/**
	 * Delete a AnimalModel based on it's id
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("<AnimalModelAction Delete> modelID="
				+ request.getParameter("aModelID"));

		String theForward = "modeldeleted";
		try {
			
	        // Get and clean method to prevent Cross-Site Scripting
	        String methodName = request.getParameter("method");
	        log.debug("methodName: " + methodName);
	        methodName = SafeHTMLUtil.clean(methodName);
	        log.debug("methodName: " + methodName);	
	        
			// Retrieve the parameter passed by the URL
			String modelID = request.getParameter("aModelID");

			AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
			animalModelManager.remove(modelID);

			// Add a message to be displayed in submitModles saying you've
			// deleted a
			// model
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"delete.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {

			log.error("Exception ocurred deleting model", e);

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.admin.message"));
			saveErrors(request, msg);

			theForward = "failure";
		}
		return mapping.findForward(theForward);
	}

	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward returnUserModels(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("<AnimalModelAction returnUserModels> Entering... ");

		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

		try {
	        // Get and clean method to prevent Cross-Site Scripting
	        String methodName = request.getParameter("method");
	        log.debug("methodName: " + methodName);
	        if (!methodName.equals("returnUserModels")){
		        methodName = SafeHTMLUtil.clean(methodName);
		        log.debug("methodName: " + methodName);
	        }				

			List amList = animalModelManager.getAllByUser((String) request
					.getSession().getAttribute(Constants.CURRENTUSER));

			// sort list by modelDescriptor, ignoring case
			Collections.sort(amList, new _sortAnimalModels());
			request.getSession().setAttribute(Constants.USERMODELLIST, amList);

		} catch (Exception e) {

			log.error("Unable to fetch models for user: ", e);
			request.getSession().setAttribute(Constants.USERMODELLIST,
					new ArrayList());

			// Set the error message
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.admin.message"));
			saveErrors(request, msg);
		}

		return mapping.findForward("submitModels");
	}
}

class _sortAnimalModels implements java.util.Comparator {

	public void SortAnimalModels() {
	}

	public int compare(Object oo1, Object oo2) {
		AnimalModel o1 = (AnimalModel) oo1;
		AnimalModel o2 = (AnimalModel) oo2;

		if (o1.getModelDescriptor()
				.compareToIgnoreCase(o2.getModelDescriptor()) > 0)
			return 1;
		else if (o1.getModelDescriptor().compareToIgnoreCase(
				o2.getModelDescriptor()) < 0)
			return -1;
		else
			return 0;
	}
}