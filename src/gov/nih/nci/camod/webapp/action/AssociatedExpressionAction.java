/**
 * 
 * $Id: AssociatedExpressionAction.java,v 1.7 2007-09-12 19:36:40 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2007/07/23 17:40:43  pandyas
 * Fixed typo in word occurred
 *
 * Revision 1.5  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.service.*;
import gov.nih.nci.camod.webapp.form.AssociatedExpressionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class AssociatedExpressionAction extends BaseAction {

    /**
     * Save
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

        log.trace("Entering edit");

        // Create a form to edit
        AssociatedExpressionForm associatedExpressionForm = (AssociatedExpressionForm) form;

        // Grab the current Engineered Transgene that is being edited from the
        // session
        String aAssociatedExpressionID = request.getParameter("aAssociatedExpressionID");
        String aEngineeredTransgeneID = request.getParameter("aEngineeredTransgeneID");
        String aGenomicSegmentID = request.getParameter("aGenomicSegmentID");
        String aTargetedModificationID = request.getParameter("aTargetedModificationID");

        log.debug("<AssocExpression edit> following Characteristics:" + "\n\t getExpressionLevel: "
                + associatedExpressionForm.getExpressionLevel() + "\n\t getName: " + associatedExpressionForm.getName()
                + "\n\t aAssociatedExpressionID: " + aAssociatedExpressionID + "\n\t getOrganTissueCode: "
                + associatedExpressionForm.getOrganTissueCode() + "\n\t getOrganTissueName: "
                + associatedExpressionForm.getOrganTissueName() + "\n\t getOrgan: "
                + associatedExpressionForm.getOrgan() + "\n\t username:"
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);
        try {
            AssociatedExpressionManager theAssociatedExpressionManager = (AssociatedExpressionManager) getBean("associatedExpressionManager");
            ExpressionFeature theExpressionFeature = theAssociatedExpressionManager.get(aAssociatedExpressionID);

            if ("Delete".equals(theAction)) {

                EngineeredGene theEngineeredGene = null;
                if (aEngineeredTransgeneID != null && aEngineeredTransgeneID.length() > 0) {
                    EngineeredTransgeneManager theEngineeredTransgeneManager = (EngineeredTransgeneManager) getBean("engineeredTransgeneManager");
                    theEngineeredGene = theEngineeredTransgeneManager.get(aEngineeredTransgeneID);
                } else if (aGenomicSegmentID != null && aGenomicSegmentID.length() > 0) {
                    GenomicSegmentManager theGenomicSegmentManager = (GenomicSegmentManager) getBean("genomicSegmentManager");
                    theEngineeredGene = theGenomicSegmentManager.get(aGenomicSegmentID);
                } else if (aTargetedModificationID != null && aTargetedModificationID.length() > 0) {
                    TargetedModificationManager theTargetedModificationManager = (TargetedModificationManager) getBean("targetedModificationManager");
                    theEngineeredGene = theTargetedModificationManager.get(aTargetedModificationID);
                }
                theAssociatedExpressionManager.remove(aAssociatedExpressionID, theEngineeredGene);
                
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("assocexpression.delete.successful"));
                saveErrors(request, msg);
                
            } else {

                theAssociatedExpressionManager.update(associatedExpressionForm, theExpressionFeature);

                log.debug("AssociatedExpression edited");

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("assocexpression.edit.successful"));
                saveErrors(request, msg);
            }

        } catch (Exception e) {
            log.error("Exception occurred creating AssociatedExpression", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting edit");
        return mapping.findForward(theForward);
    }

    /**
     * Save
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        log.trace("Entering save");

        // Create a form to save
        AssociatedExpressionForm associatedExpressionForm = (AssociatedExpressionForm) form;

        // Grab the current modelID from the session
        // String theModelId = (String)
        // request.getSession().getAttribute(Constants.MODELID);

        // Grab the current Engineered Transgene that is being edited from the
        // session
        String aEngineeredTransgeneID = request.getParameter("aEngineeredTransgeneID");
        String aGenomicSegmentID = request.getParameter("aGenomicSegmentID");
        String aTargetedModificationID = request.getParameter("aTargetedModificationID");

        log.debug("<AssocExpression save> following Characteristics:" + "\n\t getExpressionLevel: "
                + associatedExpressionForm.getExpressionLevel() + "\n\t getName: " + associatedExpressionForm.getName()
                + "\n\t aEngineeredTransgeneID: " + aEngineeredTransgeneID + "\n\t aGenomicSegmentID: "
                + aGenomicSegmentID + "\n\t aTargetedModificationID: " + aTargetedModificationID
                + "\n\t getOrganTissueCode: " + associatedExpressionForm.getOrganTissueCode()
                + "\n\t getOrganTissueName: " + associatedExpressionForm.getOrganTissueName() + "\n\t getOrgan: "
                + associatedExpressionForm.getOrgan() + "\n\t username:"
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        try {

            AssociatedExpressionManager theAssociatedExpressionManager = (AssociatedExpressionManager) getBean("associatedExpressionManager");
            ExpressionFeature theExpressionFeature = theAssociatedExpressionManager.create(associatedExpressionForm);

            if (aEngineeredTransgeneID != null) {
                EngineeredTransgeneManager theEngineeredTransgeneManager = (EngineeredTransgeneManager) getBean("engineeredTransgeneManager");
                Transgene theTransgene = theEngineeredTransgeneManager.get(aEngineeredTransgeneID);
                theTransgene.addExpressionFeature(theExpressionFeature);
                theEngineeredTransgeneManager.save(theTransgene);
                // save it

            } else if (aGenomicSegmentID != null) {
                GenomicSegmentManager theGenomicSegmentManager = (GenomicSegmentManager) getBean("genomicSegmentManager");
                GenomicSegment theGenomicSegment = theGenomicSegmentManager.get(aGenomicSegmentID);
                theGenomicSegment.addExpressionFeature(theExpressionFeature);
                theGenomicSegmentManager.save(theGenomicSegment);
                // save it
            } else if (aTargetedModificationID != null) {
                TargetedModificationManager theTargetedModificationManager = (TargetedModificationManager) getBean("targetedModificationManager");
                TargetedModification theTargetedModification = theTargetedModificationManager
                        .get(aTargetedModificationID);
                theTargetedModification.addExpressionFeature(theExpressionFeature);
                theTargetedModificationManager.save(theTargetedModification);
                // save it
            } else {
            }

            log.debug("New AssociatedExpression created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("assocexpression.creation.successful"));
            saveErrors(request, msg);

        } catch (Exception e) {
            log.error("Exception occurred creating AssociatedExpression", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward(theForward);
    }
}
