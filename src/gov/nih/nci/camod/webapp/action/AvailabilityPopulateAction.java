package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalAvailability;
import gov.nih.nci.camod.service.impl.AvailabilityManagerSingleton;
import gov.nih.nci.camod.webapp.form.AvailabilityForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AvailabilityPopulateAction extends BaseAction {

    /**
     * Pre-populate all field values in the form <FormName> Used by <jspName>
     * 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
        System.out.println("<AvailabilityPopulateAction populate> Entering ");    	

        // Create a form to edit
        AvailabilityForm availabilityForm = (AvailabilityForm) form;

        // Grab the current Availability we are working with related to this animalModel
        String aAvailabilityID = request.getParameter("aAvailabilityID");
        AnimalAvailability avilablity = AvailabilityManagerSingleton.instance().get(aAvailabilityID);

        availabilityForm.setName(avilablity.getName());        
        availabilityForm.setStockNumber(avilablity.getStockNumber());        
        
        // Store the Form in session to be used by the JSP
        request.getSession().setAttribute(Constants.FORMDATA, availabilityForm);
        
        System.out.println("<AvailabilityPopulateAction populate> Exiting ");         

        return mapping.findForward("next");        
    }
	
    /**
     * Populate the dropdown menus for 4 submission screens for Animal Availability 
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
    	
        System.out.println("<AvailabilityPopulateAction ActionForward dropdown> Entering ");
        
		//blank out the FORMDATA Constant field
        AvailabilityForm availabilityForm = (AvailabilityForm) form;
		request.getSession().setAttribute( Constants.FORMDATA, availabilityForm );        

        // setup dropdown menus
        this.dropdown(request, response);
        
        System.out.println("<AvailabilityPopulateAction ActionForward dropdown> Exiting ");

        return mapping.findForward("next");

    }
    
    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("<AvailabilityPopulateAction dropdown> Entering ");

        // Prepopulate dropdown field on submitInvestigator screen only
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PRINCIPALINVESTIGATORDROP, "");
    }    
    
}
