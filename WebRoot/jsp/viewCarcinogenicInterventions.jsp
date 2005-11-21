<%@ include file="/jsp/header.jsp" %>
<%@ include file="/jsp/sidebar.jsp" %>

<%@ page import="java.util.List" %>
<%@ page import="gov.nih.nci.camod.domain.AnimalModel" %>	

<bean:define id="mdl" name="animalmodel"/>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
<tr><td>

	<TABLE summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="100%">
	<tr><td valign="top">
		<TABLE cellpadding="0" cellspacing="0" border="0" class="contentBegins" width="100%">
		<tr><td>
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	

			<tr>
				<td class="formTitle" height="20" colspan="6">
					Carcinogenic Agents - Model:
					<c:out value="${mdl.modelDescriptor}" escapeXml="false"/>
				</td>				
			</tr>
			
			</TABLE>
			
			<br>
			
			<c:set var="agentType" value="Chemical / Drug"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[agentType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">
			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Chemical / Drug</td>				
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="17%">Chemical / Drug</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>				
			</tr>
			
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.agent.name}">
							<c:out value="${cd.agent.nameUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.agent.name}"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:out value="${cd.treatment.dosage}"/></td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:out value="${cd.treatment.regimen}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<c:out value="${cd.treatment.adminRouteUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.treatment.administrativeRoute}"/>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
					<c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>

			<c:set var="agentType" value="Growth Factor"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[agentType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="3">Growth Factor</td>				
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="33%">Growth Factor</td>
				<td class="greySubTitleLeft" width="33%">Dose</td>
				<td class="greySubTitle" width="33%">Treatment Regimen</td>		
			</tr>
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="33%">
					<c:choose>
						<c:when test="${empty cd.agent.name}">
							<c:out value="${cd.agent.nameUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.agent.name}"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="33%">
					<c:out value="${cd.treatment.dosage}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="33%">
					<c:out value="${cd.treatment.regimen}"/>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</TABLE>		
			<br>
			</c:if>

			<c:set var="agentType" value="Radiation"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[agentType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="3">Radiation</td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="33%">Radiation</td>
				<td class="greySubTitleLeft" width="33%">Dose</td>
				<td class="greySubTitle" width="33%">Treatment Regimen</td>		
			</tr>
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="33%">
					<c:choose>
						<c:when test="${empty cd.agent.name}">
							<c:out value="${cd.agent.nameUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.agent.name}"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="33%">
					<c:out value="${cd.treatment.dosage}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="33%">
					<c:out value="${cd.treatment.regimen}"/>&nbsp;
				</td>		
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>
			
			<c:set var="agentType" value="Hormone"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[agentType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="3">Hormone</td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="33%">Hormone</td>
				<td class="greySubTitleLeft" width="33%">Dose</td>
				<td class="greySubTitle" width="33%">Treatment Regimen</td>		
			</tr>
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="33%">
					<c:choose>
						<c:when test="${empty cd.agent.name}">
							<c:out value="${cd.agent.nameUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.agent.name}"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="33%">
					<c:out value="${cd.treatment.dosage}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="33%">
					<c:out value="${cd.treatment.regimen}"/>&nbsp;
				</td>		
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>

			<c:set var="agentType" value="Environment"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[agentType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Environment</td>				
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="17%">Environmental Factor</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>				
			</tr>
			
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:choose>
						<c:when test="${empty cd.agent.name}">
							<c:out value="${cd.agent.nameUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.agent.name}"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:out value="${cd.treatment.dosage}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:out value="${cd.treatment.regimen}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<c:out value="${cd.treatment.adminRouteUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.treatment.administrativeRoute}"/>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
					<c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</TABLE>		
	        <br>
	        </c:if>
     
			<c:set var="agentType" value="Viral"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[agentType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Viral Treatment</td>
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="17%">Virus</td>
				<td class="greySubTitleLeft" width="17%">Dose</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="17%">Administrative Route</td>
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>				
			</tr>
			
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:choose>
						<c:when test="${empty cd.agent.name}">
							<c:out value="${cd.agent.nameUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.agent.name}"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:out value="${cd.treatment.dosage}"/></td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:out value="${cd.treatment.regimen}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:choose>
						<c:when test="${empty cd.treatment.administrativeRoute}">
							<c:out value="${cd.treatment.adminRouteUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.treatment.administrativeRoute}"/>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
					<c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>

			<c:set var="agentType" value="Other"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[agentType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="4">Surgery</td>
			</tr>
			
			<tr>
				<td class="greySubTitleLeft" width="30%">Surgery</td>
				<td class="greySubTitleLeft" width="30%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="20%">Age at Treatment</td>
				<td class="greySubTitle" width="20%">Gender</td>				
			</tr>
			
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>&nbsp;
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<c:choose>
						<c:when test="${empty cd.agent.name}">
							<c:out value="${cd.agent.nameUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.agent.name}"/>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<c:out value="${cd.treatment.regimen}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="20%">
					<c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>

			<c:set var="agentType" value="Nutrition"/>
			<c:set var="cdList" value="${carcinogenicInterventionColl[agentType]}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="5">Nutrition</td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="20%">Nutritional Component / Diet</td>
				<td class="greySubTitleLeft" width="20%">Dose</td>
				<td class="greySubTitleLeft" width="20%">Treatment Regimen</td>
				<td class="greySubTitleLeft" width="20%">Age at Treatment</td>
				<td class="greySubTitle" width="20%">Gender</td>				
			</tr>
			
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>&nbsp;
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:choose>
						<c:when test="${empty cd.agent.name}">
							<c:out value="${cd.agent.nameUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.agent.name}"/>
						</c:otherwise>
					</c:choose>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:out value="${cd.treatment.dosage}"/></td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:out value="${cd.treatment.regimen}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="20%">
					<c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>

			<c:set var="cdList" value="${mdl.geneDeliveryCollection}"/>
			<c:if test="${not empty cdList}">
			<TABLE summary="" cellpadding="3" cellspacing="0" border="0" align="center" width="100%">	
			<tr>
				<td class="formTitleBlue" height="20" colspan="6">Gene Delivery</td>				
			</tr>
			<tr>
				<td class="greySubTitleLeft" width="17%">Viral Vector</td>
				<td class="greySubTitleLeft" width="17%">Gene</td>
				<td class="greySubTitleLeft" width="17%">Organ</td>
				<td class="greySubTitleLeft" width="17%">Treatment Regimen</td>	
				<td class="greySubTitleLeft" width="17%">Age at Treatment</td>
				<td class="greySubTitle" width="17%">Gender</td>
			</tr>
			<c:forEach var="cd" items="${cdList}" varStatus="stat">
				<c:choose>
					<c:when test = "${stat.count % 2 == 0}">
						<c:set var="tdClass" value="resultsBoxWhite"/>
					</c:when>
					<c:otherwise>
						<c:set var="tdClass" value="resultsBoxGrey"/>
					</c:otherwise>
				</c:choose>
			<tr>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<c:choose>
						<c:when test="${empty cd.viralVector}">
							<c:out value="${cd.viralVectorUnctrlVocab}"/>
						</c:when>
						<c:otherwise>
							<c:out value="${cd.viralVector}"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:out value="${cd.geneInVirus}"/>
				</td>
				<td class="<c:out value="${tdClass}"/>" width="30%">
					<c:out value="${cd.organ.EVSPreferredDescription}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="20%">
					<c:out value="${cd.treatment.regimen}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>" width="17%">
					<c:out value="${cd.treatment.ageAtTreatment}"/>&nbsp;
				</td>
				<td class="<c:out value="${tdClass}"/>End" width="17%">
					<c:out value="${cd.treatment.sexDistribution.type}"/>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</TABLE>
			<br>
			</c:if>

		</td></tr></TABLE>
	</td></tr></TABLE>
</tr></td></TABLE>

<TABLE cellpadding="10" cellspacing="0" border="0" class="contentBegins" width="100%" height="100%">
	<tr><td>
	<TABLE summary="" cellpadding="7" cellspacing="0" border="0" align="left" width="100%">
    <% pageContext.setAttribute(Parameters.MODELSECTIONVALUE, Pages.CARCINOGENIC_INTERVENTION); %>
    <%@ include file="/jsp/includeComments.jsp" %>
    </TABLE>
</TABLE>


<%@ include file="/jsp/footer.jsp" %>