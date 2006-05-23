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
				<td class="formTitleBlue" height="20" colspan="2">
				<c:out value="${xt.xenograftName}"/>
				</td>
			</tr>
			       
		<tr>
			<td class="GreyBox"><b>Cell Line/Transplant:</b></td>
			<td class="GreyBoxRightEnd"><c:out value="${xt.xenograftName}"/>&nbsp;</td>
		</tr>
		<tr>		
			<td class="WhiteBox"><b>Donor Species</b></td>
			<td class="WhiteBoxRightEnd">
				<c:choose>
					<c:when test="${not empty xt.donorSpecies}">			
					    <c:out value="${xt.donorSpecies.displayName}"/>&nbsp;
					</c:when>
				    <c:otherwise>
				        <c:if test="${not empty xt.strain}">
				        	<c:out value="${xt.strain.species.displayName}"/>&nbsp;
				        </c:if>
					</c:otherwise>
				</c:choose>&nbsp;		
			</td>
		</tr>
	
		<tr>		
			<td class="GreyBox"><b>Donor Strain</b></td>
			<td class="GreyBoxRightEnd">
				<c:choose>
					<c:when test="${empty xt.strain.name}">
						<c:out value="${xt.strain.nameUnctrlVocab}"/>
					</c:when>
					<c:otherwise>
						<c:out value="${xt.strain.name}"/>
					</c:otherwise>
				</c:choose>&nbsp;
            </td>
		</tr>
		<tr>
			<td class="WhiteBox"><b>Organ/Tissue</b></td>
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
			<td class="WhiteBox"><b>Parental Cell line:</b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${xt.parentalCellLineName}"/>&nbsp;</td>
		</tr>
		<tr>		
			<td class="GreyBox"><b>ATCC number of Cell Line:</b></td>
			<td class="GreyBoxRightEnd">
			<a target="_atcc" href="http://www.atcc.org/common/catalog/numSearch/numResults.cfm?atccNum=<c:out value="${xt.atccNumber}"/>">
			<c:out value="${xt.atccNumber}"/>&nbsp;</td>   		
		</tr>

		<tr>		
			<td class="WhiteBox"><b>Genetic Alteration:</b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${xt.geneticManipulation}"/>&nbsp;</td>
		</tr>		
		<tr>
			<td class="GreyBox"><b>Method of Modification:</b></td>
			<td class="GreyBoxRightEnd"><c:out value="${xt.modificationDescription}"/>&nbsp;</td>
		</tr>						
        <tr>
			<td class="WhiteBox"><b>Amount of Cells<b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${xt.cellAmount}"/>&nbsp;</td>
		</tr>
        <tr>
			<td class="GreyBox"><b>Growth Period<b></td>
			<td class="GreyBoxRightEnd"><c:out value="${xt.growthPeriod}"/>&nbsp;</td>
		</tr>			
		<tr>
			<td class="WhiteBox"><b>Site of Administration:</b></td>
			<td class="WhiteBoxRightEnd">
			<c:choose>
				<c:when test="${empty xt.administrativeSite}">
					<c:out value="${xt.adminSiteUnctrlVocab}"/>&nbsp;
				</c:when>
				<c:otherwise>
					<c:out value="${xt.administrativeSite}"/>&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>		
		<tr>		
			<td class="GreyBox"><b>Host Species and Strain</b></td>
			<td class="GreyBoxRightEnd">
				<c:out value="${mdl.strain.species.displayName}"/>&nbsp;
                &nbsp;/&nbsp;
				<c:out value="${mdl.strain.displayName}"/>&nbsp;
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