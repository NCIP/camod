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
        System.out.println("aHistopathID: " + aHistopathologyID);

        // Grab the current aAssocMetastasisID we are working with related to
        // this animalModel
        String aAssociatedMetastasisID = request.getParameter("aAssociatedMetastasisID");
        System.out.println("aAssocMetastasisID: " + aAssociatedMetastasisID);

        HistopathologyManager histopathologyManager = (HistopathologyManager) getBean("histopathologyManager");
        Histopathology theHistopathology = histopathologyManager.get(aHistopathologyID);
        List assocMetList = theHistopathology.getMetastatisCollection();

        // Find the associtated metastasis we are dealing with
        for (int i = 0; i < assocMetList.size(); i++) {
            Histopathology histopathology = (Histopathology) assocMetList.get(i);

            if (histopathology.getId().toString().equals(aAssociatedMetastasisID)) {
                /* Set Histopathology attributes */
                assocMetastasisForm.setAgeOfOnset(histopathology.getAgeOfOnset());
                if (histopathology.getWeightOfTumor() != null) {
                    assocMetastasisForm.setWeightOfTumor(histopathology.getWeightOfTumor().toString());
                }
                if (histopathology.getVolumeOfTumor() != null) {
                    assocMetastasisForm.setVolumeOfTumor(histopathology.getVolumeOfTumor().toString());
                }
                if (histopathology.getTumorIncidenceRate() != null) {
                    assocMetastasisForm.setTumorIncidenceRate(histopathology.getTumorIncidenceRate().toString());
                }
                assocMetastasisForm.setSurvivalInfo(histopathology.getSurvivalInfo());
                assocMetastasisForm.setGrossDescription(histopathology.getGrossDescription());
                assocMetastasisForm.setMicroscopicDescription(histopathology.getMicroscopicDescription());

                assocMetastasisForm.setComparativeData(histopathology.getComparativeData());
                assocMetastasisForm.setComments(histopathology.getComments());

                /* set Organ attributes */
                Organ organ = histopathology.getOrgan();
                System.out.println("<AssociatedMetastasisAction populate> get the Organ attributes");

                // since we are always querying from concept code (save and
                // edit), simply display EVSPreferredDescription
                assocMetastasisForm.setOrgan(organ.getEVSPreferredDescription());
                System.out.println("setOrgan= " + organ.getEVSPreferredDescription());

                assocMetastasisForm.setOrganTissueCode(organ.getConceptCode());
                System.out.println("OrganTissueCode= " + organ.getConceptCode());

                /* Set Disease object attributes */
                List diseaseList = histopathology.getDiseaseCollection();

                Disease disease = (Disease) diseaseList.get(0);
                assocMetastasisForm.setDiagnosisName(disease.getName());
                assocMetastasisForm.setDiagnosisCode(disease.getConceptCode());
                assocMetastasisForm.setTumorClassification(disease.getName());

                /* Set GeneticAlteration attributes */
                assocMetastasisForm.setObservation(histopathology.getGeneticAlteration().getObservation());
                assocMetastasisForm.setMethodOfObservation(histopathology.getGeneticAlteration()
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
