package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Conditionality;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.ModificationType;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.TargetedModification;
import gov.nih.nci.camod.service.impl.TargetedModificationManagerSingleton;
import gov.nih.nci.camod.webapp.form.TargetedModificationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TargetedModificationPopulateAction extends BaseAction {

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<TargetedModificationPopulateAction populate> Entering populate() ");

        TargetedModificationForm targetedModificationForm = (TargetedModificationForm) form;

        String aTargetedModificationID = request.getParameter("aTargetedModificationID");
        TargetedModification theTargetedModification = TargetedModificationManagerSingleton.instance().get(
                aTargetedModificationID);

        if (theTargetedModification == null) {
            request.setAttribute("deleted", "true");
        } else {
            targetedModificationForm.setModificationId(aTargetedModificationID);

            targetedModificationForm.setName(theTargetedModification.getName());
            targetedModificationForm.setBlastocystName(theTargetedModification.getBlastocystName());
            targetedModificationForm.setComments(theTargetedModification.getComments());
            targetedModificationForm.setGeneId(theTargetedModification.getGeneId());
            targetedModificationForm.setEsCellLineName(theTargetedModification.getEsCellLineName());

            Conditionality cond = theTargetedModification.getConditionality();
            targetedModificationForm.setConditionedBy(cond.getConditionedBy());
            targetedModificationForm.setDescription(cond.getDescription());

            Image image = theTargetedModification.getImage();
            if (image != null) {
                targetedModificationForm.setFileServerLocation(image.getFileServerLocation());
                targetedModificationForm.setTitle(image.getTitle());
                targetedModificationForm.setDescriptionOfConstruct(image.getDescription());
            }

            List modTypeList = theTargetedModification.getModificationTypeCollection();

            if (modTypeList.size() > 0) {
                ModificationType modType = (ModificationType) modTypeList.get(0);
                targetedModificationForm.setModificationType(modType.getName());
            } else {
                targetedModificationForm.setModificationType(Constants.Dropdowns.OTHER_OPTION);
            }
            targetedModificationForm.setOtherModificationType(theTargetedModification.getModTypeUnctrlVocab());

            MutationIdentifier theMutationIdentifier = theTargetedModification.getMutationIdentifier();

            if (theMutationIdentifier != null)
                targetedModificationForm.setNumberMGI(theMutationIdentifier.getNumberMGI().toString());

        }

        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitTargetedModification");
    }

    public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<TargetedModificationPopulateAction dropdown> Entering dropdown()");

        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitTargetedModification");
    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("<TargetedModificationPopulateAction dropdown> Entering void dropdown()");

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.TARGETEDMODIFICATIONDROP, "");

        System.out.println("<TargetedModificationPopulateAction dropdown> Exiting void dropdown()");
    }
}