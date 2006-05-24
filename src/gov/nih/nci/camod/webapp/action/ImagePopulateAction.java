/**
 * 
 * $Id: ImagePopulateAction.java,v 1.15 2006-05-24 16:51:51 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.14  2006/05/23 18:33:38  schroedn
 * Fixed a null pointer problem on staining
 *
 * Revision 1.13  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.service.impl.ImageManagerSingleton;
import gov.nih.nci.camod.webapp.form.ImageForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class ImagePopulateAction extends BaseAction {

	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<ImagePopulateAction populate> Entering populate() ");

		ImageForm imageForm = (ImageForm) form;

		String aImageID = request.getParameter("aImageID");

		Image inImage = ImageManagerSingleton.instance().get(aImageID);
       
		// Handle back arrow
		if (inImage == null) {
			request.setAttribute(Constants.Parameters.DELETED, "true");
		} else {
			imageForm.setImageId(aImageID);

			if (inImage != null) {
				imageForm.setTitle(inImage.getTitle());
				imageForm.setFileServerLocation(inImage.getFileServerLocation());
                
                if( inImage.getDescription() != null )
                {
                    imageForm.setDescriptionOfConstruct(inImage.getDescription());
                }
                
				if( inImage.getStainingMethod() != null )
                {
                        imageForm.setStainingMethod( inImage.getStainingMethod().getName() );				
                        imageForm.setOtherStainingMethod( inImage.getStainingMethod().getNameUnctrlVocab() );
                }
                
                imageForm.setThumbUrl(inImage.getThumbUrl());
                imageForm.setImageUrl(inImage.getImageUrl());
			}
		}
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STAININGDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
		return mapping.findForward("submitImages");
	}

	public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<ImagePopulateAction dropdown> Entering dropdown()");
		
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STAININGDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
		
		return mapping.findForward("submitImages");
	}
}