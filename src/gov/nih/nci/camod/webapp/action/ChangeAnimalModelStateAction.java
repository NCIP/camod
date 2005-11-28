/**
 *  @author dgeorge
 *  
 *  $Id: ChangeAnimalModelStateAction.java,v 1.9 2005-11-28 13:48:18 georgeda Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.8  2005/10/10 14:12:01  georgeda
 *  Changes for comment curation
 *
 *  Revision 1.7  2005/09/16 15:52:56  georgeda
 *  Changes due to manager re-write
 *
 *  
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Log;
import gov.nih.nci.camod.service.*;
import gov.nih.nci.camod.service.impl.CurationManagerImpl;
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class ChangeAnimalModelStateAction extends BaseAction {

	/**
	 * Change the state for the animal model
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
			HttpServletResponse inResponse) throws Exception {

		log.trace("Entering ChangeAnimalModelStateAction.execute");

		// The user didn't press the cancel button
		if (!isCancelled(inRequest)) {

			try {
				// Get the curation manager workflow XML
				CurationManager theCurationManager = new CurationManagerImpl(getServlet().getServletContext()
						.getRealPath("/")
						+ Constants.Admin.MODEL_CURATION_WORKFLOW);

				// Get the animal model
				AnimalModelStateForm theForm = (AnimalModelStateForm) inForm;
				AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
				AnimalModel theAnimalModel = theAnimalModelManager.get(theForm.getModelId());

				// Did the id match?
				if (theAnimalModel != null) {

					log.debug("Current state of model: " + theAnimalModel.getState());

					theCurationManager.changeState(theAnimalModel, theForm.getEvent());

					log.debug("New state of model: " + theAnimalModel.getState());

					// Save the associated log comment to track the curation
					// state
					LogManager theLogManager = (LogManager) getBean("logManager");
					Log theLog = theLogManager.create(theForm.getAssignedTo(), theForm.getModelId(), theAnimalModel
							.getState(), theForm.getNote());

					theAnimalModelManager.updateAndAddLog(theAnimalModel, theLog);

					// Do any association actions since we've sucessfully
					// changed
					// state
					HashMap theMap = new HashMap();
					theMap.put(Constants.FORMDATA, theForm);
					theCurationManager.applyActionsForState(theAnimalModel, theMap);
				}

			}
			catch (IllegalArgumentException e) {
				log.error("Caught illegal argument exception changing animal model state.  Probably back arrow and resubmit.", e);
			}
			catch (Exception e) {
				log.error("Caught exception changing animal model state", e);

				// Encountered an error changing the state
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
				saveErrors(inRequest, msg);

			}
		}

		log.trace("Exiting ChangeAnimalModelStateAction.execute");

		return inMapping.findForward("next");
	}
}