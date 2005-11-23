<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.domain.Histopathology" %>	
<%@ page import="gov.nih.nci.camod.Constants" %>
<%@ page import="java.util.List" %>

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>
	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
<!-- -->

	<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="left" width="100%">
	<tr>
		<td class="formTitle" height="20" colspan="2">Xenograft/Transplantation - Model:
		<c:out value="${mdl.modelDescriptor}" escapeXml="false"/></td>
	</tr>

	<c:forEach var="xt" items="${mdl.xenograftCollection}" 
			       varStatus="stat">
		<tr>
			<td class="WhiteBox"><b>Cell Line/Transplant:</b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${xt.name}"/>&nbsp;</td>
		</tr>
	
		<tr>		
			<td class="WhiteBox"><b>Species</b></td>
			<td class="WhiteBoxRightEnd">
				<c:out value="${xt.originSpecies.scientificName}"/>&nbsp;
				<c:if test="${not empty xt.originSpecies.abbreviation}">(<c:out value="${xt.originSpecies.abbreviation}"/>)</c:if>
				<c:out value="${xt.originSpecies.ethnicityStrain}"/>
			</td>
		</tr>
		<tr>
			<td class="WhiteBox"><b>Organ of the Graft/Transplant</b></td>
			<td class="WhiteBoxRightEnd">
				<c:out value="${xt.organ.EVSPreferredDescription}"/>&nbsp;
			</td>
		</tr>		
		<tr>
			<td class="GreyBox"><b>Graft Type</b></td>
			<td class="GreyBoxRightEnd">
			<c:choose>
				<c:when test="${empty xt.graftType}">
					<c:out value="${xt.graftTypeUnctrlVocab}"/>&nbsp;
				</c:when>
				<c:otherwise>
					<c:out value="${xt.graftType}"/>&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>

		<tr>
			<td class="GreyBox"><b>Parental Cell line:</b></td>
			<td class="GreyBoxRightEnd"><c:out value="${xt.parentalCellLineName}"/>&nbsp;</td>
		</tr>
		<tr>		
			<td class="GreyBox"><b>ATCC number of Cell Line:</b></td>
			<td class="GreyBoxRightEnd"><c:out value="${xt.atccNumber}"/>&nbsp;</td>   		
		</tr>

		<tr>		
			<td class="GreyBox"><b>Genetic Alteration:</b></td>
			<td class="GreyBoxRightEnd"><c:out value="${xt.geneticManipulation}"/>&nbsp;</td>
		</tr>
		
		<tr>
			<td class="WhiteBox"><b>Method of Modification:</b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${xt.modificationDescription}"/>&nbsp;</td>
		</tr>						
			
        <tr>
			<td class="GreyBox"><b>Amount of Cells<b></td>
			<td class="GreyBoxRightEnd"><c:out value="${xt.cellAmount}"/>&nbsp;</td>
		</tr>	
		<tr>		
			<td class="WhiteBox"><b>Site of Administration:</b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${xt.administrativeSite}"/>&nbsp;</td>
		</tr>	
		<tr>		
			<td class="WhiteBox"><b>Host Species</b></td>
			<td class="WhiteBoxRightEnd">
			    <c:if test="${not empty xt.hostSpecies.scientificName}"><c:out value="${xt.hostSpecies.scientificName}"/></c:if>
				<c:if test="${not empty xt.hostSpecies.abbreviation}}">(<c:out value="${xt.hostSpecies.abbreviation}"/>)</c:if>&nbsp;
			</td>
		</tr>
	
		<tr>		
			<td class="GreyBox"><b>Host Strain</b></td>
			<td class="GreyBoxRightEnd"><c:out value="${xt.hostSpecies.ethnicityStrain}"/>&nbsp;</td>
		</tr>

		<tr><td>&nbsp;</td></tr>
	</c:forEach>
	</TABLE>

<!-- -->
	</td></tr></TABLE>
</tr></td></TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.XENOGRAFT); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>