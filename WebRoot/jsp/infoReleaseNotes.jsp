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
                          Version 2.1
                         May 30th, 2006

       National Cancer Institute Center for Bioinformatics



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
    1.0 - caMOD Version 2.1 Introduction and History
================================================================
 

    caMOD 2.1, the Cancer Models Database, is a open source data management 
    system developed for the management and sharing of data of animal models. 
    caMOD features controlled vocabularies from a shared, publicly accessible metadata 
    repository (caDSR) and enterprise vocabulary services (EVS) from the National 
    Cancer Institute.  caMOD database also features Application Programming 
    Interfaces (APIs) for programmatic access to the data. 

    -- http://cancermodels.nci.nih.gov

    caMOD is a product of the NCI Center for Bioinformatics and its partners. Visit 
    the NCICB web site and the emice website for more information: 
	
   -- http://ncicb.nci.nih.gov/
   -- http://emice.nci.nih.gov/ 


================================================================
    2.0 Release History
================================================================

    caMOD Version 2.1
    --	May 30th 2006    
    
    caMOD Version 2.0
    --	December 21 2005    

    caMOD Version 1.0
    --	Fall 2001; continously updated until April 2005

================================================================
    3.0 New Features and Updates
================================================================

The caMOD 2.1 Common Data Elements (CDEs) are available via caDSR 
(cancer Data Standard Repository) http://ncicb.nci.nih.gov/core/caDSR


Submission

- Added Transient Interference section to support Zebrafish models.

Search

- Keyword search

  The keyword searched for is now hilighted in yellow on the search results pages.

- Simple Search

  The simple search now prompts the user with appropriate model descriptor/organ information based on the available models. 

- Advanced Search

  The advanced search now prompts the user with appropriate model descriptor/organ/disease information based on the available models. 

- Drug Screening 

  The drug screening search now prompts the user with appropriate NSC numbers based on the available data.
		
- Search results configurability

  A logged in user can customize the search results by selecting the number of results per page, and the information displayed on the search results screen.  
    
- Saving queries and query history

  A logged in user can view his/her query history as well as save particularly interesting queries for later use.
    
================================================================
    4.0 Defects Fixed Since Last Release
================================================================
	
    TestTrack items
    
	#225 - Allow user to clear Organ name on screens where it is not required.
	#289 - information if a mouse is a tool mouse doesn't show up on model characteristics search page.
	#296 - Wrong display of targeted vs random integration of transgene and genomic segment
	#297 - Missed camod:shorten tag for therapeutic approaches
	#345 - Users are only in https during login.  Eliminates IE nag messages when viewing images.
	#371 - Force/validate lowercase usernames on entry


================================================================
    5.0 Known Issues/Defects
================================================================
    
    1. Organ-specific preselection of diagnoses in the vocabulary trees is 
       not available
    
    2. Users need to have a caArray user account in order to submit data to 
        caArray, users need to inform caMOD team if they would like to have 
        experiments in caArray linked to their caMOD entries
        
    3. Genetic Description view screen does not include data for the following 
        sections: Libraries and Tissues (from EST data) or Protein Similarities 
        (from UniGene). caBio discontinued support for the Protein Similarities and 
        the performance for the Libraries and Tissues query did not meet minimum standards.  
        After meeting with the caBio team, it was concluded that the development team 
        will need to eliminate lazy fetching forlibrary and tissue attributes 
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


    NCICB Download Center
    --  http://ncicb.nci.nih.gov/download/

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