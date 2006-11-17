<%

/**
 * 
 * $Id: searchResults.jsp,v 1.25 2006-11-17 17:33:22 pandyas Exp $
 * 
 * $Log: not supported by cvs2svn $
 * Revision 1.24  2006/11/13 19:59:44  pandyas
 * #463	images on image search page do not open
 * Modified link to show complete img src location:
 * <IMG SRC="/camod/images/...">
 *
 * Revision 1.23  2006/11/08 20:13:22  pandyas
 * Removed height and width for mtb_logo
 *
 * Revision 1.22  2006/11/08 19:11:17  pandyas
 * added MTB logo onto view screens for Jackson Lab models
 *
 * Revision 1.21  2006/10/18 18:44:07  pandyas
 * took out image and microarray icon code
 *
 * Revision 1.20  2006/10/17 16:08:28  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.19  2006/08/13 17:43:43  pandyas
 * Updated online help - redefined camod tag by substituting mapId for topic (ePublisher changes)
 *
 * Revision 1.18  2006/05/10 18:03:00  schroedn
 * Fixed crash when loading page with no search results.
 *
 * Revision 1.17  2006/05/10 15:37:23  schroedn
 * Fixed Dup_Name bug
 *
 * Revision 1.16  2006/05/10 14:22:10  schroedn
 * New Features - Changes from code review
 *
 * Revision 1.15  2006/04/28 19:39:21  schroedn
 * Defect # 261, 238
 * Many changes, displays any search result column user has setup, options to save/update Query
 *
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
<%@ page import="gov.nih.nci.camod.util.CriteriaTableUtil" %>
<%@ page import="gov.nih.nci.camod.webapp.form.SearchForm" %>
<%@ page import="gov.nih.nci.camod.webapp.form.SaveQueryForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.Float" %>
<%@ page import="java.util.ArrayList" %>

<%      
	List results = ( List ) request.getSession().getAttribute( Constants.SEARCH_RESULTS );	
	if ( results == null ) 
	{
	    results = new ArrayList();
	    request.getSession().setAttribute( Constants.SEARCH_RESULTS, results );
	}
	
	int size = results.size();
	
	String pageSize = ( String ) request.getSession().getAttribute( Constants.ITEMSPERPAGE );	
	if ( pageSize == null ) 
	{
		pageSize = "15";
	}
		
	String[] resultColumns = ( String[] ) request.getSession().getAttribute( Constants.SEARCHRESULTCOLUMNS );
   	if ( resultColumns == null ) 
   	{
   		resultColumns = new String[] { "Model Descriptor", "Tumor Sites", "Species" };   //default        
  	}  
  	
	//String noSaveOption = request.getParameter( "noSaveOption" );  	  	 
%>

<DIV id="TipLayer" style="visibility:hidden;position:absolute;z-index:1000;top:-100;"></DIV>
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<logic:present name="<%=Constants.NOSAVEOPTION%>">
		<bean:define id="noSaveOption" name="<%=Constants.NOSAVEOPTION%>" />	
	</logic:present>

	<logic:present name="<%=Constants.CRITERIATABLE%>">
		<bean:define id="criteriaTable" name="<%=Constants.CRITERIATABLE%>" />	
	</logic:present>
	
	<logic:present name="<%=Constants.DUP_NAME%>">
		<bean:define id="dupName" name="<%=Constants.DUP_NAME%>" />
	</logic:present>	

	<logic:present name="<%=Constants.DUP_NAME_VALUE%>">
		<bean:define id="dupNameValue" name="<%=Constants.DUP_NAME_VALUE%>" />
	</logic:present>	
			
	<logic:present name="<%=Constants.QUERY_NAME%>">
		<bean:define id="aQueryName" name="<%=Constants.QUERY_NAME%>" />
	</logic:present>
	
	<logic:present name="<%=Constants.RERUN_QUERY%>">
		<bean:define id="reRunQuery" name="<%=Constants.RERUN_QUERY%>" />	
	</logic:present>

	<logic:present name="<%=Constants.ASAVEDQUERYID%>">
		<bean:define id="aSavedQueryId" name="<%=Constants.ASAVEDQUERYID%>" />	
	</logic:present>
	
	<logic:present name="<%=Constants.CURRENTUSER%>">
		<bean:define id="currentUser" name="<%=Constants.CURRENTUSER%>" />	
	</logic:present>
	
	<%							
		request.getSession().setAttribute( Constants.DUP_NAME, "false" );
			
		// Get elapsed time in seconds	
		float elapsedTimeSec = 0;
		if ( (Long) request.getSession().getAttribute( Constants.ELAPSED_TIME ) != null )
		{		
			elapsedTimeSec = ( (Long) request.getSession().getAttribute( Constants.ELAPSED_TIME )).floatValue()/1000;
		} 				
	%>


<!-- searchResults.jsp -->
<!-- Main Content Begins -->
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
						<TD class="formFieldAll"><c:out value="${criteriaTable}" escapeXml="false"/></TD>
					</TR>
				</TABLE>												
				
			    <c:if test="${ dupName == 'true' }">
					<TABLE border="0" class="contentPage" width="100%">
						<TR>
							<TD align="left" colspan="2" width="100%">
								<font color="red">*The name "<c:out value="${dupNameValue}" escapeXml="false"/>" is already being used, please choose a different name.</font><br>
						    </TD>
						</TR>
					</TABLE>	
			    </c:if>
				
				<c:choose>
								    
				    <c:when test="${ noSaveOption == 'true' }">
						<TABLE border="0" class="contentPage" width="100%">
							<TR>
								<TD width="40%">&nbsp;</TD>
								<TD align="right" width="60%">
									<font color="red">Query saved as "<c:out value="${aQueryName}" escapeXml="false"/>".</font><br>
					            </TD>
					        </TR>
				        </TABLE>		
				        <BR>
				    </c:when>
	
				    <c:when test="${ !empty reRunQuery }">
						<TABLE border="0" class="contentPage" width="100%">
							<TR>
								<TD width="40%">&nbsp;</TD>
								<TD align="right" width="60%">
									<font color="red">Query "<c:out value="${aQueryName}" escapeXml="false"/>" ran successfully.</font><br>
					            </TD>
					        </TR>
				        </TABLE>		
				        <BR>
				    </c:when>
	
					<c:when test="${ !empty aSavedQueryId }">
						<html:form action="SaveQueryAction.do?method=save" focus="queryName">
							<TABLE border="0" class="contentPage" width="100%">
								<TR>
									<TD width="20%">&nbsp;</TD>
									<TD align="left" width="80%">				
							 				<html:radio property="saveAsNew" value="no" />Update saved query "<c:out value="${aQueryName}" escapeXml="false"/>" with the new criteria. 
							 				<br>
											<html:radio property="saveAsNew" value="yes" />Save this criteria as a new saved query called <html:text styleClass="formFieldUnSized" value="My Saved Query" property="queryName" size="20"/>.             						           
											<br>
						                <input type="submit" value="Save Query" src="images/savequery.gif" />
						            </TD>
						        </TR>
					        </TABLE>
						</html:form>	
				 	</c:when>
	
				    <c:when test="${ !empty currentUser }">
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
				    </c:when>
				    
				    <c:otherwise>
					   <br>
				    </c:otherwise>
				    		    			    				
			</c:choose>
			
			</td>
		</tr>
		
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	
				<tr>
					<td class="formTitle" height="20" colspan="4">Search Results&nbsp;
					<camod:cshelp topic="search_results_help" key="ignore" image="/camod/images/iconHelp.gif" text=""/>
					</td>
				</tr>
			</TABLE>
			
            <display:table id="row" name="${sessionScope.searchResults}"              		
	          		 pagesize="<%= pageSize %>"
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
									<c:if test="${row.externalSource == 'Jax MTB'}">
										<IMG src="/camod/images/mtb_logo.jpg">
									</c:if>						             
					                 <c:out escapeXml="false" value="${row.modelDescriptor}"/>
									<c:if test="${not empty row.imageTitle}">
										<IMG src="/camod/images/image_icon.jpg" width="20" height="20">
									</c:if>	
									<c:if test="${not empty row.microarray}">
										<IMG src="/camod/images/microarray_icon.jpg" width="20" height="20">
									</c:if>																								                 	
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
							         <camod:highlight><c:out escapeXml="false" value="${row.principalInvestigatorName}"/></camod:highlight>
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