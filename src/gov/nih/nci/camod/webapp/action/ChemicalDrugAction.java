package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.*;
import gov.nih.nci.camod.webapp.form.ChemicalDrugForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * ChemicalDrugAction Class
 */

public class ChemicalDrugAction extends BaseAction {

    /**
     * Delete
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        return mapping.findForward("");
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

        return mapping.findForward("");
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

        System.out.println("<ChemicalDrugAction edit> Entering... ");

        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        // Grab the current Therapy we are working with related to this
        // animalModel
        String aTherapyID = request.getParameter("aTherapyID");

        ChemicalDrugForm chemicalDrugForm = (ChemicalDrugForm) form;

        System.out.println("<ChemicalDrugAction editing> editing... " + "\n\t name: " + chemicalDrugForm.getName()
                + "\n\t otherName: " + chemicalDrugForm.getOtherName() + "\n\t type: " + chemicalDrugForm.getType()
                + "\n\t regimen: " + chemicalDrugForm.getRegimen() + "\n\t dosage: " + chemicalDrugForm.getDosage()
                + "\n\t doseUnit: " + chemicalDrugForm.getDoseUnit() + "\n\t ageAtTreatment: "
                + chemicalDrugForm.getAgeAtTreatment() + "\n\t ageUnit: " + chemicalDrugForm.getAgeUnit() + 
                "\n\t administrativeRoute: " + chemicalDrugForm.getAdministrativeRoute());

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        AnimalModel animalModel = animalModelManager.get(modelID);

        try {

            animalModelManager.updateChemicalDrug(animalModel, chemicalDrugForm, aTherapyID);

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("chemicaldrug.edit.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {

            log.error("Unable to get add a chemical drug action: ", e);

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

        System.out.println("<ChemicalDrugAction save> Entering... ");

        // Grab the current modelID from the session
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        ChemicalDrugForm chemicalDrugForm = (ChemicalDrugForm) form;

        System.out.println("<ChemicalDrugAction save> Adding... " + "\n\t name: " + chemicalDrugForm.getName()
                + "\n\t otherName: " + chemicalDrugForm.getOtherName() + "\n\t type: " + chemicalDrugForm.getType()
                + "\n\t regimen: " + chemicalDrugForm.getRegimen() + "\n\t dosage: " + chemicalDrugForm.getDosage()
                + "\n\t doseUnit: " + chemicalDrugForm.getDoseUnit() + "\n\t ageAtTreatment: "
                + chemicalDrugForm.getAgeAtTreatment() + "\n\t NSC: " + chemicalDrugForm.getNSCNumber() + "\n\t CAS: "
                + chemicalDrugForm.getCASNumber() + "\n\t ageUnit: " + chemicalDrugForm.getAgeUnit());

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        
        AnimalModel animalModel = animalModelManager.get(modelID);
        
        try {

            animalModelManager.addChemicalDrug(animalModel, chemicalDrugForm);

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("chemicaldrug.edit.successful"));
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
