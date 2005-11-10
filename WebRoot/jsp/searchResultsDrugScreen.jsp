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
						<!-- preclinical models data-->
						<c:set var="modelList" value="${preClinicalModels[nscnum]}"/>
					  	<tr>
						<td class="formTitleBlue" colspan="2" align="center">
							Summary of preclinical trials in : <br/>
							<b>NSC: &nbsp;&nbsp;<c:out value="${agt.nscNumber}"/><br/>
							CAS: &nbsp;&nbsp; <c:out value="${agt.casNumber}"/><br/>
							</b>
						</td>
						<c:choose>
						<c:when test="${not empty modelList}">
						  	<tr><td colspan="2">
							<table summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
								<c:forEach var="ivd" items="${modelList}" varStatus="stat2">
									<c:choose>
										<c:when test = "${stat2.count % 2 == 0}">
											<c:set var="tdClass" value="resultsBoxWhite"/>
										</c:when>
										<c:otherwise>
											<c:set var="tdClass" value="resultsBoxGrey"/>
										</c:otherwise>
									</c:choose>
									<tr>
										<td align="right" class="<c:out value="${tdClass}"/>"><c:out value="${stat2.count}"/></td>
										<td class="<c:out value="${tdClass}"/>">
										<a href="ViewModelAction.do?unprotected_method=populateModelCharacteristics&<%=Constants.Parameters.MODELID+"="%><c:out value="${ivd[0]}"/>">
											<c:out value="${ivd[1]}"/>
										</a>
										</td>
										<td align="left" class="<c:out value="${tdClass}End"/>"> &nbsp;&nbsp;<c:out value="${ivd[2]}"/>
										</td>
								  	</tr>
							  	</c:forEach>
							</table></td></tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="greySubTitleLeft" colspan=2>
								<b>No cancer models found. </b>
								</td>
							</tr>
						</c:otherwise>
						</c:choose>
						<tr><td colspan="2"> &nbsp; </td></tr>
						<!-- end preclinical models data -->
					</c:if>
					<c:set var="nscnum" value="${agt.nscNumber}"/>
					<c:if test="${searchOption.doClinical}">
						<%@ include file="/jsp/includeClinicalTrials.jsp" %>
					</c:if>
					<c:if test="${searchOption.doYeast}">
					    <c:set var="agentId" value="${agt.id}"/>
						<%@ include file="/jsp/includeYeastData.jsp" %>
					</c:if>
					<c:if test="${searchOption.doInvivo}">
					    <c:set var="agentId" value="${agt.id}"/>
						<%@ include file="/jsp/includeInvivoData.jsp" %>
					</c:if>
	            </c:forEach>
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>