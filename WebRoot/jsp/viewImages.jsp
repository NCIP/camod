<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%" >
	<tr><td valign="top" colspan="2">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitle" height="20" colspan="2">
				Images - Model:
				<c:out value="${mdl.modelDescriptor}" escapeXml="false" /></td>				
			</tr>			

			<c:forEach var="p" items="${mdl.imageCollectionSorted}" 
				       varStatus="stat">
			<tr>
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			
				<td class="<c:out value="${tdClass}"/>End" colspan="2">
					<a href='<c:out value="${p.imageUrl}"/>'>
					<img src="<c:out value="${p.thumbUrl}"/>" height="40" width="40" alt="Click on the image to open in a new Browser window" target="_blank">
					( Click to View )</a>
					<br>
						<b>Title:</b> <c:out value="${p.title}"/>
					<br/>
					<b>Staining:</b> <c:out value="${p.staining}"/> <br/>
					<b>Description:</b> <c:out value="${p.description}"/>
					<br/>
				</td>
			</tr>
			</c:forEach>

            <tr><td></td></tr>
            <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.IMAGES); %>
            <%@ include file="/jsp/includeComments.jsp" %>
  
			
			</TABLE>
		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<%@ include file="/jsp/footer.jsp" %>