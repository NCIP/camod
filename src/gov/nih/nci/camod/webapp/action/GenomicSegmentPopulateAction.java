/**
 * 
 * $Id: GenomicSegmentPopulateAction.java,v 1.16 2007-03-26 12:02:30 pandyas Exp $
 *
 * $Log: not supported by cvs2svn $
 * Revision 1.15  2006/11/09 17:26:40  pandyas
 * Commented out debug code
 *
 * Revision 1.14  2006/05/22 16:52:28  pandyas
 * modified isRandom to make consistent between transgene and genomic segment
 *
 * Revision 1.13  2006/04/20 15:00:11  georgeda
 * Fixed targeted/random
 *
 * Revision 1.12  2006/04/17 19:09:40  pandyas
 * caMod 2.1 OM changes
 *
 * 
 */


package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.GenomicSegment;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.SegmentType;
import gov.nih.nci.camod.service.impl.GenomicSegmentManagerSingleton;
import gov.nih.nci.camod.webapp.form.GenomicSegmentForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GenomicSegmentPopulateAction extends BaseAction {

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //System.out.println("<GenomicSegmentPopulateAction populate> Entering populate() ");

        // setup dropdown menus
        this.dropdown(request, response);

        String aGenomicSegmentID = request.getParameter("aGenomicSegmentID");
        GenomicSegmentForm genomicSegmentForm = (GenomicSegmentForm) form;
        GenomicSegment theGenomicSegment = GenomicSegmentManagerSingleton.instance().get(aGenomicSegmentID);

        if (theGenomicSegment == null) {
            request.setAttribute(Constants.Parameters.DELETED, "true");
        } else {
            genomicSegmentForm.setSegmentId(aGenomicSegmentID);

            // populate isRandom
            if (theGenomicSegment.getIsRandom() == true) {
                genomicSegmentForm.setIsRandom("yes");
            } else {
                genomicSegmentForm.setIsRandom("no");
                genomicSegmentForm.setLocationOfIntegration(theGenomicSegment.getLocationOfIntegration());                
            }

            SegmentType inSegmentType = (SegmentType) theGenomicSegment.getSegmentType();
            genomicSegmentForm.setSegmentName(inSegmentType.getName());

            if (inSegmentType.getNameUnctrlVocab() != null) {
            	genomicSegmentForm.setSegmentName(Constants.Dropdowns.OTHER_OPTION);
                genomicSegmentForm.setOtherSegmentName(inSegmentType.getNameUnctrlVocab());
            }

            genomicSegmentForm.setSegmentSize(theGenomicSegment.getSegmentSize());
            genomicSegmentForm.setCloneDesignator(theGenomicSegment.getCloneDesignator());

            // Gene(s)

            // Marker(s)

            // Comments
            genomicSegmentForm.setComments(theGenomicSegment.getComments());
            
            // Construct Sequence
            genomicSegmentForm.setConstructSequence(theGenomicSegment.getConstructSequence());            

            // MGI Number
            MutationIdentifier theMutationIdentifier = theGenomicSegment.getMutationIdentifier();
            if (theMutationIdentifier != null){
            	if (theMutationIdentifier.getMgiNumber() != null && theMutationIdentifier.getMgiNumber().length() > 0) {
            		genomicSegmentForm.setMgiNumber(theMutationIdentifier.getMgiNumber());
            	}
            	if (theMutationIdentifier.getZfinNumber() != null && theMutationIdentifier.getZfinNumber().length() > 0) {
            		genomicSegmentForm.setZfinNumber(theMutationIdentifier.getZfinNumber());
                }
            	if (theMutationIdentifier.getRgdNumber() != null && theMutationIdentifier.getRgdNumber().length() > 0) {
            		genomicSegmentForm.setRgdNumber(theMutationIdentifier.getRgdNumber());
                }    
            }
            
            // Image
            Image inImage = theGenomicSegment.getImage();
            if (inImage != null) {
                genomicSegmentForm.setTitle(inImage.getTitle());
                genomicSegmentForm.setFileServerLocation(inImage.getFileServerLocation());
                genomicSegmentForm.setDescriptionOfConstruct(inImage.getDescription());
                genomicSegmentForm.setImageUrl(inImage.getImageUrl());
                genomicSegmentForm.setThumbUrl(inImage.getThumbUrl());
            }

        }

        return mapping.findForward("submitGenomicSegment");
    }

    public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        //System.out.println("<GenomicSegmentPopulateAction dropdown> Entering dropdown()");

        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitGenomicSegment");
    }

    /**
     * Populate all drowpdowns for this type of form
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public void dropdown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //System.out.println("<GenomicSegmentPopulateAction dropdown> Entering void dropdown()");

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.GENOMICSEGMENTDROP, "");

        //System.out.println("<GenomicSegmentPopulateAction dropdown> Exiting void dropdown()");
    }
}