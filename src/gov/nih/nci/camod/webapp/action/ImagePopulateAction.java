/**
 * 
 * $Id: ImagePopulateAction.java,v 1.20 2007-07-31 12:02:55 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.19  2007/05/23 16:57:34  pandyas
 * Added code to handle previous models that used the nameUnctrlVocab for staining method
 * The tree does not allow the user to add terms, but this is a feature request
 *
 * Revision 1.18  2007/04/20 17:51:14  pandyas
 * Modified to add Staining Method tree to Image submission
 *
 * Revision 1.17  2007/04/18 19:20:29  pandyas
 * Modified to add Staining Method tree to Image submission
 *
 * Revision 1.16  2006/05/24 20:25:53  georgeda
 * Fixed staining methods
 *
 * Revision 1.15  2006/05/24 16:51:51  pandyas
 * Converted StainingMethod to lookup - modified code to pull dropdown list from DB
 *
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

		Image theImage = ImageManagerSingleton.instance().get(aImageID);
       
		// Handle back arrow
		if (theImage == null) {
			request.setAttribute(Constants.Parameters.DELETED, "true");
		} else {
			imageForm.setImageId(aImageID);

			if (theImage != null) {
				imageForm.setTitle(theImage.getTitle());
				imageForm.setUrl(theImage.getUrl());
                
                if( theImage.getDescription() != null )
                {
                    imageForm.setDescriptionOfConstruct(theImage.getDescription());
                }
                
				if( theImage.getStainingMethod() != null )
                {
					// Older models have uncontrolled vocab with concept code = null
					if(theImage.getStainingMethod().getNameUnctrlVocab() != null){
	                    imageForm.setStainingMethod(theImage.getStainingMethod().getNameUnctrlVocab());                    
					} else {
	                    // since we are always querying from concept code (save and edit),
	                    // simply display EVSPreferredDescription
	                    imageForm.setStainingMethod(theImage.getStainingMethod().getEVSPreferredDescription());
	                    log.info("setStainingMethod= " + theImage.getStainingMethod().getEVSPreferredDescription());
	
	                    imageForm.setStainingMethodCode(theImage.getStainingMethod().getConceptCode());
	                    log.info("setStainingMethodCode= " + theImage.getStainingMethod().getConceptCode()); 
					}
                }
                
                imageForm.setThumbUrl(theImage.getThumbUrl());
                imageForm.setImageUrl(theImage.getImageUrl());
			}
		}
        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STAININGDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
		return mapping.findForward("submitImages");
	}

	public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("<ImagePopulateAction dropdown> Entering dropdown()");
		
        //NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.STAININGDROP, Constants.Dropdowns.ADD_BLANK_AND_OTHER);
		
		return mapping.findForward("submitImages");
	}
}