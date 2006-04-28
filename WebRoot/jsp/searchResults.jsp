<%

/**
 * 
 * $Id: searchResults.jsp,v 1.15 2006-04-28 19:39:21 schroedn Exp $
 * 
 * $Log: not supported by cvs2svn $
 *
 */

%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Histopathology" %>
<%@ page import="gov.nih.nci.camod.domain.SavedQuery" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="gov.nih.nci.camod.util.BuildCriteriaTable" %>
<%@ page import="gov.nih.nci.camod.webapp.form.SearchForm" %>
<%@ page import="gov.nih.nci.camod.webapp.form.SaveQueryForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%      
	List results = (List) request.getSession().getAttribute( Constants.SEARCH_RESULTS );
	
	if (results == null) {
	    results = new ArrayList();
	    request.getSession().setAttribute(Constants.SEARCH_RESULTS, results );
	}
	
	int size = results.size();
	System.out.println( "SIZE: " + size );
	
	String pageSize = (String) request.getSession().getAttribute( Constants.ITEMSPERPAGE );
		if (pageSize == null) pageSize = "15";
		
	String[] resultColumns = (String[]) request.getSession().getAttribute( Constants.SEARCHRESULTCOLUMNS );
   	    if ( resultColumns == null ) resultColumns = new String[] { "Model Descriptor", "Tumor Sites", "Species" };   //default        
  	  
	String noSaveOption = request.getParameter("noSaveOption");  	  	 
%>

