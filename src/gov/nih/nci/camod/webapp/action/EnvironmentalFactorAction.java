/**
 * 
 * $Id: EnvironmentalFactorAction.java,v 1.12 2005-10-19 19:27:28 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.11  2005/09/28 21:20:11  georgeda
 * Finished up converting to new manager
 *
 */
package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.*;
import gov.nih.nci.camod.webapp.form.EnvironmentalFactorForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * EnvironmentalFactorAction Class
 */
public final class EnvironmentalFactorAction extends BaseAction {

    /**
     * Called from submitEnvironmentalFactors.jsp
     * 
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }

        EnvironmentalFactorForm envForm = (EnvironmentalFactorForm) form;

        System.out.println("<EnvironmentalFactorAction save> following Characteristics:" 
        		+ "\n\t name: " + envForm.getName() 
        		+ "\n\t otherName: " + envForm.getOtherName() 
        		+ "\n\t dosage: " + envForm.getDosage() 
        		+ "\n\t administrativeRoute: " + envForm.getAdministrativeRoute()
                + "\n\t regimen: " + envForm.getRegimen() 
                + "\n\t ageAtTreatment: " + envForm.getAgeAtTreatment()
                + "\n\t type: " + envForm.getType() 
                + "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

        /* Grab the current modelID from the session */
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        /* Create all the manager objects needed for Screen */
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel animalModel = animalModelManager.get(modelID);

        try {
            animalModelManager.addTherapy(animalModel, envForm);

            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("environmentalfactor.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {

            log.error("Unable to get add an environmental factor: ", e);

            ActionMessages theMsg = new ActionMessages();
            theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, theMsg);
        }

        return mapping.findForward("AnimalModelTreePopulateAction");
    }

    /**
     * Cancel
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward duplicate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        return mapping.findForward("submitOverview");
    }

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

        // Grab the current Therapy we are working with related to this
        // animalModel
        String aTherapyID = request.getParameter("aTherapyID");

        // Create a form to edit
        EnvironmentalFactorForm envForm = (EnvironmentalFactorForm) form;

        System.out.println("<EnvironmentalFactorAction save> following Characteristics:" 
        		+ "\n\t name: " + envForm.getName() 
        		+ "\n\t otherName: " + envForm.getOtherName() 
        		+ "\n\t dosage: " + envForm.getDosage() 
        		+ "\n\t administrativeRoute: " + envForm.getAdministrativeRoute()
                + "\n\t regimen: " + envForm.getRegimen() 
                + "\n\t ageAtTreatment: " + envForm.getAgeAtTreatment()
                + "\n\t type: " + envForm.getType() 
                + "\n\t user: " + (String) request.getSession().getAttribute("camod.loggedon.username"));

        TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");

        try {

            Therapy theTherapy = therapyManager.get(aTherapyID);
            therapyManager.update(envForm, theTherapy);

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("environmentalfactor.edit.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {

            log.error("Unable to get add a chemical drug action: ", e);

            ActionMessages theMsg = new ActionMessages();
            theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, theMsg);
        }

        return mapping.findForward("AnimalModelTreePopulateAction");
    }

}