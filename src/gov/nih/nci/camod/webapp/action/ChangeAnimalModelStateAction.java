package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.CurationManager;
import gov.nih.nci.camod.service.impl.CurationManagerImpl;
import gov.nih.nci.camod.webapp.form.AnimalModelStateForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class ChangeAnimalModelStateAction extends BaseAction {

    /**
     * Change the state for the curation model
     */
    public ActionForward execute(ActionMapping inMapping, ActionForm inForm, HttpServletRequest inRequest,
            HttpServletResponse inResponse) throws Exception {

        if (!isCancelled(inRequest)) {

            CurationManager theCurationManager = new CurationManagerImpl(getServlet().getServletContext().getRealPath(
                    "/")
                    + "/config/CurationConfig.xml");

            System.out.println("<ChangeAnimalModelStateAction populate> Entering... ");

            AnimalModelStateForm theForm = (AnimalModelStateForm) inForm;

            // TODO: is this necessary?
            if (theForm.getEvent() == null) {
                theForm.setEvent("");
            }

            AnimalModelManager theAnimalModelManager = (AnimalModelManager) getBean("animalModelManager");

            AnimalModel theAnimalModel = theAnimalModelManager.get(theForm.getModelId());

            System.out.println("Comment: " + theForm.getComment());

            if (theAnimalModel != null) {
                System.out.println("Current state: " + theAnimalModel.getState());
                theCurationManager.changeState(theAnimalModel, theForm.getEvent());
                System.out.println("New state: " + theAnimalModel.getState());

                // Save the state change
                theAnimalModelManager.save(theAnimalModel);
            }
        }
        return inMapping.findForward("next");
    }
}
