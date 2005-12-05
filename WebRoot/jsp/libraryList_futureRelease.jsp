<%
/*
 * $Log: not supported by cvs2svn $
 * 
 * $Id: libraryList_futureRelease.jsp,v 1.1 2005-12-05 20:19:21 pandyas Exp $
 *
 */   
%>  
 
<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import='gov.nih.nci.camod.Constants.*' %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
    
<bean:define id="mdl" name="animalmodel"/>
<bean:define id="targetedModColl" name="<%=Constants.TARGETED_MOD_COLL%>"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
	
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
		
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">
			
			<tr>
				<td class="formTitle" height="20" colspan="9" align="center">
					Library List 
				</td>				
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="10%">Title</td>
				<td class="greySubTitleLeft" width="10%">Tissue</td>
				<td class="greySubTitleLeft" width="10%">Histology</td>
				<td class="greySubTitle" width="65%">Keywords</td>
			</tr>

			<c:set var="count" value="0"/>
			<c:forEach var="tm" items="${targetedModColl}">			
			<c:set var="tmId" value="${tm.id}"/>
			<c:set var="gene" value="${targetedModGeneMap[tmId]}"/>				
			
			<c:forEach var="library" items="${gene.libraryCollection}" varStatus="stat">
			<tr>
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<c:out value="${library.name}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<c:out value="${library.tissue.name}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="10%">
					<c:out value="${library.tissue.histology}" />
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="65%">
					<c:out value="${library.keyword}"/>&nbsp;
				</td>					
			</tr>
			</c:forEach>
			
			</c:forEach>			
			
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>


<%@ include file="/jsp/footer.jsp" %>
