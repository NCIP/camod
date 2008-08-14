/**
 * $Id: HormoneAction.java,v 1.12 2008-08-14 16:53:46 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2007/10/31 17:10:24  pandyas
 * Modified comments for #8355 	Add comments field to every submission page
 *
 * Revision 1.10  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.9  2005/11/09 00:17:26  georgeda
 * Fixed delete w/ constraints
 *
 * Revision 1.8  2005/11/02 21:48:09  georgeda
 * Fixed validate
 *
 * Revision 1.7  2005/11/02 19:02:08  pandyas
 * Added e-mail functionality
 *
 * Revision 1.6  2005/10/28 14:50:55  georgeda
 * Fixed null pointer problem
 *
 * Revision 1.5  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.4  2005/09/28 21:20:12  georgeda
 * Finished up converting to new manager
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
import gov.nih.nci.camod.webapp.form.HormoneForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

/**
 * HormoneAction Class
 */

public class HormoneAction extends BaseAction {

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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}

        // Grab the current CarcinogenExposure we are working with related to this animalModel
        String aCarcinogenExposureID = request.getParameter("aCarcinogenExposureID");
        
		HormoneForm hormoneForm = (HormoneForm) form;

        log.info("<HormoneAction editing> editing... " + "\n\t name: " + hormoneForm.getName()
				+ "\n\t otherName: " + hormoneForm.getOtherName() + "\n\t regimen: " + hormoneForm.getRegimen()
				+ "\n\t dosage: " + hormoneForm.getDosage()  + "\n\t Comments: " + hormoneForm.getComments()
				+ "\n\t dosageUnit: " + hormoneForm.getDosageUnit());

        CarcinogenExposureManager carcinogenExposureManager = (CarcinogenExposureManager) getBean("carcinogenExposureManager");
        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

		try {

            // Grab the current modelID from the session
            String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
            
            if ("Delete".equals(theAction)) {
                carcinogenExposureManager.remove(aCarcinogenExposureID, theAnimalModel);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("hormone.delete.successful"));
				saveErrors(request, msg);

			} else {				
                CarcinogenExposure theCarcinogenExposure = carcinogenExposureManager.get(aCarcinogenExposureID);
                carcinogenExposureManager.update(theAnimalModel, hormoneForm, theCarcinogenExposure);

				ActionMessages msg = new ActionMessages();
				msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("hormone.edit.successful"));
				saveErrors(request, msg);
			}
		} catch (Exception e) {

			log.error("Unable to edit a hormone: ", e);

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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		// Grab the current modelID from the session
		String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

		HormoneForm hormoneForm = (HormoneForm) form;

        log.info("<HormoneAction save> Adding... " + "\n\t name: " + hormoneForm.getName()
                        + "\n\t otherName: " + hormoneForm.getOtherName() + "\n\t regimen: " + hormoneForm.getRegimen()
                        + "\n\t dosage: " + hormoneForm.getDosage()  + "\n\t Comments: " + hormoneForm.getComments()
                        + "\n\t dosageUnit: " + hormoneForm.getDosageUnit());


		AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
		AnimalModel animalModel = animalModelManager.get(modelID);

		try {
			animalModelManager.addCarcinogenExposure(animalModel, hormoneForm);

			ActionMessages msg = new ActionMessages();
			msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("hormone.creation.successful"));
			saveErrors(request, msg);

		} catch (Exception e) {

			log.error("Unable to get add an environmental factor: ", e);

			ActionMessages theMsg = new ActionMessages();
			theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
			saveErrors(request, theMsg);
		}

		return mapping.findForward("AnimalModelTreePopulateAction");
	}
}
