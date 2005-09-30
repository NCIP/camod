<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<bean:define id="agentList" name="searchResults"/>
<bean:define id="searchOption" name="drugScreenSearchOptions"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitle" height="20" colspan="9">Search Results</td>				
			</tr>
			<tr>
				<td class="resultsBoxWhiteEnd" height="20" colspan="9">
					<c:if test="${empty agentList}">
						<B><I>No compounds with the specified NSC number: 
						<c:out value="${searchOption.NSCNumber}"/><br/>
						</I></B>
					</c:if>
					<c:forEach var="t" items="${agentList}" varStatus="stat">
				       <c:out value="${t.name}"/><br/>
					</c:forEach>
				</td>
			</tr>
			</TABLE>
			<br>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">
				<c:forEach var="agt" items="${agentList}" varStatus="stat">
					<%@ include file="/jsp/includeAgentDetails.jsp" %>
					<c:if test="${searchOption.doPreClinical}">
						<tr>
							<td class="formTitleBlue" height="20" colspan="2">
								Pre-clinical trial(s) in <c:out value="${agt.name}"/>
							</td>
						</tr>	
					</c:if>
					<c:set var="nscnum" value="${agt.nscNumber}"/>
					<c:if test="${searchOption.doClinical}">
						<%@ include file="/jsp/includeClinicalTrials.jsp" %>
					</c:if>
					<c:if test="${searchOption.doYeast}">
						<%@ include file="/jsp/includeYeastData.jsp" %>
					</c:if>
					<c:if test="${searchOption.doInvivo}">
						<%@ include file="/jsp/includeInvivoData.jsp" %>
					</c:if>
	            </c:forEach>
				<tr>
					<td class="WhiteBox" width="100%" colspan="2"> <a href='javascript: rs("commentWin","submitComment.jsp",415,250);'><IMG src="images/comment.gif" border=0 align=middle> <b>Place your comment here</b></a></td>
				</tr>
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>