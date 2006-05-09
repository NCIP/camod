/**
 * 
 * @author pandyas
 * 
 * $Id: MorpholinoPopulateAction.java,v 1.2 2006-05-09 18:56:58 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2006/05/03 20:05:11  pandyas
 * Modified to add Morpholino object data to application
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Morpholino;
import gov.nih.nci.camod.service.MorpholinoManager;
import gov.nih.nci.camod.webapp.form.MorpholinoForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class MorpholinoPopulateAction extends BaseAction
{

    /** 
     * Pre-populate all field values in the form <FormName> 
     *  Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {
        log.debug("<MorpholinoPopulateAction populate> Entered");

        // Create a form to edit		
        MorpholinoForm morpholinoForm = (MorpholinoForm) form;

        // Grab the current Morpholino we are working with related to this animalModel        
        String aMorpholinoID = request.getParameter("aMorpholinoID");
        System.out.println("aMorpholinoID: = " + aMorpholinoID);

        MorpholinoManager morpholinoManager = (MorpholinoManager) getBean("morpholinoManager");
        Morpholino morpholino = morpholinoManager.get(aMorpholinoID);

        if (morpholino == null)
        {
            request.setAttribute(Constants.Parameters.DELETED, "true");
        }
        else
        {
            request.setAttribute("aMorpholinoID", aMorpholinoID);

            // Set the fields from database
            if (morpholino.getSourceUnctrVocab() != null)
            {
                morpholinoForm.setSource(Constants.Dropdowns.OTHER_OPTION);
                morpholinoForm.setOtherSource(morpholino.getSourceUnctrVocab());
            }
            else
            {
                morpholinoForm.setSource(morpholino.getSource());
            }

            morpholinoForm.setType(morpholino.getType());
            morpholinoForm.setSequenceDirection(morpholino.getSequenceDirection());
            morpholinoForm.setTargetedRegion(morpholino.getTargetedRegion());

            morpholinoForm.setConcentration(morpholino.getConcentration());
            morpholinoForm.setConcentrationUnit(morpholino.getConcentrationUnit());

            if (morpholino.getDeliveryMethodUnctrlVocab() != null)
            {
                morpholinoForm.setDeliveryMethod(Constants.Dropdowns.OTHER_OPTION);
                morpholinoForm.setOtherDeliveryMethod(morpholino.getDeliveryMethodUnctrlVocab());
            }
            else
            {
                morpholinoForm.setDeliveryMethod(morpholino.getDeliveryMethod());
            }

            if (morpholino.getVisualLigandUnctrlVocab() != null)
            {
                morpholinoForm.setVisualLigand(Constants.Dropdowns.OTHER_OPTION);
                morpholinoForm.setOtherVisualLigand(morpholino.getVisualLigandUnctrlVocab());
            }
            else
            {
                morpholinoForm.setVisualLigand(morpholino.getVisualLigand());
            }
        }

        // Prepopulate all dropdown fields, set the global Constants to the following
        this.dropdown(request, response);

        return mapping.findForward("submitMorpholino");
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
    public ActionForward dropdown(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {
        log.debug("<ClinicalMarkerPopulateAction dropdown> Entering ActionForward dropdown()");

        //blank out the FORMDATA Constant field
        MorpholinoForm morpholinoForm = (MorpholinoForm) form;

        request.getSession().setAttribute(Constants.FORMDATA, morpholinoForm);

        //setup dropdown menus
        this.dropdown(request, response);

        log.debug("<MorpholinoPopulateAction dropdown> Exiting ActionForward dropdown()");

        return mapping.findForward("submitMorpholino");
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
        log.debug("<MorpholinoPopulateAction dropdown> Entering void dropdown()");

        //Prepopulate all dropdown fields, set the global Constants to the following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.MORPHOSOURCEDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CONCENTRATIONUNITSDROP, Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.MORPHOTYPEDROP, Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEQUENCEDIRECTIONSDROP, Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.DELIVERYMETHODSOURCEDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.VISUALLIGANDSDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);

        log.debug("<MorpholinoPopulateAction dropdown> Exiting void dropdown()");
    }
}
