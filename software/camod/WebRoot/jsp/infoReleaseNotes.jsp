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
                          Version 2.6
                           January 2010

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
    1.0 caMOD Version 2.6 Introduction and History 
================================================================ 
  
 
    caMOD 2.6, the Cancer Models Database, is a open source data management 
    system developed for the management and sharing of data of animal models. 
    caMOD features controlled vocabularies from a shared, publicly accessible metadata repository (caDSR) and enterprise vocabulary services (EVS) from the National 
    Cancer Institute..   
 
    -- http://cancermodels.nci.nih.gov 
 
    caMOD is a product of the NCI's Center for Biomedical Informatics and Information Technology and its partners. Visit  
    the NCICB web site and the emice website for more information:  
	 
   -- http://ncicb.nci.nih.gov/ 
   -- http://emice.nci.nih.gov/  
 
 
================================================================ 
    2.0 Release History 
================================================================ 
     caMOD Version 2.6 
    --	January 2010
    
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
BDA
The goal of the build and deployment automation (BDA) effort was to create a consistent and repeatable build and deployment process allowing each team to create production-ready software every day.  
Development teams spend time communicating, verifying and fixing various deployment environments. The environment creation process is manual, non-repeatable and slow. Automation will make it fast and cheap, 
therefore it will become part of their daily tasks. This will reduce time spent on non-development tasks and the DEV teams can better concentrate on creative development tasks.

Carcinogenic Interventions part expanded
New categories for Antibodies, Bacteria, Plasmid, Transposon, and Signaling Molecule were added to the carcinogenic interventions part. Users can now report exposure to bacteria and the utilization of the Sleeping Beauty system in caMOD. (GForge entry #23552)

The caMOD 2.5 Common Data Elements (CDEs) are available via caDSR  
   
   
================================================================ 
    4.0 Defects Fixed Since Last Release 
================================================================ 
 
- Display comments submitted to the transplantion page (GForge entry #24176)
- Delete orphaned disease records (GForge entry #23904)
- Display information stored in database when EVS does not return values (GForge entry #23751)
- Clean up microarray data table GForge entry #23551)
- Delete orphaned records in histopathology table (GForge entry #24379)
 
 
================================================================ 
    5.0 Known Issues/Defects 
================================================================ 
     
    1. Organ-specific pre-selection of diagnoses in the vocabulary trees is 
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