<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	
	<%					
		//Display search criteria of executed search
		BuildCriteriaTable newTable = new BuildCriteriaTable();
		String criteriaTable = newTable.buildCriteriaDisplayTable( (SearchForm) request.getSession().getAttribute( Constants.SEARCH_FORM ) );
				
		//Check If this was a edited and resubmitted query
		String aSavedQueryId = (String) request.getSession().getAttribute( Constants.ASAVEDQUERYID );
		String aQueryName = (String) request.getSession().getAttribute( Constants.QUERY_NAME );
		String reRunQuery = (String) request.getSession().getAttribute( Constants.RERUN_QUERY );
		String dupName = (String) request.getSession().getAttribute( Constants.DUP_NAME );
		//reset
	 	request.getSession().setAttribute( Constants.DUP_NAME, null );
				
		//Check to see if a user is logged in, if not do not allow saving of queries
		String currentUser = (String) request.getSession().getAttribute( Constants.CURRENTUSER );
		
		// Get elapsed time in seconds
		float elapsedTime = ((Long) request.getSession().getAttribute( Constants.ELAPSED_TIME )).floatValue();
	    float elapsedTimeSec = elapsedTime/1000;

	%>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr>
			<td>
				<TABLE cellpadding="5" cellspacing="0" border="0" width="100%">
					<TR>
						<TD class="formTitle" height="20">Search Criteria ( <font color="red"><%= elapsedTimeSec %></font> seconds )</TD>
					</TR>
					<TR>
						<TD class="formFieldAll"><%= criteriaTable %></TD>
					</TR>
				</TABLE>								
			
				<% if ( dupName != null && dupName.equals("true") ) { %>
						<TABLE border="0" class="contentPage" width="100%">
						<TR>
							<TD align="left" colspan="2" width="100%">
								<font color="red">*The name "<%= aQueryName %>" is already being used, please choose a different name.</font><br>
					            </TD>
					        </TR>
				        </TABLE>								
				<%} %>
				
				<% if ( noSaveOption != null ) { %>
						<TABLE border="0" class="contentPage" width="100%">
							<TR>
								<TD width="40%">&nbsp;</TD>
								<TD align="right" width="60%">
									<font color="red">Query saved as "<%= aQueryName %>".</font><br>
					            </TD>
					        </TR>
				        </TABLE>		
				        <BR>
				<% } else if ( reRunQuery != null ) { %>
						<TABLE border="0" class="contentPage" width="100%">
							<TR>
								<TD width="40%">&nbsp;</TD>
								<TD align="right" width="60%">
									<font color="red">Query "<%= aQueryName %>" ran successfully.</font><br>
					            </TD>
					        </TR>
				        </TABLE>		
				        <BR>					        							
				<% } else if ( aSavedQueryId != null ) { %>
					<html:form action="SaveQueryAction.do?method=save" focus="queryName">
						<TABLE border="0" class="contentPage" width="100%">
							<TR>
								<TD width="20%">&nbsp;</TD>
								<TD align="left" width="80%">				
						 				<html:radio property="saveAsNew" value="no" />Update saved query "<%= aQueryName %>" with the new criteria. 
						 				<br>
										<html:radio property="saveAsNew" value="yes" />Save this criteria as a new saved query called <html:text styleClass="formFieldUnSized" value="<%= aQueryName %>" property="queryName" size="20"/>.             						           
										<br>
					                <input type="submit" value="Save Query" src="images/savequery.gif" />
					            </TD>
					        </TR>
				        </TABLE>
					</html:form>										
				<% } else if ( currentUser != null ) { %>				        				
					<html:form action="SaveQueryAction.do?method=save" focus="queryName">
						<TABLE border="0" class="contentPage" width="100%">
							<TR>
								<TD width="40%">&nbsp;</TD>
								<TD align="right" width="60%">
						            <html:text styleClass="formFieldUnSized" value="My Saved Query" property="queryName" size="20"/>
		            				<INPUT name="saveAsNew" value="yes" type="hidden"/>
					                <input type="submit" value="Save Query" src="images/savequery.gif" />
					            </TD>
					        </TR>
				        </TABLE>
					</html:form>
				<%} else { %><br><% } %>
						
			</td>
		</tr>
		
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	
				<tr>
					<td class="formTitle" height="20" colspan="4">Search Results&nbsp;<camod:cshelp mapId="search_results_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/>
					</td>
				</tr>
			</TABLE>
			
            <display:table id="row" name="${sessionScope.searchResults}"              		
	          		 pagesize="<%=pageSize%>"
	          		 cellpadding="5" 
	          		 cellspacing="0" 
	          		 border="1"
 	                 width="100%"
 	                 > 	 
 	                 
 	                 <display:column title="No.">
 	                     <c:out value="${row_rowNum}"/>
				     </display:column>  
				     
					<%
					    for (int i = 0; i < resultColumns.length; i++) {
					    	//System.out.println( "selectedColumnsToDisplay[" + i + "] = " + resultColumns[i] );
							
							if( resultColumns[i].equals("Model Descriptor") ) { %>
					             <display:column href="/camod/ViewModelAction.do?unprotected_method=populateModelCharacteristics&" paramId="aModelID" paramProperty="id" title="Model Descriptor" sortable="true" >
					                 <c:out escapeXml="false" value="${row.modelDescriptor}"/>
							     </display:column>															
							<% }						
							else if( resultColumns[i].equals("Species") ) { %>
							     <display:column title="Species" sortable="true">
							         <camod:highlight><c:out value="${row.species}"/></camod:highlight>
							     </display:column>  								
							<% }       							
							else if( resultColumns[i].equals("Tumor Sites") ) { %>
							     <display:column title="Tumor Sites" sortable="true" >
							         <camod:highlight><c:out escapeXml="false" value="${row.tumorSites}"/></camod:highlight>
							     </display:column>	 							
							<% } 
							else if( resultColumns[i].equals("Submitted by") ) { %>
							     <display:column title="Submitted by" sortable="true" >
							         <camod:highlight><c:out escapeXml="false" value="${row.submitterName}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Submitted Date") ) { %>
							     <display:column title="Submitted Date" sortable="true" >
							         <camod:highlight><c:out escapeXml="false" value="${row.submittedDate}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Strain") ) { %>
							     <display:column title="Strain" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.strain}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Model Id") ) { %>
							     <display:column title="Model Id" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.modelId}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Principal Investigator") ) { %>
							     <display:column title="Principal Investigator" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.principalInvestigator}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Gender") ) { %>
							     <display:column title="Gender" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.gender}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Transgene") ) { %>
							     <display:column title="Transgene" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.transgene}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Transcriptional 1") ) { %>
							     <display:column title="Transcriptional 1" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.transcriptional1}"/></camod:highlight>
							     </display:column>	 														     
							<% }												
							else if( resultColumns[i].equals("Segment Type") ) { %>
							     <display:column title="Segment Type" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.segmentType}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Designator") ) { %>
							     <display:column title="Designator" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.designator}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Targeted Gene / Locus") ) { %>
							     <display:column title="Targeted Gene / Locus" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.targetedGeneLocus}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Type of Modification") ) { %>
							     <display:column title="Type of Modification" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.typeOfModification}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Name of inducing agent") ) { %>
							     <display:column title="Name of inducing agent" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.nameOfInducingAgent}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Gene Name") ) { %>
							     <display:column title="Gene Name" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.geneName}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Chemical") ) { %>
							     <display:column title="Chemical" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.chemical}"/></camod:highlight>
							     </display:column>	 							
							<% }																																																																													 							
							else if( resultColumns[i].equals("Environmental Factor") ) { %>
							     <display:column title="Environmental Factor" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.environmentalFactor}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Viral Vector") ) { %>
							     <display:column title="Viral Vector" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.viralVector}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Gene") ) { %>
							     <display:column title="Gene" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.gene}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Growth Factor") ) { %>
							     <display:column title="Growth Factor" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.growthFactor}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Hormone") ) { %>
							     <display:column title="Hormone" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.hormone}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Nutritional Factor") ) { %>
							     <display:column title="Nutritional Factor" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.nutritionalFactor}"/></camod:highlight>
							     </display:column>	 							
							<% }								
							else if( resultColumns[i].equals("Radiation") ) { %>
							     <display:column title="Radiation" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.radiation}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Virus") ) { %>
							     <display:column title="Virus" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.virus}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Year of Publication") ) { %>
							     <display:column title="Year of Publication" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.yearOfPublication}"/></camod:highlight>
							     </display:column>	 							
							<% }								
							else if( resultColumns[i].equals("Journal") ) { %>
							     <display:column title="Journal" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.journal}"/></camod:highlight>
							     </display:column>	 							
							<% }								
							else if( resultColumns[i].equals("PMID number") ) { %>
							     <display:column title="PMID number" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.PMIDNumber}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Site of Lesion / Tumor") ) { %>
							     <display:column title="Site of Lesion / Tumor" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.siteOfLesionTumor}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Diagnosis") ) { %>
							     <display:column title="Diagnosis" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.diagnosis}"/></camod:highlight>
							     </display:column>	 							
							<% }								
							else if( resultColumns[i].equals("Age of Onset") ) { %>
							     <display:column title="Age of Onset" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.ageOfOnset}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Tumor incidence over lifetime") ) { %>
							     <display:column title="Tumor incidence over lifetime" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.tumorIncidenceOverLifetime}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Site and Diagnosis of Metastasis") ) { %>
							     <display:column title="Site and Diagnosis of Metastasis" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.siteAndDiagnosisOfMetastasis}"/></camod:highlight>
							     </display:column>	 							
							<% }								
							else if( resultColumns[i].equals("Drug / Compound Name") ) { %>
							     <display:column title="Drug / Compound Name" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.drugCompoundName}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Name of Cell line") ) { %>
							     <display:column title="Name of Cell line" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.nameOfCellLine}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Organ / Tissue") ) { %>
							     <display:column title="Organ / Tissue" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.organTissue}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Image Title") ) { %>
							     <display:column title="Image Title" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.imageTitle}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Distributor") ) { %>
							     <display:column title="Distributor" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.distributor}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Cell line") ) { %>
							     <display:column title="Cell line" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.cellLine}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Donor Species") ) { %>
							     <display:column title="Donor Species" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.donorSpecies}"/></camod:highlight>
							     </display:column>	 							
							<% }							
							else if( resultColumns[i].equals("Graft Type") ) { %>
							     <display:column title="Graft Type" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.graftType}"/></camod:highlight>
							     </display:column>	 							
							<% }	
							else if( resultColumns[i].equals("Carcinogen") ) { %>
							     <display:column title="Carcinogen" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.carcinogen}"/></camod:highlight>
							     </display:column>	 							
							<% }
							else if( resultColumns[i].equals("Publications") ) { %>
							     <display:column title="Publications" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.publications}"/></camod:highlight>
							     </display:column>	 							
							<% }			
							else if( resultColumns[i].equals("Microarray") ) { %>
							     <display:column title="Microarray" sortable="true" headerClass="sortable" >
							         <camod:highlight><c:out escapeXml="false" value="${row.microarray}"/></camod:highlight>
							     </display:column>	 							
							<% }																										
							else {}  							
						}
					%>

    	    </display:table>	
	          
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>