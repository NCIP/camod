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
		<c:out value="${mdl.modelDescriptor}"/></td>
	</tr>

	<c:forEach var="xt" items="${mdl.xenograftCollection}" 
			       varStatus="stat">
		<tr>
			<td class="formTitleBlue" colspan="2" align="center">
				<c:out value="${xt.modelDescriptor}"/><br/>
				<a href="ViewModelAction.do?unprotected_method=populateModelCharacteristics&aModelID='<c:out value="${xt.id}"/>'"><i>Click here to view additional information on this Xenograft model</i></a>
			</td>
		</tr>
		<tr>
			<td class="WhiteBox"><b>Cell Line/Transplant:</b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${xt.modelDescriptor}"/></td>
		</tr>
	
		<tr>
			<td class="GreyBox"><b>Parental Cell line:</b></td>
			<td class="GreyBoxRightEnd">&nbsp;<c:out value="${xt.parentalCellLineName}"/></td>
		</tr>
	
		<tr>		
			<td class="WhiteBox"><b>Species</b></td>
			<td class="WhiteBoxRightEnd">&nbsp;
				<c:out value="${xt.originSpecies.scientificName}"/>&nbsp;(<c:out value="${xt.originSpecies.abbreviation}"/>)
				<c:out value="${xt.originSpecies.ethnicityStrain}"/>
			</td>
		</tr>
	
        <tr>
			<td class="GreyBox"><b>Amount of Cells<b></td>
			<td class="GreyBoxRightEnd">&nbsp;<c:out value="${xt.cellAmount}"/></td>
		</tr>	
		
        <tr>
			<td class="WhiteBox"><b>Tumor harvest date after transplant:<b></td>
			<td class="WhiteBoxRightEnd">&nbsp;<c:out value="${xt.harvestDate}"/></td>
		</tr>
	
		<tr>		
			<td class="GreyBox"><b>ATCC number of Cell Line:</b></td>
			<td class="GreyBoxRightEnd">&nbsp;<c:out value="${xt.atccNumber}"/></td>   		
		</tr>	
		
		<tr>
			<td class="WhiteBox"><b>Method of Modification:</b></td>
			<td class="WhiteBoxRightEnd">&nbsp;<c:out value="${xt.modificationDescription}"/></td>
		</tr>
	
		<tr>		
			<td class="GreyBox"><b>Genetic Alteration:</b></td>
			<td class="GreyBoxRightEnd">&nbsp;<c:out value="${xt.geneticManipulation}"/></td>
		</tr>
	
		<tr>		
			<td class="WhiteBox"><b>Host Species</b></td>
			<td class="WhiteBoxRightEnd">&nbsp;
				<c:out value="${xt.hostSpecies.scientificName}"/>&nbsp;(<c:out value="${xt.hostSpecies.abbreviation}"/>)
			</td>
		</tr>
	
		<tr>		
			<td class="GreyBox"><b>Host Strain</b></td>
			<td class="GreyBoxRightEnd">&nbsp;<c:out value="${xt.hostSpecies.ethnicityStrain}"/></td>
		</tr>
		
		<tr>		
			<td class="WhiteBox"><b>Site of Administration:</b></td>
			<td class="WhiteBoxRightEnd">&nbsp;<c:out value="${xt.administrativeSite}"/></td>
		</tr>	
	
		<tr>
			<td class="GreyBox"><b>Graft Type</b></td>
			<td class="GreyBoxRightEnd">&nbsp;
			<c:choose>
				<c:when test="${empty xt.graftType}">
					<c:out value="${xt.graftTypeUnctrlVocab}"/>
				</c:when>
				<c:otherwise>
					<c:out value="${xt.graftType}"/>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>

		<tr>
			<td class="WhiteBox"><b>Organ of the Graft/Transplant</b></td>
			<td class="WhiteBoxRightEnd">
				<c:out value="${xt.organ.EVSPreferredDescription}"/>&nbsp;
			</td>
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