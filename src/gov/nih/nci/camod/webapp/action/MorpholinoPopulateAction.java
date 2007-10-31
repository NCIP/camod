/**
 * 
 * @author pandyas
 * 
 * $Id: MorpholinoPopulateAction.java,v 1.9 2007-10-31 18:12:33 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2007/09/12 19:36:40  pandyas
 * modified debug statements for build to stage tier
 *
 * Revision 1.7  2007/04/04 13:19:27  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.6  2007/03/26 12:02:31  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.5  2006/10/23 14:18:00  pandyas
 * changed to conform to conceptCode format in all other classes
 *
 * Revision 1.4  2006/10/17 16:11:00  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.2  2006/05/09 18:56:58  georgeda
 * Changes for searching on transient interfaces
 *
 * Revision 1.1  2006/05/03 20:05:11  pandyas
 * Modified to add Morpholino object data to application
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
        TransientInterferenceForm transientInterferenceForm = (TransientInterferenceForm) form;
        
        // Grab the current TransientInterference we are working with related to this animalModel        
        String aTransIntID = request.getParameter("aTransIntID");
        log.debug("aTransIntID: = " + aTransIntID);

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
            if (transientInterference.getSourceAlternEntry() != null)
            {
            	transientInterferenceForm.setSource(Constants.Dropdowns.OTHER_OPTION);
            	transientInterferenceForm.setOtherSource(transientInterference.getSourceAlternEntry());
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
            
            if (transientInterference.getDeliveryMethodAlternEntry() != null)
            {
            	transientInterferenceForm.setDeliveryMethod(Constants.Dropdowns.OTHER_OPTION);
            	transientInterferenceForm.setOtherDeliveryMethod(transientInterference.getDeliveryMethodAlternEntry());
            }
            else
            {
            	transientInterferenceForm.setDeliveryMethod(transientInterference.getDeliveryMethod());
            }

            if (transientInterference.getVisualLigandAlternEntry() != null)
            {
            	transientInterferenceForm.setVisualLigand(Constants.Dropdowns.OTHER_OPTION);
            	transientInterferenceForm.setOtherVisualLigand(transientInterference.getVisualLigandAlternEntry());
            }
            else
            {
            	transientInterferenceForm.setVisualLigand(transientInterference.getVisualLigand());
            }
            // Populate targetSite - for Morpholino only - will be null for siRNA or if not entered for Morpholino
            if (transientInterference.getTargetSite() != null) {
            	transientInterferenceForm.setTargetSite(transientInterference.getTargetSite());
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
        log.debug("<MorpholinoPopulateAction dropdown> Entering ActionForward dropdown()");
        
		String aConceptCode = request.getParameter("aConceptCode");
		// Create a form to edit
        TransientInterferenceForm transientInterferenceForm = (TransientInterferenceForm) form;

        
		transientInterferenceForm.setConceptCode(aConceptCode);
		log.debug("<MorpholinoPopulateAction> morpholinoForm.getConceptCode: " + transientInterferenceForm.getConceptCode());

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
    	NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.MORPHOTYPEDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
    	NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.SEQUENCEDIRECTIONSDROP, Constants.Dropdowns.ADD_BLANK);
    	NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.DELIVERYMETHODDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
    	NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.VISUALLIGANDSDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
    	NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.MORPHOLINOTARGETSITE, Constants.Dropdowns.ADD_BLANK);

        log.debug("<MorpholinoPopulateAction dropdown> Exiting void dropdown()");
    }

}
