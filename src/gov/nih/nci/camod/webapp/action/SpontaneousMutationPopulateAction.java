/**
 * 
 * $Id: SpontaneousMutationPopulateAction.java,v 1.8 2006-04-19 17:38:57 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.GeneticAlteration;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.SpontaneousMutation;
import gov.nih.nci.camod.service.impl.SpontaneousMutationManagerSingleton;
import gov.nih.nci.camod.webapp.form.SpontaneousMutationForm;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SpontaneousMutationPopulateAction extends BaseAction {

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<SpontaneousMutationPopulateAction populate> Entering populate() ");
        SpontaneousMutationForm spontaneousMutationForm = (SpontaneousMutationForm) form;

        String aSpontaneousMutationID = request.getParameter("aSpontaneousMutationID");
        SpontaneousMutation theSpontaneousMutation = SpontaneousMutationManagerSingleton.instance().get(
                aSpontaneousMutationID);

        if (theSpontaneousMutation == null) {
        	request.setAttribute(Constants.Parameters.DELETED, "true");
        } else {

            request.setAttribute("aSpontaneousMutationID", aSpontaneousMutationID);
            spontaneousMutationForm.setName(theSpontaneousMutation.getName());
            spontaneousMutationForm.setComments(theSpontaneousMutation.getComments());
            spontaneousMutationForm.setGeneId(theSpontaneousMutation.getGeneId());

            List geneticList = new ArrayList(theSpontaneousMutation.getGeneticAlterationCollection());
            if (geneticList.size() > 0) {
                GeneticAlteration theGeneticAlteration = (GeneticAlteration) geneticList.get(0);
                spontaneousMutationForm.setObservation(theGeneticAlteration.getObservation());
                spontaneousMutationForm.setMethodOfObservation(theGeneticAlteration.getMethodOfObservation());
            }

            MutationIdentifier inMutationIdentifier = theSpontaneousMutation.getMutationIdentifier();
            if (inMutationIdentifier != null)
                spontaneousMutationForm.setMgiNumber(inMutationIdentifier.getMgiNumber());

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
