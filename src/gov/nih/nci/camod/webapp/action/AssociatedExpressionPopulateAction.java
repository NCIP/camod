/**
 * 
 * $Id: AssociatedExpressionPopulateAction.java,v 1.7 2006-11-09 17:21:25 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2006/05/10 14:15:39  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.5  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.AssociatedExpressionManager;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class AssociatedExpressionPopulateAction extends BaseAction {

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //System.out.println("<AssociatedExpressionPopulateAction populate> Entering populate() ");

        AssociatedExpressionForm associatedExpressionForm = (AssociatedExpressionForm) form;
        String aAssociatedExpressionID = request.getParameter("aAssociatedExpressionID");

        Enumeration theEnum = request.getParameterNames();
        //System.out.println("Enum: " + theEnum);
        AssociatedExpressionManager theAssociatedExpressionManager = (AssociatedExpressionManager) getBean("associatedExpressionManager");
        ExpressionFeature theExpressionFeature = theAssociatedExpressionManager.get(aAssociatedExpressionID);

        if (theExpressionFeature == null) {
            request.setAttribute(Constants.Parameters.DELETED, "true");
        } else {

            request.setAttribute("aAssociatedExpressionID", aAssociatedExpressionID);
            if (request.getParameter("aGenomicSegmentID") != null) {
                request.setAttribute("aGenomicSegmentID", request.getParameter("aGenomicSegmentID"));
            }
            if (request.getParameter("aTargetedModificationID") != null) {
                request.setAttribute("aTargetedModificationID", request.getParameter("aTargetedModificationID"));
            }
            if (request.getParameter("aEngineeredTransgeneID") != null) {
                request.setAttribute("aEngineeredTransgeneID", request.getParameter("aEngineeredTransgeneID"));
            }

            ExpressionLevelDesc expLevelDesc = theExpressionFeature.getExpressionLevelDesc();
            Organ organ = theExpressionFeature.getOrgan();

            associatedExpressionForm.setName(organ.getName());
            associatedExpressionForm.setOrgan(organ.getName());

            if (expLevelDesc != null)
                associatedExpressionForm.setExpressionLevel(expLevelDesc.getExpressionLevel());

            associatedExpressionForm.setEngineeredGeneID(aAssociatedExpressionID);
            associatedExpressionForm.setOrganTissueCode(organ.getConceptCode());
            associatedExpressionForm.setOrganTissueName(organ.getName());
        }

        // setup dropdown menus
        this.dropdown(request, response);

        request.getSession().setAttribute(Constants.FORMDATA, associatedExpressionForm);
        //System.out.println("<AssociatedExpressionPopulateAction populate> Exiting populate() ");
        return mapping.findForward("submitAssocExpression");
    }

    public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //System.out.println("<AssociatedExpressionPopulateAction dropdown> Entering dropdown()");

        // blank out the FORMDATA Constant field
        AssociatedExpressionForm AssociatedExpressionForm = (AssociatedExpressionForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, AssociatedExpressionForm);

        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitAssocExpression");
    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //System.out.println("<AssociatedExpressionPopulateAction dropdown> Entering void dropdown()");

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.EXPRESSIONLEVELDROP, Constants.Dropdowns.ADD_BLANK);

        //System.out.println("<AssociatedExpressionPopulateAction dropdown> Exiting void dropdown()");
    }
}
