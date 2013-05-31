<%--L
   Copyright SAIC
   Copyright SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/camod/LICENSE.txt for details.
L--%>

<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	
<%@ page import="gov.nih.nci.camod.Constants.*" %>
<%@ page import="gov.nih.nci.camod.Constants.Dropdowns" %>

<bean:define id="mdl" name="animalmodel"/>

<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<!-- Took this out of sidebar.jsp and has to go here to format correctly -->
<%@ include file="/jsp/searchMenu.jsp" %>
<tr><td>
	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%" >
	<tr><td valign="top" colspan="2">
		<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
		
			<TABLE cellpadding="3" cellspacing="0" border="0" align="center" width="100%">
			<tr>
				<th scope="col" class="formTitle" height="20" colspan="2">
				Images - Model:
				<c:out value="${mdl.modelDescriptor}" escapeXml="false" /></th>				
			</tr>
			<tr><td>&nbsp;</td></tr>			

			<c:forEach var="p" items="${mdl.imageCollection}" varStatus="stat">
				<tr>
					<c:choose>
						<c:when test = "${stat.count % 2 == 0}">
							<c:set var="tdClass" value="resultsBoxWhiteAllSides" />
						</c:when>
						<c:otherwise>
							<c:set var="tdClass" value="resultsBoxGreyAllSides"/>
						</c:otherwise>
					</c:choose>
			
					<td class="<c:out value="${tdClass}"/>" colspan="2">
						<c:if test="${empty p.urlAlternEntry}" >
							<a href='<c:out value="${p.imageUrl}"/>' target="_blank">
							<img src="<c:out value="${p.thumbUrl}"/>" height="40" width="40" alt="Click on the image to open in a new Browser window" target="_blank">( Click to View )</a>
						</c:if>	<br><br/>
					
						<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" align="center" width="100%">
						<tr>
							<td class="resultsBoxWhiteAllSides" width="15%"><b>Title</b></td>
							<td class="resultsBoxWhiteAllSidesNoLeft" width="85%">
							<c:out value="${p.title}"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="resultsBoxGrey" width="15%"><b>Staining</b></td>
							<td class="resultsBoxGreyEnd" width="85%">
							<c:out value="${p.stainingMethod.name}"/>&nbsp;
							</td>
						</tr>				
						<tr>
							<td class="resultsBoxWhite" width="15%"><b>Description</b></td>
							<td class="resultsBoxWhiteEnd" width="85%">
							<c:out value="${p.description}"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="resultsBoxGrey" width="15%"><b>Alternate URL</b></td>
							<td class="resultsBoxGreyEnd" width="85%">
							<a href='<c:out value="${p.urlAlternEntry}"/>' target="_blank"><c:out value="${p.urlAlternEntry}"/></a>&nbsp;<br/>
							<a class="sideMenuLink" href="#" onClick="myRef = window.open('html/disclaimer.html#external','mywin',
										'left=20,top=20,width=800,height=200,status=1,scrollbars=1,toolbar=1,resizable=1');myRef.focus()">Disclaimer</a>
							</td>
						</tr>					
						<tr>
							<td class="resultsBoxWhite" width="25%"><b>Comment</b></td>
							<td class="resultsBoxWhiteEnd">
								<c:out value="${p.comments}"escapeXml="false"/>&nbsp;
							</td>
						</tr>					
						</TABLE><br></br>
				    </td>
				</tr>
            <tr><td>&nbsp;</td></tr>			
			</c:forEach>

            <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.IMAGES); %>
            <%@ include file="/jsp/includeComments.jsp" %>  
			
			</TABLE>
			<tr><td>&nbsp;</td></tr>			
		</td></tr></TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>