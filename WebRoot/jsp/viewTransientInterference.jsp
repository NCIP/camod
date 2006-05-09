<%
/*
 * $Log: not supported by cvs2svn $
 *
 * $Id: viewTransientInterference.jsp,v 1.1 2006-05-09 18:59:29 georgeda Exp $
 *
 */   
%>
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" 
			align="center" width="100%">	
			<tr>
				<td class="formTitle" height="20" colspan="2">
				    Transient Interfaces - Model: <c:out value="${mdl.modelDescriptor}" escapeXml="false" />
				</td>				
			</tr>
		    <tr>
				<td>&nbsp;</td>
			</tr>
			<c:forEach var="p" items="${mdl.morpholinoCollection}">
			<tr>
				<td class="formTitleBlue" height="20" colspan="2">
					<camod:highlight><c:out value="${p.targetedRegion}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Source</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.sourceDisplayName}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Type</b></td>
				<td class="resultsBoxGreyEnd">
					<camod:highlight><c:out value="${p.type}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Sequence Direction</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.sequenceDirection}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Targeted Region</b></td>
				<td class="resultsBoxGreyEnd">
					<camod:highlight><c:out value="${p.targetedRegion}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Concentration</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.concentration}"/>&nbsp;<c:out value="${p.concentrationUnit}"/></camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Delivery Method</b></td>
				<td class="resultsBoxGreyEnd">
					<camod:highlight><c:out value="${p.deliveryMethodDisplayName}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Visualization Ligands</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.visualLigandDisplayName}"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			</c:forEach>
		
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.TRANSIENT_INTERFERENCE); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>

<%@ include file="/jsp/footer.jsp" %><%@ include file="/jsp/footer.jsp" %>