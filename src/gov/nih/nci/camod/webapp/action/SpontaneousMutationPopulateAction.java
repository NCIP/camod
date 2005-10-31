package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.impl.SpontaneousMutationManagerSingleton;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationForm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class SpontaneousMutationPopulateAction extends BaseAction {

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<SpontaneousMutationPopulateAction populate> Entering populate() ");
        SpontaneousMutationForm spontaneousMutationForm = (SpontaneousMutationForm) form;

        String aSpontaneousMutationID = request.getParameter("aSpontaneousMutationID");
        SpontaneousMutation theSpontaneousMutation = SpontaneousMutationManagerSingleton.instance().get(
                aSpontaneousMutationID);

        if (theSpontaneousMutation == null) {
            request.setAttribute("aSpontaneousMutationID", null);
        } else {

            request.setAttribute("aSpontaneousMutationID", aSpontaneousMutationID);
            spontaneousMutationForm.setName(theSpontaneousMutation.getName());
            spontaneousMutationForm.setComments(theSpontaneousMutation.getComments());

            List geneticList = theSpontaneousMutation.getGeneticAlterationCollection();
            if (geneticList.size() > 0) {
                GeneticAlteration theGeneticAlteration = (GeneticAlteration) geneticList.get(0);
                spontaneousMutationForm.setObservation(theGeneticAlteration.getObservation());
                spontaneousMutationForm.setMethodOfObservation(theGeneticAlteration.getMethodOfObservation());
            }

            MutationIdentifier inMutationIdentifier = theSpontaneousMutation.getMutationIdentifier();
            if (inMutationIdentifier != null)
                spontaneousMutationForm.setNumberMGI(inMutationIdentifier.getNumberMGI().toString());

        }

        return mapping.findForward("submitSpontaneousMutation");
    }

    public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<SpontaneousMutationPopulateAction dropdown> Entering dropdown() ");

        return mapping.findForward("submitSpontaneousMutation");
    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("<SpontaneousMutationPopulateAction dropdown> Entering void dropdown()");
        System.out.println("<SpontaneousMutationPopulateAction dropdown> Exiting void dropdown()");
    }

}
