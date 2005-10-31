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

        System.out.println("<GenomicSegmentPopulateAction populate> Entering populate() ");

        GenomicSegmentForm genomicSegmentForm = (GenomicSegmentForm) form;

        String aGenomicSegmentID = request.getParameter("aGenomicSegmentID");

        GenomicSegment theGenomicSegment = GenomicSegmentManagerSingleton.instance().get(aGenomicSegmentID);

        if (theGenomicSegment == null) {
            request.setAttribute("aGenomicSegmentID", null);
        } else {
            request.setAttribute("aGenomicSegmentID", aGenomicSegmentID);

            if (theGenomicSegment.getLocationOfIntegration().equals("Random")) {
                genomicSegmentForm.setLocationOfIntegration(theGenomicSegment.getLocationOfIntegration());
            } else {
                genomicSegmentForm.setLocationOfIntegration("Targeted");
                genomicSegmentForm.setOtherLocationOfIntegration(theGenomicSegment.getLocationOfIntegration());
            }

            SegmentType inSegmentType = (SegmentType) theGenomicSegment.getSegmentTypeCollection().get(0);
            genomicSegmentForm.setSegmentName(inSegmentType.getName());

            if (inSegmentType.getNameUnctrlVocab() != null)
                genomicSegmentForm.setOtherSegmentName(inSegmentType.getNameUnctrlVocab());

            genomicSegmentForm.setSegmentSize(theGenomicSegment.getSegmentSize());
            genomicSegmentForm.setCloneDesignator(theGenomicSegment.getCloneDesignator());

            // Gene(s)

            // Marker(s)

            // Commentes
            genomicSegmentForm.setComments(theGenomicSegment.getComments());

            // MGI Number
            MutationIdentifier inMutationIdentifier = theGenomicSegment.getMutationIdentifier();
            if (inMutationIdentifier != null)
                genomicSegmentForm.setNumberMGI(inMutationIdentifier.getNumberMGI().toString());

            // Image
            Image inImage = theGenomicSegment.getImage();
            if (inImage != null) {
                genomicSegmentForm.setTitle(inImage.getTitle());
                genomicSegmentForm.setFileServerLocation(inImage.getFileServerLocation());
                genomicSegmentForm.setDescriptionOfConstruct(inImage.getDescription());
            }

        }

        // setup dropdown menus
        this.dropdown(request, response);

        return mapping.findForward("submitGenomicSegment");
    }

    public ActionForward dropdown(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        System.out.println("<GenomicSegmentPopulateAction dropdown> Entering dropdown()");

        // blank out the FORMDATA Constant field
        GenomicSegmentForm GenomicSegmentForm = (GenomicSegmentForm) form;
        request.getSession().setAttribute(Constants.FORMDATA, GenomicSegmentForm);

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

        System.out.println("<GenomicSegmentPopulateAction dropdown> Entering void dropdown()");

        NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.GENOMICSEGMENTDROP, "");

        System.out.println("<GenomicSegmentPopulateAction dropdown> Exiting void dropdown()");
    }
}