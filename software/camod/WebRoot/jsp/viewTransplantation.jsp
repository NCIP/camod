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
		<td class="formTitle" height="20" colspan="2">Transplantation - Model:
		<c:out value="${mdl.modelDescriptor}" escapeXml="false"/></td>
	</tr>

	<c:forEach var="t" items="${mdl.transplantationCollection}" 
			       varStatus="stat">

			<tr>
				<td class="formTitleBlue" height="20" colspan="2">
				<c:out value="${t.name}" escapeXml="false"/>
				</td>
			</tr>
			       
		<tr>
			<td class="GreyBox"><b>Transplantation:</b></td>
			<td class="GreyBoxRightEnd"><c:out value="${t.name}" escapeXml="false"/>&nbsp;</td>
		</tr>
		<tr>		
			<td class="WhiteBox"><b>Donor Species</b></td>
			<td class="WhiteBoxRightEnd">
				<c:choose>
					<c:when test="${not empty t.donorSpecies}">			
					    <c:out value="${t.donorSpecies.displayName}" escapeXml="false"/>&nbsp;
					</c:when>
				    <c:otherwise>
				        <c:if test="${not empty t.strain}">
				        	<c:out value="${t.strain.species.displayName}" escapeXml="false"/>&nbsp;
				        </c:if>
					</c:otherwise>
				</c:choose>&nbsp;		
			</td>
		</tr>
	
		<tr>		
			<td class="GreyBox"><b>Donor Strain</b></td>
			<td class="GreyBoxRightEnd">
				<c:choose>
					<c:when test="${empty t.strain.name}">
						<c:out value="${t.strain.nameAlternEntry}" escapeXml="false"/>
					</c:when>
					<c:otherwise>
						<c:out value="${t.strain.name}" escapeXml="false"/>
					</c:otherwise>
				</c:choose>&nbsp;
            </td>
		</tr>
		<tr>
			<td class="WhiteBox"><b>Organ/Tissue</b></td>
			<td class="WhiteBoxRightEnd">
					<c:out value="${t.organ.EVSPreferredDescription}" escapeXml="false"/>&nbsp;
			</td>
		</tr>
						
		<tr>
			<td class="GreyBox"><b>Source Type</b></td>
			<td class="GreyBoxRightEnd">
			<c:choose>
				<c:when test="${empty t.sourceType}">
					<c:out value="${t.sourceTypeAlternEntry}" escapeXml="false"/>&nbsp;
				</c:when>
				<c:otherwise>
					<c:out value="${t.sourceType}" escapeXml="false"/>&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td class="WhiteBox"><b>Parental Cell line:</b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${t.parentalCellLineName}" escapeXml="false"/>&nbsp;</td>
		</tr>
		<tr>		
			<td class="GreyBox"><b>ATCC number of Cell Line:</b></td>
			<td class="GreyBoxRightEnd">
			<a target="atcc" href="http://www.atcc.org/common/catalog/numSearch/numResults.cfm?atccNum=<c:out value="${t.atccNumber}"/>">
			<c:out value="${t.atccNumber}" escapeXml="false"/>&nbsp;</a></td> 		
		</tr>

		<tr>
			<td class="WhiteBox"><b>Conditioning Regimen</b></td>
			<td class="WhiteBoxRightEnd">
			<c:choose>
				<c:when test="${empty t.conditioningRegimen}">
					<c:out value="${t.condRegimenAlternEntry}" escapeXml="false"/>&nbsp;
				</c:when>
				<c:otherwise>
					<c:out value="${t.conditioningRegimen}" escapeXml="false"/>&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>		

		<tr>		
			<td class="GreyBox"><b>Genetic Alteration:</b></td>
			<td class="GreyBoxRightEnd"><c:out value="${t.geneticManipulation}" escapeXml="false"/>&nbsp;</td>
		</tr>		
		<tr>
			<td class="WhiteBox"><b>Method of Modification:</b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${t.modificationDescription}" escapeXml="false"/>&nbsp;</td>
		</tr>						
        <tr>
			<td class="GreyBox"><B>Amount of Cells</B></td>
			<td class="GreyBoxRightEnd"><c:out value="${t.cellAmount}" escapeXml="false"/>&nbsp;</td>
		</tr>
        <tr>
			<td class="WhiteBox"><b>Growth Period</b></td>
			<td class="WhiteBoxRightEnd"><c:out value="${t.growthPeriod}" escapeXml="false"/>&nbsp;</td>
		</tr>			
		<tr>
			<td class="GreyBox"><b>Site of Administration:</b></td>
			<td class="GreyBoxRightEnd">
			<c:choose>
				<c:when test="${empty t.administrativeSite}">
					<c:out value="${t.adminSiteAlternEntry}" escapeXml="false"/>&nbsp;
				</c:when>
				<c:otherwise>
					<c:out value="${t.administrativeSite}" escapeXml="false"/>&nbsp;
				</c:otherwise>
			</c:choose>
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
		<tr>
			<td class="WhiteBox"><b>Comment</b></td>
			<td class="WhiteBoxRightEnd">
					<c:out value="${t.comments}" escapeXml="false"/>&nbsp;
			</td>
		</tr>		
		<tr><td>&nbsp;</td></tr>
	</c:forEach>
	</TABLE>

<!-- -->
	</td></tr></TABLE>
</td></tr></TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.TRANSPLANTATION); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
	</td></tr>    
</TABLE>

<%@ include file="/jsp/footer.jsp" %>