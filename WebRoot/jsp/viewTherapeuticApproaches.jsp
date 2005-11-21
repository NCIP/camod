<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>
<bean:define id="ta" name="therapeuticApproachesColl"/>
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitle" height="20" colspan="9">
				Therapeutic Approaches - Model:
				<c:out escapeXml="false" value="${mdl.modelDescriptor}"/></td>				
			</tr>
			<tr>
				<td class="resultsBoxWhiteEnd" height="20" colspan="9">
					<c:forEach var="t" items="${ta}" varStatus="stat">
					    <a href="<c:out value="#therap_${stat.count}"/>">
							 <c:out value="${t.agent.name}"/><br/>
						</a>  
					</c:forEach>
				</td>
			</tr>
			</TABLE>
			<br>	
			<c:forEach var="t" items="${ta}" varStatus="stat">
				<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">
				    <tr><td><a name="<c:out value="therap_${stat.count}"/>"/>&nbsp;</td></tr>
					<c:set var="agt" value="${t.agent}"/>
					<c:set var="agentId" value="${agt.id}"/>
					<c:set var="nscnum" value="${agt.nscNumber}"/>
					<%@ include file="/jsp/includeAgentDetails.jsp" %>
					<%@ include file="/jsp/includePreclinicalTrials.jsp" %>
					<%@ include file="/jsp/includeClinicalTrials.jsp" %>
					<%@ include file="/jsp/includeYeastData.jsp" %>
					<%@ include file="/jsp/includeInvivoData.jsp" %>
			    </TABLE>
			</c:forEach>
			<br/>
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.THERAPEUTIC_APPROACHES); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>