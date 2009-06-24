<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<!-- Main Content Begins -->
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
    <tr>
        <td>
			<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
				<tr>
				    <td valign="top">
						<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
							<tr>
							    <td>
									<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
										<tr>
											<td class="formTitle" height="20">Release Notes</td>				
										</tr>			
										<tr>
											<td class="resultsBoxWhiteEnd">
<pre> 
                         Release Notes 
     
                             caMOD
                          Version 2.5.1
                           June 2009

       National Cancer Institute's Center for Biomedical Informatics and Information Technology 
 
 
 
================================================================ 
                            Contents 
================================================================ 
     
    1.0 caMOD Introduction and History 
    2.0 Release History 
    3.0 New Features and Updates 
    4.0 Bugs Fixed Since Last Release 
    5.0 Known Issues/Defects 
    6.0 Bug Reports and Support 
    7.0 NCICB Web Pages 
 
 
================================================================ 
    1.0 caMOD Version 2.4 Introduction and History 
================================================================ 
  
 
    caMOD 2.5.1, the Cancer Models Database, is a open source data management 
    system developed for the management and sharing of data of animal models. 
    caMOD features controlled vocabularies from a shared, publicly accessible metadata repository (caDSR) and enterprise vocabulary services (EVS) from the National 
    Cancer Institute..   
 
    -- http://cancermodels.nci.nih.gov 
 
    caMOD is a product of the NCI Center for Bioinformatics and its partners. Visit  
    the NCICB web site and the emice website for more information:  
	 
   -- http://ncicb.nci.nih.gov/ 
   -- http://emice.nci.nih.gov/  
 
 
================================================================ 
    2.0 Release History 
================================================================ 
    caMOD Version 2.5.1  
    --	June 2009
    
    caMOD Version 2.5  
    --	Dec 2008  
     
    caMOD Version 2.4  
    --	Feb 2008  
 
    caMOD Version 2.3  
    --	Sept 2007  
         
    caMOD Version 2.2 
    --	November 30th 2006  
     
    caMOD Version 2.1.1 
    --	October 16th 2006  
     
    caMOD Version 2.1 
    --	May 30th 2006     
     
    caMOD Version 2.0 
    --	December 21 2005     
 
    caMOD Version 1.0 
    --	Fall 2001; continously updated until April 2005 
 
================================================================ 
    3.0 New Features and Updates 
================================================================ 
 
- EVS API upgrade 
		* 	The EVS (formerly part of caCORE ) API is a public domain, open source wrapper that provides 
            full access to the LexBIG Terminology Server. LexBIG hosts the NCI Thesaurus, the 
            NCI Metathesaurus, and several other vocabularies (like Zebrafish).
            
            The EVS 3.2 model, exposed as part of caCORE 3.2, has been re-released with
            LexBIG as the back-end terminology service in place of the proprietary Apelon DTS
            back end. The SDK 4.0 was used to generate the EVS 3.2 Java API, as well as the SOAP
            and HTTP REST interfaces.  The only difference between the EVS 3.2 API exposed as part of the caCORE EVS 4.x
            and the API exposed as part of caCORE 3.2 is the back-end terminology server used to
            retrieve the vocabulary data.             
            
            caMOD will upgrade to use the EVS 4.2 API to get the Display Name or Preferred Name for its
            vocabulary entries. 
             
            EVSTree has also been upgraded to retrieve all 8 vocabularies from the enw EVS 4.2 API.   
 
- caBIO API upgrade  
		*   The caMOD application connects to caBIO to retrieve additional data for the Therapeutic Approaches 
            and Genetic Description search results screens.  On the Therapeutic Approaches search results screen caMOD 
            displays clinical trials data from caBIO.  On the Genetic Description search results screen caMOD displays 
            additional data within the Targeted Modification section for "Gene Info" (official nomenclature of the gene), 
            Database Links to UniGene and CGAP, "Gene Ontology" (controlled vocabulary to describe gene and gene product attributes), 
            and "BioCarta Pathways".  

            caMOD will upgrade to use the caBIO 4.0 API (jars match EVS 4.2) to get additional data from caBIO. 
	 
