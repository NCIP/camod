/**
 * 
 * $Id: PublicationAction.java,v 1.6 2005-10-26 20:12:43 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * 
 */

package gov.nih.nci.camod.webapp.action;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.domain.PublicationStatus;
import gov.nih.nci.camod.service.AnimalModelManager;
import gov.nih.nci.camod.service.PublicationManager;
import gov.nih.nci.camod.webapp.form.PublicationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * PublicationAction Class
 */
public final class PublicationAction extends BaseAction {
	
    /**
     * Delete
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'delete' method");
        }

        return mapping.findForward("");
    }

	/**
	 * Cancel
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward duplicate(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
    throws Exception {
    	
    	 return mapping.findForward("");
    }    
    
    
    /**
     * Edit
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'edit' method");
        }
		// Create a form to edit
		PublicationForm pubForm = ( PublicationForm ) form;
		
		System.out.println( "<PublicationAction save> following Characteristics:" + 
									"\n\t name: " + pubForm.getName() +  
									"\n\t Aurthur: " + pubForm.getAuthors() + 
									"\n\t Year: " + pubForm.getYear() + 
									"\n\t Volume: " + pubForm.getVolume() +
									"\n\t PMID: " + pubForm.getPmid() +
									"\n\t Start Page: " + pubForm.getStartPage() +
									"\n\t End Page: " + pubForm.getEndPage() +
									"\n\t Title: " + pubForm.getTitle() +
									"\n\t journal: " + pubForm.getJournal() +
									"\n\t FirstTimeReported: " + pubForm.getFirstTimeReported() +
									"\n\t user: " + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
		
    	// Grab the current Therapy we are working with related to this animalModel
    	String aPubID = request.getParameter( "aPubID" );		
		
		/* Grab the current modelID from the session */
		String modelID = "" + request.getSession().getAttribute( Constants.MODELID );
        	
		// Use the current animalModel based on the ID stored in the session
		AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
        PublicationManager publicationManager = (PublicationManager) getBean( "publicationManager" );
        
		AnimalModel am = animalModelManager.get( modelID );	
		 
// TODO: Verify that the Long values are longs or else there will be exceptions, do this in the validation!		
		
		//retrieve the list of all therapies from the current animalModel		
		List pubList = am.getPublicationCollection();
		
		Publication pub = new Publication();
		
		//find the specific one we need
		for ( int i=0; i<pubList.size(); i++ ) {
			pub = (Publication)pubList.get(i);
			if ( pub.getId().toString().equals( aPubID ) )
				break;
		}
		
		/* 1.  Create the Publication Object */
        pub.setAuthors( pubForm.getAuthors() );
        pub.setTitle( pubForm.getTitle() );
        pub.setJournal( pubForm.getJournal() );
        pub.setVolume( pubForm.getVolume() );
        
       // pub.setYear( Long.valueOf( pubForm.getYear().trim() ) );  
       // pub.setStartPage( Long.valueOf( pubForm.getStartPage().trim() ) );
       // pub.setEndPage( Long.valueOf(  pubForm.getEndPage().trim() ) );
       // pub.setPmid( Long.valueOf( pubForm.getPmid().trim() )  );
        
        String strPub = pubForm.getPmid().trim();
        Pattern p = Pattern.compile("[0-9]{" + strPub.length() + "}");
		Matcher m = p.matcher( strPub );				
		if (m.matches() && strPub != null && ! strPub.equals("") ) {
			pub.setPmid( Long.valueOf( strPub ) );
		}
		
		strPub = pubForm.getStartPage().trim();
		p = Pattern.compile("[0-9]{" + strPub.length() + "}");
		m = p.matcher( strPub );
		if (m.matches() && strPub != null && ! strPub.equals("") ) {
			pub.setStartPage( Long.valueOf( strPub ) );
		}
		
		strPub = pubForm.getEndPage().trim();
		p = Pattern.compile("[0-9]{" + strPub.length() + "}");
		m = p.matcher( strPub );
		if (m.matches() && strPub != null && ! strPub.equals("") ) {
			pub.setEndPage( Long.valueOf( strPub ) );
		}
		
		strPub = pubForm.getYear().trim();
		p = Pattern.compile("[0-9]{" + strPub.length() + "}");
		m = p.matcher( strPub );
		if (m.matches() && strPub != null && ! strPub.equals("") ) {
			pub.setYear( Long.valueOf( strPub ) );
		}
                        
        if ( pubForm.getFirstTimeReported() != null && pubForm.getFirstTimeReported().equals( "yes" ) )
        	pub.setFirstTimeReported( new Boolean(true) );        	
        else
        	pub.setFirstTimeReported( new Boolean(false) );
		
        PublicationStatus pubStatus = new PublicationStatus();
        pubStatus.setName( pubForm.getName() );
        publicationManager.save( pub, pubStatus );

