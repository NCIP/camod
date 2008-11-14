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
                          Version 2.4
                           Dec 2008

       National Cancer Institute's Center for Bioinformatics



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
 

    caMOD 2.5, the Cancer Models Database, is a open source data management 
    system developed for the management and sharing of data of animal models. 
    caMOD features controlled vocabularies from a shared, publicly accessible metadata repository (caDSR) and enterprise vocabulary services (EVS) from the National 
    Cancer Institute.  

    -- http://cancermodels.nci.nih.gov

    caMOD is a product of the NCI Center for Bioinformatics and its partners. Visit 
    the NCICB web site and the emice website for more information: 
	
   -- http://ncicb.nci.nih.gov/
   -- http://emice.nci.nih.gov/ 


================================================================
    2.0 Release History
================================================================
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

- Grid
	* Programmatic access to caMOD is available through a caGRID data service (API). A data service allows data owners to share the 
	  data from a source (e.g. Oracle or MySQL relational databases) with their collaborators. caGRID is an infrastructure that connects 
	  data, research tools, scientists, and organizations to leverage their combined strengths and expertise in an open federated 
	  environment with widely accepted standards and shared tools.
		For more information about caGrid, see
		https://cabig.nci.nih.gov/workspaces/Architecture/caGrid. 

- Search 
	* Complete change of the search architecture to accomodate increased amount of data (GForge entry #5249, 16311, 16234)
	* Improved keyword search (GForge entries #16202, 15041)
	* Improved search for transgene and targeted modifications (GForge entry #15053)
	* Search for PubMed Identifier added to Advanced Search (GForge entry #11007)
	* Customization of the search results list improved (GForge entry #7474)
	
- Submission
	* Customization of publication submission page for rat model (GForge entry #9127)
	* Submission of URL for microarray experiments stored in locations other than caArray (GForge entry #5097)
	* Submission of URL for images stored in locations other than caIMAGE (GForge entry #7850)
	
- Other
	* New script improves data retrieval from PubMED (GForge entries #5173, 12064)


The caMOD 2.5 Common Data Elements (CDEs) are available via caDSR 
  
  
================================================================
    4.0 Defects Fixed Since Last Release
================================================================

- Left side search menu disappears when user customizes search result list, saves queries etc (GForge entry # 5204)
- Query not saved when search returns no models.(GForge entry # 7636)
- Drug screening search displays models that are not edited-approved (GForge #11697)
- Save query function broken (GForge #12042)
- Admin - View Model Assignment is not working (GForge entry #12108)
- Release date selection not maintaining value (GForge entry #12216)
- Dosage unit on Growth Factor page incorrectly stored and displayed (GForge entry #14332)
- TOC search does not clear search criteria of previously performed search (GForge entry #15026)

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