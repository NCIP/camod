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
                          Version 2.0
                         December 21, 2005

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
    1.0 - caMOD Version 1.0 Introduction and History
================================================================
 

    caMOD 2.0, the Cancer Models Database, is a open source data management 
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

    caMOD Version 2.0
    --	December 21 2005    

    caMOD Version 1.0
    --	Fall 2001; continously updated until April 2005

================================================================
    3.0 New Features and Updates
================================================================

caMOD 2.0 is a caBIG Silver level compliant application. 
For caBIG compatibility information see 
http://https://cabig.nci.nih.gov/guidelines_documentation

The caMOD Common Data Elements (CDEs) are available via caDSR 
(cancer Data Standard Repository) http://ncicb.nci.nih.gov/core/caDSR


General
- Utilization of NCI Thesaurus
- Utilization of the Common Security Module (CSM) for user authentication

Submission

After the user logs in the submission his/her previously entered models 
and models records in which he/she is listed a Principal Investigator 
are listed.

- Model Characteristics: 

	The scope of the cancer models database was expanded to include tool mice. 
	Tool mice or tool animals are strains that can be used to generate cancer 
	models and do normally not develop tumors. Tool mice are commonly used to 
	generate conditional knock out animals. 
	
	Examples for tool strains are Cre-expressing strains or strains that carry 
	one or more floxed genes. 


- Genetic Description 

	A field was added to all pages in this part of the application to 
	allow the submitter to enter the MGI (Mouse Genome Informatics) identifier
	which will enable the retrieval of additional information from 
	the informatics resources at the Jackson Laboratory.

	The Targeted Modification page and the induced mutation page allow 
	the user to enter the Gene Entrez ID for the affected gene. This 
	will enable us to provide users with additional information about the gene(s).

	Induced mutation contains a field for Gene Entrez ID for the affected gene. 
	If a chemical triggered the mutation the user has the option to enter the 
	CAS number for the chemical that was used.

	A submission page for Spontaneous Mutation was added.

	The submission pages for induced and spontaneous mutation contain fields to 
	gather information about the genetic changes that occurred in the models as 
	well as the methods of observation with which these changes were detected.


- Carcinogenic Intervention

	A page for entering information on Gene Delivery was added to this part 
	of the application. Gene deliveries are done by viruses that carry a gene. 
	
	All pages of the carcinogenic interventions part were updated to include 
	fields for gender, age at treatment, and administrative route (if applicable). 
	The Chemical / Drug page allows the user to enter the NSC and/or CAS number 
	of the chemical used in this experiment. 


- Publication

	The user is asked to indicate if a particular publication the existence of the 
	animal model was reported for the first time. Eventually this feature will be 
	used to link to records in the Mouse Tumor Biology Database at the Jackson Laboratory. 


- Histopathology

	Users can submit information about clinical markers and their values that were
	found in or are associated with specific tumor types.
	
	A field for describing the methods of observation / detection of genetic changes 
	in the tumors was added. 
	
	Users can enter data found in other species in particular humans in the 
	?Comparative Data? field.


- Cell lines


- Therapeutic Approaches

	To better identify the drug, users can enter the NSC and the CAS number 
	for the compound.

	More detailed information about the therapy is gathered. The following 
	fields have been added: age at treatment, gender, dosage, administrative route.

	The results of the experiment can be described in the biomarker and tumor 
	response fields. 


- Microarray

	Micrroarray data are stored in MAGE-compliant caArray application instead of 
	the Gene Expression Data Portal (GEDP).


- Images

	The text field for entering the staining method was replaced by a 
	controlled vocabulary. 


- Xenograft

	Xenograft page was extended by the following fields: ATCC number, Cell amount, 
	graft type


- Model Availability

	Users can now enter information about the availability of the animal model
	from more sources as in caMOD 1.0. They can also indicate if the tool strains 
	that were used to generate the model are available from one or more sources. 
	The users can make multiple entries per model.
	

Administration

Users who have been assigned the roles of coordinator, screener, and editor 
will be presented with a to-do list of tasks when the access 
the Admin part after logging in.


Search

- Simple Search

	The simple search was extended by a keyword search option.

- Advanced Search

	The advanced search includes now a query option for drugs or compounds 
	used in therapeutic experiments, an option to query for models that develop 
	metastases, and a keyword search.

- Drug Screening 

	The user can query for results of drug tested in yeast strains, 
	xenograft and allograft mouse models (titled for this purpose as in-vivo models),
	pre-clinical models, and humans. The tests in yeast and in-vivo models 
	were performed by the Developmental Therapeutics Program of the National 
	Cancer Institute. Information about pre-clinical models is retrieved from the 
	models part of caMOD, and human data are retrieved via caBIO from clinical trials. 
		

- Search detail pages
	The therapeutic approaches pages includes information about drug screens in yeast 
	and in-vivo, as well as clinical trials.

    
================================================================
    4.0 Defects Fixed Since Last Release
================================================================
	

	None


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
    --  http://ncicbsupport.nci.nih.gov/sw/


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