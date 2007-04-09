/**
 *  @author dgeorge
 *  
 *  $Id: ChangeAnimalModelStateAction.java,v 1.13 2007-04-09 12:37:09 pandyas Exp $
 *  
 *  $Log: not supported by cvs2svn $
 *  Revision 1.12  2007/03/27 18:42:32  pandyas
 *  Modified the code to use the Constants.MODELID for getting the animal model id and passing from submitOverview to change states of a model.  The constant is already set in SubmitAction.java for all models.
 *
 *  Revision 1.11  2006/10/17 16:11:00  pandyas
 *  modified during development of caMOD 2.2 - various
 *
 *  Revision 1.10  2006/05/15 15:44:27  georgeda
 *  Cleaned up contact info management
 *
 *  Revision 1.9  2005/11/28 13:48:18  georgeda
 *  Defect #192, handle back arrow for curation changes
 *
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class ChangeAnimalModelStateAction extends BaseAction {

	/**
	 * Change the state for the animal model This method is used by
	 * submitOverview.jsp, adminEditModels.jsp, and
	 * 
	 */
	public ActionForward execute(ActionMapping inMapping, ActionForm inForm,
			HttpServletRequest inRequest, HttpServletResponse inResponse)
			throws Exception {

		String theModelId = null;
		String theEvent = null;

		// The user didn't press the cancel button
		if (!isCancelled(inRequest)) {

			try {
				log.info("<ChangeAnimalModelStateAction> Entering try ");

				// Get the curation manager workflow XML
				CurationManager theCurationManager = new CurationManagerImpl(
						getServlet().getServletContext().getRealPath("/")
								+ Constants.Admin.MODEL_CURATION_WORKFLOW);

				// Get the animal model
				AnimalModelStateForm theForm = (AnimalModelStateForm) inForm;
				AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
				
				log.info("<ChangeAnimalModelStateAction> theForm.getModelId(): " + theForm.getModelId());
				log.info("<ChangeAnimalModelStateAction> theForm.getEvent(): " + theForm.getEvent());				
								
				if (theForm.getModelId() == null){
					// get model id from adminRoles.jsp and submitOverview.jsp (complete)
					theModelId = (String)inRequest.getSession().getAttribute(Constants.Parameters.MODELID);
					log.info("theModelId from Constants.Parameters.MODELID: " + theModelId);
					
					if (theModelId == null) {
					theModelId = inRequest.getParameter("aModelId");	
					log.info("theModelId from inRequest.getParameter: " + theModelId);
					}
					if(theModelId != null){
						theForm.setModelId(theModelId);
						log.info("theModelId set to the form-  theForm.getModelId(): " + theForm.getModelId());
					}						
				}				

				// get the model id from the form in submitOverview options
				AnimalModel theAnimalModel = theAnimalModelManager.get(theForm
						.getModelId());

				// get event from adminRoles.jsp and submitOverview.jsp
				// (complete)
				if (theForm.getEvent() == null) {
					// get event from submitOverview.jsp (back to complete or
					// screened-approved), inactive
					theEvent = inRequest.getParameter("aEvent");
					log.info("get theEvent: " + theEvent);
					// set the form so value is in one place for all code
					theForm.setEvent(theEvent);
				}

				// Get properties for e-mail function
				Properties camodProperties = new Properties();
				String camodPropertiesFileName = null;

				camodPropertiesFileName = System
						.getProperty("gov.nih.nci.camod.camodProperties");

				try {

					FileInputStream in = new FileInputStream(
							camodPropertiesFileName);
					camodProperties.load(in);

				} catch (FileNotFoundException e) {
					log.error("Caught exception finding file for properties: ",
							e);
					e.printStackTrace();
				} catch (IOException e) {
					log.error("Caught exception finding file for properties: ",
							e);
					e.printStackTrace();
				}
				String theCoordinator = camodProperties
						.getProperty("coordinator.username");

				// Did the id match?
				if (theAnimalModel != null) {

					if (theForm.getEvent().equals(
							Constants.Admin.Actions.INACTIVATE)) {
						log.info("Changing model to: " + theEvent);

						// Change model to inactive
						changeModelToInactive(theAnimalModel, theForm,
								theCurationManager, theAnimalModelManager,
								theCoordinator);
					}

					else if (theForm.getEvent().equals(
							Constants.Admin.Actions.BACK_TO_SCREENER_APPROVE)) {
						log.info("Changing model to inactive first, then to: "
								+ theForm.getEvent());

						// Change model to inactive first,then to
						// screener-approved
						changeModelToInactive(theAnimalModel, theForm,
								theCurationManager, theAnimalModelManager,
								theCoordinator);

						log.info("1) Changed model to inactive, now step 2): ");

						// Set the event and assign to coordinator,
						// Null editor or screener assignment - so model can be
						// re-assigned later
						theForm
								.setEvent(Constants.Admin.Actions.SCREENER_APPROVE);
						theForm.setAssignedTo(theCoordinator);
						theForm
								.setNote("This model has been moved back to screener_approve");

						log.info("theModelId: " + theModelId);
						log.info("theEvent: " + theEvent);

						theCurationManager.changeState(theAnimalModel, theForm
								.getEvent());

						log.info("New state of model: "
								+ theAnimalModel.getState());

						// Save the associated log comment to track the curation
						// state
						LogManager theLogManager = (LogManager) getBean("logManager");
						Log theLog = theLogManager.create(theForm
								.getAssignedTo(), theForm.getModelId(),
								theAnimalModel.getState(), theForm.getNote());

						theAnimalModelManager.updateAndAddLog(theAnimalModel,
								theLog);

						// Do any association actions since we've sucessfully
						// changed state
						HashMap<String, Object> theMap = new HashMap<String, Object>();
						theMap.put(Constants.FORMDATA, theForm);
						theCurationManager.applyActionsForState(theAnimalModel,
								theMap);

					}

					// Complete chosen from inactive mode
					else if (theForm.getEvent().equals(
							Constants.Admin.Actions.BACK_TO_COMPLETE)) {

						log.info("Check state: " + theAnimalModel.getState());

						if(!theAnimalModel.getState().equals(
								Constants.Admin.ModelState.INACTIVE)) {
							
							log.info("Model already Inactive so complete loop: ");

							// Change model to inactive first,then to
							// screener-approved
							changeModelToInactive(theAnimalModel, theForm,
									theCurationManager, theAnimalModelManager,
									theCoordinator);

						}
						log.info("Changing model to inactive then to completes loop: ");
						// set event to complete instead of back_to_complete
						theForm.setEvent(Constants.Admin.Actions.COMPLETE);
						theForm.setNote("Model has been moved back to complete");
						theForm.setAssignedTo(theCoordinator);

						log.info("1) Changed model to inactive, now step 2): ");

						log.info("Current state of model: "
								+ theAnimalModel.getState());

						theCurationManager.changeState(theAnimalModel, theForm
								.getEvent());

						log.info("New state of model: "
								+ theAnimalModel.getState());

						// Save the associated log comment to track the curation
						// state
						LogManager theLogManager = (LogManager) getBean("logManager");
						Log theLog = theLogManager.create(theForm
								.getAssignedTo(), theForm.getModelId(),
								theAnimalModel.getState(), theForm.getNote());

						theAnimalModelManager.updateAndAddLog(theAnimalModel,
								theLog);

						// Do any association actions since we've sucessfully
						// changed state
						HashMap<String, Object> theMap = new HashMap<String, Object>();
						theMap.put(Constants.FORMDATA, theForm);
						theCurationManager.applyActionsForState(theAnimalModel,
								theMap);

					} else {
						// change the state - used by all events from admin
						log.info("Common code for state changes - Event= "
								+ theForm.getEvent());
						log.info("theModelId: " + theModelId);
						log.info("Current state of model: "
								+ theAnimalModel.getState());

						theCurationManager.changeState(theAnimalModel, theForm
								.getEvent());

						log.info("New state of model: "
								+ theAnimalModel.getState());

						// Save the associated log comment to track the curation
						// state
						LogManager theLogManager = (LogManager) getBean("logManager");
						Log theLog = theLogManager.create(theForm
								.getAssignedTo(), theForm.getModelId(),
								theAnimalModel.getState(), theForm.getNote());

						theAnimalModelManager.updateAndAddLog(theAnimalModel,
								theLog);

						// Do any association actions since we've sucessfully
						// changed state
						HashMap<String, Object> theMap = new HashMap<String, Object>();
						theMap.put(Constants.FORMDATA, theForm);
						theCurationManager.applyActionsForState(theAnimalModel,
								theMap);
					}
				}

			} catch (IllegalArgumentException e) {
				log.error(
						"Caught illegal argument exception changing animal model state.  "
								+ "Probably back arrow and resubmit.", e);
			} catch (Exception e) {
				log.error("Caught exception changing animal model state", e);

				// Encountered an error changing the state
				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"errors.admin.message"));
				saveErrors(inRequest, msg);

			}
		}

		log.trace("Exiting ChangeAnimalModelStateAction.change");

		return inMapping.findForward("next");
	}

	/**
	 * Change the state for the animal model
	 */
	private void changeModelToInactive(AnimalModel theAnimalModel,
			AnimalModelStateForm theForm, CurationManager theCurationManager,
			AnimalModelManager theAnimalModelManager, String theCoordinator)
			throws Exception {

		log.info("theEvent: " + theForm.getModelId());

		// Did the id match?
		if (theAnimalModel != null) {

			// Set the event and assign to coordinator,
			// Null editor or screener assignment - so model can be
			// re-assigned later
			theForm.setModelId(theForm.getModelId());
			theForm.setEvent(Constants.Admin.Actions.INACTIVATE);
			theForm.setAssignedTo(theCoordinator);
			theForm.setNote("This model has been moved to inactive");

			log.info("theEvent: " + theForm.getEvent());

			theCurationManager.changeState(theAnimalModel, theForm.getEvent());

			log.info("New state of model: " + theAnimalModel.getState());

			// Save the associated log comment to track the curation
			// state
			LogManager theLogManager = (LogManager) getBean("logManager");
			Log theLog = theLogManager
					.create(theForm.getAssignedTo(), theForm.getModelId(),
							theAnimalModel.getState(), theForm.getNote());

			theAnimalModelManager.updateAndAddLog(theAnimalModel, theLog);

			// Do any association actions since we've sucessfully
			// changed state
			HashMap<String, Object> theMap = new HashMap<String, Object>();
			theMap.put(Constants.FORMDATA, theForm);
			theCurationManager.applyActionsForState(theAnimalModel, theMap);

			log.info("Changed model to inactive");
			log.info("theAnimalModel.getState(): " + theAnimalModel.getState());
		}

	}

}