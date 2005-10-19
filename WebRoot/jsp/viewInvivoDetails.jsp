<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>
<bean:define id="nsc" name="nsc"/>
<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20" colspan="9">
					Xenograft - Model:
					<c:out value="${xm.modelDescriptor}"/>
				</td>				
			</tr>			
			<tr>
				<td class="formTitleBlue" height="20" colspan="9">
					NSC <c:out value="${nsc}"/> - 
					<a target="_nsc" href="http://dtp.nci.nih.gov/dtpstandard/servlet/ChemData?searchtype=NSC&searchlist=<c:out value="${nsc}"/>"> Chemical Data </a>
				</td>				
			</tr>
			<tr><td colspan="9"> &nbsp;</td></tr>
			<tr>
				<td class="greySubTitleLeft" colspan="3">Administration of Drug</td>
				<td class="greySubTitleLeft" colspan="3">Toxicity Results</td>
				<td class="greySubTitle" colspan="3">Experiment Results</td>
			</tr>
			<tr>
				<td class="greySubTitleLeft">Schedule </td>
				<td class="greySubTitleLeft">Route </td>
				<td class="greySubTitleLeft">Vehicle </td>
				<td class="greySubTitleLeft">Dose </td>
				<td class="greySubTitleLeft">Toxicity Evaluation Day</td>
				<td class="greySubTitleLeft">Toxicity Survivors</td>
				<td class="greySubTitleLeft">Endpoint </td>
				<td class="greySubTitleLeft">Evaluation Day</td>
				<td class="greySubTitle">Treated/Controls(%)</td>
			</tr>
			<c:set var="tdClass" value="resultsBoxGrey"/>
			<c:forEach var="r" items="${mdl.invivoResultCollection}" 
				       varStatus="stat">
				<c:if test="${r.agent.nscNumber == nsc}">
				<c:choose>
					<c:when test = "${tdClass == 'resultsBoxGrey'}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
				<tr>
					<td class="<c:out value="${tdClass}"/>"><c:out value="${r.treatment.regimen}"/>&nbsp;</td>
					<td class="<c:out value="${tdClass}"/>"><c:out value="${r.treatment.administrativeRoute}"/>&nbsp;</td>
					<td class="<c:out value="${tdClass}"/>">Other</td>
					<td class="<c:out value="${tdClass}"/>"><c:out value="${r.treatment.dosage}"/>&nbsp;</td>
					<td class="<c:out value="${tdClass}"/>"><c:out value="${r.toxicityEvalDay}"/>&nbsp;</td>
					<td class="<c:out value="${tdClass}"/>"><c:out value="${r.toxicitySurvivors}"/>&nbsp;</td>
					<td class="<c:out value="${tdClass}"/>"><c:out value="${r.endpointCode.description}"/>&nbsp;</td>
					<td class="<c:out value="${tdClass}"/>"><c:out value="${r.evaluationDay}"/>&nbsp;</td>
					<td class="<c:out value="${tdClass}"/>End"><c:out value="${r.percentTreatedControl}"/>&nbsp;</td>
				</tr>
				</c:if>
			</c:forEach>
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>