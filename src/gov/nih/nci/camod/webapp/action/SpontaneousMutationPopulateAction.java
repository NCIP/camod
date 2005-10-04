package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SpontaneousMutationPopulateAction extends BaseAction {

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    	throws Exception {

        System.out.println("<SpontaneousMutationPopulateAction populate> Entering populate() ");

        SpontaneousMutationForm spontaneousMutationForm = (SpontaneousMutationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, spontaneousMutationForm);       

        return mapping.findForward("submitSpontaneousMutation");
    }

    public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    	throws Exception {

        System.out.println("<SpontaneousMutationPopulateAction dropdown> Entering dropdown() ");

        // blank out the FORMDATA Constant field
        SpontaneousMutationForm spontaneousMutationForm = (SpontaneousMutationForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, spontaneousMutationForm);       

       return mapping.findForward("submitSpontaneousMutation");
    } 
    
    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown( HttpServletRequest request, HttpServletResponse response ) 
    	throws Exception {

        System.out.println("<SpontaneousMutationPopulateAction dropdown> Entering void dropdown()");
        System.out.println("<SpontaneousMutationPopulateAction dropdown> Exiting void dropdown()");
    }
}
