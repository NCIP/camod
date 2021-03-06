/*L
 *  Copyright SAIC
 *  Copyright SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/camod/LICENSE.txt for details.
 */

/**
 * 
 * $Id: AssociatedMetastasisPopulateAction.java,v 1.18 2008-08-14 16:46:47 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.17  2007/10/31 17:08:48  pandyas
 * Modified commetns for #8188 	Rename UnctrlVocab items to text entries
 *
 * Revision 1.16  2007/09/12 19:36:40  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.15  2007/08/14 17:06:17  pandyas
 * Bug #8414:  getEVSPreferredDiscription needs to be implemented for Zebrafish vocabulary source
 *
 * Revision 1.14  2007/06/21 20:48:18  pandyas
 * The method getOrgan().getEVSPrefferedName does not work for Zebrafish - using getOrgan().getName(); until fixed
 *
 * Revision 1.13  2007/06/13 12:10:15  pandyas
 * Modified for save of organ/diagnosis for each tree options
 *
 * Revision 1.12  2007/05/17 18:42:25  pandyas
 * Fixed diagnosis populate when selecting other
 *
 * Revision 1.11  2007/04/30 20:10:17  pandyas
 * Implemented species specific vocabulary trees from EVSTree
 *
 * Revision 1.10  2006/11/09 17:22:17  pandyas
 * Commented out debug code
 *
 * Revision 1.9  2006/11/08 18:05:56  pandyas
 * Modified TumorIncidenceRate float to String (weight of tumor and volume of tumor also needed modified to delete properly)
 *
 * Revision 1.8  2006/11/08 17:18:06  pandyas
 * fixed typo in error message
 *
 * Revision 1.7  2006/09/12 15:09:53  georgeda
 * Modified setTumorClassification so it displayed the disease.getEVSPreferredDescription() results instead of disease.getName
 *
 * Revision 1.6  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Disease;
import gov.nih.nci.camod.domain.Histopathology;
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
            request.setAttribute("aHistopathologyID", aHistopathologyID);

            /* Set Histopathology attributes */
            assocMetastasisForm.setAgeOfOnset(associatedMetastasis.getAgeOfOnset());
            assocMetastasisForm.setAgeOfOnsetUnit(associatedMetastasis.getAgeOfOnsetUnit());
            
            assocMetastasisForm.setAgeOfDetection(associatedMetastasis.getAgeOfDetection());
            assocMetastasisForm.setAgeOfDetectionUnit(associatedMetastasis.getAgeOfDetectionUnit());            
            
            assocMetastasisForm.setWeightOfTumor(associatedMetastasis.getWeightOfTumor());
            assocMetastasisForm.setVolumeOfTumor(associatedMetastasis.getVolumeOfTumor());
            assocMetastasisForm.setTumorIncidenceRate(associatedMetastasis.getTumorIncidenceRate());

            assocMetastasisForm.setSurvivalInfo(associatedMetastasis.getSurvivalInfo());
            assocMetastasisForm.setGrossDescription(associatedMetastasis.getGrossDescription());
            assocMetastasisForm.setMicroscopicDescription(associatedMetastasis.getMicroscopicDescription());

            assocMetastasisForm.setComparativeData(associatedMetastasis.getComparativeData());
            assocMetastasisForm.setComments(associatedMetastasis.getComments());

            /* set Organ attributes */
            log.debug("<HistopathologyPopulateAction populate> get the Organ attributes");

            // since we are always querying from concept code (save and edit),
            // simply display organ.name since EVSPreferredDescription is too slow in LexEVS 5.x API, unless concept code is '00000'
            if (associatedMetastasis.getOrgan().equals(Constants.Dropdowns.CONCEPTCODEZEROS)) {
            	assocMetastasisForm.setOrgan(associatedMetastasis.getOrgan().getName());
	            log.info("associatedMetastasis.getOrgan().getName(): " + associatedMetastasis.getOrgan().getName());
	
	            assocMetastasisForm.setOrganTissueCode(associatedMetastasis.getOrgan().getConceptCode());
	            log.info("OrganTissueCode: " + associatedMetastasis.getOrgan().getConceptCode());            	
            	
            } else {
            	assocMetastasisForm.setOrgan(associatedMetastasis.getOrgan().getName());
	            log.info("associatedMetastasis.getOrgan().getName(): " + associatedMetastasis.getOrgan().getName());
	
	            assocMetastasisForm.setOrganTissueCode(associatedMetastasis.getOrgan().getConceptCode());
	            log.info("OrganTissueCode: " + associatedMetastasis.getOrgan().getConceptCode());
            }
            
             /* Set Disease object attributes - check for other Zebrafish entry*/
            Disease disease = associatedMetastasis.getDisease();
            if(disease.getNameAlternEntry() != null) {
                log.info("disease is other in DB");
                assocMetastasisForm.setTumorClassification(Constants.Dropdowns.OTHER_OPTION);
                assocMetastasisForm.setOtherTumorClassification(disease.getNameAlternEntry());
                
            } else {
                assocMetastasisForm.setDiagnosisName(disease.getName());
                assocMetastasisForm.setDiagnosisCode(disease.getConceptCode());
                assocMetastasisForm.setTumorClassification(disease.getEVSPreferredDescription());
            }

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

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ZEBRAFISHDIAGNOSISDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER_OPTION);        

    }

}
