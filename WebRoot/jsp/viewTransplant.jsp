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
		<td class="formTitle" height="20" colspan="2">Transplant - Model:
		<c:out value="${mdl.modelDescriptor}" escapeXml="false"/></td>
	</tr>

	<c:forEach var="tp" items="${mdl.transplantCollection}" 
			       varStatus="stat">

			<tr>
				<td class="formTitleBlue" height="20" colspan="2">
				<c:out value="${tp.name}" escapeXml="false"/>
				</td>
			</tr>
			       
		<tr>
			<td class="GreyBox"><b>Transplant:</b></td>
			<td class="GreyBoxRightEnd"><c:out value="${tp.name}" escapeXml="false"/>&nbsp;</td>
		</tr>
		<tr>		
			<td class="WhiteBox"><b>Donor Species</b></td>
			<td class="WhiteBoxRightEnd">
				<c:choose>
					<c:when test="${not empty tp.donorSpecies}">			
					    <c:out value="${tp.donorSpecies.displayName}" escapeXml="false"/>&nbsp;
					</c:when>
				    <c:otherwise>
				        <c:if test="${not empty tp.strain}">
				        	<c:out value="${tp.strain.species.displayName}" escapeXml="false"/>&nbsp;
				        </c:if>
					</c:otherwise>
				</c:choose>&nbsp;		
			</td>
		</tr>
	
		<tr>		
			<td class="GreyBox"><b>Donor Strain</b></td>
			<td class="GreyBoxRightEnd">
				<c:choose>
					<c:when test="${empty tp.strain.name}">
						<c:out value="${tp.strain.nameAlternEntry}" escapeXml="false"/>
					</c:when>
					<c:otherwise>
						<c:out value="${tp.strain.name}" escapeXml="false"/>
					</c:otherwise>
				</c:choose>&nbsp;
            </td>
		</tr>
		<tr>
			<td class="WhiteBox"><b>Organ/Tissue</b></td>
			<td class="WhiteBoxRightEnd">
					<c:out value="${tp.organ.EVSPreferredDescription}" escapeXml="false"/>&nbsp;
			</td>
		</tr>
						
		<tr>
			<td class="GreyBox"><b>Source Type</b></td>
			<td class="GreyBoxRightEnd">
			<c:choose>
				<c:when test="${empty tp.sourceType}">
					<c:out value="${tp.sourceTypeAlternEntry}" escapeXml="false"/>&nbsp;
				</c:when>
				<c:otherwise>
					<c:out value="${tp.sourceType}" escapeXml="false"/>&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td class="WhiteBox"><b>Parental Cell line:</b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${tp.parentalCellLineName}" escapeXml="false"/>&nbsp;</td>
		</tr>
		<tr>		
			<td class="GreyBox"><b>ATCC number of Cell Line:</b></td>
			<td class="GreyBoxRightEnd">
			<a target="atcc" href="http://www.atcc.org/common/catalog/numSearch/numResults.cfm?atccNum=<c:out value="${xt.atccNumber}"/>">
			<c:out value="${tp.atccNumber}" escapeXml="false"/>&nbsp;</td> 		
		</tr>

		<tr>
			<td class="WhiteBox"><b>Conditioning Regimen</b></td>
			<td class="WhiteBoxRightEnd">
			<c:choose>
				<c:when test="${empty tp.conditioningRegimen}">
					<c:out value="${tp.condRegimenAlternEntry}" escapeXml="false"/>&nbsp;
				</c:when>
				<c:otherwise>
					<c:out value="${tp.conditioningRegimen}" escapeXml="false"/>&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>		

		<tr>		
			<td class="GreyBox"><b>Genetic Alteration:</b></td>
			<td class="GreyBoxRightEnd"><c:out value="${tp.geneticManipulation}" escapeXml="false"/>&nbsp;</td>
		</tr>		
		<tr>
			<td class="WhiteBox"><b>Method of Modification:</b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${tp.modificationDescription}" escapeXml="false"/>&nbsp;</td>
		</tr>						
        <tr>
			<td class="GreyBox"><b>Amount of Cells<b></td>
			<td class="GreyBoxRightEnd"><c:out value="${tp.cellAmount}" escapeXml="false"/>&nbsp;</td>
		</tr>
        <tr>
			<td class="WhiteBox"><b>Growth Period<b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${tp.growthPeriod}" escapeXml="false"/>&nbsp;</td>
		</tr>			
		<tr>
			<td class="GreyBox"><b>Site of Administration:</b></td>
			<td class="GreyBoxRightEnd">
			<c:choose>
				<c:when test="${empty tp.administrativeSite}">
					<c:out value="${tp.adminSiteAlternEntry}" escapeXml="false"/>&nbsp;
				</c:when>
				<c:otherwise>
					<c:out value="${tp.administrativeSite}" escapeXml="false"/>&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>	
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Comment</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${tp.comments}"escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>			
		<tr>		
			<td class="WhiteBox"><b>Host Species and Strain</b></td>
			<td class="WhiteBoxRightEnd">
				<c:out value="${mdl.strain.species.displayName}" escapeXml="false"/>&nbsp;
                &nbsp;/&nbsp;
				<c:out value="${mdl.strain.displayName}" escapeXml="false"/>&nbsp;
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
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.TRANSPLANT); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>

<%@ include file="/jsp/footer.jsp" %>