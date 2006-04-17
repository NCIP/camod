/**
 * 
 * $Id: ChemicalDrugPopulateAction.java,v 1.16 2006-04-17 19:09:40 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.15  2005/11/02 21:47:33  georgeda
 * Fixed validate
 *
 * Revision 1.14  2005/10/31 13:46:28  georgeda
 * Updates to handle back arrow
 *
 * Revision 1.13  2005/10/28 17:44:19  georgeda
 * Fixed sex distribution error
 *
 * Revision 1.12  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.11  2005/10/27 19:25:06  georgeda
 * Validation changes
 *
 * Revision 1.10  2005/10/20 20:37:29  pandyas
 * added javadocs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.CarcinogenExposure;
import gov.nih.nci.camod.service.CarcinogenExposureManager;
import gov.nih.nci.camod.webapp.form.ChemicalDrugForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ChemicalDrugPopulateAction extends BaseAction
{

    /**
     * Pre-populate all field values in the form ChemicalDrug Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {

        System.out.println("<ChemicalDrugPopulateAction populate> Entering ");

        ChemicalDrugForm chemicalDrugForm = (ChemicalDrugForm) form;

        String aCarcinogenExposureID = request.getParameter("aCarcinogenExposureID");

        CarcinogenExposureManager carcinogenExposureManager = (CarcinogenExposureManager) getBean("carcinogenExposureManager");
        CarcinogenExposure ce = carcinogenExposureManager.get(aCarcinogenExposureID);

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        this.dropdown(request, response);

        if (ce == null)
        {
            request.setAttribute("deleted", "true");
        }
        else
        {
            request.setAttribute("aCarcinogenExposureID", aCarcinogenExposureID);

            if (ce.getTreatment().getSexDistribution() != null)
            {
                chemicalDrugForm.setType(ce.getTreatment().getSexDistribution().getType());
            }
            if (ce.getTreatment().getAgeAtTreatment() != null)
            {
                chemicalDrugForm.setAgeAtTreatment(ce.getTreatment().getAgeAtTreatment());
                chemicalDrugForm.setAgeAtTreatmentUnit(ce.getTreatment().getAgeAtTreatmentUnit());                
            }

            // Set the other name and/or selected name from database
            if (ce.getEnvironmentalFactor().getNameUnctrlVocab() != null)
            {
                chemicalDrugForm.setName(Constants.Dropdowns.OTHER_OPTION);
                chemicalDrugForm.setOtherName(ce.getEnvironmentalFactor().getNameUnctrlVocab());
            }
            else
            {
                chemicalDrugForm.setName(ce.getEnvironmentalFactor().getName());
            }

            // Set the other flag or the selected administrative route from
            // database
            if (ce.getTreatment().getAdminRouteUnctrlVocab() != null)
            {
                chemicalDrugForm.setAdministrativeRoute(Constants.Dropdowns.OTHER_OPTION);
                chemicalDrugForm.setOtherAdministrativeRoute(ce.getTreatment().getAdminRouteUnctrlVocab());
            }
            else
            {
                chemicalDrugForm.setAdministrativeRoute(ce.getTreatment().getAdministrativeRoute());
            }

            chemicalDrugForm.setDosage(ce.getTreatment().getDosage());
            chemicalDrugForm.setDosageUnit(ce.getTreatment().getDosageUnit());
            chemicalDrugForm.setRegimen(ce.getTreatment().getRegimen());
            chemicalDrugForm.setCasNumber(ce.getEnvironmentalFactor().getCasNumber());

            if (ce.getEnvironmentalFactor().getNscNumber() != null)
            {
                chemicalDrugForm.setNscNumber(ce.getEnvironmentalFactor().getNscNumber().toString());
            }
        }
        return mapping.findForward("submitChemicalDrug");
    }

    /**
     * Populate the dropdown menus for submitSurgeryOther
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward dropdown(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {

        System.out.println("<ChemicalDrugPopulateAction dropdown> ... ");

        // setup dropdown menus
        this.dropdown(request, response);

        System.out.println("<ChemicalDrugPopulateAction> exiting... ");

        return mapping.findForward("submitChemicalDrug");
    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request,
                         HttpServletResponse response) throws Exception
    {

        System.out.println("<ChemicalDrugPopulateAction dropdown> Entering... ");

        // Prepopulate all dropdown fields, set the global Constants to the
        // following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEXDISTRIBUTIONDROP, Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.AGEUNITSDROP, "");
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CHEMICALDRUGDROP, Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CHEMTHERAPYDOSEUNITSDROP, "");

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.ADMINISTRATIVEROUTEDROP, Constants.Dropdowns.ADD_BLANK);

        System.out.println("<ChemicalDrugPopulateAction> Finishing dropdown");
    }

}
