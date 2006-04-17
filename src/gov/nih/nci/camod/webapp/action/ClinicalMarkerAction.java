/**
 * @author pandyas
 * 
 * $Id: ClinicalMarkerAction.java,v 1.6 2006-04-17 19:09:40 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/11/09 00:17:25  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.4  2005/11/07 19:14:14  pandyas
 * modified for clinical marker screen
 *
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.ClinicalMarker;
import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.ClinicalMarkerManager;
import gov.nih.nci.camod.service.HistopathologyManager;
import gov.nih.nci.camod.service.impl.ClinicalMarkerManagerSingleton;
import gov.nih.nci.camod.webapp.form.ClinicalMarkerForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class ClinicalMarkerAction extends BaseAction {

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

		log.info("<ClinicalMarkerAction> Entering 'edit' method");

		// Grab the current aAssocMetastasisID we are working with related to
		// this animalModel
		String aClinicalMarkerID = request.getParameter("aClinicalMarkerID");
        String aHistopathologyID = request.getParameter("aHistopathologyID");
		log.debug("aClinicalMarkerID: " + aClinicalMarkerID);

		// Create a form to edit
		ClinicalMarkerForm clinicalMarkerForm = (ClinicalMarkerForm) form;

		log.debug("<HistopathologyAction saveClinicalMarker> following Characteristics:" 
				+ "\n\t ParentHistopathID: " + clinicalMarkerForm.getHistopathologyID() + "\n\t ClinicalMarkerID: " + aClinicalMarkerID
				+ "\n\t Name: " + clinicalMarkerForm.getName() 
                + "\n\t otherName: " + clinicalMarkerForm.getOtherName()
                + "\n\t Value: " + clinicalMarkerForm.getValue() + "\n\t user: "
				+ (String) request.getSession().getAttribute("camod.loggedon.username"));

		ClinicalMarkerManager theClinicalMarkerManager = (ClinicalMarkerManager) getBean("clinicalMarkerManager");
		
		String theAction = (String) request.getParameter(Constants.Parameters.ACTION);		

		try {
			
            if ("Delete".equals(theAction)) {
                
                HistopathologyManager theHistopathologyManager = (HistopathologyManager) getBean("histopathologyManager");  
                Histopathology theHistopathology = theHistopathologyManager.get(aHistopathologyID);         
                
            	theClinicalMarkerManager.remove(aClinicalMarkerID, theHistopathology);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("clinicalmarker.delete.successful"));
				saveErrors(request, msg);

			} else {
				
				ClinicalMarker theClinicalMarker = ClinicalMarkerManagerSingleton.instance().get(aClinicalMarkerID);
				theClinicalMarkerManager.update(clinicalMarkerForm, theClinicalMarker);				

			// Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("clinicalmarker.edit.successful"));
			saveErrors(request, msg);
			}

		} catch (Exception e) {

			log.error("Unable to get a ClinicalMarker action: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

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

		log.info("<ClinicalMarkerAction> Entering 'save' method");

		// Create a form to edit
		ClinicalMarkerForm clinicalMarkerForm = (ClinicalMarkerForm) form;
		request.getSession().setAttribute(Constants.FORMDATA, clinicalMarkerForm);

		// Grab the current modelID from the session
		String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

		// Grab the current aHistopathID from the session
		String aHistopathologyID = request.getParameter("aHistopathologyID");

		System.out.println("<ClinicalMarkerAction save> following Characteristics:"
				+ "\n\t ParentHistopathID: " + aHistopathologyID 
				+ "\n\t Name: " + clinicalMarkerForm.getName()
                + "\n\t otherName: " + clinicalMarkerForm.getOtherName()
				+ "\n\t Value: " + clinicalMarkerForm.getValue() 
				+ "\n\t user: "	+ (String) request.getSession().getAttribute("camod.loggedon.username"));
		
		String theForward = "AnimalModelTreePopulateAction";

		try {
			// retrieve model and update w/ new values
			AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
			AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
			
			HistopathologyManager theHistopathologyManager = (HistopathologyManager) getBean("histopathologyManager");	
			Histopathology theHistopathology = theHistopathologyManager.get(aHistopathologyID);			

			theAnimalModelManager.addClinicalMarker(theAnimalModel, theHistopathology, clinicalMarkerForm);

			log.info("New clinical marker created");

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("clinicalmarker.creation.successful"));
			saveErrors(request, msg);

			log.info("<ClinicalMarkerAction> Exiting 'save' method");

		} catch (Exception e) {
			log.error("Exception occurred creating a Clinical Marker", e);

			// Encountered an error saving the model.
			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, msg);
		}

		log.trace("Exiting save");
		return mapping.findForward(theForward);
	}	
	
}
