package gov.nih.nci.camod.webapp.action;

import java.util.List;
import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Disease;
import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.domain.Organ;
import gov.nih.nci.camod.service.HistopathologyManager;
import gov.nih.nci.camod.webapp.form.AssociatedMetastasisForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AssociatedMetastasisPopulateAction extends BaseAction {

    /**
     * Pre-populate all field values in the form <FormName> Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<AssociatedMetastasisPopulateAction populate> Entered");

        AssociatedMetastasisForm assocMetastasisForm = (AssociatedMetastasisForm) form;

        // Grab the current aHistopathID from the session
        String aHistopathologyID = request.getParameter("aHistopathologyID");
        log.debug("aHistopathID: " + aHistopathologyID);

        // Grab the current aAssocMetastasisID we are working with related to
        // this animalModel
        String aAssociatedMetastasisID = request.getParameter("aAssociatedMetastasisID");
        log.debug("aAssocMetastasisID: " + aAssociatedMetastasisID);

        HistopathologyManager histopathologyManager = (HistopathologyManager) getBean("histopathologyManager");
        Histopathology associatedMetastasis = histopathologyManager.get(aAssociatedMetastasisID);
        if (associatedMetastasis == null) {
            request.setAttribute(Constants.Parameters.DELETED, "true");
        } else {
            request.setAttribute("aAssociatedMetastasisID", aAssociatedMetastasisID);

            /* Set Histopathology attributes */
            assocMetastasisForm.setAgeOfOnset(associatedMetastasis.getAgeOfOnset());
            if (associatedMetastasis.getWeightOfTumor() != null) {
                assocMetastasisForm.setWeightOfTumor(associatedMetastasis.getWeightOfTumor().toString());
            }
            if (associatedMetastasis.getVolumeOfTumor() != null) {
                assocMetastasisForm.setVolumeOfTumor(associatedMetastasis.getVolumeOfTumor().toString());
            }
            if (associatedMetastasis.getTumorIncidenceRate() != null) {
                assocMetastasisForm.setTumorIncidenceRate(associatedMetastasis.getTumorIncidenceRate().toString());
            }
            assocMetastasisForm.setSurvivalInfo(associatedMetastasis.getSurvivalInfo());
            assocMetastasisForm.setGrossDescription(associatedMetastasis.getGrossDescription());
            assocMetastasisForm.setMicroscopicDescription(associatedMetastasis.getMicroscopicDescription());

            assocMetastasisForm.setComparativeData(associatedMetastasis.getComparativeData());
            assocMetastasisForm.setComments(associatedMetastasis.getComments());

            /* set Organ attributes */
            Organ organ = associatedMetastasis.getOrgan();
            System.out.println("<AssociatedMetastasisAction populate> get the Organ attributes");

            // since we are always querying from concept code (save and
            // edit), simply display EVSPreferredDescription
            assocMetastasisForm.setOrgan(organ.getEVSPreferredDescription());
            System.out.println("setOrgan= " + organ.getEVSPreferredDescription());

            assocMetastasisForm.setOrganTissueCode(organ.getConceptCode());
            System.out.println("OrganTissueCode= " + organ.getConceptCode());

            /* Set Disease object attributes */
            List diseaseList = associatedMetastasis.getDiseaseCollection();

            Disease disease = (Disease) diseaseList.get(0);
            assocMetastasisForm.setDiagnosisName(disease.getName());
            assocMetastasisForm.setDiagnosisCode(disease.getConceptCode());
            assocMetastasisForm.setTumorClassification(disease.getName());

            /* Set GeneticAlteration attributes */
            if (associatedMetastasis.getGeneticAlteration() != null) {
                assocMetastasisForm.setObservation(associatedMetastasis.getGeneticAlteration().getObservation());
                assocMetastasisForm.setMethodOfObservation(associatedMetastasis.getGeneticAlteration()
                        .getMethodOfObservation());
            }
        }

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        this.dropdown(request, response);

        return mapping.findForward("submitAssocMetastasis");
    }

    /**
     * Populate the dropdown menus
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

        System.out.println("<AssociatedMetastasisPopulateAction dropdown> Entering ActionForward dropdown()");

        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitAssocMetastasis");
    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("<AssociatedMetastasisPopulateAction dropdown> Entering void dropdown()");

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");

    }

}
