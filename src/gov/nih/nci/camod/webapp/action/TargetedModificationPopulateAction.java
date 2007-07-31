/**
 * 
 * $Id: TargetedModificationPopulateAction.java,v 1.17 2007-07-31 12:02:55 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.16  2007/04/04 13:19:07  pandyas
 * modified names for mutation identifier fields (number changed to id)
 *
 * Revision 1.15  2007/03/26 12:02:30  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.14  2006/05/08 13:42:44  georgeda
 * Reformat and clean up warnings
 *
 * Revision 1.13  2006/04/20 14:04:50  pandyas
 * changed Modification Type to getOrCreate
 *
 * Revision 1.12  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * Revision 1.11  2005/11/28 18:31:57  georgeda
 * Defect #64, fix for newly submitted models
 *
 * Revision 1.10  2005/11/28 13:50:32  georgeda
 * Defect #207, handle nulls for pages w/ uncontrolled vocab
 *
 * Revision 1.9  2005/11/21 16:54:36  georgeda
 * Defect #105, added MGI number for targeted modification and added hyperlink to search pages
 *
 * Revision 1.8  2005/11/16 19:27:47  pandyas
 * Modified Targeted Modification Types dropdown to allow multiple selections, allow the user to select "other" by itself, and allow users to select "other" along with one or more selection
 *
 * 
 */

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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TargetedModificationPopulateAction extends BaseAction
{

    public ActionForward populate(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {
        log.debug("<TargetedModificationPopulateAction populate> Entering populate() ");

        TargetedModificationForm theTargetedModificationForm = (TargetedModificationForm) form;

        String aTargetedModificationID = request.getParameter("aTargetedModificationID");
        TargetedModification theTargetedModification = TargetedModificationManagerSingleton.instance().get(aTargetedModificationID);

        if (theTargetedModification == null)
        {
            request.setAttribute("deleted", "true");
        }
        else
        {
        	theTargetedModificationForm.setModificationId(aTargetedModificationID);

        	theTargetedModificationForm.setName(theTargetedModification.getName());
            MutationIdentifier theMutationIdentifier = theTargetedModification.getMutationIdentifier();
            if (theMutationIdentifier != null)
            {
            	if (theMutationIdentifier.getMgiId() != null && theMutationIdentifier.getMgiId().length() > 0) {
            		theTargetedModificationForm.setMgiId(theMutationIdentifier.getMgiId());
            	}
            	if (theMutationIdentifier.getZfinId() != null && theMutationIdentifier.getZfinId().length() > 0) {
            		theTargetedModificationForm.setZfinId(theMutationIdentifier.getZfinId());
                }
            	if (theMutationIdentifier.getRgdId() != null && theMutationIdentifier.getRgdId().length() > 0) {
            		theTargetedModificationForm.setRgdId(theMutationIdentifier.getRgdId());
                }             	
            }

            // Get the collection/Set of modification types
            List<ModificationType> theModificationTypeList = new ArrayList<ModificationType>(theTargetedModification.getModificationTypeCollection());
            String[] theModTypes = new String[theModificationTypeList.size()];

            for (int i = 0; i < theModificationTypeList.size(); i++)
            {
                ModificationType theModificationType = (ModificationType) theModificationTypeList.get(i);
                //If one of the selections was 'Other', populate other field correctly
                if (theModificationType.getNameUnctrlVocab() != null)
                {
                    theModTypes[i] = Constants.Dropdowns.OTHER_OPTION;
                    theTargetedModificationForm.setOtherModificationType(theModificationType.getNameUnctrlVocab());
                }
                else
                {
                    theModTypes[i] = theModificationType.getName();
                }
            }
            //set the modification types on the form, including 'Other' if selected
            theTargetedModificationForm.setModificationType(theModTypes);

            theTargetedModificationForm.setBlastocystName(theTargetedModification.getBlastocystName());
            theTargetedModificationForm.setComments(theTargetedModification.getComments());
            theTargetedModificationForm.setGeneId(theTargetedModification.getGeneId());
            theTargetedModificationForm.setEsCellLineName(theTargetedModification.getEsCellLineName());

            // Construct Sequence
            theTargetedModificationForm.setConstructSequence(theTargetedModification.getConstructSequence());

            // Conditionality
            Conditionality theConditionality = theTargetedModification.getConditionality();
            if (theConditionality != null)
            {
                if ("1".equals(theConditionality.getConditionedBy()))
                {
                	theTargetedModificationForm.setConditionedBy(Constants.CONDITIONAL);
                }
                else
                {
                	theTargetedModificationForm.setConditionedBy(Constants.NOT_CONDITIONAL);
                }

                theTargetedModificationForm.setDescription(theConditionality.getDescription());
                theTargetedModificationForm.setDescription(theConditionality.getDescription());
            }

            Image image = theTargetedModification.getImage();
            if (image != null)
            {
            	theTargetedModificationForm.setUrl(image.getUrl());
            	theTargetedModificationForm.setTitle(image.getTitle());
            	theTargetedModificationForm.setDescriptionOfConstruct(image.getDescription());
            	theTargetedModificationForm.setDescriptionOfConstruct(image.getDescription());
            	theTargetedModificationForm.setThumbUrl(image.getThumbUrl());
            	theTargetedModificationForm.setImageUrl(image.getImageUrl());
            }

        }

        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitTargetedModification");
    }

    public ActionForward dropdown(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception
    {
        log.debug("<TargetedModificationPopulateAction dropdown> Entering dropdown()");

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
    public void dropdown(HttpServletRequest request,
                         HttpServletResponse response) throws Exception
    {

        log.debug("<TargetedModificationPopulateAction dropdown> Entering void dropdown()");

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.TARGETEDMODIFICATIONDROP, "");

        log.debug("<TargetedModificationPopulateAction dropdown> Exiting void dropdown()");
    }
}