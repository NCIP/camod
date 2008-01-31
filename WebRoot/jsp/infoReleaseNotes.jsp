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
                           Feb 2008

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
 

    caMOD 2.4, the Cancer Models Database, is a open source data management 
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

caELMIR Integration

caELMIR, the Cancer Electronic Laboratory Management Information Resource (http://caelmir.compmed.ucdavis.edu/caelmir/) is a laboratory information management tool that helps bench scientists to store, manage, and share experimental data generated during preclinical trials. The connection between caMOD and caELMIR  enables caMOD users to retrieve data generated in preclinical trials and review information about studies, experiments, protocols, and animals. The caELMIR study information is shown on the Therapeutic Approaches search page in caMOD. For additional data, the user will be directed to caELMIR. (GForge entry #8816)


Object Model changes as a result of the VCDE silver level compliance review:

Following the recommendations made by the Vocabulary and Common Data Elements Workgroup (https://cabig.nci.nih.gov/workspaces/VCDE) during the caMOD review the following changes were made:
- Introduction of a gene object to comply with the gene identifier standard (GForge entry #9756, #8187, #11878)
- Rename UnctrlVocab items to Alterntext entries (GForge entry #9756)
- Rename Graft object into Transplant object (GForge entry #8290)
- Add Comments field to every submission page (GForge entry #8355)
- Connect availability of model to person to resolve the available from investigator issue (GForge entry #9169)

Other Changes
- Redesigned the publication search page (GForge entry #5169, #10679)
- Redesigned publication table on cell line and therapy search page  (GForge entry #10683)
- Improved navigation within the therapy search page. These changes were caused by the integration of the caELMIR data and the redesign of the publication table (GForge entry #10504)
- Updated online help including a downloadable user guide (PDF file)


NCICB Technology Stack Requirements (GForge entry #6779, #7079, #11880)
- Upgrade to Jboss 4.0.5 from 4.0.2 
- Upgrade to newer version of ANT for project
- Upgrade to use caCORE32 in caMOD and EVSTree to render vocabulary trees

The caMOD 2.1 Common Data Elements (CDEs) are available via caDSR 
(cancer Data Standard Repository) http://ncicb.nci.nih.gov/core/caDSR
  
  
================================================================
    4.0 Defects Fixed Since Last Release
================================================================

- ATCC link not working on transplant search page (GForge entry #11878) 
- The left menu list needs to be changed for the viewInvivoDetails.jsp (GForge entry #11837, #9206) 
- Comments for associated expression are not shown for targeted modification and genomic segment (GForge entry #11833) 
- Maximize buttion in pop-up windows disabled which prevents user to see full page (GForge entry #11831)
- Fixed LDAP search function (GForge entry #7992, #8963, #11499)
- Data in construct description and construct title are not kept (GForge entry #11619)
- Fixed search function for rat anatomy, rat diseases, zebrafish anatomy, zebrafish developmental stages, and staining method vocabularies displayed in EVS tree (GForge entry #6389, 7387, 7451, 7790, 9274, 9277, 9425, 10818, 11022)
 


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