<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<!-- Main Content Begins -->
<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<!-- Took this out of sidebar.jsp and has to go here to format correctly -->
	<%@ include file="/jsp/searchMenu.jsp" %>
    <tr>
        <td>
			<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
				<tr>
				    <td valign="top">
						<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
							<tr>
							    <td>
									<TABLE summary="This table is used to format page content" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
										<tr>
											<td class="formTitle" height="20">Release Notes</td>				
										</tr>			
										<tr>
											<td class="resultsBoxWhiteEnd">
<pre> 
 Release Notes 
     
                             caMOD
                          Version 2.7.1
                          September 2012

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
    1.0 caMOD Version 2.7.1 Introduction and History 
================================================================ 
  
    caMOD 2.7.1, the Cancer Models Database, is a open source data management 
    system developed for the management and sharing of data of animal models. 
    caMOD features controlled vocabularies from a shared, publicly accessible metadata repository (caDSR) and 
    enterprise vocabulary services (EVS) from the National 
    Cancer Institute.   
 
    -- http://cancermodels.nci.nih.gov 
 
    caMOD is a product of the NCI's Center for Biomedical Informatics and Information Technology and its partners. 
    Visit the NCICB web site and the emice website for more information:  
	 
   -- http://ncicb.nci.nih.gov/ 
   -- http://emice.nci.nih.gov/  
 
 
================================================================ 
    2.0 Release History 
================================================================ 
     caMOD Version 2.7.1
    --	September 2012
     
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

None.
   
   
================================================================ 
    4.0 Defects Fixed Since Last Release 
================================================================ 

PubMed URL has changed and needed an update to use the XML format (JIRA# CAMOD-998)
Added a disclaimer when redirecting to external sites (JIRA# CAMOD-999)
MGI link to phenotypic allele detail page is broken for all engineered gene categories except targeted modification while using Firefox (JIRA# CAMOD-1000)
Registration fields accepting Invalid Data has been fixed. (JIRA# CAMOD-1004)
Completed activities to make caMOD 508 compliant.
 
================================================================ 
    5.0 Known Issues/Defects 
================================================================ 
     
    1. Organ-specific pre-selection of diagnoses in the vocabulary trees is 
       not available
    
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
    7.0 CBIIT Web Pages 
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