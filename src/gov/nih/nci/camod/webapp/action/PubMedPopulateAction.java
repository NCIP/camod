package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Publication;
import gov.nih.nci.camod.util.PopulatePubMedUtil;
import gov.nih.nci.camod.webapp.form.PublicationForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class PubMedPopulateAction extends BaseAction {

	 public ActionForward execute( ActionMapping mapping, 
			 					   ActionForm form, 
			 					   HttpServletRequest request,
			 					   HttpServletResponse response) 
	 	throws IOException, ServletException {
		 
		    System.out.println( "<PubMedPopulateAction> Entering..." );
    	 	
		    String pmid = request.getParameter( "pmid" );
		 			 	
			//TODO: set the form values from the Publication obj
			PublicationForm pubForm = ( PublicationForm ) form;

			// Validate the input
			Pattern p = Pattern.compile("[0-9]{" + pmid.length() + "}");
			Matcher m = p.matcher( pmid );
			 
			if (m.matches() && pmid != null && ! pmid.equals("") ) 
			 {
				Publication pub = new Publication();
				
                PopulatePubMedUtil.populatePumMedRecord( Long.valueOf( pmid.trim() ), pub );			
				
				pubForm.setJournal( pub.getJournal() );
				pubForm.setYear(  "" + pub.getYear() );
				pubForm.setVolume( pub.getVolume() );
				pubForm.setTitle( pub.getTitle() );
				pubForm.setStartPage( "" + pub.getStartPage() );
				pubForm.setEndPage( "" + pub.getEndPage() );
				
			} else {
			    
				System.out.println("<PubMedPopulateAction> Not a number, wrong!");

			    pubForm.setJournal( "" );
				pubForm.setYear( "" );
				pubForm.setVolume( "" );
				pubForm.setTitle( "" );
				pubForm.setStartPage( "" );
				pubForm.setEndPage( "" );

			     //Add a message to be displayed in submitOverview.jsp saying you've created a new model successfully 
		        ActionMessages msg = new ActionMessages();
		        msg.add( ActionMessages.GLOBAL_MESSAGE, new ActionMessage( "publication.incorrect.pmid" ) );
		        saveErrors( request, msg );			        
			}
		
            NewDropdownUtil.populateDropdown(request, Constants.Dropdowns.PUBDROP, "" );
			
			request.getSession().setAttribute( Constants.FORMDATA, pubForm );	
			 
	        // Forward control to the specified success URI
	        return mapping.findForward( "submitPublications" );
	 }
}
