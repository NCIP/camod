<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<!-- Main Content Begins -->
<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<!-- Took this out of sidebar.jsp and has to go here to format correctly -->
	<%@ include file="/jsp/searchMenu.jsp" %>
    <tr>
        <td>
			<TABLE summary="" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
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
                          Version 2.7
                          Feb 2011

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
    1.0 caMOD Version 2.7 Introduction and History 
================================================================ 
  
 
    caMOD 2.7, the Cancer Models Database, is a open source data management 
    system developed for the management and sharing of data of animal models. 
    caMOD features controlled vocabularies from a shared, publicly accessible metadata repository (caDSR) and 
    enterprise vocabulary services (EVS) from the National 
    Cancer Institute..   
 
    -- http://cancermodels.nci.nih.gov 
 
    caMOD is a product of the NCI's Center for Biomedical Informatics and Information Technology and its partners. 
    Visit the NCICB web site and the emice website for more information:  
	 
   -- http://ncicb.nci.nih.gov/ 
   -- http://emice.nci.nih.gov/  
 
 
================================================================ 
    2.0 Release History 
================================================================ 
     caMOD Version 2.7
    --	Feb 2011

     caMOD Version 2.6.2
    --	Oct 2010
    
     caMOD Version 2.6.1
    --	June 2010
    
     caMOD Version 2.6 
    --	March 2010
    
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
LexEVS connection upgraded
The connection to the LexEVS API was upgraded to use version 5.1 for the EVSTree application.  The data displayed to the user remains the same. 
The search functionality for the vocabulary trees has been upgraded.

   
================================================================ 
    4.0 Defects Fixed Since Last Release 
================================================================ 
 
Misspelling on transgene submission page (JIRA# CAMOD-954)
Retrieve organ information for search results pages from DB not directly from LexEVS (JIRA # CAMOD-969)
Metastases are gone from earlier models (JIRA# CAMOD-974)
Therapeutic approaches search page not shown - user gets blank page (JIRA# CAMOD-975)
Submission of Zebrafish with Thearpy and developmental stages throws error (JIRA# CAMOD-976)
 
================================================================ 
    5.0 Known Issues/Defects 
================================================================ 
     
    1. Organ-specific pre-selection of diagnoses in the vocabulary trees is 
       not available
    
    2. Genetic Description view screen does not include data for the following 
        sections: Libraries and Tissues (from EST data) or Protein Similarities 
        (from UniGene). caBio discontinued support for the Protein Similarities and 
        the performance for the Libraries and Tissues query did not meet minimum standards.  
        After meeting with the caBio team, it was concluded that the development team will need to eliminate lazy fetching for 
        library and tissue attributes if we want to improve performance.
     
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