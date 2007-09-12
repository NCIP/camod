/**
 * 
 * $Id: ImageAction.java,v 1.17 2007-09-12 19:36:40 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2007/07/31 12:13:41  pandyas
 * VCDE silver level  and caMOD 2.3 changes
 *
 * Revision 1.15  2007/07/23 17:40:42  pandyas
 * Fixed typo in word occurred
 *
 * Revision 1.14  2007/04/20 17:51:14  pandyas
 * Modified to add Staining Method tree to Image submission
 *
 * Revision 1.13  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.ImageManager;
import gov.nih.nci.camod.webapp.form.ImageForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * ImageAction Class
 */
public final class ImageAction extends BaseAction {

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

        log.trace("Entering save");

        // Create a form to edit
        ImageForm imageForm = (ImageForm) form;

        // Grab the current modelID from the session
        String aImageID = imageForm.getImageId();

        log.debug("<ImageAction save> following Characteristics:" + "\n\t getUrl: "
                + imageForm.getUrl() + "\n\t getTitle: " + imageForm.getTitle()
                + "\n\t getDescription: " + imageForm.getDescription()
                + "\n\t getStainingMethodName() : " + imageForm.getStainingMethodName()                
                + "\n\t getStainingMethodCode(): " + imageForm.getStainingMethodCode()  + "\n" 
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        String theAction = (String) request.getParameter(Constants.Parameters.ACTION);

        try {
            ImageManager imageManager = (ImageManager) getBean("imageManager");

            if ("Delete".equals(theAction)) {
                
                // Grab the current modelID from the session
                String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

                AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
                AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);
                
                imageManager.remove(aImageID, theAnimalModel);

                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("image.delete.successful"));
                saveErrors(request, msg);

            } else {
                log.debug("Image edit");

                // retrieve model and update w/ new values

                Image theImage = imageManager.get(aImageID);

                String inPath = request.getSession().getServletContext().getRealPath("/config/temp.jpg");

                // Grab the current modelID from the session
                String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);
                AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
                AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

                imageManager.update(theAnimalModel, imageForm, theImage, inPath,
                        Constants.CaImage.FTPMODELSTORAGEDIRECTORY);

                log.debug("New Image created");

                // Add a message to be displayed in submitOverview.jsp saying
                // you've
                // created a new model successfully
                ActionMessages msg = new ActionMessages();
                msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("image.edit.successful"));
                saveErrors(request, msg);
            }
        } 
        catch (IllegalArgumentException e) {
            
            theForward = "input";
            log.error("Exception occurred creating Image", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }
        catch (Exception e) {
            log.error("Exception occurred creating Image", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
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

        // Create a form to edit
        ImageForm imageForm = (ImageForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, imageForm);

        // Grab the current modelID from the session
        String theModelId = (String) request.getSession().getAttribute(Constants.MODELID);

        log.debug("<ImageAction save> following Characteristics:" + "\n\t getUrl: "
                + imageForm.getUrl() + "\n\t getTitle: " + imageForm.getTitle()
                + "\n\t getDescription: " + imageForm.getDescription()
                + "\n\t getStainingMethodName() : " + imageForm.getStainingMethodName()                   
                + "\n\t getStainingMethodCode(): " + imageForm.getStainingMethodCode()     + "\n"            
                + (String) request.getSession().getAttribute("camod.loggedon.username"));

        String theForward = "AnimalModelTreePopulateAction";

        try {
            // retrieve model and update w/ new values
            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");
            AnimalModel theAnimalModel = theAnimalModelManager.get(theModelId);

            String inPath = request.getSession().getServletContext().getRealPath("/config/temp.jpg");
            theAnimalModelManager.addImage(theAnimalModel, imageForm, inPath);

            log.debug("New Image created");

            // Add a message to be displayed in submitOverview.jsp saying you've
            // created a new model successfully
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("image.creation.successful"));
            saveErrors(request, msg);

        }
        catch (IllegalArgumentException e) {
            log.error("Exception occurred creating Image", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.image.unsupportedfiletype"));
            saveErrors(request, msg);
            
            theForward = "input";
        }
        catch (Exception e) {
            log.error("Exception occurred creating Image", e);

            // Encountered an error saving the model.
            ActionMessages msg = new ActionMessages();
            msg.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.admin.message"));
            saveErrors(request, msg);
        }

        log.trace("Exiting save");
        return mapping.findForward(theForward);
    }
}