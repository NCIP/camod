<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Histopathology" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.List" %>

<%      
	List results = (List) request.getSession().getAttribute( Constants.SEARCH_RESULTS );
	int size = results.size();
	System.out.println( "SIZE: " + size );
%>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	

				<tr>
					<td class="formTitle" height="20" colspan="4">Search Results
					</td>

				</tr>

				<tr>
					<td class="greySubTitleLeft" height="20" colspan="2">Your search returned <font color=red><b>
						<%= size %> 
					</b></font> records</td>
					<td class="greySubTitleRight" align="right" height="20" colspan="2"><b>Page 1 of 3</b> <html:link action="searchResults"><font color=red>1</font></html:link> <html:link action="searchResults">2</html:link> <html:link action="searchResults">3</html:link>next&gt;</td>
				</tr>
			</TABLE>
			
			<br>
			
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	
				<tr>
					<td class="formTitleBlue" width="4%">No.</td>
					<td class="formTitleBlue" width="32%">Model Descriptor</td>
					<td class="formTitleBlue" width="32%">Species</td>
					<td class="formTitleBlue" width="32%">Tumor Sites</td>
				</tr>

		<% if ( size > 0 ) { %>
		<logic:iterate id="aModel" 
						name="searchResults" 
						type="AnimalModel"
						indexId="idx"> <!-- length=20 -->
				<bean:define id="species" 
							name="aModel" 
							property="species"/>
				<% 
					int intIdx = idx.intValue() + 1;
					final String tdClass = ((intIdx%2)==0)?"resultsBoxWhite":"resultsBoxGrey";
				%>
				<tr>
					<td class="<%= tdClass %>" width="4%">
							<%= intIdx %> 
					</td>
					<td class="<%= tdClass %>" width="32%">
						<a href="ViewModelAction.do?unprotected_method=populateModelCharacteristics&<%=Constants.Parameters.MODELID + "=" + aModel.getId()%>">
							<bean:write name="aModel"
								   property="modelDescriptor" 
								   filter="true"/> 
						</a>	             
					</td>
					<td class="<%= tdClass %>" width="32%">
						<bean:write name="species"
							   property="scientificName" 
							   filter="true"/> 
					</td>
					<td class="<%= tdClass %>End" width="32%">
						<b>
						<c:forEach var="o" items="${aModel.distinctOrgansFromHistopathologyCollection}">
							<c:out value="${o}"/><br/>
						</c:forEach>
						</b>&nbsp;
					</td>
				</tr>
		</logic:iterate>
	    <%} else { %>
		     <TR>
		  		<TD class="resultsBoxGreyEnd" colspan=4><B><I>No models found!</I></B> 
		   		</TD>
		     </TR>
		<%}%>		

			</TABLE>
			
			<br>
			
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" width="100%">	
				<tr>
					<td class="greySubTitleLeft" height="20" colspan="2">Your search returned <font color=red>
						<b><%= size %> </b></font> records</td>
					<td class="greySubTitleRight" align="right" height="20" colspan="2"><b>Page 1 of 3</b> <html:link action="searchResults"><font color=red>1</font></html:link> <html:link action="searchResults">2</html:link> <html:link action="searchResults">3</html:link>next&gt;</td>
				</tr>
			
			</TABLE>
			
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>