/**
 * 
 * $Id: PublicationAction.java,v 1.9 2005-10-28 14:50:55 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2005/10/28 12:47:26  georgeda
 * Added delete functionality
 *
 * Revision 1.7  2005/10/27 12:53:00  georgeda
 * Refactor of publication manager
 *
 * Revision 1.6  2005/10/26 20:12:43  pandyas
 * clean up, java docs
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.PublicationManager;
import gov.nih.nci.camod.webapp.form.PublicationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

/**
 * PublicationAction Class
 */
public final class PublicationAction extends BaseAction {

    /**
     * Edit
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.debug("Entering PublicationAction.edit");

        // Create a form to edit
        PublicationForm pubForm = (PublicationForm) form;

        log.debug("<PublicationAction save> following Characteristics:" + "\n\t name: " + pubForm.getName()
                + "\n\t Aurthur: " + pubForm.getAuthors() + "\n\t Year: " + pubForm.getYear() + "\n\t Volume: "
                + pubForm.getVolume() + "\n\t PMID: " + pubForm.getPmid() + "\n\t Start Page: "
                + pubForm.getStartPage() + "\n\t End Page: " + pubForm.getEndPage() + "\n\t Title: "
                + pubForm.getTitle() + "\n\t journal: " + pubForm.getJournal() + "\n\t FirstTimeReported: "
                + pubForm.getFirstTimeReported() + "\n\t user: "
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        // Grab the current Therapy we are working with related to this
        // animalModel
        String aPubID = request.getParameter("aPubID");

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);
        
        try {
            PublicationManager publicationManager = (PublicationManager) getBean("publicationManager");
            if ("Delete".equals(theAction)) {
                publicationManager.remove(aPubID);
                
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("publication.delete.successful"));
                saveErrors(request, msg);
                
            } else {

                Publication thePublication = publicationManager.get(aPubID);
                publicationManager.update(pubForm, thePublication);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("publication.edit.successful"));
                saveErrors(request, msg);
            }
        } catch (Exception e) {

            log.error("Unable to update a publication: ", e);

            ActionMessages theMsg = new ActionMessages();
            theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, theMsg);
        }

        return mapping.findForward("AnimalModelTreePopulateAction");
    }

    /**
     * Called from submitEnvironmentalFactors.jsp
     * 
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.debug("Entering 'save' method");

        PublicationForm pubForm = (PublicationForm) form;

        log.debug("<PublicationAction save> following Characteristics:" + "\n\t name: " + pubForm.getName()
                + "\n\t Aurthur: " + pubForm.getAuthors() + "\n\t Year: " + pubForm.getYear() + "\n\t Volume: "
                + pubForm.getVolume() + "\n\t PMID: " + pubForm.getPmid() + "\n\t Start Page: "
                + pubForm.getStartPage() + "\n\t End Page: " + pubForm.getEndPage() + "\n\t Title: "
                + pubForm.getTitle() + "\n\t journal: " + pubForm.getJournal() + "\n\t FirstTimeReported: "
                + pubForm.getFirstTimeReported() + "\n\t user: "
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        /* Grab the current modelID from the session */
        String modelID = (String) request.getSession().getAttribute(Constants.MODELID);

        try {
            AnimalModelManager animalModelManager = (AnimalModelManager) getBean("animalModelManager");

            AnimalModel animalModel = animalModelManager.get(modelID);

            animalModelManager.addPublication(animalModel, pubForm);

            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("publication.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {

            log.error("Unable to add a publication: ", e);

            ActionMessages theMsg = new ActionMessages();
            theMsg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, theMsg);
        }

        return mapping.findForward("AnimalModelTreePopulateAction");
    }
}