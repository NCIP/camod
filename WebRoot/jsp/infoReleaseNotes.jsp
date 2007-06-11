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
                          Version 2.3
                         June, 2007

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
    1.0 - caMOD Version 2.3 Introduction and History
================================================================
 

    caMOD 2.3, the Cancer Models Database, is a open source data management 
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
    caMOD Version 2.3 (Iteration 1)
    --	June 2007 
        
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

Expanded EVS vocabulary support
- Update EVS Tree application to render additional species-specific vocabularies (Rat anatomy and disease and staining methods) (gforge entry #6378)
- Upgrade EVSTree app to render anatomy and developmental stage vocabulary trees for Zebrafish from the DTS server (gforge entry #6379)

Improved functionality for submission and search with Zebrafish models:
- ZEBRAFISH - genetic description pages - link to zfin.org for allele details or to Rat Genome Database (gforge entry #6776)
- ZEBRAFISH - publication search page - need to link to zfin.org (gforge entry #6774)
- Pages that use anatomy vocabulary trees need to show vocabularies depending on selected species (gforge entry #5897)
- Add conditioning regimen dropdown to xenograft page (gforge entry #4797)
- ZEBRAFISH - therapy page - add drop down list for developmental stage (gforge entry #4796)
- Add "small molecules" to chemical class list on therapy submission page (gforge entry #4795)
- ZEBRAFISH - availability pages - add page for zfin, keep investigator page (gforge entry #4794)
- ZEBRAFISH - histopathology page - make diagnosis field free text or used Hatem's list (gforge entry #4793)
- ZEBRAFISH - add drop down list for delivery method to morpholino submission and search page (gforge entry #4792)
- ZEBRAFISH - add drop down list for Target Site to morpholino submission and search page (gforge entry #4791)
- ZEBRAFISH - add age drop down list to model characteristics submission and search page (gforge entry #4790)
- ZEBRAFISH - anatomy vocabulary for histopathology page (gforge entry #4781)

Retrieve staining vocabulary from EVS
- EVS Tree code added to Image section - utilize the staining vocabulary (gforge entry #4759)

Simple and advanced search enhancements
- Make species drop down list on simple and advanced search a dynamic drop down list depending on what model species have approved records (gforge entry #6148)

Online Help
- A new version of online help including a downloadable user guide (PDF file) has been updated. 


The caMOD 2.1 Common Data Elements (CDEs) are available via caDSR 
(cancer Data Standard Repository) http://ncicb.nci.nih.gov/core/caDSR
  
  
================================================================
    4.0 Defects Fixed Since Last Release
================================================================
	
Defect #5284:  	Fix MGI link on genetic description pages and at the same time fix existing data
Defect #5267: 	MTB radiation data are not shown
Defect #5259: 	Adv search for carcinogens does not include data from Jackson Lab
Defect #5236: 	Fix Jax lab availability link and data - part 2
Defect #5227: 	Customizing search results doesn't work when carcinogen is selected

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
        
    4.  validation for the histopathology submission screen does not
    	work for all four cases of vocabularies.
    	
         
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