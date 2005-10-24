package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.service.impl.ImageManagerSingleton;
import gov.nih.nci.camod.webapp.form.ImageForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ImagePopulateAction extends BaseAction {	

    public ActionForward populate( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) 
    	throws Exception {

    	System.out.println( "<ImagePopulateAction populate> Entering populate() " );

	    ImageForm imageForm = (ImageForm) form;	 
	    
	    String aImageID = request.getParameter("aImageID");
	    Image inImage = ImageManagerSingleton.instance().get(aImageID);	  
	    
	    System.out.println( "FILESERVERLOCATION=" + inImage.getFileServerLocation() );
	    //Image
	    //Image inImage = theImage.getImage();
	    if ( inImage != null ) {
	    	imageForm.setTitle( inImage.getTitle() );
	    	imageForm.setFileServerLocation( inImage.getFileServerLocation() );
	    	imageForm.setDescriptionOfConstruct( inImage.getDescription() );
	    	
	    	//TODO: Display a message on the current image, uploading another image will replace current image
	    	//TODO: Display thumbnail and viewer for image already uploaded
	    }
	    
	    request.getSession().setAttribute(Constants.FORMDATA, imageForm);       
	
	    // setup dropdown menus
	    this.dropdown(request, response);
	    
	    return mapping.findForward( "submitImages" );
	}

	public ActionForward dropdown( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println( "<ImagePopulateAction dropdown> Entering dropdown()" );
	
	    // blank out the FORMDATA Constant field
	    ImageForm engineeredTransgeneForm = (ImageForm) form;	    
	    request.getSession().setAttribute(Constants.FORMDATA, engineeredTransgeneForm);         
	    
	    // setup dropdown menus
	    this.dropdown( request, response );
	    
	    return mapping.findForward( "submitImages" );
	} 
	
	/**
	 * Populate all drowpdowns for this type of form
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropdown( HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println( "<ImagePopulateAction dropdown> Entering void dropdown()" );
	    
	 //   NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.HOSTSPECIESDROP, "" );
	    
	    System.out.println( "<ImagePopulateAction dropdown> Exiting void dropdown()" );
	}	
}