    	//Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "publication.edit.successful" ) );
        saveErrors( request, msg );
        
        return mapping.findForward("AnimalModelTreePopulateAction");
    }

	/** Called from submitEnvironmentalFactors.jsp
	 * 
	 */ 
    public ActionForward save( ActionMapping mapping, 
							   ActionForm form,
					           HttpServletRequest request,
					           HttpServletResponse response)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'save' method");
        }    	
    	
    	PublicationForm pubForm = ( PublicationForm ) form;
    	
		System.out.println( "<PublicationAction save> following Characteristics:" + 
										"\n\t name: " + pubForm.getName() +  
										"\n\t Aurthur: " + pubForm.getAuthors() + 
										"\n\t Year: " + pubForm.getYear() + 
										"\n\t Volume: " + pubForm.getVolume() +
										"\n\t PMID: " + pubForm.getPmid() +
										"\n\t Start Page: " + pubForm.getStartPage() +
										"\n\t End Page: " + pubForm.getEndPage() +
										"\n\t Title: " + pubForm.getTitle() +
										"\n\t journal: " + pubForm.getJournal() +
										"\n\t FirstTimeReported: " + pubForm.getFirstTimeReported() +
										"\n\t user: " + (String) request.getSession().getAttribute( "camod.loggedon.username" ) );
		
		/* Grab the current modelID from the session */
        String modelID = (String) request.getSession().getAttribute( Constants.MODELID );
        
        /* Create all the manager objects needed for Screen */
        AnimalModelManager animalModelManager = (AnimalModelManager) getBean( "animalModelManager" );
        PublicationManager publicationManager = (PublicationManager) getBean( "publicationManager" );

        /* Set modelID in AnimalModel object */
        AnimalModel animalModel = animalModelManager.get( modelID );        
 
//TODO: Verify that the Long values are longs or else there will be exceptions, do this in the validation!
        
		/* 1.  Create the Publication Object */
        Publication pub = new Publication();
        pub.setAuthors( pubForm.getAuthors() );
        pub.setTitle( pubForm.getTitle() );
        pub.setJournal( pubForm.getJournal() );
        pub.setVolume( pubForm.getVolume() );
		
        String strPub = pubForm.getPmid().trim();
        Pattern p = Pattern.compile("[0-9]{" + strPub.length() + "}");
		Matcher m = p.matcher( strPub );				
		if (m.matches() && strPub != null && ! strPub.equals("") ) {
			pub.setPmid( Long.valueOf( strPub ) );
		}
		
		strPub = pubForm.getStartPage().trim();
		p = Pattern.compile("[0-9]{" + strPub.length() + "}");
		m = p.matcher( strPub );
		if (m.matches() && strPub != null && ! strPub.equals("") ) {
			pub.setStartPage( Long.valueOf( strPub ) );
		}
		
		strPub = pubForm.getEndPage().trim();
		p = Pattern.compile("[0-9]{" + strPub.length() + "}");
		m = p.matcher( strPub );
		if (m.matches() && strPub != null && ! strPub.equals("") ) {
			pub.setEndPage( Long.valueOf( strPub ) );
		}
		
		strPub = pubForm.getYear().trim();
		p = Pattern.compile("[0-9]{" + strPub.length() + "}");
		m = p.matcher( strPub );
		if (m.matches() && strPub != null && ! strPub.equals("") ) {
			pub.setYear( Long.valueOf( strPub ) );
		}
		
		//	pub.setPmid( Long.valueOf( strPub ) );
		//	pub.setStartPage( Long.valueOf( pubForm.getStartPage().trim() ) );
		//   pub.setEndPage( Long.valueOf(  pubForm.getEndPage().trim() ) );
		//  pub.setYear( Long.valueOf( pubForm.getYear().trim() ) );  
        
        if ( pubForm.getFirstTimeReported() != null && pubForm.getFirstTimeReported().equals( "yes" ) )
        	pub.setFirstTimeReported( new Boolean(true) );        	
        else
        	pub.setFirstTimeReported( new Boolean(false) );
        
        PublicationStatus pubStatus = new PublicationStatus();
        pubStatus.setName( pubForm.getName() );
        //publicationManager.savePublicationStats( pubStatus );
        
        //pub.setPublicationStatus( pubStatus );
        publicationManager.save( pub, pubStatus );
        
        animalModel.addPublication( pub );

		/* save the animalModel = saves Therapy (Hibernate saves child in 1...1 relationships)  */  
        animalModelManager.save( animalModel );
        
        System.out.println( "<PublicationAction save> saved the animalModel" );
 
    	//Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
        ActionMessages msg = new ActionMessages();
        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "publication.creation.successful" ) );
        saveErrors( request, msg );
     
        return mapping.findForward("AnimalModelTreePopulateAction");		        
    }		
}