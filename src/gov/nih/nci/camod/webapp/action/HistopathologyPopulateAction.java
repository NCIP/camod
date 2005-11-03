/**
 * 
 * @author pandyas
 * 
 * $Id: HistopathologyPopulateAction.java,v 1.4 2005-11-03 22:29:12 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/11/03 21:48:07  georgeda
 * Cleanup
 *
 * Revision 1.2  2005/11/03 18:54:10  pandyas
 * Modified for histopathology screens
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Disease;
import gov.nih.nci.camod.domain.Histopathology;
import gov.nih.nci.camod.service.impl.HistopathologyManagerSingleton;
import gov.nih.nci.camod.webapp.form.HistopathologyForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class HistopathologyPopulateAction extends BaseAction {

    /**
     * Pre-populate all field values in the form <FormName> Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<HistopathologyPopulateAction populate> Entered");

        HistopathologyForm histopathologyForm = (HistopathologyForm) form;

        // Grab the current aHistopathID from the session
        String aHistopatholgyID = request.getParameter("aHistopathologyID");
        System.out.println("aHistopatholgyID: " + aHistopatholgyID);

        Histopathology theHistopathology = HistopathologyManagerSingleton.instance().get(aHistopatholgyID);

        if (theHistopathology == null) {
            request.setAttribute(Constants.Parameters.DELETED, "true");
        } else {
            request.setAttribute("aHistopathologyID", aHistopatholgyID);

            // Prepopulate all dropdown fields, set the global Constants to the
            // following
            this.dropdown(request, response);

            /* set Organ attributes */
            System.out.println("<HistopathologyPopulateAction populate> get the Organ attributes");

            // since we are always querying from concept code (save and edit),
            // simply display EVSPreferredDescription
            histopathologyForm.setOrgan(theHistopathology.getOrgan().getEVSPreferredDescription());
            System.out.println("setOrgan= " + theHistopathology.getOrgan().getEVSPreferredDescription());

            histopathologyForm.setOrganTissueCode(theHistopathology.getOrgan().getConceptCode());
            System.out.println("OrganTissueCode= " + theHistopathology.getOrgan().getConceptCode());

            /* Set Disease object attributes */
            List diseaseList = theHistopathology.getDiseaseCollection();

            Disease disease = (Disease) diseaseList.get(0);
            histopathologyForm.setDiagnosisName(disease.getName());
            histopathologyForm.setDiagnosisCode(disease.getConceptCode());
            histopathologyForm.setTumorClassification(disease.getName());

            /* Set Histopathology attributes */
            histopathologyForm.setAgeOfOnset(theHistopathology.getAgeOfOnset());
            if (theHistopathology.getWeightOfTumor() != null) {
                histopathologyForm.setWeightOfTumor(theHistopathology.getWeightOfTumor().toString());
            }
            if (theHistopathology.getVolumeOfTumor() != null) {
                histopathologyForm.setVolumeOfTumor(theHistopathology.getVolumeOfTumor().toString());
            }
            if (theHistopathology.getTumorIncidenceRate() != null) {
                histopathologyForm.setTumorIncidenceRate(theHistopathology.getTumorIncidenceRate().toString());
            }
            histopathologyForm.setSurvivalInfo(theHistopathology.getSurvivalInfo());
            histopathologyForm.setGrossDescription(theHistopathology.getGrossDescription());
            histopathologyForm.setMicroscopicDescription(theHistopathology.getMicroscopicDescription());

            histopathologyForm.setComparativeData(theHistopathology.getComparativeData());
            histopathologyForm.setComments(theHistopathology.getComments());

            /* Set GeneticAlteration attributes */
            histopathologyForm.setObservation(theHistopathology.getGeneticAlteration().getObservation());
            histopathologyForm
                    .setMethodOfObservation(theHistopathology.getGeneticAlteration().getMethodOfObservation());
        }
        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        this.dropdown(request, response);

        return mapping.findForward("submitHistopathology");
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

        System.out.println("<HistopathologyPopulateAction dropdown> Entering ActionForward dropdown()");

        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitHistopathology");
    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("<HistopathologyPopulateAction dropdown> Entering void dropdown()");

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");

    }
}