- Add link to additional info about clinical trials such as types of cancer the trial is conducted for (GForge entry #4763)
- Make PMID field on advanced search screen user-friendly by adding a link to PubMed and trim functions (GForge entry #17040)
- Add rabbit to species list (GForge entry #18338)
- Sort therapies in the order they are entered (GForge entry #19205)
- Change data type of start_page and end_page in publication pages (GForge entry #20997)
- Upgrade validation for CE and IM in searchForm - flag added in last release (GForge entry #21177)
- Change link to NIH privacy notice (GForge entry #21552)
- Delete "Privacy Notice" (title only) from Use Guideline page (GForge entry #21553)
- Change root node for rat disease vocabulary (GForge entry #21634)
- Delete "Data not stored in caArray" from the microarray search page (GForge entry #21727)
 
 
The caMOD 2.5 Common Data Elements (CDEs) are available via caDSR  
   
   
================================================================ 
    4.0 Defects Fixed Since Last Release 
================================================================ 
 
- Do not delete EnvironmentalFactor objects - instead reuse and leave in DB (GForge entry #5172)
- Amount of cells on the transplantation submisson screen needs to accept 10^6 instead of a number (validation) (GForge entry #5235)
- Construct sequence field on genetic description screens are independent of image submission (GForge entry #11623)
- Why does unpublished entry not show up on publication, cell lines, or therapeutic approaches search screen (GForge entry #12483)
- Make sure all field sizes in database are validated correctly in validation.xml (GForge entry #17320)
- Make sure all references to Transplantation are properly named (GForge entry #17833)
- No other zebrafish diagnosis shown on search screen (GForge entry #18267)
- MTB data not displayed - causing crash (GForge entry #18754)
- Gene Ontology information coming from caBIO not displayed (GForge entry #19459)
- Blank therapy search page when NSC number is entered (GForge entry #20033)
- Customized search for Transplantation Donor Species not working (GForge entry #20715)
- Zebrafish organs are not displayed when opening up existing models in submit - edit mode (GForge entry #21635)
- Validation too stringent on image page for existing models (GForge entry #21636)
- Transplant search and submission pages do not display human organs (GForge entry #21637)
- Manually submitted diagnoses disappear in submit / edit model mode (GForge entry #21639)
- Transplantation page for old models does not open in Edit Mode (GForge entry #21726)
 
================================================================ 
    5.0 Known Issues/Defects 
================================================================ 
     
    1. Organ-specific preselection of diagnoses in the vocabulary trees is 
       not available
    
    2. Genetic Description view screen does not include data for the following 
        sections: Libraries and Tissues (from EST data) or Protein Similarities 
        (from UniGene). caBio discontinued support for the Protein Similarities and 
        the performance for the Libraries and Tissues query did not meet minimum standards.  
        After meeting with the caBio team, it was concluded that the development team will need to eliminate lazy fetching for library and tissue attributes 
        if we want to improve performance.
     
    Please report any defects found to application support.
 
 
================================================================ 
    6.0 Bug Reports and Support 
================================================================ 
     
    Send email to ncicb@pop.nci.nih.gov to request support. 
 
    In addition, mailing lists are used by the caMOD developer 
    and user community to exchange ideas and make announcements. 
 
    You can subscribe at this address: http://list.nih.gov 
 
    caMOD users 
        CAMOD_USERS-L 
         
    caMOD developers 
       CAMOD_DEVELOPERS-L  
 
================================================================ 
    7.0 NCICB Web Pages 
================================================================ 
     
    The NCI Center for Bioinformatics 
    --  http://ncicb.nci.nih.gov/ 
     
    NCICB Application Support 
    --  http://ncicb.nci.nih.gov/NCICB/support 
 
    caMOD 
    --  http://cancermodels.nci.nih.gov/ 
     
     
</pre>

											</td>
										</tr>
									</TABLE>
							    </td>
							</tr>
						</TABLE>
				    </td>
				</tr>
			</TABLE>
        </td>
    </tr>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>