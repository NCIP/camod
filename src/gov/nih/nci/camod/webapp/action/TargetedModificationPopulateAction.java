/**
 * 
 * $Id: TargetedModificationPopulateAction.java,v 1.9 2005-11-21 16:54:36 georgeda Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.8  2005/11/16 19:27:47  pandyas
 * Modified Targeted Modification Types dropdown to allow multiple selections, allow the user to select "other" by itself, and allow users to select "other" along with one or more selection
 *
 * 
 */

package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.*;
import gov.nih.nci.camod.domain.Conditionality;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.ModificationType;
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
            MutationIdentifier theMutationIdentifier = theTargetedModification.getMutationIdentifier();
            if (theMutationIdentifier != null) {
                targetedModificationForm.setNumberMGI(theMutationIdentifier.getNumberMGI().toString());
            }
            
			// Get the collection of Targeted Modifications
			List theTargetedModificationList = theTargetedModification.getModificationTypeCollection();
			String[] theTargetedMod = new String[theTargetedModificationList.size()];
			for (int i = 0; i < theTargetedModificationList.size(); i++) {
				ModificationType theModificationType = (ModificationType) theTargetedModificationList.get(i);				
				theTargetedMod[i] = theModificationType.getName();
				log.error("theTargetedMod[i]: " +theTargetedMod[i] );
				//if the selection is "other"
				if ( theTargetedMod[i].equalsIgnoreCase("Other")) {
					//log.error("Other Modification Type Selected. " +theTargetedMod[i] );
					targetedModificationForm.setModificationType(theTargetedMod);
					targetedModificationForm.setOtherModificationType(theTargetedModification.getModTypeUnctrlVocab());
				} else {
					//log.error("Another non-other Modification Type Selected. " +theTargetedMod[i] );
					targetedModificationForm.setModificationType(theTargetedMod);
				}			
			}
            
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
                targetedModificationForm.setDescriptionOfConstruct(image.getDescription());
                targetedModificationForm.setThumbUrl(image.getThumbUrl());
                targetedModificationForm.setImageUrl(image.getImageUrl());
            }

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