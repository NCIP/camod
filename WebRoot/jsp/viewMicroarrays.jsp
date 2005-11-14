<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20">
				Microarrays - Model:
				<c:out value="${mdl.modelDescriptor}" escapeXml="false"/></td>
			</tr>			
			
			<tr>
				<td class="resultsBoxWhiteEnd">Microarray data is stored in caArray.</td> 
			</tr>

			<tr>
				<td class="resultsBoxGreyEnd">
                <ul>
					<bean:define id="maColl" name="mdl" property="microArrayDataCollection"/>
					<c:forEach var="ma" items="${maColl}">
	                	<li>
	                	<c:set var="uri" value="${uri_start}${ma.experimentId}${uri_end}"/>
	                	<a href='<c:out value="${uri}"/>'><c:out value="${ma.experimentName}"/></a>
				        </li>
					</c:forEach>
                </ul>
				</td>
			</tr>
			
            <tr><td></td></tr>
            <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.MICROARRAY); %>
            <%@ include file="/jsp/includeComments.jsp" %>			
			
			</TABLE>
		</td>
		</tr>
		</TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>