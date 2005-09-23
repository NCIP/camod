package gov.nih.nci.camod.webapp.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Agent;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.SexDistribution;
import gov.nih.nci.camod.domain.Therapy;
import gov.nih.nci.camod.domain.Treatment;
import gov.nih.nci.camod.service.AgentManager;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.SexDistributionManager;
import gov.nih.nci.camod.service.TherapyManager;
import gov.nih.nci.camod.service.TreatmentManager;
import gov.nih.nci.camod.webapp.form.ChemicalDrugForm;

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
                + chemicalDrugForm.getAgeAtTreatment() + "\n\t ageUnit: " + chemicalDrugForm.getAgeUnit());

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        SexDistributionManager sexDistributionManager = (SexDistributionManager) getBean("sexDistributionManager");
        TreatmentManager treatmentManager = (TreatmentManager) getBean("treatmentManager");
        AgentManager agentManager = (AgentManager) getBean("agentManager");
        TherapyManager therapyManager = (TherapyManager) getBean("therapyManager");

        AnimalModel animalModel = animalModelManager.get(modelID);

        // retrieve the list of all therapies from the current animalModel
        List therapyList = animalModel.getTherapyCollection();

        Therapy ty = new Therapy();
        int therapyNumber = 0;

        // find the specific one we need
        for (int i = 0; i < therapyList.size(); i++) {
            ty = (Therapy) therapyList.get(i);
            System.out.println(" searching ... id=" + ty.getId().toString() + " is it equal? " + aTherapyID);
            if (ty.getId().toString().equals(aTherapyID)) {
                therapyNumber = i;
                System.out.println("found a match!");
                break;
            }
        }

        // Set the treatment
        Treatment ts = ty.getTreatment();

        // Set the gender
        SexDistribution sexDistribution = sexDistributionManager.getByType(chemicalDrugForm.getType());

        // save the treatment
        ts.setRegimen(chemicalDrugForm.getRegimen());
        ts.setSexDistribution(sexDistribution);
        // Append the ageunit onto the age at treatment variable
        ts.setAgeAtTreatment(chemicalDrugForm.getAgeAtTreatment() + " " + chemicalDrugForm.getAgeUnit());
        ts.setDosage(chemicalDrugForm.getDosage() + " " + chemicalDrugForm.getDoseUnit());
        ts.setAdministrativeRoute(chemicalDrugForm.getAdministrativeRoute());
        treatmentManager.save(ts);

        // Agent IS-A an EnvironmentalFactor
        Agent agent = ty.getAgent();
        agent.setName(chemicalDrugForm.getName());
        agent.setNscNumber(Long.valueOf(chemicalDrugForm.getNSCNumber().trim()));
        agent.setCasNumber(chemicalDrugForm.getCASNumber());
        agent.setType("Chemical / Drug");
        agentManager.save(agent);

        // TherapeuticExperiment property is false, tells us that this is an
        // environmentalFactor
        ty.setTherapeuticExperiment(new Boolean(false));
        ty.setAgent(agent);
        ty.setTreatment(ts);
        therapyManager.save(ty);
        therapyList.set(therapyNumber, ty);

        animalModel.setTherapyCollection(therapyList);

        // Add a message to be displayed in submitOverview.jsp saying you've
        // created a new model successfully
        ActionMessages msg = new ActionMessages();
        msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("chemicaldrug.edit.successful"));
        saveErrors(request, msg);

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

        System.out.println("<GrowthFactorAction save> Adding... " + "\n\t name: " + chemicalDrugForm.getName()
                + "\n\t otherName: " + chemicalDrugForm.getOtherName() + "\n\t type: " + chemicalDrugForm.getType()
                + "\n\t regimen: " + chemicalDrugForm.getRegimen() + "\n\t dosage: " + chemicalDrugForm.getDosage()
                + "\n\t doseUnit: " + chemicalDrugForm.getDoseUnit() + "\n\t ageAtTreatment: "
                + chemicalDrugForm.getAgeAtTreatment() + "\n\t NSC: " + chemicalDrugForm.getNSCNumber() + "\n\t CAS: "
                + chemicalDrugForm.getCASNumber() + "\n\t ageUnit: " + chemicalDrugForm.getAgeUnit());

        AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");
        SexDistributionManager sexDistributionManager = (SexDistributionManager) getBean("sexDistributionManager");
        TreatmentManager treatmentManager = (TreatmentManager) getBean("treatmentManager");
        AgentManager agentManager = (AgentManager) getBean("agentManager");

        AnimalModel animalModel = animalModelManager.get(modelID);

        // Set the gender
        SexDistribution sexDistribution = sexDistributionManager.getByType(chemicalDrugForm.getType());

        // Set the treatment
        Treatment ts = new Treatment();
        ts.setRegimen(chemicalDrugForm.getRegimen());
        ts.setSexDistribution(sexDistribution);
        // Append the ageunit onto the age at treatment variable
        ts.setAgeAtTreatment(chemicalDrugForm.getAgeAtTreatment() + " " + chemicalDrugForm.getAgeUnit());
        ts.setDosage(chemicalDrugForm.getDosage() + " " + chemicalDrugForm.getDoseUnit());
        ts.setAdministrativeRoute(chemicalDrugForm.getAdministrativeRoute());
        treatmentManager.save(ts);

        // Agent IS-A an EnvironmentalFactor
        Agent agent = new Agent();
        agent.setName(chemicalDrugForm.getName());
        agent.setNscNumber(Long.valueOf(chemicalDrugForm.getNSCNumber()));
        agent.setCasNumber(chemicalDrugForm.getCASNumber());
        agent.setType("Chemical / Drug");
        agentManager.save(agent);

        // TherapeuticExperiment property is false, tells us that this is an
        // environmentalFactor
        Therapy ty = new Therapy();
        ty.setTherapeuticExperiment(new Boolean(false));
        ty.setAgent(agent);
        ty.setTreatment(ts);

        // Add therapy to animalModel
        animalModel.addTherapy(ty);

        // Persist all changes to the db
        animalModelManager.save(animalModel);

        // Add a message to be displayed in submitOverview.jsp saying you've
        // created a new model successfully
        ActionMessages msg = new ActionMessages();
        msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("chemicaldrug.edit.successful"));
        saveErrors(request, msg);

        return mapping.findForward("AnimalModelTreePopulateAction");
    }

}
