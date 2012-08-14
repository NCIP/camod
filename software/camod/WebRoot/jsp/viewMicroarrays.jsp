<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>
<%@ include file="/common/taglibs.jsp"%>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>

<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<!-- Took this out of sidebar.jsp and has to go here to format correctly -->
	<%@ include file="/jsp/searchMenu.jsp" %>
	<tr><td>

	<TABLE summary="This table is used to format page content" cellpadding="10" cellspacing="0" border="0" class="contentPage" width="60%" height="100%">
	<tr><td valign="top">
		<TABLE summary="This table is used to format page content" cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<th scope="col" class="formTitle" height="20" colspan="2">
				Microarrays - Model:
				<c:out value="${mdl.modelDescriptor}" escapeXml="false"/></th>
			</tr>			
			
			<bean:define id="maColl" name="mdl" property="microArrayDataCollection"/>
			<c:forEach var="ma" items="${maColl}">
				<c:choose>
					<c:when test="${empty ma.experimentId}">
							<tr>
								<td class="resultsBoxGreyEnd"  colspan="2">
					                <ul>
					                	<li>
					                	<a href='<c:out value="${ma.url}"/>' target="_blank"><c:out value="${ma.experimentName}"/></a>
								        </li>
					                </ul>
								</td>
							</tr>
					</c:when>					
					<c:otherwise>
						<tr>
							<td class="resultsBoxGreyEnd" colspan="2">
				                <ul>
				                	<li>
				                	<c:set var="uri" value="${uri_start}${ma.experimentId}${uri_end}"/>
				                	<a href='<c:out value="${uri}"/>'><c:out value="${ma.experimentName}"/></a>
							        </li>
				                </ul>
							</td>
						</tr>								
					</c:otherwise>			
				</c:choose>	
				<c:set var="maUrl" value="${ma.url}"/>
				<c:set var="relatedModels" value="${relatedModelsByMicroArray[maUrl]}"/>
				<c:if test="${not empty relatedModels}">
				<tr>
					<td class="GreyBox" width="30%"><b>Other caMOD entries citing this MicroArray:</b></td>
					<td class="GreyBoxRightEnd" width="70%">		
						<logic:iterate id="rmdl" name="relatedModels" indexId="idx">	
							<c:forEach  items="${rmdl.modelDescriptor}" >
							<a target="_blank" href="ViewModelAction.do?unprotected_method=populateModelCharacteristics&aModelID=<c:out value="${rmdl.id}"/>"/><c:out value="${rmdl.modelDescriptor}" escapeXml="false"/></a>
							&nbsp;(<c:out value="${rmdl.id}" escapeXml="false"/>)</br>						
							</c:forEach>			
						</logic:iterate>
					</td>
				</tr>
				</c:if>
						
			</c:forEach>	
									
            <tr><td></td></tr>
            <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.MICROARRAY); %>
            <%@ include file="/jsp/includeComments.jsp" %>			
			
			</TABLE>
		</td>
		</tr>
		</TABLE>
	</td></tr></TABLE>
</td></tr></TABLE>

<%@ include file="/jsp/footer.jsp" %>