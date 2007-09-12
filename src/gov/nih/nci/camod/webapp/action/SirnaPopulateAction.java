/**
 * 
 * @author pandyas
 * 
 * $Id: SirnaPopulateAction.java,v 1.3 2007-09-12 19:36:39 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.2  2006/10/23 14:17:58  pandyas
 * changed to conform to conceptCode format in all other classes
 *
 * Revision 1.1  2006/10/17 16:11:00  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.TransientInterference;
import gov.nih.nci.camod.service.impl.TransientInterferenceManagerSingleton;
import gov.nih.nci.camod.webapp.form.TransientInterferenceForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SirnaPopulateAction extends BaseAction
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
        log.debug("<SirnaPopulateAction populate> Entered");

        // Create a form to edit		
        TransientInterferenceForm transientInterferenceForm = (TransientInterferenceForm) form;

        // Grab the current TransientInterferece we are working with related to this animalModel        
        String aTransIntID = request.getParameter("aTransIntID");
        System.out.println("aTransIntID: = " + aTransIntID);

        TransientInterference transientInterference = TransientInterferenceManagerSingleton.instance().get(aTransIntID);

		// Handle back arrow in browser        
        if (transientInterference == null)
        {
            request.setAttribute(Constants.Parameters.DELETED, "true");
        }
        else
        {
            request.setAttribute("aTransIntID", aTransIntID);

            // Populate the fields from database
            if (transientInterference.getSourceUnctrVocab() != null)
            {
            	transientInterferenceForm.setSource(Constants.Dropdowns.OTHER_OPTION);
            	transientInterferenceForm.setOtherSource(transientInterference.getSourceUnctrVocab());
            }
            else
            {
            	transientInterferenceForm.setSource(transientInterference.getSource());
            }

            transientInterferenceForm.setType(transientInterference.getType());
            transientInterferenceForm.setSequenceDirection(transientInterference.getSequenceDirection());
            transientInterferenceForm.setTargetedRegion(transientInterference.getTargetedRegion());

            transientInterferenceForm.setConcentration(transientInterference.getConcentration());
            transientInterferenceForm.setConcentrationUnit(transientInterference.getConcentrationUnit());
            transientInterferenceForm.setComments(transientInterference.getComments());            

            if (transientInterference.getDeliveryMethodUnctrlVocab() != null)
            {
            	transientInterferenceForm.setDeliveryMethod(Constants.Dropdowns.OTHER_OPTION);
            	transientInterferenceForm.setOtherDeliveryMethod(transientInterference.getDeliveryMethodUnctrlVocab());
            }
            else
            {
            	transientInterferenceForm.setDeliveryMethod(transientInterference.getDeliveryMethod());
            }

            if (transientInterference.getVisualLigandUnctrlVocab() != null)
            {
            	transientInterferenceForm.setVisualLigand(Constants.Dropdowns.OTHER_OPTION);
            	transientInterferenceForm.setOtherVisualLigand(transientInterference.getVisualLigandUnctrlVocab());
            }
            else
            {
            	transientInterferenceForm.setVisualLigand(transientInterference.getVisualLigand());
            }
        }

        // Prepopulate all dropdown fields, set the global Constants to the following
        this.dropdown(request, response);

        return mapping.findForward("submitSirna");
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
        log.debug("<SirnaPopulateAction dropdown> Entering ActionForward dropdown()");

		String conceptCode = request.getParameter("aConceptCode");
		// Create a form to edit
        TransientInterferenceForm transientInterferenceForm = (TransientInterferenceForm) form;

		transientInterferenceForm.setConceptCode(conceptCode);

        //setup dropdown menus
        this.dropdown(request, response);

        log.debug("<SirnaPopulateAction dropdown> Exiting ActionForward dropdown()");

        return mapping.findForward("submitSirna");
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
        log.debug("<SirnaPopulateAction dropdown> Entering void dropdown()");

        //Prepopulate all dropdown fields, set the global Constants to the following
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SIRNASOURCEDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
    	NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.CONCENTRATIONUNITSDROP, Constants.Dropdowns.ADD_BLANK);

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SIRNATYPEDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEQUENCEDIRECTIONSDROP, Constants.Dropdowns.ADD_BLANK);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SIRNADELIVMETHODDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SIRNAVISUALLIGANDSDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);

        log.debug("<SirnaPopulateAction dropdown> Exiting void dropdown()");
    }

}
