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
                          Version 2.2
                         November 30th, 2006

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
 

    caMOD 2.2, the Cancer Models Database, is a open source data management 
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

Improved Search functions
- Search for models with images (TestTrack entry #379)
- Search for tool strains (TestTrack entry #395)
- Search for data from external sources (TestTrack entry #380)


Improved Search Results list
- The listing of models in the search results list contains icons which indicate the source of the data e.g. from the Mouse Tumor Biology database (TestTrack entry #458) or the availability of associated microarray and/or image data for a particular record. (TestTrack entry #396)


Nomenclature names and Genotype information
- Users can now add information about the genotype of the model as well as the official strain designation (TestTrack entry #356)


siRNA experiments 
- caMOD supports the submission and retrieval of siRNA experiments (TestTrack entry #385)


Online Help
- A new version of online help including a downloadable user guide (PDF file) has been integrated. Help icons and tool tips have been modified to promote intra and inter-application consistency. (TestTrack entries #229, 413, 452) The documentation itself has been updated (TestTrack entries #286)
Integration of the new online help (a few typo fixes) and the new user documentation. (TestTrack entry #413)


Superuser Admin functions
- The privileges of a super user were expanded from being able to access and modify all models to being able to access, modify, change state, and inactivate all model entries. (TestTrack entry #389, 449, 450)


Integration of MTB data
- The complete Mouse Tumor Biology Data set (http://tumor.informatics.jax.org) has been inserted in caMOD. We would like to thank our colleguages at the Jackson Laboratory in Bar Harbor, Maine for their support with this effort.
In order to prepare caMOD for the MTB data the following changes had to be made:
	- Add external_source and external_source_identifier to AnimalModel object. (TestTrack entry #391)
	- mark histopathology records with 0% tumor incidence rate.(TestTrack entry #448)
	- Modify Organ object to display data for organ allows for conceptCodes = '00000'.(TestTrack entry #451)
	- Modify viewCarcinogenicInterventions to display data from MTB (JAX(TestTrack entry #453) 
	- Add ageOfDetection (and unit) to submission screen (also searchable). (TestTrack entry #455)


Externalize properties
- Configure the production hibernate.cfg.xml file to not print SQL to the log (TestTrack entry #408)
- Work with the systems group to come up with a scheme for externalizing properties (TestTrack entry #410)
- Modify the properties files to point to the appropriate tiers for all external applications (TestTrack entry #411)


Improvements to EVS vocabulary tree
The performance of the EVS vocabulary trees for the retrieval of controlled vocabularies for anatomy and disease was improved by adding a caching mechanism (TestTrack entries #400 and 438)


Data issues
- NIH intramural researchers used caMOD to store information about models available on the NIH campus. These entries were submitted to earlier versions of caMOD. They had to be completed in order to comply with the current standards of model submission to caMOD (TestTrack entry #366)
- Earlier versions of caMOD did not include a field for submission of the MGI (Mouse Genome Informatics) identifier for modified alleles. The MGI numbers have been added for the existing allele records (TestTrack entry #418)
  

The caMOD 2.1 Common Data Elements (CDEs) are available via caDSR 
(cancer Data Standard Repository) http://ncicb.nci.nih.gov/core/caDSR
  
  
  
================================================================
    4.0 Defects Fixed Since Last Release
================================================================
	
Defect #37:  Search for anatomy synonyms returns diseases and drugs
Defect #276: EVS tree - use display name instead of concept name
Defect #283: EVS tree - the search function is not working which also prevents users from accessing the field to manually enter diagnoses
Defect #295: Verify all free text fields allow html markup (#420, #422, #423, #429, #430, #431, #434, #439, #442, #443).
Defect #373: Transcriptional (Promoter): should be Transcriptional
Defect #374: Rearrange the order of items in the administrative route dropdown list
Defect #406: Clean up email code for assigning a model to a screener and an editor
Defect #412: Modify EVSTree so that it points to the appropriate tiers for the caBIO api
Defect #426: Remove duplicated entries on search pages with highlight tag
Defect #440: Link to Jackson lab in availability search page needs to be updated.
Defect #441: entry of incidence rate on histopathology page cannot be deleted.
Defect #447: Clean up legal rules of the road page.
Defect #454: Can not duplicate models with TransientInterferenceMethod data.
Defect #461: App support does not receive emails with requests for new user accounts.
Defect #462: customizing search results doesn't work when carcinogen is selected.



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