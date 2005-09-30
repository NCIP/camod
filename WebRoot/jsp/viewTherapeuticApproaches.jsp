<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>
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
				<c:out value="${mdl.modelDescriptor}"/></td>				
			</tr>
			<tr>
				<td class="resultsBoxWhiteEnd" height="20" colspan="9">
					<c:forEach var="t" items="${mdl.therapyCollection}" 
					       varStatus="stat">
					       <c:out value="${t.agent.name}"/><br/>
					</c:forEach>
				</td>
			</tr>
			</TABLE>
			<br>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
				<bean:define id="ta" name="therapeuticApproachesColl"/>
				<c:forEach var="t" items="${ta}" varStatus="stat">
					<c:set var="agt" value="${t.agent}"/>
					<%@ include file="/jsp/includeAgentDetails.jsp" %>
					<%@ include file="/jsp/includePreclinicalTrials.jsp" %>
					<c:set var="nscnum" value="${agt.nscNumber}"/>
					<%@ include file="/jsp/includeClinicalTrials.jsp" %>
					<%@ include file="/jsp/includeYeastData.jsp" %>
					<%@ include file="/jsp/includeInvivoData.jsp" %>
	            </c:forEach>
				<tr>
					<td class="WhiteBox" width="100%" colspan="2"> <a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
				</tr>
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>