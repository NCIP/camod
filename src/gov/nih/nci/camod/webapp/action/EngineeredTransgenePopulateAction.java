package gov.nih.nci.camod.webapp.action;

import gov.nih.nci.camod.Constants;
import gov.nih.nci.camod.domain.Conditionality;
import gov.nih.nci.camod.domain.GeneFunction;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.domain.MutationIdentifier;
import gov.nih.nci.camod.domain.RegulatoryElement;
import gov.nih.nci.camod.domain.Taxon;
import gov.nih.nci.camod.domain.Transgene;
import gov.nih.nci.camod.service.impl.EngineeredTransgeneManagerSingleton;
import gov.nih.nci.camod.webapp.form.EngineeredTransgeneForm;
import gov.nih.nci.camod.webapp.util.NewDropdownUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class EngineeredTransgenePopulateAction extends BaseAction{
	
    public ActionForward populate( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println( "<EngineeredTransgenePopulateAction populate> Entering populate() " );
	
	    EngineeredTransgeneForm engineeredTransgeneForm = (EngineeredTransgeneForm) form;	 
	    
	    String aEngineeredTransgeneID = request.getParameter("aEngineeredTransgeneID");
	    Transgene theEngineeredTransgene = EngineeredTransgeneManagerSingleton.instance().get(aEngineeredTransgeneID);
	    
	    //Tansgene Integration
	    if (theEngineeredTransgene.getLocationOfIntegration().equals( "Random" )){
	    	engineeredTransgeneForm.setLocationOfIntegration( theEngineeredTransgene.getLocationOfIntegration() );	
	    } else {
	    	engineeredTransgeneForm.setLocationOfIntegration( "Targeted" );
	    	engineeredTransgeneForm.setOtherLocationOfIntegration( theEngineeredTransgene.getLocationOfIntegration() );
	    }
	    	    
	    //Transgene (coding sequence only)
	    List taxList = theEngineeredTransgene.getTaxonCollection();
	    Taxon tax = (Taxon) taxList.get(0);

	    //NOTE: This is needed because we are storing 'Other' Species selections directly into the database
	    //      and are retrieving the dropdowns for this page from a text file ( HostSpecies.txt)
	    //      We need to get the list from
	    NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.HOSTSPECIESDROP, "" );
	    List dropdownTaxList = (List) request.getAttribute( Constants.Dropdowns.HOSTSPECIESDROP );
	   
	    engineeredTransgeneForm.setName( theEngineeredTransgene.getName() );
	    
	    if ( dropdownTaxList.contains( tax.getScientificName() ) ) {
	    	System.out.println( "\t(Transgene) Matched the list");	    	
		    engineeredTransgeneForm.setScientificName( tax.getScientificName() );
	    } else {
	    	System.out.println( "\t(Transgene) Didn't Match the list");
		    engineeredTransgeneForm.setScientificName( "Other" );
		    engineeredTransgeneForm.setOtherScientificName( tax.getScientificName() );
	    }
	    		    
	    //Transcriptional (Promoter) 1
	    List regElementList = theEngineeredTransgene.getRegulatoryElementCollection();
	    RegulatoryElement regElement = null;
	    Taxon tax_reg1 = null;
    
	    for ( int i=0; i < regElementList.size(); i++ ) {
	    	regElement = (RegulatoryElement) regElementList.get(i);
	    	
	    	if ( regElement.getRegulatoryElementType().getName().equals( "Transcriptional1" ) ) {
	    		engineeredTransgeneForm.setTranscriptional1_name( regElement.getName() );	    		
	    		tax_reg1 = regElement.getTaxon();	    	
	    			    		
	    		if ( dropdownTaxList.contains( tax_reg1.getScientificName() ) ) {	    	
	    		    engineeredTransgeneForm.setTranscriptional1_species( tax_reg1.getScientificName() );
	    	    } else {
	    		    engineeredTransgeneForm.setTranscriptional1_species( "Other" );
	    		    engineeredTransgeneForm.setTranscriptional1_otherSpecies( tax_reg1.getScientificName() );
	    	    }
	    	}
	    	
	    	if ( regElement.getRegulatoryElementType().getName().equals( "Transcriptional2" ) ) {	    		
	    		engineeredTransgeneForm.setTranscriptional2_name( regElement.getName() );	    		
	    		tax_reg1 = regElement.getTaxon();	    	
	    			
	    		if ( dropdownTaxList.contains( tax_reg1.getScientificName() ) ) {	    	
	    		    engineeredTransgeneForm.setTranscriptional2_species( tax_reg1.getScientificName() );
	    	    } else {
	    		    engineeredTransgeneForm.setTranscriptional2_species( "Other" );
	    		    engineeredTransgeneForm.setTranscriptional2_otherSpecies( tax_reg1.getScientificName() );
	    	    }
	    	}
	    	
	    	if ( regElement.getRegulatoryElementType().getName().equals( "Transcriptional3" ) ) {	    		
	    		engineeredTransgeneForm.setTranscriptional3_name( regElement.getName() );	    		
	    		tax_reg1 = regElement.getTaxon();	    	

	    		if ( dropdownTaxList.contains( tax_reg1.getScientificName() ) ) {	    	
	    		    engineeredTransgeneForm.setTranscriptional3_species( tax_reg1.getScientificName() );
	    	    } else {
	    		    engineeredTransgeneForm.setTranscriptional3_species( "Other" );
	    		    engineeredTransgeneForm.setTranscriptional3_otherSpecies( tax_reg1.getScientificName() );
	    	    }
	    	}	    
	    	
	    	if ( regElement.getRegulatoryElementType().getName().equals( "Poly A Signal" ) ) {	    		
	    		engineeredTransgeneForm.setPolyASignal_name( regElement.getName() );	    		
	    		tax_reg1 = regElement.getTaxon();	    	
	    			
	    		if ( dropdownTaxList.contains( tax_reg1.getScientificName() ) ) {	    	
	    		    engineeredTransgeneForm.setPolyASignal_species( tax_reg1.getScientificName() );
	    	    } else {
	    		    engineeredTransgeneForm.setPolyASignal_species( "Other" );
	    		    engineeredTransgeneForm.setPolyASignal_otherSpecies( tax_reg1.getScientificName() );
	    	    }
	    	}
	    	
	    	if ( regElement.getRegulatoryElementType().getName().equals( "Splice Site" ) ) {	    		
	    		engineeredTransgeneForm.setSpliceSites_name( regElement.getName() );	    		
	    		tax_reg1 = regElement.getTaxon();	    	
	    			
	    		if ( dropdownTaxList.contains( tax_reg1.getScientificName() ) ) {	    	
	    		    engineeredTransgeneForm.setSpliceSites_species( tax_reg1.getScientificName() );
	    	    } else {
	    		    engineeredTransgeneForm.setSpliceSites_species( "Other" );
	    		    engineeredTransgeneForm.setSpliceSites_otherSpecies( tax_reg1.getScientificName() );
	    	    }
	    	}
	    }
	    
	    //MGI Number
	    MutationIdentifier inMutationIdentifier = theEngineeredTransgene.getMutationIdentifier();
	    if ( inMutationIdentifier != null )
	    	engineeredTransgeneForm.setNumberMGI( inMutationIdentifier.getNumberMGI().toString() );	    
	   
	    //Gene Function
    	Object[] geneList = theEngineeredTransgene.getGeneFunctionCollection().toArray();
    	System.out.println( "\t collection.size=" +theEngineeredTransgene.getGeneFunctionCollection().size() );
    	String geneFunction = "";
    	
    	for( int i=0; i<geneList.length; i++ ) {    		
    		GeneFunction inGeneFunction = (GeneFunction) geneList[i];
    		geneFunction += inGeneFunction.getFunction();    		
    		if ( i != geneList.length - 1)
    			geneFunction += ", ";    		
    		System.out.println( "inGeneFunction.getFunction()" + inGeneFunction.getFunction() );
    	}
    	engineeredTransgeneForm.setGeneFunctions( geneFunction );
    	
	    //Conditionality
	    Conditionality inConditionality = theEngineeredTransgene.getConditionality();
	    engineeredTransgeneForm.setConditionedBy( inConditionality.getConditionedBy() );
	    engineeredTransgeneForm.setDescription( inConditionality.getDescription() );
	    
	    //Additional Features / Comments
	    engineeredTransgeneForm.setComments( theEngineeredTransgene.getComments() );
	    
	    //Image
	    Image inImage = theEngineeredTransgene.getImage();
	    if ( inImage != null ) {
	    	engineeredTransgeneForm.setTitle( inImage.getTitle() );
	    	engineeredTransgeneForm.setFileServerLocation( inImage.getFileServerLocation() );
	    	engineeredTransgeneForm.setDescriptionOfConstruct( inImage.getDescription() );
	    	
	    	//TODO: Display a message on the current image, uploading another image will replace current image
	    	//TODO: Display thumbnail and viewer for image already uploaded
	    }
	    
	    request.getSession().setAttribute(Constants.FORMDATA, engineeredTransgeneForm);       
	
	    // setup dropdown menus
	    this.dropdown(request, response);
	    
	    return mapping.findForward( "submitEngineeredTransgene" );
	}
	
	public ActionForward dropdown( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) 
		throws Exception {
	
	    System.out.println( "<EngineeredTransgenePopulateAction dropdown> Entering dropdown()" );
	
	    // blank out the FORMDATA Constant field
	    EngineeredTransgeneForm engineeredTransgeneForm = (EngineeredTransgeneForm) form;	    
	    request.getSession().setAttribute(Constants.FORMDATA, engineeredTransgeneForm);         
	    
	    // setup dropdown menus
	    this.dropdown( request, response );
	    
	    return mapping.findForward( "submitEngineeredTransgene" );
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
	
	    System.out.println( "<EngineeredTransgenePopulateAction dropdown> Entering void dropdown()" );
	    
	    NewDropdownUtil.populateDropdown( request, Constants.Dropdowns.HOSTSPECIESDROP, "" );
	    
	    System.out.println( "<EngineeredTransgenePopulateAction dropdown> Exiting void dropdown()" );
	}	
}
