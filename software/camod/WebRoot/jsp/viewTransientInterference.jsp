<%
/*
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2007/04/04 13:26:46  pandyas
 * Modified name for conditioning regimen and target site
 *
 * Revision 1.6  2007/03/26 12:06:42  pandyas
 * caMOd 2.3 enhancements for Zebrafish support
 *
 * Revision 1.5  2006/11/17 17:47:26  pandyas
 * Minor format change on transient interference method display
 *
 * Revision 1.4  2006/10/31 17:05:17  pandyas
 * Fixed choose for method=1 (SiRNA title vs Morpholino title)
 * Added more code to allow for html markup in fields
 *
 * Revision 1.3  2006/10/27 18:31:16  pandyas
 * Fixed fields in display page to allow for html markup
 *
 * Revision 1.2  2006/10/17 16:08:28  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.1  2006/05/09 18:59:29  georgeda
 * Changes for searching on transient interfaces
 *
 *
 * $Id: viewTransientInterference.jsp,v 1.8 2007-08-15 16:11:41 pandyas Exp $
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
			<c:forEach var="p" items="${mdl.transientInterferenceCollection}">
			<c:set var="method" value="${p.transientInterferenceMethod}"/>
						<c:choose>
							<c:when test = "${method.id == 1}">
								<td class="formTitle" height="20" colspan="2"> Method: Morpolino model </td>
							</c:when>
							<c:otherwise>
								<td class="formTitle" height="20" colspan="2"> Method: siRNA model </td>
							</c:otherwise>
						</c:choose>			
								
			<tr>
				<td class="formTitleBlue" height="20" colspan="2">
					<camod:highlight><c:out value="${p.targetedRegion}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Source</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.sourceDisplayName}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Type</b></td>
				<td class="resultsBoxGreyEnd">
					<camod:highlight><c:out value="${p.type}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Sequence Direction</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.sequenceDirection}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Targeted Region</b></td>
				<td class="resultsBoxGreyEnd">
					<camod:highlight><c:out value="${p.targetedRegion}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Concentration</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.concentration}" escapeXml="false"/>&nbsp;<c:out value="${p.concentrationUnit}"/></camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Delivery Method</b></td>
				<td class="resultsBoxGreyEnd">
					<camod:highlight><c:out value="${p.deliveryMethodDisplayName}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			
			<c:if test="${method.id == 1}">			
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Target Site</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.targetSite}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>			
			</c:if>
			
			<tr>
				<td class="resultsBoxGrey" width="25%"><b>Visualization Ligands</b></td>
				<td class="resultsBoxGreyEnd">
					<camod:highlight><c:out value="${p.visualLigandDisplayName}" escapeXml="false"/>&nbsp;</camod:highlight>
				</td>
			</tr>
			<tr>
				<td class="resultsBoxWhite" width="25%"><b>Comment</b></td>
				<td class="resultsBoxWhiteEnd">
					<camod:highlight><c:out value="${p.comments}" escapeXml="false"/>&nbsp;</camod:highlight>
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